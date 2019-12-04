/**
 * 
 */
package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

/**
 * @Gokul
 *
 */
public class CartDaoCollectionImpl implements CartDao {

	private static HashMap<Long, Cart> userCarts;

	/**
	 * 
	 */
	public CartDaoCollectionImpl() {
		if (userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
			try {
				List<MenuItem> menuItemList = new ArrayList<MenuItem>();
				Cart cart = new Cart(menuItemList, 0.0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.truyum.dao.CartDao#addCartItem(java.lang.Long,
	 * java.lang.Long)
	 */

	@Override
	public void addCartItem(Long userId, Long menuItemId) {
		List<MenuItem> menuItemList;
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl = new MenuItemDaoCollectionImpl();
		MenuItemDao menuItemDao = menuItemDaoCollectionImpl;
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		if (userCarts.containsKey(userId)) {
			Cart cart = userCarts.get(userId);
			menuItemList = cart.getMenuItemList();
			menuItemList.add(menuItem);
			cart.setMenuItemList(menuItemList);
			userCarts.put(userId, cart);
		} else {
			menuItemList = new CopyOnWriteArrayList<MenuItem>();
			menuItemList.add(menuItem);

			Cart cart = new Cart(menuItemList, (double) menuItem.getPrice());
			userCarts.put(userId, cart);

		}

	}

	/**
	 * @return the userCarts
	 */
	public HashMap<Long, Cart> getUserCarts() {
		return userCarts;
	}

	/**
	 * @param userCarts
	 *            the userCarts to set
	 */
	public void setUserCarts(HashMap<Long, Cart> userCarts) {
		CartDaoCollectionImpl.userCarts = userCarts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.truyum.dao.CartDao#getAllCartItems(java.lang.Long)
	 */
	@Override
	public List<MenuItem> getAllCartItems(Long userId)
			throws CartEmptyException {
		Cart cart = userCarts.get(new Long(userId));
		if (cart == null) {
			throw new CartEmptyException("Cart is Empty");
		}
		List<MenuItem> menuItemList = cart.getMenuItemList();
		if (menuItemList == null || menuItemList.size() == 0) {
			throw new CartEmptyException("Cart is Empty");
		}
		double total = 0.0;
		for (MenuItem menuitem : menuItemList) {
			total = total + menuitem.getPrice();
		}

		cart.setTotal(total);
		return menuItemList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.truyum.dao.CartDao#removeCartItem(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public void removeCartItem(Long userId, Long menuItemId) {

		if (userCarts.containsKey(userId)) {
			Cart cart = userCarts.get(userId);
			List<MenuItem> menuItemList = cart.getMenuItemList();
			for (MenuItem menuItem : menuItemList) {
				if (menuItem.getId() == menuItemId) {
					menuItemList.remove(menuItem);
				}
			}
			cart.setMenuItemList(menuItemList);
			userCarts.put(userId, cart);

		}

	}
}
