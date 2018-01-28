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
	var src = document.getElementById("profilePicFile");
	var target = document.getElementById("profilePicThumbnail");
	
	if (src.value != null) {
		document.getElementById("updateBtn").disabled = false;
		document.getElementById("updateBtn").style.cursor = "pointer";
	}
	
	var fr = new FileReader();
	fr.onload = function() {
		target.src = fr.result;
	}
	fr.readAsDataURL(src.files[0]);
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
