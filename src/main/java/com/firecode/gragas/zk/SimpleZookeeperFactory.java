package com.firecode.gragas.zk;

import java.io.IOException;

import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

/**
 * @author JIANG
 */
public class SimpleZookeeperFactory implements ZookeeperFactory{
	
	private final ZookeeperPropertys propertys;
	
	public SimpleZookeeperFactory(ZookeeperPropertys propertys){
		this.propertys = propertys;
	}

	@Override
	public ZooKeeper make() throws IOException, InterruptedException {
		Object lock = new Object();
		ZooKeeper zk = new ZooKeeper(propertys.getConnectString(),propertys.getSessionTimeout(),(event)->{
			if(event.getState() == Event.KeeperState.SyncConnected){
				synchronized (lock) {
					lock.notify();
				}
			}
		});
		synchronized (lock) {
		    lock.wait();
		}
		return zk;
	}

	@Override
	public boolean validate(ZooKeeper zk) {
		if(zk != null && zk.getState() == States.CONNECTED){
			return true;
		}
		return false;
	}

}
