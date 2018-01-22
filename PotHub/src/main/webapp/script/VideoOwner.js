var stompClient = null, iGN, eventName, currentScore;

function connect(username, topic) {
	iGN = username;
	eventName = encodeURI(topic);
    var socket = new SockJS("https://localhost:8443/ARandomName");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function aaa() {
    	console.log("Connected");
    });
}

function stompDisconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}
var start = true;
$(document).ready(function aaa(){
	$("#backToBarcodeBtn").click(function aaa() {
		stompDisconnect();
		window.location.replace("VideoCamera.html"); //Change to servlet once done
	});
	
	$("#startStreamingBtn").click(function aaa() {
		if(start){
			stompClient.send("/app/other/" + eventName, {}, JSON.stringify({
				"messageType": "Show", "userToDisplay": iGN
			}));
			connectToStream(iGN, eventName);
			$(this).text("Stop Streaming");
			$("#videoDiv").show();
			start = false;
		}else{
			stompClient.send("/app/other/" + eventName, {}, JSON.stringify({
				"messageType": "Hide", "userToDisplay": iGN
			}));
			$(this).text("Start Streaming");
			$("#videoDiv").hide();
			start = true;
		}
	});
	
	$("#startVotingBtn").click(function aaa() {
		stompClient.send("/app/other/" + eventName, {}, JSON.stringify({
			"messageType": "Push", "userToDisplay": iGN
		}));
	});
	
	$("#changePointBtn").click(function aaa() {
		
	});
	
	$("#endBtn").click(function aaa() {
		if(confirm("Am you sure you want to end?")) {
			stompClient.send("/app/other/" + eventName, {}, JSON.stringify({"messageType": "End", "userToDisplay": iGN}));
			// End event db
			$.ajax({
				"url": "https://localhost/PotHub/BarcodeScanning",
				"type": "POST",
				"data": {"eventName": eventName, "status": "E"},
				success() {
					window.location.href = "/PotHub/EventofEvent/" + eventName;
				}
			});
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
