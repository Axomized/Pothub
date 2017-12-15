package event;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.EventModel;

public class EventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EventPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Database db = new Database(0);
		
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Favicon -->"
					+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>"
					+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>"
					+ "		<link rel='icon' href='http://localhost:8080/PotHub/images/crab.gif' type='image/gif'>"
					+ "		<link rel='icon' href='http://localhost:8080/PotHub/images/crab.png?v=2' type='image/x-icon'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Event</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css' integrity='sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb' crossorigin='anonymous'>"
					+ "		<!-- Optional Script (Icons) -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/EventPage.min.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/EventPage.css' />"
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
					+ "					<span id='welcomeSpan'>Welcome, [Placeholder]</span>"
					+ "				</div>"
					+ "				<div id='profileDropdownDiv'>"
					+ "					<a href='Profile.html'>Profile</a>"
					+ "					<a href='LoginPage.html'>Logout</a>"
					+ "				</div>"
					+ "			</div>"
					+ "		</div>"
					+ "		<div id='navigation'>"
					+ "			<div class='container-fluid'>"
					+ "				<ul class='nav navbar-nav'>"
					+ "					<li id='lhome'><a href='Forum.html'>Home</a></li>"
					+ "					<li id='lprivatemessage'><a href='PrivateMesage.html'>Private Message</a></li>"
					+ "					<li id='levent'><a href='EventPage.html'>Event</a></li>"
					+ "					<li class='dropdown'>"
					+ "				        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Podcast</a>"
					+ "				        <ul class='dropdown-menu'>"
					+ "				          <li><a href='#'>Active PotCasts</a></li>"
					+ "				          <li><a href='#'>Start a PotCast</a></li>"
					+ "				          <li><a href='#'>My PotCast</a></li>"
					+ "				          <li><a href='#'>Joined PotCast</a></li>"
					+ "				        </ul>"
					+ "				      </li>"
					+ "					<li id='ldonate'><a href='Donation.html'>Donate</a></li>"
					+ "				</ul>"
					+ "			</div>"
					+ "		</div>"
					+ "		<div class='container-fluid' id='wrapper'>"
					+ "		  	<div class='row'>");
			
				try {
					for(EventModel eM:db.getEventModel("SELECT * FROM Event;")) {
						out.println( 	"<div class='col-sm-4 content'>"
						+ "					<div class='top-container'>"
						+ "						<div class='top-container-image-container'>"
						+ "							<div class='top-container-image'> "
						+ "								<img src='images/crab.jpg' alt='crab picture'>"
						+ "							</div>"
						+ "							<div class='top-container-image-gradient' onmouseenter='showOverlay(this)'>"
						+ "								<div class='event-title'>"
						+ "									<p>" + eM.getEventName() + "</p>"
						+ "								</div>"
						+ "							</div>"
						+ "							<div class='overlay' onmouseleave='hideOverlay(this)'>"
						+ "								<div class='event-layout-title'>"
						+ "									<p>" + eM.getEventName() + "</p>"
						+ "									<hr>"
						+ "								</div>"
						+ "								<div class='event-layout-desc'>"
						+ "									<p><b>Description</b></p>"
						+ "									<p>" + eM.getDescription() + "</p>"
						+ "								</div>"
						+ "								<div class='event-layout-location'>"
						+ "									<p><b>Location</b></p>"
						+ "									<p>" + eM.getVenue() + ", Singapore " + eM.getPostalCode() + "</p>"
						+ "								</div>"
						+ "								<div class='event-layout-guest'>"
						+ "									<div>"
						+ "										<p><b>Guest</b></p>"
						+ "									</div>");
							for(String s:eM.getGuestArray()) {
								out.println("				<div class='event-layout-guest-guest'>"
						+ "										<img src='images/cat.png' alt='cat picture' height='50' width='50'><br>"
						+ "										<p>" + s + "</p>"
						+ "									</div>");
							}
						out.println("					</div>"
						+ "							</div>"
						+ "						</div>"
						+ "						<div class='top-container-bottom' onclick='redirectPage()'>"
						+ "							<div class='top-container-bottom-left'>"
						+ "								<p>" + eM.getDescription() + "</p>"
						+ "							</div>"
						+ "							<div class='top-container-bottom-right'>"
						+ "								<p><i class='fa fa-users' aria-hidden='true'></i>13 Max</p>"
						+ "								<p><i class='fa fa-eye' aria-hidden='true'></i>38 Read</p>"
						+ "								<p><i class='fa fa-handshake-o' aria-hidden='true'></i>4 Joined</p>"
						+ "							</div>"
						+ "						</div>"
						+ "					</div>"
						+ "					<div class='bot-container'>"
						+ "						<div class='bot-container-image'>"
						+ "							<img src='images/cat.png' alt='cat picture'>"
						+ "						</div>"
						+ "						<div class='bot-container-name'>"
						+ "							<p>By <span style='font-weight:bold;color:green;'>" + eM.getiGN() + "</span></p>"
						+ "						</div>"
						+ "						<div class='bot-container-right'>"
						+ "							<p><i class='fa fa-heart-o fa-fw' aria-hidden='true'></i>23.7K Reputation</p>"
						+ "							<p><i class='fa fa-star-o fa-fw' aria-hidden='true'></i>Grandmaster</p>"
						+ "						</div>"
						+ "					</div>"
						+ "				</div>"	);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				out.println(
					  "			</div>"
					+ "		</div>"
					+ "		<div id='footer'>"
					+ "			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>"
					+ "			<p>We like food</p>"
					+ "			<p>"
					+ "				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a"
					+ "					href='#'>Support</a>"
					+ "			</p>"
					+ "		</div>"
					+ "		<!-- Some required javascript -->"
					+ "		<script src='https://code.jquery.com/jquery-3.2.1.slim.min.js' integrity='sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN' crossorigin='anonymous'></script>"
					+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js' integrity='sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh' crossorigin='anonymous'></script>"
					+ "		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js' integrity='sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ' crossorigin='anonymous'></script>"
					+ "	</body>"
					+ "</html>");
			
			out.close();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
