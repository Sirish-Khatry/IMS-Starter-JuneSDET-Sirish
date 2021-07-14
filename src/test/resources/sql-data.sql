INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `customers` (`first_name`, `surname`) VALUES ('chris', 'watkins');

INSERT INTO `items` (`name`, `value`) VALUES ( "HP Pavilion", 500.00);
INSERT INTO `items` (`name`, `value`) VALUES ( "Dell Laptop", 500.00);
INSERT INTO `items` (`name`, `value`) VALUES ( "Iphone12 ProMax", 900.00);


INSERT INTO `orders` (`customer_id`) VALUES (1);
INSERT INTO `orders` (`customer_id`) VALUES (2);

INSERT INTO `orders_items`(`order_id`, `item_id`) VALUES (1, 1);
INSERT INTO `orders_items`(`order_id`, `item_id`) VALUES (2, 2);
INSERT INTO `orders_items`(`order_id`, `item_id`) VALUES (2, 3);
