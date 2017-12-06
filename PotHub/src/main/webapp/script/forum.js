function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function upfirst(){
	var h = document.getElementById("firstcount").innerHTML;
	var p = parseInt(h);
	var i = (p + 1);
	document.getElementById("firstcount").innerHTML = i;
}

function downfirst(){
	var h = document.getElementById("firstcount").innerHTML;
	var i = (h-1);
	document.getElementById("firstcount").innerHTML = i;
}

function upsecond(){
	var h = document.getElementById("secondcount").innerHTML;
	var p = parseInt(h);
	var i = (p + 1);
	document.getElementById("secondcount").innerHTML = i;
}

function downsecond(){
	var h = document.getElementById("secondcount").innerHTML;
	var i = (h-1);
	document.getElementById("secondcount").innerHTML = i;
}

function upthird(){
	var h = document.getElementById("thirdcount").innerHTML;
	var p = parseInt(h);
	var i = (p + 1);
	document.getElementById("thirdcount").innerHTML = i;
}

function downthird(){
	var h = document.getElementById("thirdcount").innerHTML;
	var i = (h-1);
	document.getElementById("thirdcount").innerHTML = i;
}

function showsth(){
	document.getElementById("overlay").style.display = "block";
}

function cancell(){
	document.getElementById("overlay").style.display = "none";
}

function success(){
	alert("Success!");
	document.getElementById("overlay").style.display = "none";
}

function gonext(){
	alert("creating");
	location.href("createNewPost");
	
}