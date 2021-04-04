package org.sy.springbootredis.redis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.sy.springbootredis.entity.Salary;
import org.sy.springbootredis.mapper.SalaryMapper;

/**
 * @Author john
 * @Date 2021/4/1 23:44
 * @Version 1.0
 */
@Service
public class DoubleWriteConsistencyDemo {

    private final Logger logger = LoggerFactory.getLogger(DoubleWriteConsistencyDemo.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SalaryMapper salaryMapper;

    /**
     * 数据库和缓存双写必然存在数据不一致的问题。
     * 如果对数据有强一致性要求，就不能把数据同时放缓存和数据库。
     * 以下方案，都只能保证最终一致性。
     * 严格来说，以下方案只是降低不一致发生的概率，无法完全避免。
     * 给缓存设置过期时间是保证数据库和缓存最终一致性的兜底方案。
     * 以下方案不依赖于设置缓存过期时间兜底。
     */

    /**
     * 方案一：先更新缓存，再更新数据
     *
     * 这个方案完全不可取。
     * 假设缓存更新成功了，但是数据库更新失败。那么数据就没有持久化到数据库。
     * 如果此时有查询请求，那么会直接获取到缓存数据，读取到的是无效的数据。
     */
    public void updataCacheBeforeUpdateDB(String id, String data) {
        //更新缓存
        setData2Redis(id, data);
        if("1".equals(id)) {
            throw new RuntimeException();
        }
        //更新数据库
        setData2DB(id, data);
    }

    /**
     * 方案二：先更新数据库，再更新缓存
     *
     * 这个方案也是不可取的
     * 原因有2个：
     * 1）脏数据
     * 假设两个写请求A和B，两个请求的执行过程如下：
     * A请求修改数据库
     * B请求修改数据库
     * B请求修改缓存
     * A请求修改缓存
     * 理论上，A请求先执行的，A请求的数据要先更新到缓存，但是由于网络延迟等原因，B请求的数据反而先更新到缓存。
     * 如果此时要读取请求，那么读取到的缓存就是脏数据
     *
     * 2）假设是写多读少的场景，缓存可能还未被读取就被更新了，如果缓存的数据需要复杂的计算过程，那么就会浪费cpu资源
     */
    public void updateDBBeforeUpdateCache(String id, String data) {
        //更新数据库
        setData2DB(id, data);
        if("hello".equals(data)) {
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //更新缓存
        setData2Redis(id, data);
    }

    /**
     * 方案三：先删除缓存，再更新数据库
     *
     * 该方案也会存在缓存和数据库不一致的问题
     *
     * 场景一：假设写请求A，读请求B，按照如下顺序：
     * 1）写请求A删除缓存
     * 2）读请求B查询不到缓存，直接查询数据库，并缓存数据
     * 3）写请求A保存修改到数据库
     *
     * 解决方案：延迟双删
     * 1）写请求A删除缓存
     * 2）读请求B查询不到缓存，直接查询数据库，并缓存数据
     * 3）写请求A保存修改到数据库
     * 4）延迟X秒后，写请求A删除缓存
     *
     * 延迟的时间根据读请求来确定，假设读请求B耗时0.1秒，那么延迟X>01秒即可
     *
     * 场景二：数据库主从复制导致的不一致，假设写请求A，读请求B，按照如下顺序：
     * 1）写请求A删除缓存，保存修改到数据库
     * 2）读请求B查询从数据库，由于数据库主从复制的延迟，查询到旧值，读请求B把旧值缓存
     * 3）从数据库同步到主数据库的值
     *
     * 解决方案还是延迟双删，只是延迟时间变成大于主从数据库同步的时间
     *
     * 采用延迟双删有个问题，就是吞吐量小，每次写请求都要延迟X秒，解决方案是异步延迟删除。
     * 但是如果异步延迟删除失败，还是会有数据不一致的问题，如何解决？重试机制！！！
     *
     * 就是异步删除缓存失败的时候发送一个消息到消息中间件，然后由消息消费者重试删除缓存
     *
     */
    public void deleteCacheBeforeUpdateDB(String id, String data) {
        //删除缓存
        deleteDataFromRedis(id);
        try {
            Thread.sleep(50*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //更新数据库
        setData2DB(id, data);
        //延迟删除
//        delayDelete(id);
        asynDelayDelete(id);
    }
    private void delayDelete(String id) {
        try {
            Thread.sleep(30*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        if(true) {
//            throw new RuntimeException();
//        }
        deleteDataFromRedis(id);
    }
    private void asynDelayDelete(String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                delayDelete(id);
            }
        }).start();
    }

    /**
     * 方案四：先更新数据库，再删除缓存
     *
     * 这个方案是facebook也在使用的最佳实践
     * 还是有数据不一致的情况存在，例如：写请求A，读请求B，按照如下顺序：
     * 1）缓存刚好失效，读请求B查询数据库得到旧值
     * 2）写请求A保存修改到数据库
     * 3）写请求A删除缓存
     * 4）读请求B缓存旧值
     *
     * 上述情况需要比较极端的情况才会出现，因为一般情况下数据库的读操作比操作写要快。
     * 如果真的出现上述情况，解决方案还是：延迟双删
     * 为了提供吞吐量，那么就是：异步延迟双删
     *
     * 异步删除失败的解决方案是：重试机制
     *
     * 重试机制的实现方式有多种，常见：消息队列实现的重试机制
     */
    public void updateDBBeforeDelteCache(String id, String data) {
        //更新数据库
        setData2DB(id, data);
        //删除缓存
        deleteDataFromRedis(id);
        //
        asynDelayDelete(id);
    }

    /*
    * 读取数据
    * 先读取缓存，如果没有缓存，再读取数据
    * 数据库存在要读取的数据，则缓存数据，然后返回，否则，直接返回
    */
    public String getData(String id) {
        String data = null;
        //从缓存读取数据
        data = getDataFromRedis(id);
        //缓存存在，直接返回
        if(null!=data) {
            //返回
            return data;
        }
        //从数据库读取数据
        data = getDataFromDB(id);
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //数据库存在，缓存到redis
        if(null!=data) {
            setData2Redis(id, data);
        }
        return data;
    }

    /**
     * 删除缓存
     */
    public void deleteDataFromRedis(String id) {
        stringRedisTemplate.delete(id);
    }

    /**
     * 更新缓存
     */
    public void setData2Redis(String id, String data) {
        try {
            stringRedisTemplate.opsForValue().set(id, data);
        } catch (Exception e) {
            logger.error("更新缓存异常，id：{}，data：{}", id, data, e);
        }
    }

    /**
     * 读取缓存
     */
    public String getDataFromRedis(String id) {
        String value = stringRedisTemplate.opsForValue().get(id);
        return value;
    }

    /**
     * 更新数据库
     */
    public void setData2DB(String id, String data) {
//        if("2".equals(id)) {
//            throw new RuntimeException();
//        }
        QueryWrapper<Salary> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        Salary salary = Salary.builder().name(data).build();
        salaryMapper.update(salary, queryWrapper);
    }

    /**
     * 读取数据库
     */
    public String getDataFromDB(String id) {
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Salary salary = salaryMapper.selectOne(queryWrapper);
        if(salary!=null) {
            return salary.getName();
        }
        return null;
    }

}
