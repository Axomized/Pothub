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

import database.Database;
import database.model.EventModel;

public class EventofEventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Database db;
    
    public EventofEventPage() {
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
    	String nameOfEvent = request.getPathInfo().substring(1);
		//System.out.println(nameOfEvent);
    	try {
			EventModel eM = db.getEventofEventPage(nameOfEvent);
	    	
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
			sb.append("		<!-- Page Title -->");
			sb.append("		<title>" + decodeString(eM.getEventName()) + "</title>");
			sb.append("		<!-- Latest compiled and CSS -->");
			sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
			sb.append("		<!-- Optional theme -->");
			sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
			sb.append("		<!-- My Script -->");
			sb.append("		<script src='/PotHub/script/EventofEventPage.min.js' defer></script>");
			sb.append("		<!-- My Style Sheet -->");
			sb.append("		<link rel='stylesheet' type='text/css' href='/PotHub/css/EventofEventCss.css' />");
			sb.append("	</head>");
			sb.append("	<body>");
			sb.append("		<!--  Navigation Bar -->");
			sb.append("		<div id='header'>");
			sb.append("			<div id='companyTitle'>");
			sb.append("				<h1>PotHub</h1>");
			sb.append("			</div>");
			sb.append("			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>");
			sb.append("				<div id='profilePic'>");
			sb.append("					<img src='/PotHub/images/profile.png' alt='ProfilePicture' height='50' width='50'/>");
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
			sb.append("				        <ul class='dropdown-menu'>");
			sb.append("				        	<li><a href='/PotHub/EventPage'>Events</a></li>");
			sb.append("				        	<li><a href='/PotHub/MyEventPage'>My Events</a></li>");
			sb.append("				        </ul>");
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
			sb.append("				<div class='event-header'>");
			sb.append("					<div class='event-header-image'>");
			sb.append("						<img src='/PotHub/Image/" + decodeString(db.getFileNameByFileID(eM.getThumbnail())) + "' alt='Thumbnail'>");
			sb.append("					</div>");
			sb.append("					<div class='event-header-title-gradient'>");
			sb.append("						<div class='event-title'>");
			sb.append("							<p>" + decodeString(eM.getEventName()) + "</p>");
			sb.append("						</div>");
			sb.append("						<div class='event-header-button'>");
			sb.append("							<button type='button' class='btn btn-success' onclick='changeColor(this)'>Join</button> <!-- Servlet change the text according to status -->");
			sb.append("						</div>");
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("			<div class='row event-bottom-half'>");
			sb.append("				<div class='col-sm-6 event-bottom-left'>");
			sb.append("					<div class='event-bottom-left-top'>");
			sb.append("						<div class='col-sm-8 event-desc'>");
			sb.append("							<p><b>Description</b></p><br>");
			sb.append("							<p class='desc'>" + decodeString(eM.getDescription()) + "</p>");
			sb.append("						</div>");
			sb.append("						<div class='col-sm-4 event-guest'>");
			sb.append("							<p><b>Guest</b></p>");
			
			ArrayList<String> guestArray = eM.getGuestArray();
			if(!guestArray.isEmpty()) {
				sb.append("							<div class='row'>");
				for(String s:guestArray) {
					sb.append("							<div>");
					if(db.getUserProfilePic(s) != null)
						sb.append("								<img src='/PotHub/Image/" + decodeString(db.getUserProfilePic(s)) + "' alt='Guest's Profile Picture' height='50' width='50'><br>");
					else
						sb.append("								<img src='/PotHub/images/cat.png' alt='crab picture' height='50' width='50'><br>");
					sb.append("								<p>" + decodeString(s) + "</p>");
					sb.append("							</div>");
				}
				sb.append("							</div>");
			}
			
			sb.append("						</div>");
			sb.append("					</div>");
			sb.append("					<div class='event-gallery'>");
			sb.append("						<p><b>Gallery:</b></p>");
			
			ArrayList<Integer> galleryArray = eM.getFileListArray();
			if(!galleryArray.isEmpty()) {
				for(int s:galleryArray) {
					String fileName = db.getFileNameByFileID(s);
					sb.append("				<img src='/PotHub/Image/" + decodeString(fileName) + "' alt='Gallery Images'>");
				}
			}
			String[] parts = decodeString(eM.getVenue()).split("\\`");
			
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("				<div class='col-sm-6 event-bottom-right'>");
			sb.append("					<div class='event-bottom-right-top'>");
			sb.append("						<iframe style='width:100%;height:500px' frameborder='0' style='border:0' src='https://www.google.com/maps/embed/v1/place?key=AIzaSyBksQSICQgS5CoCf49IyTtozR8R198pTS0&q=" + encodeString(parts[0]) + "' allowfullscreen>");
			sb.append("						</iframe>");
			sb.append("						<div class='event-timing'>");
			sb.append("							<p><b>Location</b><br>");
			sb.append("							<p>" + parts[0] + "</p>");
			sb.append("							<p>" + parts[1] + "</p><br>");
			sb.append("							<p><b>Date and Time</b></p>");
			sb.append("							<p>" + eM.getDate() + "</p>");
			sb.append("						</div>");
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div id='footer'>");
			sb.append("			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>");
			sb.append("			<p>We like food</p>");
			sb.append("			<p>");
			sb.append("				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>");
			sb.append("			</p>");
			sb.append("		</div>");
			sb.append("		<!-- Optional Scripts for Bootstrap -->");
			sb.append("		<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>");
			sb.append("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>");
			sb.append("	</body>");
			sb.append("</html>");
			out.write(sb.toString().getBytes());
			
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("/PotHub/EventPage");
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String decodeString(String line) throws UnsupportedEncodingException {
		return URLDecoder.decode(line, "UTF-8");
	}
	
	private String encodeString(String line) throws UnsupportedEncodingException {
		return URLEncoder.encode(line, "UTF-8");
	}
}
