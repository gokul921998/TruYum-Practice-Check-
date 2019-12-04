<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html>
<head>
<title>Menu Item List for Customers</title>
<link rel="stylesheet" href="style/style.css">
</head>
<body>
	<header class="header container-fluid">
		<h1 class="header-itemleft" style="padding-left: 50px; float: left">truYum</h1>
		<img src="image/image1.jfif"> <a class="header-itemright"
			href="ShowMenuItemListCustomerServlet">Menu</a>
	</header>
	<br>
	<br>
	<c:if test="${removeCartStatus}">
		<h2 style="text-align: center; color: green">Item removed from
			cart successfully</h2>
	</c:if>
	<section class="body-main">
		<h1>Cart</h1>
		<br> <br>
		<c:set var="sum" value="${0}" />
		<table>
			<tr>
				<th style="width: 200px" class="th-text-align-left">Name</th>
				<th>Free Delivery</th>
				<th class="th-text-align-right">Price</th>
				<th></th>
			</tr>
			<c:forEach var="item" items="${cartMenuList}">
				<tr>
					<td class="th-text-align-left">${item.name}</td>
					<td>${item.freeDelivery ? 'Yes' : 'No'}</td>
					<td class="th-text-align-right" style="width: 100px"><fmt:setLocale
							value="en_IN" /> <fmt:formatNumber type="currency"
							value="${item.price}" /></td>
					<c:set var="sum" value="${sum+item.price}" />

					<td><a href="RemoveCartServlet?menuItemId=${item.id}">Delete</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<th>Total</th>
				<th class="td-text-align-right"><fmt:setLocale value="en_IN" />
					<fmt:formatNumber type="currency" value="${sum}" /></th>
			</tr>
		</table>
	</section>
	<footer>Copyrightę2019</footer>

</body>
</html>