function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function filterText() {  
	var rex = new RegExp($("#selectFilter").val());
	if(rex =="/All/") {
		$("tbody > tr").show();
	}
	else {
		$("tbody > tr").hide();
		$("tbody > tr").filter(function() {
		return rex.test($(this).text());
		}).show();
	}
}
