package database.model;

import java.sql.Timestamp;

public class DatabaseUserModel {
	String email;
	String iGN;
	String contact_No;
	char gender;
	String address;
	int profilePic;
	Timestamp lastLogin;
	Timestamp joinDate;
	int cookingRank;
	int points;
	double totalDonation;
	boolean isPriviledged;
	
	public DatabaseUserModel(String email, String iGN, String contact_No, char gender, String address,
			Timestamp joinDate, int cookingRank, int points, double totalDonation, boolean isPriviledged) {
		this.email = email;
		this.iGN = iGN;
		this.contact_No = contact_No;
		this.gender = gender;
		this.address = address;
		this.joinDate = joinDate;
		this.cookingRank = cookingRank;
		this.points = points;
		this.totalDonation = totalDonation;
		this.isPriviledged = isPriviledged;
	}

	public DatabaseUserModel(String email, String iGN, String contact_No, char gender, String address, int profilePic,
			Timestamp joinDate, int cookingRank, int points, double totalDonation, boolean isPriviledged) {
		this.email = email;
		this.iGN = iGN;
		this.contact_No = contact_No;
		this.gender = gender;
		this.address = address;
		this.profilePic = profilePic;
		this.joinDate = joinDate;
		this.cookingRank = cookingRank;
		this.points = points;
		this.totalDonation = totalDonation;
		this.isPriviledged = isPriviledged;
	}

	public DatabaseUserModel(String email, String iGN, String contact_No, char gender, String address,
			Timestamp lastLogin, Timestamp joinDate, int cookingRank, int points, double totalDonation,
			boolean isPriviledged) {
		this.email = email;
		this.iGN = iGN;
		this.contact_No = contact_No;
		this.gender = gender;
		this.address = address;
		this.lastLogin = lastLogin;
		this.joinDate = joinDate;
		this.cookingRank = cookingRank;
		this.points = points;
		this.totalDonation = totalDonation;
		this.isPriviledged = isPriviledged;
	}


	public DatabaseUserModel(String email, String iGN, String contact_No, char gender, String address, int profilePic,
			Timestamp lastLogin, Timestamp joinDate, int cookingRank, int points, double totalDonation,
			boolean isPriviledged) {
		this.email = email;
		this.iGN = iGN;
		this.contact_No = contact_No;
		this.gender = gender;
		this.address = address;
		this.profilePic = profilePic;
		this.lastLogin = lastLogin;
		this.joinDate = joinDate;
		this.cookingRank = cookingRank;
		this.points = points;
		this.totalDonation = totalDonation;
		this.isPriviledged = isPriviledged;
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

	public String getAddress() {
		return address;
	}

	public int getProfilePic() {
		return profilePic;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public int getCookingRank() {
		return cookingRank;
	}

	public int getPoints() {
		return points;
	}

	public double getTotalDonation() {
		return totalDonation;
	}

	public boolean isPriviledged() {
		return isPriviledged;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public void setProfilePic(int profilePic) {
		this.profilePic = profilePic;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	public void setCookingRank(int cookingRank) {
		this.cookingRank = cookingRank;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setTotalDonation(double totalDonation) {
		this.totalDonation = totalDonation;
	}

	public void setPriviledged(boolean isPriviledged) {
		this.isPriviledged = isPriviledged;
	}
}
