function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

$(document).ready(function(){
	$(".front-container").mouseenter(function() {
		$(this).next().slideDown(100);
		
	});
	
	$(".back-container").mouseleave(function(){
		$(this).slideUp(100);
	});
	
	$("#createButton").click(function(){
		$("#popup-container").show();
	});
	
	$("#closeBtn").click(function(){
		$("#popup-container").hide();
	});
});