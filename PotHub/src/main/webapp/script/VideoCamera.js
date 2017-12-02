var success = false;
var xhttp = new XMLHttpRequest();
var video = document.querySelector("#video");
var frameDiv = document.getElementById("frames");

xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		if(this.responseText == "True"){
			//onSuccessScanning(); //Success... Call Method
		}
	}
};

navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;

if (navigator.getUserMedia) {       
	navigator.getUserMedia({video: true}, handleVideo, videoError);
}

function handleVideo(stream) {
	video.src = window.URL.createObjectURL(stream);
}

function videoError(e) {
	// do something
}


var DailyDoseOfCarrot = setInterval(function(){ captureFrame();}, 1000);

function captureFrame(){
	if(success)
		clearInterval(DailyDoseOfCarrot);
	else{
		var canvas = document.createElement("canvas");
		canvas.width = 640;
		canvas.height = 480;
		var ctx = canvas.getContext("2d");
		ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
		var dataurl = canvas.toDataURL('image/png', 1000);
		frameDiv.appendChild(canvas);
		xhttp.open("POST", "/PotHub/processImage", true);
		xhttp.setRequestHeader("Content-Type", "application/upload");
		xhttp.send(dataurl);
	}
}

function onSuccessScanning(){
	success = true;
	alert("Welcome");
}
