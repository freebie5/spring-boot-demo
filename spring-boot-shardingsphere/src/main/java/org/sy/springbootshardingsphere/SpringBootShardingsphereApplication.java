package org.sy.springbootshardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class
})
@MapperScan("org.sy.springbootshardingsphere.mapper")
public class SpringBootShardingsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShardingsphereApplication.class, args);
    }

}
