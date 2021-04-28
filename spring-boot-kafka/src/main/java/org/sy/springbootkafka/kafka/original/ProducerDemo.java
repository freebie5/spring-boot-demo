package org.sy.springbootkafka.kafka.original;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
public class ProducerDemo {

    private static final String BROKERS = "192.168.1.143:9092";
    private static final String TOPIC = "topic-demo";

    public static Properties init() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        //消息可靠性的配置
        //acks=0，不需要等待任何服务端的响应。
        //acks=1，leader副本写入成功，broker就发响应。
        //acks=-1或者acks=all，需要等待ISR中的所有副本都成功，broker才发响应。
        //特定情况下ISR只有leader副本，那么退化为acks=1的情况。配置min.insync.replicas参数获得更高的可靠性。
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");//acks的值是一个字符串

        return properties;
    }

    private static final int FIRE_AND_FORGET = 0;
    private static final int SYNC = 1;
    private static final int ASYNC = 2;

    public static void main(String[] args) {
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(init());
        ProducerRecord<String,String> record = new ProducerRecord<String, String>(TOPIC, 0, "dd", "{\"name\":\"t\"}");

        //默认分区器
        //可以指ProducerRecord类的入参指定partition和key
        //public ProducerRecord(String topic, Integer partition, Long timestamp, K key, V value, Iterable<Header> headers)
        //1 指定了partition的话，就发送消息到指定的partition
        //2 未指定partition的话，按照下面的规制：
        //2.1 key=null时，轮询的方式，发送消息到“可用”分区中的一个
        //2.2 key!=null时，对key进行hash再求余的方式，发送消息到“所有”分区中的一个


        int sendType = ASYNC;
        switch (sendType) {
            //发后即忘，不关心消息是否送达
            case FIRE_AND_FORGET:
                producer.send(record);
                break;
            //同步方式发送消息，可靠性高
            case SYNC:
                try {
                    Future<RecordMetadata> future = producer.send(record);
                    RecordMetadata metadata = future.get();
                    System.out.println(metadata.topic() + "-" + metadata.partition() + "-" + metadata.offset());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            //异步方式发送消息，可靠，同一个主题的分区，先发送的消息，先异步处理
            case ASYNC:
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if(exception!=null) {
                            exception.printStackTrace();
                        } else {
                            System.out.println(metadata.topic() + "-" + metadata.partition() + "-" + metadata.offset());
                        }
                    }
                });
                break;
            default:
                break;
        }
        //关闭生产者
        producer.close();
    }

}
