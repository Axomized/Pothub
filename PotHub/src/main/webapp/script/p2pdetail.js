function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function showReportables() {
	console.log("Showing reportables");
	document.getElementById("reportList").style.display = "block";
	document.getElementById("reportButton").setAttribute( "onClick", "hideReportables()" );
}

function hideReportables() {
	console.log("Hiding reportables");
	document.getElementById("reportList").style.display = "none";
	document.getElementById("reportButton").setAttribute( "onClick", "showReportables()" );
}