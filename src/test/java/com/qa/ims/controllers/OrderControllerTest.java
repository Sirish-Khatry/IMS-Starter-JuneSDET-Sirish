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
	public void testUpdate() {
		final Long order_id = 1L, item_id = 1L;
		final Order created = new Order(order_id, item_id);

		Mockito.when(utils.getLong()).thenReturn(order_id, item_id);
		Mockito.when(dao.update(created)).thenReturn(created);

		assertEquals(created, controller.update());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).update(created);
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 1L, "jordan", "harrison", "HP Pavilion"));
		orders.add(new Order(2L, 2L, 2L, "chris", "watkins", "Dell Laptop"));
		orders.add(new Order(2L, 2L, 3L, "chris", "watkins", "Iphone12 ProMax"));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testReadSpecific() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 1L, "jordan", "harrison", "HP Pavilion"));
		
		Mockito.when(utils.getLong()).thenReturn(1L);
		try {
			Mockito.when(dao.readList(1L)).thenReturn(orders);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(orders, this.controller.readList());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		try {
			Mockito.verify(dao, Mockito.times(1)).readList(1L);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testReadAllOrder() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 1L, "jordan", "harrison", "HP Pavilion"));
		orders.add(new Order(2L, 2L, 2L, "chris", "watkins", "Dell Laptop"));
		orders.add(new Order(2L, 2L, 3L, "chris", "watkins", "Iphone12 ProMax"));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testDelete() {

		Mockito.when(utils.getLong()).thenReturn(1L, 1L);
		Mockito.when(dao.deleteItem(1L, 1L)).thenReturn(1);

		assertEquals(1L, this.controller.removeItem());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).deleteItem(1L, 1L);
	}
	
	@Test
	public void testDeleteOrder() {

		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.delete(1L)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(1L);
	}
	

	@Test
	public void testCreateOrder() {

		final Long customer_id = 1L;
		final Order created = new Order(customer_id);

		Mockito.when(utils.getLong()).thenReturn(customer_id);
		Mockito.when(dao.create(created)).thenReturn(created);

			assertEquals(created, controller.create());


		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testTotal() {

		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.TotalCost(1L)).thenReturn(500L);

		assertEquals(500L, controller.totalCost().longValue());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).TotalCost(1L);
	}
}
