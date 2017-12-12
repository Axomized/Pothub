package database.model;

import java.sql.Date;

public class FileTableModel {
	int fileID;
	String fileName;
	byte[] data; // Might set to InputStream
	Date fileDate;
	double fileSize;

	public FileTableModel(int fileID, String fileName, byte[] data, Date fileDate, double fileSize) {
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

	public Date getFileDate() {
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

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
}
