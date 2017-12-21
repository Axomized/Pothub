package adminSearch;

public class SearchSanitizer {
	public static String sanitise(String input){
		return input.replaceAll("[^A-Za-z0-9\\s]", "");
	}
}
