package cn.edu.aqtc.im;

import cn.edu.aqtc.im.launcher.NettyImLaunch;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName : ApplicationBootstrap
 * @Description : IM启动类
 * @Author : zhangjj
 * @Date: 2020-04-04
 */
@SpringBootApplication
@EnableCaching
@Slf4j
public class ApplicationBootstrap implements CommandLineRunner {

    @Autowired
    private NettyImLaunch nettyImLaunch;

    @Autowired
    private StringEncryptor stringEncrypor;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //nettyImLaunch.run();
    }

    public String encrty(String encr) {
        String encrypt = stringEncrypor.encrypt(encr);
        //encrypt  就是加密之后的密文
        return encrypt;
    }

    //解密，输入密文，可解析成明文
    public String decrypt(String encr) {
        String encrypt = stringEncrypor.decrypt(encr);
        return encrypt;
    }
}
