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

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}