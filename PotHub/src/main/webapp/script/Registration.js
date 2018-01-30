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

function hasLowerCase(str) {
	if(str.toUpperCase != str){
		return true;
	}
	else
		return false;
}

function validatePassword() {
	var password = document.getElementById("password").value;
	
	if (password.length<8)
	{
		document.getElementById("messagePassword").style.visibility = "visible";
	}
	else
	{
		document.getElementById("messagePassword").style.visibility = "hidden";		
	}
}

