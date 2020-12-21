package org.sy.springbootshardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

@SpringBootApplication
@MapperScan("org.sy.springbootshardingsphere.mapper")
public class SpringBootShardingsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShardingsphereApplication.class, args);
    }

}
