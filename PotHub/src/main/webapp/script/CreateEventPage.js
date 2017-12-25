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
    var address = document.getElementById("Address");
    var additionalAddress = document.getElementById("AdditionalAddress");
    var guestName = document.getElementById("GuestName");
    var galleryUpload = document.getElementById("guest-container-right-container");
    
    if(eventName.value.length > 255){
    }else{return false}
    
    if(postalCode.value.length < 6 && postalCode.value.length >= 1){
    }else{return false}	
    
    if((additionalAddress.value.length + address.value.length) < 255){
    }else{return false}	
    
    if(guestName.childElementCount < 50){
    }else{alert("Upload only <10 photos");return false}	
    
    if($("#upload").files.length < 10){
    }else{return false}	
    
    return true;
}

$(function(){
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
						$("#guestNameList").val($("#guestNameList").val() + "," + $(this).text());
					}
					
				});
			}
		});
	});
	
	$("#postalInput").keyup(function(){
		var line = $("#postalInput").val()
		xhttp.open("POST", "/PotHub/GoogleGeocoding", true);
		xhttp.send(line);
	})
	
	$("#mainAddress").keyup(function(){
		var line = $("#mainAddress").val()
		xhttp.open("POST", "/PotHub/GoogleGeocoding", true);
		xhttp.send(line);
	})
	
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
		$('#fileNum').val("");
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