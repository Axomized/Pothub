function showBehalfName() {
	var x = document.getElementById("checkYes").checked;
	var behalfName = document.getElementById("behalfName");
	
	/**alert(x);**/
	
	if (x = true) {
		behalfName.style.display = "block";
	}
	else {
		behalfName.style.display = "none";
	}
}