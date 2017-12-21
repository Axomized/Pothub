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

import adminSearch.BansSearchObject;
import database.model.*;

public class Database {
	//final String DB_URL = "jdbc:sqlserver://localhost:3306;databaseName=PotHub;";
	final String DB_URL="jdbc:sqlserver://119.74.135.44:3306;databaseName=PotHub;";

	Connection conn = null;

	public Database(int permission) throws SQLException, FileNotFoundException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
	public ArrayList<DatabaseUserModel> getDatabaseUser() throws SQLException {
		ArrayList<DatabaseUserModel> aldum = new ArrayList<DatabaseUserModel>();
		ResultSet rs = getResultSet("SELECT * FROM DatabaseUser");
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
			int userPermission			= rs.getInt("UserPermission");
			aldum.add(new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged, userPermission));
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
		ppstmt.setString(6, dUM.getAddress());
		ppstmt.setString(7, dUM.getUnitNo());
		ppstmt.setInt(8, dUM.getProfilePic());
		ppstmt.setDate(9, new Date(dUM.getLastLogin().getTime()));
		ppstmt.setDate(10, new Date(dUM.getJoinDate().getTime()));
		ppstmt.setInt(11, dUM.getCookingRank());
		ppstmt.setInt(12, dUM.getPoints());
		ppstmt.setBigDecimal(13, dUM.getTotalDonation());
		ppstmt.setBoolean(14, dUM.isPriviledged());
		ppstmt.setInt(15, dUM.getUserPermission());

		executeUpdate(ppstmt);
	}
	
	//Appeal
	public ArrayList<AppealModel> getAppeal() throws SQLException{
		ArrayList<AppealModel> appeals = new ArrayList<AppealModel>();
		ResultSet rs = getResultSet("SELECT * FROM Bans LEFT OUTER JOIN Appeal ON Bans.IGN = Appeal.IGN;");
		while(rs.next()) {
			int appealID				= rs.getInt("appealID");
			String iGN					= rs.getString("IGN");
			Date receiveDate			= rs.getDate("receiveDate");
			String message				= rs.getString("message");
			boolean approval			= rs.getBoolean("approval");
			Date dateApproved			= rs.getDate("dateApproved");
			
			appeals.add(new AppealModel(appealID,iGN, receiveDate, message, approval, dateApproved));
		}
		return appeals;
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
	public void updateBans(String sql, BansModel bM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setDate(1, bM.getStartDate());
		ppstmt.setDate(2, bM.getEndDate());
		ppstmt.setString(3, bM.getReason());
		ppstmt.setString(4, bM.getAdmin());
		ppstmt.setBoolean(5, bM.isPardoned());

		executeUpdate(ppstmt);
	}
	
	public ArrayList<BansModel> getBansModel() throws SQLException {
		ArrayList<BansModel> bannedppl = new ArrayList<BansModel>();
		BansSearchObject bso = new BansSearchObject();
		
		ResultSet rs = getResultSet(bso.getExecutableSQL());
		while(rs.next()) {
			String iGN					= rs.getString("IGN");
			Date startDate				= rs.getDate("startDate");
			Date endDate				= rs.getDate("endDate");
			String reason				= rs.getString("reason");
			String admin				= rs.getString("admin");
			boolean pardoned			= rs.getBoolean("pardoned");
			bannedppl.add(new BansModel(iGN, startDate, endDate, reason, admin, pardoned));
		}
		return bannedppl;
	}
	
	//Report
	public ArrayList<ReportModel> getManyReports() throws SQLException{
			ArrayList<ReportModel> reports = new ArrayList<ReportModel>();
			ResultSet rs = getResultSet(""
					+ "SELECT reportID, IGNSend, IGNReceive, evidenceType, Date, Evidence, reason, guiltyOrNot "
					+ "FROM Report;");
			while(rs.next()) {
				int reportID = 				rs.getInt("reportID");
				String iGNSend = 			rs.getString("IGNSend");
				String iGNReceive = 		rs.getString("IGNReceive");
				String evidenceType = 		rs.getString("evidenceType");
				Date date =					rs.getDate("Date");
				int evidence = 				rs.getInt("Evidence");
				String reason = 			rs.getString("reason");
				boolean guiltyOrNot = 		rs.getBoolean("guiltyOrNot");
				
				reports.add(new ReportModel(reportID, iGNSend, iGNReceive, evidenceType, date, evidence,
						reason, guiltyOrNot));
			}
			return reports;
	}
	
	//CommentModel
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
	public void updateCommentVote(String sql, CommentVoteModel cVM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, cVM.getiGN());
		ppstmt.setDate(2, cVM.getDate());

		executeUpdate(ppstmt);
	}
	
	//DonationModel
	public void updateDonation(String sql, DonationModel dM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, dM.getiGN());
		ppstmt.setDate(2, dM.getDonation_Date());
		ppstmt.setBigDecimal(3, dM.getDonation_Amount());
		ppstmt.setString(4, dM.getOnBehalf());

		executeUpdate(ppstmt);
	}
	
	public ArrayList<DonationModel> getDonationModel() throws SQLException{
		ArrayList<DonationModel> donations = new ArrayList<DonationModel>();
		ResultSet rs = getResultSet("SELECT donationID, IGN, donation_date, donation_amount, onBehalf FROM Donation;");
		while(rs.next()) {
			int donationID				= rs.getInt("DonationID");
			String iGN					= rs.getString("IGN");
			Date donationDate			= rs.getDate("donation_Date");
			BigDecimal donationAmount	= rs.getBigDecimal("donation_amount");
			String onBehalf				= rs.getString("onBehalf");
			
			donations.add(new DonationModel(donationID, iGN, donationDate, donationAmount, onBehalf));
		}
		return donations;
	}
	
	//EventModel
	public ArrayList<EventModel> getEventModelForEventPage() throws SQLException {
		ArrayList<EventModel> alem = new ArrayList<EventModel>();
		ResultSet rs = getResultSet("SELECT EventName, IGN, Thumbnail, Description, Date, PostalCode, Venue, Guest FROM Event;");
		while(rs.next()) {
			String eventName	= rs.getString("EventName");
			String iGN 			= rs.getString("IGN");
			int thumbnail		= rs.getInt("Thumbnail");
			String description	= rs.getString("Description");
			Date date			= rs.getDate("Date");
			String postalCode	= rs.getString("PostalCode");
			String venue		= rs.getString("Venue");
			String guest		= rs.getString("Guest");
			
			alem.add(new EventModel(0, eventName, iGN, thumbnail, description, date, postalCode, venue, 0, guest, null));
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
	public void updateFileTable(String sql, FileTableModel fTM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, fTM.getFileName());
		ppstmt.setBytes(2, fTM.getData());
		ppstmt.setDate(3, new Date(fTM.getFileDate().getTime()));
		ppstmt.setFloat(4, (float)fTM.getFileSize());

		executeUpdate(ppstmt);
	}
	
	//FoodPreferences
	public void updateFoodPreferences(String sql, FoodPreferences fP) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, fP.getiGN());
		ppstmt.setString(2, fP.getFoodPref());

		executeUpdate(ppstmt);
	}
	
	//ForumPostModel
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
