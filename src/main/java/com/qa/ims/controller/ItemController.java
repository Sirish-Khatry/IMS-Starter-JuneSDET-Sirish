package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a name for the item");
		LOGGER.info("=".repeat(90));
		String name = utils.getString();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a value of the item in 2 decimal places");
		LOGGER.info("=".repeat(90));
		double value = utils.getDouble();
		Item item = itemDAO.create(new Item(name, value));
		LOGGER.info("=".repeat(90));
		LOGGER.info("Item created");
		LOGGER.info("=".repeat(90));
		return item;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter the id of the item you would like to update");
		LOGGER.info("=".repeat(90));
		Long id = utils.getLong();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a name for the item");
		LOGGER.info("=".repeat(90));
		String name = utils.getString();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter a value for the item in 2 decimal places");
		LOGGER.info("=".repeat(90));
		double value = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, value));
		LOGGER.info("=".repeat(90));
		LOGGER.info("Customer Updated");
		LOGGER.info("=".repeat(90));
		return item;
	}

	/**
	 * Deletes an existing item by the id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("=".repeat(90));
		LOGGER.info("Please enter the id of the item you would like to delete");
		LOGGER.info("=".repeat(90));
		Long id = utils.getLong();
		LOGGER.info("=".repeat(90));
		LOGGER.info("Item Deleted");
		LOGGER.info("=".repeat(90));
		return itemDAO.delete(id);
	}

	@Override
	public Item read() {
		readAll();
		return null;
	}

	@Override
	public List<Item> readList() {
		// TODO Auto-generated method stub
		return null;
	}

}
