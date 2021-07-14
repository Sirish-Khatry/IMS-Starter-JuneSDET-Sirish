package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
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
		Mockito.when(dao.create(created)).thenReturn(created);

	
		try {
			assertEquals(created, controller.createOrderItem());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).createOrderItem(created);
	}

//	@Test
//	public void testReadAll() {
//		List<Customer> customers = new ArrayList<>();
//		customers.add(new Customer(1L, "jordan", "harrison"));
//
//		Mockito.when(dao.readAll()).thenReturn(customers);
//
//		assertEquals(customers, controller.readAll());
//
//		Mockito.verify(dao, Mockito.times(1)).readAll();
//	}
//
//	@Test
//	public void testUpdate() {
//		Customer updated = new Customer(1L, "chris", "perrins");
//
//		Mockito.when(this.utils.getLong()).thenReturn(1L);
//		Mockito.when(this.utils.getString()).thenReturn(updated.getFirstName(), updated.getSurname());
//		Mockito.when(this.dao.update(updated)).thenReturn(updated);
//
//		assertEquals(updated, this.controller.update());
//
//		Mockito.verify(this.utils, Mockito.times(1)).getLong();
//		Mockito.verify(this.utils, Mockito.times(2)).getString();
//		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
//	}
//
//	@Test
//	public void testDelete() {
//		final long ID = 1L;
//
//		Mockito.when(utils.getLong()).thenReturn(ID);
//		Mockito.when(dao.delete(ID)).thenReturn(1);
//
//		assertEquals(1L, this.controller.delete());
//
//		Mockito.verify(utils, Mockito.times(1)).getLong();
//		Mockito.verify(dao, Mockito.times(1)).delete(ID);
//	}
}
