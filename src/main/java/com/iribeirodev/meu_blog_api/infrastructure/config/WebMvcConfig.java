package com.iribeirodev.meu_blog_api.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configura uma url base para todos os controllers REST.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("api", HandlerTypePredicate.forAnnotation(RestController.class));
	}
}
