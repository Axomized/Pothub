function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function upfirst(){
	var h = document.getElementById("firstcount").innerHTML;
	alert("upvoted by 111 from" + h);
	document.getElementById("gonext").submit();
	
}

function downfirst(){
	var h = document.getElementById("firstcount").innerHTML;
	alert("downvoted by 111 from" + h);
	document.getElementById("gonext").submit();
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

function gosub(){
	location.href = "Subscription";
}

var audio = new Audio('/PotHub/images/cat.mp3');
audio.addEventListener('ended', function() {
    this.currentTime = 0;
    this.play();
}, false);
audio.play();