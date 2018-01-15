var hidden = true;
var googleMapLink;
var xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		if(this.responseText != null || this.responseText != ""){
			if(this.responseText.substring(0, 7) == "Geocode")
				document.getElementById("mainAddress").value = this.responseText.substring(7);
			else if(this.responseText.substring(0, 7) == "Reverse")
				document.getElementById("postalInput").value = this.responseText.substring(7);
		}
	}
};

function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

// For Iframe's script
function showPreview(){
	if(hidden){
		var iframe = $("#iframeEvent");
		var contents = iframe.contents();
		var fileupload = document.getElementById("fileUpload");
		if(fileupload.value.length > 0){
			var reader = new FileReader();

			reader.onload = function (e) {
				contents.find(".headerImage").attr("src", e.target.result);
			}
			reader.readAsDataURL(fileupload.files[0]);
		}else{
			var src;
			switch($("#fileNum").val()){
				case "4":
					src = "../images/wood.jpeg";
					break;
				case "5":
					src = "../images/blue.jpeg";
					break;
				case "6":
					src = "../images/mountain.jpeg";
					break;
			}
			contents.find(".headerImage").attr("src", src);
		}
		contents.find(".title").text(decodeURI(encodeURI($("#eventName").val())));
		contents.find(".desc").text(decodeURI(encodeURI($("#description").val())));
		var guestDiv = contents.find(".guest");
		var guestInputValue = $("#guestNameList").val();
		var guestArray = guestInputValue.split("_");
		for(var i = 0; i < guestArray.length; i++){
			var div = $(document.createElement('div'));
			div.append("<img src='../images/cat.png' alt='Guest's Profile Picture' height='50' width='50'><br>")
			div.append("<p>" + decodeURI(encodeURI(guestArray[i])) + "</p>");
			guestDiv.append(div);
		}
		var eventGallery = contents.find(".event-gallery"); //Iframe's gallery
		var input = document.getElementById("upload");
		for (var i = 0; i < input.files.length; i++) {
			if (input.files && input.files[i]) 
			{
				var reader = new FileReader();

				reader.onload = function (e) {
					eventGallery.append("<img src='" + e.target.result + "' alt='Gallery's pictures'>");
				}
				reader.readAsDataURL(input.files[i]);
			}
		}
		var googleMapLink = "https://www.google.com/maps/embed/v1/place?key=AIzaSyBksQSICQgS5CoCf49IyTtozR8R198pTS0&q=" + encodeURI($("#mainAddress").val());
		var googleMap = contents.find("#googleMap").attr("src", googleMapLink);
		var address = encodeURI($("#mainAddress").val() + ", " + $("#additionalAddress").val());
		contents.find(".venue").text(decodeURI(address));
		var timedate = $("#eventDate").val() + " " + $("#timeinput").val();
		contents.find(".time").text(timedate);
		$("#popup-container").show();
		hidden = false;
	}else{
		$("#popup-container").hide();
		hidden = true;
	}
}

var open = false;
function showDefault() {
	if(open){
		$("#default-thumbnail").fadeOut();
		open = false;
	}
	else{
		$("#default-thumbnail").fadeIn();
		open = true;
	}
}

function changeThumbnail(current, number){
	var chosen = document.getElementById("chosenThumbnail");
	chosen.src = current.src;
	document.getElementById("fileUpload").value = "";
	document.getElementById("fileNum").value = number;
	open = true;
	showDefault();
}

function addToLeft(text){
	text.remove();
	var string = text.innerHTML;
	$("#guestnamelist").append("<option>" + string + "</option>");
}

