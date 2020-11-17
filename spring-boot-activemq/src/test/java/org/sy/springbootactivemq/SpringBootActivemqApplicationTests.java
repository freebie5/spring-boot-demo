package org.sy.springbootactivemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

@SpringBootTest
class SpringBootActivemqApplicationTests {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    void test01() {

        jmsMessagingTemplate.convertAndSend("myActiveMQ_Queue", "hello,activemq");

    }

    @Test
    void test02() {

        jmsMessagingTemplate.convertAndSend("myActiveMQ_Topic", "hello,activemq");

    }

}
