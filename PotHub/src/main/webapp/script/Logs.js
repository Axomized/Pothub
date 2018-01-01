function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function customDateSelect() {
	var selectDate = document.getElementById("selectDate");
	var afterDateDiv = document.getElementById("afterDateDiv");
	var beforeDateDiv = document.getElementById("beforeDateDiv");
	
	if (selectDate.value == "Custom") {
		afterDateDiv.style.display = "inline-block";
		beforeDateDiv.style.display = "inline-block";
	}
	else {
		afterDateDiv.style.display = "none";
		beforeDateDiv.style.display = "none";
	}
}
