function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function showBehalfName() {
	var x = document.getElementById("checkYes").checked;
	var behalfName = document.getElementById("behalfName");
	
	if (x == true) {
		behalfName.style.display = "block";
		behalfName.required = true;
	}
	else {
		behalfName.style.display = "none";
		behalfName.required = false;
	}
}

var visaRegex = new RegExp("^4");
var mastercardRegex = new RegExp("^5[1-5]|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720");
var amexRegex = new RegExp("^3[47]");

function checkCardType() {
	var cardNo = document.getElementById("cardNo").value;
	//Sanitize the input
	cardNo = cardNo.replace(/[^\d]/g, '');
	
	//Visa
	if (cardNo.match(visaRegex)) {
		document.getElementById("visaIcon").style.display = "inline-block";
	}
	else if (cardNo.match(mastercardRegex)) {
		document.getElementById("mastercardIcon").style.display = "inline-block";
	}
	else if (cardNo.match(amexRegex)) {
		document.getElementById("amexIcon").style.display = "inline-block";
	}
	else {
		document.getElementById("visaIcon").style.display = "none";
		document.getElementById("mastercardIcon").style.display = "none";
		document.getElementById("amexIcon").style.display = "none";
	}
}
