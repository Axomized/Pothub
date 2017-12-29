function redirectPage(eventName){
	window.location.href = "EventofEventPage/" + eventName;
}

$(function(){
	$(".top-container-image-gradient").mouseenter(function(){
		$(this).hide();
		$(this).next().show();
	});
	
	$(".overlay").mouseleave(function(){
		$(this).hide();
		$(this).prev().show();
	});
});

/* Profile */

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}