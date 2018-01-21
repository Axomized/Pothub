function validateEmail(){
	var email = document.getElementById("email").value;
	if (email == "")
	{
		var paragraph = document.getElementById("messageEmail");
		var text = document.createTextNode("Please enter your email.");
		paragraph.appendChild(text);
	
	}
	
	else if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(.[a-zA-Z]{2,})$"))
	{
		var paragraph = document.getElementById("messageEmail");
		var text = document.createTextNode("Please enter a valid email.");
		paragraph.appendChild(text);
	}
	else 
	{
		document.getElementById("messageEmail").style.visibility = "hidden";
	}
	
}

