function validateEmail() {
	var email = document.getElementById("email").value;
    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) 
    {
		document.getElementById("messageEmail").style.visibility = "visible";
	}
	else 
	{
		document.getElementById("messageEmail").style.visibility = "hidden";
	}

}

function validatePassword() {
	var password = document.getElementById("password").value;
	var upperCase= new RegExp('[A-Z]');
	var lowerCase= new RegExp('[a-z]');
	var numbers = new RegExp('[0-9]');
	
	if (!password.match(lowerCase))
	{
		document.getElementById("messagePassword").style.visibility = "visible";
	}
	else if (!password.match(upperCase))
	{
		document.getElementById("messagePassword").style.visibility = "visible";
	}
	else if (!password.match(numbers))
	{
		document.getElementById("messagePassword").style.visibility = "visible";		
	}
	else if (password.length < 8)
	{
		document.getElementById("messagePassword").style.visibility = "visible";
	}
	else
	{
		document.getElementById("messagePassword").style.visibility = "hidden";		
	}
}

function validatePassword2() {
	var password2 = document.getElementById("password2").value;
	var password = document.getElementById("password").value;
	
	if (password2 != password)
	{
		document.getElementById("messagePassword2").style.visibility = "visible";
	}
	else
	{
		document.getElementById("messagePassword2").style.visibility = "hidden";
	}
	
}

function validateName() {
	var name = document.getElementById("name").value;
	
	if (name.length == 0)
	{
		document.getElementById("messageName").style.visibility = "visible";
	}
	else
	{
		document.getElementById("messageName").style.visibility = "hidden";

	}
	
}

function validateContact() {
	var contact = document.getElementById("contact").value;
	var numbers = new RegExp('[0-9]');

	if (contact.length != 8 || !contact.match(numbers))
	{
		document.getElementById("messageContact").style.visibility = "visible";
	}
	else
	{
		document.getElementById("messageContact").style.visibility = "hidden";
	}
}

function validatePostal() {
	var postalCode = document.getElementById("address").value;
	var numbers = new RegExp('[0-9]');

	if (postalCode.length != 6 || !postalCode.match(numbers))
	{
		document.getElementById("messagePostal").style.visibility = "visible";
	}
	else
	{
		document.getElementById("messagePostal").style.visibility = "hidden";
	}
}

function validateUnit() {
	var unitNo = document.getElementById("unitno").value;
	
	if (unitNo.length == 0)
	{
		document.getElementById("messageUnit").style.visibility = "visible";
	}
	else
	{
		document.getElementById("messageUnit").style.visibility = "hidden";
	}
}

