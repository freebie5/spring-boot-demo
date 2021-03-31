package org.sy.springboothelloworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Configuration
public class TestSingleBeanConfig02 extends TestSingleBeanSuperConfig {

//    @Bean
//    public TestConfig testConfig() {
//        return new TestConfig();
//    }

    public TestSingleBeanConfig02() {
        System.out.println("constructor B");
    }

    @Bean
    public B b() {
        return new B();
    }

    @Override
    public void addInterceptors() {
        System.out.println("B");
    }

}
