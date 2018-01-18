package event.leaderboard.model;

public class WebMessage {
	String messageType; //Retrieve, Push
	String userToDisplay;
	
	public WebMessage() {
	}

	public WebMessage(String messageType) {
		this.messageType = messageType;
	}

	public WebMessage(String messageType, String userToDisplay) {
		this.messageType = messageType;
		this.userToDisplay = userToDisplay;
	}

	public String getMessageType() {
		return messageType;
	}

	public String getUserToDisplay() {
		return userToDisplay;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public void setUserToDisplay(String userToDisplay) {
		this.userToDisplay = userToDisplay;
	}
}
