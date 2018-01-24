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

function onlyNumbers(inputsForFilled) {
	inputsForFilled.value = inputsForFilled.value.replace(/[^\d]/g, "");
}

function checkFile() {
	if (document.getElementById("profilePicFile").value != null) {
		document.getElementById("updateBtn").disabled = false;
		document.getElementById("updateBtn").style.cursor = "pointer";
	}
}

function checkSelect() {
	var genderSelect = document.getElementById("genderSelect");
	if (genderSelect.options[genderSelect.selectedIndex].value != "") {
		document.getElementById("updateBtn").disabled = false;
		document.getElementById("updateBtn").style.cursor = "pointer";
	}
}

function isBoxChecked() {
	var checked = document.getElementById("checkFilter").checked;
	if (checked == true || checked == false) {
		document.getElementById("updateBtn").disabled = false;
		document.getElementById("updateBtn").style.cursor = "pointer";
	}
}