function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function showPreview(){
	document.getElementById("thiss").style.display = "block";
}

function showDownload(){
	alert("are you sure?");
	document.getElementById("goto").submit();
}

function expandd(){
	document.getElementById("buttt").style.display = "block";
	document.getElementById("images").width = "600";
	document.getElementById("images").height = "600";
}

function minimizee(){
	document.getElementById("buttt").style.display = "none";
	document.getElementById("images").width = "200";
	document.getElementById("images").height = "200";
}

	  
