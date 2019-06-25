package com.project.test.demo.dao;

import com.project.test.demo.exception.OrderNotFoundException;
import com.project.test.demo.model.Order;

public interface OrderDao {

	Order getOrderById(Long id) throws OrderNotFoundException;
}
