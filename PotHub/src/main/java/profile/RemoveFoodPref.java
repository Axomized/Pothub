package profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.FoodPreferences;

public class RemoveFoodPref extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveFoodPref() {
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
		
		try {
			Database db = new Database(0);
			ArrayList<FoodPreferences> foodPrefList = db.getFoodPref(username);
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
					+ "				<li id='lprivatemessage'><a href='PrivateMessage'>Private Message</a></li>"
					+ "				<li class='dropdown'>"
					+ "					<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>"
					+ "					<ul class='dropdown-menu'>"
					+ "						<li><a href='EventPage'>Events</a></li>"
					+ "						<li><a href='MyEventPage'>My Events</a></li>"
					+ "					</ul>"
					+ "				</li>"
					+ "				<li class='dropdown'>"
					+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>"
					+ "			        <ul class='dropdown-menu'>"
					+ "			          <li><a href='p2plist'>Active PotCasts</a></li>"
					+ "			          <li><a href='p2preg'>Start a PotCast</a></li>"
					+ "			          <li><a href='p2pmy'>My PotCast</a></li>"
					+ "			          <li><a href='p2pjoined'>Joined PotCast</a></li>"
					+ "			        </ul>"
					+ "			      </li>"
					+ "				<li id='ldonate'><a href='Donation'>Donate</a></li>"
					+ "			</ul>"
					+ "		</div>"
					+ "		<div id='wrapper'>"
					+ "			<div id='content-wrapper'>"
					+ "				<div id='profileNavDiv'>"
					+ "					<div id='profileNavList'>"
					+ "						<a href='Profile'>About</a>"
					+ "						<a href='FoodPref'>Food Preferences</a>"
					+ "						<a href='ProfileDonation'>Donation History</a>"
					+ "						<a href='EditProfile' id='defaultSelected'>Settings</a>"
					+ "					</div>"
					+ "				</div>"
					+ "				<div id='content' class='row'>"
					+ "					<div id='sideBarDivWrap' class='col-sm-3'>"
					+ "						<div id='sideBarDiv'>"
					+ "							<ul id='sideBarList'>"
					+ "								<li id='listHeader'>Personal Settings</li>"
					+ "								<li><a href='EditProfile'>Edit Profile</a></li>"
					+ "								<li><a href='ChangePassword'>Change Password</a></li>"
					+ "								<li><a href='AddFoodPref'>Add Food Preferences</a></li>"
					+ "								<li><a href='RemoveFoodPref' id='linkSelected'>Remove Food Preferences</a></li>"
					+ "							</ul>"
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
					+ "									<div id='foodListDiv' class='row'>");
					for (FoodPreferences fp : foodPrefList) {
						out.print("<div class='foodDiv col-sm-3'>"
								+ "	<label class='custom-control custom-checkbox foodLabel'>"
								+ "		<input type='checkbox' class='custom-control-input' name='foodChosen' value='" + fp.getFoodPref() + "'>"
								+ "		<span class='custom-control-indicator'></span>"
								+ "		<span class='custom-control-description'>" + fp.getFoodPref() + "</span>"
								+ "	</label>"
								+ "</div>");
					}
					out.print("							</div>"
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "";
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
		}
		else {
			response.sendRedirect("Login");
		}
		
		try {
			Database db = new Database(2);
			String[] foodChosenArray = request.getParameterValues("foodChosen");
			if (foodChosenArray != null && foodChosenArray.length != 0) {
				for (String s : foodChosenArray) {
					db.deleteFoodPref(username, s);
				}
				response.sendRedirect("FoodPref");
			}
			else {
				System.out.println("No food chosen");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
