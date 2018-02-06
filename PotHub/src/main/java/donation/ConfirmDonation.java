package donation;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.DatabaseUserModel;
import database.model.DonationModel;
import database.model.LogsModel;
import database.model.TemporaryStoreModel;
import login.BanChecker;

public class ConfirmDonation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmDonation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "";
		Integer pinAttempts = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			if ((Integer)session.getAttribute("pinAttempts") == null) {
				session.setAttribute("pinAttempts", 0);
				pinAttempts = (Integer)session.getAttribute("pinAttempts");
			}
			else {
				pinAttempts = (Integer)session.getAttribute("pinAttempts");
			}
			
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
			TemporaryStoreModel tsmCheck = db.getTempStore(username);
			Timestamp currentTime = Timestamp.from(Instant.now());
			String errorMsg = "";
			boolean resendPIN = false;
			if (tsmCheck == null) {
				response.sendRedirect("Donation");
				return;
			}
			else {
				if (currentTime.after(tsmCheck.getTemporaryTime())) {
					resendPIN = true;
					errorMsg = "PIN has expired. Click to send a new PIN.";
				}
				else if (pinAttempts > 2) {
					resendPIN = true;
					errorMsg = "You have exceeded maximum amount of tries. Click to send a new PIN.";
				}
			}
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Confirm Donation</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/ConfirmDonation.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/ConfirmDonation.css' />"
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
					+ "				<div id='content'>"
					+ "					<form id='donationPINForm' autocomplete='off' method='post'>"
					+ "						<div id='confirmDonationDiv'>"
					+ "							<div id='onTopDiv'>"
					+ "								Enter the PIN sent to your email"
					+ "							</div>");
					if (resendPIN) {
						out.print("<div id='belowDiv'>"
								+ "	<input type='password' id='pinInput1' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput1\", \"pinInput2\")'>"
								+ "	<input type='password' id='pinInput2' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput1\", \"pinInput3\")'>"
								+ "	<input type='password' id='pinInput3' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput2\", \"pinInput4\")'>"
								+ "	<input type='password' id='pinInput4' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput3\", \"pinInput5\")'>"
								+ "	<input type='password' id='pinInput5' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput4\", \"pinInput6\")'>"
								+ "	<input type='password' id='pinInput6' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput5\", \"pinInput6\")'>"
								+ "</div>"
								+ "<div id='errorMsg'>" + errorMsg + "</div>"
								+ "<div id='buttonsDiv'>"
								+ "	<input type='submit' id='resendBtn' name='resendBtn' value='Resend PIN' onclick='onResend()'>"
								+ "	<input type='submit' id='confirmBtn' name='confirmBtn' value='Confirm' style='cursor:not-allowed;' disabled>"
								+ "</div>");
					}
					else {
						out.print("<div id='belowDiv'>"
								+ "	<input type='password' id='pinInput1' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput1\", \"pinInput2\")'>"
								+ "	<input type='password' id='pinInput2' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput1\", \"pinInput3\")'>"
								+ "	<input type='password' id='pinInput3' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput2\", \"pinInput4\")'>"
								+ "	<input type='password' id='pinInput4' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput3\", \"pinInput5\")'>"
								+ "	<input type='password' id='pinInput5' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput4\", \"pinInput6\")'>"
								+ "	<input type='password' id='pinInput6' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput5\", \"pinInput6\")'>"
								+ "	</div>");
						out.print("<div id='buttonsDiv'>"
								+ "	<input type='submit' id='confirmBtn' name='confirmBtn' value='Confirm'>"
								+ "</div>");
					}
					out.print("				</div>"
					+ "					</form>"
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
		Integer pinAttempts = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			pinAttempts = (Integer)session.getAttribute("pinAttempts");
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
			DatabaseUserModel dum = db.getUserProfile(username);
			TemporaryStoreModel tsm = db.getTempStore(username);
			DonationModel dm = new DonationModel();
			LogsModel lm = new LogsModel();
			HashPIN hp = new HashPIN();
			
			boolean resendPIN = false;
			boolean incorrectPIN = false;
			Timestamp currentTime = Timestamp.from(Instant.now());
			String[] pinArray = request.getParameterValues("pinInput");
			String errorMessage = "";
			
			if (tsm != null ) {
				if (request.getParameter("confirmBtn") != null && !request.getParameter("confirmBtn").isEmpty()) {
					StringBuilder builder = new StringBuilder();
					for (String s : pinArray) {
					    builder.append(s);
					}
					String pinNumber = builder.toString();
					String iGN = tsm.getiGN();
					String hashedPIN = hp.getHashedPIN(pinNumber, hp.getDecodedSalt(tsm.getTemporarySalt()));
					
					if (currentTime.before(tsm.getTemporaryTime())) {
						if (hashedPIN.equals(tsm.getTemporaryPIN())) {
							session.setAttribute("pinAttempts", 0);
							dm.setDonationAmount(tsm.getTemporaryAmount());
							dm.setDonationDate(Timestamp.from(Instant.now()));
							dm.setiGN(iGN);
							dm.setOnBehalf(tsm.getTemporaryOnBehalf());
							db.insertDonation(dm);
							if (tsm.getTemporaryOnBehalf() != null && !tsm.getTemporaryOnBehalf().isEmpty()) {
								DatabaseUserModel dumOnBehalf = db.getUserProfile(tsm.getTemporaryOnBehalf());
								db.updateTotalDonation(dumOnBehalf.getTotalDonation().add(tsm.getTemporaryAmount()), tsm.getTemporaryOnBehalf());
								lm.setiGN(username);
								lm.setLogDate(Timestamp.from(Instant.now()));
								lm.setiPAddress(lm.getClientIP(request));
								lm.setLogType("Donation");
								lm.setLogActivity(username + " donated " + "$" + tsm.getTemporaryAmount() + " on behalf of " + tsm.getTemporaryOnBehalf());
								db.insertLogs(lm);
								DatabaseUserModel dumOnBehalfAfter = db.getUserProfile(tsm.getTemporaryOnBehalf());
								if ((dumOnBehalfAfter.getTotalDonation().compareTo(new BigDecimal("10")) >= 0) && (!dumOnBehalfAfter.isPriviledged())) {
									db.updateIsPrivileged(true, tsm.getTemporaryOnBehalf());
									lm.setiGN(tsm.getTemporaryOnBehalf());
									lm.setLogDate(Timestamp.from(Instant.now()));
									lm.setiPAddress(lm.getClientIP(request));
									lm.setLogType("Others");
									lm.setLogActivity(tsm.getTemporaryOnBehalf() + " became privileged");
									db.insertLogs(lm);
								}
							}
							else {
								db.updateTotalDonation(dum.getTotalDonation().add(tsm.getTemporaryAmount()), username);
								lm.setiGN(username);
								lm.setLogDate(Timestamp.from(Instant.now()));
								lm.setiPAddress(lm.getClientIP(request));
								lm.setLogType("Donation");
								lm.setLogActivity(username + " donated " + "$" + tsm.getTemporaryAmount());
								db.insertLogs(lm);
								DatabaseUserModel dumAfter = db.getUserProfile(username);
								if ((dumAfter.getTotalDonation().compareTo(new BigDecimal("10")) >= 0) && (!dumAfter.isPriviledged())) {
									db.updateIsPrivileged(true, username);
									lm.setiGN(username);
									lm.setLogDate(Timestamp.from(Instant.now()));
									lm.setiPAddress(lm.getClientIP(request));
									lm.setLogType("Others");
									lm.setLogActivity(username + " became privileged");
									db.insertLogs(lm);
								}
							}
							db.deleteFromTempStore(username);
							response.sendRedirect("DonationSuccess");
							return;
						}
						else {
							incorrectPIN = true;
							errorMessage = "PIN is incorrect.";
							session.setAttribute("pinAttempts", ++pinAttempts);
							
							if (pinAttempts > 2) {
								resendPIN = true;
								errorMessage = "You have exceeded maximum amount of tries. Click to send a new PIN.";
							}
						}
					}
					else {
						resendPIN = true;
						errorMessage = "PIN has expired. Click to send a new PIN.";
					}
				}
				else if (request.getParameter("resendBtn") != null && !request.getParameter("resendBtn").isEmpty()) {
					session.setAttribute("pinAttempts", 0);
					TemporaryStoreModel tsmResend = new TemporaryStoreModel();
					SendEmail se = new SendEmail();
					byte[] salt = hp.createSalt();
					String encodedSalt = hp.getEncodedSalt(salt);
					String pinNo = tsmResend.generatePIN();
					String hashedPIN = hp.getHashedPIN(pinNo, salt);
					
					tsmResend.setiGN(tsm.getiGN());
					tsmResend.setTemporaryPIN(hashedPIN);
					tsmResend.setTemporarySalt(encodedSalt);
					tsmResend.setTemporaryTime(tsmResend.getTime5MinsLater());
					if (db.updateTempStore(tsmResend)) {
						se.sendEmail(dum.getEmail(), pinNo);
					}
				}
			}
			else {
				response.sendRedirect("DonationSuccess");
				return;
			}
			
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Confirm Donation</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/ConfirmDonation.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/ConfirmDonation.css' />"
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
					+ "				<div id='content'>"
					+ "					<form id='donationPINForm' autocomplete='off' method='post'>"
					+ "						<div id='confirmDonationDiv'>"
					+ "							<div id='onTopDiv'>"
					+ "								Enter the PIN sent to your email"
					+ "							</div>");
					if (resendPIN) {
						out.print("<div id='belowDiv'>"
								+ "	<input type='password' id='pinInput1' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput1\", \"pinInput2\")'>"
								+ "	<input type='password' id='pinInput2' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput1\", \"pinInput3\")'>"
								+ "	<input type='password' id='pinInput3' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput2\", \"pinInput4\")'>"
								+ "	<input type='password' id='pinInput4' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput3\", \"pinInput5\")'>"
								+ "	<input type='password' id='pinInput5' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput4\", \"pinInput6\")'>"
								+ "	<input type='password' id='pinInput6' name='pinInput' maxlength='1' disabled required oninput='moveToNext(this, \"pinInput5\", \"pinInput6\")'>"
								+ "</div>"
								+ "<div id='errorMsg'>" + errorMessage + "</div>"
								+ "<div id='buttonsDiv'>"
								+ "	<input type='submit' id='resendBtn' name='resendBtn' value='Resend PIN' onclick='onResend()'>"
								+ "	<input type='submit' id='confirmBtn' name='confirmBtn' value='Confirm' style='cursor:not-allowed;' disabled>"
								+ "</div>");
					}
					else {
						out.print("<div id='belowDiv'>"
								+ "	<input type='password' id='pinInput1' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput1\", \"pinInput2\")'>"
								+ "	<input type='password' id='pinInput2' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput1\", \"pinInput3\")'>"
								+ "	<input type='password' id='pinInput3' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput2\", \"pinInput4\")'>"
								+ "	<input type='password' id='pinInput4' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput3\", \"pinInput5\")'>"
								+ "	<input type='password' id='pinInput5' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput4\", \"pinInput6\")'>"
								+ "	<input type='password' id='pinInput6' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput5\", \"pinInput6\")'>"
								+ "	</div>");
								if (incorrectPIN) {
									out.print("<div id='errorMsg'>" + errorMessage + "</div>");
								}
						out.print("<div id='buttonsDiv'>"
								+ "	<input type='submit' id='confirmBtn' name='confirmBtn' value='Confirm'>"
								+ "</div>");
					}
					out.print("				</div>"
					+ "					</form>"
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
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
