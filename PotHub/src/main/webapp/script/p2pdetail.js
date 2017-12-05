function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function showReportables() {
	console.log("Showing reportables");
	document.getElementById("reportList").style.display = "block";
}

function hideReportables() {
	document.getElementById("reportList").style.display = "none";
	console.log("Hiding reportables");
}