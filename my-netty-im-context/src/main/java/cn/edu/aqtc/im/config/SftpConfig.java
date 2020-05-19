package cn.edu.aqtc.im.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SftpConfig
 * @Description : sftp配置
 * @Author : zhangjj
 * @Date: 2020-05-17
 */
@Data
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.client")
@Component
public class SftpConfig {

    private String host;

    private Integer port;

    private String protocol;

    private String username;

    private String password;

    private String root;

    private String privateKey;

    private String passphrase;

    private String sessionStrictHostKeyChecking;

    private Integer sessionConnectTimeout;

    private Integer channelConnectedTimeout;
}
