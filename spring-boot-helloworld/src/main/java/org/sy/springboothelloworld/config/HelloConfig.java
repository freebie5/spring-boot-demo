package org.sy.springboothelloworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Configuration
public class HelloConfig implements WebMvcConfigurer {

    @Autowired
    private HelloInterceptor helloInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        System.out.println("hhhhhh");
        registry.addInterceptor(helloInterceptor).addPathPatterns("/**");

    }

}
