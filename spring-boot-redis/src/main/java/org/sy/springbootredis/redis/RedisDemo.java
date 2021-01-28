package org.sy.springbootredis.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Set;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
public class RedisDemo {

    public static void main(String[] args) {
        String ip = "192.168.1.182";
        int port = 6379;
        //Redis链接超时时间300秒
        Jedis jedis = new Jedis(ip, port);
        jedis.auth("123456");
//        String key = "likeTop";
//        jedis.zadd(key, 10, "Java");
//        jedis.zadd(key, 20, "Spring");
//        jedis.zadd(key, 7, "MySQL");
//        jedis.zadd(key, 1, "Redis");
//        jedis.zadd(key, 150, "Kafka");
//        jedis.zadd(key, 50, "NIO");
//        jedis.zadd(key, 20, "Tomcat");
//        jedis.zadd(key, 10, "JMeter");
//        Set<String> result = jedis.zrevrange(key, 0, -1);
//        System.out.println(result);


//        double beforeScore = jedis.zscore(key, "Java");
//        System.out.println(beforeScore);
//        jedis.zincrby(key, 5, "Java");
//        double afterScore = jedis.zscore(key, "Java");
//        System.out.println(afterScore);


//        //分布式锁
//        String disLockKey = "dis_lock";
//        String disLockVal = "true";
//        SetParams setParams = new SetParams();
//        setParams.ex(50);
//        setParams.nx();
//        //一开始不存在key，获取锁成功，返回OK
//        String flag1 = jedis.set(disLockKey, disLockVal, setParams);
//        System.out.println(flag1);
//        //已经存在key，获取锁失败，返回null
//        String flag2 = jedis.set(disLockKey, disLockVal, setParams);
//        System.out.println(flag2);
//        //先删除锁，获取锁成功，返回OK
//        jedis.del(disLockKey);
//        String flag3 = jedis.set(disLockKey, disLockVal, setParams);
//        System.out.println(flag3);


        //异步消息队列
        String asyncQueueKey = "async_queue";
//        //进队
//        jedis.rpush(asyncQueueKey, "apple");
//        jedis.rpush(asyncQueueKey, "banana");
//        jedis.rpush(asyncQueueKey, "pear");
//        //出队
//        System.out.println(jedis.llen(asyncQueueKey));
//        jedis.lpop(asyncQueueKey);
//        System.out.println(jedis.llen(asyncQueueKey));
//        jedis.lpop(asyncQueueKey);
//        System.out.println(jedis.llen(asyncQueueKey));
//        jedis.lpop(asyncQueueKey);
        jedis.rpush(asyncQueueKey, "apple");
        System.out.println(jedis.llen(asyncQueueKey));
        jedis.blpop(asyncQueueKey,"100");
        System.out.println(jedis.llen(asyncQueueKey));
        jedis.blpop(asyncQueueKey, "100");
        System.out.println("end");
    }

}