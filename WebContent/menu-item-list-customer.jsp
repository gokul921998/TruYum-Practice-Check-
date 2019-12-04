
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
	<c:if test="${addCartStatus}">
		<h2 style="text-align: center; color: green">Item added to cart
			successfully</h2>
	</c:if>
	<section class="body-main">
		<h2>Menu Items</h2>
		<table>
			<tr>
				<th class="th-text-align-left">Name</th>
				<th>Free Delivery</th>
				<th class="th-text-align-right">Price</th>
				<th>Category</th>

				<th>Action</th>
			</tr>
			<c:forEach var="item" items="${customerMenuItemList}">
				<tr>
					<td class="th-text-align-left">${item.name}</td>
					<td>${item.freeDelivery ? 'Yes' : 'No'}</td>
					<td class="th-text-align-right"><fmt:setLocale value="en_IN" />
						<fmt:formatNumber type="currency" value="${item.price}" /></td>
					<td>${item.category}</td>

					<td><a href="AddToCartServlet?menuItemId=${item.id}">Add
							to Cart</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<footer>Copyright©2019</footer>
</body>
</html>