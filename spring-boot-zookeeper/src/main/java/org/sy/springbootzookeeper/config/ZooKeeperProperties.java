package org.sy.springbootzookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "curator")
public class ZooKeeperProperties {

//    #重试次数
//    curator.retryCount=5
//            #重试间隔时间
//    curator.elapsedTimeMs=5000
//            #zookeeper地址
//    curator.connectString=192.168.1.151:2181,192.168.1.152:2181,192.168.1.153:2181
//            #session超时时间
//    curator.sessionTimeoutMs=60000
//            #连接超时时间
//    curator.connectionTimeoutMs=5000
    private int retryCount;
    private int elapsedTimeMs;
    private String connectString;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;

}
