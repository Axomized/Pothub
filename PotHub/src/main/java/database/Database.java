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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import adminSearch.BansSearchObject;
import adminSearch.DonationSearchObject;
import adminSearch.RankSearchObject;
import adminSearch.ReportSearchObject;
import adminSearch.SearchSanitizer;
import database.model.*;
import logs.LogsSearch;
import p2pfood.PotcastSearchObject;
import potcastTalk.TalkTimer;
import profile.ProfileDonationSearch;
import profile.ProfileUpdate;

public class Database {
	//final String DB_URL="jdbc:sqlserver://119.74.135.44:3306;databaseName=PotHub;"
	private final String DB_URL="jdbc:sqlserver://pothub.database.windows.net:1433;"
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
			boolean isFiltered			= rs.getBoolean("IsFiltered");
			aldum.add(new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged, userPermission, isFiltered));
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
	
	public String getEmailByIGN(String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Email FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, iGN);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			return rs.getString("email");
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
	
	public int getPermissionForIGN(String ign) throws SQLException{
		PreparedStatement pptstmt = conn.prepareStatement("SELECT userPermission FROM databaseUser WHERE IGN = ?");
		pptstmt.setString(1, ign);
		ResultSet rs = pptstmt.executeQuery();
		while(rs.next()){
			return rs.getInt("userPermission");
		}
		return 0;
	}
	
	public DatabaseUserModel getDatabaseUserByIGN(String ign) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM DatabaseUser WHERE IGN = ?");
		ps.setString(1, ign);
		ResultSet rs = ps.executeQuery();
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
			boolean isFiltered			= rs.getBoolean("IsFiltered");
			return new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged, userPermission, isFiltered);
		}
		return null;
	}
	
	public DatabaseUserModel getDatabaseUserByEmail(String theEmail) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM DatabaseUser WHERE email = ?");
		ps.setString(1, theEmail);
		ResultSet rs = ps.executeQuery();
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
			boolean isFiltered			= rs.getBoolean("IsFiltered");
			return new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged, userPermission, isFiltered);
		}
		return null;
	}
	
	public int getDatabaseUserRank(String ign) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT CookingRank FROM DatabaseUser WHERE IGN = ?");
		ps.setString(1, ign);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			return rs.getInt("CookingRank");
		}
		return 0;
	}
	
	public void setDatabaseUserRank(String ign, int newRank) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("UPDATE databaseUser SET CookingRank = ? WHERE ign =?");
		ps.setInt(1, newRank);
		ps.setString(2, ign);
		
		ps.executeUpdate();
	}
	
	//For Login Page
	public LoginModel getLogin(String enteredPassword, String enteredEmail) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Email, Password, Salt, passwordResetted FROM Login WHERE Email = ?;");
		ppstmt.setString(1, enteredEmail);
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			String email = rs.getString("Email");
			String password = rs.getString("Password");
			String salt = rs.getString("Salt");
			Boolean passwordResetted = rs.getBoolean("passwordResetted");

			return new LoginModel(email, password, salt, passwordResetted);
		}
		return null;
	}
	
	//For Registration Page
	public void insertLogin(LoginModel lm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Login(Email, Password, Salt, passwordResetted) VALUES(?,?,?,?);");
		ppstmt.setString(1, lm.getEmail());
		ppstmt.setString(2, lm.getPassword());
		ppstmt.setString(3, lm.getSalt());
		ppstmt.setBoolean(4, false);
		
		ppstmt.executeUpdate();
	}
	
	//For Registration Page
	public void insertRegistration(DatabaseUserModel dum) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO DatabaseUser(IGN, Email, Contact_No, Gender, Address, UnitNo, JoinDate, CookingRank, Points, TotalDonation, IsPriviledged, UserPermission, IsFiltered) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
		ppstmt.setString(1, dum.getiGN());
		ppstmt.setString(2, dum.getEmail());
		ppstmt.setString(3, dum.getContact_No());
		ppstmt.setString(4, String.valueOf(dum.getGender()));
		ppstmt.setString(5, dum.getAddress());
		ppstmt.setString(6, dum.getUnitNo());
		ppstmt.setDate(7, new Date(Timestamp.from(Instant.now()).getTime()));
		ppstmt.setInt(8, 2000);
		ppstmt.setInt(9, 0);
		ppstmt.setInt(10, 0);
		ppstmt.setBoolean(11, false);
		ppstmt.setInt(12, 0);
		ppstmt.setBoolean(13, false);
		
		ppstmt.executeUpdate();
	}
	
	//For Registration Page, Login Page, and Forget Password
	public boolean getEmail(String enteredEmail) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT count(*) FROM Login WHERE Email = ?;");
		ppstmt.setString(1, enteredEmail);
		ResultSet rs = ppstmt.executeQuery();
		boolean emailExist = false;
		if (rs.next()) {
			int count = rs.getInt(1);
			if (count > 0) {
				emailExist = true;
			}
		}
		return emailExist;
		
	}
	
	//For Registration Page - IGN
	public boolean getIGN(String enteredName) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT count(*) FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, enteredName);
		ResultSet rs = ppstmt.executeQuery();
		boolean nameExist = false;
		if (rs.next()) {
			int count = rs.getInt(1);
			if (count > 0) {
				nameExist = true;
			}
		}
		return nameExist;
	}
	
	//For Forget Password Page - Update New Password and Change passwordResetted to true
	public void updatePassword(String newPassword, String enteredEmail) throws SQLException{
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE Login SET Password = ?, passwordResetted = ? WHERE Email = ?");
		ppstmt.setString(1, newPassword);
		ppstmt.setBoolean(2, true);
		ppstmt.setString(3, enteredEmail);
		executeUpdate(ppstmt);
	}
	
	//For Force Change Password - Update New Password and Change passwordResetted to false
	public void updateChangedPassword(String newPassword, String enteredEmail) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE Login SET Password = ?, passwordResetted = ? WHERE Email = ?");
		ppstmt.setString(1, newPassword);
		ppstmt.setBoolean(2, false);
		ppstmt.setString(3, enteredEmail);
		executeUpdate(ppstmt);
	}
		
	//For admin panel - inserting new food for user's food preferences
	public void insertNewFood(FoodListModel flm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO FoodList(Food, FoodType) VALUES(?,?);");
		ppstmt.setString(1, flm.getFood());
		ppstmt.setString(2, flm.getFoodType());
		ppstmt.executeUpdate();
	}
	
	//For profile page - user's food preferences
	public ArrayList<FoodListModel> getFoodList(String type) throws SQLException {
		ArrayList<FoodListModel> foodList = new ArrayList<FoodListModel>();
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Food FROM FoodList WHERE FoodType = ?;");
		ppstmt.setString(1, type);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String food = rs.getString("Food");
			foodList.add(new FoodListModel(food));
		}
		return foodList;
	}
	
	//For profile page - user's profile information
	public DatabaseUserModel getUserProfile(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Email, Contact_No, Gender, Bio, Address, UnitNo, ProfilePic, JoinDate, CookingRank, Points, TotalDonation, IsPriviledged, IsFiltered FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String email 				= rs.getString("Email");
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
			boolean isFiltered			= rs.getBoolean("IsFiltered");
			return new DatabaseUserModel(email, contact_No, gender, bio, address, unitNo, profilePic, joinDate, cookingRank, points, totalDonation, isPriviledged, isFiltered);
		}
		return null;
	}
	
	public int getCookingRankFrom(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT CookingRank FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, name);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()){
			return rs.getInt("CookingRank");
		}
		return 0;
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
	public ArrayList<FoodPreferencesModel> getFoodPref(String name) throws SQLException {
		ArrayList<FoodPreferencesModel> foodPrefList = new ArrayList<FoodPreferencesModel>();
		PreparedStatement ppstmt = conn.prepareStatement("SELECT FoodPref FROM FoodPreferences WHERE IGN = ?");
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String foodPref = rs.getString("FoodPref");
			foodPrefList.add(new FoodPreferencesModel(foodPref));
		}
		return foodPrefList;
	}
	
	//For profile page - user's food preferences
	public void insertFoodPref(String name, String foodPref) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO FoodPreferences(IGN, FoodPref) VALUES(?,?);");
		ppstmt.setString(1, name);
		ppstmt.setString(2, foodPref);
		ppstmt.executeUpdate();
	}
	
	//For profile page - user's food preferences
	public void deleteFoodPref(String name, String foodPref) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("DELETE FROM FoodPreferences WHERE IGN = ? AND foodPref = ?;");
		ppstmt.setString(1, name);
		ppstmt.setString(2, foodPref);
		ppstmt.executeUpdate();
	}
	
	//For profile page - user's donation history
	public ArrayList<DonationModel> getUserDonation(ProfileDonationSearch search, String name) throws SQLException {
		ArrayList<DonationModel> userDonationList = new ArrayList<DonationModel>();
		PreparedStatement ppstmt = conn.prepareStatement(search.getSearchQuery());
		ppstmt.setString(1, name);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			Timestamp donationDate		= rs.getTimestamp("DonationDate");
			BigDecimal donationAmount	= rs.getBigDecimal("DonationAmount");
			String onBehalf				= rs.getString("OnBehalf");
			userDonationList.add(new DonationModel(donationDate, donationAmount, onBehalf));
		}
		return userDonationList;
	}
	
	//For profile page - update user's profile information
	public boolean updateUserProfile(ProfileUpdate profileUpdate) throws SQLException {
		boolean success = false;
		PreparedStatement ppstmt = conn.prepareStatement(profileUpdate.getUpdateQuery());
		int count = ppstmt.executeUpdate();
		if (count != 0) {
			success = true;
		}
		return success;
	}
	
	public LoginModel getUserPassSalt(String email) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Password, Salt FROM Login WHERE Email = ?;");
		ppstmt.setString(1, email);
		ResultSet rs = ppstmt.executeQuery();
		while (rs.next()) {
			String password = rs.getString("Password");
			String salt 	= rs.getString("Salt");
			return new LoginModel(password, salt);
		}
		return null;
	}
	
	//For profile page - changing user's passwords
	public boolean updateUserPassAndSalt(LoginModel lm) throws SQLException{
		boolean success = false;
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE Login SET Password = ?, Salt = ? WHERE Email = ?;");
		ppstmt.setString(1, lm.getPassword());
		ppstmt.setString(2, lm.getSalt());
		ppstmt.setString(3, lm.getEmail());
		int count = ppstmt.executeUpdate();
		if (count != 0) {
			success = true;
		}
		return success;
	}
	
	//For donation page
	public TemporaryStoreModel getTempStore(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT * FROM TemporaryStore WHERE IGN = ?;");
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
	public boolean updateTempStore(TemporaryStoreModel tsm) throws SQLException {
		boolean success = false;
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE TemporaryStore SET TemporaryPIN = ?, TemporarySalt = ?, TemporaryTime = ? WHERE IGN = ?;");
		ppstmt.setString(1, tsm.getTemporaryPIN());
		ppstmt.setString(2, tsm.getTemporarySalt());
		ppstmt.setTimestamp(3, tsm.getTemporaryTime());
		ppstmt.setString(4, tsm.getiGN());
		int count = ppstmt.executeUpdate();
		if (count != 0) {
			success = true;
		}
		return success;
	}
	
	//For donation page
	public boolean insertTempStore(TemporaryStoreModel tsm) throws SQLException {
		boolean success = false;
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO TemporaryStore(IGN, TemporaryAmount, TemporaryPIN, TemporarySalt, TemporaryOnBehalf, TemporaryTime) VALUES(?,?,?,?,?,?);");
		ppstmt.setString(1, tsm.getiGN());
		ppstmt.setBigDecimal(2, tsm.getTemporaryAmount());
		ppstmt.setString(3, tsm.getTemporaryPIN());
		ppstmt.setString(4, tsm.getTemporarySalt());
		ppstmt.setString(5, tsm.getTemporaryOnBehalf());
		ppstmt.setTimestamp(6, tsm.getTemporaryTime());
		int count = ppstmt.executeUpdate();
		if (count != 0) {
			success = true;
		}
		return success;
	}
	
	//For donation page
	public void deleteFromTempStore(String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("DELETE FROM TemporaryStore WHERE IGN = ?;");
		ppstmt.setString(1, name);
		ppstmt.executeUpdate();
	}
	
	//For donation page
	public void updateTotalDonation(BigDecimal donatedAmount, String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE DatabaseUser SET TotalDonation = ? WHERE IGN = ?;");
		ppstmt.setBigDecimal(1, donatedAmount);
		ppstmt.setString(2, name);
		ppstmt.executeUpdate();
	}
	
	//For donation page
	public void updateIsPrivileged(boolean isPrivileged, String name) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE DatabaseUser SET isPriviledged = ? WHERE IGN = ?;");
		ppstmt.setBoolean(1, isPrivileged);
		ppstmt.setString(2, name);
		ppstmt.executeUpdate();
	}
	
	//For donation page
	public void insertDonation(DonationModel dm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Donation(IGN, DonationDate, DonationAmount, OnBehalf) VALUES(?,?,?,?);");
		ppstmt.setString(1, dm.getiGN());
		ppstmt.setTimestamp(2, dm.getDonationDate());
		ppstmt.setBigDecimal(3, dm.getDonationAmount());
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
			logsList.add(new LogsModel(iGN, logDate, iPAddress, logType, logActivity));
		}
		return logsList;
	}
	
	//For logs page
	public void insertLogs(LogsModel lm) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Logs(IGN, LogDate, IPAddress, LogType, LogActivity) VALUES(?,?,?,?,?);");
		ppstmt.setString(1, lm.getiGN());
		ppstmt.setTimestamp(2, lm.getLogDate());
		ppstmt.setString(3, lm.getiPAddress());
		ppstmt.setString(4, lm.getLogType());
		ppstmt.setString(5, lm.getLogActivity());
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
			boolean isFiltered			= false;
			aldum.add(new DatabaseUserModel(email, iGN, contact_No, gender, bio, address, unitNo, profilePic, lastLogin, joinDate, cookingRank, points, totalDonation, isPriviledged, userPermission, isFiltered));
		}
		return aldum;
	}
	
	public int getDatabaseUserPoints(String ignn) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Points FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, ignn);
		int point = 0;
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			point = rs.getInt("Points");	
		}
		return point;
	}
	
	public void addDatabaseUserPoints(int pointt, String ignn) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE DatabaseUser SET Points = ? WHERE IGN = ?;");
		ppstmt.setInt(1, pointt);
		ppstmt.setString(2, ignn);
		
		executeUpdate(ppstmt);
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
			String iGN					= rs.getString("iGN");
			int potcastID				= rs.getInt("potcastID");
			String title 				= rs.getString("title");
			String description			= rs.getString("description");
			int maxBids					= rs.getInt("maxBids");
			Timestamp bidStopTime		= rs.getTimestamp("bidStopTime");
			Timestamp pickupTime		= rs.getTimestamp("pickupTime");
			int minBid					= rs.getInt("minBid");
			int startingCR				= rs.getInt("startingCR");
			int picture 				= rs.getInt("picture");
			boolean startCookingEmail	= rs.getBoolean("startCookingEmail");
			potcasts.add(new PotcastModel(iGN, potcastID, title, description, maxBids, bidStopTime,
					pickupTime, minBid, startingCR, picture, startCookingEmail));
		}
		return potcasts;
	}
	
	public PotcastModel getPotcastByID(int id) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Potcast WHERE potcastID = ?");
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String iGN					= rs.getString("iGN");
			int potcastID				= rs.getInt("potcastID");
			String title 				= rs.getString("title");
			String description			= rs.getString("description");
			int maxBids					= rs.getInt("maxBids");
			Timestamp bidStopTime		= rs.getTimestamp("bidStopTime");
			Timestamp pickupTime		= rs.getTimestamp("pickupTime");
			int minBid					= rs.getInt("minBid");
			int startingCR				= rs.getInt("startingCR");
			int picture 				= rs.getInt("picture");
			boolean startCookingEmail	= rs.getBoolean("startCookingEmail");
			return(new PotcastModel(iGN, potcastID, title, description, maxBids, bidStopTime,
					pickupTime, minBid, startingCR, picture, startCookingEmail));
		}
		return null;
	}
	
	public ArrayList<PotcastModel> getPotcastStartCookingEmail() throws SQLException{
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Potcast WHERE startCookingEmail = ? AND bidStopTime > ?");
		ps.setBoolean(1, false);
		ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		
		ArrayList<PotcastModel> potcasts = new ArrayList<PotcastModel>();
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String iGN					= rs.getString("iGN");
			int potcastID				= rs.getInt("potcastID");
			String title 				= rs.getString("title");
			String description			= rs.getString("description");
			int maxBids					= rs.getInt("maxBids");
			Timestamp bidStopTime		= rs.getTimestamp("bidStopTime");
			Timestamp pickupTime		= rs.getTimestamp("pickupTime");
			int minBid					= rs.getInt("minBid");
			int startingCR				= rs.getInt("startingCR");
			int picture 				= rs.getInt("picture");
			boolean startCookingEmail	= rs.getBoolean("startCookingEmail");
			potcasts.add(new PotcastModel(iGN, potcastID, title, description, maxBids, bidStopTime,
					pickupTime, minBid, startingCR, picture, startCookingEmail));
		}
		return potcasts;
	}
	
	public void updateBid(String ign, int potcastID, int rating) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("UPDATE PotcastBid SET rating = ? WHERE ign = ? AND potcastID = ?");
		ps.setInt(1, rating);
		ps.setString(2, ign);
		ps.setInt(3, potcastID);
		
		ps.executeUpdate();
	}
	
	//Count Potcasts
	public int getNumberOfPotcastsFrom(String ign) throws SQLException{
	    PreparedStatement ps = conn.prepareStatement("SELECT * FROM Potcast WHERE IGN = ? AND PickupTime > ?");
	    ps.setString(1, ign);
	    ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
	    
	    ResultSet rs = ps.executeQuery();
	    int toRet=0;
	    while(rs.next()){
	    	toRet++;
	    }
	    return toRet;
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
	
	//Potcast Bids
	public ArrayList<Integer> getBidsForPotcastUser(String ign) throws SQLException{
		ArrayList<Integer> pbms = new ArrayList<Integer>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT PotcastID FROM PotcastBid WHERE ign = ?;");
		ps.setString(1, ign);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			pbms.add(rs.getInt("potcastID"));
		}
		
		return pbms;
	}
	
	public ArrayList<PotcastModel> getJoinedPotcasts(String ign) throws SQLException{
		ArrayList<PotcastModel> pbms = new ArrayList<PotcastModel>();
		ArrayList<Integer> potcastIDs = this.getBidsForPotcastUser(ign);
		
		for(Integer potcastID : potcastIDs){
			pbms.add(this.getPotcastByID(potcastID));
		}
		
		return pbms;
	}
	
	public String addPotcastBid(PotcastBidModel pbm) throws SQLException{
		ArrayList<PotcastBidModel> bids = this.getBidsForPotcast(pbm.getPotcastID());
		PotcastModel pot = this.getPotcastByID(pbm.getPotcastID());
		
		if(pbm.getBidAmount().intValue()<=0){
			return "Bid too low";
		}
		//If bid is below minimum
		if(pbm.getBidAmount().intValue()<pot.getMinBid()){
			return "Bid too low";
		}
		//If more bids than max, check: if input bid is larger than lowest relevant bid

		else if(bids.size()>pot.getMaxBids() && bids.get(bids.size()-pot.getMaxBids()).getBidAmount().intValue()>pbm.getBidAmount().intValue()){
			return "Bid too low";
		}
		else if(pbm.getBidAmount().intValue()>250){
			return "Bid realistically please";
		}
		else{
		PreparedStatement ps = conn.prepareStatement("INSERT INTO PotcastBid (PotcastID, IGN, bidAmount) values (?,?,?)");
		ps.setInt(1, pbm.getPotcastID());
		ps.setString(2, pbm.getiGN());
		ps.setBigDecimal(3, pbm.getBidAmount());
		
		ps.executeUpdate();
		return "Bid accepted!";
		}
	}
	
	//Potcast Add
	public void addPotcast(PotcastModel pcm) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("INSERT INTO Potcast (IGN, Title, Description, MaxBids, MinBid, BidStopTime, PickupTime, StartingCR, Picture) VALUES (?,?,?,?,?,?,?,?,?)");
		ps.setString(1, pcm.getiGN());
		ps.setString(2, pcm.getTitle());
		ps.setString(3, pcm.getDescription());
		ps.setInt(4, pcm.getMaxBids());
		ps.setInt(5, pcm.getMinBid());
		ps.setTimestamp(6, pcm.getBidStopTime());
		ps.setTimestamp(7, pcm.getPickupTime());
		ps.setInt(8, pcm.getStartingCR());
		ps.setInt(9, pcm.getPicture());
		
		ps.executeUpdate();
		
		TalkTimer.addToTimer(pcm);
	}
	
	//Potcast Add
	public void setPotcastEmailAsSend(int id) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("UPDATE potcast set StartCookingEmail = ? WHERE potcastID = ?");
		ps.setBoolean(1, true);
		ps.setInt(2, id);
		
		ps.executeUpdate();
	}
	
	//Appeal
	public ArrayList<AppealModel> getAppeal() throws SQLException{
		ArrayList<AppealModel> appeals = new ArrayList<AppealModel>();
		ResultSet rs = getResultSet("SELECT * FROM Bans LEFT OUTER JOIN Appeal ON Bans.BanID = Appeal.BanID;");
		while(rs.next()) {
			int appealID				= rs.getInt("appealID");
			String iGN					= rs.getString("IGN");
			Date receiveDate			= rs.getDate("receiveDate");
			String message				= rs.getString("message");
			int approval				= rs.getInt("approval");
			Date dateApproved			= rs.getDate("dateApproved");
			int banID					= rs.getInt("banID");
			
			appeals.add(new AppealModel(appealID,iGN, receiveDate, message, approval, dateApproved, banID));
		}
		return appeals;
	}
	
	public ArrayList<AppealModel> getAppealsForUser(String ign) throws SQLException{
		ArrayList<AppealModel> appeals = new ArrayList<AppealModel>();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Appeal WHERE IGN = ? ");
		ps.setString(1, SearchSanitizer.sanitise(ign));
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int appealID				= rs.getInt("appealID");
			String iGN					= rs.getString("IGN");
			Date receiveDate			= rs.getDate("receiveDate");
			String message				= rs.getString("message");
			int approval				= rs.getInt("approval");
			Date dateApproved			= rs.getDate("dateApproved");
			int banID					= rs.getInt("banID");
			
			appeals.add(new AppealModel(appealID,iGN, receiveDate, message, approval, dateApproved, banID));
		}
		return appeals;
	}
	
	public AppealModel getAppealsByBanID(int id) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Appeal WHERE banID = ? ");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int appealID				= rs.getInt("appealID");
			String iGN					= rs.getString("IGN");
			Date receiveDate			= rs.getDate("receiveDate");
			String message				= rs.getString("message");
			int approval				= rs.getInt("approval");
			Date dateApproved			= rs.getDate("dateApproved");
			int banID					= rs.getInt("banID");
			
			return new AppealModel(appealID,iGN, receiveDate, message, approval, dateApproved, banID);
		}
		return null;
	}
	
	public AppealModel getAppealsByAppealID(int id) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Appeal WHERE AppealID = ? ");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int appealID				= rs.getInt("appealID");
			String iGN					= rs.getString("IGN");
			Date receiveDate			= rs.getDate("receiveDate");
			String message				= rs.getString("message");
			int approval				= rs.getInt("approval");
			Date dateApproved			= rs.getDate("dateApproved");
			int banID					= rs.getInt("banID");
			
			return new AppealModel(appealID,iGN, receiveDate, message, approval, dateApproved, banID);
		}
		return null;
	}
	
	public void addAppeal(AppealModel appeal) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("INSERT INTO Appeal (IGN, receiveDate, message, approval, banID) values (?,?,?,?,?)");
		ps.setString(1, appeal.getiGN());
		ps.setDate(2, appeal.getReceiveDate());
		ps.setString(3, appeal.getMessage());
		ps.setInt(4, appeal.getApproval());
		ps.setInt(5, appeal.getBanID());
		
		ps.executeUpdate();
	}
	
	public void updateAppeal(String sql, AppealModel aM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setDate(1, aM.getReceiveDate());
		ppstmt.setString(2, aM.getMessage());
		ppstmt.setInt(3, aM.getApproval());
		ppstmt.setDate(4, aM.getDateApproved());

		executeUpdate(ppstmt);
	}
	
	public void verdictOnAppeal(int appealID, int verdict) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE appeal SET approval = ?, dateApproved = ? WHERE appealID = ?");
		ppstmt.setInt(1, verdict);
		ppstmt.setDate(2, new Date(System.currentTimeMillis()));
		ppstmt.setInt(3, appealID);
		ppstmt.executeUpdate();
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
	
	public ArrayList<BansModel> getBansForUser(String ign) throws SQLException{
		ArrayList<BansModel> toRet = new ArrayList<BansModel>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT BanID, IGN, startDate, endDate, reason, admin, pardoned FROM Bans WHERE IGN = ?");
		ps.setString(1, ign);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			int banID					= rs.getInt("BanID");
			String iGN					= rs.getString("IGN");
			Date startDate				= rs.getDate("startDate");
			Date endDate				= rs.getDate("endDate");
			String reason				= rs.getString("reason");
			String admin				= rs.getString("admin");
			boolean pardoned			= rs.getBoolean("pardoned");
			toRet.add(new BansModel(banID, iGN, startDate, endDate, reason, admin, pardoned));
		}
		return toRet;
	}
	
	public BansModel getBansByID(int bID) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("SELECT BanID, IGN, startDate, endDate, reason, admin, pardoned FROM Bans WHERE banID = ?");
		ps.setInt(1, bID);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			int banID					= rs.getInt("BanID");
			String iGN					= rs.getString("IGN");
			Date startDate				= rs.getDate("startDate");
			Date endDate				= rs.getDate("endDate");
			String reason				= rs.getString("reason");
			String admin				= rs.getString("admin");
			boolean pardoned			= rs.getBoolean("pardoned");
			return new BansModel(banID, iGN, startDate, endDate, reason, admin, pardoned);
		}
		return null;
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
	
	public ArrayList<ReportModel> getReportsFromOneUser(String ign) throws SQLException{
		ArrayList<ReportModel> reports = new ArrayList<ReportModel>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT reportID, IGNSend, IGNReceive, evidenceType, Date, Evidence, reason, guiltyOrNot"
				+ " FROM Report"
				+ " WHERE IGNSend = ?");
		ps.setString(1, ign);
		ResultSet rs = ps.executeQuery();
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
	
	public void addReport(ReportModel report) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("INSERT INTO report (IGNSend, IGNReceive, evidenceType, Date, Evidence, reason, guiltyOrNot) values(?,?,?,?,?,?,?)");
		ps.setString(1, report.getiGNSend());
		ps.setString(2, report.getiGNReceive());
		ps.setString(3, report.getEvidenceType());
		ps.setDate(4, report.getDate());
		ps.setInt(5, report.getEvidence());
		ps.setString(6, report.getReason());
		ps.setInt(7, report.isGuiltyOrNot());
		
		ps.executeUpdate();
	}

	public void convictUser(boolean permanent, int reportID, String admin) throws SQLException{
			long maxTime = (long)21459167 * (long)1000000;
			
			ReportSearchObject rso = new ReportSearchObject();
			rso.setReportID(reportID);
			
			ArrayList<ReportModel> rms = this.getManyReports(rso);
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
	
	public void ignoreUser(String iGN) throws SQLException{
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE Bans SET pardoned='false' WHERE IGN = ?");
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
			String description = rs.getString("description");
			
			
			comments.add(new CommentModel(commentId, postId, d, ign, description));
		}
		return comments;
	}
	
	public void updateComment(String sql, CommentModel cM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setInt(1, cM.getPostID());
		ppstmt.setDate(2, cM.getDate());
		ppstmt.setString(3, cM.getiGN());
		ppstmt.setString(4, cM.getDescription());

		executeUpdate(ppstmt);
	}
	
	public void addComment(CommentModel c) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Comment(postID, date, iGN, description) VALUES (?,?,?,?); ");
		ppstmt.setInt(1, c.getPostID());
		ppstmt.setDate(2, c.getDate());
		ppstmt.setString(3, c.getiGN());
		ppstmt.setString(4, c.getDescription());
	
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
		ppstmt.setTimestamp(2, dM.getDonationDate());
		ppstmt.setBigDecimal(3, dM.getDonationAmount());
		ppstmt.setString(4, dM.getOnBehalf());

		executeUpdate(ppstmt);
	}
	
	public ArrayList<DonationModel> getDonationModel(DonationSearchObject dm) throws SQLException{
		ArrayList<DonationModel> donations = new ArrayList<DonationModel>();
		ResultSet rs = getResultSet(dm.getExecutableSQL());
		while(rs.next()) {
			int donationID				= rs.getInt("DonationID");
			String iGN					= rs.getString("IGN");
			Timestamp donationDate		= rs.getTimestamp("donationDate");
			BigDecimal donationAmount	= rs.getBigDecimal("donationamount");
			String onBehalf				= rs.getString("onBehalf");
			
			donations.add(new DonationModel(donationID, iGN, donationDate, donationAmount, onBehalf));
		}
		return donations;
	}
	
	//EventModel
	public ArrayList<EventModel> getEventModelForEventPage() throws SQLException, UnsupportedEncodingException {
		ArrayList<EventModel> alem = new ArrayList<EventModel>();
		ResultSet rs = getResultSet("SELECT EventID, EventName, IGN, Thumbnail, Description, Date, PostalCode, Venue, Max_No_People, Guest, Status FROM Event;");
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
			String status		= rs.getString("Status");
			
			alem.add(new EventModel(eventID, eventName, iGN, thumbnail, description, date, postalCode, venue, max_No_People, guest, status));
		}
		return alem;
	}
	// Get eventID from eventName
	public int getEventIDFromEventName(String eventName) throws SQLException, UnsupportedEncodingException {
		PreparedStatement ps = conn.prepareStatement("SELECT EventID FROM Event WHERE EventName = ?;");
		ps.setString(1, eventName);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int eventID = rs.getInt("EventID");
		
		return eventID;
	}
	
	// Get eventName from eventID
	public String getEventNameFromEventID(int eventID) throws SQLException, UnsupportedEncodingException {
		PreparedStatement ps = conn.prepareStatement("SELECT EventName FROM Event WHERE EventID = ?;");
		ps.setInt(1, eventID);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		String eventName = rs.getString("EventName");
		
		return eventName;
	}
	
	// Get eventID from eventName
	public boolean isOwner(int eventID, String iGN) throws SQLException, UnsupportedEncodingException {
		PreparedStatement ps = conn.prepareStatement("SELECT iGN FROM Event WHERE EventID = ?;");
		ps.setInt(1, eventID);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		String owner = rs.getString("IGN");
		
		if(iGN.equals(owner)) {
			return true;
		}else {
			return false;
		}
	}
	
	// Set the Event status
	public void setEventStatus(String eventName, String status) throws SQLException, UnsupportedEncodingException {
		PreparedStatement ps = conn.prepareStatement("UPDATE Event SET Status = ? WHERE EventName = ?;");
		ps.setString(1, status);
		ps.setString(2, eventName);
		
		executeUpdate(ps);
	}
	
	//For EventofEventPage
	public EventModel getEventofEventPage(String nameOfEvent) throws SQLException, UnsupportedEncodingException {
		PreparedStatement ps = conn.prepareStatement("SELECT EventName, IGN, Thumbnail, Description, Date, PostalCode, Venue, Guest, FileList, Status FROM Event WHERE EventName = ?;");
		ps.setString(1, nameOfEvent);
		
		ResultSet rs = ps.executeQuery();
		EventModel eM = new EventModel();
		while(rs.next()) {
			String eventName	= rs.getString("EventName");
			String iGN			= rs.getString("IGN");
			int thumbnail		= rs.getInt("Thumbnail");
			String description	= rs.getString("Description");
			Timestamp date		= rs.getTimestamp("Date");
			String postalCode	= rs.getString("PostalCode");
			String venue		= rs.getString("Venue");
			String guest		= rs.getString("Guest");
			String fileList		= rs.getString("FileList");
			String status		= rs.getString("Status");
			
			eM = new EventModel(eventName, iGN, thumbnail, description, date, postalCode, venue, guest, fileList, status);
		}
		return eM;
	}
		
	//Get guest's profile picture
	public String getUserProfilePic(String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT ProfilePic FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, iGN);
		ResultSet rs = ppstmt.executeQuery();
		String fileName = null;
		while(rs.next()) {
			int profilePic = rs.getInt("ProfilePic");
			if(profilePic == 0) {
				break;
			}
			PreparedStatement ppstmt2 = conn.prepareStatement("SELECT ImageName FROM ImageTable WHERE ImageID = ?;");
			ppstmt2.setInt(1, profilePic);
			ResultSet rs2 = ppstmt2.executeQuery();
			while(rs2.next()) {
				fileName = rs2.getString("ImageName");
			}
		}
		return fileName;
	}
	
	//Get IGN's priviledge
	public boolean getUserPriviledge(String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT isPriviledged FROM DatabaseUser WHERE IGN = ?;");
		ppstmt.setString(1, iGN);
		
		boolean isPriviledged = false;
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			isPriviledged = rs.getBoolean("isPriviledged");
			
		}
		return isPriviledged;
	}
		
	//For MyEvent
	public ArrayList<EventModel> getEventModelForMyEventPage() throws SQLException, UnsupportedEncodingException {
		ArrayList<EventModel> alem = new ArrayList<EventModel>();
		ResultSet rs = getResultSet("SELECT EventID, EventName, IGN, Thumbnail, Description, Date, PostalCode, Venue, Max_No_People, Guest, Status FROM Event ORDER BY Date;");
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
			String status		= rs.getString("Status");
			
			alem.add(new EventModel(eventID, eventName, iGN, thumbnail, description, date, postalCode, venue, max_No_People, guest, status));
		}
		return alem;
	}
	
	//CreateEvent
	public void insertCreateEvent(EventModel eM) throws SQLException, UnsupportedEncodingException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Event(EventName, IGN, Thumbnail, Description, Date, PostalCode, Venue, AutoAccept, Max_No_People, Guest, FileList, Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
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
		ppstmt.setString(12, eM.getStatus());

		ArrayList<String> als = eM.getGuestArray();
		for(String s: als) {
			PreparedStatement ppstmt1 = conn.prepareStatement("INSET INTO PeopleEventConfirmList(EventID, IGN, Confirmed) VALUES(?,?,?);");
			ppstmt1.setInt(1, eM.getEventID());
			ppstmt1.setString(2, s);
			ppstmt1.setBoolean(3, false);
			
			executeUpdate(ppstmt1);
		}
		
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

		executeUpdate(ppstmt);
	}
	
	//Insert into FileTable
	public void insertFileTable(FileTableModel fTM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO FileTable(FileName, Data) VALUES (?,?);");
		ppstmt.setString(1, fTM.getFileName());
		ppstmt.setBytes(2, fTM.getData());

		executeUpdate(ppstmt);
	}
	
	public void insertSubscription(SubscriptionModel sm) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO Subscription(IGN, Subscriber) VALUES (?,?);");
		ppstmt.setString(1, sm.getIGN());
		ppstmt.setString(2, sm.getSubs());
		executeUpdate(ppstmt);
	}
	
	public void deleteSubscription(SubscriptionModel sm) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("DELETE FROM Subscription WHERE IGN = ? AND Subscriber = ? ;");
		ppstmt.setString(1, sm.getIGN());
		ppstmt.setString(2, sm.getSubs());
		executeUpdate(ppstmt);
	}
	
	public ArrayList<SubscriptionModel> getSubscriptionModel() throws SQLException {
		ArrayList<SubscriptionModel> g = new ArrayList<SubscriptionModel>();
		PreparedStatement ppstmt = conn.prepareStatement("SELECT * FROM Subscription;");
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			SubscriptionModel p = new SubscriptionModel();
			String gn = rs.getString("IGN");
			String subs = rs.getString("Subscriber");
			p.setIGN(gn);
			p.setSubs(subs);
			g.add(p);
			
		}
		return g;
	}
	
	public int getFileCount() throws SQLException{
		PreparedStatement ppstmt = conn.prepareStatement("SELECT MAX(FileID) as maxCount from FileTable;");
		ResultSet rs = ppstmt.executeQuery();
		rs.next();
		int count	= rs.getInt("maxCount");
		return count;
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
	//Get All File From File Table
	public ArrayList<Integer>  getFileID() throws SQLException {
		ArrayList<Integer> ft = new ArrayList<Integer>();
		ResultSet rs = getResultSet("SELECT FileID FROM FileTable");
		while(rs.next()) {
			int FileId = rs.getInt("FileID");

			ft.add(FileId);
		}
		return ft;
	}
	
	//Get FileTable by fileName
	public FileTableModel getFileTableByFileName(String name) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("SELECT * FROM FileTable WHERE FileName = ?;");
		ppstmt.setString(1, name);
		
		FileTableModel fTM = new FileTableModel();
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			int fileID	= rs.getInt("FileID");
			String fileName	= rs.getString("FileName");
			byte[] data	= rs.getBytes("Data");
			
			fTM = new FileTableModel(fileID, fileName, data);
		}
		return fTM;
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
			
			return new FileTableModel(fileID, fileName, data);
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
	public void updateFoodPreferences(String sql, FoodPreferencesModel fP) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setString(1, fP.getiGN());
		ppstmt.setString(2, fP.getFoodPref());

		executeUpdate(ppstmt);
	}
	
	//ForumPostModel
	public int getVotes(int postID) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Upvotes FROM ForumPost WHERE PostID = ?;");
		ppstmt.setInt(1, postID);
		ResultSet rs = ppstmt.executeQuery();
		rs.next();
		int postid1	= rs.getInt("Upvotes");
		return postid1;
	}
	
	public void updateVotes(int votesnum, int postID) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE ForumPost SET Upvotes = ? WHERE PostID = ?;");
		ppstmt.setInt(1, votesnum);
		ppstmt.setInt(2, postID);
		executeUpdate(ppstmt);
	}
	
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
		ResultSet rs = getResultSet("SELECT * FROM ForumPost ORDER BY PostID DESC;");
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
	
	public ForumPostModel getForumModelByID(int postID) throws SQLException{
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM ForumPost WHERE postID = ?");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int postID1 = rs.getInt("PostID");
			String thread = rs.getString("Thread");
			int upvotes = rs.getInt("Upvotes");
			String iGN = rs.getString("IGN");
			Timestamp date = rs.getTimestamp("Date");
			int picture = rs.getInt("Picture");
			String description = rs.getString("Description");
			String fileAttachment = rs.getString("FileAttachment");
			String text = rs.getString("ForumNormalText");
			String url = rs.getString("ForumURL");
			
			
			return new ForumPostModel(postID1, thread, upvotes, iGN, date, picture, description, fileAttachment, text, url);
		}
		return null;
	}
	
	public String getPersonWithEventID(int eventID) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT IGN FROM Event WHERE EventID = ?;");
		ppstmt.setInt(1, eventID);
		ResultSet rs = ppstmt.executeQuery();
		
		while(rs.next()) {
			return rs.getString("IGN");
		}
		return null;
	}
	
	//ForumVoteModel
	public void addForumVote(ForumVoteModel fP) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement("INSERT INTO ForumVote(PostID, IGN, Date) VALUES (?,?,?); ");
		ppstmt.setInt(1, fP.getPostID());
		ppstmt.setString(2, fP.getiGN());
		ppstmt.setDate(3, fP.getDate());

		executeUpdate(ppstmt);
	}
	
	public ArrayList<ForumVoteModel> getForumVoteModel() throws SQLException{
		ArrayList<ForumVoteModel> votes = new ArrayList<ForumVoteModel>();
		ResultSet rs = getResultSet("SELECT * FROM ForumVote");
		while(rs.next()) {
			int postID = rs.getInt("PostID");
			String iGN = rs.getString("IGN");
			Date date = rs.getDate("Date");
			
			votes.add(new ForumVoteModel(postID, iGN, date));
		}
		return votes;
	}
	
	public boolean getWhetherCanVoteForumVoteModel(String iGN, int postID) throws SQLException{
		PreparedStatement ppstmt = conn.prepareStatement("SELECT * FROM ForumVote WHERE IGN = ? AND POSTID = ?;");
		ppstmt.setString(1, iGN);
		ppstmt.setInt(2, postID);
		ResultSet rs = ppstmt.executeQuery();
		if(rs.next()) {
			return false; // Got duplicate
		}else {
			return true; // Dont have (Can vote)
		}
	}
	
	//PeopleEventListModel
	public void updatePeopleEventList(String sql, PeopleEventListModel pELM) throws SQLException { 
		PreparedStatement ppstmt = conn.prepareStatement(sql);
		ppstmt.setInt(1, pELM.getEventID());
		ppstmt.setString(2, pELM.getInvitationPending());

		executeUpdate(ppstmt);
	}
		
	//Get number of people pending
	public ArrayList<String> getPeopleEventListPending(int eventID) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT InvitationPending FROM PeopleEventList WHERE EventID = ?;");
		ppstmt.setInt(1, eventID);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			PeopleEventListModel pELM = new PeopleEventListModel(0, null);
			pELM.setInvitationPending(rs.getString("InvitationPending"));
			
			return pELM.getInvitationPendingArray();
		}
		return new ArrayList<String>();
	}
	
	public boolean insertPeopleEventConfirmList(int eventID, String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT Count(*) FROM PeopleEventConfirmList WHERE EventID = ?;");
		ppstmt.setInt(1, eventID);
		ppstmt.setString(2, iGN);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			int numberOfCurrentPpt = rs.getInt(1);
			PreparedStatement ppstmt1 = conn.prepareStatement("SELECT Max_No_People FROM Event WHERE EventID = ?;");
			ppstmt1.setInt(1, eventID);
			ResultSet rs1 = ppstmt.executeQuery();
			if(numberOfCurrentPpt >= rs1.getInt("Max_No_People")) {
				return false;
			}
		}
		PreparedStatement ppstmt1 = conn.prepareStatement("INSERT INTO PeopleEventConfirmList(EventID, IGN, Confirmed) VALUES (?,?,'0');");
		ppstmt1.setInt(1, eventID);
		ppstmt1.setString(2, iGN);
		
		executeUpdate(ppstmt);
		return true;
	}
	
	public void deletePeopleEventConfirmList(int eventID, String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("DELETE FROM PeopleEventConfirmList WHERE EventID = ? AND IGN  = ?;");
		ppstmt.setInt(1, eventID);
		ppstmt.setString(2, iGN);
		
		executeUpdate(ppstmt);
	}
	
	//Get number of people confirmed
	public ArrayList<String[]> getPeopleEventListConfirm(int eventID) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT IGN, Confirmed FROM PeopleEventConfirmList WHERE EventID = ?;");
		ppstmt.setInt(1, eventID);
		ResultSet rs = ppstmt.executeQuery();
		
		ArrayList<String[]> als = new ArrayList<String[]>();
		while(rs.next()) {
			String iGN = rs.getString("IGN");
			boolean confirmed = rs.getBoolean("Confirmed");
			String[] array = {iGN, String.valueOf(confirmed)};
			als.add(array);
		}
		return als;
	}
	
	// Whether IGN is in confirm list
	public String getWhetherPeopleEventList(int eventID, String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("SELECT IGN FROM PeopleEventConfirmList WHERE EventID = ?;");
		ppstmt.setInt(1, eventID);
		
		ResultSet rs = ppstmt.executeQuery();
		while(rs.next()) {
			if(iGN.equals(rs.getString("IGN"))) {
				return "C";
			}
		}
		
		ppstmt = conn.prepareStatement("SELECT InvitationPending FROM PeopleEventList WHERE EventID = ?;");
		ppstmt.setInt(1, eventID);
		
		rs = ppstmt.executeQuery();
		while(rs.next()) {
			PeopleEventListModel pELM = new PeopleEventListModel(0, null);
			pELM.setInvitationPending(rs.getString("InvitationPending"));
			ArrayList<String> als = pELM.getInvitationPendingArray();
			
			for(String s: als) {
				if(iGN.equals(s)) {
					return "P";
				}
			}
		}
		return "N";
	}
	
	// Whether IGN is in confirm list
		public void addParticipationPoints(int eventID) throws SQLException {
			PreparedStatement ppstmt = conn.prepareStatement("SELECT IGN FROM PeopleEventConfirmList WHERE EventID = ? AND Confirmed = 'true';");
			ppstmt.setInt(1, eventID);
			
			ResultSet rs = ppstmt.executeQuery();
			while(rs.next()) {
				PreparedStatement ppstmt1 = conn.prepareStatement("SELECT Points FROM Database WHERE IGN = ?;");
				ppstmt1.setString(1, rs.getString("IGN"));
				PreparedStatement ppstmt2 = conn.prepareStatement("UPDATE Database SET Points = ? WHERE iGN = ?;");
				ppstmt2.setInt(1, (rs.getInt("Points") + 10));
				ppstmt2.setString(2, rs.getString("IGN"));
				executeUpdate(ppstmt2);
			}
		}
		
	// Set people in confirm list "Confirmed"
	public void setPeopleEventListConfirmConfirmed(int eventID, String iGN) throws SQLException {
		PreparedStatement ppstmt = conn.prepareStatement("UPDATE PeopleEventConfirmList SET Confirmed = '1' WHERE EventID = ? AND IGN = ?;");
		ppstmt.setInt(1, eventID);
		ppstmt.setString(2, iGN);
		executeUpdate(ppstmt);
	}
	
	public void close() throws SQLException {
		conn.close();
	}
	
	
	public static void main(String[] arg0) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException{
		Database db = new Database(2);
		SubscriptionModel sss = new SubscriptionModel();
		sss.setIGN("lalalala");
		sss.setSubs("paowkfjfooooooooooooooooooooo");
		db.deleteSubscription(sss);
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
