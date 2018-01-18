var stompClient = null;
var iGN;
var eventName;
var currentScore;

function connect(username, topic) {
	iGN = username;
	eventName = topic;
    var socket = new SockJS('https://localhost:8443/ARandomName');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
var start = true;
$(document).ready(function(){
	$("#backToBarcodeBtn").click(function(){
		disconnect();
		window.location.replace("VideoCamera.html"); //Change to servlet once done
	});
	
	$("#startStreamingBtn").click(function(){
		if(start){
			stompClient.send("/app/other/" + eventName, {}, JSON.stringify({
				"messageType": "Show", "userToDisplay": iGN
			}));
			connectToStream(iGN, eventName);
			$(this).text("Stop Streaming");
			start = false;
		}else{
			stompClient.send("/app/other/" + eventName, {}, JSON.stringify({
				"messageType": "Hide", "userToDisplay": iGN
			}));
			$(this).text("Start Streaming");
			start = true;
		}
	});
	
	$("#startVotingBtn").click(function(){
		stompClient.send("/app/other/" + eventName, {}, JSON.stringify({
			"messageType": "Push", "userToDisplay": iGN
		}));
	});
	
	$("#changePointBtn").click(function(){
		
	});
	
	$("#endBtn").click(function(){
		if(confirm("I am sure and I want to end.")){
			stompClient.send("/app/other/" + eventName, {}, JSON.stringify({
				"messageType": "End", "userToDisplay": iGN
			}));
		}
	});
});

//Profile
function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
