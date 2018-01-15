function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function moveToNext(id, previousID, nextID) {
	id.value = id.value.replace(/[^\d]/g, "");
	
	if (id.value.length > 0) {
		document.getElementById(nextID).focus();
	}
	else {
		document.getElementById(previousID).focus();
	}
}

function onResend() {
	document.getElementById("pinInput1").required = false;
	document.getElementById("pinInput2").required = false;
	document.getElementById("pinInput3").required = false;
	document.getElementById("pinInput4").required = false;
	document.getElementById("pinInput5").required = false;
	document.getElementById("pinInput6").required = false;
}