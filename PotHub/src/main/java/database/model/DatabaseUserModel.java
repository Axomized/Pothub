package database.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DatabaseUserModel {
	private String email;
	private String iGN;
	private String contact_No;
	private char gender;
	private String bio;
	private String address;
	private String unitNo;
	private int profilePic;
	private Date lastLogin;
	private Date joinDate;
	private int cookingRank;
	private int points;
	private BigDecimal totalDonation;
	private boolean isPriviledged;
	private int userPermission;
	
	public DatabaseUserModel() {
		
	}
	
	//For login page
	public DatabaseUserModel (String iGN) {
		this.iGN = iGN;
	}
	
	public DatabaseUserModel(String email, String iGN, String contact_No, char gender, String bio, String address, String unitNo,
			int profilePic, Date lastLogin, Date joinDate, int cookingRank, int points, BigDecimal totalDonation,
			boolean isPriviledged, int userPermission) {
		this.email = email;
		this.iGN = iGN;
		this.contact_No = contact_No;
		this.gender = gender;
		this.bio = bio;
		this.address = address;
		this.unitNo = unitNo;
		this.profilePic = profilePic;
		this.lastLogin = lastLogin;
		this.joinDate = joinDate;
		this.cookingRank = cookingRank;
		this.points = points;
		this.totalDonation = totalDonation;
		this.isPriviledged = isPriviledged;
		this.userPermission = userPermission;
	}
	
	//For profile page
	public DatabaseUserModel(String email, String contact_No, char gender, String bio, String address, String unitNo, 
			int profilePic, Date joinDate, int cookingRank, int points, BigDecimal totalDonation, boolean isPriviledged) {
		this.email = email;
		this.contact_No = contact_No;
		this.gender = gender;
		this.bio = bio;
		this.address = address;
		this.unitNo = unitNo;
		this.profilePic = profilePic;
		this.joinDate = joinDate;
		this.cookingRank = cookingRank;
		this.points = points;
		this.totalDonation = totalDonation;
		this.isPriviledged = isPriviledged;
	}
	
	//For event page
	public DatabaseUserModel(int profilePic, int cookingRank, int points) {
		this.profilePic = profilePic;
		this.cookingRank = cookingRank;
		this.points = points;
	}
	
	//For Registration Page
	public DatabaseUserModel(String email, String iGN){
		this.email = email;
		this.iGN = iGN;
	}

	public String getEmail() {
		return email;
	}

	public String getiGN() {
		return iGN;
	}

	public String getContact_No() {
		return contact_No;
	}

	public char getGender() {
		return gender;
	}

	public String getBio() {
		return bio;
	}

	public String getAddress() {
		return address;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public int getProfilePic() {
		return profilePic;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public int getCookingRank() {
		return cookingRank;
	}

	public int getPoints() {
		return points;
	}

	public BigDecimal getTotalDonation() {
		return totalDonation;
	}

	public boolean isPriviledged() {
		return isPriviledged;
	}

	public int getUserPermission() {
		return userPermission;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public void setContact_No(String contact_No) {
		this.contact_No = contact_No;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public void setProfilePic(int profilePic) {
		this.profilePic = profilePic;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public void setCookingRank(int cookingRank) {
		this.cookingRank = cookingRank;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setTotalDonation(BigDecimal totalDonation) {
		this.totalDonation = totalDonation;
	}

	public void setPriviledged(boolean isPriviledged) {
		this.isPriviledged = isPriviledged;
	}

	public void setUserPermission(int userPermission) {
		this.userPermission = userPermission;
	}
	
	public String dateFormat(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}
}
