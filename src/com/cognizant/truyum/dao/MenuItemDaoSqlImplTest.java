/**
 * 
 */
package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

/**
 * @Gokul
 *
 */
public class MenuItemDaoSqlImplTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
		testGetMenuItem();

	}

	public static void testGetMenuItemListAdmin() {
		MenuItemDaoSqlImpl menuItemDaoSqlImpl = new MenuItemDaoSqlImpl();
		List<MenuItem> menuItems = menuItemDaoSqlImpl
				.getMenuItemListAdmin();
		for (MenuItem menuItem : menuItems) {
			System.out.println(menuItem);
		}
	}

	public static void testGetMenuItemListCustomer() {
		MenuItemDaoSqlImpl menuItemDaoSqlImpl = new MenuItemDaoSqlImpl();
		List<MenuItem> menuItems = menuItemDaoSqlImpl
				.getMenuItemListCustomer();
		for (MenuItem menuItem : menuItems) {
			System.out.println(menuItem);
		}
	}

	public static void testModifyMenuItem() {
		MenuItem menuItem = null;
		try {
			menuItem = new MenuItem(4, "Cake", 80.00f, true,
					DateUtil.convertToDate("23/12/2017"), "MainCourse", false);
		} catch (ParseException e) {
			System.out.println("Parse Exception: " + e.getMessage());
		}
		MenuItemDaoSqlImpl menuItemDaoSqlImpl = new MenuItemDaoSqlImpl();
		MenuItemDao menuItemDao = menuItemDaoSqlImpl;
		menuItemDao.modifyMenuItem(menuItem);
		System.out.println("The modified details are: "
				+ menuItemDao.getMenuItem((long) 4));
	}

	public static void testGetMenuItem() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the id: ");
		long id = in.nextLong();
		MenuItemDaoSqlImpl menuItemDaoSqlImpl = new MenuItemDaoSqlImpl();
		MenuItemDao menuItemDao = menuItemDaoSqlImpl;
		MenuItem menuItemForFetching = menuItemDao.getMenuItem(id);
		System.out.println(menuItemForFetching);
		in.close();
	}

}