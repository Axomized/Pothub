function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function showDropdown() {
	$("#dropdownContentDiv").toggle();
}

function customDateSelect() {
	var selectDate = document.getElementById("selectDate");
	var afterDateDiv = document.getElementById("afterDateDiv");
	var beforeDateDiv = document.getElementById("beforeDateDiv");
	var afterDate = document.getElementById("afterDate");
	var beforeDate = document.getElementById("beforeDate");
	
	if (selectDate.value == "Custom") {
		afterDateDiv.style.display = "inline-block";
		beforeDateDiv.style.display = "inline-block";
		afterDate.required = true;
		beforeDate.required = true;
	}
	else {
		afterDateDiv.style.display = "none";
		beforeDateDiv.style.display = "none";
		afterDate.required = false;
		beforeDate.required = false;
	}
}
