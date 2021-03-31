package org.sy.springboothelloworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Configuration
public class TestSingleBeanConfig01 extends TestSingleBeanSuperConfig {

//    @Bean
//    public TestConfig testConfig() {
//        return new TestConfig();
//    }

    public TestSingleBeanConfig01() {
        System.out.println("constructor A");
    }

    @Bean
    public A a() {
        return new A();
    }

    @Override
    public void addInterceptors() {
        System.out.println("A");
    }

}
