package donation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonationSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonationSuccess() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "";
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
		}
		else {
			response.sendRedirect("Login");
		}
		
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>"
				+ "<html>"
				+ "	<head>"
				+ "		<meta charset='UTF-8'>"
				+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
				+ "		<!-- Page Title -->"
				+ "		<title>Donation Success</title>"
				+ "		<!-- Latest compiled and CSS -->"
				+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
				+ "		<!-- Optional theme -->"
				+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
				+ "		<!-- My Own Script -->"
				+ "		<script src='script/DonationSuccess.js'></script>"
				+ "		<!-- My Style Sheet -->"
				+ "		<link rel='stylesheet' type='text/css' href='css/DonationSuccess.css' />"
				+ "	</head>"
				+ "	<body>"
				+ "		<!--  Navigation Bar -->"
				+ "		<div id='header'>"
				+ "			<div id='companyTitle'>"
				+ "				<h1>PotHub</h1>"
				+ "			</div>"
				+ "			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>"
				+ "				<div id='profilePic'>"
				+ "					<img src='images/profile.png' height='50' width='50'/>"
				+ "					<span id='welcomeSpan'>Welcome, " + username + "</span>"
				+ "				</div>"
				+ "				<div id='profileDropdownDiv'>"
				+ "					<a href='Profile'>Profile</a>"
				+ "					<a href='LoginPage'>Logout</a>"
				+ "				</div>"
				+ "			</div>"
				+ "		</div>"
				+ "		<div id='navigation'>"
				+ "			<ul>"
				+ "				<li id='lhome'><a href='Forum'>Home</a></li>"
				+ "				<li id='lprivatemessage'><a href='#01'>Private Message</a></li>"
				+ "				<li class='dropdown'>"
				+ "		        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>"
				+ "			        <ul class='dropdown-menu'>"
				+ "			        	<li><a href='/PotHub/EventPage'>Events</a></li>"
				+ "		        		<li><a href='/PotHub/MyEventPage'>My Events</a></li>"
				+ "			        </ul>"
				+ "		    	</li>"
				+ "				<li class='dropdown'>"
				+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>"
				+ "			        <ul class='dropdown-menu'>"
				+ "			          <li><a href='#'>Active PotCasts</a></li>"
				+ "			          <li><a href='#'>Start a PotCast</a></li>"
				+ "			          <li><a href='#'>My PotCast</a></li>"
				+ "			          <li><a href='#'>Joined PotCast</a></li>"
				+ "			        </ul>"
				+ "			      </li>"
				+ "				<li id='ldonate'><a href='Donation'>Donate</a></li>"
				+ "			</ul>"
				+ "		</div>"
				+ "		<div id='wrapper'>"
				+ "			<div id='content-wrapper'>"
				+ "				<div id='content'>"
				+ "					<div id='donationWrapDiv'>"
				+ "						<div id='postDonationDiv'>"
				+ "							<div id='donationHeaderDiv'>"
				+ "								<img src='images/checked.png' id='donationSuccessPic' height='50' width='50'/>"
				+ "								<p>Thanks " + username + " for your donation!</p>"
				+ "							</div>"
				+ "							<div id='donationContentDiv'>"
				+ "								<p>Your donation will certainly contribute in ensuring PotHub becomes a greater platform for people to share their joys in cooking.</p>"
				+ "							</div>"
				+ "							<div id='donationFooterDiv'>"
				+ "								<button id='confirmBtn' onclick='toDonation()'>Confirm</button>"
				+ "							</div>"
				+ "						</div>"
				+ "					</div>"
				+ "				</div>"
				+ "			</div>"
				+ "		</div>"
				+ "		<div id='footer'>"
				+ "			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>"
				+ "			<p>We like food</p>"
				+ "			<p>"
				+ "				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
				+ "			</p>"
				+ "		</div>"
				+ "		<!-- Optional Scripts for Bootstrap -->"
				+ "		<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
				+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
				+ "		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
				+ "	</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
