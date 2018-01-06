function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function redirectPage(eventName){
	window.location.href = "EventofEventPage/" + eventName;	
}

function getTime(){
	$(".time").each(function(){
		var date = Number($(this).text());
		var requestedDate = new Date(date);
		var currentTime = new Date();
		if(currentTime > requestedDate){
			$(this).html("Ended");
		}else{
			var timeLeft = requestedDate.getTime() - currentTime.getTime();
			var seconds = parseInt((timeLeft/1000)%60);
			var minutes = parseInt((timeLeft/(1000*60))%60);
			var hours = parseInt(timeLeft/(1000*60*60));
			$(this).html(hours + " Hours " + minutes + " Minutes " + seconds + " Seconds");
		}
	});
	
	setInterval(function(){ updateTime();}, 1000);
}

function updateTime(hours, minutes, seconds){
	$(".time").each(function(){
		if(!($(this).text() === "Ended")){
			var date = $(this).text(); 
			var returnString;
			
			var lastIndexSeconds = date.indexOf(' Seconds');
			var lastIndexMinutes = date.indexOf(' Minutes ');
			var lastIndexHour = date.indexOf(' Hours ');
			
			var seconds; 
			var minutes;
			var hours;
			
			if(lastIndexHour == -1){
				hours = 0;
			}else{
				hours = Number(date.substring(0, lastIndexHour));
			}
			if(lastIndexMinutes == -1){
				minutes = 0;
			}else{
				minutes = Number(date.substring(lastIndexHour + 7, lastIndexMinutes));
			}
			if(lastIndexSeconds == -1){
				seconds = 0;
			}else{
				seconds = Number(date.substring(lastIndexMinutes + 9, lastIndexSeconds));
			}
			
			seconds--;
			if(seconds < 0){
				minutes--;
				if(minutes < 0){
					hours--;
					if(hours < 0){
						
					}else{
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
						returnString = "Ended";
					}
				}
			}else{
				returnString = hours + " Hours " + minutes + " Minutes " + seconds + " Seconds";
			}
			
			$(this).html(returnString);
		}
	});
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
	
	$("#createButton").click(function(){
		$("#popup-container").show();
	});
	
	$("#closeBtn").click(function(){
		$("#popup-container").hide();
	});
});