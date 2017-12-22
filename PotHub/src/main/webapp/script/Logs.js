function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function filterText() {  
	var rex = new RegExp($("#selectFilter").val());
	if(rex =="/All/") {
		clearFilter()
	}
	else {
		$("tbody > tr").hide();
		$("tbody > tr").filter(function() {
		return rex.test($(this).text());
		}).show();
	}
}

function clearFilter() {
	$("tbody > tr").show();
}