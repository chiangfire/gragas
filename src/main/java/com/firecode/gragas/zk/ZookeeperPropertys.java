package com.firecode.gragas.zk;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix="gragas.zookeeper")
public class ZookeeperPropertys {
	
	private String connectString;
	
	private int sessionTimeout;
	
	private boolean enabled = false;
	

}
