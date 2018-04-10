package com.aidic.springboot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.aidic.springboot.filter.TestFilter;
import com.aidic.springboot.listener.ListenerTest;
import com.aidic.springboot.servlet.TestServlet;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebConfig {

	/**
	 * 注册自定义servlet为bean
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean1() {
	    return new ServletRegistrationBean(new TestServlet(),"/servlet");
	}
	
	
	/**
	 * 注册监听器
	 * 
	 */
	@Bean
	public ServletListenerRegistrationBean<ListenerTest> listenerTest(){
		
		 return new ServletListenerRegistrationBean<ListenerTest>(new ListenerTest());
	}
	
	
	/**
	 * 注册filter，
	 * 
	 */
	@Bean
	public FilterRegistrationBean testFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		Filter filter = new TestFilter();
		filterRegistrationBean.setFilter(filter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		filterRegistrationBean.setUrlPatterns(urls);
		return filterRegistrationBean;
		
	}
	
	
	/**
	 * 注册HttpMessageConverters为bean，整合fastJson
	 * @return
	 */
	@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        
        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        
        return new HttpMessageConverters(converter);

    }
}