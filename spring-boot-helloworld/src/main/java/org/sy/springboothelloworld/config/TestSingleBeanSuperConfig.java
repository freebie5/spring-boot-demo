package org.sy.springboothelloworld.config;

import org.springframework.context.annotation.Bean;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
public class TestSingleBeanSuperConfig {

    public TestSingleBeanSuperConfig() {
        System.out.println("constructor super");
    }

    @Bean
    public TestConfig testConfig() {
        System.out.println("test");
        addInterceptors();
        return new TestConfig();
    }

    protected void addInterceptors() {
    }

}
