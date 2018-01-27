function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function redirectPage(eventName) {
	window.location.href = "EventofEventPage/" + eventName;
}

function updateTime() {
	$(".time").each(function aaa() {
		if( $(this).text() !== "Ended" && $(this).text() !== "Ongoing") {
			var returnString;
			var seconds;
			var minutes;
			var hours;
			var date = $(this).text(); 
			var lastIndexSeconds = date.indexOf(" Seconds");
			var lastIndexMinutes = date.indexOf(" Minutes ");
			var lastIndexHour = date.indexOf(" Hours ");
			
			if(lastIndexHour === -1) {
				hours = 0;
			}else{
				hours = Number(date.substring(0, lastIndexHour));
			}
			if(lastIndexMinutes === -1) {
				minutes = 0;
			}else {
				minutes = Number(date.substring(lastIndexHour + 7, lastIndexMinutes));
			}
			if(lastIndexSeconds === -1) {
				seconds = 0;
			}else {
				seconds = Number(date.substring(lastIndexMinutes + 9, lastIndexSeconds));
			}
			
			seconds--;
			if(seconds < 0) {
				minutes--;
				if(minutes < 0) {
					hours--;
					if(hours >= 0) {
						minutes = 59;
						seconds = 59;
					}
				}else{
					seconds = 59;
				}
			}
			
			if(hours <= 0){
				returnString = "0 Hours " + minutes + " Minutes " + seconds + " Seconds";
				if(minutes <= 0){
					returnString = "0 Hours 0 Minutes " + seconds + " Seconds";
					if(seconds <= 0){
						// End event db
						$.ajax({
							"url": "BarcodeScanning",
							"type": "POST",
							"data": {"eventName": eventName, "status": "E"},
							success: function aaa() {
								window.location.href = "EventofEvent/" + eventName;
							}
						});
						returnString = "Ongoing";
					}
				}
			}else{
				returnString = hours + " Hours " + minutes + " Minutes " + seconds + " Seconds";
			}
			
			$(this).html(returnString);
		}
	});
}

function getTime(){
	setInterval(function aaa(){ updateTime();}, 1000);
}

function checkPriviledge(isPriviledged){
	if(isPriviledged === "true"){
		window.location.href = "CreateEventPage";
	}else{
		$("#popup-container").show();
	}
}

$(document).ready(function(){
	$(".front-container").mouseenter(function() {
		$(this).next().slideDown(100);
		$(this).hide();
	});
	
	$(".back-container").mouseleave(function(){
		$(this).prev().show();
		$(this).slideUp(100);
	});
	
	$("#closeBtn").click(function(){
		$("#popup-container").hide();
	});
});