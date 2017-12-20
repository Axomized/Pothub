function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

$(document).ready(function(){
	$(".front-container").mouseenter(function() {
		$(this).next().show();
		
	});
	$(".back-container").mouseleave(function(){
		$(this).hide();
	});
});