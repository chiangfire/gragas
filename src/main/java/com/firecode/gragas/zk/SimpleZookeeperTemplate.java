package com.firecode.gragas.zk;

import java.io.IOException;
import java.util.function.Function;

import org.apache.zookeeper.ZooKeeper;

public class SimpleZookeeperTemplate implements ZookeeperTemplate{
	
	private final ZookeeperPool pool;
	
	public SimpleZookeeperTemplate(ZookeeperPool pool){
		this.pool = pool;
	}
	
	public <T> T execute(Function<ZooKeeper, T> function){
		ZooKeeper zk = null;
		try {
			zk = pool.borrowed();
			return function.apply(zk);
		} catch (InterruptedException | IOException e) {
			new RuntimeException(e);
		}finally{
			if(zk != null) pool.returned(zk);
		}
		return null;
	}

}
