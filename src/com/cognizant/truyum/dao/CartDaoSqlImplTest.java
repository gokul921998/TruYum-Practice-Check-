/**
 * 
 */
package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

/**
 * @Gokul
 *
 */
public class CartDaoSqlImplTest {

	/**
	 * @param args
	 * @throws CartEmptyException 
	 */
	public static void main(String[] args) throws CartEmptyException {
		testAddCartItem();
		testGetAllCartItems();
		testRemoveCartItem();

	}

	public static void testAddCartItem() throws CartEmptyException {
		CartDaoSqlImpl cartDaoSqlImpl = new CartDaoSqlImpl();
		CartDao cartDao = cartDaoSqlImpl;
		cartDao.addCartItem((long) 2, (long) 000004);
		cartDao.addCartItem((long) 2, (long) 000003);
		List<MenuItem> menuItemList = cartDao.getAllCartItems((long) 2);
		System.out.println("MenuItem list :" + menuItemList);

	}

	public static void testGetAllCartItems() throws CartEmptyException {
		CartDaoSqlImpl cartDaoSqlImpl = new CartDaoSqlImpl();
		CartDao cartDao = cartDaoSqlImpl;
		List<MenuItem> menuItemList = cartDao.getAllCartItems((long) 2);
		System.out.println("MenuItem list :" + menuItemList);

	}

	public static void testRemoveCartItem() throws CartEmptyException {
		CartDaoSqlImpl cartDaoSqlImpl = new CartDaoSqlImpl();
		CartDao cartDao = cartDaoSqlImpl;

		try {
			cartDao.removeCartItem((long) 2, (long) 4);
			List<MenuItem> menuItemList = cartDao.getAllCartItems((long) 2);
			System.out.println("MenuItem list after removed:" + menuItemList);
		} catch (Exception e) {
			throw new CartEmptyException("Cart is empty");
		}

	}
}