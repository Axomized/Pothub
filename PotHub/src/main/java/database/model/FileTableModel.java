package database.model;

import java.sql.Timestamp;

public class FileTableModel {
	int fileID;
	String fileName;
	byte[] data; // Might set to InputStream
	Timestamp fileDate;
	double fileSize;

	public FileTableModel(int fileID, String fileName, byte[] data, Timestamp fileDate, double fileSize) {
		this.fileID = fileID;
		this.fileName = fileName;
		this.data = data;
		this.fileDate = fileDate;
		this.fileSize = fileSize;
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

	public Timestamp getFileDate() {
		return fileDate;
	}

	public double getFileSize() {
		return fileSize;
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

	public void setFileDate(Timestamp fileDate) {
		this.fileDate = fileDate;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
}
