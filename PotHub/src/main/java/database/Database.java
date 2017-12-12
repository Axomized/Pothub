package database;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.model.AppealModel;
import database.model.BansModel;
import database.model.CommentModel;
import database.model.CommentVoteModel;
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

	private ResultSet getResultSet(String sqlline) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sqlline);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	private void executeUpdate(PreparedStatement ppstmt) throws SQLException {
		int count = ppstmt.executeUpdate();
		if (count == 0) {
			System.out.println("!!! Update failed !!!\n");
		} else {
			System.out.println("!!! Update successful !!!\n");
		}
	}

	//DatabaseUser
	public ArrayList<DatabaseUserModel> getDatabaseUser(String sqlline) throws SQLException {
		ArrayList<DatabaseUserModel> aldum = new ArrayList<DatabaseUserModel>();
		ResultSet rs = getResultSet(sqlline);
		while(rs.next()) {
			String email 				= rs.getString("Email");
			String iGN					= rs.getString("IGN");
			String contact_No			= rs.getString("Contact_No");
			char gender					= rs.getString("Gender").charAt(0);
			String bio					= rs.getString("Bio");
			String address				= rs.getString("Address");
			String unitNo				= rs.getString("UnitNo");
			int profilePic				= rs.getInt("ProfilePic");
			Date lastLogin				= rs.getDate("LastLogin");
			Date joinDate				= rs.getDate("JoinDate");
			int cookingRank				= rs.getInt("CookingRank");
			int points					= rs.getInt("Points");
			BigDecimal totalDonation	= rs.getBigDecimal("TotalDonation");
			boolean isPriviledged		= rs.getBoolean("IsPriviledged");
			aldum.add(new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged));
		}
		return aldum;
	}
	
	public void updateDatabaseUser(String sql, DatabaseUserModel dUM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, dUM.getEmail());
		ppstmt.setString(2, dUM.getiGN());
		ppstmt.setString(3, dUM.getContact_No());
		ppstmt.setString(4, String.valueOf(dUM.getGender()));
		ppstmt.setString(5, dUM.getBio());
		ppstmt.setString(6, dUM.getaddress());
		ppstmt.setString(7, dUM.getUnitNo());
		ppstmt.setInt(8, dUM.getProfilePic());
		ppstmt.setDate(9, new Date(dUM.getLastLogin().getTime()));
		ppstmt.setDate(10, new Date(dUM.getJoinDate().getTime()));
		ppstmt.setInt(11, dUM.getCookingRank());
		ppstmt.setInt(12, dUM.getPoints());
		ppstmt.setBigDecimal(13, dUM.getTotalDonation());
		ppstmt.setBoolean(14, dUM.isPriviledged());

		executeUpdate(ppstmt);
	}
	
	//Appeal
	public ArrayList<AppealModel> getAppealModel(String sqlline) throws SQLException {
		ArrayList<AppealModel> alam = new ArrayList<AppealModel>();
		ResultSet rs = getResultSet(sqlline);
		while(rs.next()) {
			String email 				= rs.getString("Email");
			String iGN					= rs.getString("IGN");
			String contact_No			= rs.getString("Contact_No");
			char gender					= rs.getString("Gender").charAt(0);
			String bio					= rs.getString("Bio");
			String address				= rs.getString("Address");
			String unitNo				= rs.getString("UnitNo");
			int profilePic				= rs.getInt("ProfilePic");
			Date lastLogin				= rs.getDate("LastLogin");
			Date joinDate				= rs.getDate("JoinDate");
			int cookingRank				= rs.getInt("CookingRank");
			int points					= rs.getInt("Points");
			BigDecimal totalDonation	= rs.getBigDecimal("TotalDonation");
			boolean isPriviledged		= rs.getBoolean("IsPriviledged");
			DatabaseUserModel dUM = new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged);
			
			Date receiveDate	= rs.getDate("ReceiveDate");
			String message		= rs.getString("Message");
			boolean approval	= rs.getBoolean("Approval");
			Date dateApproved	= rs.getDate("DateApproved");
			alam.add(new AppealModel(dUM, receiveDate, message, approval, dateApproved));
		}
		return alam;
	}
	
	public void updateAppeal(String sql, AppealModel aM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setDate(1, aM.getReceiveDate());
		ppstmt.setString(2, aM.getMessage());
		ppstmt.setBoolean(3, aM.isApproval());
		ppstmt.setDate(4, aM.getDateApproved());

		executeUpdate(ppstmt);
	}
	
	//Bans
	public ArrayList<BansModel> getBansModel(String sqlline) throws SQLException {
		ArrayList<BansModel> albm = new ArrayList<BansModel>();
		ResultSet rs = getResultSet(sqlline);
		while(rs.next()) {
			String email 				= rs.getString("Email");
			String iGN					= rs.getString("IGN");
			String contact_No			= rs.getString("Contact_No");
			char gender					= rs.getString("Gender").charAt(0);
			String bio					= rs.getString("Bio");
			String address				= rs.getString("Address");
			String unitNo				= rs.getString("UnitNo");
			int profilePic				= rs.getInt("ProfilePic");
			Date lastLogin				= rs.getDate("LastLogin");
			Date joinDate				= rs.getDate("JoinDate");
			int cookingRank				= rs.getInt("CookingRank");
			int points					= rs.getInt("Points");
			BigDecimal totalDonation	= rs.getBigDecimal("TotalDonation");
			boolean isPriviledged		= rs.getBoolean("IsPriviledged");
			DatabaseUserModel dUM = new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged);
			
			Date startDate		= rs.getDate("StartDate");
			Date endDate		= rs.getDate("EndDate");
			String reason		= rs.getString("Reason");
			String admin		= rs.getString("Admin");
			boolean pardoned 	= rs.getBoolean("Pardoned");
			albm.add(new BansModel(dUM, startDate, endDate, reason, admin, pardoned));
		}
		return albm;
	}
	
	public void updateBans(String sql, BansModel bM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setDate(1, bM.getStartDate());
		ppstmt.setDate(2, bM.getEndDate());
		ppstmt.setString(3, bM.getReason());
		ppstmt.setString(4, bM.getAdmin());
		ppstmt.setBoolean(3, bM.isPardoned());

		executeUpdate(ppstmt);
	}
	
	//CommentModel
	public ArrayList<CommentModel> getCommentModel(String sqlline) throws SQLException {
		ArrayList<CommentModel> alcm = new ArrayList<CommentModel>();
		ResultSet rs = getResultSet(sqlline);
		while(rs.next()) {
			String email 				= rs.getString("Email");
			String iGN					= rs.getString("IGN");
			String contact_No			= rs.getString("Contact_No");
			char gender					= rs.getString("Gender").charAt(0);
			String bio					= rs.getString("Bio");
			String address				= rs.getString("Address");
			String unitNo				= rs.getString("UnitNo");
			int profilePic				= rs.getInt("ProfilePic");
			Date lastLogin				= rs.getDate("LastLogin");
			Date joinDate				= rs.getDate("JoinDate");
			int cookingRank				= rs.getInt("CookingRank");
			int points					= rs.getInt("Points");
			BigDecimal totalDonation	= rs.getBigDecimal("TotalDonation");
			boolean isPriviledged		= rs.getBoolean("IsPriviledged");
			DatabaseUserModel dUM = new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged);
			
			int commentID		= rs.getInt("CommentID");
			int postID			= rs.getInt("PostID");
			Date date			= rs.getDate("Date");
			int comment1 		= rs.getInt("Comment1");
			String description 	= rs.getString("Description");
			alcm.add(new CommentModel(commentID, postID, date, dUM, comment1, description));
		}
		return alcm;
	}
	
	public void updateComment(String sql, CommentModel cM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setInt(1, cM.getCommentID());
		ppstmt.setInt(2, cM.getPostID());
		ppstmt.setDate(3, cM.getDate());
		ppstmt.setString(4, cM.getiGN());
		ppstmt.setInt(5, cM.getComment1());
		ppstmt.setString(6, cM.getDescription());

		executeUpdate(ppstmt);
	}
	
	//CommentVoteModel
	public ArrayList<CommentVoteModel> getCommentVoteModel(String sqlline) throws SQLException {
		ArrayList<CommentVoteModel> alcvm = new ArrayList<CommentVoteModel>();
		ResultSet rs = getResultSet(sqlline);
		while(rs.next()) {
			String email 				= rs.getString("Email");
			String iGN					= rs.getString("IGN");
			String contact_No			= rs.getString("Contact_No");
			char gender					= rs.getString("Gender").charAt(0);
			String bio					= rs.getString("Bio");
			String address				= rs.getString("Address");
			String unitNo				= rs.getString("UnitNo");
			int profilePic				= rs.getInt("ProfilePic");
			Date lastLogin				= rs.getDate("LastLogin");
			Date joinDate				= rs.getDate("JoinDate");
			int cookingRank				= rs.getInt("CookingRank");
			int points					= rs.getInt("Points");
			BigDecimal totalDonation	= rs.getBigDecimal("TotalDonation");
			boolean isPriviledged		= rs.getBoolean("IsPriviledged");
			DatabaseUserModel dUM = new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged);
			
			int commentID 	= rs.getInt("CommentID");
			Date date		= rs.getDate("Date");
			
			alcvm.add(new CommentVoteModel(commentID, dUM, date));
		}
		return alcvm;
	}
	
	public void updateCommentVote(String sql, CommentVoteModel cVM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setInt(1, cVM.getCommentID());
		ppstmt.setString(2, cVM.getiGN());
		ppstmt.setDate(3, cVM.getDate());

		executeUpdate(ppstmt);
	}
	
	//FileTable
	public ArrayList<FileTableModel> getFileTableModel(String sqlline) throws SQLException {
		ArrayList<FileTableModel> alftm = new ArrayList<FileTableModel>();
		ResultSet rs = getResultSet(sqlline);
		while(rs.next()) {
			int fileID			= rs.getInt("FileID");
			String fileName		= rs.getString("FileName");
			byte[] data			= rs.getBytes("Data");
			Date fileDate		= rs.getDate("FileDate");
			double fileSize		= rs.getDouble("FileSize");
			alftm.add(new FileTableModel(fileID, fileName, data, fileDate, fileSize));
		}
		return alftm;
	}
	
	public void updateFileTable(String sql, FileTableModel fTM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, fTM.getFileName());
		ppstmt.setBytes(2, fTM.getData());
		ppstmt.setDate(3, new Date(fTM.getFileDate().getTime()));
		ppstmt.setFloat(4, (float)fTM.getFileSize());

		executeUpdate(ppstmt);
	}
	
	/*
	public static void main(String[] arg0) throws SQLException, FileNotFoundException {
		Database db = new Database(0);
		String sqlline = "SELECT * FROM CommentVote INNER JOIN DatabaseUser ON DatabaseUser.IGN = CommentVote.IGN;";
		ArrayList<CommentVoteModel> alam = db.getCommentVoteModel(sqlline);
		for(CommentVoteModel dum:alam)
			System.out.println(dum.getiGN());
	}
	*/
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
