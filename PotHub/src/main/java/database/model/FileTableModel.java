package database.model;

public class FileTableModel {
	int fileID;
	String fileName;
	byte[] data; // Might set to InputStream

	public FileTableModel() {
	}
	
	public FileTableModel(int fileID, String fileName, byte[] data) {
		this.fileID = fileID;
		this.fileName = fileName;
		this.data = data;
	}

	public int getFileID() {
		return fileID;
	}

	public String getFileName() {
		return fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
