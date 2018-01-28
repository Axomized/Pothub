package p2pfood;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adminSearch.SearchSanitizer;
import database.Database;
import database.model.DatabaseUserModel;
import database.model.PotcastModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/p2plist")
public class PotcastList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PotcastList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = "";

		if (session != null && session.getAttribute("username") != null) {
			username = (String) session.getAttribute("username");
		} else {
			response.sendRedirect("Login");
			return;
		}
		if (username.equals("")) {
			response.sendRedirect("Login");
			return;
		}

		try {
			PotcastSearchObject pso = new PotcastSearchObject();
			Database db = new Database(0);

			DatabaseUserModel dbu0 = db.getDatabaseUserByIGN(username);

			if (request.getParameter("title") != null) {
				pso.setTitle(SearchSanitizer.sanitise(SearchSanitizer.trimTo(request.getParameter("title"),64)));
			}
			if (request.getParameter("searchOption") != null) {
				pso.setOrderBy(request.getParameter("searchOption"));

				if (request.getParameter("searchOption").equals("bids")) {
					pso.setPurpose(4);
				}
				if (request.getParameter("searchOption").equals("closingTime")) {
					pso.setOrderBy("bidStopTime");
				}
				if (request.getParameter("searchOption").equals("pickupTime")) {
					pso.setOrderBy("pickupTime");
				}
				if (request.getParameter("searchOption").equals("price")) {
					pso.setOrderBy("minBid");
				}
				if (request.getParameter("searchOption").equals("cookingRank")) {
					pso.setOrderBy("startingCR");
				}
			}
			if (request.getParameter("searchOrder") != null) {
				if (request.getParameter("searchOrder").equals("asc")) {
					pso.setAscDesc(true);
				}
				if (request.getParameter("searchOrder").equals("desc")) {
					pso.setAscDesc(false);
				}
			}

			PotcastSearchObject pso3 = new PotcastSearchObject();
			pso3.setPurpose(3);

			ArrayList<PotcastModel> top3Potcasts = db.getLatestPotcasts(pso3);
			ArrayList<String> postalCodes3 = new ArrayList<String>();

			for (PotcastModel ap : top3Potcasts) {
				postalCodes3.add(db.getDatabaseUserPostalCodeFromIGN(ap.getiGN()));
			}

			String url = MapDistance.mapURLBuilder(postalCodes3, dbu0.getAddress());

			ArrayList<String> distances3 = MapDistance.getJsonFromURL(url);
			PrintWriter pw = response.getWriter();

			pw.append("<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset='ISO-8859-1'>" + "<meta name='viewport'"
					+ "	content='width=device-width, initial-scale=1, shrink-to-fit=no'>" + "<!-- Favicon -->"
					+ "<link rel='icon' href='images/crab.gif' type='image/gif'>"
					+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>" + "<!-- Page Title -->"
					+ "<title>Potcast List</title>" + "<!-- Latest compiled and CSS -->"
					+ " <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'>"
					+ "	<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js'></script>"
					+ "	<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
					+ "	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'></script>"
					+ "<!-- Optional theme -->" + "<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "<!-- My Own Script -->" + "<script src='script/p2plist.js'></script>" + "<!-- My Style Sheet -->"
					+ "<link rel='stylesheet' type='text/css' href='css/p2plist.css' />" 
					+ "    <style>"
					+ "      #map {"
					+ "        height: 1000px;"
					+ "        width: 1500px;"
					+ "        margin: 50px;"	
					+ "        border-radius: 20px;"	
					+ "      }"
					+ "    </style>"
					+ "</head>" + "<body>"
					+ "	<!--  Navigation Bar -->" + "		<div id='header'>" + "<div id='companyTitle'>"
					+ "<h1>PotHub</h1>" + "</div>"
					+ "<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>"
					+ "<div id='profilePic'>" + "<img src='images/profile.png' height='50' width='50'/>"
					+ "<span id='welcomeSpan'>Welcome, " + username + "</span>" + "</div>"
					+ "<div id='profileDropdownDiv'>" + "<a href='Profile'>Profile</a>");

			pw.append("<a href='Logout'>Logout</a>");

			pw.append("</div>" + "</div>" + "</div>" + "	<div id='navigation'>"
					+ "		<div class='container-fluid'>" 
					+ "			<ul class='nav navbar-nav'>"
					+ "				<li id='lhome'>"
					+ "				<a href='Forum'>Home</a></li>"
					+ "				<li class='dropdown'>"
					+ "			        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>"
					+ "			        	<ul class='dropdown-menu'>"
					+ "				        	<li><a href='/PotHub/EventPage'>Events</a></li>"
					+ "				        	<li><a href='/PotHub/MyEventPage'>My Events</a></li>"
					+ "			        	</ul>"
					+ "			    	</li>"
					+ "				<li class='dropdown'>"
					+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>"
					+ "			        <ul class='dropdown-menu'>"
					+ "			          <li><a href='p2plist'>Active Potcasts</a></li>"
					+ "			          <li><a href='p2preg'>Start a Potcast</a></li>"
					+ "			          <li><a href='p2pmy'>My Potcast</a></li>"
					+ "			          <li><a href='p2pjoined'>Joined Potcast</a></li>" + "			        </ul>"
					+ "			      </li>"
					+ "				<li id='ldonate'><a href='Donation'>Donate</a></li>" 
					+ "			</ul>"
					+ "		</div>" + "	</div>" + "<div id='wrapper'>" + "<div id='secondHeader'>" + "<h2>Potcast</h2>"
					+ "<div id='searchBar'</div>" + "<p>Search Titles: </p>" + "<form method='get'>"
					+ "<input type='text' name='title'></input><input type='submit'></input>"
					+ "<p id='toggleSpan'>More Options</p>" + "<div id='hidableSearchBlock'>"
					+ "<p>Sort Results By: </p>" + "<div id='search'>" + "<div id='radios'>" + "<ul>"
					+ "<li><input type='radio' name='searchOption' id='radioActiveBids' value='bids'></input><label for='radioActiveBids'>Active Bids</label></li>"
					+ "<li><input type='radio' name='searchOption' id='radioClosingTime' value='closingTime'></input><label for='radioClosingTime'>Bid Closing Time</label></li>"
					+ "<li><input type='radio' name='searchOption' id='radioPickupTime' value='pickupTime'></input><label for='radioPickupTime'>Pickup Time</label></li>"
					+ "<li><input type='radio' name='searchOption' id='radioPrice' value='price'></input><label for='radioPrice'>Price</label></li>"
					+ "<li><input type='radio' name='searchOption' id='radioCR' value='cookingRank'></input><label for='radioCR'>Cooking Rating</label></li>"
					+ "</ul>" + "</div>" + "<div id='radios2'>" + "<ul>"
					+ "<li><input type='radio' name='searchOrder' id='radioAscend' value='asc'></input><label for='radioAscend'>Ascending</label></li>"
					+ "<li><input type='radio' name='searchOrder' id='radioDescend' value='desc'></input><label for='radioDescend'>Descending</label></li>"
					+ "</ul>" + "</div>" + "</form>" + "</div>" + "</div>" + "</div></div>" 
					+ "<button onclick=openNav()>Open Map</button>");
					pw.append( "<h1>Closing soon:</h1>");
			
			int counter3 = 0;
			for (PotcastModel ap : top3Potcasts) {

				pw.append("<a href='p2pdetail?potcastID=" + ap.getPotcastID()
						+ "'><div id='displayUnit'><div id='thumbnailBox'>");
				pw.append("<img height=150 width=150 src='/PotHub/Image/"
						+ db.getImageTableByImageID(ap.getPicture()).getImageName() + "'/></div>");
				pw.append("<div id='column1'>" + "<div class='row1 foodTitle'>" + ap.getTitle() + "</div>");
				pw.append("<div class='row1'>" + ap.getiGN() + ", " + ap.getStartingCR() + "CR</div>" + "</div>"
						+ "<div id='column2'>");

				if (db.getBidsForPotcast(ap.getPotcastID()).size() > ap.getMaxBids()) {
					pw.append("<div class='row2'>" + ap.getMaxBids() + "/" + ap.getMaxBids() + " Bids, ");

					pw.append(TimestampToDateTime(ap.getBidStopTime()));

					pw.append("</div><div class='row2'>$"
							+ db.getBidsForPotcast(ap.getPotcastID()).get(ap.getMaxBids() - 1).getBidAmount() + "</div>"
							+ "</div>");
				} else {
					pw.append("<div class='row2'>" + db.getBidsForPotcast(ap.getPotcastID()).size() + "/"
							+ ap.getMaxBids() + " Bids, ");

					pw.append(TimestampToDateTime(ap.getBidStopTime()));

					pw.append("</div>" + "<div class='row2'>$" + ap.getMinBid() + "</div>" + "</div>");
				}

				pw.append("<div id='column2'><div class='row2'>");

				pw.append(TimestampToDateTime(ap.getPickupTime()));

				pw.append(", " + distances3.get(counter3) + "</div></div></div></a>");
				counter3++;
			}

			pw.append("<h1>Active Potcasts: </h1>");

			System.out.println(pso.getExecutableSQL());
			ArrayList<PotcastModel> activePotcasts = db.getLatestPotcasts(pso);
			ArrayList<String> postalCodes = new ArrayList<String>();

			for (PotcastModel ap : activePotcasts) {
				postalCodes.add(db.getDatabaseUserPostalCodeFromIGN(ap.getiGN()));
			}

			String url0 = MapDistance.mapURLBuilder(postalCodes, dbu0.getAddress());
			ArrayList<String> distances = MapDistance.getJsonFromURL(url0);
			int counter = 0;
			for (PotcastModel ap : activePotcasts) {
				pw.append("<a href='p2pdetail?potcastID=" + ap.getPotcastID()
						+ "'><div id='displayUnit'><div id='thumbnailBox'>");
				pw.append("<img height=150 width=150 src='/PotHub/Image/"
						+ db.getImageTableByImageID(ap.getPicture()).getImageName() + "'/></div>");
				pw.append("<div id='column1'>" + "<div class='row1 foodTitle'>" + ap.getTitle() + "</div>");
				pw.append("<div class='row1'>" + ap.getiGN() + ", " + ap.getStartingCR() + "CR</div>" + "</div>"
						+ "<div id='column2'>");

				if (db.getBidsForPotcast(ap.getPotcastID()).size() > ap.getMaxBids()) {
					pw.append("<div class='row2'>" + ap.getMaxBids() + "/" + ap.getMaxBids() + " Bids, ");

					pw.append(TimestampToDateTime(ap.getBidStopTime()));

					pw.append("</div>" + "<div class='row2'>$"
							+ db.getBidsForPotcast(ap.getPotcastID()).get(ap.getMaxBids() - 1).getBidAmount() + "</div>"
							+ "</div>");
				} else {
					pw.append("<div class='row2'>" + db.getBidsForPotcast(ap.getPotcastID()).size() + "/"
							+ ap.getMaxBids() + " Bids, ");

					pw.append(TimestampToDateTime(ap.getBidStopTime()));

					pw.append("</div>" + "<div class='row2'>$" + ap.getMinBid() + "</div>" + "</div>");
				}

				pw.append("<div id='column2'><div class='row2'>");

				pw.append(TimestampToDateTime(ap.getPickupTime()));

				pw.append(", " + distances.get(counter) + "</div></div></div></a>");
				counter++;
			}

			pw.append("</div>"
					
+ "<div id='myNav' class='overlay'>"
+ "<a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>"
+ "    <div id='map'></div>"
+ "</div>"
+ "    <script>"
+ "      function initMap() {"
+ "        var bounds = new google.maps.LatLngBounds;"
+ "        var markersArray = [];"
+ ""
+ "        var origin1 = 'Singapore "+dbu0.getAddress()+"';"
+ ""
+ "        var destinationIcon = 'https://chart.googleapis.com/chart?' +"
+ "            'chst=d_map_pin_letter&chld=D|FF0000|000000';"
+ "        var originIcon = 'https://chart.googleapis.com/chart?' +"
+ "            'chst=d_map_pin_letter&chld=O|FFFF00|000000';"
+ "        var map = new google.maps.Map(document.getElementById('map'), {"
+ "          center: {lat: 1.29, lng: 103.85},"
+ "          zoom: 8"
+ "        });"
+ "        var geocoder = new google.maps.Geocoder;"
+ ""
+ "        var service = new google.maps.DistanceMatrixService;"
+ "        service.getDistanceMatrix({"
+ "          origins: [origin1],"
+ "          destinations: [");
			
			boolean notFirst=false;
			System.out.println(postalCodes);
			for(String postCode : postalCodes){
				if(notFirst){
					pw.append(",");
				}
				pw.append("'Singapore "+postCode+"'");
				notFirst=true;
			}
			
pw.append("],"
+ "          travelMode: 'DRIVING',"
+ "          unitSystem: google.maps.UnitSystem.METRIC,"
+ "          avoidHighways: false,"
+ "          avoidTolls: false"
+ "        }, function(response, status) {"
+ "          if (status !== 'OK') {"
+ "            alert('Error was: ' + status);"
+ "          } else {"
+ "            var originList = response.originAddresses;"
+ "            var destinationList = response.destinationAddresses;"
+ "            deleteMarkers(markersArray);"
+ ""
+ "            var showGeocodedAddressOnMap = function(asDestination) {"
+ "              var icon = asDestination ? destinationIcon : originIcon;"
+ "              return function(results, status) {"
+ "                if (status === 'OK') {"
+ "                  map.fitBounds(bounds.extend(results[0].geometry.location));"
+ "                  markersArray.push(new google.maps.Marker({"
+ "                    map: map,"
+ "                    position: results[0].geometry.location,"
+ "                    icon: icon"
+ "                  }));"
+ "                } else {"
+ "                  alert('Geocode was not successful due to: ' + status);"
+ "                }"
+ "              };"
+ "            };"
+ ""
+ "            for (var i = 0; i < originList.length; i++) {"
+ "              var results = response.rows[i].elements;"
+ "              geocoder.geocode({'address': originList[i]},"
+ "                  showGeocodedAddressOnMap(false));"
+ "              for (var j = 0; j < results.length; j++) {"
+ "                geocoder.geocode({'address': destinationList[j]},"
+ "                    showGeocodedAddressOnMap(true));"
+ "              }"
+ "            }"
+ "          }"
+ "        });"
+ "      }"
+ ""
+ "      function deleteMarkers(markersArray) {"
+ "        for (var i = 0; i < markersArray.length; i++) {"
+ "          markersArray[i].setMap(null);"
+ "        }"
+ "        markersArray = [];"
+ "      }"
+ "    </script>"
+ "    <script async defer "
+ "    src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDmftQ7JHdzj22y3wlP01IH_LlTgFQ3JOE&callback=initMap'>"
+ "    </script>"

					+ "<div id='footer'>" + "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
					+ "<p>We like food</p>" + "<p>"
					+ "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>" + "</p>"
					+ "</div>" 
					+"</body>" + "</html>");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private static String TimestampToDateTime(Timestamp toChange) {
		Date date = new Date(toChange.getTime());
		DateFormat formatter = new SimpleDateFormat("MM.dd HH:mm");
		return formatter.format(date);
	}

}
