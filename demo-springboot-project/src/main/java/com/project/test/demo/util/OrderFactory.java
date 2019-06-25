package com.project.test.demo.util;

import java.util.Arrays;

import com.project.test.demo.exception.OrderNotFoundException;
import com.project.test.demo.model.Item;
import com.project.test.demo.model.Order;

public class OrderFactory {

	// Method to create a fake order
	public static Order getOrder(Long id) throws OrderNotFoundException {

		if (id == 1) {
			Order order = new Order();
			Item item = new Item(id,"order 1", 21L, 1.0F);
			order.setItem(Arrays.asList(item));
			return order;
		} else if (id == 2) {
			Order order = new Order();
			Item item = new Item(id,"order 2", 35L, 4.0F);
			order.setItem(Arrays.asList(item));
			return order;
		} else if (id == 3) {
			Order order = new Order();
			Item item = new Item(id,"order 3", 5L, 10.0F);
			order.setItem(Arrays.asList(item));
			return order;
		} else {
			throw new OrderNotFoundException(id);
		}

	}

}
