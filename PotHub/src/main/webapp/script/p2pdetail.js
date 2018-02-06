function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function showReportables() {
	console.log("Showing reportables");
	document.getElementById("popup").style.display = "block";
	document.getElementById("reportButton").setAttribute( "onClick", "hideReportables()" );
}

function hideReportables() {
	console.log("Hiding reportables");
	document.getElementById("popup").style.display = "none";
	document.getElementById("reportButton").setAttribute( "onClick", "showReportables()" );
}

function hideReports() {
	console.log("Hiding reportables");
	document.getElementById("popup").style.display = "none";
	document.getElementById("reportButton").setAttribute( "onClick", "showReportables()" );
}

function showReportBuyers() {
	console.log("Showing reportables");
	document.getElementById("buyersList").style.display = "block";
	document.getElementById("buyerPara").setAttribute( "onClick", "hideReportBuyers()" );
	document.getElementById("buyerPara").innerHTML = "Buyers ";
}

function hideReportBuyers() {
	console.log("Hiding reportables");
	document.getElementById("buyersList").style.display = "none";
	document.getElementById("buyerPara").setAttribute( "onClick", "showReportBuyers()" );
	document.getElementById("buyerPara").innerHTML = "Report No-Shows ";
}

$(document).ready(function() {
	hideReportBuyers();
	hideReportables();
});