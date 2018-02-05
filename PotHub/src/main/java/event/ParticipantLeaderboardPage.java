package event;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import login.BanChecker;

public class ParticipantLeaderboardPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = "";
			String eventName = "";
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            username = (String)session.getAttribute("username");
	            eventName = (String)session.getAttribute("EventName");
	        }
	        else {
	            response.sendRedirect("Login");
	        }

			if(BanChecker.isThisGuyBanned(username)){
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
			sb.append("		<title>Leaderboard</title>");
			sb.append("	    <!-- Latest compiled minified Bootstrap -->");
			sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
			sb.append("	    <!-- Optional theme -->");
			sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
			sb.append("		<!-- My Style Sheet -->");
			sb.append("		<link rel='stylesheet' type='text/css' href='css/Leaderboard.css' />");
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
				sb.append("				<img src='Image/" + currentProfilePic + "' alt='ProfilePicture' height='50' width='50' style='border-radius:50%' />");
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
			sb.append("			<div id='left-container'>");
			sb.append("				<div id='left-left-container'>");
			sb.append("					<div id='resultPage'>");
			sb.append("						<i class='fa fa-clipboard fa-2x' aria-hidden='true'></i>");
			sb.append("					</div><hr>");
			sb.append("					<div id='registerPage'>");
			sb.append("						<i class='fa fa-pencil-square-o fa-2x' aria-hidden='true'></i>");
			sb.append("					</div><hr>");
			sb.append("				</div>");
			sb.append("				<div id='left-right-container'>");
			sb.append("					<div class='userDetailsInfo-container'>");
			sb.append("						<div class='left-container-picture'>");
			if(currentProfilePic != null) {
				sb.append("						<img id='displayImage' src='Image/" + currentProfilePic + "'>");
			}else {
				sb.append("						<img id='displayImage' src='images/crab.jpg'>");
			}
			sb.append("						</div>");
			sb.append("						<div class='left-container-title'>");
			sb.append("							<p><b id='displayName'>" + username + "</b></p>");
			sb.append("							<p id='displayIGN'>Cooked by: </p>");
			sb.append("						</div>");
			sb.append("						<div class='left-container-points'>");
			sb.append("							<p id='displayScore'>/10</p>");
			sb.append("						</div>");
			sb.append("						<div class='left-container-desc'>");
			sb.append("							<p><b>Description</b></p>");
			sb.append("							<p id='displayDesc'></p>");
			sb.append("						</div>");
			sb.append("					</div>");
			sb.append("					<div class='userDetailsForm-container'>");
			sb.append("						<h4><b>Register your food!</b></h4><br>");
			sb.append("						<p><b>Name of your food</b><span class='requiredInput'>*</span></p>");
			sb.append("						<input type='text' id='foodName' name='foodName' class='form-control' placeholder='Name'><br>");
			sb.append("						<p><b>Short Description of your food</b><span class='requiredInput'>*</span></p>");
			sb.append("						<textarea id='foodDesc' name='foodDesc' class='form-control' placeholder='Short Description'></textarea><br>");
			sb.append("						<button id='registerBtn' class='btn btn-primary'>Submit</button>");
			sb.append("					</div>");
			sb.append("					<div id='leftBars'>");
			sb.append("						<i class='fa fa-bars fa-2x' aria-hidden='true'></i>");
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("			<div id='right-container'>");
			sb.append("				<div class='right-top-container'>");
			sb.append("					<div class='right-top-topthree-container' id='2ndPlace'>");
			sb.append("						<div class='right-top-topthree-picture'>");
			sb.append("							<img id='2ndPlaceImage'>");
			sb.append("						</div>");
			sb.append("						<p>2nd</p>");
			sb.append("					</div>");
			sb.append("					<div class='right-top-topthree-container' id='1stPlace'>");
			sb.append("						<div class='right-top-topthree-picture'>");
			sb.append("							<img id='1stPlaceImage'>");
			sb.append("						</div>");
			sb.append("						<p>1st</p>");
			sb.append("					</div>");
			sb.append("					<div class='right-top-topthree-container' id='3rdPlace'>");
			sb.append("						<div class='right-top-topthree-picture'>");
			sb.append("							<img id='3rdPlaceImage'>");
			sb.append("						</div>");
			sb.append("						<p>3rd</p>");
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("				<div class='right-bottom-container'>");
			//sb.append("					<div class='right-bottom-leaderboard-container row'>");
			//sb.append("						<div class='right-bottom-ranking'>");
			//sb.append("							<p><b>1</b></p>");
			//sb.append("						</div>");
			//sb.append("						<div class='right-bottom-picture'>");
			//sb.append("							<img src=''>");
			//sb.append("							<p class='name'><b></b></p>");
			//sb.append("						</div>");
			//sb.append("						<div class='right-bottom-score'>");
			//sb.append("							<div class='progressbar'>");
			//sb.append("								<div class='progressbarbar'> / 10</div>");
			//sb.append("							</div>");
			//sb.append("						</div>");
			//sb.append("					</div>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("			<div id='popupBackground'>");
			sb.append("				<div id='popup'>");
			sb.append("					<div id='userIGN-container'>");
			sb.append("						<p><b id='popupIGN'></b></p>");
			sb.append("					</div>");
			sb.append("					<div id='userProfileImageAndFood'>");
			sb.append("						<img id='popupPicture' src=''>");
			sb.append("					</div>");
			sb.append("					<div id='starVoting'>");
			sb.append("						<div id='starContainer'>");
			sb.append("							<img src='images/fivestar.png' id='starBlackBackground'>");
			sb.append("							<div id='starYellowBackground'></div>");
			sb.append("						</div>");
			sb.append("						<input type='number' id='starNum' min='0' max='5' step='0.1' value='2.5'>");
			sb.append("						<button id='voteBtn'>Vote</button>");
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("			<div id='videoDiv'></div>");
			sb.append("		</div>");
			sb.append("		<div id='footer'>");
			sb.append("			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>");
			sb.append("			<p>We like food</p>");
			sb.append("			<p>");
			sb.append("				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>");
			sb.append("			</p>");
			sb.append("		</div>");
			sb.append("		");
			sb.append("		<!-- Latest compiled minified Jquery Script -->");
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
			sb.append("		<script src='script/Leaderboard.js'></script>");
			sb.append("		<script src='script/VideoParticipant2.min.js'></script>");
			sb.append("	</body>");
			sb.append("</html>");
			out.write(sb.toString().getBytes());
			
			out.close();
		} catch(Exception e) {
			response.sendRedirect("EventPage");
			e.printStackTrace();
		}
		
	}

	// On End ( Add points )
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventName = request.getParameter("eventName");
		if(eventName == null || eventName.isEmpty()) {
			doGet(request, response);
		}else {
			try {
				final Database DB = new Database(2);
				DB.addParticipationPoints(DB.getEventIDFromEventName(eventName));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
