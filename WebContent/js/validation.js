function validateMenuItemForm() {
	var name = document.forms["editMenuForm"]["itemName"].value;
	var price = document.forms["editMenuForm"]["price"].value;
	var launchDate = document.forms["editMenuForm"]["date"].value;
	var category = document.forms["editMenuForm"]["itemType"].value;
	if (name == "") {
		alert("Name is required");
		return false;
	} else if (name.length < 2 || name.length > 65) {
		alert("Name should have 2 to 65 characters");
		return false;
	}
	if (price == "") {
		alert("Price is required");
		return false;
	} else if (isNaN(price)) {
		alert("Price has to be a number");
		return false;
	}
	if (launchDate == "") {
		alert("Date of Launch is required");
		return false;
	}
	if (category == "") {
		alert("Select one category");
		return false;
	}

}