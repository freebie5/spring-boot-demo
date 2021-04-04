package org.sy.springbootredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sy.springbootredis.redis.DoubleWriteConsistencyDemo;

/**
 * @Author john
 * @Date 2021/4/4 2:20
 * @Version 1.0
 */

@RestController
public class TestController {

    @Autowired
    private DoubleWriteConsistencyDemo doubleWriteConsistencyDemo;

    @RequestMapping("/getData")
    public String getData(@RequestParam("id") String id) {
        return doubleWriteConsistencyDemo.getData(id);
    }

    @RequestMapping("/updataCacheBeforeUpdateDB")
    public String updataCacheBeforeUpdateDB (@RequestParam("id") String id, @RequestParam("name") String name) {
        doubleWriteConsistencyDemo.updataCacheBeforeUpdateDB(id, name);
        return "ok";
    }

    @RequestMapping("/updateDBBeforeUpdateCache")
    public String updateDBBeforeUpdateCache (@RequestParam("id") String id, @RequestParam("name") String name) {
        doubleWriteConsistencyDemo.updateDBBeforeUpdateCache(id, name);
        return "ok";
    }

    @RequestMapping("/deleteCacheBeforeUpdateDB")
    public String deleteCacheBeforeUpdateDB (@RequestParam("id") String id, @RequestParam("name") String name) {
        doubleWriteConsistencyDemo.deleteCacheBeforeUpdateDB(id, name);
        return "ok";
    }

    @RequestMapping("/updateDBBeforeDelteCache")
    public String updateDBBeforeDelteCache (@RequestParam("id") String id, @RequestParam("name") String name) {
        doubleWriteConsistencyDemo.updateDBBeforeDelteCache(id, name);
        return "ok";
    }

}
