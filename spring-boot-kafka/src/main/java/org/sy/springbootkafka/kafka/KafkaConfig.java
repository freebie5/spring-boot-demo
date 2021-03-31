//package org.sy.springbootkafka.kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author john
// * @Date 2021/1/30 23:16
// * @Version 1.0
// */
//@Configuration
//public class KafkaConfig {
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory();
//        concurrentKafkaListenerContainerFactory.setConcurrency(4);
//        return concurrentKafkaListenerContainerFactory;
//    }
//
//    @Bean
//    public ConsumerFactory consumerFactory() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.141:9092,192.168.1.142:9092,192.168.1.143:9092");
//        return new DefaultKafkaConsumerFactory(configs);
//    }
//
//}
