package com.project.test.demo.service;

import com.project.test.demo.model.Order;
import com.project.test.demo.util.exception.OrderNotFoundException;

public interface OrderService {
	
	public void receiveOrder(Order order);
	
	public void processOrder(Order order) throws Exception;
	
	public Order getOrderById(Long id) throws OrderNotFoundException;

}
