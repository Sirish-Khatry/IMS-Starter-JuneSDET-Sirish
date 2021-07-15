package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final Long order_id = 1L, item_id = 1L;
		final Order created = new Order(order_id, item_id);

		Mockito.when(utils.getLong()).thenReturn(order_id, item_id);
		Mockito.when(dao.createOrderItem(created)).thenReturn(created);

		try {
			assertEquals(created, controller.createOrderItem());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).createOrderItem(created);
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 1L, "jordan", "harrison", "HP Pavilion" ));
		orders.add(new Order(2L, 2L, 2L, "chris", "watkins", "Dell Laptop" ));
		orders.add(new Order(2L, 2L, 3L, "chris", "watkins", "Iphone12 ProMax"));

		Mockito.when(dao.readAllOrderDetails()).thenReturn(orders);

		assertEquals(orders, controller.readAllOrderDetail());

		Mockito.verify(dao, Mockito.times(1)).readAllOrderDetails();
	}

	@Test
	public void testDelete() {

		Mockito.when(utils.getLong()).thenReturn(1L, 1L);
		Mockito.when(dao.deleteItem(1L, 1L)).thenReturn(1);

		assertEquals(1L, this.controller.removeItem());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).deleteItem(1L, 1L);
	}
}
