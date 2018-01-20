package event.geocoding;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class GoogleGeocoding extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		try {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String line = sCurrentLine;
				if(line.length() >= 6) {
					try {
						Integer.parseInt(line);
						response.getWriter().append("Geocode" + geocoding(line));
					}catch(NumberFormatException e) {
						response.getWriter().append("Reverse" + reverseGeocoding(line));
					}
				}
			}
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
	
	private String geocoding(String postalCode) throws ApiException, InterruptedException, IOException{
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBoS4GIygmCRWDWUDDbheb3B8OqNjGDuL0").build();
		
		GeocodingResult[] results =  GeocodingApi.geocode(context, postalCode).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		LatLng latlng = new LatLng();
		latlng.lat = Double.parseDouble(gson.toJson(results[0].geometry.location.lat));
		latlng.lng = Double.parseDouble(gson.toJson(results[0].geometry.location.lng));
	
		GeocodingResult[] results1 =  GeocodingApi.reverseGeocode(context, latlng).await();
		return gson.toJson(results1[0].formattedAddress).replaceAll("\"", "");
	}

	private String reverseGeocoding(String address) throws ApiException, InterruptedException, IOException{
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBoS4GIygmCRWDWUDDbheb3B8OqNjGDuL0").build();
		
		GeocodingResult[] results =  GeocodingApi.geocode(context, address).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		LatLng latlng = new LatLng();
		latlng.lat = Double.parseDouble(gson.toJson(results[0].geometry.location.lat));
		latlng.lng = Double.parseDouble(gson.toJson(results[0].geometry.location.lng));
	
		GeocodingResult[] results1 =  GeocodingApi.reverseGeocode(context, latlng).await();
		for(int i = 0; i < results1[0].addressComponents.length; i++){
		    String searchedResult = gson.toJson(results1[0].addressComponents[i].types[0]);
		    searchedResult = searchedResult.replaceAll("\"", "");
		    if("POSTAL_CODE".equals(searchedResult)) {
		    	String postalCode = gson.toJson(results1[0].addressComponents[i].longName).replaceAll("\"", "");
		    	return postalCode;
		    }
		}
		return "";
	}
}
