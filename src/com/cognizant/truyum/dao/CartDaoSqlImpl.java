/**
 * 
 */
package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

/**
 * @Gokul
 *
 */
public class CartDaoSqlImpl implements CartDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.truyum.dao.CartDao#addCartItem(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public void addCartItem(Long userId, Long menuItemId) {
		PreparedStatement preparedStatement;
		Connection connection = ConnectionHandler.getConnection();
		try {
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("insert into cart values (default, ?, ?)");
				preparedStatement.setLong(1, userId);
				preparedStatement.setLong(2, menuItemId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.truyum.dao.CartDao#getAllCartItems(java.lang.Long)
	 */
	@Override
	public List<MenuItem> getAllCartItems(Long userId)
			throws CartEmptyException {
		PreparedStatement preparedStatement;
		Connection connection = null;
		ResultSet resultSet;
		boolean activeFlag, freeDeliveryFlag;
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		try {
			connection = ConnectionHandler.getConnection();
			if (connection != null) {
				Cart cart = new Cart(menuItemList, (double) 0);
				StringBuffer sqlBuffer = new StringBuffer();
				sqlBuffer
						.append("select menu_item.me_id, menu_item.me_name, menu_item.me_free_delivery, menu_item.me_price, menu_item.me_active, ")
						.append("menu_item.me_category, menu_item.me_date_of_launch from menu_item inner ")
						.append("join cart on ")
						.append("menu_item.me_id = cart.ct_pr_id ")
						.append("where cart.ct_us_id = ?;");
				preparedStatement = connection.prepareStatement(sqlBuffer
						.toString());
				preparedStatement.setLong(1, userId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Long menuItemId = resultSet.getLong(1);
					String name = resultSet.getString(2);
					float price = resultSet.getFloat(4);
					String active = resultSet.getString(5);
					String freeDelivery = resultSet.getString(3);
					String category = resultSet.getString(6);
					Date dateOfLaunch = resultSet.getDate(7);
					if (freeDelivery != null && freeDelivery.equals("Yes"))
						freeDeliveryFlag = true;
					else
						freeDeliveryFlag = false;
					if (active != null && freeDelivery.equals("Yes"))
						activeFlag = true;
					else
						activeFlag = false;

					MenuItem menuItem = new MenuItem(menuItemId, name, price,
							activeFlag, dateOfLaunch, category,
							freeDeliveryFlag);
					menuItemList.add(menuItem);
				}
				cart.setMenuItemList(menuItemList);
				cart.setTotal(getTotalPrice(userId, connection));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (menuItemList.size() == 0)
			throw new CartEmptyException("Cart is empty");
		return menuItemList;
	}

	/**
	 * @param userId
	 * @param connection
	 * @return
	 */
	private double getTotalPrice(Long userId, Connection connection) {
		PreparedStatement preparedStatement;
		// List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		ResultSet resultSet;
		double total = 0;
		if (connection != null) {
			try {
				// Cart cart = new Cart(menuItemList, 0.0);
				StringBuffer sqlBuffer = new StringBuffer();
				sqlBuffer
						.append("select SUM(menu_item.me_price) from menu_item inner ")
						.append("join cart on ")
						.append("menu_item.me_id = cart.ct_pr_id ")
						.append("where cart.ct_us_id = ?");
				preparedStatement = connection.prepareStatement(sqlBuffer
						.toString());
				preparedStatement.setLong(1, userId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					total = resultSet.getDouble(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.truyum.dao.CartDao#removeCartItem(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public void removeCartItem(Long userId, Long menuItemId) {

		PreparedStatement preparedStatement;
		Connection connection = ConnectionHandler.getConnection();
		try {
			if (connection != null) {
				preparedStatement = connection
						.prepareStatement("delete from cart where  ct_us_id = ? and ct_pr_id = ?;");
				preparedStatement.setLong(1, userId);
				preparedStatement.setLong(2, menuItemId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
