package com.jiangnan.qiuzhao.service.Impl;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ActiveMqProduct {
    @Autowired
    private JmsMessagingTemplate jms;

    public String queue(String name,String message){
        jms.convertAndSend(new ActiveMQQueue(name), message);
        return "发送成功";
    }

    public List<String> getmsg(String DestinationName) throws JMSException {
        List<String> list = new LinkedList<>();
        try {
            ConnectionFactory connectionFactory = jms.getConnectionFactory();
            Connection conn = connectionFactory .createConnection();
            conn.start();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(DestinationName);
            QueueBrowser browser = session.createBrowser(queue);
            Enumeration<?> messages = browser.getEnumeration();
            while (messages.hasMoreElements()) {
                TextMessage textMsg = (TextMessage) messages.nextElement();
                String s = jms.receiveAndConvert(DestinationName, String.class);
                list.add(s);
//                System.out.println(textMsg.getText());
            }

            browser.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
