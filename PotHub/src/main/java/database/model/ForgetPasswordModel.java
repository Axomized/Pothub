package database.model;

public class ForgetPasswordModel {
	private String newPassword;
	private String body;
	
	public String getNewPassword(){
		return newPassword;
	}
	
	public String getBody(){
		return body;
	}
	
	public void setNewPassword(String newPassword){
		this.newPassword = newPassword;
	}
	
	public void setBody(String body){
		this.body = body;
	}

}
