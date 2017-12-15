function initMap() {
	var address = {lat: 1.3787, lng: 103.8493};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 16,
		center: address
	});
	var marker = new google.maps.Marker({
		position: address,
		map: map
	});
}

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function toEditProfilePage() {
	location.href = "EditProfile";
}
