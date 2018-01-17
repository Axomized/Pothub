package donation;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.DatabaseUserModel;
import database.model.TemporaryStoreModel;

public class Donation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Donation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("username");
		
		try {
			Database db = new Database(0);
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Donation</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/Donation.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/Donation.css'>"
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
					+ "					<form id='donateForm' method='post' autocomplete='off'>"
					+ "						<div id='onBehalfDiv'>"
					+ "							<div id='askDiv'>"
					+ "								Are you donating on behalf of someone? Check the box if yes, ignore if no."
					+ "							</div>"
					+ "							<div class='thatLine'></div>"
					+ "							<div id='onBehalfCheckDiv'>"
					+ "								<label id='yesLabel' class='custom-control custom-checkbox' for='checkYes'>"
					+ "									<input type='checkbox' id='checkYes' class='custom-control-input' name='checkYes' value='Yes' onclick='showBehalfName()'>"
					+ "									<span class='custom-control-indicator'></span>"
					+ "  									<span class='custom-control-description'>Yes</span>"
					+ "								</label>"
					+ "								<input type='text' id='behalfName' name='behalfName' list='behalfNameList'>"
					+ "								<datalist id='behalfNameList'>");
					for (String userIGN : db.getDatabaseUserIGN()) {
						out.print("<option value='" + userIGN + "'></option>");
					}
					out.println("					</datalist>"			
					+ "							</div>"
					+ "						</div>"
					+ "						<div id='donateAmtDiv'>"
					+ "							<label id='donateLabel' for='donateAmt'>How much would you like to donate?</label>"
					+ "							<input type='number' id='donateAmt' name='donateAmt' min='0' max='5000' step='0.01' placeholder='0.00' required>"
					+ "						</div>"
					+ "						<div id='inputInfoDiv'>"
					+ "							<div id='nameInputDiv'>"
					+ "								<label id='nameLabel' for='nameInput'>Name</label>"
					+ "								<input type='text' id='nameInput' name='nameInput' required>"
					+ "							</div>"
					+ "							<div id='cardInputDiv'>"
					+ "								<label id='cardLabel' for='cardNo'>Credit Card Number</label>"
					+ "								<div id='inputCardDiv'>"
					+ "									<input type='text' id='cardNo' name='cardNo' maxlength='19' oninput='checkCardType()' required>"
					+ "									<div id='cardIconDiv'>"
					+ "										<i id='visaIcon' class='fa fa-cc-visa cardIcon' aria-hidden='true'></i>"
					+ "										<i id='mastercardIcon' class='fa fa-cc-mastercard cardIcon' aria-hidden='true'></i>"
					+ "										<i id='amexIcon' class='fa fa-cc-amex cardIcon' aria-hidden='true'></i>"
					+ "										<i id='dinersIcon' class='fa fa-cc-diners-club cardIcon' aria-hidden='true'></i>"
					+ "										<i id='jcbIcon' class='fa fa-cc-jcb cardIcon' aria-hidden='true'></i>"
					+ "									</div>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='dateCodeDiv'>"
					+ "								<div id='dateDiv'>"
					+ "									<label id='expiryDateLabel'>Expiration</label>"
					+ "									<select id='selectMonth' class='custom-select' name='selectMonth' required>"
					+ "										<option value='' selected disabled hidden='true'>MM</option>"
					+ "										<option value='1'>1</option>"
					+ "										<option value='2'>2</option>"
					+ "										<option value='3'>3</option>"
					+ "										<option value='4'>4</option>"
					+ "										<option value='5'>5</option>"
					+ "										<option value='6'>6</option>"
					+ "										<option value='7'>7</option>"
					+ "										<option value='8'>8</option>"
					+ "										<option value='9'>9</option>"
					+ "										<option value='10'>10</option>"
					+ "										<option value='11'>11</option>"
					+ "										<option value='12'>12</option>"
					+ "									</select>"
					+ "									<select id='selectYear' class='custom-select' name='selectYear' required>"
					+ "										<option value='' selected disabled hidden='true'>YYYY</option>"
					+ "										<option value='2017'>2017</option>"
					+ "										<option value='2018'>2018</option>"
					+ "										<option value='2019'>2019</option>"
					+ "										<option value='2020'>2020</option>"
					+ "										<option value='2021'>2021</option>"
					+ "										<option value='2022'>2022</option>"
					+ "										<option value='2023'>2023</option>"
					+ "										<option value='2024'>2024</option>"
					+ "										<option value='2025'>2025</option>"
					+ "										<option value='2026'>2026</option>"
					+ "										<option value='2027'>2027</option>"
					+ "										<option value='2028'>2028</option>"
					+ "										<option value='2029'>2029</option>"
					+ "										<option value='2030'>2030</option>"
					+ "									</select>"
					+ "								</div>"
					+ "								<div id='codeDiv'>"
					+ "									<label id='codeLabel' for='securityCode'>Security Code</label>"
					+ "									<input type='text' id='securityCode' name='securityCode' maxlength='4' required/>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div class='thatLine'></div>"
					+ "							<div id='buttonDiv'>"
					+ "								<input type='submit' id='donateBtn' name='donateBtn' value='Donate'>"
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String username = (String)session.getAttribute("username");
			Database db = new Database(2);
			TemporaryStoreModel tsm =  new TemporaryStoreModel();
			TemporaryStoreModel tsmCheck = db.getTempStore(username);
			SendEmail se = new SendEmail();
			HashPIN hp = new HashPIN();
			ValidateCard vc = new ValidateCard();
			
			String behalfName = request.getParameter("behalfName");
			String donateAmt = request.getParameter("donateAmt");
			String ccName = request.getParameter("nameInput");
			String ccNumber = request.getParameter("cardNo");
			String ccMonth = request.getParameter("selectMonth");
			String ccYear = request.getParameter("selectYear");
			String securityCode = request.getParameter("securityCode");
			byte[] salt = hp.createSalt();
			String encodedSalt = hp.getEncodedSalt(salt);
			String pinNo = tsm.generatePIN();
			String hashedPIN = hp.getHashedPIN(pinNo, salt);
			Timestamp currentTime = Timestamp.from(Instant.now());
			String errorMessage = "";
			
			if (tsmCheck != null) {
				if (currentTime.before(tsmCheck.getTemporaryTime())) {
					response.sendRedirect("ConfirmDonation");
				}
				else {
					db.deleteFromTempStore(username);
					if (validateInputString(donateAmt, ccName, ccNumber, ccMonth, ccYear, securityCode)) {
						if (behalfName != null && !behalfName.isEmpty()) {
							if (vc.validateCCNo(ccNumber)) {
								if (vc.validateCode(ccNumber, securityCode)) {
									tsm.setiGN(username);
									tsm.setTemporaryAmount(new BigDecimal(donateAmt));
									tsm.setTemporaryOnBehalf(behalfName);
									tsm.setTemporaryPIN(hashedPIN);
									tsm.setTemporarySalt(encodedSalt);
									tsm.setTemporaryTime(tsm.getTime5MinsLater());
									if (db.insertTempStore(tsm)) {
										se.sendEmail("dr.que9@gmail.com", pinNo);
									}
									response.sendRedirect("ConfirmDonation");
								}
								else {
									errorMessage = "Invalid security code";
								}
							}
							else {
								errorMessage = "Invalid credit card number";
							}
						}
						else {
							if (vc.validateCCNo(ccNumber)) {
								if (vc.validateCode(ccNumber, securityCode)) {
									tsm.setiGN(username);
									tsm.setTemporaryAmount(new BigDecimal(donateAmt));
									tsm.setTemporaryOnBehalf(behalfName);
									tsm.setTemporaryPIN(hashedPIN);
									tsm.setTemporarySalt(encodedSalt);
									tsm.setTemporaryTime(tsm.getTime5MinsLater());
									if (db.insertTempStore(tsm)) {
										se.sendEmail("dr.que9@gmail.com", pinNo);
									}
									response.sendRedirect("ConfirmDonation");
								}
								else {
									errorMessage = "Invalid security code";
								}
							}
							else {
								errorMessage = "Invalid credit card number";
							}
						}
					}
					else {
						errorMessage = "Please fill in all required inputs";
					}
				}
			}
			else {
				if (validateInputString(donateAmt, ccName, ccNumber, ccMonth, ccYear, securityCode)) {
					if (behalfName != null && !behalfName.isEmpty()) {
						if (vc.validateCCNo(ccNumber)) {
							if (vc.validateCode(ccNumber, securityCode)) {
								tsm.setiGN(username);
								tsm.setTemporaryAmount(new BigDecimal(donateAmt));
								tsm.setTemporaryOnBehalf(behalfName);
								tsm.setTemporaryPIN(hashedPIN);
								tsm.setTemporarySalt(encodedSalt);
								tsm.setTemporaryTime(tsm.getTime5MinsLater());
								if (db.insertTempStore(tsm)) {
									se.sendEmail("dr.que9@gmail.com", pinNo);
								}
								response.sendRedirect("ConfirmDonation");
							}
							else {
								errorMessage = "Invalid security code";
							}
						}
						else {
							errorMessage = "Invalid credit card number";
						}
					}
					else {
						if (vc.validateCCNo(ccNumber)) {
							if (vc.validateCode(ccNumber, securityCode)) {
								tsm.setiGN(username);
								tsm.setTemporaryAmount(new BigDecimal(donateAmt));
								tsm.setTemporaryOnBehalf(behalfName);
								tsm.setTemporaryPIN(hashedPIN);
								tsm.setTemporarySalt(encodedSalt);
								tsm.setTemporaryTime(tsm.getTime5MinsLater());
								if (db.insertTempStore(tsm)) {
									se.sendEmail("dr.que9@gmail.com", pinNo);
								}
								response.sendRedirect("ConfirmDonation");
							}
							else {
								errorMessage = "Invalid security code";
							}
						}
						else {
							errorMessage = "Invalid credit card number";
						}
					}
				}
				else {
					errorMessage = "Please fill in all required inputs";
				}
			}
			
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Donation</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/Donation.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/Donation.css'>"
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
					+ "					<form id='donateForm' autocomplete='off' method='post'>"
					+ "						<div id='onBehalfDiv'>"
					+ "							<div id='askDiv'>"
					+ "								Are you donating on behalf of someone? Check the box if yes, ignore if no."
					+ "							</div>"
					+ "							<div class='thatLine'></div>"
					+ "							<div id='onBehalfCheckDiv'>"
					+ "								<label id='yesLabel' class='custom-control custom-checkbox' for='checkYes'>"
					+ "									<input type='checkbox' id='checkYes' class='custom-control-input' name='checkYes' value='Yes' onclick='showBehalfName()'>"
					+ "									<span class='custom-control-indicator'></span>"
					+ "  									<span class='custom-control-description'>Yes</span>"
					+ "								</label>"
					+ "								<input type='text' id='behalfName' name='behalfName' list='behalfNameList'>"
					+ "								<datalist id='behalfNameList'>");
					for (String userIGN : db.getDatabaseUserIGN()) {
						out.print("<option value='" + userIGN + "'></option>");
					}
					out.println("					</datalist>"			
					+ "							</div>"
					+ "						</div>"
					+ "						<div id='donateAmtDiv'>"
					+ "							<label id='donateLabel' for='donateAmt'>How much would you like to donate?</label>"
					+ "							<input type='number' id='donateAmt' name='donateAmt' min='0' max='5000' step='0.01' placeholder='0.00' required>"
					+ "						</div>"
					+ "						<div id='inputInfoDiv'>"
					+ "							<div id='nameInputDiv'>"
					+ "								<label id='nameLabel' for='nameInput'>Name</label>"
					+ "								<input type='text' id='nameInput' name='nameInput' required>"
					+ "							</div>"
					+ "							<div id='cardInputDiv'>"
					+ "								<label id='cardLabel' for='cardNo'>Credit Card Number</label>"
					+ "								<div id='inputCardDiv'>"
					+ "									<input type='text' id='cardNo' name='cardNo' maxlength='19' oninput='checkCardType()' required>"
					+ "									<div id='cardIconDiv'>"
					+ "										<i id='visaIcon' class='fa fa-cc-visa cardIcon' aria-hidden='true'></i>"
					+ "										<i id='mastercardIcon' class='fa fa-cc-mastercard cardIcon' aria-hidden='true'></i>"
					+ "										<i id='amexIcon' class='fa fa-cc-amex cardIcon' aria-hidden='true'></i>"
					+ "										<i id='dinersIcon' class='fa fa-cc-diners-club cardIcon' aria-hidden='true'></i>"
					+ "										<i id='jcbIcon' class='fa fa-cc-jcb cardIcon' aria-hidden='true'></i>"
					+ "									</div>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='dateCodeDiv'>"
					+ "								<div id='dateDiv'>"
					+ "									<label id='expiryDateLabel'>Expiration</label>"
					+ "									<select id='selectMonth' class='custom-select' name='selectMonth' required>"
					+ "										<option value='' selected disabled hidden='true'>MM</option>"
					+ "										<option value='1'>1</option>"
					+ "										<option value='2'>2</option>"
					+ "										<option value='3'>3</option>"
					+ "										<option value='4'>4</option>"
					+ "										<option value='5'>5</option>"
					+ "										<option value='6'>6</option>"
					+ "										<option value='7'>7</option>"
					+ "										<option value='8'>8</option>"
					+ "										<option value='9'>9</option>"
					+ "										<option value='10'>10</option>"
					+ "										<option value='11'>11</option>"
					+ "										<option value='12'>12</option>"
					+ "									</select>"
					+ "									<select id='selectYear' class='custom-select' name='selectYear' required>"
					+ "										<option value='' selected disabled hidden='true'>YYYY</option>"
					+ "										<option value='2017'>2017</option>"
					+ "										<option value='2018'>2018</option>"
					+ "										<option value='2019'>2019</option>"
					+ "										<option value='2020'>2020</option>"
					+ "										<option value='2021'>2021</option>"
					+ "										<option value='2022'>2022</option>"
					+ "										<option value='2023'>2023</option>"
					+ "										<option value='2024'>2024</option>"
					+ "										<option value='2025'>2025</option>"
					+ "										<option value='2026'>2026</option>"
					+ "										<option value='2027'>2027</option>"
					+ "										<option value='2028'>2028</option>"
					+ "										<option value='2029'>2029</option>"
					+ "										<option value='2030'>2030</option>"
					+ "									</select>"
					+ "								</div>"
					+ "								<div id='codeDiv'>"
					+ "									<label id='codeLabel' for='securityCode'>Security Code</label>"
					+ "									<input type='text' id='securityCode' name='securityCode' maxlength='4' required/>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='errorMsg'>" + errorMessage + "</div>"
					+ "							<div class='thatLine'></div>"
					+ "							<div id='buttonDiv'>"
					+ "								<input type='submit' id='donateBtn' name='donateBtn' value='Donate'>"
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
	
	private boolean validateInputString(String donateAmt, String ccName, String ccNumber, String ccMonth, String ccYear, String securityCode) {
		boolean isNotNull = false;
		
		if ((donateAmt != null && !donateAmt.isEmpty()) && (ccName != null && !ccName.isEmpty()) && (ccNumber != null && !ccNumber.isEmpty()) 
				&& (ccMonth != null && !ccMonth.isEmpty()) && (ccYear != null && !ccYear.isEmpty()) && (securityCode != null && !securityCode.isEmpty())) {
			isNotNull = true;
		}
		
		return isNotNull;
	}

}
