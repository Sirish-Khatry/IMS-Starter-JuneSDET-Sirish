package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum ItemChoice {

	YES("Add more items to order"), NO("Thats all the items in the order");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private ItemChoice(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	public static void printChoices() {
		for (ItemChoice choice : ItemChoice.values()) {
			LOGGER.info(choice.getDescription());
		}
	}

	public static ItemChoice getChoice(Utils utils) {
		ItemChoice choice;
		while (true) {
			try {
				choice = ItemChoice.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return choice;
	}

}
