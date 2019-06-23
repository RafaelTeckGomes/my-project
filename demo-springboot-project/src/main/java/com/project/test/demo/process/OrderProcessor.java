package com.project.test.demo.process;

import org.apache.log4j.Logger;

import com.project.test.demo.model.Order;
import com.project.test.demo.queue.QueueOrder;
import com.project.test.demo.service.OrderService;

public class OrderProcessor implements Runnable {

	Logger logger = Logger.getLogger(OrderProcessor.class);
	
	private QueueOrder queueOrder;
	
	public OrderProcessor(QueueOrder queueOrder,OrderService orderService) {		
		this.queueOrder = queueOrder;
		this.orderService = orderService;
	}

	private OrderService orderService;

	@Override
	public void run() {
		while (true) {
			try {
				Order order = queueOrder.getValue();
				if(order != null) {
					orderService.processOrder(order);
				}	
				Thread.sleep(1000);
			} catch (Exception e) {
				logger.error(e.toString());
			}
		}

	}

}
