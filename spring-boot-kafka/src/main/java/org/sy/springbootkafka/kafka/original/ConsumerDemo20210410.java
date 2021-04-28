package org.sy.springbootkafka.kafka.original;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author john
 * @Date 2021/4/10 22:55
 * @Version 1.0
 */
@Slf4j
public class ConsumerDemo20210410 {

    private static Properties init() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.141:9092,192.168.1.142:9092,192.168.1.143:9092");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "today");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "today-group");
        return properties;
    }

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer(init());
        consumer.subscribe(Arrays.asList("new"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord record : records) {
                log.info("topic:{},value:{}",record.topic(),record.value());
            }
        }
//        consumer.close();
    }

}
