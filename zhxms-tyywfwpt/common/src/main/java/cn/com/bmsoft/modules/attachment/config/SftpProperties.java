package cn.com.bmsoft.modules.attachment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sftp.client")
@Data
public class SftpProperties {

    /**
     * FTP 协议
     */
    private String protocol;

    /**
     * 地址
     */
    private String host;

    /**
     * 端口号
     */
    private int port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 根路径
     */
    private String root;

    private String privateKey;

    private String passphrase;

    private String sessionStrictHostKeyChecking;

    private int sessionConnectTimeout;

    private int channelConnectedTimeout;

}
