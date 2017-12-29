package logs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.LogsModel;

public class Logs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logs() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Database db = new Database(0);
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
					+ "				<li><a href='#00'>General</a></li>"
					+ "				<li><a href='#01'>Bans &amp; Appeals</a></li>"
					+ "				<li><a href='#02'>Donation History</a></li>"
					+ "				<li><a href='#03'>Forum Control</a></li>"
					+ "				<li><a href='#04'>Support Tickets</a></li>"
					+ "				<li><a href='#05'>Reports</a></li>"
					+ "			</ul>"
					+ "		</div>"
					+ "		<div id='wrapper'>"
					+ "			<div id='content-wrapper'>"
					+ "				<div id='content'>"
					+ "					<div id='filterDiv'>"
					+ "						<div id='searchDiv'>"
					+ "							<label id='searchLabel'>Search:</label>"
					+ "							<input type='text' id ='searchInput' name='searchInput'>"
					+ "						</div>"
					+ "						<div id='selectDiv'>"
					+ "							<label id='filterLabel'>Filter by:</label>"
					+ "							<select id='selectFilter' name='selectFilter' onchange='filterText()'>"
					+ "								<option selected disabled hidden='true'>Type</option>"
					+ "								<option value='All'>All</option>"
					+ "								<option value='Login'>Login</option>"
					+ "								<option value='Forum'>Forum</option>"
					+ "								<option value='Event'>Event</option>"
					+ "								<option value='Potcast'>Potcast</option>"
					+ "								<option value='Donation'>Donation</option>"
					+ "							</select>"
					+ "						</div>"
					+ "						<div id='beforeDateDiv'>"
					+ "							<label id='beforeDateLabel'>Before:</label>"
					+ "							<input type='date' id='beforeDateInput' name='beforeDateInput'>"
					+ "						</div>"
					+ "						<div id='duringDateDiv'>"
					+ "							<label id='duringDateLabel'>During:</label>"
					+ "							<input type='date' id='duringDateInput' name='duringDateInput'>"
					+ "						</div>"
					+ "						<div id='afterDateDiv'>"
					+ "							<label id='afterDateLabel'>After:</label>"
					+ "							<input type='date' id='afterDateInput' name='afterDateInput'>"
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
					+ "									<th>Suspicious</th>"
					+ "								</tr>"
					+ "							</thead>"
					+ "							<tbody>");
					for (LogsModel lm : db.getLogs()) {
						out.print("<tr>"
								+ "	<td>" + lm.converTimestamp(lm.getLogDate()) + "</td>"
								+ "	<td>" + lm.getiPAddress() + "</td>"
								+ "	<td>" + lm.getiGN() + "</td>"
								+ " <td>" + lm.getLogType() + "</td>"
								+ "	<td>" + lm.getLogActivity() + "</td>");
						if (lm.isSuspicious()) {
							out.print("<td>Yes</td>");
						}
						else {
							out.print("<td>No</td>");
						}
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

}
