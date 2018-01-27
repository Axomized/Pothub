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

/* Open when someone clicks on the span element */
function openNav() {
    document.getElementById("myNav").style.width = "100%";
}

/* Close when someone clicks on the "x" symbol inside the overlay */
function closeNav() {
    document.getElementById("myNav").style.width = "0%";
}
$( document ).ready(function() {
    hideSearchBlock();
});