package database.model;

public class ImageTableModel {
	int imageID;
	String imageName;
	byte[] imageData;
	int inUse;
	
	public ImageTableModel(int imageID, String imageName, byte[] imageData, int inUse) {
		this.imageID = imageID;
		this.imageName = imageName;
		this.imageData = imageData;
		this.inUse = inUse;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public int getInUse() {
		return inUse;
	}

	public void setInUse(int inUse) {
		this.inUse = inUse;
	}
}
