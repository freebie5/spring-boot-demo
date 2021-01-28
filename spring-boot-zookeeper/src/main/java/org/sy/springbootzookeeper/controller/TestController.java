package org.sy.springbootzookeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sy.springbootzookeeper.service.TestService;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/buy")
    public String buy() {
        testService.buy();
        return "ok";
    }

    @RequestMapping("/buy2")
    public String buy2() {
        testService.buy();
        return "ok";
    }

}
