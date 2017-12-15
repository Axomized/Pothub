var video = document.querySelector("#video");

navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;

if (navigator.getUserMedia) {       
	navigator.getUserMedia({video: true}, handleVideo, videoError);
}

function handleVideo(stream) {
	video.src = window.URL.createObjectURL(stream);
	moveBox(stream);
}

function videoError(e) {
	// do something
}

function connect() {
	var socket = new SockJS('/ARandomName');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		console.log('Connected: ' + frame);
	});
}

function moveBox(stream) {
	stompClient.send("/app/sendVideoStream", {}, stream);
}