package database.model;

public class LoginModel {
	String email;
	String password;
	String salt;

	public LoginModel(String email, String password, String salt) {
		this.email = email;
		this.password = password;
		this.salt = salt;
	}
	
	//For Registration Page
	public LoginModel(String email){
		this.email = email;
	}
	
	public LoginModel() {
		
	}

	public LoginModel(String password, String salt) {
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
