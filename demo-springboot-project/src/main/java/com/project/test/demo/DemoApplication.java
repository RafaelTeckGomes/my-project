package com.project.test.demo;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import com.project.test.demo.process.OrderProcessor;
import com.project.test.demo.queue.QueueOrder;
import com.project.test.demo.service.OrderService;

@SpringBootApplication
@EnableCircuitBreaker
public class DemoApplication {

	static Logger logger = Logger.getLogger(DemoApplication.class);

	@Autowired
	private QueueOrder queueOrder;
	
	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);

	}

	@PostConstruct
	public void init() {
		logger.info("Starting OrderProcessor...");
		OrderProcessor sender = new OrderProcessor(queueOrder,orderService);
		new Thread(sender).start();
		logger.info("OrderProcessor has been started...");
	}
}
