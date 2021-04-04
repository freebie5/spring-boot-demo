package org.sy.springbootredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(value = {"org.sy.springbootredis.mapper"})
public class SpringBootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class, args);
    }

}
