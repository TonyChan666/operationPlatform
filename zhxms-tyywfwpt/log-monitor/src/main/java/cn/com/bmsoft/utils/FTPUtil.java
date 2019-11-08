package cn.com.bmsoft.utils;

import cn.com.bmsoft.entity.CollectionTask;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class FTPUtil {

    private static Environment env;
    @Autowired
    public void set(Environment env) {
        this.env = env;
    }


    public static Channel connectSFTP(String username, String host, int port, String password) throws JSchException {
        Session session = null;
        Channel channel = null;
        long start = System.currentTimeMillis();
        JSch jSch = new JSch();
        session = jSch.getSession(username, host, port);
        session.setPassword(password);
        Properties config = new Properties();
        // 设置不用检查hostKey
        // 如果设置成“yes”，ssh就会自动把计算机的密匙加入“$HOME/.ssh/known_hosts”文件，
        // 并且一旦计算机的密匙发生了变化，就拒绝连接
        config.put("StrictHostKeyChecking", "no");
        // UseDNS选项打开状态下,当客户端试图登录OpenSSH服务器时,服务器端先根据客户端的IP地址进行DNS PTR反向查询,查询出客户端的host name，然后根据查询出的客户端host name进行DNS 正向A记录查询，验证与其原始IP地址是否一致
        // 这是防止客户端欺骗的一种手段，但一般我们的IP是动态的，不会有PTR记录的，打开这个选项不过是在白白浪费时间而已。
        // 默认值是 “yes” ，为了提高效率减少时间消耗，则把UseDNS设置为“no”
        // 用域名访问时才将此选项打开
        config.put("UseDNS", "no");
        session.setConfig(config);
        session.connect(15000);
        session.setTimeout(15000);
        channel = session.openChannel("sftp");
        channel.connect();
        long end = System.currentTimeMillis();
        return channel;
    }

    /**
     * 获取服务器上的log文件
     * @return 数据流
     */
    public static List<String> load(CollectionTask task) {
        String url = task.getCollectIp();
        int port = task.getCollectPort();
        String username = task.getCollectUsername();
        String password = task.getCollectPassword();
        String filepath = task.getCollectPath();
        String filename = task.getCollectFileName();

        ChannelSftp channel = null;
        List<String> list = new ArrayList<>();

        //连接远程sftp
        try {
            channel = (ChannelSftp)connectSFTP(username, url, port, password);
            BufferedReader bufferedReader = null;
            channel.cd(filepath);//进入指定目录操作
            InputStream inputStream = channel.get(filename);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null) {
                //....数据处理
                if(!tempString.equals(""))
                    list.add(tempString);
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
        finally {
            if (channel != null) {
                channel.quit();
                channel.disconnect();
                try{
                    if (channel.getSession() != null)
                        channel.getSession().disconnect();
                }catch (JSchException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }
}
