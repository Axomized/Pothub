package event;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.encoder.Encode;

import database.Database;
import database.model.EventModel;
import database.model.LogsModel;

public class EventofEventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Database db;
    private HttpSession session;

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
    	try {
    		String nameOfEvent = encodeString(request.getPathInfo().substring(1));
    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			EventModel eM = db.getEventofEventPage(nameOfEvent);
			//Check session whether the user got login in
			String username = "";
	        session = request.getSession(false);
	        if (session != null) {
	            username = (String)session.getAttribute("username");
	            session.setAttribute("EventName", nameOfEvent);
	        }
	        else {
	            response.sendRedirect("../Login");
	        }
			String status = db.getWhetherPeopleEventList(db.getEventIDFromEventName(nameOfEvent), username);
			
	    	ServletOutputStream out = response.getOutputStream();
			StringBuffer sb = new StringBuffer();
			sb.append("<!DOCTYPE html>");
			sb.append("<html>");
			sb.append("	<head>");
			sb.append("		<meta charset='UTF-8'>");
			sb.append("		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
			sb.append("		<!-- Favicon -->");
			sb.append("		<link rel='icon' href='../images/crab.gif' type='image/gif'>");
			sb.append("		<link rel='icon' href='../images/crab.png?v=2' type='image/x-icon'>");
			sb.append("		<!-- Page Title -->");
			sb.append("		<title>" + decodeString(eM.getEventName()) + "</title>");
			sb.append("		<!-- Latest compiled and CSS -->");
			sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
			sb.append("		<!-- Optional theme -->");
			sb.append("		<script defer src='https://use.fontawesome.com/aff6d7353c.js'></script>");
			sb.append("		<!-- My Script -->");
			sb.append("		<script src='../script/EventofEventPage.min.js' defer></script>");
			sb.append("		<!-- My Style Sheet -->");
			sb.append("		<link rel='stylesheet' type='text/css' href='../css/EventofEventCss.css' />");
			sb.append("	</head>");
			sb.append("	<body>");
			sb.append("		<!--  Navigation Bar -->");
			sb.append("		<div id='header'>");
			sb.append("			<div id='companyTitle'>");
			sb.append("				<p>PotHub</p>");
			sb.append("			</div>");
			sb.append("			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>");
			sb.append("				<div id='profilePic'>");
			String currentProfilePic = db.getUserProfilePic(username);
			if(currentProfilePic != null) {
				sb.append("				<img src='../Image/" + currentProfilePic + "' alt='ProfilePicture' height='50' width='50'/>");
			}else {
				sb.append("				<img src='../images/profile.png' alt='ProfilePicture' height='50' width='50'/>");
			}
			sb.append("					<span id='welcomeSpan'>Welcome, " + username + "</span>");
			sb.append("				</div>");
			sb.append("				<div id='profileDropdownDiv'>");
			sb.append("					<a href='../Profile'>Profile</a>");
			sb.append("					<a href='../Logout'>Logout</a>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div id='navigation'>");
			sb.append("			<div class='container-fluid'>");
			sb.append("				<ul class='nav navbar-nav'>");
			sb.append("					<li id='lhome'><a href='../Forum'>Home</a></li>");
			sb.append("					<li class='dropdown'>");
			sb.append("		        		<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>");
			sb.append("			        	<ul class='dropdown-menu'>");
			sb.append("			        		<li><a href='../EventPage'>Events</a></li>");
			sb.append("		        			<li><a href='../MyEventPage'>My Events</a></li>");
			sb.append("			        	</ul>");
			sb.append("		    		</li>");
			sb.append("					<li class='dropdown'>");
			sb.append("			        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>");
			sb.append("			        	<ul class='dropdown-menu'>");
			sb.append("			          		<li><a href='../p2plist'>Active PotCasts</a></li>");
			sb.append("			          		<li><a href='../p2preg'>Start a PotCast</a></li>");
			sb.append("			         	 	<li><a href='../p2pmy'>My PotCast</a></li>");
			sb.append("			          		<li><a href='../p2pjoined'>Joined PotCast</a></li>");
			sb.append("			        	</ul>");
			sb.append("			      	</li>");
			sb.append("					<li id='ldonate'><a href='../Donation'>Donate</a></li>");
			sb.append("				</ul>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div class='container-fluid' id='wrapper'>");
			sb.append("			<div id='popup-container'>");
			sb.append("				<div id='popup'>");
			sb.append("					<div id='popup-title'>");
			sb.append("						<p><b>Report Event</b>");
			sb.append("					</div>");
			sb.append("					<div id='popup-icons'>");
			sb.append("						<label class='radio-inline'><input type='radio' name='report' value='Sexual/Offensive content'> Sexual/Offensive content</label><br>");
			sb.append("						<label class='radio-inline'><input type='radio' name='report' value='Harmful or abusive content'> Harmful or abusive content</label><br>");
			sb.append("						<label class='radio-inline'><input type='radio' name='report' value='Promotes terrorism'> Promotes terrorism</label><br>");
			sb.append("						<label class='radio-inline'><input type='radio' name='report' value='Spam/Misleading'> Spam/Misleading</label><br>");
			sb.append("						<label class='radio-inline'><input type='radio' name='report' value='Others'> Others</label><br>");
			sb.append("						<input type='text' class='form-control' name='othersText' id='othersText' placeholder='Type here...'>");
			sb.append("					</div>");
			sb.append("					<i class='fa fa-times-circle-o fa-2x' aria-hidden='true' id='closeBtn'></i>");
			if(eM.getiGN().equals(username)) {
				sb.append("				</div>");
				sb.append("			</div>");
				sb.append("		  	<div class='row'>");
				sb.append("				<div class='event-header'>");
				sb.append("					<div class='event-header-image'>");
				sb.append("						<img src='../Image/" + db.getImageByImageID(eM.getThumbnail()) + "' alt='Thumbnail'>");
				sb.append("					</div>");
				sb.append("					<div class='event-header-title-gradient'>");
				sb.append("						<div class='event-title'>");
				sb.append("							<p>" + decodeString(eM.getEventName()) + "</p>");
				sb.append("						</div>");
				sb.append("						<div class='event-header-button'>");
				sb.append("							<button class='btn btn-danger' onclick=\"changeColor(this); closeEvent('" + nameOfEvent + "')\">Delete Event</button> <!-- Servlet change the text according to status -->");
				sb.append("						</div>");
				sb.append("						<div class='event-header-button2'>");
				sb.append("							<button class='btn btn-info' onclick='redirectToInteractiveOwner()'>Barcode Scanning</button> <!-- Servlet change the text according to status -->");
				sb.append("						</div>");
			}else {
				sb.append("					<button onclick=\"reportEvent('" + db.getEventIDFromEventName(nameOfEvent) + "', '" + username + "', '" + eM.getiGN() + "')\" class='btn btn-success'>Submit</button>");
				sb.append("				</div>");
				sb.append("			</div>");
				sb.append("		  	<div class='row'>");
				sb.append("				<div class='event-header'>");
				sb.append("					<div class='event-header-image'>");
				sb.append("						<img src='../Image/" + db.getImageByImageID(eM.getThumbnail()) + "' alt='Thumbnail'>");
				sb.append("					</div>");
				sb.append("					<div class='event-header-title-gradient'>");
				sb.append("						<div class='event-title'>");
				sb.append("							<p>" + decodeString(eM.getEventName()) + "</p>");
				sb.append("						</div>");
				switch(status) {
				case "C":
					sb.append("						<div class='event-header-button'>");
					sb.append("							<button class='btn btn-danger' onclick=\"changeColor(this); removeConfirmRequest('" + nameOfEvent + "', '" + username + "')\">Confirmed</button> <!-- Servlet change the text according to status -->");
					sb.append("						</div>");
					sb.append("						<div class='event-header-button2'>");
					sb.append("							<button class='btn btn-info' onclick='redirectToInteractive()'>My Barcode</button> <!-- Servlet change the text according to status -->");
					sb.append("						</div>");
					break;
				case "P":
					sb.append("						<div class='event-header-button'>");
					sb.append("							<button class='btn btn-warning' onclick=\"changeColor(this); removePendingRequest('" + nameOfEvent + "', '" + username + "')\">Pending</button> <!-- Servlet change the text according to status -->");
					sb.append("						</div>");
					break;
				case "Z":
					sb.append("						<div class='event-header-button'>");
					sb.append("							<button class='btn'>Ended</button> <!-- Servlet change the text according to status -->");
					sb.append("						</div>");
					break;
				default:
					sb.append("						<div class='event-header-button'>");
					sb.append("							<button class='btn btn-success' onclick=\"changeColor(this); sendJoinRequest('" + nameOfEvent + "', '" + username + "')\">Join</button> <!-- Servlet change the text according to status -->");
					sb.append("						</div>");
					break;
				}
			}
			
			sb.append("								<div class='event-header-button3'>");
			sb.append("									<button class='btn btn-danger' id='reportBtn'><i class='fa fa-flag'></i></button>");
			sb.append("								</div>");
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
						sb.append("								<img src='../Image/" + db.getUserProfilePic(s) + "' alt='Guests Profile Picture' height='50' width='50'><br>");
					else
						sb.append("								<img src='../images/cat.png' alt='crab picture' height='50' width='50'><br>");
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
					String fileName = db.getImageByImageID(s);
					sb.append("				<img src='../Image/" + fileName + "' alt='Gallery Images'>");
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
			sb.append("							<p>" + dateFormat.format(eM.getDate()) + "</p>");
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
			sb.append("		<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>");
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
			response.sendRedirect("../EventPage");
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("Type");
		if(type.isEmpty() || type == null) {
			doGet(request, response);
		}else {
			try {
				final Database DB = new Database(2);
				String eventName = request.getParameter("eventName");
				String username = "";
				switch(type) {
					case "Close":
						HttpSession session = request.getSession(false);
			            username = (String)session.getAttribute("username");
						DB.setEventStatus(eventName, "Z");
						
						// Logs cancel Event
						LogsModel lm = new LogsModel();
						lm.setiGN(username);
						lm.setLogDate(Timestamp.from(Instant.now()));
						lm.setiPAddress(InetAddress.getLocalHost().getHostAddress());
						lm.setLogType("Event");
						lm.setLogActivity(username + " cancelled event");
						DB.insertLogs(lm);
						
						break;
					case "RemoveConfirm":
						username = request.getParameter("iGN");
						DB.deletePeopleEventConfirmList(DB.getEventIDFromEventName(eventName), username);
						break;
					case "RemovePending":
						username = request.getParameter("iGN");
						break;
					case "Join":
						username = request.getParameter("iGN");
						DB.insertPeopleEventConfirmList(DB.getEventIDFromEventName(eventName), username);
						break;
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void destroy() {
		try {
			session.removeAttribute("EventName");
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String decodeString(String line) throws UnsupportedEncodingException {
		return Encode.forHtml(URLDecoder.decode(line, "UTF-8"));
	}
	
	private String encodeString(String line) throws UnsupportedEncodingException {
		return URLEncoder.encode(line, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
	}
}
