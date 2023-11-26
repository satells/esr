package com.esr.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Remove converter XML caso seja introduzida em alguma dependencia transitiva
 * **/
@Configuration
class WebMvcConfiguration implements WebMvcConfigurer {
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//		List<HttpMessageConverter<?>> convertersXML = converters.stream()
//				.filter(c -> c instanceof MappingJackson2XmlHttpMessageConverter).collect(Collectors.toList());
//
//		converters.removeAll(convertersXML);
	}
}
