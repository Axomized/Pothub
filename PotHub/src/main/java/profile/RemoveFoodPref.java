package profile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFoodPref extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveFoodPref() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>"
				+ "<html>"
				+ "	<head>"
				+ "		<meta charset='UTF-8'>"
				+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
				+ "		<!-- Page Title -->"
				+ "		<title>Remove Food Preferences</title>"
				+ "		<!-- Latest compiled and CSS -->"
				+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
				+ "		<!-- Optional theme -->"
				+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
				+ "		<!-- My Own Script -->"
				+ "		<script src='script/RemoveFoodPref.js'></script>"
				+ "		<!-- My Style Sheet -->"
				+ "		<link rel='stylesheet' type='text/css' href='css/RemoveFoodPref.css'/>"
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
				+ "			<ul>"
				+ "				<li id='lhome'><a href='Forum.html'>Home</a></li>"
				+ "				<li id='lprivatemessage'><a href='PrivateMessage.html'>Private Message</a></li>"
				+ "				<li id='levent'><a href='EventPage.html'>Event</a></li>"
				+ "				<li class='dropdown'>"
				+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>"
				+ "			        <ul class='dropdown-menu'>"
				+ "			          <li><a href='#'>Active PotCasts</a></li>"
				+ "			          <li><a href='#'>Start a PotCast</a></li>"
				+ "			          <li><a href='#'>My PotCast</a></li>"
				+ "			          <li><a href='#'>Joined PotCast</a></li>"
				+ "			        </ul>"
				+ "			      </li>"
				+ "				<li id='ldonate'><a href='Donation.html'>Donate</a></li>"
				+ "			</ul>"
				+ "		</div>"
				+ "		<div id='wrapper'>"
				+ "			<div id='content-wrapper'>"
				+ "				<div id='profileNavDiv'>"
				+ "					<div id='profileNavList'>"
				+ "						<a href='Profile.html'>About</a>"
				+ "						<a href='FoodPref.html'>Food Preferences</a>"
				+ "						<a href='ProfileDonation.html'>Donation History</a>"
				+ "						<a href='EditProfile.html' id='defaultSelected'>Settings</a>"
				+ "					</div>"
				+ "				</div>"
				+ "				<div id='content' class='row'>"
				+ "					<div id='sideBarDivWrap' class='col-sm-3'>"
				+ "						<div id='sideBarDiv'>"
				+ "							<ul id='sideBarList'>"
				+ "								<li id='listHeader'>Personal Settings</li>"
				+ "								<li><a href='EditProfile.html'>Edit Profile</a></li>"
				+ "								<li><a href='AddFoodPref.html'>Add Food Preferences</a></li>"
				+ "								<li><a href='RemoveFoodPref.html' id='linkSelected'>Remove Food Preferences</a></li>"
				+ "							</ul>							"
				+ "						</div>"
				+ "					</div>"
				+ "					<div id='profileContentDiv' class='col-sm-9'>"
				+ "						<div id='aboutContentDiv'>"
				+ "							<div id='prefHeaderDiv'>"
				+ "								<span id='prefHeaderSpan'>Remove Food Preferences</span>"
				+ "							</div>"
				+ "							<div id='prefInfoDiv'>"
				+ "								<p>You may choose to remove any food from the blacklist you've made.</p>"
				+ "							</div>"
				+ "							<form id='removeFoodForm' method='post'>"
				+ "								<div id='prefListDiv'>"
				+ "									<div id='foodPrefDiv'>"
				+ "										<span id='foodPrefSpan'>Blacklist of Food</span>"
				+ "									</div>"
				+ "									<div id='foodListDiv' class='row'>"
				+ "										<div class='foodDiv col-sm-3'>"
				+ "											<label class='custom-control custom-checkbox foodLabel'>"
				+ "												<input type='checkbox' class='custom-control-input' name='foodChosen' value='Beef'>"
				+ "												<span class='custom-control-indicator'></span>"
				+ "		  										<span class='custom-control-description'>Beef</span>"
				+ "											</label>"
				+ "										</div>"
				+ "									</div>"
				+ "								</div>"
				+ "								<div id='updateBtnDiv' class='buttonsDiv'>"
				+ "									<input type='submit' id='saveBtn' class='editBtn' value='Save changes'>"
				+ "								</div>"
				+ "							</form>"
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
				+ "		<!-- Optional Bootstrap Scripts -->"
				+ "		<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
				+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
				+ "		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
				+ "	</body>"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
