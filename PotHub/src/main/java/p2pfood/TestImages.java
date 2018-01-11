package p2pfood;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import org.apache.commons.compress.utils.IOUtils;

import database.Database;

public class TestImages {
	public static void main(String[] arg0) throws ClassNotFoundException, SQLException, IOException{
		Database db = new Database(2);
		File file = new File("C:\\Users\\Matt\\Desktop\\magikarp.jpg");
		InputStream in = new FileInputStream(file);
		String fileName = file.getName();
		byte[] fileData = IOUtils.toByteArray(in);
		float fileSize = file.length();
		
		System.out.println(fileName);
		System.out.println(fileData);
		System.out.println(fileSize);
		
		try {
			System.out.println(db.addPictureWithDupeCheck(fileName, fileData));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
