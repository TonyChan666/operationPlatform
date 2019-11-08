package cn.com.bmsoft.modules.attachment.service.impl;

import cn.com.bmsoft.utils.DateUtils;
import cn.com.bmsoft.modules.attachment.config.SftpProperties;
import cn.com.bmsoft.modules.attachment.service.FileSystemService;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Slf4j
@Service("fileSystemService")
public class FileSystemServiceImpl implements FileSystemService {

    @Autowired
    private SftpProperties sftpProperties;

    // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";

    /**
     * 创建SFTP连接
     *
     * @return
     * @throws Exception
     */
    private ChannelSftp createSftp() throws Exception {
        JSch jsch = new JSch();
        log.info("Try to connect sftp[" + sftpProperties.getUsername() + "@" + sftpProperties.getHost() + "], use password[" + sftpProperties.getPassword() + "]");

        Session session = createSession(jsch, sftpProperties.getHost(), sftpProperties.getUsername(), sftpProperties.getPort());
        session.setPassword(sftpProperties.getPassword());
        session.connect(sftpProperties.getSessionConnectTimeout());

        log.info("Session connected to {}.", sftpProperties.getHost());

        Channel channel = session.openChannel(sftpProperties.getProtocol());
        channel.connect(sftpProperties.getChannelConnectedTimeout());

        log.info("Channel created to {}.", sftpProperties.getHost());

        return (ChannelSftp) channel;
    }

    /**
     * 加密秘钥方式登陆
     *
     * @return
     */
    private ChannelSftp connectByKey() throws Exception {
        JSch jsch = new JSch();

        // 设置密钥和密码 ,支持密钥的方式登陆
        if (StringUtils.isNotBlank(sftpProperties.getPrivateKey())) {
            if (StringUtils.isNotBlank(sftpProperties.getPassphrase())) {
                // 设置带口令的密钥
                jsch.addIdentity(sftpProperties.getPrivateKey(), sftpProperties.getPassphrase());
            } else {
                // 设置不带口令的密钥
                jsch.addIdentity(sftpProperties.getPrivateKey());
            }
        }
        log.info("Try to connect sftp[" + sftpProperties.getUsername() + "@" + sftpProperties.getHost() + "], use private key[" + sftpProperties.getPrivateKey()
                + "] with passphrase[" + sftpProperties.getPassphrase() + "]");

        Session session = createSession(jsch, sftpProperties.getHost(), sftpProperties.getUsername(), sftpProperties.getPort());
        // 设置登陆超时时间
        session.connect(sftpProperties.getSessionConnectTimeout());
        log.info("Session connected to " + sftpProperties.getHost() + ".");

        // 创建sftp通信通道
        Channel channel = session.openChannel(sftpProperties.getProtocol());
        channel.connect(sftpProperties.getChannelConnectedTimeout());
        log.info("Channel created to " + sftpProperties.getHost() + ".");
        return (ChannelSftp) channel;
    }

    /**
     * 上传文件
     *
     * @param fileName
     * @param inputStream
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> uploadFile(String fileName, InputStream inputStream) throws Exception {

        ChannelSftp sftp = this.createSftp();

        Map<String, Object> map = new HashMap<>();

        /*
         * 1、文件按日期分类存放，不区分业务
         * 2、文件名按UUID设置，避免同名文件发生覆盖
         */
        String fileDir = DateUtils.format(new Date(), "yyyy-MM-dd");  //文件夹名
        String fileSuffixName = fileName.substring(fileName.lastIndexOf("."), fileName.length());  //文件后缀，文件类型
        String storageFileName = UUID.randomUUID().toString() + fileSuffixName; // 实际存储的文件名

        map.put("storageFileName", storageFileName);
        map.put("fileType", fileSuffixName);
        map.put("relativePath", sftpProperties.getRoot() + "/" + fileDir);
        map.put("absolutePath", sftpProperties.getRoot() + "/" + fileDir + "/" + storageFileName);

        try {
            sftp.cd(sftpProperties.getRoot());
            log.info("Change path to {}", sftpProperties.getRoot());

            boolean dirs = this.createDirs(fileDir, sftp);
            if (!dirs) {
                log.error("Remote path error. path:{}", fileDir);
                throw new Exception("Upload File failure");
            }
            sftp.put(inputStream, storageFileName);
        } catch (Exception e) {
            log.error("Upload file failure. TargetPath: {}", fileDir, e);
            throw new Exception("Upload File failure");
        } finally {
            this.disconnect(sftp);
        }

        return map;
    }

    /**
     * 上传文件
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> uploadFile(String fileName, File file) throws Exception {
        return this.uploadFile(fileName, new FileInputStream(file));
    }

    /**
     * 下载文件<br/>
     * 该方法会产生临时文件，需要删除掉，file.delete();
     *
     * @param targetPath
     * @return
     * @throws Exception
     */
    @Override
    public File downloadFile(String targetPath) throws Exception {
        ChannelSftp sftp = this.createSftp();
        OutputStream outputStream = null;
        try {
            //sftp.cd(sftpProperties.getRoot());
            //log.info("Change path to {}", sftpProperties.getRoot());

            File file = new File(targetPath.substring(targetPath.lastIndexOf("/") + 1));

            outputStream = new FileOutputStream(file);
            sftp.get(targetPath, outputStream);
            log.info("Download file success. TargetPath: {}", targetPath);
            return file;
        } catch (Exception e) {
            log.error("Download file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Download File failure");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            this.disconnect(sftp);
        }
    }

    /**
     * 删除文件
     *
     * @param targetPath
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteFile(String targetPath) throws Exception {
        ChannelSftp sftp = null;
        try {
            sftp = this.createSftp();
            //sftp.cd(sftpProperties.getRoot());
            sftp.rm(targetPath);
            return true;
        } catch (Exception e) {
            log.error("Delete file failure. TargetPath: {}", targetPath, e);
            throw new Exception("Delete File failure");
        } finally {
            this.disconnect(sftp);
        }
    }

    /**
     * 创建一级或者多级目录
     *
     * @param dirPath
     * @param sftp
     * @return
     */
    private boolean createDirs(String dirPath, ChannelSftp sftp) {
        if (dirPath != null && !dirPath.isEmpty()
                && sftp != null) {
            String[] dirs = Arrays.stream(dirPath.split("/"))
                    .filter(StringUtils::isNotBlank)
                    .toArray(String[]::new);

            for (String dir : dirs) {
                try {
                    sftp.cd(dir);
                    log.info("Change directory {}", dir);
                } catch (Exception e) {
                    try {
                        sftp.mkdir(dir);
                        log.info("Create directory {}", dir);
                    } catch (SftpException e1) {
                        log.error("Create directory failure, directory:{}", dir, e1);
                        e1.printStackTrace();
                    }
                    try {
                        sftp.cd(dir);
                        log.info("Change directory {}", dir);
                    } catch (SftpException e1) {
                        log.error("Change directory failure, directory:{}", dir, e1);
                        e1.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 创建session
     *
     * @param jsch
     * @param host
     * @param username
     * @param port
     * @return
     * @throws Exception
     */
    private Session createSession(JSch jsch, String host, String username, Integer port) throws Exception {
        Session session = null;

        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }

        if (session == null) {
            throw new Exception(host + " session is null");
        }

        session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, sftpProperties.getSessionStrictHostKeyChecking());
        return session;
    }

    /**
     * 关闭连接
     *
     * @param sftp
     */
    private void disconnect(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                    log.info("sftp is closed already");
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}