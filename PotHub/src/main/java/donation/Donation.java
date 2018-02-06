package donation;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.DatabaseUserModel;
import database.model.TemporaryStoreModel;
import login.BanChecker;

public class Donation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Donation() {
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
					+ "					<form id='donateForm' autocomplete='off' method='post'>"
					+ "						<div id='onBehalfDiv'>"
					+ "							<div id='askDiv'>"
					+ "								Are you donating on behalf of someone? Check the box if yes, ignore if no."
					+ "							</div>"
					+ "							<div class='thatLine'></div>"
					+ "							<div id='onBehalfCheckDiv'>"
					+ "								<label id='yesLabel' class='custom-control custom-checkbox' for='checkYes'>"
					+ "									<input type='checkbox' id='checkYes' class='custom-control-input' name='checkYes' onclick='showBehalfName()'>"
					+ "									<span class='custom-control-indicator'></span>"
					+ "  								<span class='custom-control-description'>Yes</span>"
					+ "								</label>"
					+ "								<div id='behalfNameDiv'>"
					+ "									<label id='behalfNameLabel' class='inputLabel' for='behalfName'>Donee</label>"
					+ "									<input type='text' id='behalfName' name='behalfName' list='behalfNameList'>"
					+ "									<datalist id='behalfNameList'>");
					for (String s : db.getDatabaseUserIGN()) {
						out.print("<option value='" + s + "'></option>"); 
					}
					out.print("							</datalist>"
					+ "								</div>"
					+ "							</div>"
					+ "						</div>"
					+ "						<div id='donateAmtDiv'>"
					+ "							<div class='flexDiv'>"
					+ "								<div class='centerDiv'>"
					+ "									<label id='donateLabel' class='inputLabel' for='donateAmt'>How much would you like to donate?</label>"
					+ "									<input type='number' id='donateAmt' name='donateAmt' min='0.01' max='2000' step='0.01' placeholder='0.00' required>"
					+ "								</div>"
					+ "							</div>"
					+ "						</div>"
					+ "						<div id='inputInfoDiv'>"
					+ "							<div id='nameInputDiv' class='flexDiv'>"
					+ "								<div class='centerDiv'>"
					+ "									<label id='nameLabel' class='inputLabel' for='nameInput'>Name</label>"
					+ "									<input type='text' id='nameInput' name='nameInput' required>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='cardInputDiv' class='flexDiv'>"
					+ "								<div class='centerDiv'>"
					+ "									<label id='cardLabel' class='inputLabel' for='cardNo'>Credit Card Number</label>"
					+ "									<div id='inputCardDiv'>"
					+ "										<input type='text' id='cardNo' name='cardNo' maxlength='19' oninput='checkCardType()' required>"
					+ "										<div id='cardIconDiv'>"
					+ "											<i id='visaIcon' class='fa fa-cc-visa cardIcon' aria-hidden='true'></i>"
					+ "											<i id='mastercardIcon' class='fa fa-cc-mastercard cardIcon' aria-hidden='true'></i>"
					+ "											<i id='amexIcon' class='fa fa-cc-amex cardIcon' aria-hidden='true'></i>"
					+ "											<i id='dinersIcon' class='fa fa-cc-diners-club cardIcon' aria-hidden='true'></i>"
					+ "											<i id='jcbIcon' class='fa fa-cc-jcb cardIcon' aria-hidden='true'></i>"
					+ "										</div>"
					+ "									</div>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='dateCodeDiv'>"
					+ "								<div id='dateCodeInputDiv' class='row'>"
					+ "									<div id='dateDiv' class='col-md-3'>"
					+ "										<label id='expiryDateLabel' class='inputLabel' >Expiration</label>"
					+ "										<select id='selectMonth' class='custom-select' name='selectMonth' required>"
					+ "											<option value='' selected disabled hidden='true'>MM</option>"
					+ "											<option value='1'>1</option>"
					+ "											<option value='2'>2</option>"
					+ "											<option value='3'>3</option>"
					+ "											<option value='4'>4</option>"
					+ "											<option value='5'>5</option>"
					+ "											<option value='6'>6</option>"
					+ "											<option value='7'>7</option>"
					+ "											<option value='8'>8</option>"
					+ "											<option value='9'>9</option>"
					+ "											<option value='10'>10</option>"
					+ "											<option value='11'>11</option>"
					+ "											<option value='12'>12</option>"
					+ "										</select>"
					+ "										<select id='selectYear' class='custom-select' name='selectYear' required>"
					+ "											<option value='' selected disabled hidden='true'>YYYY</option>"
					+ "											<option value='2017'>2017</option>"
					+ "											<option value='2018'>2018</option>"
					+ "											<option value='2019'>2019</option>"
					+ "											<option value='2020'>2020</option>"
					+ "											<option value='2021'>2021</option>"
					+ "											<option value='2022'>2022</option>"
					+ "											<option value='2023'>2023</option>"
					+ "											<option value='2024'>2024</option>"
					+ "											<option value='2025'>2025</option>"
					+ "											<option value='2026'>2026</option>"
					+ "											<option value='2027'>2027</option>"
					+ "											<option value='2028'>2028</option>"
					+ "											<option value='2029'>2029</option>"
					+ "											<option value='2030'>2030</option>"
					+ "										</select>"
					+ "									</div>"
					+ "									<div id='codeDiv' class='col-md-3'>"
					+ "										<label id='codeLabel' class='inputLabel' for='securityCode'>Security Code</label>"
					+ "										<input type='text' id='securityCode' name='securityCode' maxlength='4' required>"
					+ "									</div>"
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
			TemporaryStoreModel tsm =  new TemporaryStoreModel();
			TemporaryStoreModel tsmCheck = db.getTempStore(username);
			DatabaseUserModel dum = db.getUserProfile(username);
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
			boolean behalfNameError = false;
			boolean donateAmtError = false;
			boolean cardNameError = false;
			boolean cardNoError = false;
			boolean securityCodeError = false;
			boolean fillInputError = false;
			
			if (tsmCheck != null) {
				db.deleteFromTempStore(username);
				if (validateInputString(donateAmt, ccName, ccNumber, ccMonth, ccYear, securityCode)) {
					if (behalfName != null && !behalfName.isEmpty()) {
						if ((checkUserExists(behalfName, db) && (new BigDecimal(donateAmt).compareTo(new BigDecimal("2000.00")) <= 0) && (ccName.matches("^[a-zA-Z ]*$")) && (vc.validateCCNo(ccNumber)) && (vc.validateCode(ccNumber, securityCode)))) {
							tsm.setiGN(username);
							tsm.setTemporaryAmount(new BigDecimal(donateAmt));
							tsm.setTemporaryOnBehalf(behalfName);
							tsm.setTemporaryPIN(hashedPIN);
							tsm.setTemporarySalt(encodedSalt);
							tsm.setTemporaryTime(tsm.getTime5MinsLater());
							if (db.insertTempStore(tsm)) {
								se.sendEmail(dum.getEmail(), pinNo);
							}
							response.sendRedirect("ConfirmDonation");
							return;
						}
						if (!(checkUserExists(behalfName, db))) {
							behalfNameError = true;
						}
						if (!(new BigDecimal(donateAmt).compareTo(new BigDecimal("2000.00")) <= 0)) {
							donateAmtError = true;
						}
						if (!(ccName.matches("^[a-zA-Z ]*$"))) {
							cardNameError = true;
						}
						if (!(vc.validateCCNo(ccNumber))) {
							cardNoError = true;
						}
						if (!(vc.validateCode(ccNumber, securityCode))) {
							securityCodeError = true;
						}
					}
					else {
						if ((new BigDecimal(donateAmt).compareTo(new BigDecimal("2000.00")) <= 0) && (ccName.matches("^[a-zA-Z ]*$")) && (vc.validateCCNo(ccNumber)) && vc.validateCode(ccNumber, securityCode)) {
							tsm.setiGN(username);
							tsm.setTemporaryAmount(new BigDecimal(donateAmt));
							tsm.setTemporaryOnBehalf(behalfName);
							tsm.setTemporaryPIN(hashedPIN);
							tsm.setTemporarySalt(encodedSalt);
							tsm.setTemporaryTime(tsm.getTime5MinsLater());
							if (db.insertTempStore(tsm)) {
								se.sendEmail(dum.getEmail(), pinNo);
							}
							response.sendRedirect("ConfirmDonation");
							return;
						}
						if (!(new BigDecimal(donateAmt).compareTo(new BigDecimal("2000.00")) <= 0)) {
							donateAmtError = true;
						}
						if (!(ccName.matches("^[a-zA-Z ]*$"))) {
							cardNameError = true;
						}
						if (!(vc.validateCCNo(ccNumber))) {
							cardNoError = true;
						}
						if (!(vc.validateCode(ccNumber, securityCode))) {
							securityCodeError = true;
						}
					}
				}
				else {
					fillInputError = false;
				}
			}
			else {
				if (validateInputString(donateAmt, ccName, ccNumber, ccMonth, ccYear, securityCode)) {
					if (behalfName != null && !behalfName.isEmpty()) {
						if ((checkUserExists(behalfName, db) && (new BigDecimal(donateAmt).compareTo(new BigDecimal("2000.00")) <= 0) && (ccName.matches("^[a-zA-Z ]*$")) && (vc.validateCCNo(ccNumber)) && (vc.validateCode(ccNumber, securityCode)))) {
							tsm.setiGN(username);
							tsm.setTemporaryAmount(new BigDecimal(donateAmt));
							tsm.setTemporaryOnBehalf(behalfName);
							tsm.setTemporaryPIN(hashedPIN);
							tsm.setTemporarySalt(encodedSalt);
							tsm.setTemporaryTime(tsm.getTime5MinsLater());
							if (db.insertTempStore(tsm)) {
								se.sendEmail(dum.getEmail(), pinNo);
							}
							response.sendRedirect("ConfirmDonation");
							return;
						}
						if (!(checkUserExists(behalfName, db))) {
							behalfNameError = true;
						}
						if (!(new BigDecimal(donateAmt).compareTo(new BigDecimal("2000.00")) <= 0)) {
							donateAmtError = true;
						}
						if (!(ccName.matches("^[a-zA-Z ]*$"))) {
							cardNameError = true;
						}
						if (!(vc.validateCCNo(ccNumber))) {
							cardNoError = true;
						}
						if (!(vc.validateCode(ccNumber, securityCode))) {
							securityCodeError = true;
						}
					}
					else {
						if ((new BigDecimal(donateAmt).compareTo(new BigDecimal("2000.00")) <= 0) && (ccName.matches("^[a-zA-Z ]*$")) && (vc.validateCCNo(ccNumber)) && vc.validateCode(ccNumber, securityCode)) {
							tsm.setiGN(username);
							tsm.setTemporaryAmount(new BigDecimal(donateAmt));
							tsm.setTemporaryOnBehalf(behalfName);
							tsm.setTemporaryPIN(hashedPIN);
							tsm.setTemporarySalt(encodedSalt);
							tsm.setTemporaryTime(tsm.getTime5MinsLater());
							if (db.insertTempStore(tsm)) {
								se.sendEmail(dum.getEmail(), pinNo);
							}
							response.sendRedirect("ConfirmDonation");
							return;
						}
						if (!(new BigDecimal(donateAmt).compareTo(new BigDecimal("2000.00")) <= 0)) {
							donateAmtError = true;
						}
						if (!(ccName.matches("^[a-zA-Z ]*$"))) {
							cardNameError = true;
						}
						if (!(vc.validateCCNo(ccNumber))) {
							cardNoError = true;
						}
						if (!(vc.validateCode(ccNumber, securityCode))) {
							securityCodeError = true;
						}
					}
				}
				else {
					fillInputError = true;
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
					+ "					<form id='donateForm' autocomplete='off' method='post'>"
					+ "						<div id='onBehalfDiv'>"
					+ "							<div id='askDiv'>"
					+ "								Are you donating on behalf of someone? Check the box if yes, ignore if no."
					+ "							</div>"
					+ "							<div class='thatLine'></div>");
					if (behalfNameError) {
						out.print("<div id='onBehalfCheckDiv'>"
								+ "	<label id='yesLabel' class='custom-control custom-checkbox' for='checkYes'>"
								+ "		<input type='checkbox' id='checkYes' class='custom-control-input' name='checkYes' checked onclick='showBehalfName()'>"
								+ "		<span class='custom-control-indicator'></span>"
								+ "		<span class='custom-control-description'>Yes</span>"
								+ "	</label>"
								+ "	<div id='behalfNameDiv' style='display: block;'>"
								+ "		<label id='behalfNameLabel' class='inputLabel' for='behalfName'>Donee</label>"
								+ "		<input type='text' id='behalfName' name='behalfName' list='behalfNameList'>"
								+ "		<div class='errorMsg'>Donee does not exist.</div>"
								+ "		<datalist id='behalfNameList'>");
						for (String s : db.getDatabaseUserIGN()) {
							out.print("		<option value='" + s + "'></option>");
						}
						out.print("		</datalist>"
								+ "	</div>"
								+ "</div>");
					}
					else {
						out.print("<div id='onBehalfCheckDiv'>"
								+ "	<label id='yesLabel' class='custom-control custom-checkbox' for='checkYes'>"
								+ "		<input type='checkbox' id='checkYes' class='custom-control-input' name='checkYes' onclick='showBehalfName()'>"
								+ "		<span class='custom-control-indicator'></span>"
								+ "		<span class='custom-control-description'>Yes</span>"
								+ "	</label>"
								+ "	<div id='behalfNameDiv'>"
								+ "		<label id='behalfNameLabel' class='inputLabel' for='behalfName'>Donee</label>"
								+ "		<input type='text' id='behalfName' name='behalfName' list='behalfNameList'>"
								+ "		<datalist id='behalfNameList'>");
						for (String s : db.getDatabaseUserIGN()) {
							out.print("		<option value='" + s + "'></option>");
						}
						out.print("		</datalist>"
								+ "	</div>"
								+ "</div>");
					}
					out.print("				</div>"
					+ "						<div id='donateAmtDiv'>"
					+ "							<div class='flexDiv'>"
					+ "								<div class='centerDiv'>"
					+ "									<label id='donateLabel' class='inputLabel' for='donateAmt'>How much would you like to donate?</label>"
					+ "									<input type='number' id='donateAmt' name='donateAmt' min='0.01' max='2000' step='0.01' placeholder='0.00' required>");
					if (donateAmtError) {
						out.print("<div class='errorMsg'>You can only donate a maximum of $2000.</div>");
					}
					out.print("						</div>"
					+ "							</div>"
					+ "						</div>"
					+ "						<div id='inputInfoDiv'>"
					+ "							<div id='nameInputDiv' class='flexDiv'>"
					+ "								<div class='centerDiv'>"
					+ "									<label id='nameLabel' class='inputLabel' for='nameInput'>Name</label>"
					+ "									<input type='text' id='nameInput' name='nameInput' required>");
					if (cardNameError) {
						out.print("<div class='errorMsg'>Only letters.</div>");
					}
					out.print("						</div>"
					+ "							</div>"
					+ "							<div id='cardInputDiv' class='flexDiv'>"
					+ "								<div class='centerDiv'>"
					+ "									<label id='cardLabel' class='inputLabel' for='cardNo'>Credit Card Number</label>"
					+ "									<div id='inputCardDiv'>"
					+ "										<input type='text' id='cardNo' name='cardNo' maxlength='19' oninput='checkCardType()' required>");
					if (cardNoError) {
						out.print("<div class='errorMsg'>Invalid credit card number.</div>");
					}
					out.print("								<div id='cardIconDiv'>"
					+ "											<i id='visaIcon' class='fa fa-cc-visa cardIcon' aria-hidden='true'></i>"
					+ "											<i id='mastercardIcon' class='fa fa-cc-mastercard cardIcon' aria-hidden='true'></i>"
					+ "											<i id='amexIcon' class='fa fa-cc-amex cardIcon' aria-hidden='true'></i>"
					+ "											<i id='dinersIcon' class='fa fa-cc-diners-club cardIcon' aria-hidden='true'></i>"
					+ "											<i id='jcbIcon' class='fa fa-cc-jcb cardIcon' aria-hidden='true'></i>"
					+ "										</div>"
					+ "									</div>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='dateCodeDiv'>"
					+ "								<div id='dateCodeInputDiv' class='row'>"
					+ "									<div id='dateDiv' class='col-md-3'>"
					+ "										<label id='expiryDateLabel' class='inputLabel' >Expiration</label>"
					+ "										<select id='selectMonth' class='custom-select' name='selectMonth' required>"
					+ "											<option value='' selected disabled hidden='true'>MM</option>"
					+ "											<option value='1'>1</option>"
					+ "											<option value='2'>2</option>"
					+ "											<option value='3'>3</option>"
					+ "											<option value='4'>4</option>"
					+ "											<option value='5'>5</option>"
					+ "											<option value='6'>6</option>"
					+ "											<option value='7'>7</option>"
					+ "											<option value='8'>8</option>"
					+ "											<option value='9'>9</option>"
					+ "											<option value='10'>10</option>"
					+ "											<option value='11'>11</option>"
					+ "											<option value='12'>12</option>"
					+ "										</select>"
					+ "										<select id='selectYear' class='custom-select' name='selectYear' required>"
					+ "											<option value='' selected disabled hidden='true'>YYYY</option>"
					+ "											<option value='2017'>2017</option>"
					+ "											<option value='2018'>2018</option>"
					+ "											<option value='2019'>2019</option>"
					+ "											<option value='2020'>2020</option>"
					+ "											<option value='2021'>2021</option>"
					+ "											<option value='2022'>2022</option>"
					+ "											<option value='2023'>2023</option>"
					+ "											<option value='2024'>2024</option>"
					+ "											<option value='2025'>2025</option>"
					+ "											<option value='2026'>2026</option>"
					+ "											<option value='2027'>2027</option>"
					+ "											<option value='2028'>2028</option>"
					+ "											<option value='2029'>2029</option>"
					+ "											<option value='2030'>2030</option>"
					+ "										</select>"
					+ "									</div>"
					+ "									<div id='codeDiv' class='col-md-3'>"
					+ "										<label id='codeLabel' class='inputLabel' for='securityCode'>Security Code</label>"
					+ "										<input type='text' id='securityCode' name='securityCode' maxlength='4' required>");
					if (securityCodeError) {
						out.print("<div class='errorMsg'>Invalid security code.</div>");
					}
					out.print("							</div>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div class='thatLine'></div>");
					if (fillInputError) {
						out.print("<div id='fillInputError' class='errorMsg'>Please fill in all required inputs.</div>");
					}
					out.print("					<div id='buttonDiv'>"
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
		if ((donateAmt != null && !donateAmt.isEmpty()) && (ccName != null && !ccName.isEmpty()) && (ccNumber != null && !ccNumber.isEmpty()) 
				&& (ccMonth != null && !ccMonth.isEmpty()) && (ccYear != null && !ccYear.isEmpty()) && (securityCode != null && !securityCode.isEmpty())) {
			return true;
		}
		return false;
	}
	
	private boolean checkUserExists(String behalfName, Database db) {
		try {
			for (String s : db.getDatabaseUserIGN()) {
				if (behalfName.equals(s)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
