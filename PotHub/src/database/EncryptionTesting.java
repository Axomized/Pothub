package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import database.model.ShoppingLoginModel;

public class EncryptionTesting {
	private ArrayList<ShoppingLoginModel> clearTextArray = new ArrayList<>();
	
	public EncryptionTesting() throws FileNotFoundException{
	    ArrayList<String> scannerArray = new ArrayList<>();
	    AES aes = null;
	    
	    File file = new File("src/database/TomatoFactoryPlans.txt");
	    Scanner sc = new Scanner(file);
	    while(sc.hasNextLine()){
	    	String line = sc.nextLine();
	    	scannerArray.add(line);
	    }
	    
	    for(int i = 0; i < scannerArray.size(); i++){
	    	if(i == 0){
	    		aes = new AES(scannerArray.get(0));
	    	}else{
	    		Scanner sc1 = new Scanner(scannerArray.get(i));
	    		sc1.useDelimiter("~");
	    		String username = aes.decrypt(sc1.next());
	    		String password = aes.decrypt(sc1.next());
	    		clearTextArray.add(new ShoppingLoginModel(username,password));
	    		sc1.close();
	    	}
	    }
	    
	    /*
	    for(ShoppingLoginModel lm:clearTextArray){
	    	 System.out.println("Decrypted Login: " + aes.decrypt(lm.getLogin()));
	    	 System.out.println("Decrypted Password: " + aes.decrypt(lm.getPassword()) + "\n");
	    }
	    */
	    
	    sc.close();
	}

	protected ArrayList<ShoppingLoginModel> getArray(){
		return clearTextArray;
	}
}
