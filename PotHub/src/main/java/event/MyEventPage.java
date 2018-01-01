package event;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.EventModel;

public class MyEventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Database db;
    
    public MyEventPage() {
        super();
    }

    public void init(ServletConfig config) throws ServletException{
    	try {
			db = new Database(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("	<head>");
		sb.append("		<meta charset='UTF-8'>");
		sb.append("		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
		sb.append("		<!-- Favicon -->");
		sb.append("		<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>");
		sb.append("		<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>");
		sb.append("		<link rel='icon' href='http://localhost:8080/PotHub/images/crab.png?v=2' type='image/x-icon'>");
		sb.append("		<!-- Page Title -->");
		sb.append("		<title>My Events</title>");
		sb.append("		<!-- Latest compiled and CSS -->");
		sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
		sb.append("		<!-- My Own Script -->");
		sb.append("		<script src='script/MyEventPage.min.js' defer></script>");
		sb.append("		<!-- My Style Sheet -->");
		sb.append("		<link rel='stylesheet' type='text/css' href='css/MyEventPage.css' />");
		sb.append("	</head>");
		sb.append("	<body onload='getTime()'>");
		sb.append("		<!--  Navigation Bar -->");
		sb.append("		<div id='header'>");
		sb.append("			<div id='companyTitle'>");
		sb.append("				<h1>PotHub</h1>");
		sb.append("			</div>");
		sb.append("			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>");
		sb.append("				<div id='profilePic'>");
		sb.append("					<img src='images/profile.png' alt='ProfilePicture' height='50' width='50'/>");
		sb.append("					<span id='welcomeSpan'>Welcome, [Placeholder]</span>");
		sb.append("				</div>");
		sb.append("				<div id='profileDropdownDiv'>");
		sb.append("					<a href='Profile.html'>Profile</a>");
		sb.append("					<a href='LoginPage.html'>Logout</a>");
		sb.append("				</div>");
		sb.append("			</div>");
		sb.append("		</div>");
		sb.append("		<div id='navigation'>");
		sb.append("			<ul>");
		sb.append("				<li id='lhome'><a href='Forum.html'>Home</a></li>");
		sb.append("				<li id='lprivatemessage'><a href='#01'>Private Message</a></li>");
		sb.append("				<li class='dropdown'>");
		sb.append("		        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>");
		sb.append("			        <ul class='dropdown-menu'>");
		sb.append("			        	<li><a href='/PotHub/EventPage'>Events</a></li>");
		sb.append("			        	<li><a href='/PotHub/MyEventPage'>My Events</a></li>");
		sb.append("			        </ul>");
		sb.append("		    	</li>");
		sb.append("				<li class='dropdown'>");
		sb.append("			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>");
		sb.append("			        <ul class='dropdown-menu'>");
		sb.append("			          <li><a href='#'>Active PotCasts</a></li>");
		sb.append("			          <li><a href='#'>Start a PotCast</a></li>");
		sb.append("			          <li><a href='#'>My PotCast</a></li>");
		sb.append("			          <li><a href='#'>Joined PotCast</a></li>");
		sb.append("			        </ul>");
		sb.append("			     </li>");
		sb.append("				<li id='ldonate'><a href='Donation.html'>Donate</a></li>");
		sb.append("			</ul>");
		sb.append("		</div>");
		sb.append("		<div id='popup-container'>");
		sb.append("			<div id='popup'>");
		sb.append("				<div id='popup-title'>");
		sb.append("					<p><b>Ways to get privilege</b>");
		sb.append("				</div>");
		sb.append("				<div id='popup-icons' class='row'>");
		sb.append("					<div class='col-sm-6'>");
		sb.append("						<p><b>Being active on Forums</b></p>");
		sb.append("						<img src='images/forumIcon.png' alt='Forum Icon' width='100' height='100'>");
		sb.append("					</div>");
		sb.append("					<div class='col-sm-6'>");
		sb.append("						<p><b>Donating to fund the website</b></p>");
		sb.append("						<img src='images/donationIcon.png' alt='Donation Icon' width='100' height='100'>");
		sb.append("					</div>");
		sb.append("				</div>");
		sb.append("				<i class='fa fa-times-circle-o fa-2x' aria-hidden='true' id='closeBtn'></i>");
		sb.append("			</div>");
		sb.append("		</div>");
		sb.append("		<div id='wrapper'>");
		sb.append("			<button class='btn btn-success' id='createButton'>Create Event</button>");
		sb.append("			<div id='content-container'>");
		
		ArrayList<EventModel> eMAL;
		try {
			eMAL = db.getEventModelForMyEventPage();
		
			for(EventModel eM:eMAL) {
				String eventName = eM.getEventName();
				String[] parts = decodeString(eM.getVenue()).split("\\`");
				
				sb.append("		<div class='content'>");
				sb.append("			<img src='/PotHub/Image/" + decodeString(db.getFileNameByFileID(eM.getThumbnail())) + "' alt='crab picture'>");
				sb.append("			<div class='row front-container'>");
				sb.append("				<div class='title'>");
				sb.append("					<div class='hostedOrNot'>");
				sb.append("						<p><b>" + hostOrNot(eM.getiGN(), "Blackpepper3")+ "</b></p>");
				sb.append("					</div>");
				sb.append("					<p>" + decodeString(eM.getEventName()) + "</p>");
				sb.append("				</div>");
				sb.append("				<div class='timeleft'>");
				sb.append("					<p class='time'>" + eM.getDate().getTime() + "</p>");
				sb.append("				</div>");
				sb.append("			</div>");
				sb.append("			<div class='row back-container' onclick='redirectPage(\"" + encodeString(eventName) + "\")'>");
				sb.append("				<div class='event-layout-title'>");
				sb.append("					<p>" + decodeString(eventName) + "</p>");
				sb.append("					<hr>");
				sb.append("				</div>");
				sb.append("				<div class='event-layout-desc'>");
				sb.append("					<p><b>Description</b></p>");
				sb.append("					<p>" + decodeString(eM.getDescription()) + "</p>");
				sb.append("				</div>");
				sb.append("				<div class='event-layout-location'>");
				sb.append("					<p><b>Location</b></p>");
				sb.append("					<p>" + parts[0] + ", " + parts[1] + "</p>");
				sb.append("				</div>");
				
				if(!eM.getGuestArray().isEmpty()) {
					sb.append("			<div class='event-layout-guest'>");
					sb.append("				<div>");
					sb.append("					<p><b>Guest</b></p>");
					sb.append("				</div>");
					
					for(String s:eM.getGuestArray()) {
						sb.append("			<div class='event-layout-guest-guest'>");
						
						String fileName = db.getUserProfilePic(s);
						if(fileName != null) {
							sb.append("			<img src='/PotHub/Image/" + fileName + "' alt='cat picture' height='50' width='50'><br>");
						}else {
							sb.append("			<img src='images/cat.png' alt='cat picture' height='50' width='50'><br>");
						}
						
						sb.append("				<p>" + decodeString(s) + "</p>");
						sb.append("			</div>");
					}
					sb.append("			</div>");
				}
				
				sb.append("			</div>");
				sb.append("		</div>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sb.append("			</div>");
		sb.append("		</div>");
		sb.append("		<div id='footer'>");
		sb.append("			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>");
		sb.append("			<p>We like food</p>");
		sb.append("			<p>");
		sb.append("				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>");
		sb.append("			</p>");
		sb.append("		</div>");
		sb.append("		<!-- Optional Script -->");
		sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
		sb.append("		<script src='https://code.jquery.com/jquery-3.2.1.min.js' integrity='sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=' crossorigin='anonymous'></script>");
		sb.append("		<!-- Optional Scripts for Bootstrap -->");
		sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>");
		sb.append("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>");
		sb.append("	</body>");
		sb.append("</html>");

		out.write(sb.toString().getBytes());
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String hostOrNot(String a, String b) {
		if(a.equals(b)) {
			return "Hosted";
		}else {
			return "";
		}
	}
	
	private String decodeString(String line) throws UnsupportedEncodingException {
		return URLDecoder.decode(line, "UTF-8");
	}
	
	private String encodeString(String line) throws UnsupportedEncodingException {
		return URLEncoder.encode(line, "UTF-8");
	}
}
