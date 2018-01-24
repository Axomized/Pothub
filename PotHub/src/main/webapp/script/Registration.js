function validateEmail() {
	var email = document.getElementById("email").value;
    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length || email == "") 
    {
		document.getElementById("messageEmail").style.visibility = "visible";
	}
	else 
	{
		document.getElementById("messageEmail").style.visibility = "hidden";
	}

}

function validatePassword() {
//	var password = document.getElementById("password").value;
//	
//	if (values.password.length < 8 || !password.match("^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"))
//	{
//		document.getElementById("messagePassword").style.visibility = "visible";
//	}
//	else
//	{
//		document.getElementById("messagePassword").style.visibility = "hidden";		
//	}
}

