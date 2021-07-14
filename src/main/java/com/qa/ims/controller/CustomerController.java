package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class CustomerController implements CrudController<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CustomerDAO customerDAO;
	private Utils utils;

	public CustomerController(CustomerDAO customerDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer);
		}
		return customers;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Customer create() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a first name");
		LOGGER.info("=".repeat(90));
		String firstName = utils.getString();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a surname");
		LOGGER.info("=".repeat(90));
		String surname = utils.getString();
		Customer customer = customerDAO.create(new Customer(firstName, surname));
		LOGGER.info("=".repeat(90));
		LOGGER.info("Customer created");
		LOGGER.info("=".repeat(90));
		return customer;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Customer update() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter the id of the customer you would like to update");
		LOGGER.info("=".repeat(90));
		Long id = utils.getLong();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a first name");
		LOGGER.info("=".repeat(90));
		String firstName = utils.getString();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a surname");
		LOGGER.info("=".repeat(90));
		String surname = utils.getString();
		Customer customer = customerDAO.update(new Customer(id, firstName, surname));
		LOGGER.info("=".repeat(90));
		LOGGER.info("Customer Updated");
		LOGGER.info("=".repeat(90));
		return customer;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter the id of the customer you would like to delete");
		LOGGER.info("=".repeat(90));
		Long id = utils.getLong();
		LOGGER.info("=".repeat(90));
		return customerDAO.delete(id);
	}

	@Override
	public Customer read() {
		readAll();
		return null;
		
	}

	@Override
	public List<Customer> readList() {
		// TODO Auto-generated method stub
		return null;
	}


}
