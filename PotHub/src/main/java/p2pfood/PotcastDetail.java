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

import org.owasp.encoder.Encode;

import adminSearch.SearchSanitizer;
import database.Database;
import database.model.DatabaseUserModel;
import database.model.PotcastBidModel;
import database.model.PotcastModel;
import database.model.ReportModel;
import login.BanChecker;

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
		
		if(BanChecker.isThisGuyBanned(username)){
			response.sendRedirect("Login");
			return;
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
			
			if(!db.getPrivilegeForIGN(username)){
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
				if(!bid.getRating().equals("") && bid.getiGN().equals(username)){
					canRate = false;
				}
			}
			

			//Check if can report
			boolean canReport = false;
			
			if(System.currentTimeMillis()>pm.getPickupTime().getTime()+(long)3600000&&pm.getiGN().equals(username)){
				canReport = true;
			}

			ArrayList<ReportModel> reports = db.getReportsFromOneUser((String) session.getAttribute("username"));
			ArrayList<ReportModel> relevantReports = new ArrayList<ReportModel>();
			
			for(ReportModel report:reports){
				if(report.isGuiltyOrNot()==0){
					relevantReports.add(report);
				}
			}
			
			//Check if restricted
			boolean restricted = false;
			if(db.isPotcastRestricted(username)){
				restricted=true;
			}
			
			DatabaseUserModel dum = db.getUserProfile(username);
			
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

			pw.append("</head>" + "<body id='babout'>" 
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
					+ "<div id='foodAndMap'>");
			
					boolean doesNotExist = true;
					for(ReportModel rel : relevantReports){
						if(rel.getEvidence()==pm.getPotcastID()&&rel.getEvidenceType().equals("Potcast")){
							doesNotExist = false;
						}
					}
					if(pm.getiGN().equals(username)&&pm.getBidStopTime().getTime()>System.currentTimeMillis()){
						pw.append("<form method='post' action='deletePotcast'>"
								+ "<p><button id='deleteButton'><img src='images/cross.png' height=30 width=30>Delete</button>"
								+ "<input type='hidden' name='potcastID' value='"
								+ pm.getPotcastID()
								+ "'></input></p>"
								+ "</form>");
					}
					else if(db.getPermissionForIGN(username)>0){
						pw.append( "<form method='post' action='deletePotcast'>"
								+ "<p><button id='deleteButton'><img src='images/cross.png' height=30 width=30>Delete</button>"
								+ "<input type='hidden' name='potcastID' value='"
								+ pm.getPotcastID()
								+ "'></input></p>"
								+ "</form>");
					}
					
					if(relevantReports.size()<=2&&doesNotExist&&!pm.getiGN().equals(username)&&canReport){
						pw.append( "<p><button id='reportButton' onclick='showReport;'><img src='images/flag.png' height=30 width=30>Report</button></p>");
						pw.append("				<div id='popup'>");
						pw.append("					<div id='popup-title'>");
						pw.append("						<p><b>Report Event</b>");
						pw.append("					</div>");
						pw.append("					<div id='popup-icons'>");
						pw.append("						<form method='post' action='reportHandler'>");
						pw.append("						<label class='radio-inline'><input type='radio' name='reason' value='Sexual/Offensive content'> Sexual/Offensive content</label><br>");
						pw.append("						<label class='radio-inline'><input type='radio' name='reason' value='Harmful or abusive content'> Harmful or abusive content</label><br>");
						pw.append("						<label class='radio-inline'><input type='radio' name='reason' value='Promotes terrorism'> Promotes terrorism</label><br>");
						pw.append("						<label class='radio-inline'><input type='radio' name='reason' value='Spam/Misleading'> Spam/Misleading</label><br>");
						pw.append("						<label class='radio-inline'><input type='radio' name='reason' value='Others'> Others</label><br>");
						pw.append("						<input type='text' class='form-control' name='othersText' id='othersText' placeholder='Type here...'>"
						+ "<input name='evidenceType' value='Potcast' type='hidden'></input>"
						+ "<input name='evidence' value='"
						+ pm.getPotcastID()
						+ "' type='hidden'>");
						pw.append("						<button class='btn btn-success'>Submit</button>");
						pw.append("						</form>");
						pw.append("					</div>");
						pw.append("					<i class='fa fa-times-circle-o fa-2x' aria-hidden='true' id='closeBtn' onclick='hideReports()'></i>");
						pw.append("					</div>");
					}
					
					pw.append( "<img height=400 width =400 src='Image/" + db.getImageByImageID(pm.getPicture())
					+ "' id='foodPicture'>" + "<iframe src='//www.google.com/maps/embed/v1/place?" + "&zoom=15"
					+ "&key=AIzaSyDmftQ7JHdzj22y3wlP01IH_LlTgFQ3JOE" + "&q=Singapore," + dbu.getAddress()
					+ "' id='foodPicture'></iframe>" + "</div>" + "<div id='foodText'>" + "<div class='pointsDiv'><div class='upper-Divtitle'><p>"
					+ pm.getTitle() + "</p></div><div class='thatLine'></div><div class='lower-Divtitle'><p> by "+pm.getiGN()+"</p>" + "</div></div>");

			if (pm.getMaxBids() < bids.size()) {
				pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Price</div><div class='thatLine'></div><div id='lower-Div'> $" + bids.get(bids.size() - pm.getMaxBids()).getBidAmount() + "</p></div></div>");
				pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Description</div><div class='thatLine'></div><div id='lower-Div'>"+ Encode.forHtml(pm.getDescription())+"</p></div></div>");
				pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Bids</div><div class='thatLine'></div><div id='lower-Div'>"+ pm.getMaxBids() + "/" + pm.getMaxBids() +"</p></div></div>");
			} else {
				pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Price</div><div class='thatLine'></div><div id='lower-Div'> $" + pm.getMinBid() + "</p></div></div>");
				pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Description</div><div class='thatLine'></div><div id='lower-Div'>"+ Encode.forHtml(pm.getDescription())+"</p></div></div>");
				pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Bids</div><div class='thatLine'></div><div id='lower-Div'>"+ bids.size() + "/" + pm.getMaxBids() +"</p></div></div>");
			}

			pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Closing at</div><div class='thatLine'></div><div id='lower-Div'>"+ timestampToDateTime(pm.getBidStopTime()) +"</p></div></div>");
			pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Pickup at</div><div class='thatLine'></div><div id='lower-Div'>"+ timestampToDateTime(pm.getPickupTime()) +"</p></div></div>");
			pw.append("<div class='pointsDiv'><div class='upper-Div'><p>Address</div><div class='thatLine'></div><div id='lower-Div'>" + dbu.getUnitNo() + ", Singapore " + dbu.getAddress() + "</p></div></div>");
			
			if(!username.equals(pm.getiGN())){
				pw.append("<div class='pointsDiv'>" + "<p id='buyerPara1'>Buyers  </p>" + "<ul id='buyersList1'>");
				for (PotcastBidModel bid : bids) {
					pw.append("<li>" + bid.getiGN() + "</li>");
				}
			}
			else{
				pw.append("<div class='pointsDiv'>" + "<p onclick='showReportBuyers()' id='buyerPara'>Report No-Shows </p>" + "<ul id='buyersList'>");
				for (PotcastBidModel bid : bids) {
					pw.append("<form method='post' action='noShowHandler'>"
							+ "<input type='hidden' name='bidder' value='"+bid.getiGN()+"'></input>"
							+ "<input type='hidden' name='bidon' value='"+bid.getPotcastID()+"'></input>"
							+ "<li><button class='plainTextButton'>" 
							+ bid.getiGN() 
							+ "</button></li></form>");
				}
			}
			
			pw.append("</ul>" + "</div>");
			
					if(restricted&&db.isPotcastRecent(username)){
						canBid=false;
					}

					if(canBid){
					pw.append("<div class='pointsDiv'><form method='post'><input type='hidden' name='potcastID' value='"+pm.getPotcastID()+"'></input><input type='number' id='bidNumberBox' name='amount'></input>"
						+ "<button>Bid!</button>" 
						+ "</form></div>");
					}
					if (request.getParameter("response") != null) {
						pw.append("<div class='pointsDiv'><p>"+SearchSanitizer.sanitise(request.getParameter("response")) + "</p></div>");
					}
					
					if(canRate){
						pw.append("<div class='pointsDiv'><form method='post' action='RatingHandler'>"
								+ "<input type='hidden' name='potcastID' value='"+pm.getPotcastID()+"'></input>"
								+ "<input type='number' min='0' max='10' value='5' name='rating'></input>"
								+ "<button>Send Rating!</button>" 
								+ "</form></div>");
					}
					if(restricted){
						pw.append("<div class='pointsDiv'>"
								+ "<p>You are restricted due to frequent reports of not collecting food you bidded for! You may only bid once evrey 24 hours.</p>"
								+ "</div>");
					}
					pw.append("</div>");
					
					pw.append( "</div>" + "<div id='footer'>"
					+ "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>" + "<p>We like food</p>"
					+ "<p>" + "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
					+ "</p>" + "</div>" + "</body>" + "</html>");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("p2plist");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.sendRedirect("p2plist");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("p2plist");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("potcastID")!=null){
		try {
			HttpSession session = request.getSession(false);
			Database db = new Database(2);
			if (session == null) {
				response.sendRedirect("Login");
				return;
			}
			if(Long.parseLong(request.getParameter("amount"))>0&&Long.parseLong(request.getParameter("amount"))<1000){
			
			PotcastBidModel pbm = new PotcastBidModel(Integer.parseInt(request.getParameter("potcastID")),
					(String) session.getAttribute("username"),
					BigDecimal.valueOf(Long.parseLong(request.getParameter("amount"))), "0");

			response.sendRedirect("p2pdetail?potcastID=" + request.getParameter("potcastID") + "&response='" + db.addPotcastBid(pbm)+"'");
			return;
			}
			else{
				response.sendRedirect("p2pdetail?potcastID=" + request.getParameter("potcastID") + "&response='Please give a valid number!'");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("p2pdetail?potcastID=" + request.getParameter("potcastID") + "&response='Error! Please try again!'");
			return;
		}
		}
		else{
			response.sendRedirect("p2plist");
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
