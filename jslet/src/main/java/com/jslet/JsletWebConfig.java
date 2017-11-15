package com.jslet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jslet.converter.FastJsonRequestMessageConverter;
import com.jslet.converter.FastJsonResponseMessageConverter;

//@Configuration
public class JsletWebConfig extends WebMvcConfigurationSupport {

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(fastJsonRequestEntityMessageConverter());
		converters.add(fastJsonResponseEntityMessageConverter());
		converters.add(fastJsonHttpMessageConverter());
		super.addDefaultHttpMessageConverters(converters);
		super.configureMessageConverters(converters);
	}

	@Bean
	public FastJsonRequestMessageConverter fastJsonRequestEntityMessageConverter() {
		FastJsonRequestMessageConverter bean = new FastJsonRequestMessageConverter();
		setConverterParam(bean);
		return bean;
	}

	@Bean
	public FastJsonResponseMessageConverter fastJsonResponseEntityMessageConverter() {
		FastJsonResponseMessageConverter bean = new FastJsonResponseMessageConverter();
		setConverterParam(bean);
		return bean;
	}

	@Bean
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
		FastJsonHttpMessageConverter bean = new FastJsonHttpMessageConverter();
		setConverterParam(bean);
		return bean;
	}

	private void setConverterParam(FastJsonHttpMessageConverter converter) {
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		converter.setSupportedMediaTypes(mediaTypes);
		converter.setFeatures(SerializerFeature.UseISO8601DateFormat);
	}
}
