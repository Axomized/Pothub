package profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.Instant;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;
import org.owasp.encoder.Encode;

import database.Database;
import database.model.DatabaseUserModel;
import database.model.LogsModel;
import login.BanChecker;

@MultipartConfig(fileSizeThreshold = 1024*1024*2, maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "";
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			if (BanChecker.isThisGuyBanned(username)){
	            response.sendRedirect("Login");
	            return;
	        }
		}
		else {
			response.sendRedirect("Login");
			return;
		}
		
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
					+ "				<div id='profilePic'>");
					if (dum.getProfilePic() != 0) {
						out.print("<img src='Image/" + db.getImageByImageID(dum.getProfilePic()) + "' class='roundProfilePic' height='50' width='50'/>");
					}
					else {
						out.print("<img src='images/profile.png' class='roundProfilePic' height='50' width='50'/>");
					}
					out.print("			<span id='welcomeSpan'>Welcome, " + username + "</span>"
					+ "				</div>"
					+ "				<div id='profileDropdownDiv'>"
					+ "					<a href='Profile'>Profile</a>"
					+ "					<a href='Logout'>Logout</a>"
					+ "				</div>"
					+ "			</div>"
					+ "		</div>"
					+ "		<div id='navigation'>"
					+ "			<ul>"
					+ "				<li id='lhome'><a href='Forum'>Home</a></li>"
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
					+ "								<li><a href='EditProfile' id='linkSelected'>Edit Profile</a></li>"
					+ "								<li><a href='ChangePassword'>Change Password</a></li>"
					+ "								<li><a href='AddFoodPref'>Add Food Preferences</a></li>"
					+ "								<li><a href='RemoveFoodPref'>Remove Food Preferences</a></li>"
					+ "							</ul>"
					+ "						</div>"
					+ "					</div>"
					+ "					<div id='profileContentDiv' class='col-sm-9'>"
					+ "						<div id='changeProfileDiv'>"
					+ "							<div id='editProfileInfoDiv'>"
					+ "								<div id='upper-EditProfileInfoDiv'>"
					+ "									Edit Profile"
					+ "								</div>"
					+ "								<div id='lower-EditProfileInfoDiv'>"
					+ "									<span id='editProfileInfoSpan'>Change your gender, contact number, bio and address.</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<form id='profileForm' autocomplete='off' enctype='multipart/form-data' method='post'>"
					+ "								<div id='editProfileDiv' class='row'>"
					+ "									<div id='userInfoDiv' class='col-sm-9'>"
					+ "										<div id='filterDiv' class='divWrap'>"
					+ "											<label id='filterLabel' class='custom-control custom-checkbox' for='checkFilter'>");
					if (dum.isFiltered()) {
						out.print("<input type='checkbox' id='checkFilter' class='custom-control-input' name='checkFilter' checked onclick='isBoxChecked()'>");
					}
					else {
						out.print("<input type='checkbox' id='checkFilter' class='custom-control-input' name='checkFilter' onclick='isBoxChecked()'>");
					}
					out.print("										<span class='custom-control-indicator'></span>"
					+ "												<span class='custom-control-description'>Filter by food preferences</span>"
					+ "											</label>"
					+ "											<p id='filterInfoText'>When you check this box, forum posts will be filtered according to your food preferences.</p>"
					+ "										</div>"
					+ "										<div id='genderDiv' class='divWrap'>"
					+ "											<label id='genderLabel' for='genderSelect'>Gender</label>"
					+ "											<select id='genderSelect' class='custom-select' name='genderSelect' onchange='checkSelect()'>");
					if (dum.getGender() == 'M') {
						out.print("<option value='' selected disabled hidden='true'>Male</option>");
					}
					else if (dum.getGender() == 'F') {
						out.print("<option value='' selected disabled hidden='true'>Female</option>");
					}
					out.print("										<option value='Male'>Male</option>"
					+ "												<option value='Female'>Female</option>"
					+ "											</select>"
					+ "										</div>"
					+ "										<div id='contactNoDiv' class='divWrap'>"
					+ "											<label id='contactNoLabel' for='contactNoInput'>Contact Number</label>"
					+ "											<input type='text' id='contactNoInput' class='inputsForFill' name='contactNoInput' maxlength='8' value='" + Encode.forHtml(dum.getContact_No()) + "' oninput='startedTyping(this); onlyNumbers(this)'>"
					+ "										</div>"
					+ "										<div id='bioDiv' class='divWrap'>"
					+ "											<label id='bioLabel' for='bioText'>Bio</label>"
					+ "											<textarea id='bioText' class='inputsForFill' name='bioText' maxlength='255' oninput='startedTyping(this)'>" + Encode.forHtml(dum.getBio()) + "</textarea>"
					+ "											<div id='bioInfoText'>Only a maximum of 255 characters.</div>"
					+ "										</div>"
					+ "										<div id='addressDiv' class='divWrap'>"
					+ "											<div id='postalCodeDiv'>"
					+ "												<label id='postalCodeLabel' for='postalCodeInput'>Postal Code</label>"
					+ "												<input type='text' id='postalCodeInput' class='inputsForFill' name='postalCodeInput' maxlength='6' value='" + Encode.forHtml(dum.getAddress()) + "' oninput='startedTyping(this); onlyNumbers(this)'>"
					+ "											</div>"
					+ "											<div id='unitNoDiv' class='innerDiv'>"
					+ "												<label id='unitNoLabel' for='unitNoInput'>Unit Number</label>"
					+ "												<input type='text' id='unitNoInput' class='inputsForFill' name='unitNoInput' value='" + Encode.forHtml(dum.getUnitNo()) + "' oninput='startedTyping(this)'>"
					+ "											</div>"
					+ "										</div>"
					+ "									</div>"
					+ "									<div id='userPicDiv' class='col-sm-3'>"
					+ "										<div id='profileImgDiv'>");
					if (dum.getProfilePic() != 0) {
						out.print("<img src='Image/" + db.getImageByImageID(dum.getProfilePic()) + "' id='profilePicThumbnail' height='150' width='150'/>");
					}
					else {
						out.print("<img src='images/profile.png' id='profilePicThumbnail' height='150' width='150'/>");
					}
					out.print("								</div>"
					+ "										<div id='editProfileImgDiv'>"
					+ "											<label id='profilePicLabel' for='profilePicFile'>"
					+ "											<input type='file' id='profilePicFile' name='profilePicFile' accept='image/*' onchange='checkFile()'>"
					+ "											<span id='profilePicSpan'>Upload new picture</span>"
					+ "											</label>"
					+ "										</div>"
					+ "									</div>"
					+ "								</div>"
					+ "								<div id='updateBtnDiv'>"
					+ "									<input type='submit' id='updateBtn' name='updateBtn' value='Update profile' disabled>"
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
			if (BanChecker.isThisGuyBanned(username)){
	            response.sendRedirect("Login");
	            return;
	        }
		}
		else {
			response.sendRedirect("Login");
			return;
		}
		
		try {
			Database db = new Database(2);
			ProfileUpdate profileUpdate = new ProfileUpdate(username);
			LogsModel lm = new LogsModel();
			String isFiltered = request.getParameter("checkFilter");
			String gender = request.getParameter("genderSelect");
			String contact_No = request.getParameter("contactNoInput");
			String bio = request.getParameter("bioText");
			String address = request.getParameter("postalCodeInput");
			String unitNo = request.getParameter("unitNoInput");
			Part profilePicPart = request.getPart("profilePicFile");
			String profilePicName = Paths.get(profilePicPart.getSubmittedFileName()).getFileName().toString();
			byte[] profilePicByte = IOUtils.toByteArray(profilePicPart.getInputStream());
			boolean updateProfileSuccess = false;
			boolean contact_NoError = false;
			boolean bioError = false;
			boolean addressError = false;
			boolean unitNoError = false;
			
			if (validateInputs(isFiltered, gender, contact_No, bio, address, unitNo)) {
				if (isFiltered != null && !isFiltered.isEmpty()) {
					profileUpdate.setFiltered(true);
				}
				else {
					profileUpdate.setFiltered(false);
				}
				if (gender != null && !gender.isEmpty()) {
					profileUpdate.setGender(gender);
				}
				if (contact_No != null && !contact_No.isEmpty()) {
					if (contact_No.length() == 8 && contact_No.matches("\\d+")) {
						profileUpdate.setContact_No(contact_No);
					}
					else {
						contact_NoError = true;
					}
				}
				if (bio != null && !bio.isEmpty()) {
					if (bio.length() <= 255) {
						profileUpdate.setBio(bio);
					}
					else {
						bioError = true;
					}
				}
				if (address != null && !address.isEmpty()) {
					if (address.length() == 6 && address.matches("\\d+")) {
						profileUpdate.setAddress(address);
					}
					else {
						addressError = true;
					}
				}
				if (unitNo != null && !unitNo.isEmpty()) {
					if (unitNo.length() == 7) {
						profileUpdate.setUnitNo(unitNo);
					}
					else {
						unitNoError = true;
					}
				}
				if (profilePicName != null && !profilePicName.isEmpty()) {
					profileUpdate.setProfilePicName(profilePicName);
					profileUpdate.setProfilePicByte(profilePicByte);
				}
				
				if (db.updateUserProfile(profileUpdate)) {
					updateProfileSuccess = true;
					lm.setiGN(username);
					lm.setLogDate(Timestamp.from(Instant.now()));
					lm.setiPAddress(lm.getClientIP(request));
					lm.setLogType("Profile");
					lm.setLogActivity(username + " edited profile information");
					db.insertLogs(lm);
				}
			}
			else {
				System.out.println("All has nothing");
			}
			DatabaseUserModel dum = db.getUserProfile(username);
			
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
					+ "				<div id='profilePic'>");
					if (dum.getProfilePic() != 0) {
						out.print("<img src='Image/" + db.getImageByImageID(dum.getProfilePic()) + "' class='roundProfilePic' height='50' width='50'/>");
					}
					else {
						out.print("<img src='images/profile.png' class='roundProfilePic' height='50' width='50'/>");
					}
					out.print("			<span id='welcomeSpan'>Welcome, " + username + "</span>"
					+ "				</div>"
					+ "				<div id='profileDropdownDiv'>"
					+ "					<a href='Profile'>Profile</a>"
					+ "					<a href='Logout'>Logout</a>"
					+ "				</div>"
					+ "			</div>"
					+ "		</div>"
					+ "		<div id='navigation'>"
					+ "			<ul>"
					+ "				<li id='lhome'><a href='Forum'>Home</a></li>"
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
					+ "								<li><a href='EditProfile' id='linkSelected'>Edit Profile</a></li>"
					+ "								<li><a href='ChangePassword'>Change Password</a></li>"
					+ "								<li><a href='AddFoodPref'>Add Food Preferences</a></li>"
					+ "								<li><a href='RemoveFoodPref'>Remove Food Preferences</a></li>"
					+ "							</ul>"
					+ "						</div>"
					+ "					</div>"
					+ "					<div id='profileContentDiv' class='col-sm-9'>"
					+ "						<div id='changeProfileDiv'>"
					+ "							<div id='editProfileInfoDiv'>"
					+ "								<div id='upper-EditProfileInfoDiv'>"
					+ "									Edit Profile"
					+ "								</div>"
					+ "								<div id='lower-EditProfileInfoDiv'>"
					+ "									<span id='editProfileInfoSpan'>Change your gender, contact number, bio and address.</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<form id='profileForm' autocomplete='off' enctype='multipart/form-data' method='post'>"
					+ "								<div id='editProfileDiv' class='row'>"
					+ "									<div id='userInfoDiv' class='col-sm-9'>");
					if (updateProfileSuccess && !contact_NoError && !bioError && !addressError && !unitNoError) {
						out.print("<div id='updateSuccessDiv'>"
								+ "	<p>Updated profile successfully.</p>"
								+ "</div>");
					}
					out.println("							<div id='filterDiv' class='divWrap'>"
							+ "									<label id='filterLabel' class='custom-control custom-checkbox' for='checkFilter'>");
					if (dum.isFiltered()) {
						out.print("<input type='checkbox' id='checkFilter' class='custom-control-input' name='checkFilter' checked onclick='isBoxChecked()'>");
					}
					else {
						out.print("<input type='checkbox' id='checkFilter' class='custom-control-input' name='checkFilter' onclick='isBoxChecked()'>");
					}
							out.print("								<span class='custom-control-indicator'></span>"
							+ "										<span class='custom-control-description'>Filter by food preferences</span>"
							+ "									</label>"
							+ "									<p id='filterInfoText'>When you check this box, forum posts will be filtered according to your food preferences.</p>"
							+ "								</div>"
							+ "								<div id='genderDiv' class='divWrap'>"
							+ "									<label id='genderLabel' for='genderSelect'>Gender</label>"
							+ "									<select id='genderSelect' class='custom-select' name='genderSelect' onchange='checkSelect()'>");
					if (dum.getGender() == 'M') {
						out.print("<option value='' selected disabled hidden='true'>Male</option>");
					}
					else if (dum.getGender() == 'F') {
						out.print("<option value='' selected disabled hidden='true'>Female</option>");
					}
					out.print("										<option value='Male'>Male</option>"
					+ "												<option value='Female'>Female</option>"
					+ "											</select>"
					+ "										</div>"
					+ "										<div id='contactNoDiv' class='divWrap'>"
					+ "											<label id='contactNoLabel' for='contactNoInput'>Contact Number</label>"
					+ "											<input type='text' id='contactNoInput' class='inputsForFill' name='contactNoInput' maxlength='8' value='" + Encode.forHtml(dum.getContact_No()) + "' oninput='startedTyping(this); onlyNumbers(this)'>");
					if (contact_NoError) {
						out.print("<div class='errorMsg'>Only a maximum of 8 numbers.</div>");
					}
					out.print("								</div>"
					+ "										<div id='bioDiv' class='divWrap'>"
					+ "											<label id='bioLabel' for='bioText'>Bio</label>"
					+ "											<textarea id='bioText' class='inputsForFill' name='bioText' maxlength='255' oninput='startedTyping(this)'>" + Encode.forHtml(dum.getBio()) + "</textarea>");
					if (bioError) {
						out.print("<div id='bioInfoText' style='color: #f74225; font-weight: 600;'>Only a maximum of 255 characters.</div>");
					}
					else {
						out.print("<div id='bioInfoText'>Only a maximum of 255 characters.</div>");
					}
					out.print("								</div>"
					+ "										<div id='addressDiv' class='divWrap'>"
					+ "											<div id='postalCodeDiv'>"
					+ "												<label id='postalCodeLabel' for='postalCodeInput'>Postal Code</label>"
					+ "												<input type='text' id='postalCodeInput' class='inputsForFill' name='postalCodeInput' maxlength='6' value='" + Encode.forHtml(dum.getAddress()) + "' oninput='startedTyping(this); onlyNumbers(this)'>");
					if (addressError) {
						out.print("<div class='errorMsg'>Only a maximum of 6 numbers.</div>");
					}
					out.print("									</div>"
					+ "											<div id='unitNoDiv' class='innerDiv'>"
					+ "												<label id='unitNoLabel' for='unitNoInput'>Unit Number</label>"
					+ "												<input type='text' id='unitNoInput' class='inputsForFill' name='unitNoInput' value='" + Encode.forHtml(dum.getUnitNo()) + "' oninput='startedTyping(this)'>");
					if (unitNoError) {
						out.print("<div class='errorMsg'>Invalid unit number.</div>");
					}
					out.print("									</div>"
					+ "										</div>"
					+ "									</div>"
					+ "									<div id='userPicDiv' class='col-sm-3'>"
					+ "										<div id='profileImgDiv'>");
					if (dum.getProfilePic() != 0) {
						out.print("<img src='Image/" + db.getImageByImageID(dum.getProfilePic()) + "' id='profilePicThumbnail' height='150' width='150'/>");
					}
					else {
						out.print("<img src='images/profile.png' id='profilePicThumbnail' height='150' width='150'/>");
					}
					out.print("								</div>"
					+ "										<div id='editProfileImgDiv'>"
					+ "											<label id='profilePicLabel' for='profilePicFile'>"
					+ "											<input type='file' id='profilePicFile' name='profilePicFile' accept='image/*' onchange='checkFile()'>"
					+ "											<span id='profilePicSpan'>Upload new picture</span>"
					+ "											</label>"
					+ "										</div>"
					+ "									</div>"
					+ "								</div>"
					+ "								<div id='updateBtnDiv'>"
					+ "									<input type='submit' id='updateBtn' name='updateBtn' value='Update profile' disabled>"
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
	
	private boolean validateInputs(String isFiltered, String gender, String contact_No, String bio, String address, String unitNo) {
		boolean isNotNull = false;
		if ((isFiltered != null && !isFiltered.isEmpty()) || (gender != null && !gender.isEmpty()) || (contact_No != null && !contact_No.isEmpty()) || (bio != null && !bio.isEmpty()) || (address != null && !address.isEmpty() 
				|| (unitNo != null && !unitNo.isEmpty()))) {
			isNotNull = true;
		}
		return isNotNull;
	}

}
