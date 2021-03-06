package org.sy.springbootzookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author john
 * @Date 2021/1/28 23:39
 * @Version 1.0
 */
@Configuration
public class ZooKeeperConfig {

    @Autowired
    ZooKeeperProperties zooKeeperProperties;

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.newClient(
                zooKeeperProperties.getConnectString(),
                zooKeeperProperties.getSessionTimeoutMs(),
                zooKeeperProperties.getConnectionTimeoutMs(),
                new RetryNTimes(zooKeeperProperties.getRetryCount(), zooKeeperProperties.getElapsedTimeMs()));
    }

}
