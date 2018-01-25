package profile;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.model.DatabaseUserModel;
import database.model.FoodPreferencesModel;

public class FoodPrefFilter {
	
	public String getFilterForumQuery(String name) {
		String filterQuery = "SELECT * FROM ForumPost ORDER BY";
		try {
			Database db = new Database(0);
			DatabaseUserModel dum = db.getUserProfile(name);
			ArrayList<FoodPreferencesModel> foodPrefList = db.getFoodPref(name);
			if (dum.isFiltered()) {
				for (FoodPreferencesModel fp : foodPrefList) {
					filterQuery += " CASE WHEN UPPER(Thread) LIKE UPPER('%" + fp.getFoodPref() + "%') THEN 1 ELSE 0 END, CASE WHEN UPPER(Description) LIKE UPPER('%" + fp.getFoodPref() + "%') THEN 1 ELSE 0 END,";
				}
			}
			
			filterQuery += " PostID DESC;";
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(filterQuery);
		return filterQuery;
	}

	public static void main(String[] args) {
		FoodPrefFilter fp = new FoodPrefFilter();
		String filterQuery = fp.getFilterForumQuery("GordonRamsey");
		System.out.println(filterQuery);
	}

}
