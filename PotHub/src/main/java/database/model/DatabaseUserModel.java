package database.model;

import java.sql.Timestamp;

public class DatabaseUserModel {
	String email;
	String iGN;
	String contact_No;
	char gender;
	String address;
	Timestamp lastLogin;
	Timestamp joinDate;

	public DatabaseUserModel(String email, String iGN, String contact_No, char gender, String address,
			Timestamp lastLogin, Timestamp joinDate) {
		super();
		this.email = email;
		this.iGN = iGN;
		this.contact_No = contact_No;
		this.gender = gender;
		this.address = address;
		this.lastLogin = lastLogin;
		this.joinDate = joinDate;
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

	public Timestamp getLastLogin() {
		return lastLogin;
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

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
}
