function showOverlay(imageGradient){
	var imageGradientParent = imageGradient.parentElement;
	imageGradient.style.display = "none";
	var overlay = imageGradientParent.getElementsByTagName('div')[3];
	overlay.style.display = "block";
}

function hideOverlay(overlay){
	var overlayParent = overlay.parentElement;
	overlay.style.display = "none";
	var imageGradient = overlayParent.getElementsByTagName('div')[1];
	imageGradient.style.display = "block";
}

/* Profile */

function redirectPage(){
	window.location.href = "../html/EventofEventPage.html";
}

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function toEditProfilePage() {
	location.href = "EditProfile.html";
}