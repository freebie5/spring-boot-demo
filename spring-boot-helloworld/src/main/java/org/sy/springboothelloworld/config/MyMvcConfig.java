package org.sy.springboothelloworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(myInterceptor).addPathPatterns("/**");

    }

}
