package com.qa.ims;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class IMS {

	public static final Logger LOGGER = LogManager.getLogger();

	private final CustomerController customers;
	private final ItemController items;
	private final OrderController orders;
	private final Utils utils;

	public IMS() {
		this.utils = new Utils();
		final CustomerDAO custDAO = new CustomerDAO();
		this.customers = new CustomerController(custDAO, utils);
		final ItemDAO itemDAO = new ItemDAO();
		this.items = new ItemController(itemDAO, utils);
		final OrderDAO orderDAO = new OrderDAO();
		this.orders = new OrderController(orderDAO, utils);

	}

	public void imsSystem() {
		LOGGER.info("Welcome to the Inventory Management System!");
		DBUtils.connect();

		Domain domain = null;
		do {
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();
			LOGGER.info("=".repeat(90));

			domain = Domain.getDomain(utils);

			domainAction(domain);

		} while (domain != Domain.STOP);
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
			case CUSTOMER:
				active = this.customers;
				break;
			case ITEM:
				active = this.items;
				break;
			case ORDER:
				active = this.orders;
				break;
			case STOP:
				return;
			default:
				break;
			}
			LOGGER.info("=".repeat(90));
			LOGGER.info(() -> "What would you like to do with " + domain.name().toLowerCase() + ":");
			LOGGER.info("=".repeat(90));
			Action.printActions();
			LOGGER.info("=".repeat(90));
			Action action = Action.getAction(utils);

			if (action == Action.RETURN) {
				changeDomain = true;
			} else {
				doAction(active, action, domain.name().toLowerCase());
			}
		} while (!changeDomain);
	}

	public void doAction(CrudController<?> crudController, Action action, String domain) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			LOGGER.info("=".repeat(50));
			crudController.read();
			LOGGER.info("=".repeat(50));
			break;
		case READ_ALL:
			LOGGER.info("=".repeat(50));
			crudController.readAll();
			LOGGER.info("=".repeat(50));
			break;
		case ADD_TO_ORDER:
			try {
				orders.createOrderItem();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case TOTAL_COST:
			orders.totalCost();
			break;
		case REMOVE_ITEM:
			orders.removeItem();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}

	}

}
