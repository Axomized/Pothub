package event;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.EventModel;

public class EventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Database db;
    private ArrayList<EventModel> eMAL;
    private byte[] header;
    private byte[] footer;
    
    public EventPage() {
        super();
    }

    public void init(ServletConfig config) throws ServletException{
    	try {
			db = new Database(0);
			eMAL = db.getEventModelForEventPage();
			
			StringBuffer sb = new StringBuffer();
			sb.append("<!DOCTYPE html>");
			sb.append("<html>");
			sb.append("	<head>");
			sb.append("		<meta charset='UTF-8'>");
			sb.append("		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
			sb.append("		<!-- Favicon -->");
			sb.append("		<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>");
			sb.append("		<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>");
			sb.append("		<link rel='icon' href='http://localhost:8080/PotHub/images/crab.gif' type='image/gif'>");
			sb.append("		<link rel='icon' href='http://localhost:8080/PotHub/images/crab.png?v=2' type='image/x-icon'>");
			sb.append("		<!-- Page Title -->");
			sb.append("		<title>Event</title>");
			sb.append("		<!-- Latest compiled and CSS -->");
			sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css' integrity='sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb' crossorigin='anonymous'>");
			sb.append("		<!-- Optional Script (Icons) -->");
			sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
			sb.append("		<!-- My Own Script -->");
			sb.append("		<script src='script/EventPage.min.js' defer></script>");
			sb.append("		<!-- My Style Sheet -->");
			sb.append("		<link rel='stylesheet' type='text/css' href='css/EventPage.css' />");
			sb.append("	</head>");
			sb.append("	<body>");
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
			sb.append("			<div class='container-fluid'>");
			sb.append("				<ul class='nav navbar-nav'>");
			sb.append("					<li id='lhome'><a href='Forum.html'>Home</a></li>");
			sb.append("					<li id='lprivatemessage'><a href='PrivateMesage.html'>Private Message</a></li>");
			sb.append("					<li class='dropdown'>");
			sb.append("			        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>");
			sb.append("			        	<ul class='dropdown-menu'>");
			sb.append("				        	<li><a href='/PotHub/EventPage'>Events</a></li>");
			sb.append("				        	<li><a href='/PotHub/MyEventPage'>My Events</a></li>");
			sb.append("			        	</ul>");
			sb.append("			    	</li>");
			sb.append("					<li class='dropdown'>");
			sb.append("				        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Podcast</a>");
			sb.append("				        <ul class='dropdown-menu'>");
			sb.append("				          <li><a href='#'>Active PotCasts</a></li>");
			sb.append("				          <li><a href='#'>Start a PotCast</a></li>");
			sb.append("				          <li><a href='#'>My PotCast</a></li>");
			sb.append("				          <li><a href='#'>Joined PotCast</a></li>");
			sb.append("				        </ul>");
			sb.append("				      </li>");
			sb.append("					<li id='ldonate'><a href='Donation.html'>Donate</a></li>");
			sb.append("				</ul>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div class='container-fluid' id='wrapper'>");
			sb.append("		  	<div class='row'>");
			header = sb.toString().getBytes();
			sb.delete(0, sb.length());
			
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div id='footer'>");
			sb.append("			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>");
			sb.append("			<p>We like food</p>");
			sb.append("			<p>");
			sb.append("				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>");
			sb.append("			</p>");
			sb.append("		</div>");
			sb.append("		<!-- Some required javascript -->");
			sb.append("		<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js' integrity='sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh' crossorigin='anonymous'></script>");
			sb.append("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js' integrity='sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ' crossorigin='anonymous'></script>");
			sb.append("	</body>");
			sb.append("</html>");
			footer = sb.toString().getBytes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		out.write(header);
		try {
			//Check session whether the user got login in
			String username = "";
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            username = (String)session.getAttribute("username");
	            session.setAttribute("pinAttempts", 0);
	        }
	        else {
	            response.sendRedirect("Login");
	        }
	        
			for(EventModel eM:eMAL) {
	    		
	    		if(eM.getStatus().equals("E")) {
	    			continue;
	    		}
	    		
				int eventID = eM.getEventID();
				String iGN = eM.getiGN();
				String[] parts = decodeString(eM.getVenue()).split("\\`");
				
				StringBuffer sb = new StringBuffer();
				sb.append("				<div class='col-sm-4 content'>");
				sb.append("					<div class='top-container'>");
				sb.append("						<div class='top-container-image-container'>");
				sb.append("							<div class='top-container-image'> ");
				sb.append("								<img src='/PotHub/Image/" + db.getImageByImageID(eM.getThumbnail()) + "' alt='crab picture'>");
				sb.append("							</div>");
				sb.append("							<div class='top-container-image-gradient'>");
				sb.append("								<div class='event-title'>");
				sb.append("									<p>" + decodeString(eM.getEventName()) + "</p>");
				sb.append("								</div>");
				sb.append("							</div>");
				sb.append("							<div class='overlay'>");
				sb.append("								<div class='event-layout-title'>");
				sb.append("									<p>" + decodeString(eM.getEventName()) + "</p>");
				sb.append("									<hr>");
				sb.append("								</div>");
				sb.append("								<div class='event-layout-desc'>");
				sb.append("									<p><b>Description</b></p>");
				sb.append("									<p>" + decodeString(eM.getDescription()) + "</p>");
				sb.append("								</div>");
				sb.append("								<div class='event-layout-location'>");
				sb.append("									<p><b>Location</b></p>");
				sb.append("									<p>" + parts[0] + ", " + parts[1] + "</p>");
				sb.append("								</div>");
				
				if(!eM.getGuestArray().isEmpty()) {
						sb.append("						<div class='event-layout-guest'>");
						sb.append("							<div>");
						sb.append("								<p><b>Guest</b></p>");
						sb.append("							</div>");
				
					for(String s:eM.getGuestArray()) {
						sb.append("							<div class='event-layout-guest-guest'>");
						
						String fileName = db.getUserProfilePic(s);
						if(fileName != null) {
							sb.append("							<img src='/PotHub/Image/" + decodeString(fileName) + "' alt='Guest Profile Picture' height='50' width='50'><br>");
						}else {
							sb.append("							<img src='images/cat.png' alt='Guest Profile Picture' height='50' width='50'><br>");
						}
						
						sb.append("								<p>" + decodeString(s) + "</p>");
						sb.append("							</div>");
					}
					
					sb.append("							</div>");
				}
					
				sb.append("							</div>");
				sb.append("						</div>");
				sb.append("						<div class='top-container-bottom' onclick='redirectPage(\"" + encodeString(eM.getEventName()) + "\")'>");
				sb.append("							<div class='top-container-bottom-left'>");
				sb.append("								<p>" + decodeString(eM.getDescription()) + "</p>");
				sb.append("							</div>");
				sb.append("							<div class='top-container-bottom-right'>");
				sb.append("								<p><i class='fa fa-users' aria-hidden='true'></i>" + eM.getMax_No_People() + " Max</p>");
				sb.append("								<p><i class='fa fa-eye' aria-hidden='true'></i>" + db.getPeopleEventListPending(eventID).size() + " Pending</p>");
				sb.append("								<p><i class='fa fa-handshake-o' aria-hidden='true'></i>" + db.getPeopleEventListConfirm(eventID).size() + " Confirmed</p>");
				sb.append("							</div>");
				sb.append("						</div>");
				sb.append("					</div>");
				sb.append("					<div class='bot-container'>");
				sb.append("						<div class='bot-container-image'>");
				
				String fileName = db.getUserProfilePic(iGN);
				if(fileName != null) {
					sb.append("						<img src='/PotHub/Image/" + fileName + "' alt='User\'s Profile Picture'>");
				}else {
					sb.append("						<img src='images/cat.png' alt='cat picture'>");
				}
				
				sb.append("			</div>");
				sb.append("						<div class='bot-container-name'>");
				sb.append("							<p>By <span style='font-weight:bold;color:green;'>" + iGN + "</span></p>");
				sb.append("						</div>");
				sb.append("						<div class='bot-container-right'>");
				sb.append("							<p><i class='fa fa-heart-o fa-fw' aria-hidden='true'></i>" + db.getProfileForEvent(iGN).getPoints() + "</p>");
				sb.append("							<p><i class='fa fa-star-o fa-fw' aria-hidden='true'></i>" + db.getProfileForEvent(iGN).getCookingRank() + "</p>");
				sb.append("						</div>");
				sb.append("					</div>");
				sb.append("				</div>"	);
				
				out.write(sb.toString().getBytes());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		out.write(footer);
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String decodeString(String line) throws UnsupportedEncodingException {
		return URLDecoder.decode(line, "UTF-8");
	}
	
	private String encodeString(String line) throws UnsupportedEncodingException {
		return URLEncoder.encode(line, "UTF-8");
	}
}
