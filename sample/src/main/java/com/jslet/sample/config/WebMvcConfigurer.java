package com.jslet.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.jslet.JsletWebConfig;

@Configuration
// @Import({ com.jslet.JsletWebConfig.class })
public class WebMvcConfigurer extends JsletWebConfig {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
		super.addCorsMappings(registry);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
	}

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

}
