package com.mypoject.crawler.robot;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mypoject.crawler.robot.controller.MyCrawler;

@SpringBootApplication
public class WebCrawlerApplication {
	
	static Logger logger = Logger.getLogger(WebCrawlerApplication.class);

	@Autowired
	private MyCrawler myCrawler;
	
	public static void main(String[] args) {
		SpringApplication.run(WebCrawlerApplication.class, args);
	}

	
	@PostConstruct
	public void init() throws Exception {
		logger.info("Starting WebCrawlerApplication...");
		myCrawler.init();
		logger.info("WebCrawlerApplication has been started...");
	}
}
