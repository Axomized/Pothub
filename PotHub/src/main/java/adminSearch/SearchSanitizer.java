package adminSearch;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SearchSanitizer {
	public static String sanitise(String input){
		try {
			return URLDecoder.decode(input.replace("+", " "), "UTF-8").replaceAll("[^A-Za-z0-9\\s]", "");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static String trimTo(String input, int length){
		if(input.length()>length){
			return input.substring(0, length);
		}
		else{
			return input;
		}
	}
}
