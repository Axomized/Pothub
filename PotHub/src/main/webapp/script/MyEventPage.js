function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function redirectPage(eventName){
	window.location.href = "EventofEventPage/" + eventName;	
}

function getTime(p, date){
	alert("Working");
	var requestedDate = new Date(date);
	var currentTime = new Date();
	var timeLeft = currentTime.getTime() - requestedDate.getTime();
	
	var seconds = parseInt((timeLeft/1000)%60);
	var minutes = parseInt((timeLeft/(1000*60))%60);
	var hours = parseInt((timeLeft/(1000*60*60))%24);
	
	p.innerHTML = hours + "Hours " + minutes + " Minutes" + seconds + " Seconds";
	setTimeout(function(){
		seconds -= 1;
		if(seconds < 0){
			seconds = 60;
			minute -= 1;
			if(minute < 0){
				minute = 60;
				hours -= 1;
			}
		}
		p.innerHTML = hours + "Hours " + minutes + " Minutes" + seconds + " Seconds";
	}, 1000);
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