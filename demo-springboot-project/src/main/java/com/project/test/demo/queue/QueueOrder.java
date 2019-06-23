package com.project.test.demo.queue;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.project.test.demo.model.Order;

@Component
public class QueueOrder implements BasicQueue<Order> {
	
	private static Queue<Order> orderQueue = new LinkedList<Order>();

	@Override
	public void addValue(Order value) {
		orderQueue.add(value);
		
	}

	@Override
	public Order getValue() {	
		return orderQueue.poll();
	} 

}
