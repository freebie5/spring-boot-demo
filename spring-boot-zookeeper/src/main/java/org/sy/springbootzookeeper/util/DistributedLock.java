package org.sy.springbootzookeeper.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class DistributedLock implements InitializingBean {

    private final static String NAME_SPACE = "locknamespace";
    private final static String ROOT_PATH_LOCK = "/rootlock";
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private CuratorFramework curatorFramework;

    /**
     * 获取分布式锁
     */
    public void acquireDistributedLock(String path) {
        String keyPath = ROOT_PATH_LOCK + path;
        while (true) {
            try {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(keyPath);
                log.info("获取分布式锁成功");
                return;
            } catch (Exception e) {
                log.error("获取分布式锁失败");
                try {
                    if (countDownLatch.getCount() <= 0) {
                        countDownLatch = new CountDownLatch(1);
                    }
                    countDownLatch.await();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 释放分布式锁
     */
    public boolean releaseDistributedLock(String path) {
        try {
            String keyPath = ROOT_PATH_LOCK + path;
            if (curatorFramework.checkExists().forPath(keyPath) != null) {
                curatorFramework.delete().forPath(keyPath);
            }
            log.info("释放分布式锁成功");
        } catch (Exception e) {
            log.error("释放分布式锁失败");
            return false;
        }
        return true;
    }

    /**
     * 创建 watcher 事件
     */
    private void addWatcher(String path) throws Exception {
        PathChildrenCache cache = new PathChildrenCache(curatorFramework, path, false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((client, event) -> {
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String oldPath = event.getData().getPath();
                log.info("上一个节点 "+ oldPath + " 已经被断开");
                if (oldPath.contains(path)) {
                    //释放计数器，让当前的请求获取锁
                    countDownLatch.countDown();
                }
            }
        });
    }

    /**
     * 设置curator workspace，创建分布式锁的根节点，监听根节点
     */
    @Override
    public void afterPropertiesSet() {
        curatorFramework.usingNamespace(NAME_SPACE);
        try {
            if (curatorFramework.checkExists().forPath(ROOT_PATH_LOCK) == null) {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(ROOT_PATH_LOCK);
            }
            addWatcher(ROOT_PATH_LOCK);
            log.info("/rootlock 的 watcher 创建成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
