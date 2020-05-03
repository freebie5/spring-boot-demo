package org.sy.springbootzookeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sy.springbootzookeeper.util.DistributedLock;

@Service
public class TestService {

    @Autowired
    private DistributedLock distributedLock;

    public boolean buy() {
        String lockName = "/buylock";
        distributedLock.acquireDistributedLock(lockName);
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        distributedLock.releaseDistributedLock(lockName);
        return true;
    }

}
