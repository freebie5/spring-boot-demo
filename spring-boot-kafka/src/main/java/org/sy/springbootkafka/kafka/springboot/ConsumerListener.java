package org.sy.springbootkafka.kafka.springboot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Component
public class ConsumerListener {

    private final static Log logger = LogFactory.getLog(ConsumerListener.class);

    @KafkaListener(topics = "test", concurrency = "2")
    public void processMessage(String content) {
        logger.info("kafka消费者收到消息：");
        logger.info(content);
    }

}
