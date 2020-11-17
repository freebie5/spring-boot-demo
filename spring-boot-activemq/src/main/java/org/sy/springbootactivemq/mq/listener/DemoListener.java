package org.sy.springbootactivemq.mq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class DemoListener {

    @JmsListener(destination = "myActiveMQ_Queue")
    public void receiveQueue(TextMessage textMessage) throws JMSException {
        String msg = textMessage.getText();
        System.out.println("收到队列消息："+msg);
    }

//    @JmsListener(destination = "myActiveMQ_Topic")
    public void receiveTopic(TextMessage textMessage) throws JMSException {
        String msg = textMessage.getText();
        System.out.println("收到主题消息："+msg);
    }

}
