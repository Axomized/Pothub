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
import database.model.PotcastBidModel;
import database.model.PotcastModel;
import login.BanChecker;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/p2pmy")
public class PotcastMy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PotcastMy() {
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

		if (session != null) {
			username = (String) session.getAttribute("username");
		} else {
			response.sendRedirect("Login");
			return;
		}
		
		if(BanChecker.isThisGuyBanned(username)){
			response.sendRedirect("Login");
			return;
		}

		PotcastSearchObject pso = new PotcastSearchObject();

		pso.setIgn(username);
		pso.setPurpose(1);

		if (request.getParameter("title") != null) {
			pso.setTitle(SearchSanitizer.sanitise(SearchSanitizer.trimTo(request.getParameter("title"),64)));
		}
		if (request.getParameter("searchOption") != null) {
			pso.setOrderBy(request.getParameter("searchOption"));

			if (request.getParameter("searchOption").equals("bids")) {
				pso.setPurpose(2);
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

		try {
			Database db = new Database(0);
			ArrayList<PotcastModel> pots = db.getLatestPotcasts(pso);
			DatabaseUserModel dbu0 = db.getDatabaseUserByIGN(username);

			DatabaseUserModel dum = db.getUserProfile(username);
			PrintWriter pw = response.getWriter();
			pw.append(
					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
							+ "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>" + "<head>"
							+ "<title>My Potcasts</title>" + "<meta http-equiv='content-language' content='en-us' />"
							+ "<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
							+ "<!-- Favicon -->" + "<link rel='icon' href='images/crab.gif' type='image/gif'>"
							+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>"
							+ "<!-- Latest compiled and CSS -->"
							+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
							+ "<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
							+ "<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
							+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
							+ "<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
							+ "<!-- My Own Script -->" + "<script src='script/p2pmy.js'></script>"
							+ "<!-- My Style Sheet -->"
							+ "<link rel='stylesheet' type='text/css' href='css/p2pmy.css' />" + "</head>" + "<body>"
							+ "	<!--  Navigation Bar -->" + "		<div id='header'>" + "<div id='companyTitle'>"
							+ "<h1>PotHub</h1>" + "</div>"
							+ "			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>"
							+ "				<div id='profilePic'>");
							if (dum.getProfilePic() != 0) {
								pw.append("<img src='Image/" + db.getImageByImageID(dum.getProfilePic()) + "' class='roundProfilePic' height='50' width='50'/>");
							}
							else {
								pw.append("<img src='images/profile.png' class='roundProfilePic' height='50' width='50'/>");
							}
							pw.append("			<span id='welcomeSpan'>Welcome, " + username + "</span>"
							+ "				</div>"
							+ "<div id='profileDropdownDiv'>" + "<a href='Profile'>Profile</a>");

							pw.append("<a href='Logout'>Logout</a>"
							+ "</div>" + "</div>" + "</div>"
							+ "	<div id='navigation'>" + "		<div class='container-fluid'>"
							+ "<ul class='nav navbar-nav'>"
									+ "				<li id='lhome'>"
									+ "				<a href='Forum'>Home</a></li>"
									+ "				<li class='dropdown'>"
									+ "			        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>"
									+ "			        	<ul class='dropdown-menu'>"
									+ "				        	<li><a href='EventPage'>Events</a></li>"
									+ "				        	<li><a href='MyEventPage'>My Events</a></li>"
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
									+ "		</div>" + "	</div>" + "<div id='wrapper'>"
							+ "<div id='secondHeader'>" + "<h2>My Potcasts</h2>" + "<div id='searchBar'>"
							+ "<p>Search Titles: </p>" + "<form method='get'>"
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
							+ "</ul>" + "</div>" + "</form>" + "</div>" + "</div>" + "</div>");

			ArrayList<String> postalCodes = new ArrayList<String>();

			for (PotcastModel ap : pots) {
				postalCodes.add(db.getDatabaseUserPostalCodeFromIGN(ap.getiGN()));
			}
			
			ArrayList<String> distances = new ArrayList<String>();
			
			if(postalCodes.size()>0){

			distances = MapDistance
					.getJsonFromURL(MapDistance.mapURLBuilder(postalCodes, 
							dbu0.getAddress()));
			}

			int counter = 0;
			for (PotcastModel pot : pots) {
				pw.append("<a href='p2pdetail?potcastID=" + pot.getPotcastID() + "'><div id='displayUnit' ");
				if (System.currentTimeMillis() > pot.getPickupTime().getTime()) {
					pw.append("class='inactive'>");
				} else {
					pw.append("class='active'>");
				}
				pw.append("<div id='thumbnailBox'><img height=150 width=150 src='Image/"
						+ db.getImageByImageID(pot.getPicture()) + "'></div>" + "<div id='column1'>"
						+ "<div class='row1 foodTitle'>" + pot.getTitle() + "</div>" + "<div class='row1'>"
						+ pot.getiGN() + ", " + pot.getStartingCR() + "</div></div>" + "<div id='column2'>"
						+ "<div class='row2'>");

				ArrayList<PotcastBidModel> bids = db.getBidsForPotcast(pot.getPotcastID());
				int cost = 0;
				if (pot.getMaxBids() < bids.size()) {
					pw.append(pot.getMaxBids() + "/" + pot.getMaxBids());
					cost = bids.get(bids.size() - pot.getMaxBids()).getBidAmount().intValue();
				} else {
					pw.append(bids.size() + "/" + pot.getMaxBids());
					cost = pot.getMinBid();
				}

				pw.append(" Bids, $" + cost + "</div>" + "<div class='row2'>Closing at "
						+ timestampToDateTime(pot.getBidStopTime()) + "</div></div>" + "<div id='column2'><div class='row2'>"
						+ "Pickup at " + timestampToDateTime(pot.getPickupTime()) + ", " + distances.get(counter) + "</div>"
						+ "<div class='row3'>" + distances.get(counter)+" away"
						+ "</div></div></div></a>");

				counter++;
			}

			pw.append("</div></div><div id='footer'>"
					+ "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>" + "<p>We like food</p>"
					+ "<p>" + "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
					+ "</p>" + "</div>" + "</body>" + "</html>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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

	private static String timestampToDateTime(Timestamp toChange) {
		Date date = new Date(toChange.getTime());
		DateFormat formatter = new SimpleDateFormat("MM.dd HH:mm");
		return formatter.format(date);
	}

}
