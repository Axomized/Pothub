package profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.DatabaseUserModel;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Profile() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("username");
		
		try {
			Database db = new Database(0);
			DatabaseUserModel dum = db.getUserProfile(username);
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Profile</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/Profile.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/Profile.css'/>"
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
					+ "					<a href='Profile'>Profile</a>"
					+ "					<a href='LoginPage'>Logout</a>"
					+ "				</div>"
					+ "			</div>"
					+ "		</div>"
					+ "		<div id='navigation'>"
					+ "			<ul>"
					+ "				<li id='lhome'><a href='Forum'>Home</a></li>"
					+ "				<li id='lprivatemessage'><a href='PrivateMessage'>Private Message</a></li>"
					+ "				<li id='levent'><a href='EventPage'>Event</a></li>"
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
					+ "				<div id='profileNavDiv'>"
					+ "					<div id='profileNavList'>"
					+ "						<a href='Profile' id='defaultSelected'>About</a>"
					+ "						<a href='FoodPref'>Food Preferences</a>"
					+ "						<a href='ProfileDonation'>Donation History</a>"
					+ "						<a href='EditProfile'>Settings</a>"
					+ "					</div>"
					+ "				</div>"
					+ "				<div id='content' class='row'>"
					+ "					<div id='profilePicDiv' class='col-sm-3'>"
					+ "						<div id='profileImgDiv'>");
					if (dum.getProfilePic() != 0) {
						out.print("<img src='/PotHub/Image/" + db.getImageByImageID(dum.getProfilePic()) + "' height='150' width='150'/>");
					}
					else {
						out.print("<img src='images/profile.png' height='150' width='150'/>");
					}
					out.print("				</div>"
					+ "						<div id='displayNameDiv'>"
					+ "							<span id='displayNameSpan'>" + username + "</span>"
					+ "						</div>"
					+ "						<div id='joinedDiv'>"
					+ "							<span id='joinedSpan'>Joined on " + dum.dateFormat(dum.getJoinDate()) + "</span>"
					+ "						</div>");
					if (dum.isPriviledged() == true) {
						out.print("<div id='privilegedDiv'>"
								+ "	<span id='privilegedSpan'>Privileged</span>"
								+ "</div>");
					}
					out.print("				<div id='editProfileBtnDiv'>"
					+ "							<button id='editProfileBtn' onclick='toEditProfilePage()'>Edit profile</button>"
					+ "						</div>"
					+ "					</div>"
					+ "					<div id='profileContentDiv' class='col-sm-9'>"
					+ "						<div id='aboutContentDiv'>"
					+ "							<div id='pointsDiv'>"
					+ "								<div id='upper-PointsDiv'>"
					+ "									Points Accumulated"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-PointsDiv'>"
					+ "									<span id='pointsSpan'>" + dum.getPoints() + "</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='cookingRankDiv'>"
					+ "								<div id='upper-RankDiv'>"
					+ "									Cooking Rank"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-RankDiv'>"
					+ "									<span id='rankSpan'>" + dum.getCookingRank() + "</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='totalAmtDiv'>"
					+ "								<div id='upper-TotalAmtDiv'>"
					+ "									Total Amount Donated"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-TotalAmtDiv'>"
					+ "									<span id='totalDonationSpan'>$" + dum.getTotalDonation() + "</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='genderDiv'>"
					+ "								<div id='upper-GenderDiv'>"
					+ "									Gender"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-GenderDiv'>"
					+ "									<span id='genderSpan'>" + dum.getGender() + "</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='emailDiv'>"
					+ "								<div id='upper-EmailDiv'>"
					+ "									Email"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-EmailDiv'>"
					+ "									<span id='emailSpan'>" + dum.getEmail() + "</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='contactDiv'>"
					+ "								<div id='upper-ContactDiv'>"
					+ "									Contact"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-ContactDiv'>"
					+ "									<span id='contactSpan'>" + dum.getContact_No() + "</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='bioDiv'>"
					+ "								<div id='upper-BioDiv'>"
					+ "									Bio"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-BioDiv'>"
					+ "									<p>" + dum.getBio() + "</p>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='addressDiv'>"
					+ "								<div id='upper-AddressDiv'>"
					+ "									Address"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-AddressDiv'>"
					+ "									<div id='postalCodeDiv'>"
					+ "										<span id='psSpan'>Postal Code</span>"
					+ "										<span id='postalCodeSpan'>" + dum.getAddress() + "</span>"
					+ "									</div>"
					+ "									<div id='unitNoDiv'>"
					+ "										<span id='unSpan'>Unit Number</span>"
					+ "										<span id='unitNoSpan'>" + dum.getUnitNo() + "</span>"
					+ "									</div>"
					+ "								</div>"
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
					+ "		<!-- Optional Bootstrap Scripts -->"
					+ "		<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
					+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
					+ "		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
					+ "	</body>"
					+ "</html>");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
