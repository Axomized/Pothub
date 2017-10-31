package database;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.ShoppingLoginModel;

public class Database{
    final String DB_URL="jdbc:sqlserver://localhost:3306;databaseName=PotHub;";
    //final String DB_URL="jdbc:sqlserver://119.74.135.44:3306;databaseName=PotHub;";
    
    Connection conn = null;
    
    public Database(int permission) throws SQLException, FileNotFoundException{
    	EncryptionTesting et = new EncryptionTesting();
    	ArrayList<ShoppingLoginModel> loginModelArray = et.getArray();
    	switch(permission){
		 	case 0:
		 		conn = DriverManager.getConnection(DB_URL, loginModelArray.get(0).getLogin(), loginModelArray.get(0).getPassword());
		 		break;
		 	case 1:
		 		conn = DriverManager.getConnection(DB_URL, loginModelArray.get(1).getLogin(), loginModelArray.get(1).getPassword());
		 		break;
    	}
    }
    
    //public ArrayList<String>
    private ResultSet getResultSet(String sqlline) throws SQLException{
    	PreparedStatement ps = conn.prepareStatement(sqlline);
    	ResultSet rs = ps.executeQuery();
    	return rs;
    }
    
    /*
     * 	private void getFileAttachment(){
	 * 		Database db = new Database(0);
	    	PreparedStatement ps = db.conn.prepareStatement("SELECT * FROM FileTable");
	    	ResultSet rs = ps.executeQuery();
	    	rs.next();
	    	
	    	InputStream c = rs.getBinaryStream(2);
	    	OutputStream outputStream = new FileOutputStream("D:/hello.jpg");
	    	int read = 0;
	    	byte[] bytes = new byte[1024];
	    	while((read = c.read(bytes)) != -1) {
	    		outputStream.write(bytes, 0, read);
	    	}
	    	
	    	c.close();
	    	outputStream.close();
	    	db.conn.close();
       	}
     */
}
