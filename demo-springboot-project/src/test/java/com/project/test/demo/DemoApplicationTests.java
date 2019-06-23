package com.project.test.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.project.test.demo.model.Item;
import com.project.test.demo.model.Order;
import com.project.test.demo.service.OrderService;
import com.project.test.demo.util.exception.OrderNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DemoApplicationTests {

	@Mock
	OrderService orderServiceMock;

	@Before
	public void initContext() throws Exception {
		MockitoAnnotations.initMocks(this);
		orderServiceMock.processOrder(new Order());
		orderServiceMock.receiveOrder(new Order());
	}

	@Test
	public void tesGetOrder() throws OrderNotFoundException {
		Long id = 3L;
		Order order3 = new Order();
		Item item = new Item(id, "order 3", 5L, 10.0F);
		order3.setItem(Arrays.asList(item));
		when(orderServiceMock.getOrderById(anyLong())).thenReturn(order3);
		assertEquals(orderServiceMock.getOrderById(anyLong()), order3);
	}

	@Test
	public void testProcessOrder() throws Exception {
		verify(orderServiceMock, atLeastOnce()).processOrder(any(Order.class));
	}

	@Test
	public void testReceivedOrder() throws Exception {
		verify(orderServiceMock, atLeastOnce()).receiveOrder(any(Order.class));
	}

	@Test(expected = OrderNotFoundException.class)
	public void testOrderNotFound() throws OrderNotFoundException {
		Long id = -1L;
		doThrow(new OrderNotFoundException(id)).when(orderServiceMock).getOrderById(id);
		orderServiceMock.getOrderById(id);
	}

}
