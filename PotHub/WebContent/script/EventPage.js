function showOverlay(imageGradient){
	var imageGradientParent = imageGradient.parentElement;
	imageGradient.style.display = "none";
	var overlay = imageGradientParent.getElementsByTagName('div')[3];
	overlay.style.display = "block";
}

function hideOverlay(overlay){
	var overlayParent = overlay.parentElement;
	overlay.style.display = "none";
	var imageGradient = overlayParent.getElementsByTagName('div')[1];
	imageGradient.style.display = "block";
}