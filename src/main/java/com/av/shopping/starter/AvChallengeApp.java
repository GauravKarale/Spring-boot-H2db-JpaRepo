package com.av.shopping.starter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * 
 * Author: Gaurav Karale
 * version 1.0.0
 * Date :- 06/05/2017
 * Info :-Spring boot starter class for AV shopping api 
 * */
@SpringBootApplication
@EnableCaching
@EnableJpaRepositories("com.av.shopping.repository")
@EntityScan("com.av.shopping.model")
@ComponentScan("com.av.shopping.service")
@ComponentScan("com.av.shopping.exception")
@ComponentScan("com.av.shopping.controller")
@ComponentScan("com.av.shopping.configuration")
public class AvChallengeApp {

	public static void main(String[] args) {
		new SpringApplicationBuilder() 
		.sources(AvChallengeApp.class)
		.run(args);
	}
}
