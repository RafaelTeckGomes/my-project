package com.project.test.demo.dao;

import com.project.test.demo.model.Order;
import com.project.test.demo.util.exception.OrderNotFoundException;

public interface OrderDao {

	Order getOrderById(Long id) throws OrderNotFoundException;
}
