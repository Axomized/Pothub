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

public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static String email;
	//private static String contactNo;
	//private static char gender;
	//private static String bio;
	//private static String address;
	//private static String unitNo;
       
    public EditProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Database db = new Database(0);
			DatabaseUserModel currentUser = db.getUserProfile("Placeholder from Session Attribute");
			
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
				+ "		<title>Edit Profile</title>"
				+ "		<!-- Latest compiled and CSS -->"
				+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
				+ "		<!-- Optional theme -->"
				+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
				+ "		<!-- My Own Script -->"
				+ "		<script src='script/EditProfile.js'></script>"
				+ "		<!-- My Style Sheet -->"
				+ "		<link rel='stylesheet' type='text/css' href='css/EditProfile.css' />"
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
				+ "				<form id='profileForm' autocomplete='off' enctype='multipart/form-data' method='post'>"
				+ "					<div id='content' class='row'>"
				+ "						<div id='profilePicDiv' class='col-sm-4'>"
				+ "							<div id='profileImgDiv'>"
				+ "								<img src='images/profile.png' height='50%' width='50%'/>"
				+ "							</div>"
				+ "							<div id='editProfileImgDiv'>"
				+ "								<label id='profilePicLabel' for='profilePicFile'>"
				+ "									<input type='file' id='profilePicFile' name='profilePicFile' accept='image/*' onchange='checkFile()'>"
				+ "									<span id='profilePicSpan'>Upload new picture</span>"
				+ "								</label>"
				+ "							</div>"
				+ "						</div>"
				+ "						<div id='profileContentDiv' class='col-sm-8'>"
				+ "							<div id='profileNavDiv'>"
				+ "								<div id='profileNavList'>"
				+ "									<a href='Profile' id='defaultSelected'>About</a>"
				+ "									<a href='FoodPref'>Food Preferences</a>"
				+ "									<a href='ProfileDonation'>Donation History</a>"
				+ "								</div>"
				+ "							</div>"
				+ "							<div id='changeProfileDiv'>"
				+ "								<div id='editProfileInfoDiv'>"
				+ "									<div id='upper-EditProfileInfoDiv'>"
				+ "										Edit Profile"
				+ "									</div>"
				+ "									<div id='lower-EditProfileInfoDiv'>"
				+ "										<span id='editProfileInfoSpan'>Change your password, gender, email, contact number, bio and address.</span>"
				+ "									</div>"
				+ "								</div>"
				+ "								<div id='passwordDiv'>"
				+ "									<div id='oldPassDiv'>"
				+ "										<label id='oldPassLabel' for='oldPassInput'>Old password</label>"
				+ "										<input type='password' id='oldPassInput' class='inputsForFill' name='oldPassInput' oninput='startedTyping(this)'>"
				+ "									</div>"
				+ "									<div id='newPassDiv'>"
				+ "										<label id='newPassLabel' for='newPassInput'>New password</label>"
				+ "										<input type='password' id='newPassInput' class='inputsForFill' name='newPassInput' oninput='startedTyping(this)'>"
				+ "									</div>"
				+ "									<div id='confirmPassDiv'>"
				+ "										<label id='confirmPassLabel' for='confirmPassInput'>Confirm password</label>"
				+ "										<input type='password' id='confirmPassInput' class='inputsForFill' name='confirmPassInput' oninput='startedTyping(this)'>"
				+ "									</div>"
				+ "									<div id='errorMsg'>Password must have at least 8 characters</div>"
				+ "								</div>"
				+ "								<div id='genderDiv'>"
				+ "									<label id='genderLabel' for='genderSelect'>Gender</label>"
				+ "									<select id='genderSelect' class='custom-select' name='genderSelect' onchange='checkSelect()'>"
				+ "										<option value='' selected disabled hidden='true'>Choose your gender</option>"
				+ "										<option value='M'>M</option>"
				+ "										<option value='F'>F</option>"
				+ "									</select>"
				+ "								</div>"
				+ "								<div id='emailDiv'>"
				+ "									<label id='emailLabel' for='emailInput'>Email</label>"
				+ "									<input type='email' id='emailInput' class='inputsForFill' name='emailInput' oninput='startedTyping(this)'>"
				+ "								</div>"
				+ "								<div id='contactNoDiv'>"
				+ "									<label id='contactNoLabel' for='contactNoInput'>Contact Number</label>"
				+ "									<input type='text' id='contactNoInput' class='inputsForFill' name='contacNoInput' oninput='startedTyping(this)'>"
				+ "								</div>"
				+ "								<div id='bioDiv'>"
				+ "									<label id='bioLabel' for='bioText'>Bio</label>"
				+ "									<textarea id='bioText' class='inputsForFill' name='bioText' oninput='startedTyping(this)'></textarea>"
				+ "								</div>"
				+ "								<div id='addressDiv'>"
				+ "									<div id='postalCodeDiv'>"
				+ "										<label id='postalCodeLabel' for='postalCodeInput'>Postal Code</label>"
				+ "										<input type='text' id='postalCodeInput' class='inputsForFill' name='postalCodeInput' oninput='startedTyping(this)'>"
				+ "									</div>"
				+ "									<div id='unitNoDiv'>"
				+ "										<label id='unitNoLabel' for='unitNoInput'>Unit Number</label>"
				+ "										<input type='text' id='unitNoInput' class='inputsForFill' name='unitNoInput' oninput='startedTyping(this)'>"
				+ "									</div>"
				+ "								</div>"
				+ "								<div id='updateBtnDiv'>"
				+ "									<button id='updateBtn' name='updateBtn' value='updateBtn' disabled>Update profile</button>"
				+ "								</div>"
				+ "							</div>"
				+ "						</div>"
				+ "					</div>"
				+ "				</form>"
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
