package com.track;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooTest {

    public static void main(String[] args) {
        try {

//            zookeeperTestCreat();
            zookeeperTestSelect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void zookeeperTestCreat() throws Exception{
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        ZooKeeper zooKeeper=
                new ZooKeeper("123.56.225.139:2181",
                        4000, new Watcher() {
                    public void process(WatchedEvent event) {
                        if(Event.KeeperState.SyncConnected==event.getState()){
                            //如果收到了服务端的响应事件，连接成功
                            countDownLatch.countDown();
                        }
                    }
                });
        countDownLatch.await();

        //CONNECTED
        System.out.println("State");
        System.out.println(zooKeeper.getState());
        // 创建结点
        zooKeeper.create("/dubbo1","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    }

    // 查询节点
    public static void zookeeperTestSelect() throws Exception {
        CuratorFramework curatorFramework= CuratorFrameworkFactory.
                builder().connectString("123.56.225.139:2181").
                sessionTimeoutMs(4000).retryPolicy(new
                ExponentialBackoffRetry(1000,3)).
                namespace("").build();
        curatorFramework.start();
        Stat stat=new Stat();
        //查询节点数据
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/dubbo1");
        System.out.println(new String(bytes));
        curatorFramework.close();
    }

}
