function connect(iGN) {
	var socket = new SockJS("https://localhost:8443/ARandomName");
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		stompClient.subscribe("/topic/" + iGN, function aaa() {
			window.location.href = "/PotHub/ParticipantLeaderboardPage";
		});
	});
}

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}