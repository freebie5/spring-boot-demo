package org.sy.springbootkafka.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
public class ConsumerDemo {

    private static final String BROKERS = "192.168.1.143:9092";
    private static final String TOPIC = "topic-demo";
    private static final String GROUP_ID = "group.demo";

    private static Properties init() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer.client.id.demo");
        //开启手动位移提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return properties;
    }

    public static void main(String[] args) {
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(init());
//        consumer.subscribe(Arrays.asList(topic));
        consumer.assign(Arrays.asList(new TopicPartition(TOPIC, 0)));

        while(true) {
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord<String,String> record:records) {
                System.out.println(record.topic() + "-" + record.partition() + "-" + record.offset());
                System.out.println(record.key() + "-" + record.value());
            }
            //手动位移提交，分为：
            //1同步提交
//            consumer.commitSync();
            //2异步提交
            consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                    if(exception==null) {
                         System.out.println(offsets);
                    } else {
                        exception.printStackTrace();
                    }
                }
            });
        }

//        consumer.close();
    }

}
