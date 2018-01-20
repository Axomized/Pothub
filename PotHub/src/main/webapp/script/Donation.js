function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function showBehalfName() {
	var x = document.getElementById("checkYes").checked;
	var behalfNameDiv = document.getElementById("behalfNameDiv");
	
	if (x == true) {
		behalfNameDiv.style.display = "block";
		behalfNameDiv.required = true;
	}
	else {
		behalfNameDiv.style.display = "none";
		behalfNameDiv.required = false;
	}
}

var visaRegex = new RegExp("^4");
var mastercardRegex = new RegExp("^5[1-5]|^222[1-9]|^22[3-9][0-9]|^2[3-6][0-9]{2}|^27[01][0-9]|^2720");
var amexRegex = new RegExp("^3[47]");
var dinersRegex = new RegExp("^36|^3[89]|^30[0-5]|^3095");
var jcbRegex = new RegExp("^35(2[89]|[3-8][0-9])");

function checkCardType() {
	var cardNoInput = document.getElementById("cardNo");
	cardNoInput.value = cardNoInput.value.replace(/[^\d]/g, "");
	var cardNo = cardNoInput.value;
	
	//var cardNo = document.getElementById("cardNo").value;
	//cardNo = cardNo.replace(/[^\d]/g, "");
	
	//Visa
	if (cardNo.match(visaRegex) && cardNo.length <= 19) {
		document.getElementById("visaIcon").style.display = "inline-block";
	}
	//Mastercard
	else if (cardNo.match(mastercardRegex) && cardNo.length <= 19) {
		document.getElementById("mastercardIcon").style.display = "inline-block";
	}
	//American Express
	else if (cardNo.match(amexRegex) && cardNo.length <= 19) {
		document.getElementById("amexIcon").style.display = "inline-block";
	}
	//Diners Club
	else if (cardNo.match(dinersRegex) && cardNo.length <= 19) {
		document.getElementById("dinersIcon").style.display = "inline-block";
	}
	//JCB
	else if (cardNo.match(jcbRegex) && cardNo.length <= 19) {
		document.getElementById("jcbIcon").style.display = "inline-block";
	}
	else {
		document.getElementById("visaIcon").style.display = "none";
		document.getElementById("mastercardIcon").style.display = "none";
		document.getElementById("amexIcon").style.display = "none";
		document.getElementById("dinersIcon").style.display = "none";
		document.getElementById("jcbIcon").style.display = "none";
	}
}
