package event;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpHeaders;

import database.Database;

public class BarcodeScanning extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = "";
			String eventName = "";
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            username = (String)session.getAttribute("username");
	            eventName = (String)session.getAttribute("EventName");
	            if(eventName.isEmpty() || eventName == null) {
	            	response.sendRedirect("/PotHub/EventPage");
	            }
	        }
	        else {
	            response.sendRedirect("/PotHub/Login");
	        }
	        
	        Database db = new Database(0);
	        final int EVENTID = db.getEventIDFromEventName(eventName);
	        if(!db.isOwner(EVENTID, username)) {
	        	response.sendRedirect("/PotHub/EventPage");
	        }
	        
	        
	        ArrayList<String[]> confirmList = db.getPeopleEventListConfirm(EVENTID);
	        ArrayList<String> notScannedList = new ArrayList<String>();
	        int numOfNotScanned = 0;
	        for(String[] s: confirmList) {
				if(!Boolean.parseBoolean(s[1])) {
					notScannedList.add(s[0]);
					numOfNotScanned++;
				}
			}
	        
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
			sb.append("		<title>Barcode Scanning</title>");
			sb.append("		<!-- Latest compiled and CSS -->");
			sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
			sb.append("		<!-- Optional theme -->");
			sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
			sb.append("		<!-- My Style Sheet -->");
			sb.append("		<link rel='stylesheet' type='text/css' href='css/VideoCamera.css' />");
			sb.append("	</head>");
			sb.append("	<body onload=\"connect('" + eventName + "', '" + username + "')\">");
			sb.append("		<!--  Navigation Bar -->");
			sb.append("		<div id='header'>");
			sb.append("			<div id='companyTitle'>");
			sb.append("				<p>PotHub</p>");
			sb.append("			</div>");
			sb.append("			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>");
			sb.append("				<div id='profilePic'>");
			sb.append("					<img src='images/profile.png' height='50' width='50'/>");
			sb.append("					<span id='welcomeSpan'>Welcome, " + username + "</span>");
			sb.append("				</div>");
			sb.append("				<div id='profileDropdownDiv'>");
			sb.append("					<a href='/PotHub/Profile'>Profile</a>");
			sb.append("					<a href='/PotHub/Logout'>Logout</a>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div id='navigation'>");
			sb.append("			<ul>");
			sb.append("				<li id='lhome'><a href='/PotHub/Forum'>Home</a></li>");
			sb.append("				<li id='lprivatemessage'><a href='#01'>Private Message</a></li>");
			sb.append("				<li class='dropdown'>");
			sb.append("		        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>");
			sb.append("			        <ul class='dropdown-menu'>");
			sb.append("			        	<li><a href='/PotHub/EventPage'>Events</a></li>");
			sb.append("		        		<li><a href='/PotHub/MyEventPage'>My Events</a></li>");
			sb.append("			        </ul>");
			sb.append("		    	</li>");
			sb.append("				<li class='dropdown'>");
			sb.append("			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>");
			sb.append("			        <ul class='dropdown-menu'>");
			sb.append("			          <li><a href='/PotHub/p2plist'>Active PotCasts</a></li>");
			sb.append("			          <li><a href='/PotHub/p2preg'>Start a PotCast</a></li>");
			sb.append("			          <li><a href='/PotHub/p2pmy'>My PotCast</a></li>");
			sb.append("			          <li><a href='/PotHub/p2pjoined'>Joined PotCast</a></li>");
			sb.append("			        </ul>");
			sb.append("			      </li>");
			sb.append("				<li id='ldonate'><a href='/PotHub/Donation'>Donate</a></li>");
			sb.append("			</ul>");
			sb.append("		</div>");
			sb.append("		<div id='wrapper'>");
			sb.append("			<div id='top-container' class='row'>");
			sb.append("				<div id='top-video-container'>");
			sb.append("				    <video id='video' muted></video>");
			sb.append("				</div>");
			sb.append("				<div id='top-right-container'>");
			sb.append("					<div id='top-right-title-container'>");
			sb.append("						<p><b id='numLeft'>" + numOfNotScanned + " have not scanned.</b></p><hr>");
			sb.append("					</div>");
			sb.append("					<div id='top-right-name-container'>");
			sb.append("						<ul id='userList'>");
			
			for(String s: notScannedList) {
				sb.append("						<li>" + s + "</li>");
			}
			
			sb.append("						</ul>");
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("				<button type='button' id='redirectBtn' class='btn btn-primary' onclick='stopRecording()'>Stop</button>");
			sb.append("			</div>");
			sb.append("			<div id='bottom-container'>");
			sb.append("				<p><b>Scanned</b></p>");
			sb.append("				<div id='bottom-scanned-container' class='row'>");
			
			for(String[] s: confirmList) {
				if(Boolean.parseBoolean(s[1])) {
					sb.append("<img src='/PotHub/Image/" + db.getUserProfilePic(s[0]) + "' alt='User's Profile picture' height='50' width='50'>");
				}
			}
			
			//sb.append("					<img src='images/crab.jpg' alt='User's Profile picture' height='50' width='50'>");
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
			sb.append("		<!-- Latest compiled minified SockJS Script -->");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js'></script>");
			sb.append(" 	<!-- Latest compiled minified STOMP Script -->");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js'></script>");
			sb.append("		<!-- Optional Scripts for Bootstrap -->");
			sb.append("		<script src='https://rawgit.com/muaz-khan/RTCMultiConnection/master/dist/RTCMultiConnection.min.js'></script>");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/6.0.4/adapter.js' integrity='sha256-pcIHeV2fplzdElupaGGkE0vfUIatGwut2jqr8/paQRU=' crossorigin='anonymous'></script>");
			sb.append("		<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>");
			sb.append("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>");
			sb.append("		<!-- My Own Script -->");
			sb.append("		<script src='script/VideoCamera.js'></script>");
			sb.append("	</body>");
			sb.append("</html>");
			out.write(sb.toString().getBytes());
			
			out.close();
		} catch(Exception e) {
			response.sendRedirect("/PotHub/EventPage");
			e.printStackTrace();
		}
	}

	// I using post to confirm confirmed participants
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final String EVENTNAME = request.getParameter("eventName");
			final String USERNAME = request.getParameter("iGN");
			final Database DB = new Database(2);
			if(USERNAME == null) {
				final String STATUS = request.getParameter("status");
				DB.setEventStatus(EVENTNAME, STATUS);
			}else {
				final int EVENTID = DB.getEventIDFromEventName(EVENTNAME);
				DB.setPeopleEventListConfirmConfirmed(EVENTID, USERNAME);
			}
			response.getWriter().write("Success");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
