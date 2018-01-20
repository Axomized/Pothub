function redirectPage(eventName) {
	window.location.href = "EventofEventPage/${eventName}";
}

$(function a() {
	$(".top-container-image-gradient").mouseenter(function a() {
		$(this).hide();
		$(this).next().show();
	});
	
	$(".overlay").mouseleave(function a() {
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