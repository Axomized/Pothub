package database.model;

public class LoginModel {
	private String email;
	private String password;
	private String salt;
	private boolean passwordResetted;
	private int count;

	public LoginModel() {}

	//For Registration Page
	public LoginModel(int count){
		this.count = count;
	}
		
	public LoginModel(String password, String salt) {
		this.password = password;
		this.salt = salt;
	}

	public LoginModel(String email, String password, String salt) {
		this.email = email;
		this.password = password;
		this.salt = salt;
	}

	public LoginModel(String email, String password, String salt, boolean passwordResetted) {
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.passwordResetted = passwordResetted;
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

	public boolean isPasswordResetted() {
		return passwordResetted;
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

	public void setPasswordResetted(boolean passwordResetted) {
		this.passwordResetted = passwordResetted;
	}
}
