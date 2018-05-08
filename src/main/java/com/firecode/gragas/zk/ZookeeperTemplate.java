package com.firecode.gragas.zk;

import java.util.function.Function;

import org.apache.zookeeper.ZooKeeper;

public interface ZookeeperTemplate {
	
	
	public <T> T execute(Function<ZooKeeper, T> function);

	
}
