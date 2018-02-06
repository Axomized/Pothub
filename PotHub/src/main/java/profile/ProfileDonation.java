package profile;

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
import database.model.DonationModel;
import login.BanChecker;

public class ProfileDonation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProfileDonation() {
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
			ProfileDonationSearch search = new ProfileDonationSearch();
			String onBehalf = request.getParameter("onBehalf");
			String amount = request.getParameter("amount");
			String dateInput = request.getParameter("selectDate");
			String afterDate = request.getParameter("afterDate");
			String beforeDate = request.getParameter("beforeDate");
			
			if (onBehalf != null && !onBehalf.isEmpty()) {
				search.setOnBehalf(onBehalf);
			}
			if (amount != null && !amount.isEmpty()) {
				search.setDonationAmount(new BigDecimal(amount.replaceAll("\\D+", "")));
			}
			if (dateInput != null && !dateInput.isEmpty()) {
				search.setDateInput(dateInput);
				
				if (dateInput.equals("Custom")) {
					if ((afterDate != null && !afterDate.isEmpty()) && (beforeDate != null && !beforeDate.isEmpty())) {
						search.setAfterDate(afterDate);
						search.setBeforeDate(beforeDate);
					}
				}
			}
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Donation History</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/ProfileDonation.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/ProfileDonation.css'/>"
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
					+ "						<a href='ProfileDonation' id='defaultSelected'>Donation History</a>"
					+ "						<a href='EditProfile'>Settings</a>"
					+ "					</div>"
					+ "				</div>"
					+ "				<div id='content' class='row'>"
					+ "					<div id='profilePicDiv' class='col-sm-3'>"
					+ "						<div id='profileImgDiv'>");
					if (dum.getProfilePic() != 0) {
						out.print("<img src='Image/" + db.getImageByImageID(dum.getProfilePic()) + "' class='roundProfilePic' height='150' width='150'/>");
					}
					else {
						out.print("<img src='images/profile.png' class='roundProfilePic' height='150' width='150'/>");
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
					out.print("			</div>"
					+ "					<div id='profileContentDiv' class='col-sm-9'>"
					+ "						<div id='aboutContentDiv'>"
					+ "							<div id='dropdownDivWrap'>"
					+ "								<div id='dropdownDiv'>"
					+ "									<div id='filterBtnDiv'>"
					+ "										<button id='filterBtn' onclick='showDropdown()'>Filter</button>"
					+ "									</div>"
					+ "									<div id='dropdownContentDiv'>"
					+ "										<form id='searchForm' autocomplete='off' method='get'>"
					+ "											<div id='filterDiv'>"
					+ "												<div id='onBehalfDiv' class='searchDiv'>"
					+ "													<label id='onBehalfLabel' for='onBehalf'>Search by donee:</label>"
					+ "													<input type='text' id ='onBehalf' class='searchInput' name='onBehalf' list='onBehalfList'>"
					+ "													<datalist id='onBehalfList'>");
					for (String userIGN : db.getDatabaseUserIGN()) {
						out.print("<option value='" + userIGN + "'></option>");
					}
					out.print("											</datalist>"
					+ "												</div>"
					+ "												<div id=amountDiv' class='searchDiv'>"
					+ "													<label id='amountLabel' for='amount'>Search by amount:</label>"
					+ "													<input type='text' id='amount' class='searchInput' name='amount'>"
					+ "												</div>"
					+ "												<div id='dateDiv' class='searchDiv'>"
					+ "													<label id='dateLabel' for='selectDate'>Search by date:</label>"
					+ "													<select id='selectDate' class='searchSelect' name='selectDate' onchange='customDateSelect()'>"
					+ "														<option selected value='All'>All</option>"
					+ "														<option value='Yesterday'>Yesterday</option>"
					+ "														<option value='Last 7 days'>Last 7 days</option>"
					+ "														<option value='Last 30 days'>Last 30 days</option>"
					+ "														<option value='Last 90 days'>Last 90 days</option>"
					+ "														<option value='Custom'>Custom</option>"
					+ "													</select>"
					+ "												</div>"
					+ "												<div id='afterDateDiv' class='searchDiv'>"
					+ "													<label id='afterDateLabel' for='afterDate'>After:</label>"
					+ "													<input type='date' id='afterDate' class='searchInput' name='afterDate'>"
					+ "												</div>"
					+ "												<div id='beforeDateDiv' class='searchDiv'>"
					+ "													<label id='beforeDateLabel' for='beforeDate'>Before:</label>"
					+ "													<input type='date' id='beforeDate' class='searchInput' name='beforeDate'>"
					+ "												</div>"
					+ "												<div id='buttonDiv' class='searchDiv'>"
					+ "													<input type='submit' id='searchBtn'>"
					+ "												</div>"
					+ "											</div>"
					+ "										</form>"
					+ "									</div>"
					+ "								</div>"
					+ "							</div>"
					+ "							<div id='tableWrap'>"
					+ "								<table id='donationTable' class='table table-striped'>"
					+ "									<thead>"
					+ "										<tr>"
					+ "											<th>Date</th>"
					+ "											<th>Amount</th>"
					+ "											<th>Donee</th>"
					+ "										</tr>"
					+ "									</thead>"
					+ "									<tbody>");
					for (DonationModel dm : db.getUserDonation(search, username)) {
						out.print("<tr>"
								+ "	<td>" + dm.converTimestamp(dm.getDonationDate()) + "</td>"
								+ "	<td>" + "$" + dm.getDonationAmount() + "</td>");
						if (dm.getOnBehalf() == null || dm.getOnBehalf().isEmpty()) {
							out.print("<td>NIL</td>");
						}
						else {
							out.print("<td>" + dm.getOnBehalf() + "</td>");
						}
						out.print("</tr>");
					}
					out.print("							</tbody>"
					+ "								</table>"
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
