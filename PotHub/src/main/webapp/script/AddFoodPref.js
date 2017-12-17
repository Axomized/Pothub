function showProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "block";
}

function hideProfileDropdown() {
	document.getElementById("profileDropdownDiv").style.display = "none";
}

function addOthers() {
	var addBtnDiv = document.getElementById("addBtnDiv");
	if (addBtnDiv.childElementCount < 2) {
		var otherInput = document.createElement("input");
		otherInput.setAttribute("type", "text");
		otherInput.placeholder = "Please enter a food"
		otherInput.style.marginLeft = "5px";
		otherInput.style.marginRight = "5px";
		
		addBtnDiv.appendChild(otherInput);
		
		var otherBtn = document.createElement("button");
		otherBtn.className = "editBtn";
		otherBtn.textContent = "Add";
		otherBtn.onclick = function () {
			if(otherInput.value.length != 0) {
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
		}
		addBtnDiv.appendChild(otherBtn);
	}
}