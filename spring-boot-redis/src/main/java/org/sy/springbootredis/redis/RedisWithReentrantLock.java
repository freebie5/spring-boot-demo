package org.sy.springbootredis.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
public class RedisWithReentrantLock {

    private ThreadLocal<Map<String,Integer>> locks = new ThreadLocal<>();

    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    private boolean _lock(String key) {
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(5000);
        return jedis.set(key, "", setParams)!=null;
    }

    private void _unlock(String key) {
        jedis.del(key);
    }

    private Map<String,Integer> currentLocks() {
        Map<String,Integer> refs = locks.get();
        if(refs!=null) {
            return refs;
        }
        locks.set(new HashMap<String,Integer>(16));
        return locks.get();
    }

    public boolean lock(String key) {
        Map<String, Integer> refs = currentLocks();
        Integer lockCount = refs.get(key);
        //已经持有锁，则锁次数加一
        if(lockCount!=null) {
            refs.put(key, lockCount+1);
            return true;
        }
        //第一次获取锁
        boolean ok = this._lock(key);
        if(!ok) {
            return false;
        } else {
            refs.put(key,1);
            return true;
        }
    }

    public boolean unlock(String key) {
        Map<String, Integer> refs = currentLocks();
        Integer lockCount = refs.get(key);
        //没有锁
        if(lockCount==null) {
            return false;
        }
        //有锁，则锁次数减一
        lockCount--;
        if(lockCount>0) {
            refs.put(key, lockCount);
        } else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        String ip = "192.168.1.182";
        int port = 6379;
        Jedis jedis = new Jedis(ip, port);
        jedis.auth("123456");
        String key = "myLock";
        RedisWithReentrantLock lock = new RedisWithReentrantLock(jedis);
        System.out.println(lock.lock(key));
        System.out.println(lock.lock(key));
        System.out.println(lock.unlock(key));
        System.out.println(lock.unlock(key));
    }

}
