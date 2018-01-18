package database.model;

public class ForgetPasswordModel {
	String newPassword;
	String body;
	
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
