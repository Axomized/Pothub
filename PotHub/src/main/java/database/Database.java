package database;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.*;

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
		ppstmt.setBoolean(5, bM.isPardoned());

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
		ppstmt.setInt(1, cM.getPostID());
		ppstmt.setDate(2, cM.getDate());
		ppstmt.setString(3, cM.getiGN());
		ppstmt.setInt(4, cM.getComment1());
		ppstmt.setString(5, cM.getDescription());

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
		ppstmt.setString(1, cVM.getiGN());
		ppstmt.setDate(2, cVM.getDate());

		executeUpdate(ppstmt);
	}
	
	//DonationModel
	public ArrayList<DonationModel> getDonationModel(String sqlline) throws SQLException {
		ArrayList<DonationModel> aldm = new ArrayList<DonationModel>();
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
			
			int donationID				= rs.getInt("DonationID");
			Date donation_Date			= rs.getDate("Donation_Date");
			BigDecimal donation_Amount	= rs.getBigDecimal("Donation_Amount");
			String onBehalf				= rs.getString("OnBehalf");
			
			aldm.add(new DonationModel(donationID, dUM, donation_Date, donation_Amount, onBehalf));
		}
		return aldm;
	}
	
	public void updateDonation(String sql, DonationModel dM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, dM.getiGN());
		ppstmt.setDate(2, dM.getDonation_Date());
		ppstmt.setBigDecimal(3, dM.getDonation_Amount());
		ppstmt.setString(4, dM.getOnBehalf());

		executeUpdate(ppstmt);
	}
	
	//EventModel
	public ArrayList<EventModel> getEventModel(String sqlline) throws SQLException {
		ArrayList<EventModel> alem = new ArrayList<EventModel>();
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
			
			int fileID			= rs.getInt("FileID");
			String fileName		= rs.getString("FileName");
			byte[] data			= rs.getBytes("Data");
			Date fileDate		= rs.getDate("FileDate");
			double fileSize		= rs.getDouble("FileSize");
			FileTableModel fTM = new FileTableModel(fileID, fileName, data, fileDate, fileSize);
			
			
			int eventID			= rs.getInt("EventID");
			String eventName	= rs.getString("EventName");
			String description	= rs.getString("Description");
			Date date			= rs.getDate("Date");
			String postalCode	= rs.getString("PostalCode");
			String venue		= rs.getString("Venue");
			int max_No_People	= rs.getInt("Max_No_People");
			String guest		= rs.getString("Guest");
			String fileList		= rs.getString("FileList");
			
			alem.add(new EventModel(eventID, eventName, dUM, fTM, description, date, postalCode, venue, max_No_People, guest, fileList));
		}
		return alem;
	}
	
	public void updateEvent(String sql, EventModel eM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, eM.getEventName());
		ppstmt.setString(2, eM.getiGN());
		ppstmt.setInt(3, eM.getThumbnail());
		ppstmt.setString(4, eM.getDescription());
		ppstmt.setDate(5, eM.getDate());
		ppstmt.setString(6, eM.getPostalCode());
		ppstmt.setString(7, eM.getVenue());
		ppstmt.setInt(8, eM.getMax_No_People());
		ppstmt.setString(9, eM.getGuest());
		ppstmt.setString(10, eM.getFileList());

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
	
	//FoodPreferences
	public ArrayList<FoodPreferences> getFoodPreferences(String sqlline) throws SQLException {
		ArrayList<FoodPreferences> alfp = new ArrayList<FoodPreferences>();
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
			
			String foodPref = rs.getString("FoodPref");
			
			alfp.add(new FoodPreferences(dUM, foodPref));
		}
		return alfp;
	}
	
	public void updateFoodPreferences(String sql, FoodPreferences fP) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, fP.getiGN());
		ppstmt.setString(2, fP.getFoodPref());

		executeUpdate(ppstmt);
	}
	
	//ForumPostModel
	public ArrayList<ForumPostModel> getForumPostModel(String sqlline) throws SQLException {
		ArrayList<ForumPostModel> alfpm = new ArrayList<ForumPostModel>();
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
			
			int fileID			= rs.getInt("FileID");
			String fileName		= rs.getString("FileName");
			byte[] data			= rs.getBytes("Data");
			Date fileDate		= rs.getDate("FileDate");
			double fileSize		= rs.getDouble("FileSize");
			FileTableModel fTM = new FileTableModel(fileID, fileName, data, fileDate, fileSize);
			
			int postID				= rs.getInt("PostID");
			String thread			= rs.getString("Thread");
			int upvotes				= rs.getInt("Upvotes");
			Date date				= rs.getDate("Date");
			String description		= rs.getString("Description");
			String fileAttachment	= rs.getString("FileAttachment");
			
			alfpm.add(new ForumPostModel(postID, thread, upvotes, dUM, date, fTM, description, fileAttachment));
		}
		return alfpm;
	}
	
	public void updateForumPost(String sql, ForumPostModel fP) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, fP.getThread());
		ppstmt.setInt(2, fP.getUpvotes());
		ppstmt.setString(3, fP.getiGN());
		ppstmt.setDate(4, fP.getDate());
		ppstmt.setInt(5, fP.getPicture());
		ppstmt.setString(6, fP.getDescription());
		ppstmt.setString(7, fP.getFileAttachment());

		executeUpdate(ppstmt);
	}
	
	//ForumVoteModel
	public ArrayList<ForumVoteModel> getForumVoteModel(String sqlline) throws SQLException {
		return null;
	}
	
	public void updateForumVote(String sql, ForumVoteModel fP) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setInt(1, fP.getPostID());
		ppstmt.setString(2, fP.getiGN());
		ppstmt.setDate(3, fP.getDate());

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
