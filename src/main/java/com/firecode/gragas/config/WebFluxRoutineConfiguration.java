package com.firecode.gragas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;

@Configuration
public class WebFluxRoutineConfiguration extends DelegatingWebFluxConfiguration {

	/**
	 * 静态资源映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("*.html","*.js","/w/**")
		        .addResourceLocations("classpath:/webapp/");
		        /*.resourceChain(true)
				.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"))
				.addTransformer(new AppCacheManifestTransformer())*/
	}
	
}
