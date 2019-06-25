package com.project.test.demo.dao.impl;

import org.springframework.stereotype.Component;

import com.project.test.demo.dao.OrderDao;
import com.project.test.demo.exception.OrderNotFoundException;
import com.project.test.demo.model.Order;
import com.project.test.demo.util.OrderFactory;

@Component
public class OrderDaoImpl implements OrderDao {

	@Override
	public Order getOrderById(Long id) throws OrderNotFoundException {
		return OrderFactory.getOrder(id);
	}

}
