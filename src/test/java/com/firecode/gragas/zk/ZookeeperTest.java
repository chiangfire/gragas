package com.firecode.gragas.zk;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import com.firecode.gragas.BaseTest;

public class ZookeeperTest extends BaseTest{
	public static final String ZK_CONECTION_STRING = "127.0.0.1:2181";
	public static final int ZK_TIME_OUT = 5000;
	private static final Object lock = new Object();
	private ZooKeeper zk;
	
	@Before
	public void before() throws IOException, InterruptedException{
		zk = new ZooKeeper(ZK_CONECTION_STRING,ZK_TIME_OUT,event -> {
			//连接并且同步数据
			if(event.getState() == Event.KeeperState.SyncConnected){
				synchronized (lock) {
					lock.notify();
				}
			}
		});
		synchronized (lock) {
			lock.wait(ZK_TIME_OUT);
		}
	}
	
	@Test
	public void test1() throws IOException, InterruptedException, KeeperException{
		Stat stat = new Stat();
		zk.getChildren("/", false, (rc,path,ctx,childrens) ->{
			p(rc);
			p(path);
			p(ctx);
			p(childrens);
			synchronized (lock) {
				lock.notify();
			}
		},stat);
		synchronized (lock) {
			lock.wait();
		}
		p(stat.getMzxid());
	}
}
