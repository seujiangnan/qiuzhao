package com.jiangnan.qiuzhao.service;

import com.aliyuncs.exceptions.ClientException;
import com.jiangnan.qiuzhao.service.Impl.SendSmsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
class SendSmsServiceTest {
    @Autowired
    private SendSmsService sendSmsService;

    @Value("${access.keyId}")
    private String accessKeyId;

    @Value("${access.keySecret}")
    private String accessSecret;

    @Test
    void sendcheckCode() throws ClientException {
        Map<String, String> stringStringMap = sendSmsService.sendcheckCode("1567021563", "1234");
        System.out.println(stringStringMap.get("Message"));
    }
}