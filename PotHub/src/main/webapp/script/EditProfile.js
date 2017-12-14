function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function startedTyping(inputsForFilled) {
	if (inputsForFilled.value.length > 0) {
		document.getElementById("updateBtn").disabled = false;
		document.getElementById("updateBtn").style.cursor = "pointer";
	}
	else {
		document.getElementById("updateBtn").disabled = true;
		document.getElementById("updateBtn").style.cursor = "not-allowed";
	}
}

function checkFile() {
	if (document.getElementById("profilePicFile").value != null) {
		alert("File chosen");
		document.getElementById("updateBtn").disabled = false;
		document.getElementById("updateBtn").style.cursor = "pointer";
	}
}