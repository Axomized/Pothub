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
	document.getElementById("storeReport").value = ppp
	document.getElementById("overlay").style.display = "block";
}

function cancell(){
	document.getElementById("overlay").style.display = "none";
}

function success(){
	var x = document.getElementById("storeReport").value
	alert("Success reporting of " + x);
	document.getElementById("overlay").style.display = "none";
}

function gonext(){
	alert("creating");
	location.href = "createNewPost";
}

function gosub(){
	location.href = "Subscription";
}


var audio = new Audio('/PotHub/images/jj.mp3');
audio.addEventListener('ended', function() {
	var audio = new Audio('/PotHub/images/cat.mp3');
	audio.addEventListener('ended', function() {
		this.currentTime = 0;
	    this.play();
	}, false);
    audio.play();
}, false);
audio.play();