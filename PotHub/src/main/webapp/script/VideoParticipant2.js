var localUsername = "wx";
var roomID = 'BroadcastRoom';
var iceServer = [];

$.ajax({
	url: "https://global.xirsys.net/_turn/MyFirstApp/",
	type: 'PUT',
	contentType: 'text/plain',
	xhrFields: {
		withCredentials: false
	},
	headers: {
		"Authorization": "Basic " + btoa("ARandomUsername123:e72a0a98-f13e-11e7-9ec5-117660593455")
	},
	success: function(res) {
		iceServer = res.v.iceServers;
		startConnection();
	},
	error: function() {
		//Error here
	}
});

function startConnection(){
	var connection = new RTCMultiConnection();
	
	connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/';

	connection.enableScalableBroadcast = true;
	
	connection.maxRelayLimitPerUser = 1;
	
	connection.autoCloseEntireSession = true;
	
	connection.codecs.video = 'H264';
	
	var stunServer = {url: "stun:119.74.135.44:6666"};
	connection.iceServers = [stunServer];

	/*
	for(var i = 0; i < iceServer.length; i++){
		connection.iceServers.push({"url":"stun:192.168.1.17:6666"});
	}
	*/
	
	connection.session = {
	    audio: true,
	    video: true,
	    oneway: true
	};
	
	connection.sdpConstraints.mandatory = {
	    OfferToReceiveAudio: false,
	    OfferToReceiveVideo: false
	};
	
	connection.userid = localUsername;
	connection.checkPresence(roomID, function(isRoomEists, roomID) {
		if(isRoomEists) {
			connection.join(roomID, function(){
				console.log("Room Joined");
			});
		}
		else {
			console.log(roomID + " Room not opened yet.")
		}
	});
	
	connection.onstream = function(event){
		var video = event.mediaElement;
		video.autoplay = true;
		video.controls = false;
		document.getElementById("videoDiv").append(video);
	}
}
