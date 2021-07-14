DROP TABLE IF EXISTS orders_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS customers;

CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40)NOT NULL,
    `surname` VARCHAR(40)NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `items` (
    `item_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `value` DECIMAL(6,2)NOT NULL,
    PRIMARY KEY (`item_id`)
);


CREATE TABLE IF NOT EXISTS `orders` (
    `order_id` INT NOT NULL AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    PRIMARY KEY (`order_id`),
	FOREIGN KEY(`customer_id`) REFERENCES `customers`(`customer_id`)
);

CREATE TABLE IF NOT EXISTS `orders_items` (
    `orders_items_id` INT NOT NULL AUTO_INCREMENT,
    `order_id` INT NOT NULL,
    `item_id` INT NOT NULL,
    PRIMARY KEY (`orders_items_id`),
	FOREIGN KEY(`order_id`) REFERENCES `orders`(`order_id`),
	FOREIGN KEY(`item_id`) REFERENCES `items`(`item_id`)
);



