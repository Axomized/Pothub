function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function upfirst(){
	var h = document.getElementById("firstcount").innerHTML;
	alert("upvoted by 1 from" + h);
	location.reload();
}

function downfirst(){
	var h = document.getElementById("firstcount").innerHTML;
	alert("downvoted by 1 from" + h);
	location.reload();
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
	location.href = "createNewPost";
	
}