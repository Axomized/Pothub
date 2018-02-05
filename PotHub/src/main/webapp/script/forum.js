function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function upfirst(current){
	current = $(current);
	var totalUpvotes = current.next().text();
	var id = current.prev().prev().val();
	var name = current.prev().prev().prev().val();
	// Submit Vote
	$.ajax({
		"url": "Forum",
		"type": "POST",
		"data": {"upordown" : parseInt(totalUpvotes) + 1, "hisname": name, "hisid": id},
		success: function(res) {
			alert("Success");
			current.next().text(parseInt(totalUpvotes) + 1);
			window.location.href = "Forum";
		}
	});
}

function downfirst(current){
	current = $(current);
	var totalUpvotes = current.prev().text();
	var id = current.prev().prev().prev().prev().val();
	var name = current.prev().prev().prev().prev().prev().val();
	// Submit Vote
	$.ajax({
		"url": "Forum",
		"type": "POST",
		"data": {"upordown" : parseInt(totalUpvotes) - 1, "hisname": name, "hisid": id},
		success: function(res) {
			alert("Success");
			current.prev().text(parseInt(totalUpvotes) - 1);
			window.location.href = "Forum";
		}
	});
}

function showsth(ppp){
	document.getElementById("storeReport").value = ppp;
	document.getElementById("overlay").style.display = "block";
}

function cancell(){
	document.getElementById("overlay").style.display = "none";
}

function success(){
	var x = document.getElementById("storeReport").value;
	alert("Success reporting of " + x);
	document.getElementById("overlay").style.display = "none";
	if(document.getElementById("Check1").checked){
		document.getElementById("kimtan").value = document.getElementById("Check1").value;
	}
	else if(document.getElementById("Check2").checked){
		document.getElementById("kimtan").value = document.getElementById("Check2").value;
	}
	else if(document.getElementById("Check3").checked){
		document.getElementById("kimtan").value = document.getElementById("Check3").value;
	}
	else if(document.getElementById("Check4").checked){
		document.getElementById("kimtan").value = document.getElementById("Check4").value;
	}
	document.getElementById("whyareyou").submit();
}

function gonext(){
	location.href = "createNewPost";
}

function gosub(){
	location.href = "Subscription";
}

function gofor(){
	location.href = "Forum";
}

function gotre(){
	location.href = "Trending";
}

function sameas(){
	document.getElementById("dd1").submit();
}

function selectOnlyThis(id) {
	document.getElementById("comment").value = '';
	for (var i = 1;i <= 4; i++)
	{
		document.getElementById("Check" + i).checked = false;
	}
	document.getElementById(id).checked = true;
}

function checkforcheck(){
	for (var i = 1;i <= 4; i++)
	{
		document.getElementById("Check" + i).checked = false;
	}

}

function submit(input){
	input.parentNode.submit();
}

var audio = new Audio('images/jjawesome.mp3');
audio.addEventListener('ended', function() {
	audio.play();
}, false);
audio.play();