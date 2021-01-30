package org.sy.springbootapollo.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author john
 * @Date 2021/1/30 21:23
 * @Version 1.0
 */
@Slf4j
@RestController
public class TestApolloController {

    @ApolloConfig("application")
    private Config config;

    @ApolloConfig("test01")
    private Config test01Config;

    @RequestMapping("/test")
    public String test() {
        String test01 = config.getProperty("hello.world", "111");
        boolean isOpen = test01Config.getBooleanProperty("openlog", true);
        if(isOpen) {
            log.info("open log");
        }
        return test01;
    }

}
