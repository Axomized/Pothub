function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
function gonext(){
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

function submit(input){
	input.parentNode.submit();
}
//dont touch
function filter(aa){
	document.getElementById("omgosh").value = aa;
	document.getElementById("thisis").submit();
}

function removingSub(rrr){
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