function SaveSignUpInput(){
	var name = document.getElementById("name").value;
	var email = document.getElementById("email").value;
	var contact = document.getElementById("contact").value;
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	var address = document.getElementById("address").value;
	alert(name + email + contact + password + password2 + address);
	location.href = ''
}