var token; 			// Ajax (Dont delete)
var host;			// Ajax (Dont delete)
var localUsername; 	// Name of Current User
var ice;			// "Description" of Current User
var pcs = {};		// List of "Connected" peers
var pc;
var localStream; 	// Local Stream

$(document).ready(function(){
	localUsername = "Wx";
	
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
			onIce(res); 		//52
		},
		error: function() {
			//Error here
		}
	});
	
	$.ajax ({
		url: "https://global.xirsys.net/_token/MyFirstApp?k=" + localUsername, // Using username-based connection
		type: "PUT",
		contentType: 'text/plain',
		xhrFields: {
			withCredentials: false
		},
		headers: {
			"Authorization": "Basic " + btoa("ARandomUsername123:e72a0a98-f13e-11e7-9ec5-117660593455") // !! Security risk here !!
		},
		success: function (res){
			token = res.v;	// Gets token from json response
			getHost(token); // Pass token to get "host" 		// 92
		},
		error: function() {
			//Error here
		}
	});
	
	(function(a){(jQuery.browser=jQuery.browser||{}).mobile=/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4))})(navigator.userAgent||navigator.vendor||window.opera);
});

function onIce(r) { // 23
	ice = r.v;
	var constraints;
	//if a user is using a mobile browser 
	if(jQuery.browser.mobile){
		console.log("Is Mobile...");
		
	    constraints = {
	         audio: true,
	         video: {
	        	 width: 420, 
	        	 height: 380
	         }
	    };
	}else { 
		console.log("Not Mobile...");
		
		constraints = {audio: true, video: { width: 1280, height: 720 } }; 
	}
	navigator.mediaDevices.getUserMedia(constraints)
	.then(
			stream => onGetMedia(stream) // 82
	)
	.catch(
			function(err){
				console.log(err);
			}
	);
}

function onGetMedia(stream) {	// 73					// Gets stream from line 10
	var video = document.getElementById("localVideo");	// Get video element from html
	video.srcObject = stream;								// Start "streaming" own stream to video element
	localStream = stream;
	video.onloadedmetadata = function(e) {
	    video.play();
	};
}

//Getting fastest server information (host)
function getHost(token){ 	// 42
	$.ajax ({
		url: "https://global.xirsys.net/_host/MyFirstApp?type=signal&k=" + localUsername,
		type: "GET",
		contentType: 'text/plain',
		xhrFields: {
			withCredentials: false
		},
		headers: {
			"Authorization": "Basic " + btoa("ARandomUsername123:e72a0a98-f13e-11e7-9ec5-117660593455") // !! Security risk here !!
		},
		success: function (res){
			host = res.v;		// Gets "host" from json response
			openSocket(host); 	// Pass "host" to openSocket // 114
		},
		error: function() {
			//Error here
		}
	});
}

//Creating a socket
function openSocket(host){
	socket = new WebSocket(host + "/v2/" + token); 		// Using websocket to establish signaling
	socket.addEventListener("message", onSocketMessage);// On receiving message (Basically anything) call onSocketMessage //120
}

//On successful connection
function onSocketMessage(evt) {	// 116
	var data = JSON.parse(evt.data); // Get data from message
	var option;
	switch (data.m.o) {
	case "peers": // First to connect/Already connected (The Creator)
		var users = data.p.users;
		for(i = 0; i < users.length; i++) {
			console.log("User in chat:" + users[i]);
		}
		break;
	case "peer_connected": // Not first to connect (The Followers)
		var f = data.m.f.split("/"); // Get the username of the user
		var joining = f[f.length-1];
		console.log("New user joined:" + joining)
		callPeer(joining); // Pass username to callPeer() (Basically trying to connect to other users) // 161
		break;
	case "message":	// When it is a "message"
		switch(data.p.msg.type) {
		case "offer": //If the message is an "Offer"
			var desc = new RTCSessionDescription(data.p.msg); // Info on the peer
			var f = data.m.f.split("/"); // Get the username of the user
			var sender = f[f.length-1];
			var pc = createNewPeerConnection(sender); // Pass username to createNewPeerConnection() (Register the user and get his "description")
			pc.setRemoteDescription(desc); // "Register" the remote user
			pc.createAnswer().then(d => onCreateAnswer(d, sender));	// Reply back to the Offer // 261
			break;
		case "answer": //If the message is an "Answer"
			var desc = new RTCSessionDescription(data.p.msg);
			var f = data.m.f.split("/"); // Get the username of the user
			var sender = f[f.length-1];
			pcs[sender].pc.setRemoteDescription(desc); // "Register" the remote user
			break;
		case "candidate": //If the message is an "Candidate"
			var f = data.m.f.split("/"); // Get the username of the user
			var sender = f[f.length-1];
			var candidate = new RTCIceCandidate(data.p.msg);
			pcs[sender].pc.addIceCandidate(candidate);
		}
	}
}

function callPeer(peer) { // 134
	var pc = createNewPeerConnection(peer); // 169
	var dataChannel = pc.createDataChannel("data");
	pcs[peer].dc = dataChannel;
	setDataChannelHandlers(dataChannel);
	pc.createOffer().then(d => onCreateOffer(d, peer)); // 244
}

