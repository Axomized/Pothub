package event;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;

public class OwnerLeaderboardPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = "";
			String eventName = "";
	        final HttpSession SESSION = request.getSession(false);
	        if (SESSION != null) {
	            username = (String)SESSION.getAttribute("username");
	            eventName = (String)SESSION.getAttribute("EventName");
	        }
	        else {
	            response.sendRedirect("Login");
	        }
	        
	        final Database DB = new Database(0);
	        
	        ServletOutputStream out = response.getOutputStream();
			StringBuffer sb = new StringBuffer();
			sb.append("<!DOCTYPE html>");
			sb.append("<html>");
			sb.append("	<head>");
			sb.append("	    <meta charset='UTF-8'>");
			sb.append("		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
			sb.append("		<!-- Favicon -->");
			sb.append("		<link rel='icon' href='images/crab.gif' type='image/gif'>");
			sb.append("		<link rel='icon' href='images/crab.png?v=2' type='image/x-icon'>");
			sb.append("		<!-- Page Title -->");
			sb.append("		<title>Leaderboard Owner</title>");
			sb.append("		<!-- Latest compiled and CSS -->");
			sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
			sb.append("		<!-- Optional theme -->");
			sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
			sb.append("		<!-- My Style Sheet -->");
			sb.append("		<link rel='stylesheet' type='text/css' href='css/VideoOwner.css' />");
			sb.append("	</head>");
			sb.append("	<body onload=\"connect('" + username + "', '" + eventName + "')\">");
			sb.append("		<!--  Navigation Bar -->");
			sb.append("		<div id='header'>");
			sb.append("			<div id='companyTitle'>");
			sb.append("				<p>PotHub</p>");
			sb.append("			</div>");
			sb.append("			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>");
			sb.append("				<div id='profilePic'>");
			
			String currentProfilePic = DB.getUserProfilePic(username);
			if(currentProfilePic != null) {
				sb.append("				<img src='Image/" + currentProfilePic + "' alt='ProfilePicture' height='50' width='50'/>");
			}else {
				sb.append("				<img src='images/profile.png' alt='ProfilePicture' height='50' width='50'/>");
			}
			sb.append("					<span id='welcomeSpan'>Welcome, " + username + "</span>");
			sb.append("				</div>");
			sb.append("				<div id='profileDropdownDiv'>");
			sb.append("					<a href='Profile'>Profile</a>");
			sb.append("					<a href='Logout'>Logout</a>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div id='navigation'>");
			sb.append("			<div class='container-fluid'>");
			sb.append("				<ul class='nav navbar-nav'>");
			sb.append("					<li id='lhome'><a href='Forum'>Home</a></li>");
			sb.append("					<li id='lprivatemessage'><a href='html/ComingSoon.html'>Private Message</a></li>");
			sb.append("					<li class='dropdown'>");
			sb.append("		        		<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>");
			sb.append("			        	<ul class='dropdown-menu'>");
			sb.append("			        		<li><a href='EventPage'>Events</a></li>");
			sb.append("		        			<li><a href='MyEventPage'>My Events</a></li>");
			sb.append("			        	</ul>");
			sb.append("		    		</li>");
			sb.append("					<li class='dropdown'>");
			sb.append("			        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>");
			sb.append("			        	<ul class='dropdown-menu'>");
			sb.append("			          		<li><a href='p2plist'>Active PotCasts</a></li>");
			sb.append("			          		<li><a href='p2preg'>Start a PotCast</a></li>");
			sb.append("			         	 	<li><a href='p2pmy'>My PotCast</a></li>");
			sb.append("			          		<li><a href='p2pjoined'>Joined PotCast</a></li>");
			sb.append("			        	</ul>");
			sb.append("			      	</li>");
			sb.append("					<li id='ldonate'><a href='Donation'>Donate</a></li>");
			sb.append("				</ul>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div id='wrapper'>");
			sb.append("			<div>");
			sb.append("				<button id='backToBarcodeBtn' class='btn btn-warning'>Barcode Scanning</button>");
			sb.append("				<button id='startStreamingBtn' class='btn btn-success'>Start Streaming</button>");
			sb.append("				<button id='startVotingBtn' class='btn btn-info'>Start Voting</button>");
			sb.append("				<!--  <button id='changePointBtn' class='btn btn-primary'>Change current score</button>  -->");
			sb.append("				<button id='endBtn' class='btn btn-danger'>End Event</button>");
			sb.append("			</div>");
			sb.append("			<div id='videoDiv'></div>");
			sb.append("			<div id='voteDiv' class='row'>");
			
			final int EVENTID = DB.getEventIDFromEventName(eventName);
			for(String[] s: DB.getPeopleEventListConfirm(EVENTID)) {
				if(Boolean.parseBoolean(s[1])) {
					sb.append("				<div class='voteDivContent'>");
					
					final String FILENAME = DB.getUserProfilePic(s[0]);
					if(FILENAME == null || FILENAME.isEmpty()) {
						sb.append("					<img src='images/cat.png' alt='Users Profile Picture' width='50' height='50'>");
					}else {
						sb.append("					<img src='Image/" + FILENAME + "' alt='Users Profile Picture' width='50' height='50'>");
					}
					sb.append("					<p>" + s[0] + "</p>");
					sb.append("				</div>");
				}
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
			sb.append("		");
			sb.append("	  	<!-- Latest compiled minified Jquery Script -->");
			sb.append("	    <script src='https://code.jquery.com/jquery-3.2.1.min.js' integrity='sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=' crossorigin='anonymous'></script>");
			sb.append("	    <!-- Latest compiled minified SockJS Script -->");
			sb.append("	    <script src='https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js'></script>");
			sb.append("	    <!-- Latest compiled minified STOMP Script -->");
			sb.append("	    <script src='https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js'></script>");
			sb.append("	    <!-- RTCMultiConnection -->");
			sb.append("	    <script src='https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/6.0.4/adapter.min.js'></script>");
			sb.append("		<script src='https://rawgit.com/muaz-khan/RTCMultiConnection/master/dist/RTCMultiConnection.min.js'></script>");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.0.4/socket.io.js'></script>");
			sb.append("		<!-- Bootstrap js -->");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js' integrity='sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh' crossorigin='anonymous'></script>");
			sb.append("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js' integrity='sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ' crossorigin='anonymous'></script>");
			sb.append("	    <!-- My Own Script -->");
			sb.append("	    <script src='script/VideoOwner.min.js'></script>");
			sb.append("	  	<script src='script/VideoParticipant.min.js'></script>");
			sb.append("	</body>");
			sb.append("</html>");
			out.write(sb.toString().getBytes());
			
			out.close();
		}catch(Exception e) {
			response.sendRedirect("Login");
			e.printStackTrace();
		}
	}

	// Log Event to Logs
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
