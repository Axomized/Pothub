function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function toRemoveFoodPref() {
	location.href = "RemoveFoodPref.html";
}

function addOthers() {
	var otherInput = document.getElementById("otherInput");
	var otherBtn = document.getElementById("otherBtn");
	var doneBtn = document.getElementById("doneBtn");
	
	otherInput.style.display = "inline-block";
	otherBtn.style.display = "inline-block";
	doneBtn.style.display = "inline-block";
	
	otherInput.oninput = function() {
		if (otherInput.value.length > 0 && otherInput.value.match(/^[a-zA-Z\s]*$/)) {
			otherBtn.disabled = false;
			otherBtn.style.cursor = "pointer";
		}
		else {
			otherBtn.disabled = true;
			otherBtn.style.cursor = "not-allowed";
		}
	}
	otherBtn.onclick = function () {
		var otherFoodDiv = document.getElementById("otherFoodDiv");
		
		var otherDiv = document.createElement("div");
		otherDiv.className = "foodDiv col-sm-3";
		
		var foodLabel = document.createElement("label");
		foodLabel.className = "custom-control custom-checkbox foodLabel";
		
		var foodCheck = document.createElement("input");
		foodCheck.setAttribute("type", "checkbox");
		foodCheck.className = "custom-control-input";
		foodCheck.name = "foodChosen";
		foodCheck.value = otherInput.value;
		foodCheck.checked = true;
		
		var spanIndicator = document.createElement("span");
		spanIndicator.className = "custom-control-indicator";
		
		var spanDescriptor = document.createElement("span");
		spanDescriptor.className = "custom-control-description";
		spanDescriptor.textContent = otherInput.value;
		
		otherFoodDiv.appendChild(otherDiv);
		otherDiv.appendChild(foodLabel);
		foodLabel.appendChild(foodCheck);
		foodLabel.appendChild(spanIndicator);
		foodLabel.appendChild(spanDescriptor);
		
		otherInput.value = "";
	}
	doneBtn.onclick = function() {
		otherInput.style.display = "none";
		otherBtn.style.display = "none";
		doneBtn.style.display = "none";
		$("html, body").animate({ scrollTop: document.body.scrollHeight }, 1000);
	}
}