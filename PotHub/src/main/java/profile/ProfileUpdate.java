package profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import database.Database;

public class ProfileUpdate {
	private String iGN;
	private boolean isFiltered;
	private String gender;
	private String contact_No;
	private String bio;
	private String address;
	private String unitNo;
	private String profilePicName;
	private byte[] profilePicByte;
	
	public ProfileUpdate(String iGN) {
		this.iGN = iGN;
	}
	
	public String getiGN() {
		return iGN;
	}

	public void setiGN(String iGN) {
		this.iGN = iGN;
	}

	public boolean isFiltered() {
		return isFiltered;
	}

	public void setFiltered(boolean isFiltered) {
		this.isFiltered = isFiltered;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact_No() {
		return contact_No;
	}

	public void setContact_No(String contact_No) {
		this.contact_No = contact_No;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getProfilePicName() {
		return profilePicName;
	}

	public void setProfilePicName(String profilePicName) {
		this.profilePicName = profilePicName;
	}

	public byte[] getProfilePicByte() {
		return profilePicByte;
	}

	public void setProfilePicByte(byte[] profilePicByte) {
		this.profilePicByte = profilePicByte;
	}

	public String getUpdateQuery() {
		String updateQuery = "UPDATE DatabaseUser SET";
		try {
			Database db = new Database(2);
			
			if (isFiltered) {
				updateQuery += " IsFiltered = '1',";
			}
			else {
				updateQuery += " IsFiltered = '0',";
			}
			
			if (gender != null && !gender.isEmpty()) {
				if (gender.charAt(0) == 'M' || gender.charAt(0) == 'F') {
					updateQuery += " Gender = '" + gender.charAt(0) + "',";
				}
			}
			if (contact_No != null && !contact_No.isEmpty()) {
				updateQuery += " Contact_No = '" + contact_No + "',";
			}
			if (bio != null && !bio.isEmpty()) {
				updateQuery += " Bio = '" + bio + "',";
			}
			if (address != null && !address.isEmpty()) {
				updateQuery += " Address = '" + address + "',";
			}
			if (unitNo != null && !unitNo.isEmpty()) {
				updateQuery += " UnitNo = '" + unitNo + "',";
			}
			if (profilePicName != null && !profilePicName.isEmpty() && profilePicByte != null) {
				updateQuery += " ProfilePic = '" + db.addPictureWithDupeCheck(profilePicName, profilePicByte) + "',";
			}
			
			if (updateQuery.substring(updateQuery.length() -1).equals(",")) {
				updateQuery = updateQuery.substring(0, updateQuery.length() -1);
			}
			updateQuery += " WHERE IGN = '" + iGN + "';";
			System.out.println(updateQuery);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return updateQuery;
	}

	public static void main(String[] args) {
		
	}

}
