package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("customer_id");
		Long item_id = resultSet.getLong("item_id");
		String first_name = resultSet.getString("first_name");
		String lastname = resultSet.getString("surname");
		String item_name = resultSet.getString("name");

		return new Order(id, customer_id, item_id, first_name, lastname, item_name);
	}
	
	public Long modelFromResultSetTotal(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		Long total_cost = resultSet.getLong("total_cost");
		return (total_cost);
	}

	public Order modelFromResultSetSpecific(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("customer_id");

		return new Order(id, customer_id);
	}
	

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();

				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSetSpecific(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT c.customer_id, c.first_name, c.surname, o.order_id, i.item_id,i.name from customers c"
						+ " INNER JOIN orders o ON c.customer_id = o.customer_id"
						+ " INNER JOIN orders_items oi ON o.order_id = oi.order_id"
						+ " INNER JOIN items i ON oi.item_id = i.item_id ORDER BY oi.orders_items_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Long TotalCost(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT o.order_id AS ORDER_ID, SUM(i.value) AS Total_Cost"
						+ " FROM orders o"
						+ "	JOIN orders_items oi ON oi.order_id = o.order_id"
						+ "	JOIN items i ON i.item_id = oi.item_id WHERE o.order_id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSetTotal(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomer_id());
			statement.executeUpdate();
			//return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order createOrderItem(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders_items(order_id, item_id) VALUES (?, ?)");) {
			statement.setLong(1, order.getId());
			statement.setLong(2, order.getItem_id());
			statement.executeUpdate();
			LOGGER.info("Item Added To The Order");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.info("Item Failed to be Added to the Order");
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<Order> readAllOrderDetails() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT c.customer_id, c.first_name, c.surname, o.order_id, i.item_id,i.name from customers c"
								+ " INNER JOIN orders o ON c.customer_id = o.customer_id"
								+ " INNER JOIN orders_items oi ON o.order_id = oi.order_id"
								+ " INNER JOIN items i ON oi.item_id = i.item_id ORDER BY o.order_id");) {

			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public List<Order> readList(Long id) throws SQLException {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT c.customer_id, c.first_name, c.surname, o.order_id, i.item_id,i.name from customers c"
								+ " INNER JOIN orders o ON c.customer_id = o.customer_id"
								+ " INNER JOIN orders_items oi ON o.order_id = oi.order_id"
								+ " INNER JOIN items i ON oi.item_id = i.item_id WHERE o.order_id = ?");) {
			statement.setLong(1, id);
			List<Order> orders = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery();) {

				while (resultSet.next()) {
					orders.add(modelFromResultSet(resultSet));
				}
				return orders;
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return new ArrayList<>();

		}
	}

	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE order_items SET item_id = ? WHERE order_id = ?");) {
			statement.setLong(1, order.getId());
			statement.setLong(2, order.getItem_id());
			statement.executeUpdate();
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Order read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
