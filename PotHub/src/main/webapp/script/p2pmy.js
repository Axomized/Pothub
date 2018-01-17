function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function showSearchBlock() {
	document.getElementById("hidableSearchBlock").style.display = "block";
	document.getElementById('toggleSpan').onclick = function(){hideSearchBlock();};
	document.getElementById('toggleSpan').innerHTML = "Less options";
}

function hideSearchBlock() {
	document.getElementById("hidableSearchBlock").style.display = "none";
	document.getElementById('toggleSpan').onclick = function(){showSearchBlock();};
	document.getElementById('toggleSpan').innerHTML = "More options";
}

$( document ).ready(function() {
    hideSearchBlock();
});