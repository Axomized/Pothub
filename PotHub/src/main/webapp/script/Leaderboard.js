var stompClient = null, iGN, eventName, currentArray = [], videoDiv = $("#videoDiv");

function stompDisconnect () {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
}

function sendVote(userFrom, userTo, score, topic) {
	try{
		var userScore = {userFrom, userTo, score};
		stompClient.send("/app/update/" + topic, {}, JSON.stringify(userScore));
	}catch(err){
		alert("Error");
	}
}

function updateStar(container) {
	var starYellow = document.getElementById("starYellowBackground");
	var value = container.val();
	var width = scoreToWidth(value);
	starYellow.style.width = width + "px";
}

function scoreToWidth(score) {
	var value = score;
	var width = 0;
	switch(true){
	case (value >= 4):
		width += 17;
	width += (75 * value);
	break;
	case (value >= 3):
		width += 15;
	width += (75 * value);
	break;
	case (value >= 2):
		width += 13;
	width += (75 * value);
	break;
	case (value >= 1):
		width += 11;
	width += (75 * value);
	break;
	case (value > 0):
		width += 9;
	width += (75 * value);
	break;
	default:
		width = 0;
	break;
	}
	return width;
}

$("#starNum").click(function aaa () {
	updateStar($(this));
});

$("#starNum").change(function aaa () {
	updateStar($(this));
});

$("#starBlackBackground").click(function aaa(e) {
	var x = e.pageX - $(this).offset().left;
	var value = 0;
	switch(true){
	case (x <= 400 && x >= 354.5):
		value = 5;
	break;
	case (x >= 317):
		value = 4.5;
	break;
	case (x >= 277.5):
		value = 4;
	break;
	case (x >= 238):
		value = 3.5;
	break;
	case (x >= 200):
		value = 3;
	break;
	case (x >= 163):
		value = 2.5;
	break;
	case (x >= 123.5):
		value = 2;
	break;
	case (x >= 86):
		value = 1.5;
	break;
	case (x >= 46.5):
		value = 1;
	break;
	case (x >= 9):
		value = 0.5;
	break;
	default:
		value = 0;
	break;
	}
	document.getElementById("starNum").value = value;
	updateStar($("#starNum"));
});

$("#voteBtn").click(function aaa () {
	var userFrom = iGN;
	var userTo = $("#popupIGN").text();
	var score = $("#starNum").val();
	var topic = eventName;
	sendVote(userFrom, userTo, score, topic);
	$("#popupBackground").hide();
});

//Check difference in two json
function checkJson(obj1, obj2) {
	var ret = {};
	for(var i in obj2) {
	    if(!obj1.hasOwnProperty(i) || obj2[i] !== obj1[i]) {
	    	ret[i] = obj2[i];
	    }
	}
	return ret;
};

// ProgressBar animation
function changeAndAnimateBar(index, width, score){
	var childofchildofchildofchild = $(".right-bottom-leaderboard-container").eq(index).children().eq(2).children().eq(0).children().eq(0)
	childofchildofchildofchild.css("width", width + "%");
	childofchildofchildofchild.text((score * 2) + " / 10");
}

