//var success = false;
var xhttp = new XMLHttpRequest();
var video = document.querySelector("#video");
var track;

xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		if(this.responseText.substring(0,4) == "True"){
			onSuccessScanning(this.responseText.substring(5)); //Success... Call Method
		}
	}
};

navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;

if (navigator.getUserMedia) {       
	navigator.getUserMedia({video: true}, handleVideo, videoError);
}

function handleVideo(stream) {
	video.src = window.URL.createObjectURL(stream);
	track = stream.getTracks()[0];
}

function videoError(e) {
	// do something
}


var DailyDoseOfCarrot = setInterval(function(){ captureFrame();}, 500);

function captureFrame(){
	var canvas = document.createElement("canvas");
	canvas.width = 640;
	canvas.height = 480;
	var ctx = canvas.getContext("2d");
	ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
	var dataurl = canvas.toDataURL('image/png', 1);
	xhttp.open("POST", "/PotHub/ProcessImage", true);
	xhttp.setRequestHeader("Content-Type", "application/upload");
	xhttp.send(dataurl);
}

function onSuccessScanning(barcodeResult){
	//success = true;
	alert(barcodeResult);
}

function stopRecording(){
	track.stop();
	clearInterval(DailyDoseOfCarrot);
}