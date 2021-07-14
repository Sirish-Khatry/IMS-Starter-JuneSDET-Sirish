package com.qa.ims.controller;

import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	
	public List<Order> readAllOrderDetail() {
		List<Order> orders = orderDAO.readAllOrderDetails();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	
	public Long totalCost() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter id of the order");
		LOGGER.info("=".repeat(90));
		Long order_id = utils.getLong();
		Long order_total =  orderDAO.TotalCost(order_id);
		LOGGER.info("=".repeat(90));
		LOGGER.info("Total Cost of orderID:"+ order_id + " is £" + order_total);
		LOGGER.info("=".repeat(90));
		List<Order> orders = null;
		try {
		 orders = orderDAO.readList(order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return null;
	}

	@Override
	public List<Order> readList() {
		readAll();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter id of the order you would like to read");
		Long order_id = utils.getLong();
		LOGGER.info("=".repeat(90));
		List<Order> orders = null;
		try {
			orders = orderDAO.readList(order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Order create() {

		LOGGER.info("Please enter id of the customer");
		Long cust_id = utils.getLong();
		Order order = orderDAO.create(new Order(cust_id));
		LOGGER.info("Order created");
		return order;
	}

	public Order createOrderItem() throws SQLException {
		LOGGER.info("=".repeat(90));
		readAllOrderDetail();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter id of the order you would like to add item to");
		LOGGER.info("=".repeat(90));
		Long order_id = utils.getLong();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter id of the item to add to this order");
		LOGGER.info("=".repeat(90));
		Long item_id = utils.getLong();
		Order order = orderDAO.createOrderItem(new Order(order_id, item_id));
		LOGGER.info("=".repeat(90));
		LOGGER.info("=".repeat(90));
		LOGGER.info("Item added");
		//readAllOrderDetail();
		LOGGER.info("=".repeat(90));
		return order;

	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter the id of the order you would like to update");
		LOGGER.info("=".repeat(90));
		Long order_id = utils.getLong();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a id of the item in the order that you would like to update");
		LOGGER.info("=".repeat(90));
		Long item_id = utils.getLong();
		LOGGER.info("=".repeat(90));
		Order order = orderDAO.update(new Order(order_id, item_id));
		LOGGER.info("Order Updated");
		LOGGER.info("=".repeat(90));
		return order;
	}

	/**
	 * Deletes an existing item by the id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("Order Removed");
		return orderDAO.delete(id);
	}

	@Override
	public Order read() {
		readList();
		return null;
	}

	public int removeItem() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter the id of the order you want to remove item from");
		Long order_id = utils.getLong();
		LOGGER.info("Please enter the id of the item you want to remove");
		Long item_id = utils.getLong();
		LOGGER.info("=".repeat(90));
		orderDAO.deleteItem(order_id, item_id);
		LOGGER.info("Item Removed");
		readAllOrderDetail();
		LOGGER.info("=".repeat(90));
		
		return 0;
				
		
	}

}
