package com.firecode.gragas.zk;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.ZooKeeper;
import org.junit.Assert;
import org.junit.Test;

public class PoolTest {
	
	@Test
	public void test() throws InterruptedException {
		ZookeeperPropertys propertys = new ZookeeperPropertys("127.0.0.1:2181",5000,true);
		ZookeeperFactory zkFactory = new SimpleZookeeperFactory(propertys);
		ZookeeperPool pool = new ZookeeperPool(zkFactory);
		CountDownLatch latch = new CountDownLatch(11);
		
		new Thread(()->{
			for(int i=0;i<1;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					Thread.sleep(20);
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		
		new Thread(()->{
			for(int i=0;i<20;i++){
				try {
					ZooKeeper zk = pool.borrowed();
					pool.returned(zk);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}).start();
		latch.await();
		Assert.assertEquals("空闲数据有误", 8, pool.getIdleNum());
		Assert.assertEquals("全部数量有误", 8,pool.getAllNum());
		Assert.assertEquals("剩余数量有误",0,pool.getSurplus());
	}

}
