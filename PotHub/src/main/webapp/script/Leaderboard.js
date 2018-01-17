var stompClient = null;

function connect() {
    var socket = new SockJS('/ARandomName');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/update', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

$("#starNum").change(function(){
	var starYellow = document.getElementById("starYellowBackground");
	var value = $(this).val();
	var width = 0;
	switch(value){
		case (value >= 4):
			width += 2;
		case (value >= 3):
			width += 2;
		case (value >= 2):
			width += 2;
		case (value >= 1):
			width += 2;
		case (value > 0):
			width += 9;
			width += (7.5 * value);
			break;
	}
	starYellow.width = width;
	alert("Entered");
});

$("#voteBtn").click(function(){
	alert("hello");
});


//Profile
function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}
