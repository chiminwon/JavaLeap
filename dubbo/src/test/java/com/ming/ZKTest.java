package com.ming;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

public class ZKTest {

    private static final String path = "/testnode/test/node";

    @Test
    public void createNode() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.179.128:2181", 5000, null);
        Stat exist = zooKeeper.exists(path, false);
        if (null == exist) {
            zooKeeper.create(path, "allen3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        zooKeeper.close();
    }

    @Test
    public void createWatch() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.179.128:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() == Event.EventType.NodeDeleted && watchedEvent.getPath().endsWith(path)) {
                    System.out.println("Your thing is not existed!!!");
                }
            }
        });
        Stat exist = zooKeeper.exists(path, false);
        if (exist != null) {
            zooKeeper.delete(path, -1);
        }
        Thread.sleep(5);
        zooKeeper.close();
    }
}
