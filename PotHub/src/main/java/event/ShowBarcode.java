package event;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import database.Database;

public class ShowBarcode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession SESSION = request.getSession(false);
		
		String username = "";
		String eventName = "";
		if(SESSION != null) {
			username = (String)SESSION.getAttribute("username");
			eventName = (String)SESSION.getAttribute("EventName");
	    }else {
	    	response.sendRedirect("Login");
	    }
        
		try {
			Database DB = new Database(0);
			ArrayList<String[]> alsa = new ArrayList<String[]>();
			alsa = DB.getPeopleEventListConfirm(DB.getEventIDFromEventName(eventName));
			for(String[] s: alsa) {
				if(s[0].equals(username)) {
					if(Boolean.parseBoolean(s[1])) {
						response.sendRedirect("ParticipantLeaderboardPage");
					}
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
			sb.append("		<link rel='icon' href='images/crab.gif' type='image/gif'>");
			sb.append("		<link rel='icon' href='images/crab.png?v=2' type='image/x-icon'>");
			sb.append("		<!-- Page Title -->");
			sb.append("		<title>Barcode</title>");
			sb.append("		<!-- Latest compiled and CSS -->");
			sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
			sb.append("		<!-- Optional theme -->");
			sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
			sb.append("		<!-- My Style Sheet -->");
			sb.append("		<link rel='stylesheet' type='text/css' href='css/ShowBarcode.css' />");
			sb.append("	</head>");
			sb.append("	<body onload=\"connect('" + username + "')\">");
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
			sb.append("			<img src='GenerateBarcode'>");
			sb.append("		</div>");
			sb.append("		<div id='footer'>");
			sb.append("			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>");
			sb.append("			<p>We like food</p>");
			sb.append("			<p>");
			sb.append("				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a");
			sb.append("					href='#'>Support</a>");
			sb.append("			</p>");
			sb.append("		</div>");
			sb.append("		");
			sb.append("		<!-- Latest compiled minified SockJS Script -->");
			sb.append("	    <script src='https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js'></script>");
			sb.append("	    <!-- Latest compiled minified STOMP Script -->");
			sb.append("	    <script src='https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js'></script>");
			sb.append("		<!-- Optional Scripts for Bootstrap -->");
			sb.append("		<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>");
			sb.append("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>");
			sb.append("		<!-- My Own Script -->");
			sb.append("		<script src='script/ShowBarcode.min.js'></script>");
			sb.append("	</body>");
			sb.append("</html>");
			out.write(sb.toString().getBytes());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("Login");
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
