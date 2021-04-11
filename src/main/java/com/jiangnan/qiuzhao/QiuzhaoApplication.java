package com.jiangnan.qiuzhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class QiuzhaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QiuzhaoApplication.class, args);
    }

}
