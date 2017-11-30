function checkInputsFilled() {
	var nameInput = document.getElementById("nameInput").value;
	var oldPassInput = document.getElementById("oldPassInput").value;
	var newPassInput = document.getElementById("newPassInput").value;
	var confirmPassInput = document.getElementById("confirmPassInput").value;
	var emailInput = document.getElementById("emailInput").value;
	var bioText = document.getElementById("bioText").value;
	var x = document.getElementById("updateBtn").disabled;
	
	if (nameInput == null || nameInput == "" && oldPassInput == null || oldPassInput == "" && newPassInput == null || newPassInput == "" && 
		confirmPassInput == null || confirmPassInput == "" && emailInput == null || emailInput == "" && bioText == null || bioText == "") {
		alert("No changes were made.");
	}
}