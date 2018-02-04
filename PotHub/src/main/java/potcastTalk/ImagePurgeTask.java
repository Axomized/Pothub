package potcastTalk;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import database.Database;

public class ImagePurgeTask extends TimerTask {
	public ImagePurgeTask(){
	}

    @Override
    public void run() {
    	try {
			completeTask();
		} catch (FileNotFoundException | ClassNotFoundException | MessagingException | SQLException e) {
			e.printStackTrace();
		}
    }

    private void completeTask() throws AddressException, MessagingException, FileNotFoundException, ClassNotFoundException, SQLException {
       Database db = new Database(2);
       db.purgeUnusedImages();
    }
}