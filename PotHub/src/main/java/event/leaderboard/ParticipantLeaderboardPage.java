package event.leaderboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParticipantLeaderboardPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ParticipantLeaderboardPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("	<head>");
		sb.append("	    <meta charset='UTF-8'>");
		sb.append("		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
		sb.append("		<!-- Favicon -->");
		sb.append("		<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>");
		sb.append("		<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>");
		sb.append("		<!-- Page Title -->");
		sb.append("		<title>Leaderboard</title>");
		sb.append("	    <!-- Latest compiled minified Bootstrap -->");
		sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
		sb.append("	    <!-- Optional theme -->");
		sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
		sb.append("		<!-- My Style Sheet -->");
		sb.append("		<link rel='stylesheet' type='text/css' href='css/Leaderboard.css' />");
		sb.append("	</head>");
		sb.append("	<body onload='connect('Blackpepper3', 'TeaParty')'>");
		sb.append("	<noscript><h2 style='color: #ff0000'>Seems your browser doesn't support Javascript! Websocket relies on Javascript beingenabled. Please enable Javascript and reload this page!</h2></noscript>");
		sb.append("		<!--  Navigation Bar -->");
		sb.append("		<div id='header'>");
		sb.append("			<div id='companyTitle'>");
		sb.append("				<h1>PotHub</h1>");
		sb.append("			</div>");
		sb.append("			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>");
		sb.append("				<div id='profilePic'>");
		sb.append("					<img src='images/profile.png' height='50' width='50'/>");
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
		sb.append("		        		<li><a href='/PotHub/MyEventPage'>My Events</a></li>");
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
		sb.append("			      </li>");
		sb.append("				<li id='ldonate'><a href='Donation.html'>Donate</a></li>");
		sb.append("			</ul>");
		sb.append("		</div>");
		sb.append("		<div id='wrapper'>");
		sb.append("			<div class='left-container'>");
		sb.append("				<div class='left-container-picture'>");
		sb.append("					<img src='images/crab.jpg'>");
		sb.append("					<img src='images/crab.jpg'>");
		sb.append("				</div>");
		sb.append("				<div class='left-container-title'>");
		sb.append("					<p><b>Curry Crab</b></p>");
		sb.append("					<p>Cooked by: Blackpepper3</p>");
		sb.append("				</div>");
		sb.append("				<div class='left-container-points'>");
		sb.append("					<p>8.6/10</p>");
		sb.append("				</div>");
		sb.append("				<div class='left-container-desc'>");
		sb.append("					<p><b>Description</b></p>");
		sb.append("					<p>Curry crab cooked by me... Very nice.</p>");
		sb.append("				</div>");
		sb.append("				<div class='left-container-close'>");
		sb.append("					<i class='fa fa-times' aria-hidden='true'></i>");
		sb.append("				</div>");
		sb.append("			</div>");
		sb.append("			<div class='right-container'>");
		sb.append("				<div class='right-top-container'>");
		sb.append("					<div class='right-top-topthree-container'>");
		sb.append("						<div class='right-top-topthree-picture'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("						</div>");
		sb.append("						<p>2nd</p>");
		sb.append("					</div>");
		sb.append("					<div class='right-top-topthree-container'>");
		sb.append("						<div class='right-top-topthree-picture'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("						</div>");
		sb.append("						<p>1st</p>");
		sb.append("					</div>");
		sb.append("					<div class='right-top-topthree-container'>");
		sb.append("						<div class='right-top-topthree-picture'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("						</div>");
		sb.append("						<p>3rd</p>");
		sb.append("					</div>");
		sb.append("				</div>");
		sb.append("				<div class='right-bottom-container'>");
		sb.append("					<div class='right-bottom-leaderboard-container row'>");
		sb.append("						<div class='right-bottom-ranking'>");
		sb.append("							<p><b>1</b></p>");
		sb.append("						</div>");
		sb.append("						<div class='right-bottom-picture'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("							<p><b>Jerry</b></p>");
		sb.append("						</div>");
		sb.append("						<div class='right-bottom-score'>");
		sb.append("							<div class='progressbar'>");
		sb.append("								<div class='progressbarbar'>8.7 / 10</div>");
		sb.append("							</div>");
		sb.append("						</div>");
		sb.append("					</div>");
		sb.append("					<div class='right-bottom-leaderboard-container row'>");
		sb.append("						<div class='right-bottom-ranking'>");
		sb.append("							<p><b>2</b></p>");
		sb.append("						</div>");
		sb.append("						<div class='right-bottom-picture'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("							<p><b>Jerry</b></p>");
		sb.append("						</div>");
		sb.append("						<div class='right-bottom-score'>");
		sb.append("							<div class='progressbar'>");
		sb.append("								<div class='progressbarbar'>8.7 / 10</div>");
		sb.append("							</div>");
		sb.append("						</div>");
		sb.append("					</div>");
		sb.append("					<div class='right-bottom-leaderboard-container row'>");
		sb.append("						<div class='right-bottom-ranking'>");
		sb.append("							<p><b>3</b></p>");
		sb.append("						</div>");
		sb.append("						<div class='right-bottom-picture'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("							<p><b>Jerry</b></p>");
		sb.append("						</div>");
		sb.append("						<div class='right-bottom-score'>");
		sb.append("							<div class='progressbar'>");
		sb.append("								<div class='progressbarbar'>8.7 / 10</div>");
		sb.append("							</div>");
		sb.append("						</div>");
		sb.append("					</div>");
		sb.append("					<div class='right-bottom-leaderboard-container row'>");
		sb.append("						<div class='right-bottom-ranking'>");
		sb.append("							<p><b>4</b></p>");
		sb.append("						</div>");
		sb.append("						<div class='right-bottom-picture'>");
		sb.append("							<img src='images/crab.jpg'>");
		sb.append("							<p><b>Jerry</b></p>");
		sb.append("						</div>");
		sb.append("						<div class='right-bottom-score'>");
		sb.append("							<div class='progressbar'>");
		sb.append("								<div class='progressbarbar'>8.7 / 10</div>");
		sb.append("							</div>");
		sb.append("						</div>");
		sb.append("					</div>");
		sb.append("				</div>");
		sb.append("			</div>");
		sb.append("			<div id='popupBackground'>");
		sb.append("				<div id='popup'>");
		sb.append("					<div id='userIGN-container'>");
		sb.append("						<p id='userIGN'><b>Blackpepper3</b></p>");
		sb.append("					</div>");
		sb.append("					<div id='userProfileImageAndFood'>");
		sb.append("						<img src='images/crab.jpg'>");
		sb.append("						<img src='images/crab.jpg'>");
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
		sb.append("		</div>");
		sb.append("		");
		sb.append("		<!-- Latest compiled minified Jquery Script -->");
		sb.append("	    <script src='https://code.jquery.com/jquery-3.2.1.min.js' integrity='sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=' crossorigin='anonymous'></script>");
		sb.append("	    <!-- Latest compiled minified SockJS Script -->");
		sb.append("	    <script src='https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js'></script>");
		sb.append("	    <!-- Latest compiled minified STOMP Script -->");
		sb.append("	    <script src='https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js'></script>");
		sb.append("	    <!-- My Own Script -->");
		sb.append("		<script src='script/Leaderboard.js'></script>");
		sb.append("		<script src='script/VideoParticipant2.js'></script>");
		sb.append("	</body>");
		sb.append("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
