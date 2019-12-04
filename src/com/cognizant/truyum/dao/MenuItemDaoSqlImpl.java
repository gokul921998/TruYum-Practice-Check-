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

import com.cognizant.truyum.model.MenuItem;

/**
 * @Gokul
 *
 */
public class MenuItemDaoSqlImpl implements MenuItemDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.truyum.dao.MenuItemDao#getMenuItemListAdmin()
	 */
	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		if (connection != null) {
			try {
				preparedStatement = connection
						.prepareStatement("select me_id, me_name, me_price, me_active, me_date_of_launch, me_category, me_free_delivery from menu_item");
				resultSet = preparedStatement.executeQuery();
				boolean activeFlag, freeDeliveryFlag;
				Date date_of_launch;
				while (resultSet.next()) {
					int menuItemId = resultSet.getInt("me_id");
					String name = resultSet.getString("me_name");
					float price = resultSet.getFloat("me_price");
					String active = resultSet.getString("me_active");
					date_of_launch = resultSet.getDate("me_date_Of_launch");
					String category = resultSet.getString("me_category");
					String freeDelivery = resultSet
							.getString("me_free_delivery");
					if (active != null && active.equals("Yes"))
						activeFlag = true;
					else
						activeFlag = false;
					if (freeDelivery != null && freeDelivery.equals("Yes"))
						freeDeliveryFlag = true;
					else
						freeDeliveryFlag = false;
					MenuItem menuItem = new MenuItem(menuItemId, name, price,
							activeFlag, date_of_launch, category,
							freeDeliveryFlag);
					menuItemList.add(menuItem);
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
		return menuItemList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.truyum.dao.MenuItemDao#getMenuItemListCustomer()
	 */
	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		if (connection != null) {
			try {
				preparedStatement = connection
						.prepareStatement("select * from menu_item where me_active='Yes' and me_date_of_launch <= now()");
				resultSet = preparedStatement.executeQuery();
				boolean activeFlag, freeDeliveryFlag;
				Date date_of_launch;
				while (resultSet.next()) {
					int menuItemId = resultSet.getInt(1);
					String name = resultSet.getString(2);
					float price = resultSet.getFloat(3);
					String active = resultSet.getString(4);
					date_of_launch = resultSet.getDate(5);
					String category = resultSet.getString(6);
					String freeDelivery = resultSet.getString(7);
					if (active != null && active.equals("Yes"))
						activeFlag = true;
					else
						activeFlag = false;
					if (freeDelivery != null && freeDelivery.equals("Yes"))
						freeDeliveryFlag = true;
					else
						freeDeliveryFlag = false;
					MenuItem menuItem = new MenuItem(menuItemId, name, price,
							activeFlag, date_of_launch, category,
							freeDeliveryFlag);
					menuItemList.add(menuItem);
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
		return menuItemList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cognizant.truyum.dao.MenuItemDao#modifyMenuItem(com.cognizant.truyum
	 * .model.MenuItem)
	 */
	@Override
	public void modifyMenuItem(MenuItem menuItem) {

		Connection connection = ConnectionHandler.getConnection();
		String query = "update menu_item set me_name = ?, me_price = ?, me_active = ?, me_date_of_launch = ?, me_free_delivery = ?, me_category = ? where me_id = ?";
		try {
			if (connection != null) {
				PreparedStatement preparedStatement = connection
						.prepareStatement(query);
				preparedStatement.setString(1, menuItem.getName());
				preparedStatement.setFloat(2, menuItem.getPrice());
				if (menuItem.isActive())
					preparedStatement.setString(3, "Yes");
				else
					preparedStatement.setString(3, "No");
				preparedStatement.setDate(4, new java.sql.Date(menuItem
						.getDateOfLaunch().getTime()));
				if (menuItem.isFreeDelivery())
					preparedStatement.setString(5, "Yes");
				else
					preparedStatement.setString(5, "No");
				preparedStatement.setString(6, menuItem.getCategory());
				preparedStatement.setLong(7, menuItem.getId());
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
	 * @see com.cognizant.truyum.dao.MenuItemDao#getMenuItem(java.lang.Long)
	 */
	@Override
	public MenuItem getMenuItem(Long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		MenuItem menuItem = null;
		if (connection != null) {
			try {
				preparedStatement = connection
						.prepareStatement("select * from menu_item where me_id=?");
				preparedStatement.setLong(1, menuItemId);

				resultSet = preparedStatement.executeQuery();
				boolean activeFlag, freeDeliveryFlag;
				Date date_of_launch;
				while (resultSet.next()) {
					String name = resultSet.getString(2);
					float price = resultSet.getFloat(3);
					String active = resultSet.getString(4);
					date_of_launch = resultSet.getDate(5);
					String category = resultSet.getString(6);
					String freeDelivery = resultSet.getString(7);
					if (active != null && active.equals("Yes"))
						activeFlag = true;
					else
						activeFlag = false;
					if (freeDelivery != null && freeDelivery.equals("Yes"))
						freeDeliveryFlag = true;
					else
						freeDeliveryFlag = false;
					menuItem = new MenuItem(menuItemId, name, price,
							activeFlag, date_of_launch, category,
							freeDeliveryFlag);
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
		return menuItem;

	}
}
