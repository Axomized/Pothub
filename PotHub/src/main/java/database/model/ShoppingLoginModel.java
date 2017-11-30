package database.model;

public class ShoppingLoginModel {
	String login;
	String password;

	public ShoppingLoginModel(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}