function validateForm() {
	var eventName = document.getElementById("eventName");
	var postalCode = document.getElementById("postalInput");
	var address = document.getElementById("mainAddress");
	var additionalAddress = document.getElementById("additionalAddress");
	var guestName = document.getElementById("guestNameList");
	var galleryUpload = $("#upload");

	if(eventName.value.length < 255){
		
	}else{
		alert("Event Name: Failed");
		return false;
	}

	if(postalCode.value.length <= 6 && postalCode.value.length >= 1){
		
	}else{
		alert("Postal Code: Failed");
		return false;
	}	

	if(additionalAddress.value.length + address.value.length < 255){
		
	}else{
		alert("Address Name: Failed");
		return false;
	}	

	if(guestName.childElementCount < 50){
		
	}else{
		alert("Guest Name List: Failed");
		return false;
	}	

	if(galleryUpload.files.length < 10){
		
	}else{
		alert("Upload only <10 photos");
		return false;
	}	
}

$(function(){
	$("#closeBtn").click(function(){
		$("#popup-container").hide();
		hidden = true;
	});
	
	$("#textguestname").bind('input', function () {
		$("option").each(function(){
			if($("#textguestname").val() == $(this).val()){
				$('#guest-container-right-container').append("<p class='right-guest' onclick='addToLeft(this)'>" + $("#textguestname").val() + "</p>");
				$(this).remove();
				$("#textguestname").val("");
				$(".right-guest").each(function(index){
					if(index == 0){
						$("#guestNameList").val($(this).text());
					}else{
						$("#guestNameList").val($("#guestNameList").val() + "_" + $(this).text());
					}

				});
			}
		});
	});

	$("#postalInput").keyup(function(){
		var line = $("#postalInput").val();
		if(line == ""){
			$("#mainAddress").prop('readonly', false);
		}else if(line.length > 0){
			$("#mainAddress").prop('readonly', true);
			xhttp.open("POST", "/PotHub/GoogleGeocoding", true);
			xhttp.send(line);
		}
	});

	$("#postalInput").change(function(){
		var line = $("#postalInput").val();
		if(line == ""){
			$("#mainAddress").prop('readonly', false);
		}else if(line.length > 0){
			$("#mainAddress").prop('readonly', true);
			xhttp.open("POST", "/PotHub/GoogleGeocoding", true);
			xhttp.send(line);
		}
	});

	$("#mainAddress").keyup(function(){
		var line = $("#mainAddress").val();
		if(line == ""){
			$("#postalInput").prop('readonly', false);
		}else if(line.length > 0){
			$("#postalInput").prop('readonly', true);
			xhttp.open("POST", "/PotHub/GoogleGeocoding", true);
			xhttp.send(line);
		}
	});

	$("#mainAddress").change(function(){
		var line = $("#mainAddress").val();
		if(line == ""){
			$("#postalInput").prop('readonly', false);
		}else if(line.length > 0){
			$("#postalInput").prop('readonly', true);
			xhttp.open("POST", "/PotHub/GoogleGeocoding", true);
			xhttp.send(line);
		}
	});

	$('#fileUpload').change(function(){
		var input = this;
		var url = $(this).val();
		var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
		if (input.files && input.files[0]&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) 
		{
			var reader = new FileReader();

			reader.onload = function (e) {
				$('#chosenThumbnail').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
		$('#fileNum').removeAttr('value');
		open = true;
		showDefault();
	});

	$('#upload').change(function(){
		var input = this;
		var url = $(this).val();
		var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
		for (var i = 0; i < input.files.length; ++ i) {
			if (input.files && input.files[i] && (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) 
			{
				var reader = new FileReader();

				reader.onload = function (e) {
					$('#gallery').append(
							"<div class='removableImage' style='position:relative;'>" +
							"<img src='" + e.target.result + "' alt='User's pictures' height='100px' width='200px' onclick='removeImg(this)' >" +
							"<div style='position:absolute;top:0;left:0;width:200px;height:100px;z-index:1;background-color:rgba(155,155,155,0.5);display:none'></div>" +
					"</div>");

					$('.removableImage').mouseenter(function(){
						$(this).children("div").css("display", "block");
						$('.removableImage').click(function(){
							$(this).remove();
						})
					});

					$('.removableImage').mouseleave(function(){
						$(this).children("div").css("display", "none");
					});
				}
				reader.readAsDataURL(input.files[i]);
			}
		}
		open = true;
		showDefault();
	});


});