package org.sy.springboothelloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@RestController
public class HelloWordController {

    @RequestMapping("/hello")
    public String hello(@RequestParam Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "helloworld";
    }

}
