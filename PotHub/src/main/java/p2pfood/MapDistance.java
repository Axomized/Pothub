package p2pfood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapDistance {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static ArrayList<String> getJsonFromURL(String url) throws JSONException, IOException{
	    JSONObject json = readJsonFromUrl(url);
	    JSONArray array = json.getJSONArray("rows");

	    JSONObject json1 = array.getJSONObject(0);
	    JSONArray array1 = json1.getJSONArray("elements");
	    
	    ArrayList<String> toRet = new ArrayList<String>();
	    
	    for(int i =0; i<array1.length();i++){
	    	try{
	    	toRet.add(array1.getJSONObject(i).getJSONObject("distance").getString("text"));
	    	}
	    	catch(JSONException j ){
	    		toRet.add("N.A");
	    	}
	    }
	    
	    return toRet;
  }
  
  public static String mapURLBuilder(ArrayList<String> pcodes, String origin){
		String connectionURL= "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=Singapore,%20"+origin+"&destinations=";
		boolean notFirst=false;
		
		for(String pcode:pcodes){
			if(notFirst){
				connectionURL+="%7c";
			}
			connectionURL+="Singapore,%20"+pcode;

			notFirst=true;
		}
				connectionURL+="&key=AIzaSyDp7sh0cctb7uFWpCaCFUO7-27GMy2uJJ8";
		return connectionURL;
  }
}