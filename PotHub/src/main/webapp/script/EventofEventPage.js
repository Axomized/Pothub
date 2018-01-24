function changeColor(button) {
	if(button.innerHTML === "Join") {
		button.classList.add("btn-warning");
		button.classList.remove("btn-success");
		button.innerHTML = "Pending";
	}
	else if(button.innerHTML === "Pending") {
		button.classList.add("btn-success");
		button.classList.remove("btn-warning");
		button.innerHTML = "Join";
	}else{
		if(confirm("Are you sure you want to leave event?")){
			button.classList.add("btn-success");
			button.classList.remove("btn-danger");
			button.innerHTML = "Join";
		}
	}
}

function redirectToInteractive() {
	window.location.href = "/PotHub/ShowBarcode";
}

function redirectToInteractiveOwner() {
	window.location.href = "/PotHub/BarcodeScanning";
}

/* Profile */

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
