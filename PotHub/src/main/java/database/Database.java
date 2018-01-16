package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import adminSearch.BansSearchObject;
import adminSearch.DonationSearchObject;
import adminSearch.RankSearchObject;
import adminSearch.ReportSearchObject;
import adminSearch.SearchSanitizer;
import database.model.AppealModel;
import database.model.BansModel;
import database.model.CommentModel;
import database.model.CommentVoteModel;
import database.model.DatabaseUserModel;
import database.model.DonationModel;
import database.model.EventModel;
import database.model.FileTableModel;
import database.model.FoodListModel;
import database.model.FoodPreferences;
import database.model.ForumPostModel;
import database.model.ForumVoteModel;
import database.model.ImageTableModel;
import database.model.LoginModel;
import database.model.LogsModel;
import database.model.PeopleEventListModel;
import database.model.PotcastBidModel;
import database.model.PotcastModel;
import database.model.ReportModel;
import database.model.ShoppingLoginModel;
import database.model.TemporaryStoreModel;
import logs.LogsSearch;
import p2pfood.PotcastSearchObject;
import profile.ProfileDonationSearch;

public class Database {
	//final String DB_URL="jdbc:sqlserver://119.74.135.44:3306;databaseName=PotHub;"
	final String DB_URL="jdbc:sqlserver://pothub.database.windows.net:1433;"
			+ "database=PotHub;"
			+ "user=PotHub@pothub;"
			+ "password=SassyPenguin123;"
			+ "encrypt=true;"
			+ "trustServerCertificate=false;"
			+ "hostNameInCertificate=*.database.windows.net;"
			+ "loginTimeout=30;";

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
	
	public String getDatabaseUserPostalCodeFromIGN(String ign) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT Address FROM DatabaseUser WHERE IGN = ?");
		ps.setString(1, ign);

