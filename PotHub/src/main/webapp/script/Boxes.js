var isMouseDown = false;
var mouseTop;
var mouseLeft;
var topOffset;
var leftOffset;

function connect() {
	var socket = new SockJS('/ARandomName');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/Boxes', function (updateBox) {
			updateBoxOnScreen(String(updateBox.body));
		});
	});
}

function moveBox() {
	var boxPosition = {"boxTop":mouseTop, "boxLeft":mouseLeft};
	stompClient.send("/app/updateBoxPosition", {}, JSON.stringify(boxPosition));
}

function updateBoxOnScreen(result) {
	var boxPos = JSON.parse(result);
	document.getElementById("redBox").style.top = boxPos.boxTop + "px";
	document.getElementById("redBox").style.left = boxPos.boxLeft + "px";
}

$(function() {
	$(document).on('mousemove', function(event) {
		mouseTop = event.pageY;
		mouseLeft = event.pageX;
	});
	
	$("#redBox").on('mousedown', function (e){
		isMouseDown = true;
	    
	    topOffset = e.pageY - $("#redBox").offset().top;
	    leftOffset = e.pageX - $("#redBox").offset().left;
	    
	    $(this).parents().on('mousemove', function (e) {
	    	if(isMouseDown){
	    		moveBox();
		    	$("#redBox").offset({
		            top: e.pageY - topOffset,
		            left: e.pageX - leftOffset
		        });
	    	}
	    });
	    
	    $(this).parents().on('mouseup', function (e){
			isMouseDown = false;
		});
	});
	
	
});