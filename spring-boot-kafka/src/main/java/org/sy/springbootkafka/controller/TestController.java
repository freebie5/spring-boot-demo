package org.sy.springbootkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class TestController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/hello")
    public String hello() {
        kafkaTemplate.send("test", "hello kafka");
        return "hello kafka";
    }

}
