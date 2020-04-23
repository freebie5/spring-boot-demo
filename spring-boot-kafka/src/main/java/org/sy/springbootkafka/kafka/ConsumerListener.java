package org.sy.springbootkafka.kafka;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    private final static Log logger = LogFactory.getLog(ConsumerListener.class);

    @KafkaListener(topics = "test")
    public void processMessage(String content) {
        logger.info("kafka消费者收到消息：");
        logger.info(content);
    }

}
