package login;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import adminSearch.SearchSanitizer;
import database.Database;
import database.model.BansModel;

public class BanChecker {
	public static boolean isThisGuyBanned(String guy){
		try{
		Database db = new Database(0);

		ArrayList<BansModel> bans = db.getBansForUser(SearchSanitizer.sanitise(guy));
		
		for(BansModel ban : bans){
			if(ban.getEndDate().getTime()>System.currentTimeMillis() && !ban.isPardoned()){
				return true;
			}
		}
		}
		
		catch(SQLException e){
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[]args){
		System.out.println(isThisGuyBanned("thegrandma"));
	}
}