		ResultSet rs =	ps.executeQuery();
		while(rs.next()) {
			return rs.getString("Address");
		}
		return "";
	}
	
	//For Login Page - Select User IGN by Email
	public DatabaseUserModel getIGNbyEmail(String email) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT IGN FROM DatabaseUser WHERE Email = ?;");
		ppstmt.setString(1, email);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String iGN					= rs.getString("IGN");
			return new DatabaseUserModel(iGN);
		}
		return null;
	}
	
	public boolean getPrivilegeForIGN(String ign) throws SQLException{
		PreparedStatement pptstmt = conn.prepareStatement("SELECT isPriviledged FROM databaseUser WHERE IGN = ?");
		pptstmt.setString(1, ign);
		ResultSet rs = pptstmt.executeQuery();
		while(rs.next()){
			return rs.getBoolean("isPriviledged");
		}
		return false;
	}
	
	//For Login Page
	public LoginModel getLogin(String enteredPassword, String enteredEmail) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Email, Password, Salt FROM Login WHERE Email = ?;");
		ppstmt.setString(1, enteredEmail);
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			String email = rs.getString("Email");
			String password = rs.getString("Password");
			String salt = rs.getString("Salt");

			return new LoginModel(email, password, salt);
		}
		return null;
	}
	
	//For Registration Page
	public void insertLogin(LoginModel lm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Login(Email, Password, Salt) VALUES(?,?,?);");
		ppstmt.setString(1, lm.getEmail());
		ppstmt.setString(2, lm.getPassword());
		ppstmt.setString(3, lm.getSalt());
		
		ppstmt.executeUpdate();
	}
	
	//For Registration Page
	public void insertRegistration(DatabaseUserModel dum) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO DatabaseUser(IGN, Email, Contact_No, Gender, Address, UnitNo, JoinDate, CookingRank, Points, TotalDonation, IsPriviledged, UserPermission) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
		ppstmt.setString(1, dum.getiGN());
		ppstmt.setString(2, dum.getEmail());
		ppstmt.setString(3, dum.getContact_No());
		ppstmt.setString(4, String.valueOf(dum.getGender()));
		ppstmt.setString(5, dum.getAddress());
		ppstmt.setString(6, dum.getUnitNo());
		ppstmt.setDate(7, new Date(Timestamp.from(Instant.now()).getTime()));
		ppstmt.setInt(8, 0);
		ppstmt.setInt(9, 0);
		ppstmt.setInt(10, 0);
		ppstmt.setBoolean(11, false);
		ppstmt.setInt(12, 0);
		
		ppstmt.executeUpdate();
	}
	
	//For admin panel - inserting new food for user's food preferences
	public void insertNewFood(FoodListModel flm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO FoodList(Food, FoodType) VALUES(?,?);");
		ppstmt.setString(1, flm.getFood());
		ppstmt.setString(2, flm.getFoodType());
		ppstmt.executeUpdate();
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
	
	public void updateRank(String ign, int permissionLevel){
		try {
			PreparedStatement ppstmt = conn.prepareStatement("UPDATE DatabaseUser SET UserPermission = ? WHERE ign = ?");
			ppstmt.setInt(1, permissionLevel);
			ppstmt.setString(2, ign);
			ppstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//For profile page - user's food preferences
	public ArrayList<FoodPreferences> getFoodPref(String name) throws SQLException {
		ArrayList<FoodPreferences> foodPrefList = new ArrayList<FoodPreferences>();
		PreparedStatement ppstmt = conn.prepareStatement("SELECT FoodPref FROM FoodPreferences WHERE IGN = ?");
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String foodPref = rs.getString("FoodPref");
			foodPrefList.add(new FoodPreferences(foodPref));
		}
		return foodPrefList;
	}
	
	//For profile page - user's food preferences
	public void insertFoodPref(FoodPreferences fp) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO FoodPreferences(IGN, FoodPref) VALUES(?,?);");
		ppstmt.setString(1, fp.getiGN());
		ppstmt.setString(2, fp.getFoodPref());
		ppstmt.executeUpdate();
	}
	
	//For profile page - user's food preferences
	public void deleteFoodPref(String foodPref) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("DELETE FROM FoodPreferences WHERE FoodPref = ?");
		ppstmt.setString(1, foodPref);
		ppstmt.executeUpdate();
	}
	
	//For profile page - user's donation history
	public ArrayList<DonationModel> getUserDonation(ProfileDonationSearch search, String name) throws SQLException {
		ArrayList<DonationModel> userDonationList = new ArrayList<DonationModel>();
		PreparedStatement ppstmt = conn.prepareStatement(search.getSearchQuery());
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			Timestamp donation_Date		= rs.getTimestamp("Donation_Date");
			BigDecimal donation_Amount	= rs.getBigDecimal("Donation_Amount");
			String onBehalf				= rs.getString("OnBehalf");
			userDonationList.add(new DonationModel(donation_Date, donation_Amount, onBehalf));
		}
		return userDonationList;
	}
	
	//For donation page
	public TemporaryStoreModel getTempStore(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT * FROM TemporaryStore WHERE IGN = ?");
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String iGN					= rs.getString("IGN");
			BigDecimal temporaryAmount 	= rs.getBigDecimal("TemporaryAmount");
			String temporaryPIN 		= rs.getString("TemporaryPIN");
			String temporarySalt		= rs.getString("TemporarySalt");
			String temporaryOnBehalf 	= rs.getString("TemporaryOnBehalf");
			Timestamp temporaryTime 	= rs.getTimestamp("TemporaryTime");
			return new TemporaryStoreModel(iGN, temporaryAmount, temporaryPIN, temporarySalt, temporaryOnBehalf, temporaryTime);
		}
		return null;
	}
	
	//For donation page
	public void updateTempStore(TemporaryStoreModel tsm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE TemporaryStore SET TemporaryPIN = ?, TemporarySalt = ?, TemporaryTime = ? WHERE IGN = ?");
		ppstmt.setString(1, tsm.getTemporaryPIN());
		ppstmt.setString(2, tsm.getTemporarySalt());
		ppstmt.setTimestamp(3, tsm.getTemporaryTime());
		ppstmt.setString(4, tsm.getiGN());
		ppstmt.executeUpdate();
	}
	
	//For donation page
	public void insertTempStore(TemporaryStoreModel tsm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO TemporaryStore(IGN, TemporaryAmount, TemporaryPIN, TemporarySalt, TemporaryOnBehalf, TemporaryTime) VALUES(?,?,?,?,?,?);");
		ppstmt.setString(1, tsm.getiGN());
		ppstmt.setBigDecimal(2, tsm.getTemporaryAmount());
		ppstmt.setString(3, tsm.getTemporaryPIN());
		ppstmt.setString(4, tsm.getTemporarySalt());
		ppstmt.setString(5, tsm.getTemporaryOnBehalf());
		ppstmt.setTimestamp(6, tsm.getTemporaryTime());
		ppstmt.executeUpdate();
	}
	
	//For donation page
	public void deleteFromTempStore(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("DELETE FROM TemporaryStore WHERE IGN = ?;");
		ppstmt.setString(1, name);
		ppstmt.executeUpdate();
	}
	
	//For donation page
	public void insertDonation(DonationModel dm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Donation(IGN, DonationDate, DonationAmount, OnBehalf) VALUES(?,?,?,?);");
		ppstmt.setString(1, dm.getiGN());
		ppstmt.setTimestamp(2, dm.getDonation_Date());
		ppstmt.setBigDecimal(3, dm.getDonation_Amount());
		ppstmt.setString(4, dm.getOnBehalf());
		ppstmt.executeUpdate();
	}
	
	//For logs page
	public ArrayList<LogsModel> getLogs(LogsSearch logsSearch) throws SQLException {
		ArrayList<LogsModel> logsList = new ArrayList<LogsModel>();
		PreparedStatement ppstmt = conn.prepareStatement(logsSearch.getSearchQuery());
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String iGN 				= rs.getString("IGN");
			Timestamp logDate		= rs.getTimestamp("LogDate");
			String iPAddress 		= rs.getString("IPAddress");
			String logType 			= rs.getString("LogType");
			String logActivity 		= rs.getString("LogActivity");
			boolean isSuspicious 	= rs.getBoolean("IsSuspicious");
			logsList.add(new LogsModel(iGN, logDate, iPAddress, logType, logActivity, isSuspicious));
		}
		return logsList;
	}
	
	//For logs page
	public void insertLogs(LogsModel lm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Logs(IGN, LogDate, IPAddress, LogType, LogActivity, IsSuspcious) VALUES(?,?,?,?,?,?);");
		ppstmt.setString(1, lm.getiGN());
		ppstmt.setTimestamp(2, lm.getLogDate());
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
	
	public ArrayList<DatabaseUserModel> getDatabaseUserRanks(RankSearchObject rso) throws SQLException {
		ArrayList<DatabaseUserModel> aldum = new ArrayList<DatabaseUserModel>();
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
	
	//Potcast 
	public ArrayList<PotcastModel> getLatestPotcasts(PotcastSearchObject pso) throws SQLException{
		ArrayList<PotcastModel> potcasts = new ArrayList<PotcastModel>();
		ResultSet rs = getResultSet(pso.getExecutableSQL());
		while(rs.next()) {
			String iGN			= rs.getString("iGN");
			int potcastID		= rs.getInt("potcastID");
			String title 		= rs.getString("title");
			String description	= rs.getString("description");
			int maxBids			= rs.getInt("maxBids");
			Timestamp bidStopTime	= rs.getTimestamp("bidStopTime");
			Timestamp pickupTime	= rs.getTimestamp("pickupTime");
			int minBid			= rs.getInt("minBid");
			int startingCR		= rs.getInt("startingCR");
			int picture 		= rs.getInt("picture");
			potcasts.add(new PotcastModel(iGN, potcastID, title, description, maxBids, bidStopTime,
					pickupTime, minBid, startingCR, picture));
		}
		return potcasts;
	}
	
	//Count Potcasts
	public int getNumberOfPotcastsFrom(String ign) throws SQLException{
	    Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	            ResultSet.CONCUR_READ_ONLY);
	        ResultSet r = s
	            .executeQuery("SELECT Count(*) FROM Potcast WHERE IGN = '"+SearchSanitizer.sanitise(ign)+"' AND PickupTime > '"+new Timestamp(System.currentTimeMillis()).toString()+"'");
	        r.last();
	        return r.getRow();
	}
	
	//Potcast Bids
	public ArrayList<PotcastBidModel> getBidsForPotcast(int id) throws SQLException{
		ArrayList<PotcastBidModel> pbms = new ArrayList<PotcastBidModel>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT b.potcastID, b.iGN, b.bidAmount FROM Potcast a INNER JOIN PotcastBid b ON a.PotcastID = b.PotcastID WHERE b.PotcastID = ? ORDER BY b.bidAmount;");
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			int potcastID = rs.getInt("potcastID");
			String iGN = rs.getString("iGN");
			BigDecimal bidAmount = rs.getBigDecimal("bidAmount");
			
			pbms.add(new PotcastBidModel(potcastID, iGN, bidAmount, ""));
		}
		
		return pbms;
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
	
	public ArrayList<BansModel> getBansModel(BansSearchObject bso) throws SQLException {
		ArrayList<BansModel> bannedppl = new ArrayList<BansModel>();

		ResultSet rs = getResultSet(bso.getExecutableSQL());
		while(rs.next()) {
			int banID					= rs.getInt("BanID");
			String iGN					= rs.getString("IGN");
			Date startDate				= rs.getDate("startDate");
			Date endDate				= rs.getDate("endDate");
			String reason				= rs.getString("reason");
			String admin				= rs.getString("admin");
			boolean pardoned			= rs.getBoolean("pardoned");
			bannedppl.add(new BansModel(banID, iGN, startDate, endDate, reason, admin, pardoned));
		}
		return bannedppl;
	}
	
	public int howManyBans(BansSearchObject bso) throws SQLException {
		ResultSet rs = getResultSet(bso.getExecutableSQL());
		int counter = 0;
		while(rs.next()){
			counter++;
		}
		return counter;
	}
	
	//Report
	public ArrayList<ReportModel> getManyReports(ReportSearchObject rso) throws SQLException{
			ArrayList<ReportModel> reports = new ArrayList<ReportModel>();
			ResultSet rs = getResultSet(rso.getExecutableSQL());
			while(rs.next()) {
				int reportID = 				rs.getInt("reportID");
				String iGNSend = 			rs.getString("IGNSend");
				String iGNReceive = 		rs.getString("IGNReceive");
				String evidenceType = 		rs.getString("evidenceType");
				Date date =					rs.getDate("Date");
				int evidence = 				rs.getInt("Evidence");
				String reason = 			rs.getString("reason");
				int guiltyOrNot = 		rs.getInt("guiltyOrNot");
				
				reports.add(new ReportModel(reportID, iGNSend, iGNReceive, evidenceType, date, evidence,
						reason, guiltyOrNot));
			}
			return reports;
	}
	
	public ArrayList<ReportModel> getPersonalReports(ReportSearchObject rso) throws SQLException{
		ArrayList<ReportModel> reports = new ArrayList<ReportModel>();
		ResultSet rs = getResultSet(rso.getExecutableSQL());
		while(rs.next()) {
			int reportID = 				rs.getInt("reportID");
			String iGNSend = 			rs.getString("IGNSend");
			String iGNReceive = 		rs.getString("IGNReceive");
			String evidenceType = 		rs.getString("evidenceType");
			Date date =					rs.getDate("Date");
			int evidence = 				rs.getInt("Evidence");
			String reason = 			rs.getString("reason");
			int guiltyOrNot = 		rs.getInt("guiltyOrNot");
			
			reports.add(new ReportModel(reportID, iGNSend, iGNReceive, evidenceType, date, evidence,
					reason, guiltyOrNot));
		}
		return reports;
	}
	

	public void convictUser(boolean permanent, int reportID, String admin) throws SQLException{
			long maxTime = (long)21459167 * (long)1000000;
			
			ReportSearchObject rso = new ReportSearchObject();
			rso.setReportID(reportID);
			
			ArrayList<ReportModel> rms = this.getPersonalReports(rso);
			ReportModel subjectUser = rms.get(0);
		
			PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Bans (IGN, startDate, endDate, reason, admin, pardoned) Values (?,?,?,?,?,?)");
			ppstmt.setString(1, subjectUser.getiGNReceive());
			ppstmt.setDate(2, new Date(System.currentTimeMillis()));
			
			
			if(permanent){
				ppstmt.setDate(3, new Date(maxTime));
			}
			else{
				BansSearchObject bso = new BansSearchObject();
				bso.setiGN(subjectUser.getiGNReceive());

				if(howManyBans(bso)==0){
					ppstmt.setDate(3, new Date(System.currentTimeMillis()+(86400*1000)));
				}
				else if(howManyBans(bso)==1){
					ppstmt.setDate(3, new Date(System.currentTimeMillis()+(3*86400*1000)));
				}
				else if(howManyBans(bso)==2){
					ppstmt.setDate(3, new Date(System.currentTimeMillis()+(7*86400*1000)));
				}
				else{
					ppstmt.setDate(3, new Date(System.currentTimeMillis()+(14*86400*1000)));
				}
			}

			ppstmt.setString(4,subjectUser.getReason());
			ppstmt.setString(5, "HandsomeMatt");
			ppstmt.setBoolean(6, false);

			executeUpdate(ppstmt);
			
			PreparedStatement ppstmt2 = conn.prepareStatement("UPDATE Report SET guiltyOrNot=2 WHERE ReportID = ?");
			ppstmt2.setInt(1, reportID);
			
			ppstmt2.execute();
	}
	
	public void pardonUser(String iGN) throws SQLException{
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE Bans SET pardoned='true' WHERE IGN = ?");
		ppstmt.setString(1, iGN);
		executeUpdate(ppstmt);
	}
	
	public void pardonReport(int reportID) throws SQLException{
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE Report SET guiltyOrNot=1 WHERE ReportID = ?");
		ppstmt.setInt(1, reportID);
		executeUpdate(ppstmt);
	}
	
	//CommentModel
	public ArrayList<CommentModel> getCommentModel() throws SQLException{
		ArrayList<CommentModel> comments = new ArrayList<CommentModel>();
		ResultSet rs = getResultSet("SELECT * FROM Comment");
		while(rs.next()) {
			int commentId = rs.getInt("commentID");
			int postId = rs.getInt("postID");
			Date d = rs.getDate("date");
			String ign = rs.getString("iGN");
			int comment = rs.getInt("comment1");
			String description = rs.getString("description");
			
			
			comments.add(new CommentModel(commentId, postId, d, ign, comment, description));
		}
		return comments;
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
	
	public void addComment(CommentModel c) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Comment(postID, date, iGN, comment1, description) VALUES (?,?,?,?,?); ");
		ppstmt.setInt(1, c.getPostID());
		ppstmt.setDate(2, c.getDate());
		ppstmt.setString(3, c.getiGN());
		ppstmt.setInt(4, c.getComment1());
		ppstmt.setString(5, c.getDescription());
	
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
		ppstmt.setTimestamp(2, dM.getDonation_Date());
		ppstmt.setBigDecimal(3, dM.getDonation_Amount());
		ppstmt.setString(4, dM.getOnBehalf());

		executeUpdate(ppstmt);
	}
	
	public ArrayList<DonationModel> getDonationModel(DonationSearchObject dm) throws SQLException{
		ArrayList<DonationModel> donations = new ArrayList<DonationModel>();
		ResultSet rs = getResultSet(dm.getExecutableSQL());
		while(rs.next()) {
			int donationID				= rs.getInt("DonationID");
			String iGN					= rs.getString("IGN");
			Timestamp donationDate		= rs.getTimestamp("donation_Date");
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
			PreparedStatement ppstmt2 = conn.prepareStatement("SELECT ImageName FROM ImageTable WHERE ImageID = ?;");
			ppstmt2.setInt(1, profilePic);
			ResultSet rs2 = ppstmt2.executeQuery();
			while(rs2.next()) {
				String fileName = rs2.getString("ImageName");
				
				return fileName;
			}
		}
		return null;
	}
	
	//Get IGN's priviledge
	public boolean getUserPriviledge(String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT isPriviledged FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, iGN);
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			boolean isPriviledged = rs.getBoolean("isPriviledged");
			
			return isPriviledged;
		}
		return false;
	}
		
	//For MyEvent
	public ArrayList<EventModel> getEventModelForMyEventPage() throws SQLException, UnsupportedEncodingException {
		ArrayList<EventModel> alem = new ArrayList<EventModel>();
		ResultSet rs = getResultSet("SELECT EventID, EventName, IGN, Thumbnail, Description, Date, PostalCode, Venue, Max_No_People, Guest FROM Event ORDER BY Date DESC;");
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
	
	//Get ImageName by ImageID
	public String getImageByImageID(int iD) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("SELECT ImageName FROM ImageTable WHERE ImageID = ?;");
		ppstmt.setInt(1, iD);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			String imageName	= rs.getString("ImageName");
			return imageName;
		}
		return null;
	}
	
	//Get ImageTable by ImageID
	public ImageTableModel getImageTableByImageID(int id) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("SELECT ImageName, ImageID, ImageData, inUse FROM ImageTable WHERE ImageID = ?;");
		ppstmt.setInt(1, id);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			String imageName	= rs.getString("ImageName");
			int imageID = rs.getInt("ImageID");
			byte[] imageData = rs.getBytes("ImageData");
			int inUse = rs.getInt("inUse");
			return new ImageTableModel(imageID, imageName, imageData, inUse);
		}
		return null;
	}
	
	//Get ImageTable by ImageName
	public ImageTableModel getImageTableByImageName(String hashname) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("SELECT ImageName, ImageID, ImageData, inUse FROM ImageTable WHERE ImageName = ?;");
		ppstmt.setString(1, hashname);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			String imageName	= rs.getString("ImageName");
			int imageID = rs.getInt("imageID");
			byte[] imageData = rs.getBytes("imageData");
			int inUse = rs.getInt("inUse");
			return new ImageTableModel(imageID, imageName, imageData, inUse);
		}
		return null;
	}
	
	//Add ImageName by ImageID
	//Returns the ID of wherever your image ends up (Or a dupe of it)
	public int addPictureWithDupeCheck(String imageName, byte[] imageData) throws SQLException, NoSuchAlgorithmException, IOException { 
		String hash = SHA1.SHAsum(imageData);
		String extension = imageName.substring(imageName.length()-4, imageName.length()-1);
		//Gets the storable name
		String hashName = hash+extension;
		
		//Checking for a dupe
		int idUsingHashName = getIDUsingHashName(hashName);
		
		//If dupe is found
		if(idUsingHashName!=-1){
			setInUseFor(idUsingHashName,getInUseFor(idUsingHashName));
			return idUsingHashName;
		}
		else{
		//Inserting a new entry
		//(ADD UPLOAD CODES HERE)
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO ImageTable (ImageName, imageData, inUse) Values(?,?,?);");
		ppstmt.setString(1, hashName);
		ppstmt.setBytes(2, imageData);
		ppstmt.setInt(3, 1);
		
		
		ppstmt.executeUpdate();
		//(/ADD UPLOAD CODES HERE)
		//Searching for ID of new entry
		PreparedStatement ppstmt2 = conn.prepareStatement("SELECT ImageID FROM ImageTable WHERE imageName=?;");
		ppstmt2.setString(1, hashName);
		ResultSet checkForNewEntry = ppstmt2.executeQuery();
		
		while(checkForNewEntry.next()){
			return checkForNewEntry.getInt("ImageID");
		}
			System.out.println("Cannot find new entry");
		return 0;
		}
	}
	
	public int getIDUsingHashName(String hashName) throws SQLException{
		PreparedStatement ppstmt = conn.prepareStatement("SELECT ImageID, ImageName, inUse FROM ImageTable WHERE imageName=?;");
		ppstmt.setString(1, hashName);
		ResultSet checkForDuplicates = ppstmt.executeQuery();
		
		while(checkForDuplicates.next()){
			int inUseCount = checkForDuplicates.getInt("inUse");
			int id = checkForDuplicates.getInt("ImageID");
			
			setInUseFor(id, inUseCount);
			
			return checkForDuplicates.getInt("ImageID");
		}
		return -1;
	}
	
	public void setInUseFor(int id, int inUse) throws SQLException{
		PreparedStatement updatingInUseByID = conn.prepareStatement("UPDATE ImageTable SET inUse = ? WHERE ImageID = ?");
		updatingInUseByID.setInt(1, inUse+1);
		updatingInUseByID.setInt(2, id);
		
		updatingInUseByID.executeUpdate();
	}
	
	public int getInUseFor(int id) throws SQLException{
		PreparedStatement gettingInUseByID = conn.prepareStatement("SELECT inUse FROM ImageTable WHERE ImageID = ?");
		gettingInUseByID.setInt(1, id);
		
		ResultSet oneLiner = gettingInUseByID.executeQuery();
		while(oneLiner.next()){
			return oneLiner.getInt("inUse");
		}
		
		System.out.println("No record found from ID");
		return 0;
	}
	
	public int editPicture(String imageName, byte[] imageData, int oldID) throws NoSuchAlgorithmException, IOException, SQLException{
		int toRet=0;
		toRet = addPictureWithDupeCheck(imageName, imageData);
		setInUseFor(oldID,getInUseFor(oldID)-1);
		
		return toRet;
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
		ResultSet rs = getResultSet("SELECT * FROM ForumPost ORDER BY PostID desc");
		while(rs.next()) {
			int postID = rs.getInt("PostID");
			String thread = rs.getString("Thread");
			int upvotes = rs.getInt("Upvotes");
			String iGN = rs.getString("IGN");
			Timestamp date = rs.getTimestamp("Date");
			int picture = rs.getInt("Picture");
			String description = rs.getString("Description");
			String fileAttachment = rs.getString("FileAttachment");
			String text = rs.getString("ForumNormalText");
			String url = rs.getString("ForumURL");
			
			
			forums.add(new ForumPostModel(postID, thread, upvotes, iGN, date, picture, description, fileAttachment, text, url));
		}
		return forums;
	}
	
	public void addForumPost(ForumPostModel fP) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO ForumPost(Thread, Upvotes, IGN, Date, Picture, Description, FileAttachment, ForumNormalText, ForumURL) VALUES (?,?,?,?,?,?,?,?,?); ");
		ppstmt.setString(1, fP.getThread());
		ppstmt.setInt(2, fP.getUpvotes());
		ppstmt.setString(3, fP.getiGN());
		ppstmt.setTimestamp(4, fP.getDate());
		ppstmt.setInt(5, fP.getPicture());
		ppstmt.setString(6, fP.getDescription());
		ppstmt.setString(7, fP.getFileAttachment());
		ppstmt.setString(8, fP.getForumNormalText());
		ppstmt.setString(9, fP.getForumURL());

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
	public static void main(String[] arg0) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException{
		Database db = new Database(2);
		File file = new File("C:\\Users\\Wei Xuan\\Desktop\\mountain.jpeg");
		InputStream in = new FileInputStream(file);
		String fileName = file.getName();
		byte[] fileData = IOUtils.toByteArray(in);
		float fileSize = file.length();
		
		System.out.println(fileName);
		System.out.println(fileData);
		System.out.println(fileSize);
		
		System.out.println(db.addPictureWithDupeCheck(fileName, fileData));
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
