package cn.edu.aqtc.im;

import cn.edu.aqtc.im.launcher.NettyImLaunch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName : ApplicationBootstrap
 * @Description : IM启动类
 * @Author : zhangjj
 * @Date: 2020-04-04
 */
@SpringBootApplication
@Slf4j
public class ApplicationBootstrap implements CommandLineRunner {

    @Autowired
    private NettyImLaunch nettyImLaunch;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyImLaunch.run();
    }
}
