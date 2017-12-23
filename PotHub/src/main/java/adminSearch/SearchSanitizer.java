package adminSearch;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SearchSanitizer {
	public static String sanitise(String input){
		try {
			return URLDecoder.decode(input, "UTF-8").replaceAll("[^A-Za-z0-9\\s]", "");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
