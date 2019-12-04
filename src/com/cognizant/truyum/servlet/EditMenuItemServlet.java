package com.cognizant.truyum.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.dao.MenuItemDaoSqlImpl;
import com.cognizant.truyum.model.MenuItem;

/**
 * Servlet implementation class EditMenuItemServlet
 */
@WebServlet("/EditMenuItemServlet")
public class EditMenuItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditMenuItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// request.getAttribute("menuItem");
		MenuItemDaoSqlImpl menuItemDaoSqlImpl = new MenuItemDaoSqlImpl();
		MenuItemDao menuItemDao = menuItemDaoSqlImpl;
		String name = request.getParameter("itemName");
		String id = request.getParameter("id");
		String price = request.getParameter("price");
		String active = request.getParameter("active");
		String date = request.getParameter("date");
		String itemType = request.getParameter("itemType");
		boolean freeDelivery = request.getParameter("freedelivery") != null;
		MenuItem menuItem = new MenuItem();
		boolean activeFlag;
		if (active.equals("yes"))
			activeFlag = true;
		else
			activeFlag = false;
		menuItem.setActive(activeFlag);
		menuItem.setCategory(itemType);
		menuItem.setFreeDelivery(freeDelivery);
		menuItem.setId(Long.parseLong(id));
		menuItem.setName(name);
		menuItem.setPrice(Float.parseFloat(price));
		Date dateOfLaunch;
		try {
			dateOfLaunch = sdf.parse(date);
			menuItem.setDateOfLaunch(dateOfLaunch);
			menuItemDao.modifyMenuItem(menuItem);
			response.sendRedirect("edit-menu-item-status.jsp");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
