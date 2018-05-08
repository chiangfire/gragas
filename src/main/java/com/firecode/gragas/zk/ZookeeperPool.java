package com.firecode.gragas.zk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.DisposableBean;

public class ZookeeperPool implements DisposableBean{
	
	private List<ZooKeeper> alls = new ArrayList<>();
	private AtomicInteger count = new AtomicInteger(8);
	private LinkedBlockingQueue<ZooKeeper> idles = new LinkedBlockingQueue<>();
	private Object createLock = new Object();
	private final ZookeeperFactory zkFactory;
	
	public ZookeeperPool(ZookeeperFactory zkFactory){
		this.zkFactory = zkFactory;
	}
	
	
	
	public ZooKeeper borrowed() throws InterruptedException, IOException{
		ZooKeeper zk = idles.poll();
		if(zk == null){
			zk = create();
			if(zk == null){
				zk = idles.take();
			}
		}
		if(!zkFactory.validate(zk)){
			if(zk != null){
				System.err.println(zk.getState());
				zk.close();
			}
			alls.remove(zk);
			count.getAndIncrement();
			return borrowed();
		}
		return zk;
	}
	
	
	public void returned(ZooKeeper zk){
		
		idles.offer(zk);
	}
	
	
	private ZooKeeper create() throws IOException, InterruptedException{
		Boolean create = null;
		synchronized (createLock) {
			if(count.getAndDecrement() == 0){
				create = Boolean.FALSE;
				count.getAndIncrement();
			}else{
				create = Boolean.TRUE;
			}
		}
		if(!create.booleanValue()){
			return null;
		}
		ZooKeeper zk = zkFactory.make();
		alls.add(zk);
		return zk;
	}
	
	public int getIdleNum(){
		
		return idles == null ? 0 : idles.size();
	}
	
	public int getAllNum(){
		
		return alls == null ? 0 : alls.size();
	}
	
	public int getSurplus(){
		
		return count.get();
	}

	@Override
	public void destroy() throws Exception {
		if(alls != null){
			alls.forEach(zk->{
				if(zk != null)
					try {
						zk.close();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
			});
		}
		if(idles != null){
			idles.clear();
		}
	}
	
}
