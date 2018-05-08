package com.firecode.gragas.zk;

import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;

/**
 * ZooKeeper创建工厂
 * @author JIANG
 */
public interface ZookeeperFactory {
	
	public ZooKeeper make()throws IOException,InterruptedException;
	
	public boolean validate(ZooKeeper zk);
}
