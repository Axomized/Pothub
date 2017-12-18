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
	var addBtnDiv = document.getElementById("addBtnDiv");
	if (addBtnDiv.childElementCount < 2) {
		var otherInput = document.createElement("input");
		otherInput.setAttribute("type", "text");
		otherInput.placeholder = "Please enter a food"
		otherInput.style.marginRight = "10px";
		otherInput.style.width = "160px";
		
		var otherBtn = document.createElement("button");
		otherBtn.className = "editBtn";
		otherBtn.textContent = "Add";
		otherBtn.style.marginRight = "10px";
		otherBtn.onclick = function () {
			if(otherInput.value.length != 0 && otherInput.value.match(/^[a-zA-Z\s]*$/)) {
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
			else {
				otherInput.value = "";
			}
		}
		var doneBtn = document.createElement("button");
		doneBtn.className = "editBtn";
		doneBtn.textContent = "Done";
		doneBtn.onclick = function() {
			addBtnDiv.removeChild(otherInput);
			addBtnDiv.removeChild(otherBtn);
			addBtnDiv.removeChild(doneBtn);
			$("html, body").animate({ scrollTop: document.body.scrollHeight }, 1000);
		}
		addBtnDiv.appendChild(otherInput);
		addBtnDiv.appendChild(otherBtn);
		addBtnDiv.appendChild(doneBtn);
	}
}