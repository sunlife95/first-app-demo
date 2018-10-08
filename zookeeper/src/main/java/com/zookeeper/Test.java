package com.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Test {
    public static Logger log = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        log.info("nihao");
        ZookeeperTest zookeeperTest = new ZookeeperTest();
        zookeeperTest.connect("132.232.44.64");
//        zookeeperTest.deleteNode("/test");
//        System.out.println(zookeeperTest.createNode("/test","test".getBytes(),CreateMode.PERSISTENT));
        zookeeperTest.getZooKeeper().getData("/test",watchedEvent -> {
            log.info(watchedEvent.toString());
        },new Stat());
        zookeeperTest.getZooKeeper().setData("/test","hi,i'm slf".getBytes(),zookeeperTest.getZooKeeper().exists("/test",true).getVersion());
        for (int i = 0; i < 5; i++) {
            log.debug((zookeeperTest.createNode("/test/child", null,
                    CreateMode.PERSISTENT_SEQUENTIAL)));
        }
    }
}
