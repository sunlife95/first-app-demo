package com.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZookeeperTest {
    private ZooKeeper zooKeeper ;

    public ZooKeeper connect(String host) throws IOException {
        ZooKeeper zooKeeper = new ZooKeeper(host,2181,watchedEvent -> {
            System.out.println("# connect "+watchedEvent.toString());
        });
        this.zooKeeper = zooKeeper;
        return zooKeeper;
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
    public String createNode(String path, byte[] data, CreateMode createMode) throws KeeperException, InterruptedException {
       return zooKeeper.create(path,data,ZooDefs.Ids.OPEN_ACL_UNSAFE,createMode);
    }
    public void deleteNode(String path) throws KeeperException, InterruptedException {
        zooKeeper.delete(path,zooKeeper.exists(path,true).getVersion());
    }
    public Stat watch(String path) throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        zooKeeper.getData(path,watchedEvent -> {
            System.out.println("# event " + watchedEvent.toString());
        },stat);
        return stat;
    }
    public ZooKeeper getZooKeeper(){
        return this.zooKeeper;
    }
}
