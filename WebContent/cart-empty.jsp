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
			href="ShowCartServlet">Cart</a> <a class="header-itemright"
			href="ShowMenuItemListCustomerServlet">Menu</a>
	</header>
	<br>
	<br>
	<section class="body-main">
		<h1>Cart</h1>
		<h2 style="color: grey">
			No items in cart. Use 'Add to Cart' option in <a
				href="ShowMenuItemListCustomerServlet">Menu Item List</a>
		</h2>
	</section>
	<footer>Copyright©2019</footer>

</body>
</html>