// Display New Bar
var currentTotalRank = 0;
function displayBar(userTo, score, index){
	var div = document.createElement("div"); 	// right-bottom-leaderboard-container row
	div.className = "right-bottom-leaderboard-container row";
	var div1 = document.createElement("div"); 	// right-bottom-ranking
	div1.className = "right-bottom-ranking";
	var p1 = document.createElement("p"); 		// <p></p>
	var b1 = document.createElement("b"); 		// <b></b>
	if(index != null){
		var content1 = document.createTextNode((Number(index) + 1));
	}else{
		var content1 = document.createTextNode(currentTotalRank);
	}
	b1.appendChild(content1); 	// <b>1</b>
	p1.appendChild(b1);			// <p><b>1</b></p>
	div1.appendChild(p1);		// <div class="right-bottom-ranking"> <p><b>1</b></p> </div>
	div.appendChild(div1);		// <div class="right-bottom-leaderboard-container row"> <div class="right-bottom-ranking> <p><b>1</b></p> </div> </div>
	
	var div2 = document.createElement("div"); 	// right-bottom-picture
	div2.className = "right-bottom-picture";
	var img1 = document.createElement("img"); 	// <img/>
	// Get User Profile Picture
	$.ajax({
		"url": "Image",
		"type": "POST",
		"data": {"iGN" : userTo},
		success(res) {
			img1.src = "Image/" + res;
		}
	});
	var p2 = document.createElement("p"); 		// <p></p>
	p2.className = "name";
	var b2 = document.createElement("b"); 		// <b></b>
	var content2 = document.createTextNode(userTo);
	b2.appendChild(content2); 	// <b>Blackpepper3</b>
	p2.appendChild(b2);			// <p><b>Blackpepper3</b></p>
	div2.appendChild(img1);		// <div class="right-bottom-picture"> <img src=""/> </div>
	div2.appendChild(p2);		// <div class="right-bottom-picture"> <img src=""/> <p><b>1</b></p> </div>
	div.appendChild(div2);
	
	var div3 = document.createElement("div"); 	// right-bottom-score
	div3.className = "right-bottom-score";
	var div4 = document.createElement("div"); 	// progressbar
	div4.className = "progressbar";
	var div5 = document.createElement("div"); 	// progressbarbar
	div5.className = "progressbarbar";
	div5.style.width = (score * 20) + "%";
	var content3 = document.createTextNode((score * 2) + " / 10");
	div5.appendChild(content3);
	div4.appendChild(div5);
	div3.appendChild(div4);
	div.appendChild(div3);
	$(div).click(function(){
		if($("#displayName").val() !== userTo){ // Check the one he clicked is not showing on the left already
			stompClient.send("/app/register2/" + eventName + "/" + iGN, {}, userTo);
		}
		displayUsersDetail(userTo);
	});
	if(index != null){
		$(".right-bottom-container").children("div").eq(index).after(div);
		$(".right-bottom-container").children("div").eq(index).remove();
	}else {
		$(".right-bottom-container").append(div);
	}
	currentTotalRank++;
}

function displayUsersDetail(lbd) {
	if(lbd.title !== null && lbd.title !== "") {
		var iGN = lbd.iGN;
		var title = lbd.title;
		var desc = lbd.desc;
		var score = lbd.totalScore;
		
		// Get User Profile Picture
		$.ajax({
			"url": "Image",
			"type": "POST",
			"data": {"iGN" : iGN},
			success(res) {
				$("#displayImage").attr("src", "Image/" + res);
			}
		});
		$("#displayName").text(decodeURI(title));
		$("#displayIGN").text("Cooked by: " + iGN);
		$("#displayScore").text(score + " / 10");
		$("#displayDesc").text(decodeURI(desc));
	}else {
		// Get User Profile Picture
		$.ajax({
			"url": "Image",
			"type": "POST",
			"data": {"iGN" : iGN},
			success(res) {
				$("#displayImage").attr("src", "Image/" + res);
			}
		});
		$("#displayName").text("User never register");
		$("#displayIGN").text("Cooked by: " + iGN);
		$("#displayScore").text(score + " / 10");
		$("#displayDesc").text("User never register");
	}
}

//Process json to see whether to display or animate
function displayAndAnimateBar(json) {
	currentTotalRank = currentArray.length + 1
	//Display top 3 (Not working yet)
	$("1stPlace").children().eq(0).children().eq(0).attr("src", "images/cat.png");
	/*
	for(v in json){
		if(v < 3){
			//console.log(json[v].userTo);
			// Get User Profile Picture
			$.ajax({
				"url": "https://webapp-180126154704.azurewebsites.net/Image",
				"type": "POST",
				"data": {"iGN" : json[v].userTo},
				success(res) {
					switch(v){
						case 0:
							$("#1stPlace").children("div").children("img").attr("src", "https://webapp-180126154704.azurewebsites.net/Image/" + res);
							break;
						case 1:
							$("#2ndPlace").children("div").children("img").attr("src", "https://webapp-180126154704.azurewebsites.net/Image/" + res);
							break;
						case 2:
							$("3rdPlace").children("div").children("img").attr("src", "https://webapp-180126154704.azurewebsites.net/Image/" + res);
							break;
					}
				}
			});
		}else{
			break;
		}
	}
	*/
	// Check difference
	var different = checkJson(currentArray, json);
	for(i in different){
		var diff = different[i];
		if(Object.keys(currentArray).length > i) {
			var curr = currentArray[i];
			if(diff.userTo === curr.userTo) {	// If the user same row as previous (1st....1st)
				if(diff.score !== curr.score) {			// If the user score have changed (1st, 4......1st, 4.5)
					var width = diff.score * 20;
					changeAndAnimateBar(i, width, diff.score);
				}
			}else {
				var userTo = diff.userTo;
				var score = diff.score;
				displayBar(userTo, score, i);
			}
		}
		else {
			var userTo = diff.userTo;
			var score = diff.score;
			displayBar(userTo, score, null);
		}
	}
	currentArray = different;
}

