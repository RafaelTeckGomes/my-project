package com.project.test.demo.util;

public enum OrderEnum {

	ORDER_ADD_QUEUE("Order has been in the processing queue!"),
	ORDER_PROCESSED("Order has been processed!");

	public String msg;

	OrderEnum(String msg) {
		this.msg = msg;
	}

}
