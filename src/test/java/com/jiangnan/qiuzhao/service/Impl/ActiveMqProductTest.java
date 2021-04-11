package com.jiangnan.qiuzhao.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class ActiveMqProductTest {
    @Autowired
    private ActiveMqProduct activeMqProduct;
    @Test
    void queue() {
        activeMqProduct.queue("enenen","222");
    }

    @Test
    void getmsg() throws JMSException {
        List<String> enenen = activeMqProduct.getmsg("enenen");
        System.out.println(enenen);
    }
}