function createNewPeerConnection(username){ //162
	var pc = new RTCPeerConnection(ice);
	localStream.getTracks().forEach(track => pc.addTrack(track, localStream));
	pc.ondatachannel = evt => onDataChannel(evt); // 178
	pc.onicecandidate = evt => onIceCandidate(evt); // 207
	pcs[username] = {pc: pc, dc: null};
	return pc;
}

function onDataChannel(evt) { // 172
	var dataChannel = evt.channel;
	var keys = Object.keys(pcs);
	var comp;
	var localDescription;
	var remoteDescription;
	for(var i = 0; i < keys.length; i++) {
		comp = pcs[keys[i]];
		if(evt.currentTarget.localDescription.sdp == comp.pc.localDescription.sdp) {
			comp.dc = dataChannel;
		}
	}
	setDataChannelHandlers(dataChannel); // 193
}

function setDataChannelHandlers(dc) { // 190
	dc.onmessage = evt => onDataMessage(evt); // 198
	dc.onopen = evt => onDataChannelOpen(evt); // 203
}

function onDataMessage(evt) { // 194
	var messageObj = JSON.parse(evt.data);
	console.log(messageObj.f + " said: " + messageObj.msg);
}

function onDataChannelOpen(evt) { // 195
	console.log("Data Channel now opens");
}

function onIceCandidate(evt) { // 173
	var remoteUsername = getUsernameByRemoteDescription(evt.target.remoteDescription.sdp); // 232
	var candidate = evt.candidate;
	if (candidate != null && remoteUsername != null) {
		var cPkt = {
				type: "candidate",
				sdpMLineIndex: candidate.sdpMLineIndex,
				sdpMid: candidate.sdpMid,
				candidate: candidate.candidate
		};
		var pkt = {
				t: "u",
				m: {
					f: "MyFirstApp/" + localUsername,
					o: 'message',
					t: remoteUsername
				},
				p: {
					msg: cPkt
				}
		}
		socket.send(JSON.stringify(pkt)); // 120
	}
}

function getUsernameByRemoteDescription(sdp) { // 208
	var keys = Object.keys(pcs);
	var pc;
	for(var i = 0; i < keys.length; i++) {
		pc = pcs[keys[i]].pc;
		if(pc.remoteDescription.sdp == sdp) {
			return keys[i];
		}
	}
	return null;
}

function onCreateOffer(d, peer) { // 166
	//d.sdp = setVideoCodec(d.sdp);
	pcs[peer].pc.setLocalDescription(d);
	var pkt = {
			t: "u", 
			m: {
				f: "MyFirstApp/" + localUsername, 
				o: "message", 
				t: peer
			}, 
			p: {
				msg: d
			}
	};
	socket.send(JSON.stringify(pkt)); // 120
}

function onCreateAnswer(d, peer) { // 144
	//d.sdp = setVideoCodec(d.sdp);
	pcs[peer].pc.setLocalDescription(d);
	var pkt = {
			t: "u", 
			m: {
				f: "MyFirstApp/" + localUsername, 
				o: "message",
				t: peer
			}, 
			p: {
				msg: d
			}
	};
	socket.send(JSON.stringify(pkt)); // 120
}

/*
function setVideoCodec(sdp){
	var sdpLines = sdp.split('\r\n');
	var videoLine;
	
	for (var i = 0; i < sdpLines.length; i++) {
	    if (sdpLines[i].search('m=video') !== -1) {
	    	videoLine = i;
	    	break;
	    }
	}

	if (videoLine === null){
		return sdp;
	}else{
		var preferredCodec = [];
		for (var i = videoLine; i < sdpLines.length; i++) {
		    if (sdpLines[i].search('a=rtpmap') !== -1) {
		    	var rtpmapLine = sdpLines[i].split(" ");
		    	if(rtpmapLine[1].substr(0, 4) !== "H264"){
		    		sdpLines.splice(i, 1);
		    	}else{
		    		var array = [rtpmapLine[0].substr(9)];
		    		preferredCodec = preferredCodec.concat(array);
		    	}
		    }
		}
		
		for (var i = videoLine; i <sdpLines.length; i++){
			if(sdpLines[i].search('a=rtcp-fb') !== -1){
		    	var rtpmapLine = sdpLines[i].split(" ");
		    	var prepareDel = true;
		    	for(var j=0; j<preferredCodec.length;j++){
		    		if(rtpmapLine[0].substr(10) === preferredCodec[j]){
		    			prepareDel = false;
			    	}
		    	}
		    	if(prepareDel){
		    		sdpLines.splice(i, 5);
		    	}
		    }
		}
		
		for (var i = videoLine; i <sdpLines.length; i++){
			if(sdpLines[i].search('a=fmtp') !== -1){
		    	var rtpmapLine = sdpLines[i].split(" ");
		    	var prepareDel = true;
		    	for(var j=0; j<preferredCodec.length;j++){
		    		if(rtpmapLine[0].substr(7) === preferredCodec[j]){
		    			prepareDel = false;
			    	}
		    	}
		    	if(prepareDel){
		    		sdpLines.splice(i, 1);
		    	}
		    }
		}

		var videoLineLine = sdpLines[videoLine].split(" ");
		var videoLineLineExtracted = [videoLineLine[0], videoLineLine[1], videoLineLine[2]];
		videoLineLine = videoLineLineExtracted.concat(preferredCodec);
		sdpLines[videoLine] = videoLineLine.join(" ");
		
		sdp = sdpLines.join('\r\n');
		return sdp;
	}
}*/