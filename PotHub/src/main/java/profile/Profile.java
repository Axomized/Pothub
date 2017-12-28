package profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.DatabaseUserModel;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static String IGN;
	//private static String email;
	//private static String contactNo;
	//private static char gender;
	//private static String bio;
	//private static String address;
	//private static String unitNo;
	//private static String joinDate;
	//private static int cookingRank;
	//private static int points;
	//private static BigDecimal totalDonation;

	public Profile() {
		super();
	}
	
	private String convertDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String convertedDate = sdf.format(date);
		return convertedDate;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Database db = new Database(0);
			DatabaseUserModel currentUser = db.getUserProfile("GordonRamsey");
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
					+ "				<div id='content' class='row'>"
					+ "					<div id='profilePicDiv' class='col-sm-4'>"
					+ "						<div id='profileImgDiv'>"
					+ "							<img src='images/profile.png' height='50%' width='50%'/>"
					+ "						</div>"
					+ "						<div id='displayNameDiv'>"
					+ "							<span id='displayNameSpan'>Placeholder</span>"
					+ "						</div>"
					+ "						<div id='joinedDiv'>"
					+ "							<span id='joinedSpan'>Joined on 31/12/2016</span>"
					+ "						</div>"
					+ "						<div id='privilegedDiv'>"
					+ "							<span id='privilegedSpan'>Privileged</span>"
					+ "						</div>"
					+ "						<div id='editProfileBtnDiv'>"
					+ "							<button id='editProfileBtn' onclick='toEditProfilePage()'>Edit profile</button>"
					+ "						</div>"
					+ "					</div>"
					+ "					<div id='profileContentDiv' class='col-sm-8'>"
					+ "						<div id='profileNavDiv'>"
					+ "							<div id='profileNavList'>"
					+ "								<a href='Profile.html' id='defaultSelected'>About</a>"
					+ "								<a href='FoodPref.html'>Food Preferences</a>"
					+ "								<a href='ProfileDonation.html'>Donation History</a>"
					+ "							</div>"
					+ "						</div>"
					+ "						<div id='aboutContentDiv'>"
					+ "							<div id='pointsDiv'>"
					+ "								<div id='upper-PointsDiv'>"
					+ "									Points Accumulated"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-PointsDiv'>"
					+ "									<span id='pointsSpan'>250 points</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='cookingRankDiv'>"
					+ "								<div id='upper-RankDiv'>"
					+ "									Cooking Rank"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-RankDiv'>"
					+ "									<span id='rankSpan'>5000</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='totalAmtDiv'>"
					+ "								<div id='upper-TotalAmtDiv'>"
					+ "									Total Amount Donated"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-TotalAmtDiv'>"
					+ "									$500"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='genderDiv'>"
					+ "								<div id='upper-GenderDiv'>"
					+ "									Gender"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-GenderDiv'>"
					+ "									<span id='genderSpan'>M</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='emailDiv'>"
					+ "								<div id='upper-EmailDiv'>"
					+ "									Email"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-EmailDiv'>"
					+ "									<span id='emailSpan'>Insert email of thyself</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='contactDiv'>"
					+ "								<div id='upper-ContactDiv'>"
					+ "									Contact"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-ContactDiv'>"
					+ "									<span id='contactSpan'>Insert contact of thyself</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='bioDiv'>"
					+ "								<div id='upper-BioDiv'>"
					+ "									Bio"
					+ "								</div>"
					+ "								<div class='thatLine'></div>"
					+ "								<div id='lower-BioDiv'>"
					+ "									<p>Insert 150-words biography of thyself</p>"
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
					+ "										<span id='postalCodeSpan'>Insert postal code of thyself</span>"
					+ "									</div>"
					+ "									<div id='unitNoDiv'>"
					+ "										<span id='unSpan'>Unit Number</span>"
					+ "										<span id='unitNoSpan'>Insert unit number of thyself</span>"
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
