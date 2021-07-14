package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
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
		final Order created = new Order(3L, 1L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		expected.add(new Customer(2L, "chris", "watkins"));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Customer(2L, "chris", "watkins"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 2L;
		assertEquals(new Customer(ID, "chris", "watkins"), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}
