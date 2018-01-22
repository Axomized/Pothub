function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function submitting(){
	if(document.getElementById("exampleFormControlInput1").value == ""){
		alert("Please Fill in your Forum Title");
		return false;
	}
	else if(document.getElementById("exampleFormControlTextarea1").value == ""){
		alert("Please Fill in your Forum Description");
		return false;
	}
	else if(document.getElementById("needit").value == ""){
		alert("Please select a thumbnail picture");
		return false;
	}
	else{
		return confirm("Are you sure?");
	}

}

function goback(){
	window.location = "Forum";
}

//function showT(){
//	document.getElementById("text1").style.display = "block";
//}

function showP(){
	document.getElementById("image1").style.display = "block";
}

function showV(){
	document.getElementById("video1").style.display = "block";
}

function showU(){
	document.getElementById("link1").style.display = "block";
}

function showF(){
	document.getElementById("file1").style.display = "block";
}

//function closeT(){
//	document.getElementById("text1").style.display = "none";
//}

function closeI(){
	document.getElementById("image1").style.display = "none";
}

function closeV(){
	document.getElementById("video1").style.display = "none";
}

function closeU(){
	document.getElementById("link1").style.display = "none";
}

function closeF(){
	document.getElementById("file1").style.display = "none";
}
