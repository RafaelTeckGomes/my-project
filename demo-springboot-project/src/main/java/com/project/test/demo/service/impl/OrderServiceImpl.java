package com.project.test.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.test.demo.dao.OrderDao;
import com.project.test.demo.exception.OrderNotFoundException;
import com.project.test.demo.model.Order;
import com.project.test.demo.queue.QueueOrder;
import com.project.test.demo.service.OrderService;
import com.project.test.demo.util.OrderEnum;

@Service
public class OrderServiceImpl implements OrderService {

	Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Autowired
	private QueueOrder queueOrder;
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public void receiveOrder(Order order) {
		queueOrder.addValue(order);

	}

	@Override
	@HystrixCommand(fallbackMethod = "errorProcessOrderFallBack")
	public void processOrder(Order order) throws Exception {
		logger.info(order.getItem().toString());
		logger.info(OrderEnum.ORDER_PROCESSED.msg);
	}

	@SuppressWarnings("unused")
	private void errorProcessOrderFallBack(Order order) {
		logger.info("CIRCUIT BREAKER ENABLED!!! No Response From Order service!");
	}

	@Override
	public Order getOrderById(Long id) throws OrderNotFoundException{
		return orderDao.getOrderById(id);
	}

}
