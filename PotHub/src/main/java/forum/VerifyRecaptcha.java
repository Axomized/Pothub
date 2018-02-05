package forum;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL; 
import javax.json.Json; 
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
public class VerifyRecaptcha {
	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	//for localhost: 6LdoEUIUAAAAANJh8x4SYiVGri487rpzVwyFT5hj
	// for web : 6LdFc0QUAAAAAN6ghzTQJAv4JXr4JzaONyj69tRT
	public static final String secret = "6LdFc0QUAAAAAN6ghzTQJAv4JXr4JzaONyj69tRT";
	private final static String USER_AGENT = "Mozilla/5.0";
	public static boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}
		
		try{
		URL obj = new URL(url);//creating url object
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();//send http post request to get contents from the website

		// add reuqest header
		con.setRequestMethod("POST");//define the method
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String postParams = "secret=" + secret + "&response="
				+ gRecaptchaResponse;//setting the url parameter before sending

		// Send post request
		con.setDoOutput(true);//using url connection for output
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());//getting output from google
		wr.writeBytes(postParams);
		wr.flush();//forces everything out and clear the buffer
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {//writing data from inputstream to string
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		
		//parse JSON response and return 'success' value
		JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		
		return jsonObject.getBoolean("success");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}