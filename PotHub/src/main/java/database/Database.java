package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.compress.utils.IOUtils;

import adminSearch.BansSearchObject;
import adminSearch.DonationSearchObject;
import adminSearch.RankSearchObject;
import adminSearch.ReportSearchObject;
import database.model.AppealModel;
import database.model.BansModel;
import database.model.CommentModel;
import database.model.CommentVoteModel;
import database.model.DatabaseUserModel;
import database.model.DonationModel;
import database.model.EventModel;
import database.model.FileTableModel;
import database.model.FoodPreferences;
import database.model.ForumPostModel;
import database.model.ForumVoteModel;
import database.model.LogsModel;
import database.model.PeopleEventListModel;
import database.model.ReportModel;
import database.model.ShoppingLoginModel;

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
	
	//Get list of User's IGN
	public ArrayList<String> getDatabaseUserIGN() throws SQLException {
		ArrayList<String> aldum = new ArrayList<String>();
		ResultSet rs = getResultSet("SELECT IGN FROM DatabaseUser");
		while(rs.next()) {
			String iGN					= rs.getString("IGN");
			aldum.add(iGN);
		}
		return aldum;
	}
	
	//For profile page - user's profile information
	public DatabaseUserModel getUserProfile(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Email, IGN, Contact_No, Gender, Bio, Address, UnitNo, ProfilePic, JoinDate, CookingRank, Points, TotalDonation, IsPriviledged FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String email 				= rs.getString("Email");
			String iGN					= rs.getString("IGN");
			String contact_No			= rs.getString("Contact_No");
			char gender					= rs.getString("Gender").charAt(0);
			String bio					= rs.getString("Bio");
			String address				= rs.getString("Address");
			String unitNo				= rs.getString("UnitNo");
			int profilePic				= rs.getInt("ProfilePic");
			Date joinDate				= rs.getDate("JoinDate");
			int cookingRank				= rs.getInt("CookingRank");
			int points					= rs.getInt("Points");
			BigDecimal totalDonation	= rs.getBigDecimal("TotalDonation");
			boolean isPriviledged		= rs.getBoolean("IsPriviledged");
			return new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, joinDate, cookingRank, points, totalDonation, isPriviledged);
		}
		return null;
	}
	
	//For profile page - user's donation history
	public DonationModel getUserDonation(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT DonationDate, DonationAmount, OnBehalf FROM Donation WHERE IGN = ?;");
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			Date donationDate			= rs.getDate("donation_Date");
			BigDecimal donationAmount	= rs.getBigDecimal("donation_amount");
			String onBehalf				= rs.getString("onBehalf");
			return new DonationModel(donationDate, donationAmount, onBehalf);
		}
		return null;
	}
	
	//For donation page
	public void insertDonation(DonationModel dm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Donation(IGN, DonationDate, DonationAmount, OnBehalf) VALUES(?,?,?,?);");
		ppstmt.setString(1, dm.getiGN());
		ppstmt.setDate(2, dm.getDonation_Date());
		ppstmt.setBigDecimal(3, dm.getDonation_Amount());
		ppstmt.setString(4, dm.getOnBehalf());
		ppstmt.executeUpdate();
	}
	
	//For logs page
	public ArrayList<LogsModel> getLogs() throws SQLException {
		ArrayList<LogsModel> logsList = new ArrayList<LogsModel>();
		PreparedStatement ppstmt = conn.prepareStatement("SELECT * FROM Logs;");
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			int logID 				= rs.getInt("LogID");
			String iGN 				= rs.getString("IGN");
			Date logDate		 	= rs.getDate("LogDate");
			String iPAddress 		= rs.getString("IPAddress");
			String logType 			= rs.getString("LogType");
			String logActivity 		= rs.getString("LogActivity");
			boolean isSuspicious 	= rs.getBoolean("IsSuspicious");
			logsList.add(new LogsModel(logID, iGN, logDate, iPAddress, logType, logActivity, isSuspicious));
		}
		return logsList;
	}
	
	//For logs page
	public void insertLogs(LogsModel lm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Logs(IGN, LogDate, IPAddress, LogType, LogActivity, IsSuspcious) VALUES(?,?,?,?,?,?);");
		ppstmt.setString(1, lm.getiGN());
		ppstmt.setDate(2, lm.getLogDate());
		ppstmt.setString(3, lm.getiPAddress());
		ppstmt.setString(4, lm.getLogType());
		ppstmt.setString(5, lm.getLogActivity());
		ppstmt.setBoolean(6, lm.isSuspicious());
		ppstmt.executeUpdate();
	}
	
	//For event page
	public DatabaseUserModel getProfileForEvent(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT ProfilePic, CookingRank, Points FROM DatabaseUser WHERE IGN =?");
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			int profilePic				= rs.getInt("ProfilePic");
			int cookingRank				= rs.getInt("CookingRank");
			int points					= rs.getInt("Points");
			
			return new DatabaseUserModel(profilePic, cookingRank, points);
		}
		return null;
		
	}
	
	public ArrayList<DatabaseUserModel> getDatabaseUserRanks() throws SQLException {
		ArrayList<DatabaseUserModel> aldum = new ArrayList<DatabaseUserModel>();
		RankSearchObject rso = new RankSearchObject();
		ResultSet rs = getResultSet(rso.getExecutableSQL());
		while(rs.next()) {
			String email 				= null;
			String iGN					= rs.getString("IGN");
			String contact_No			= null;
			char gender					= ' ';
			String bio					= null;
			String address				= null;
			String unitNo				= null;
			int profilePic				= 0;
			Date lastLogin				= null;
			Date joinDate				= rs.getDate("JoinDate");
			int cookingRank				= 0;
			int points					= 0;
			BigDecimal totalDonation	= BigDecimal.valueOf(0);
			boolean isPriviledged		= false;
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
			ReportSearchObject rso = new ReportSearchObject();
			ResultSet rs = getResultSet(rso.getExecutableSQL());
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
		DonationSearchObject dm = new DonationSearchObject();
		ResultSet rs = getResultSet(dm.getExecutableSQL());
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
	public ArrayList<EventModel> getEventModelForEventPage() throws SQLException, UnsupportedEncodingException {
		ArrayList<EventModel> alem = new ArrayList<EventModel>();
		ResultSet rs = getResultSet("SELECT EventID, EventName, IGN, Thumbnail, Description, Date, PostalCode, Venue, Max_No_People, Guest FROM Event;");
		while(rs.next()) {
			int eventID		= rs.getInt("EventID");
			String eventName	= rs.getString("EventName");
			String iGN 			= rs.getString("IGN");
			int thumbnail		= rs.getInt("Thumbnail");
			String description	= rs.getString("Description");
			Timestamp date		= rs.getTimestamp("Date");
			String postalCode	= rs.getString("PostalCode");
			String venue		= rs.getString("Venue");
			int max_No_People	= rs.getInt("Max_No_People");
			String guest		= rs.getString("Guest");
			
			alem.add(new EventModel(eventID, eventName, iGN, thumbnail, description, date, postalCode, venue, true, max_No_People, guest, null));
		}
		return alem;
	}
	
	//For EventofEventPage
	public EventModel getEventofEventPage(String nameOfEvent) throws SQLException, UnsupportedEncodingException {
		PreparedStatement ps = conn.prepareStatement("SELECT EventName, Thumbnail, Description, Date, PostalCode, Venue, Guest, FileList FROM Event WHERE EventName = ?;");
		ps.setString(1, nameOfEvent);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String eventName	= rs.getString("EventName");
			int thumbnail		= rs.getInt("Thumbnail");
			String description	= rs.getString("Description");
			Timestamp date		= rs.getTimestamp("Date");
			String postalCode	= rs.getString("PostalCode");
			String venue		= rs.getString("Venue");
			String guest		= rs.getString("Guest");
			String fileList		= rs.getString("FileList");
			
			return new EventModel(0, eventName, null, thumbnail, description, date, postalCode, venue, true, 0, guest, fileList);
		}
		return null;
	}
		
	//Get guest's profile picture
	public String getUserProfilePic(String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT ProfilePic FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, iGN);
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			int profilePic = rs.getInt("ProfilePic");
			if(profilePic == 0) {
				break;
			}
			PreparedStatement ppstmt2 = conn.prepareStatement("SELECT FileName FROM FileTable WHERE FileID = ?;");
			ppstmt2.setInt(1, profilePic);
			ResultSet rs2 = ppstmt2.executeQuery();
			while(rs2.next()) {
				String fileName = rs2.getString("FileName");
				
				return fileName;
			}
		}
		return null;
	}
	
	//CreateEvent
	public void insertCreateEvent(EventModel eM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Event(EventName, IGN, Thumbnail, Description, Date, PostalCode, Venue, AutoAccept, Max_No_People, Guest, FileList) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
		ppstmt.setString(1, eM.getEventName());
		ppstmt.setString(2, eM.getiGN());
		ppstmt.setInt(3, eM.getThumbnail());
		ppstmt.setString(4, eM.getDescription());
		ppstmt.setTimestamp(5, eM.getDate());
		ppstmt.setString(6, eM.getPostalCode());
		ppstmt.setString(7, eM.getVenue());
		ppstmt.setBoolean(8, eM.isAutoAccept());
		ppstmt.setInt(9, eM.getMax_No_People());
		ppstmt.setString(10, eM.getGuest());
		ppstmt.setString(11, eM.getFileList());

		executeUpdate(ppstmt);
	}
	
	public void updateEvent(String sql, EventModel eM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, eM.getEventName());
		ppstmt.setString(2, eM.getiGN());
		ppstmt.setInt(3, eM.getThumbnail());
		ppstmt.setString(4, eM.getDescription());
		ppstmt.setTimestamp(5, eM.getDate());
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
		ppstmt.setFloat(3, (float)fTM.getFileSize());

		executeUpdate(ppstmt);
	}
	
	//Insert into FileTable
	public void insertFileTable(FileTableModel fTM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO FileTable(FileName, Data, FileSize) VALUES (?,?,?);");
		ppstmt.setString(1, fTM.getFileName());
		ppstmt.setBytes(2, fTM.getData());
		ppstmt.setFloat(3, (float)fTM.getFileSize());

		executeUpdate(ppstmt);
	}
	
	//Get FileTable FileID
	public String getFileTableID(String fileName) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("SELECT FileID FROM FileTable WHERE FileName = ?;");
		ppstmt.setString(1, fileName);
		
		ResultSet rs = ppstmt.executeQuery();
		rs.next();
		String fileID	= rs.getString("FileID");
		return fileID;
	}
	
	//Get FileTable by fileName
	public FileTableModel getFileTableByFileName(String name) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("SELECT * FROM FileTable WHERE FileName = ?;");
		ppstmt.setString(1, name);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			int fileID	= rs.getInt("FileID");
			String fileName	= rs.getString("FileName");
			byte[] data	= rs.getBytes("Data");
			float fileSize	= rs.getFloat("FileSize");
			
			return new FileTableModel(fileID, fileName, data, fileSize);
		}
		return null;
	}
	
	//Get FileTable by fileID
	public FileTableModel getFileTableByFileID(int iD) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("SELECT * FROM FileTable WHERE FileID = ?;");
		ppstmt.setInt(1, iD);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			int fileID	= rs.getInt("FileID");
			String fileName	= rs.getString("FileName");
			byte[] data	= rs.getBytes("Data");
			float fileSize	= rs.getFloat("FileSize");
			
			return new FileTableModel(fileID, fileName, data, fileSize);
		}
		return null;
	}
	
	//Get FileName by FileID
	public String getFileNameByFileID(int iD) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("SELECT FileName FROM FileTable WHERE FileID = ?;");
		ppstmt.setInt(1, iD);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			String fileName	= rs.getString("FileName");
			
			return fileName;
		}
		return null;
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
		ppstmt.setTimestamp(4, fP.getDate());
		ppstmt.setInt(5, fP.getPicture());
		ppstmt.setString(6, fP.getDescription());
		ppstmt.setString(7, fP.getFileAttachment());

		executeUpdate(ppstmt);
	}
	
	public ArrayList<ForumPostModel> getForumModel() throws SQLException{
		ArrayList<ForumPostModel> forums = new ArrayList<ForumPostModel>();
		ResultSet rs = getResultSet("SELECT * FROM ForumPost");
		while(rs.next()) {
			int postID = rs.getInt("PostID");
			String thread = rs.getString("Thread");
			int upvotes = rs.getInt("Upvotes");
			String iGN = rs.getString("IGN");
			Timestamp date = rs.getTimestamp("Date");
			int picture = rs.getInt("Picture");
			String description = rs.getString("Description");
			String fileAttachment = rs.getString("FileAttachment");
			
			
			forums.add(new ForumPostModel(postID, thread, upvotes, iGN, date, picture, description, fileAttachment));
		}
		return forums;
	}
	
	//ForumVoteModel
	public void updateForumVote(String sql, ForumVoteModel fP) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setInt(1, fP.getPostID());
		ppstmt.setString(2, fP.getiGN());
		ppstmt.setDate(3, fP.getDate());

		executeUpdate(ppstmt);
	}
	
	//PeopleEventListModel
	public void updatePeopleEventList(String sql, PeopleEventListModel pELM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setInt(1, pELM.getEventID());
		ppstmt.setString(2, pELM.getInvitationPending());
		ppstmt.setString(3, pELM.getInvitationConfirm());

		executeUpdate(ppstmt);
	}
	
	//Get number of people pending
	public ArrayList<String> getPeopleEventListPending(int eventID) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT InvitationPending FROM PeopleEventList WHERE EventID = ?;");
		ppstmt.setInt(1, eventID);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			PeopleEventListModel pELM = new PeopleEventListModel(0, null, null);
			pELM.setInvitationPending(rs.getString("InvitationPending"));
			
			return pELM.getInvitationPendingArray();
		}
		return new ArrayList<String>();
	}
	
	//Get number of people confirmed
	public ArrayList<String> getPeopleEventListConfirm(int eventID) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT InvitationConfirm FROM PeopleEventList WHERE EventID = ?;");
		ppstmt.setInt(1, eventID);
		ResultSet rs = ppstmt.executeQuery();
		
		while(rs.next()) {
			PeopleEventListModel pELM = new PeopleEventListModel(0, null, null);
			pELM.setInvitationConfirm(rs.getString("InvitationConfirm"));
			
			return pELM.getInvitationConfirmArray();
		}
		return new ArrayList<String>();
	}
	
	public void close() throws SQLException {
		conn.close();
	}
	/*
	public static void main(String[] arg0) throws ClassNotFoundException, SQLException, IOException{
		Database db = new Database(2);
		File file = new File("C:\\Users\\Wei Xuan\\Desktop\\mountain.jpeg");
		InputStream in = new FileInputStream(file);
		String fileName = file.getName();
		byte[] fileData = IOUtils.toByteArray(in);
		float fileSize = file.length();
		
		System.out.println(fileName);
		System.out.println(fileData);
		System.out.println(fileSize);
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO FileTable(FileName, Data, FileSize) VALUES (?,?,?);");
		ps.setString(1, fileName);
		ps.setBytes(2, fileData);
		ps.setFloat(3, fileSize);
		db.executeUpdate(ps);
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