// Display leaderboardDetails( Own food picture, name and description)
function displayLeaderboardDetails(lbd){
	var iGN = lbd.iGN;
	var title = lbd.title;
	var desc = lbd.desc;
	var score = lbd.totalScore;
	
	// Get User Profile Picture
	$.ajax({
		"url": "Image",
		"type": "POST",
		"data": {"iGN" : iGN},
		success(res) {
			$("#displayImage").attr("src", "Image/" + res);
		}
	});
	$("#displayName").text(decodeURI(title));
	$("#displayIGN").text("Cooked by: " + iGN);
	$("#displayScore").text(score + " / 10");
	$("#displayDesc").text(decodeURI(desc));
	alert("Success");
}

// Vote
function startVotingDisplay(userTo) {
	$("#popupIGN").text(userTo);
	// Get User Profile Picture
	$.ajax({
		"url": "Image",
		"type": "POST",
		"data": {"iGN" : userTo},
		success(res) {
			$("#popupPicture").attr("src", "Image/" + res);
		}
	});
	document.getElementById("popupBackground").style.display = "block";
}

// End
function countDownAndRedirectToEndPage () {

}

// Video Stream
function hideStreamAsIfNotStreaming () {
	$("#videoDiv").hide();
}

function showStreamAsIfStreaming () {
	connectToStream(iGN, eventName);
	$("#videoDiv").show();
}

// Sidebar Animation
var sidebarOpen = true;
$("#leftBars").click(function aaa () {
	if(sidebarOpen) {
		document.getElementById("left-container").style.right = "260px";
		document.getElementById("right-container").style.marginLeft = "-260px";
		sidebarOpen = false;
	}else {
		document.getElementById("left-container").style.right = "0";
		document.getElementById("right-container").style.marginLeft = "0";
		sidebarOpen = true;
	}
});

$("#resultPage").click(function aaa () {
	$(".userDetailsInfo-container").show();
	$(".userDetailsForm-container").hide();
});

$("#registerPage").click(function aaa () {
	$(".userDetailsInfo-container").hide();
	$(".userDetailsForm-container").show();
});

// Register Detail
$("#registerBtn").click(function aaa () {
	var nameInput = $("#foodName").val();
	var descInput = $("#foodDesc").val();
	
	var title;
	var desc;
	if(nameInput !== null) {
		title = encodeURI(nameInput);
	}
	if(descInput !== null) {
		desc = encodeURI(descInput);
	}
	
	var foodDetail = {iGN, title, desc};
	stompClient.send("/app/register/" + eventName + "/" + iGN, {}, JSON.stringify(foodDetail));
});

// Connect
function connect(username, topic) {
	iGN = username;
	eventName = topic;
	var socket = new SockJS("https://localhost:8443/ARandomName");
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function aaa () {
		stompClient.subscribe("/topic/" + eventName + "/" + iGN, function aaa(socketReply) {
			var array = JSON.parse(socketReply.body);
			if(array.length>0) {
				if(array[0].score===-5) { // On successful register (A register)
					displayLeaderboardDetails(array[0]);
				}else if(array[0].score===-6){ 	// Left side sidebar display onclick (A click on B)
					displayUsersDetail(array[0]);
				}
			}
		});
		stompClient.subscribe("/topic/" + eventName, function aaa(socketReply) {
			try{
				var array = JSON.parse(socketReply.body);
				if(array.length>0) {
					if(array[0].score===-1) {
						startVotingDisplay(array[0].userTo); // "Push" on receiving owner's force vote
					}else if(array[0].score===-2) { // "Hide"
						hideStreamAsIfNotStreaming();
					}else if(array[0].score===-3) { // "Show"
						showStreamAsIfStreaming();
					}else if(array[0].score===-4) { // "End"
						countDownAndRedirectToEndPage();
					}else {							// On receiving normal info
						displayAndAnimateBar(array);
					}
				}
			}catch(err) {
				console.log("Error: \n" + err);
			}
		});
		// Get the current score
		stompClient.send("/app/other/" + eventName, {}, JSON.stringify({"messageType": "Retrieve", "userToDisplay": username}));
	});
}

// Profile
function showProfileDropdown () {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown () {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
