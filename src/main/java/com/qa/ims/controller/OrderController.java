package com.qa.ims.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.ItemChoice;
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

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Order create() {
		boolean choice = false;
		LOGGER.info("Please enter id of the customer");
		Long cust_id = utils.getLong();
		Order order = orderDAO.create(new Order(cust_id));
		LOGGER.info("Order created");
		return order;
	}

	public Order createAgain(ResultSet order_id) throws SQLException {
		Order order;
		boolean choice = true;
		Long orderID;
		orderID = order_id.getLong(1);
		while (choice) {

			ItemChoice pick = null;
			LOGGER.info("Add item to the order?");
			ItemChoice.printChoices();
			pick = ItemChoice.getChoice(utils);

			switch (pick) {
			case YES:
				LOGGER.info("Please enter id of the item you would like to add to the order");
				Long item_id2 = utils.getLong();
				if (order_id.next()) {
					order = orderDAO.createOrderItem(new Order(orderID, item_id2));
				} else {
					LOGGER.info("Order it not found");
					order = null;
				}
			case NO:
				choice = false;
			}

		}
		return null;

	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long order_id = utils.getLong();
		LOGGER.info("Please enter a id of the item in the order that you would like to update");
		Long item_id = utils.getLong();
		Order order = orderDAO.update(new Order(order_id, item_id));
		LOGGER.info("Order Updated");
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
		return orderDAO.delete(id);
	}

}
