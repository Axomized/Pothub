package profile;

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
import database.model.LoginModel;
import login.PBKDF2;

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePassword() {
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
			DatabaseUserModel dum = db.getUserProfile(username);
			
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Change Password</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/ChangePassword.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/ChangePassword.css' />"
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
						out.print("<img src='Image/" + db.getImageByImageID(dum.getProfilePic()) + "' height='50' width='50'/>");
					}
					else {
						out.print("<img src='images/profile.png' height='50' width='50'/>");
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
					+ "								<li><a href='EditProfile'>Edit Profile</a></li>"
					+ "								<li><a href='ChangePassword' id='linkSelected'>Change Password</a></li>"
					+ "								<li><a href='AddFoodPref'>Add Food Preferences</a></li>"
					+ "								<li><a href='RemoveFoodPref'>Remove Food Preferences</a></li>"
					+ "							</ul>"
					+ "						</div>"
					+ "					</div>"
					+ "					<div id='profileContentDiv' class='col-sm-9'>"
					+ "						<div id='changeProfileDiv'>"
					+ "							<div id='editProfileInfoDiv'>"
					+ "								<div id='upper-EditProfileInfoDiv'>"
					+ "									Change Password"
					+ "								</div>"
					+ "								<div id='lower-EditProfileInfoDiv'>"
					+ "									<span id='editProfileInfoSpan'>Change your password.</span>"
					+ "								</div>"
					+ "							</div>"
					+ "							<form id='changePassForm' autocomplete='off' method='post'>"
					+ "								<div id='passwordDiv' class='divWrap'>"
					+ "									<div id='oldPassDiv'>"
					+ "										<label id='oldPassLabel' for='oldPassInput'>Old password</label>"
					+ "										<input type='password' id='oldPassInput' class='inputsForFill' name='oldPassInput' required>"
					+ "									</div>"
					+ "									<div id='newPassDiv' class='innerDiv'>"
					+ "										<label id='newPassLabel' for='newPassInput'>New password</label>"
					+ "										<input type='password' id='newPassInput' class='inputsForFill' name='newPassInput' required>"
					+ "									</div>"
					+ "									<div id='confirmPassDiv' class='innerDiv'>"
					+ "										<label id='confirmPassLabel' for='confirmPassInput'>Confirm password</label>"
					+ "										<input type='password' id='confirmPassInput' class='inputsForFill' name='confirmPassInput' required>"
					+ "									</div>"
					+ "								</div>"
					+ "								<div id='updateBtnDiv'>"
					+ "									<input type='submit' id='updateBtn' name='updateBtn' value='Update password'>"
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
			DatabaseUserModel dum = db.getUserProfile(username);
			PBKDF2 hashClass = new PBKDF2();
			LoginModel lm = db.getUserPassSalt(dum.getEmail());
			LoginModel lmUpdate = new LoginModel();
			byte[] decodedSalt = PBKDF2.fromHex(lm.getSalt());
			byte[] newSalt = hashClass.createSalt();
			String newEncodedSalt = PBKDF2.toHex(newSalt);
			String oldPass = request.getParameter("oldPassInput");
			String newPass = request.getParameter("newPassInput");
			String confirmPass = request.getParameter("confirmPassInput");
			boolean incorrectPass = false;
			boolean passReq = false;
			boolean oldNewSame = false;
			boolean passNotMatch = false;
			boolean passChangeSuccess = false;
			
			if (validateInputString(oldPass, newPass, confirmPass)) {
				if ((hashClass.getHashedPass(oldPass, decodedSalt).equals(lm.getPassword())) && (!newPass.equals(oldPass) && newPass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) && (confirmPass.equals(newPass))) {
					lmUpdate.setPassword(hashClass.getHashedPass(confirmPass, newSalt));
					lmUpdate.setSalt(newEncodedSalt);
					lmUpdate.setEmail(dum.getEmail());
					if (db.updateUserPassAndSalt(lmUpdate)) {
						passChangeSuccess = true;
					}
					System.out.println("Can change password successfully");
				}
				if (!hashClass.getHashedPass(oldPass, decodedSalt).equals(lm.getPassword())) {
					incorrectPass = true;
				}
				if (newPass.equals(oldPass)) {
					oldNewSame = true;
				}
				if (!newPass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
					passReq = true;
				}
				if (!confirmPass.equals(newPass)) {
					passNotMatch = true;
				}
			}
			
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Change Password</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/ChangePassword.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/ChangePassword.css' />"
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
						out.print("<img src='Image/" + db.getImageByImageID(dum.getProfilePic()) + "' height='50' width='50'/>");
					}
					else {
						out.print("<img src='images/profile.png' height='50' width='50'/>");
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
					+ "								<li><a href='EditProfile'>Edit Profile</a></li>"
					+ "								<li><a href='ChangePassword' id='linkSelected'>Change Password</a></li>"
					+ "								<li><a href='AddFoodPref'>Add Food Preferences</a></li>"
					+ "								<li><a href='RemoveFoodPref'>Remove Food Preferences</a></li>"
					+ "							</ul>"
					+ "						</div>"
					+ "					</div>"
					+ "					<div id='profileContentDiv' class='col-sm-9'>"
					+ "						<div id='changeProfileDiv'>"
					+ "							<div id='editProfileInfoDiv'>"
					+ "								<div id='upper-EditProfileInfoDiv'>"
					+ "									Change Password"
					+ "								</div>"
					+ "								<div id='lower-EditProfileInfoDiv'>"
					+ "									<span id='editProfileInfoSpan'>Change your password.</span>"
					+ "								</div>"
					+ "							</div>");
					if (passChangeSuccess) {
						out.print("<div id='updateSuccessDiv'>"
								+ "	<p>Password changed successfully.</p>"
								+ "</div>");
					}
					out.print("					<form id='changePassForm' autocomplete='off' method='post'>"
					+ "								<div id='passwordDiv' class='divWrap'>"
					+ "									<div id='oldPassDiv'>"
					+ "										<label id='oldPassLabel' for='oldPassInput'>Old password</label>"
					+ "										<input type='password' id='oldPassInput' class='inputsForFill' name='oldPassInput' required>");
					if (incorrectPass) {
						out.print("<div class='errorMsg'>Password is incorrect.</div>");
					}
					out.print("							</div>"
					+ "									<div id='newPassDiv' class='innerDiv'>"
					+ "										<label id='newPassLabel' for='newPassInput'>New password</label>"
					+ "										<input type='password' id='newPassInput' class='inputsForFill' name='newPassInput' required>");
					if (oldNewSame) {
						out.print("<div class='errorMsg'>New password cannot be the same as old one.</div>");
					}
					if (passReq) {
						out.print("<div class='errorMsg'>Password must have at least 8 characters and contain at least a number, lower and upper character.</div>");
					}
					out.print("							</div>"
					+ "									<div id='confirmPassDiv' class='innerDiv'>"
					+ "										<label id='confirmPassLabel' for='confirmPassInput'>Confirm password</label>"
					+ "										<input type='password' id='confirmPassInput' class='inputsForFill' name='confirmPassInput' required>");
					if (passNotMatch) {
						out.print("<div class='errorMsg'>Password does not match the new one.</div>");
					}
					out.print("							</div>"
					+ "								</div>"
					+ "								<div id='updateBtnDiv'>"
					+ "									<input type='submit' id='updateBtn' name='updateBtn' value='Update password'>"
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
	
	private boolean validateInputString(String oldPass, String newPass, String confirmPass) {
		if ((oldPass != null && !oldPass.isEmpty()) && (newPass != null && !newPass.isEmpty()) && (confirmPass != null && !confirmPass.isEmpty())) {
			return true;
		}
		return false;
	}

}
