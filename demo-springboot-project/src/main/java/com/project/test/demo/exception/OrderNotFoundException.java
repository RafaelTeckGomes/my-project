package com.project.test.demo.exception;

public class OrderNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3928503659648925927L;
	
	
    public OrderNotFoundException(Long id) {
        super("Order not found with id: "+id);
    }

}
