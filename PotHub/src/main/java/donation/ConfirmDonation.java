package donation;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.DonationModel;
import database.model.TemporaryStoreModel;

public class ConfirmDonation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmDonation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.setAttribute("pinAttempts", 0);
		
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
				+ "				<div id='content'>"
				+ "					<form id='donationPINForm' autocomplete='off' method='post'>"
				+ "						<div id='confirmDonationDiv'>"
				+ "							<div id='onTopDiv'>"
				+ "								Enter the PIN sent to your email"
				+ "							</div>"
				+ "							<div id='belowDiv'>"
				+ "								<input type='password' id='pinInput1' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput1\", \"pinInput2\")'>"
				+ "								<input type='password' id='pinInput2' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput1\", \"pinInput3\")'>"
				+ "								<input type='password' id='pinInput3' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput2\", \"pinInput4\")'>"
				+ "								<input type='password' id='pinInput4' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput3\", \"pinInput5\")'>"
				+ "								<input type='password' id='pinInput5' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput4\", \"pinInput6\")'>"
				+ "								<input type='password' id='pinInput6' name='pinInput' maxlength='1' required oninput='moveToNext(this, \"pinInput5\", \"pinInput6\")'>"
				+ "							</div>"
				+ "							<div id='buttonsDiv'>"
				+ "								<input type='submit' id='resendBtn' name='resendBtn' value='Resend PIN' onclick='onResend()'>"
				+ "								<input type='submit' id='confirmBtn' name='confirmBtn' value='Confirm'>"
				+ "							</div>"
				+ "						</div>"
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String username = (String)request.getAttribute("username");
			int pinAttempts = (Integer)request.getAttribute("pinAttempts");
			Database db = new Database(2);
			TemporaryStoreModel tsm = db.getTempStore(username);
			DonationModel dm = new DonationModel();
			HashPIN hp = new HashPIN();
			
			boolean resendPIN = false;
			boolean incorrectPIN = false;
			Timestamp currentTime = Timestamp.from(Instant.now());
			
			String[] PIN = request.getParameterValues("pinInput");
			StringBuilder builder = new StringBuilder();
			for (String s : PIN) {
			    builder.append(s);
			}
			String pinNumber = builder.toString();
			
			if (request.getParameter("confirmBtn") != null && !request.getParameter("confirmBtn").isEmpty()) {
				String iGN = tsm.getiGN();
				String hashedPIN = hp.getHashedPIN(pinNumber, hp.getDecodedSalt(tsm.getTemporarySalt()));
				
				if (currentTime.before(tsm.getTemporaryTime())) {
					if (hashedPIN.equals(tsm.getTemporaryPIN())) {
						dm.setDonation_Amount(tsm.getTemporaryAmount());
						dm.setDonation_Date(Timestamp.from(Instant.now()));
						dm.setiGN(iGN);
						dm.setOnBehalf(tsm.getTemporaryOnBehalf());
						db.insertDonation(dm);
					}
					else {
						incorrectPIN = true;
						session.setAttribute("pinAttempts", pinAttempts++);
						
						if (pinAttempts > 3) {
							resendPIN = true;
							session.setAttribute("pinAttempts", 0);
						}
					}
				}
				else {
					resendPIN = true;
					db.deleteFromTempStore(username);
				}
			}
			else if (request.getParameter("resendBtn") != null && !request.getParameter("resendBtn").isEmpty()) {
				System.out.println("Resend button clicked");
				TemporaryStoreModel tsmResend = new TemporaryStoreModel();
				SendEmail se = new SendEmail();
				byte[] salt = hp.createSalt();
				String encodedSalt = hp.getEncodedSalt(salt);
				String pinNo = tsmResend.generatePIN();
				String hashedPIN = hp.getHashedPIN(pinNo, salt);
				se.sendEmail("", pinNo);
				tsmResend.setiGN(tsm.getiGN());
				tsmResend.setTemporaryPIN(hashedPIN);
				tsmResend.setTemporarySalt(encodedSalt);
				tsmResend.setTemporaryTime(tsmResend.getTime5MinsLater());
				db.updateTempStore(tsmResend);
				doGet(request, response);
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
					+ "				<div id='content'>"
					+ "					<form id='donationPINForm' autocomplete='off' method='post'>"
					+ "						<div id='confirmDonationDiv'>"
					+ "							<div id='onTopDiv'>"
					+ "								Enter the PIN sent to your email"
					+ "							</div>"
					+ "							<div id='belowDiv'>"
					+ "								<input type='password' id='pinInput1' name='pinInput' maxlength='1' required oninput='moveToNext(this, 'pinInput1', 'pinInput2')'>"
					+ "								<input type='password' id='pinInput2' name='pinInput' maxlength='1' required oninput='moveToNext(this, 'pinInput1', 'pinInput3')'>"
					+ "								<input type='password' id='pinInput3' name='pinInput' maxlength='1' required oninput='moveToNext(this, 'pinInput2', 'pinInput4')'>"
					+ "								<input type='password' id='pinInput4' name='pinInput' maxlength='1' required oninput='moveToNext(this, 'pinInput3', 'pinInput5')'>"
					+ "								<input type='password' id='pinInput5' name='pinInput' maxlength='1' required oninput='moveToNext(this, 'pinInput4', 'pinInput6')'>"
					+ "								<input type='password' id='pinInput6' name='pinInput' maxlength='1' required oninput='moveToNext(this, 'pinInput5', 'pinInput6')'>"
					+ "							</div>");
					if (incorrectPIN = true) {
						out.print("<div id='errorMsg'>Incorrect PIN entered</div>");
					}
					out.print("					<div id='buttonsDiv'>");
					if (resendPIN = true) {
						out.print("<input type='submit' id='resendBtn' name='resendBtn' value='Resend PIN' onclick='onResend()'>");
					}
					out.print("						<input type='submit' id='confirmBtn' name='confirmBtn' value='Confirm'>"
					+ "							</div>"
					+ "						</div>"
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

}
