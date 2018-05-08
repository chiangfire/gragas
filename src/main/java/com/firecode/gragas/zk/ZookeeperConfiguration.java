package com.firecode.gragas.zk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties({ZookeeperPropertys.class})
@ConditionalOnProperty(value="gragas.zookeeper.enabled",matchIfMissing=true)
public class ZookeeperConfiguration {
	
	
	@Bean
	@Scope("singleton")
	public ZookeeperTemplate zookeeperTemplate(ZookeeperPropertys zookeeperPropertys){
		ZookeeperFactory zkFactory = new SimpleZookeeperFactory(zookeeperPropertys);
		ZookeeperPool pool = new ZookeeperPool(zkFactory);
		return new SimpleZookeeperTemplate(pool);
	}
}
