var stompClient = null;
var iGN;
var eventName;
var currentScore;

function disconnect() {
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

function updateStar(container){
	var starYellow = document.getElementById("starYellowBackground");
	var value = container.val();
	var width = scoreToWidth(value);
	starYellow.style.width = width + "px";
}

function scoreToWidth(score){
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

$("#starNum").click(function(){
	updateStar($(this));
});

$("#starNum").change(function(){
	updateStar($(this));
});

$("#starBlackBackground").click(function(e){
	var x = e.pageX - $(this).offset().left;
	var value = 0;
	switch(true){
	case (x <= 382 && x >= 354.5):
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

$("#voteBtn").click(function(){
	var userFrom = iGN;
	var userTo = $("#userIGN").text();
	var score = $("#starNum").val();
	var topic = eventName;
	sendVote(userFrom, userTo, score, topic);
	$("#popupBackground").hide();
});

//ProgressBar animation
function animateBar(userTo, score){
	$(".progressbarbar").each(function(index){
		//if($(this).parent().parent().previous().children(".name").text() === userTo){
		$(this).animate({width: '100%'});
		//}
	});
}

//Vote
function startVotingDisplay(userTo){
	document.getElementById("popupBackground").style.display = "block";
}

//End
function countDownAndRedirectToEndPage(){

}

//Video Stream
function hideStreamAsIfNotStreaming(){
	$("#videoDiv").hide();
}

function showStreamAsIfStreaming(){
	$("#videoDiv").show();
}

//Sidebar Animation
var sidebarOpen = true;
$("#leftBars").click(function(){
	if(sidebarOpen){
		document.getElementById("left-container").style.right = "260px";
		document.getElementById("right-container").style.marginLeft = "-260px";
		sidebarOpen = false;
	}else{
		document.getElementById("left-container").style.right = "0";
		document.getElementById("right-container").style.marginLeft = "0";
		sidebarOpen = true;
	}
});

$("#resultPage").click(function(){
	$(".userDetailsInfo-container").show();
	$(".userDetailsForm-container").hide();
});

$("#registerPage").click(function(){
	$(".userDetailsInfo-container").hide();
	$(".userDetailsForm-container").show();
});

//Connect
function connect(username, topic) {
	iGN = username;
	eventName = topic;
	var socket = new SockJS('https://localhost:8443/ARandomName');
	var stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		stompClient.subscribe('/topic/' + topic, function (socketReply) {
			var array = JSON.parse(socketReply.body);
			if(array.length>0){
				if(array[0].score===-1){
					startVotingDisplay(array[0].userTo);
				}else if(array[0].score===-2){
					hideStreamAsIfNotStreaming();
				}else if(array[0].score===-3){
					showStreamAsIfStreaming();
				}else if(array[0].score===-4){
					countDownAndRedirectToEndPage();
				}else{
					var json = JSON.parse(socketReply.body);
					for(var i=0; i<json.length; i++){animateBar(json.i.userTo, scoreToWidth(json.i.score));}
				}
			}
		});
		stompClient.send("/app/other/" + topic, {}, JSON.stringify({"messageType": "Retrieve", "userToDisplay": username}));
	});

	connectToStream(username, topic);
}

//Profile
function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
