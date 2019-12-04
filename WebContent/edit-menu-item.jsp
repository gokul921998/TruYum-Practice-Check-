<%@	page import="com.cognizant.truyum.model.MenuItem"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html>
<head>
<titleEdit Menu Item</title>
<script type="text/javascript" src="js/validation.js"></script>
<link rel="stylesheet" href="style/style.css">
<style>
.body-main th {
	text-align: left;
}

.body-main td {
	text-align: left;
}

.button {
	background-color: #004080;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
</head>
<body>

	<c:set var="categories"
		value="Starters, Main Course, Dessert, Drinks, Ice Cream, Cock Tails, Mock Tails"
		scope="application" />
	<c:set var="selected" value="${menuItem.category}" scope="application" />

	<header class="header container-fluid">
		<h1 style="padding-left: 50px; float: left">truYum</h1>
		<img src="image/image1.jfif"> <a class="header-itemright"
			href="ShowMenuItemListAdminServlet">Menu</a>
	</header>
	<section class="body-main"><br>
		<h1>Edit Menu Item</h1>
		<br>
		<form name="editMenuForm" action="EditMenuItemServlet" method="post"
			onsubmit="return validateMenuItemForm()">
			<table style="width: 100%">
				<tr>
					<th colspan="4" style="padding-top: 25px"><label for="mname">Name:</label></th>
				</tr>
				<tr>
					<td colspan="4"><input type="text" id="mname" name="itemName"
						style="width: 85%" value="${menuItem.name}"></td>
				</tr>
				<tr>
					<th style="padding-top: 25px"><label for="mprice">Price:</label></th>
					<th style="padding-top: 25px"><label for="mactive">Active:</label></th>
					<th style="padding-top: 25px"><label for="mdate">Date
							of Launch:</label></th>
					<th style="padding-top: 25px"><label for="mcategory">Category:</label></th>
				</tr>
				<tr>
					<td><input type="text" name="price" id="mprice"
						value="${menuItem.price}"></td>
					<c:choose>
						<c:when test="${menuItem.active}">
							<td><input type="radio" name="active" value="yes"
								id="mactive" checked>Yes <input type="radio"
								name="active" value="no" id="mactive"> No</td>
						</c:when>
						<c:otherwise>
							<td><input type="radio" name="active" value="yes"
								id="mactive">Yes <input type="radio" name="active"
								value="no" id="mactive" checked>No</td>
						</c:otherwise>
					</c:choose>
					<td><input type="date" name="date" id="mdate"
						value=<fmt:formatDate pattern="dd/MM/yyyy" value="${menuItem.dateOfLaunch}"/>></td>
					<td><select name="itemType" id="mcategory">
							<option value="${selected}" selected>${selected}</option>
							<c:forEach items="${categories}" var="category">
								<c:if test="${category!= selected}">
									<option value="${category}">${category}</option>
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="padding-top: 25px"><input type="checkbox"
						name="freedelivery" value="${menuItem.freeDelivery}"
						<c:if test="${menuItem.freeDelivery}">checked="checked"</c:if> />
						<label>Free Delivery</label></td>
				</tr>
				<tr>
					<td><input type="hidden" value="${menuItem.id}" name="id"></td>
				</tr>
				<tr>
					<td style="padding-top: 25px"><input type="submit"
						class="button" value="Save"></td>
				</tr>
			</table>
		</form>
	</section>
	<footer>Copyright©2019</footer>

</body>
</html>