package logs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.encoder.Encode;

import database.Database;
import database.model.LogsModel;

public class Logs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logs() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			Database db = new Database(0);
			LogsSearch logsSearch = new LogsSearch();
			if (session == null || session.getAttribute("username") == null) {
	    		response.sendRedirect("AdminLogin");
	    		return;
			}
			else if (db.getPermissionForIGN((String)session.getAttribute("username")) == 2 && db.authAdminSession(session.getId())) {
				String iGN = request.getParameter("username");
				String logType = request.getParameter("selectType");
				String dateInput = request.getParameter("selectDate");
				String afterDate = request.getParameter("afterDate");
				String beforeDate = request.getParameter("beforeDate");
				
				if (iGN != null && !iGN.isEmpty()) {
					logsSearch.setiGN(iGN);
				}
				if (logType != null && !logType.isEmpty()) {
					logsSearch.setLogType(logType);
				}
				if (dateInput != null && !dateInput.isEmpty()) {
					logsSearch.setDateInput(dateInput);
					
					if (dateInput.equals("Custom")) {
						if ((afterDate != null && !afterDate.isEmpty()) && (beforeDate != null && !beforeDate.isEmpty())) {
							logsSearch.setAfterDate(afterDate);
							logsSearch.setBeforeDate(beforeDate);
						}
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
					+ "		<title>Logs</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/Logs.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/Logs.css'>"
					+ "	</head>"
					+ "	<body>"
					+ "		<!--  Navigation Bar -->"
					+ "		<div id='header'>"
					+ "			<div id='companyTitle'>"
					+ "				<h1>Admin Panel</h1>"
					+ "			</div>"
					+ "			"
					+ "		</div>"
					+ "		<div id='navigation'>"
					+ "			<ul>"
					+ "				<li><a href='AdminGeneral'>General</a></li>"
					+ "				<li><a href='AdminBans'>Bans &amp; Appeals</a></li>"
					+ "				<li><a href='AdminDonations'>Donations</a></li>"
					+ "				<li><a href='AdminRanks'>Forum Control</a></li>"
					+ "				<li><a href='AdminReports'>Reports</a></li>"
					+ "				<li><a href='Logs'>Logs</a></li>"
					+ "				<li id='logoutLink'><a href='AdminLogout'>Logout</a></li>"
					+ "			</ul>"
					+ "		</div>"
					+ "		<div id='wrapper'>"
					+ "			<div id='content-wrapper'>"
					+ "				<div id='content'>"
					+ "					<div id='dropdownDivWrap'>"
					+ "						<div id='dropdownDiv'>"
					+ "							<div id='filterBtnDiv'>"
					+ "								<button id='filterBtn' onclick='showDropdown()'>Filter</button>"
					+ "							</div>"
					+ "							<div id='dropdownContentDiv'>"
					+ "								<form id='searchForm' autocomplete='off' method='get'>"
					+ "									<div id='filterDiv'>"
					+ "										<div id='nameDiv' class='searchDiv'>"
					+ "											<label id='nameLabel' for='username'>Search by name:</label>"
					+ "											<input type='text' id ='username' class='searchInput' name='username' list='nameList'>"
					+ "											<datalist id='nameList'>");
					for (String userIGN : db.getDatabaseUserIGN()) {
						out.print("<option value='" + userIGN + "'></option>");
					}
					out.print("									</datalist>"
					+ "										</div>"
					+ "										<div id='typeDiv' class='searchDiv'>"
					+ "											<label id='typeLabel' for='selectType'>Search by type:</label>"
					+ "											<select id='selectType' class='searchSelect' name='selectType'>"
					+ "												<option selected value='All'>All</option>"
					+ "												<option value='Login'>Login</option>"
					+ "												<option value='Profile'>Profile</option>"
					+ "												<option value='Forum'>Forum</option>"
					+ "												<option value='Event'>Event</option>"
					+ "												<option value='Potcast'>Potcast</option>"
					+ "												<option value='Donation'>Donation</option>"
					+ "												<option value='Others'>Others</option>"
					+ "											</select>"
					+ "										</div>"
					+ "										<div id='dateDiv' class='searchDiv'>"
					+ "											<label id='dateLabel' for='selectDate'>Search by date:</label>"
					+ "											<select id='selectDate' class='searchSelect' name='selectDate' onchange='customDateSelect()'>"
					+ "												<option selected value='All'>All</option>"
					+ "												<option value='Yesterday'>Yesterday</option>"
					+ "												<option value='Last 7 days'>Last 7 days</option>"
					+ "												<option value='Last 30 days'>Last 30 days</option>"
					+ "												<option value='Last 90 days'>Last 90 days</option>"
					+ "												<option value='Custom'>Custom</option>"
					+ "											</select>"
					+ "										</div>"
					+ "										<div id='afterDateDiv' class='searchDiv'>"
					+ "											<label id='afterDateLabel' for='afterDate'>After:</label>"
					+ "											<input type='date' id='afterDate' class='searchInput' name='afterDate'>"
					+ "										</div>"
					+ "										<div id='beforeDateDiv' class='searchDiv'>"
					+ "											<label id='beforeDateLabel' for='beforeDate'>Before:</label>"
					+ "											<input type='date' id='beforeDate' class='searchInput' name='beforeDate'>"
					+ "										</div>"
					+ "										<div id='buttonDiv' class='searchDiv'>"
					+ "											<input type='submit' id='searchBtn'>"
					+ "										</div>"
					+ "									</div>"
					+ "								</form>"
					+ "							</div>"
					+ "						</div>"
					+ "					</div>"
					+ "					<div id='tableWrap'>"
					+ "						<table id='logsTable' class='table table-striped'>"
					+ "							<thead>"
					+ "								<tr>"
					+ "									<th>Date</th>"
					+ "									<th>IP Address</th>"
					+ "									<th>Username</th>"
					+ "									<th>Type</th>"
					+ "									<th>Activity</th>"
					+ "								</tr>"
					+ "							</thead>"
					+ "							<tbody>");
					for (LogsModel lm : db.getLogs(logsSearch)) {
						out.print("<tr>"
								+ "	<td>" + lm.converTimestamp(lm.getLogDate()) + "</td>"
								+ "	<td>" + lm.getiPAddress() + "</td>");
						if (lm.getiGN() == null || lm.getiGN().isEmpty()) {
							out.print("<td>NIL</td>");
						}
						else {
							out.print("<td>" + lm.getiGN() + "</td>");
						}
						out.print(" <td>" + lm.getLogType() + "</td>"
								+ "	<td>" + decodeString(lm.getLogActivity()) + "</td>"
								+ "</tr>");
					}
					out.print("					</tbody>"
					+ "						</table>"
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
	
	private String decodeString(String input) throws UnsupportedEncodingException {
		return Encode.forHtml(URLDecoder.decode(input, "UTF-8"));
	}

}
