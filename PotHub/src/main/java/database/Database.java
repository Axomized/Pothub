package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.model.DatabaseUserModel;
import database.model.FileTableModel;
import database.model.ShoppingLoginModel;

public class Database {
	final String DB_URL = "jdbc:sqlserver://localhost:3306;databaseName=PotHub;";
	//final String DB_URL="jdbc:sqlserver://119.74.135.44:3306;databaseName=PotHub;";

	Connection conn = null;

	public Database(int permission) throws SQLException, FileNotFoundException {
		EncryptionTesting et = new EncryptionTesting();
		ArrayList<ShoppingLoginModel> loginModelArray = et.getArray();
		switch (permission) {
		case 0: //Read
			conn = DriverManager.getConnection(DB_URL, loginModelArray.get(0).getLogin(),
					loginModelArray.get(0).getPassword());
			break;
		case 1: //Write
			conn = DriverManager.getConnection(DB_URL, loginModelArray.get(1).getLogin(),
					loginModelArray.get(1).getPassword());
			break;
		case 2: //Read&Write
			conn = DriverManager.getConnection(DB_URL, loginModelArray.get(2).getLogin(),
					loginModelArray.get(2).getPassword());
			break;
		}
	}

	// public ArrayList<String>
	@SuppressWarnings("unused")
	private ResultSet getResultSet(String sqlline) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sqlline);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public void updateDatabaseUser(String sql, DatabaseUserModel dUM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, dUM.getEmail());
		

		executeUpdate(ppstmt);
	}

	private void updateFileTable(String sql, FileTableModel fTM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, fTM.getFileName());
		ppstmt.setBytes(2, fTM.getData());
		ppstmt.setDate(3, new Date(fTM.getFileDate().getTime()));
		ppstmt.setFloat(4, (float)fTM.getFileSize());

		executeUpdate(ppstmt);
	}
	
	private void executeUpdate(PreparedStatement ppstmt) throws SQLException {
		int count = ppstmt.executeUpdate();
		if (count == 0) {
			System.out.println("!!! Update failed !!!\n");
		} else {
			System.out.println("!!! Update successful !!!\n");
		}
	}
	
	public void insertFileToFileTable(File file) throws FileNotFoundException, SQLException {
		String fileName = file.getName();
		byte[] dataByte = new byte[(int)file.length()];
		Timestamp fileDate = new Timestamp(file.lastModified());
		float fileSize = file.length();
		FileTableModel fTM = new FileTableModel(0, fileName, dataByte, fileDate, fileSize);
		
		String sql = "INSERT INTO FileTable(FileName, Data, FileDate, FileSize) VALUES (?,?,?,?);";
		updateFileTable(sql, fTM);
	}

	/*
	 * private void getFileAttachment(){ Database db = new Database(0);
	 * PreparedStatement ps =
	 * db.conn.prepareStatement("SELECT * FROM FileTable"); ResultSet rs =
	 * ps.executeQuery(); rs.next();
	 * 
	 * InputStream c = rs.getBinaryStream(2); OutputStream outputStream = new
	 * FileOutputStream("D:/hello.jpg"); int read = 0; byte[] bytes = new
	 * byte[1024]; while((read = c.read(bytes)) != -1) {
	 * outputStream.write(bytes, 0, read); }
	 * 
	 * c.close(); outputStream.close(); db.conn.close(); }
	 */
}
