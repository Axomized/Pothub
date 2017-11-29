package database.model;

public class LoginModel {
	String email;
	String password;
	String salt;
	
	public LoginModel(DatabaseUserModel dUM, String password, String salt) {
		super();
		this.email = dUM.getEmail();
		this.password = password;
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
