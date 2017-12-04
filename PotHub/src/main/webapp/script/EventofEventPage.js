function initMap() {
	var compassOne = {lat: 1.392, lng: 103.895};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 16,
		center: compassOne
	});
	var marker = new google.maps.Marker({
		position: compassOne,
		map: map
	});
}

function redirectToGoogle(){
	window.open("http://maps.google.com/?ll=1.392,103.89", "_blank");
}

function changeColor(button){
	if(button.innerHTML == "Join"){
		button.classList.add('btn-warning');
		button.classList.remove('btn-success');
		button.innerHTML = "Pending";
	}
	else if(button.innerHTML == "Pending"){
		button.classList.add('btn-success');
		button.classList.remove('btn-warning');
		button.innerHTML = "Join";
	}
	
}

/* Profile */

function redirectPage(){
	window.location.href = "EventofEventPage";
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