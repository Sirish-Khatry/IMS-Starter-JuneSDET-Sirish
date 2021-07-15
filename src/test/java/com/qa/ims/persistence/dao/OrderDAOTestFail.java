package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTestFail {

	private final OrderDAO DAO = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect("null");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(1L, 1L, 1L, "jordan", "harrison", "HP Pavilion");
		assertNull(DAO.createOrderItem(created));
	}

	@Test
	public void testCreateOrder() {
		final Order created = new Order(1L);
		assertNull(DAO.create(created));
	}

//	
//	@Test
//	public void totalCost() {
//		Long expected = 1400L;
//		assertEquals(expected, DAO.TotalCost(2L));
//	}
//	
	@Test
	public void testReadAll() {
		assertNull(DAO.readAllOrderDetails());
	}

	@Test
	public void testReadLatestOrderItem() {
		assertNull(DAO.readLatestOrderItems());
	}

	@Test
	public void testReadAllOrders() {
		assertNull(DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatestDetail());
	}

	@Test
	public void testRead() {
		final long ID = 2L;
		try {
			assertNull(DAO.readList(ID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Test
//	public void testUpdate() {
//		final Customer updated = new Customer(1L, "chris", "perrins");
//		assertEquals(updated, DAO.update(updated));
//	}
//
	@Test
	public void testDeleteOrder() {
		assertEquals(0, DAO.delete(1));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, DAO.deleteItem(2,3));
	}
}
