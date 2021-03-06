package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {


	private final OrderDAO DAO = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(1L,1L,1L, "jordan", "harrison", "HP Pavilion");
		assertEquals(created, DAO.createOrderItem(created));
	}

	@Test
	public void testCreateOrder() {
		final Order created = new Order(1L);
		final Order expected = new Order (3L, 1L);
		assertEquals(expected, DAO.create(created));
	}
	
	@Test
	public void totalCost() {
		Long expected = 1400L;
		assertEquals(expected, DAO.TotalCost(2L));
	}
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 1L, "jordan", "harrison", "HP Pavilion" ));
		expected.add(new Order(2L, 2L, 2L, "chris", "watkins", "Dell Laptop" ));
		expected.add(new Order(2L, 2L, 3L, "chris", "watkins", "Iphone12 ProMax"));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatestOrderItem() {
		final Order expected = new Order(2L, 3L);
		assertEquals(expected, DAO.readLatestOrderItems());
	}
	

	@Test
	public void testReadLatest() {
		assertEquals(new Order(2L, 2L, 3L, "chris", "watkins", "Iphone12 ProMax" ), DAO.readLatestDetail());
	}

	@Test
	public void testRead() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(2L, 2L, 2L, "chris", "watkins", "Dell Laptop"));
		expected.add(new Order(2L, 2L, 3L, "chris", "watkins", "Iphone12 ProMax"));
		final long ID = 2L;
		try {
			assertEquals(expected, DAO.readList(ID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
//	public void testUpdate() {
//
//		Order expected = new Order(2L, 1L);
//		assertEquals(expected, DAO.update(expected));
//	}

	@Test
	public void testDeleteOrder() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.deleteItem(2,3));
	}
}
