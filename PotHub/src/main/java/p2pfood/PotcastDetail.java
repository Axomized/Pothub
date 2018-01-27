package p2pfood;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

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
import database.model.ReportModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/p2pdetail")
public class PotcastDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PotcastDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean canBid = true;
		boolean canRate = false;
		String username="";
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("Login");
			return;
		}
		else{
			username=(String) session.getAttribute("username");
		}
		
		int potcastID=0;
		if (request.getParameter("potcastID") == null) {
			response.sendRedirect("p2plist");
			return;
		}
		else{
			potcastID = Integer.parseInt(request.getParameter("potcastID"));
		}

		PrintWriter pw = response.getWriter();
		try {
			Database db = new Database(0);
			
			PotcastModel pm = db.getPotcastByID(potcastID);
			
			DatabaseUserModel dbu = db.getDatabaseUserByIGN(pm.getiGN());

			if (dbu == null){
				response.sendRedirect("p2plist");
				return;
			}
			ArrayList<PotcastBidModel> bids = db.getBidsForPotcast(pm.getPotcastID());
			if(bids.size()>pm.getMaxBids()){
				bids = getRelevantBids(bids, pm.getMaxBids());
			}
			
			//Check if can bid
			if(System.currentTimeMillis()>pm.getBidStopTime().getTime()){
				canBid=false;
			}
			for(PotcastBidModel bid : bids){
				if(bid.getiGN().equals(username)){
					canBid=false;
				}
			}
			if(pm.getiGN().equals(username)){
				canBid=false;
			}
			//Check if can rate
			
			if(System.currentTimeMillis()>pm.getPickupTime().getTime()){
				for(PotcastBidModel bid : bids){
					if(bid.getiGN().equals(username)){
						canRate=true;
					}
				}
			}
			
			for(PotcastBidModel bid : bids){
				System.out.println(bid.getRating());
				System.out.println(bid.getiGN());
				if(!bid.getRating().equals("") && bid.getiGN().equals(username)){
					canRate = false;
				}
			}
			
			ArrayList<ReportModel> reports = db.getReportsFromOneUser((String) session.getAttribute("username"));
			ArrayList<ReportModel> relevantReports = new ArrayList<ReportModel>();
			
			for(ReportModel report:reports){
				if(report.isGuiltyOrNot()==0){
					relevantReports.add(report);
				}
			}
			
			pw.append(
					"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
							+ "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>" + "<head>"
							+ "<meta charset='ISO-8859-1'>" + "<meta name='viewport'"
							+ "	content='width=device-width, initial-scale=1, shrink-to-fit=no'>" + "<!-- Favicon -->"
							+ "<link rel='icon' href='images/crab.gif' type='image/gif'>"
							+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>" + "<!-- Page Title -->"
							+ "<title>Potcast Details</title>" + "<!-- Latest compiled and CSS -->"
							+ " <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'>"
							+ "	<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js'></script>"
							+ "	<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
							+ "	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'></script>"
							+ "<!-- My Own Script -->" + "<script src='script/p2pdetail.js'></script>"
							+ "<!-- Optional theme -->"
							+ "<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
							+ "<!-- My Style Sheet -->"
							+ "<link rel='stylesheet' type='text/css' href='css/p2pdetail.css' />");

			pw.append("</head>" + "<body id='babout'>" + "<!--  Navigation Bar -->" + "		<div id='header'>"
					+ "<div id='companyTitle'>" + "<h1>PotHub</h1>" + "</div>"
					+ "<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>"
					+ "<div id='profilePic'>" + "<img src='images/profile.png' height='50' width='50'/>"
					+ "<span id='welcomeSpan'>Welcome, "+username+"</span>" + "</div>"
					+ "<div id='profileDropdownDiv'>" + "<a href='Profile'>Profile</a>"
					+ "<a href='Logout'>Logout</a>" + "</div>" + "</div>" + "</div>"
					+ "	<div id='navigation'>" + "		<div class='container-fluid'>"
					+ "<ul class='nav navbar-nav'>"
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
					+ "		</div>" + "	</div>" + "<div id='wrapper'>" 
					+ "<div id='foodAndMap'>");
			
					boolean doesNotExist = true;
					for(ReportModel rel : relevantReports){
						if(rel.getEvidence()==pm.getPotcastID()&&rel.getEvidenceType().equals("Potcast")){
							doesNotExist = false;
						}
					}
					
					if(relevantReports.size()<=2&&doesNotExist&&!pm.getiGN().equals(username)){
					pw.append( "<button id='reportButton' onclick='showReportables();'><img src='images/flag.png' height=30 width=30></button>"
					+ "<div id='reportOptions'>" 
					+ "<form method='post' action='reportHandler' id='reportList'>"
					+ "<input name='evidenceType' value='Potcast' type='hidden'></input>"
					+ "<input name='evidence' value='"
					+ pm.getPotcastID()
					+ "' type='hidden'></input>"
					+ "<textarea name='reason' maxlength='100' required></textarea>"
					+ "<input type='submit'></input>"
					+ "</form>"
					+ "</div>");
					}
					
					pw.append( "<img height=400 width =400 src='/PotHub/Image/" + db.getImageByImageID(pm.getPicture())
					+ "' id='foodPicture'>" + "<iframe src='//www.google.com/maps/embed/v1/place?" + "&zoom=15"
					+ "&key=AIzaSyDmftQ7JHdzj22y3wlP01IH_LlTgFQ3JOE" + "&q=Singapore," + dbu.getAddress()
					+ "' id='foodPicture'></iframe>" + "</div>" + "<div id='foodText'>" + "<div id='foodTitle'>" + "<p>"
					+ pm.getTitle() + " by "+pm.getiGN()+"</p>" + "</div>" + "<div id='foodPrice'>");

			if (pm.getMaxBids() < bids.size()) {
				pw.append("<p>Price: $" + bids.get(bids.size() - pm.getMaxBids()).getBidAmount() + "</p></div>"
						+ "<div id='foodDesc'>" + "<p id='descHead'>Description: </p><p>" + pm.getDescription() + "</p>" + "</div>"
						+ "<div id='foodBits2'><p>");
				pw.append(pm.getMaxBids() + "/" + pm.getMaxBids() 
				+ "Bids</p></div><div id='foodBits2b'><p>Closing at ");
			} else {
				pw.append("<p>Price: $" + pm.getMinBid() + "</p>" + "</div>" 
						+ "<div id='foodDesc'>" + "<p id='descHead'>Description: </p><p>"
						+ pm.getDescription() + "</p></div>" 
						+ "<div id='foodBits2'><p>");
				pw.append(bids.size() + "/" + pm.getMaxBids() 
				+ "Bids</p></div><div id='foodBits2b'><p>Closing at ");
			}

			pw.append(timestampToDateTime(pm.getBidStopTime()) + "</p>" + "</div>" + "<div id='foodBits'>"
					+ "<p>Pickup at " + timestampToDateTime(pm.getPickupTime()) + "</p>" + "</div>" 
					+ "<div id='foodDesc'><p>Address: </p>"
					+ "<p>" + dbu.getUnitNo() + ", Singapore " + dbu.getAddress() + "</p>" + "</div>"
					+ "<div id='foodBuyers'>" + "<p>Buyers: </p>" + "<ul>");
			for (PotcastBidModel bid : bids) {
				pw.append("<li>" + bid.getiGN() + "</li>");
			}
			pw.append("</ul>" + "</div>");

					if(canBid){
					pw.append("<div id='foodDesc'><form method='post'><input type='hidden' name='potcastID' value='"+pm.getPotcastID()+"'></input><input type='number' id='bidNumberBox' name='amount'></input>"
						+ "<button>Bid!</button>" 
						+ "</form></div>");
					}
					else if (request.getParameter("response") != null) {
						pw.append("<div id='foodDesc'><p>"+SearchSanitizer.sanitise(request.getParameter("response")) + "</p></div>");
					}
					
					if(canRate){
						pw.append("<div id='foodDesc'><form method='post' action='RatingHandler' target = '_blank'>"
								+ "<input type='hidden' name='potcastID' value='"+pm.getPotcastID()+"'></input>"
								+ "<input type='number' min='0' max='10' value='5' name='rating'></input>"
								+ "<button>Send Rating!</button>" 
								+ "</form></div>");
					}
					pw.append("</div>");
					
					pw.append( "</div>" + "<div id='footer'>"
					+ "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>" + "<p>We like food</p>"
					+ "<p>" + "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
					+ "</p>" + "</div>" + "</body>" + "</html>");
		} catch (SQLException e) {
			response.sendRedirect("p2plist");
		} catch (ClassNotFoundException e) {
			response.sendRedirect("p2plist");
		} catch (Exception e) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			Database db = new Database(2);
			if (session == null) {
				response.sendRedirect("Login");
				return;
			}

			PotcastBidModel pbm = new PotcastBidModel(Integer.parseInt(request.getParameter("potcastID")),
					(String) session.getAttribute("username"),
					BigDecimal.valueOf(Long.parseLong(request.getParameter("amount"))), "0");

			response.sendRedirect("p2pdetail?potcastID=" + request.getParameter("potcastID") + "&response='" + db.addPotcastBid(pbm)+"'");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("p2pdetail?potcastID=" + request.getParameter("potcastID"));
		}
	}

	private static String timestampToDateTime(Timestamp toChange) {
		Date date = new Date(toChange.getTime());
		DateFormat formatter = new SimpleDateFormat("MM.dd HH:mm");
		return formatter.format(date);
	}
	
	private static ArrayList<PotcastBidModel> getRelevantBids(ArrayList<PotcastBidModel> allBids, int topHowMany){
		Collections.reverse(allBids);
		ArrayList<PotcastBidModel> bidsToRet = new ArrayList<PotcastBidModel>();
		
		for(int i=0;i<topHowMany;i++){
			bidsToRet.add(allBids.get(i));
		}
		Collections.reverse(bidsToRet);
		return bidsToRet;
	}
}
