package profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.DatabaseUserModel;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String IGN;
	private static String email;
	private static String contactNo;
	private static char gender;
	private static String bio;
	private static String address;
	private static int points;
	private static int cookingRank;

	public Profile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Database db = new Database(0);
			ArrayList<DatabaseUserModel> userList = db.getDatabaseUser();
			for (DatabaseUserModel user : userList) {
				IGN = user.getiGN();
				email = user.getEmail();
				contactNo = user.getContact_No();
				gender = user.getGender();
				bio = user.getBio();
				address = "Singapore " + user.getAddress() + ", " + user.getUnitNo();
				points = user.getPoints();
				cookingRank = user.getCookingRank();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
				+ "					<a href='LoginPage.html'>Logout</a>"
				+ "				</div>"
				+ "			</div>"
				+ "		</div>"
				+ "		<div id='navigation'>"
				+ "			<ul>"
				+ "				<li id='lhome'><a href='Forum'>Home</a></li>"
				+ "				<li id='lprivatemessage'><a href='PrivateMessage.html'>Private Message</a></li>"
				+ "				<li id='levent'><a href='EventPage'>Event</a></li>"
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
				+ "				<div id='content' class='row'>"
				+ "					<div id='profilePicDiv' class='col-sm-4'>"
				+ "						<div id='profileImgDiv'>"
				+ "							<img src='images/profile.png' height='50%' width='50%'/>"
				+ "						</div>"
				+ "						<div id='displayNameDiv'>"
				//+ "							<span id='displayNameSpan'>Placeholder</span>"
				+ "							<span id='displayNameSpan'>" + IGN + "</span>"
				+ "						</div>"
				+ "						<div id='editProfileBtnDiv'>"
				+ "							<button id='editProfileBtn' onclick='toEditProfilePage()'>Edit profile</button>"
				+ "						</div>"
				+ "					</div>"
				+ "					<div id='profileContentDiv' class='col-sm-8'>"
				+ "						<div id='profileNavDiv'>"
				+ "							<div id='profileNavList'>"
				+ "								<a href='Profile' id='defaultSelected'>About</a>"
				+ "							</div>"
				+ "						</div>"
				+ "						<div id='aboutContentDiv'>"
				+ "							<div id='pointsDiv'>"
				+ "								<div id='upper-PointsDiv'>"
				+ "									Points Accumulated"
				+ "								</div>"
				+ "								<div class='thatLine'></div>"
				+ "								<div id='lower-PointsDiv'>"
				//+ "									<span id='pointsSpan'>250 points</span>"
				+ "									<span id='pointsSpan'>" + points + "</span>"
				+ "								</div>"
				+ "							</div>"
				+ "							<div id='cookingRankDiv'>"
				+ "								<div id='upper-RankDiv'>"
				+ "									Cooking Rank"
				+ "								</div>"
				+ "								<div class='thatLine'></div>"
				+ "								<div id='lower-RankDiv'>"
				//+ "									<span id='rankSpan'>5000</span>"
				+ "									<span id='rankSpan'>" + cookingRank + "</span>"
				+ "								</div>"
				+ "							</div>"
				+ "							<div id='genderDiv'>"
				+ "								<div id='upper-GenderDiv'>"
				+ "									Gender"
				+ "								</div>"
				+ "								<div class='thatLine'></div>"
				+ "								<div id='lower-GenderDiv'>"
				//+ "									<span id='genderSpan'>M</span>"
				+ "									<span id='genderSpan'>" + gender + "</span>"
				+ "								</div>"
				+ "							</div>"
				+ "							<div id='emailDiv'>"
				+ "								<div id='upper-EmailDiv'>"
				+ "									Email"
				+ "								</div>"
				+ "								<div class='thatLine'></div>"
				+ "								<div id='lower-EmailDiv'>"
				//+ "									<span id='emailSpan'>Insert email of thyself</span>"
				+ "									<span id='emailSpan'>" + email + "</span>"
				+ "								</div>"
				+ "							</div>"
				+ "							<div id='contactDiv'>"
				+ "								<div id='upper-ContactDiv'>"
				+ "									Contact"
				+ "								</div>"
				+ "								<div class='thatLine'></div>"
				+ "								<div id='lower-ContactDiv'>"
				//+ "									<span id='contactSpan'>Insert contact of thyself</span>"
				+ "									<span id='contactSpan'>" + contactNo + "</span>"
				+ "								</div>"
				+ "							</div>"
				+ "							<div id='bioDiv'>"
				+ "								<div id='upper-BioDiv'>"
				+ "									Bio"
				+ "								</div>"
				+ "								<div class='thatLine'></div>"
				+ "								<div id='lower-BioDiv'>"
				//+ "									<p>Insert 150-words biography of thyself</p>"
				+ "									<p>" + bio + "</p>"
				+ "								</div>"
				+ "							</div>"
				+ "							<div id='addressDiv'>"
				+ "								<div id='upper-AddressDiv'>"
				+ "									Address"
				+ "								</div>"
				+ "								<div class='thatLine'></div>"
				+ "								<div id='lower-AddressDiv'>"
				//+ "									<span id='addressSpan'>Insert address of thyself</span>"
				+ "									<span id='addressSpan'>" + address + "</span>"
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
