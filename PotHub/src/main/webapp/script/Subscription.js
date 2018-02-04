function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
function gonext(){
	alert("creating");
	location.href = "createNewPost";
}
function gosub(){
	location.href = "Subscription";
}
function goFor(){
	location.href = "Forum";
}

function gotre(){
	location.href = "Trending";
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

function upfirst(){
	var h = document.getElementById("firstcount").innerHTML;
	alert("upvoted by 11 from" + h);
	document.getElementById("yesorno").value = "up";
	document.getElementById("gonext").submit();
	
}

function downfirst(){
	var h = document.getElementById("firstcount").innerHTML;
	alert("downvoted by 11 from" + h);
	document.getElementById("yesorno").value = "down";
	document.getElementById("gonext").submit();
}

function filter(aa){
	alert("the value is" + aa);
	document.getElementById("omgosh").value = aa;
	document.getElementById("thisis").submit();
}

function removingSub(rrr){
	alert("are you suree?");
	document.getElementById("uniquee").innerHTML = "Are you sure you want to unsubscribe from " + rrr + "?";
	document.getElementById("getthiss").value = rrr;
	document.getElementById("overlay1").style.display = "block";
}

function backk(){
	document.getElementById("overlay1").style.display = "none";
}

function unSUB(){
	document.getElementById("unsubbing").submit();
}