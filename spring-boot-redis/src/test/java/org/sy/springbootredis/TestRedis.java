package org.sy.springbootredis;

import redis.clients.jedis.Jedis;

/**
 * @Author john
 * @Date 2021/3/11 21:48
 * @Version 1.0
 */
public class TestRedis {

    public static void main(String[] args) {
        String ip = "192.168.1.181";
        int port = 6379;
        //Redis链接超时时间300秒
        Jedis jedis = new Jedis(ip, port);
        jedis.auth("123456");
        jedis.select(4);
        //插入10w数据
        for(int i=1;i<100000;i++) {
            String key = "qps";
            String value = "http"+i;
            jedis.pfadd(key, value);
        }
        System.out.println("finish");
    }

}
