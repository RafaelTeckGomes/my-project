package com.project.test.demo.service;

import com.project.test.demo.exception.OrderNotFoundException;
import com.project.test.demo.model.Order;

public interface OrderService {
	
	public void receiveOrder(Order order);
	
	public void processOrder(Order order) throws Exception;
	
	public Order getOrderById(Long id) throws OrderNotFoundException;

}
