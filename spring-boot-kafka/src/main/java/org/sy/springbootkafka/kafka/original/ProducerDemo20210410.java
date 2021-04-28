package org.sy.springbootkafka.kafka.original;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @Author john
 * @Date 2021/4/10 22:35
 * @Version 1.0
 */
@Slf4j
public class ProducerDemo20210410 {

    private static Properties init() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.141:9092,192.168.1.142:9092,192.168.1.143:9092");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    private static void fire4ForgetSend() {
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(init());
        ProducerRecord<String, String> record = new ProducerRecord<>("new", "{'name':'fire4Forget'}");
        producer.send(record);
        producer.close();
    }

    private static void asyncSend() {
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(init());
        ProducerRecord<String, String> record = new ProducerRecord<>("new", "{'name':'async'}");
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception!=null) {
                    log.error("发送消息异常", exception);
                }
                if(metadata!=null) {
                    System.out.println("发送消息到主题："+metadata.topic());
                }
            }
        });
        producer.close();
    }

    private static void syncSend() {
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(init());
        ProducerRecord<String, String> record = new ProducerRecord<>("new", "{'name':'sync'}");
        Future<RecordMetadata> future = producer.send(record);
        try {
            RecordMetadata metadata = future.get();
            System.out.println("发送消息到主题："+metadata.topic());
        } catch (Exception e) {
            log.error("发送消息异常", e);
        }
        producer.close();
    }

    public static void main(String[] args) {
        //发后即忘
        fire4ForgetSend();
        //异步发送
        asyncSend();
        //同步发送
        syncSend();
    }

}
