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

function closeEvent(eventName) {
	// Send to servlet to close event
	$.ajax({
		"url": "EventofEventPage",
		"type": "POST",
		"data": {"Type": "Close", "eventName" : eventName}
	});
	location.reload(); // Reload the page cause I lazy retype some codes
}

function removeConfirmRequest(eventName, username) {
	// Send to servlet to cancel event confirm
	$.ajax({
		"url": "EventofEventPage",
		"type": "POST",
		"data": {"Type": "RemoveConfirm", "eventName" : eventName, "iGN": username}
	});
	location.reload(); // Reload the page cause I lazy retype some codes
}

function removePendingRequest(eventName, username) {
	// Send to servlet to cancel event pending
	$.ajax({
		"url": "EventofEventPage",
		"type": "POST",
		"data": {"Type": "RemovePending", "eventName" : eventName, "iGN": username}
	});
	location.reload(); // Reload the page cause I lazy retype some codes
}

function sendJoinRequest(eventName, username) {
	// Send to servlet to join event
	$.ajax({
		"url": "EventofEventPage",
		"type": "POST",
		"data": {"Type": "Join", "eventName" : eventName, "iGN": username}
	});
	location.reload(); // Reload the page cause I lazy retype some codes
}

function redirectToInteractive() {
	window.location.href = "../ShowBarcode";
}

function redirectToInteractiveOwner() {
	window.location.href = "../BarcodeScanning";
}

/* Profile */

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
