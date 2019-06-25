package com.project.test.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.test.demo.exception.OrderNotFoundException;
import com.project.test.demo.model.Order;
import com.project.test.demo.service.OrderService;
import com.project.test.demo.util.OrderEnum;

@RestController
@RequestMapping(path = "/services")
public class MyController {

	Logger logger = Logger.getLogger(MyController.class);

	@Autowired
	private OrderService orderService;
	
	
	//Method receive a xml payload and add a queue to process async.
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, value = "/processOrder")
	public ResponseEntity<String> process(@RequestBody Order order) {
		if (order != null) {
			logger.info("Order received: " + order.getItem().toString());
			orderService.receiveOrder(order);
			return ResponseEntity.ok(OrderEnum.ORDER_ADD_QUEUE.msg);
		}
		return ResponseEntity.noContent().build();
	}

	//Method to return a order in json format.
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/getOrder")
	public ResponseEntity<Object> process(@RequestParam Long id) {
		if (id != null) {
			logger.info("Order id received: " + id);
			Order order;
			try {
				order = orderService.getOrderById(id);
			} catch (OrderNotFoundException e) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(order);
		}
		return ResponseEntity.noContent().build();
	}

}
