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
		"data": {"Type": "Close", "eventName" : eventName},
		success: function aaa(){
			windows.location.href = "../EventPage"; // Redirect the page
		}
	});
}

function removeConfirmRequest(eventName, username) {
	// Send to servlet to cancel event confirm
	$.ajax({
		"url": "EventofEventPage",
		"type": "POST",
		"data": {"Type": "RemoveConfirm", "eventName" : eventName, "iGN": username}
	});
	windows.location.href = "../EventPage"; // Redirect page
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
		"data": {"Type": "Join", "eventName" : eventName, "iGN": username},
		success: function aaa(res) {
			console.log("Wolf: " + res);
			console.log("Wolf2: " + Boolean.valueOf(res));
			if(Boolean.valueOf(res)) {
				location.reload(); // Reload the page cause I lazy retype some codes
			}else {
				alert("Maximum participants entered.");
			}
		}
	});
}

function redirectToInteractive() {
	window.location.href = "../ShowBarcode";
}

function redirectToInteractiveOwner() {
	window.location.href = "../BarcodeScanning";
}

function showReport() {
	$("#popup-container").show();
}

function hideReport() {
	$("#popup-container").hide();
}

function reportEvent(eventID, iGN, eventOwner) {
	var nameOfRadio = $('input[type=radio]:checked').val();
	if(nameOfRadio == undefined) {
		alert("Please select one reason.");
	}else if(nameOfRadio === "Others") {
		var othersText = $("#othersText").val();
		if(othersText === null || othersText === ""){
			othersText = "Others";
		}
		if(confirm("Are you sure?")) {
			$.ajax({
				"url": "../EventPage",
				"type": "POST",
				"data": {"eventID": eventID, "iGN" : iGN, "eventOwner": eventOwner, "Type": "Others", "Other": othersText},
				success: function(){
					alert("Report success.");
					hideReport();
				}
			});
		}
	}else {
		if(confirm("Are you sure?")) {
			$.ajax({
				"url": "../EventPage",
				"type": "POST",
				"data": {"eventID": eventID, "iGN" : iGN, "eventOwner": eventOwner, "Type": nameOfRadio},
				success: function(){
					alert("Report success.");
					hideReport();
				}
			});
		}
	}
}

$(document).ready(function() {
	$("#reportBtn").click(function(){showReport();});
	$("#closeBtn").click(function(){hideReport();});
	$("input").on("change", function() {
		var nameOfRadio = $('input[type=radio]:checked').val();
		if(nameOfRadio === "Others") {
			$("#othersText").show();
		} else{
			$("#othersText").hide();
		}
	});
});

/* Profile */

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
