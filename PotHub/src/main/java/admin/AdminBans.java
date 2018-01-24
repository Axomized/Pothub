package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adminSearch.BansSearchObject;
import database.Database;
import database.model.AppealModel;
import database.model.BansModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/AdminBans")
public class AdminBans extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminBans() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("user")==null){
    		response.sendRedirect("AdminLogin");
    		return;
		}
		BansSearchObject bso = new BansSearchObject();

		if (request.getParameter("username") != null) {
			bso.setiGN(request.getParameter("username"));
		}
		if (request.getParameter("admin") != null) {
			bso.setAdmin(request.getParameter("admin"));
		}
		if (request.getParameter("banStart1") != null && request.getParameter("banStart1").length() > 0) {
			bso.setStartDateOpen(Date.valueOf(request.getParameter("banStart1")));
		}
		if (request.getParameter("banStart2") != null && request.getParameter("banStart2").length() > 0) {
			bso.setStartDateClose(Date.valueOf(request.getParameter("banStart2")));
		}
		if (request.getParameter("banEnd1") != null && request.getParameter("banEnd1").length() > 0) {
			bso.setEndDateOpen(Date.valueOf(request.getParameter("banEnd1")));
		}
		if (request.getParameter("banEnd2") != null && request.getParameter("banEnd2").length() > 0) {
			bso.setEndDateClose(Date.valueOf(request.getParameter("banEnd2")));
		}
		if (request.getParameter("reason") != null && request.getParameter("reason").length() > 0) {
			bso.setReason(request.getParameter("reason"));
		}
		PrintWriter pw = response.getWriter();
		pw.append(
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
						+ "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>" + "<head>"
						+ "<!-- Favicon -->" + "<link rel='icon' href='images/crab.gif' type='image/gif'>"
						+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>" + "<title>PotHub Bans</title>"
						+ "<meta http-equiv='content-language' content='en-us' />"
						+ "<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
						+ "<link rel='stylesheet' type='text/css' media='screen' href='css/banscreen.css' />"
						+ "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
						+ "<script src='//cdnjs.cloudflare.com/ajax/libs/tether/1.3.1/js/tether.min.js'></script>"
						+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
						+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
						+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
						+ "<script src='script/jquery.tablesorter.min.js'></script>" + "</head>" + "<body id='babout'>"
						+ "<div id='header'>" + "<h1>Admin Panel</h1>" + "</div>" + "<div id='navigation'>" + "<ul>"
						+ "<li>" + "<a href='AdminGeneral'>General</a>" + "</li>" + "<li>"
						+ "<a href='AdminBans'>Bans & Appeals</a>" + "</li>" + "<li>"
						+ "<a href='AdminDonations'>Donations</a>" + "</li>" + "<li>"
						+ "<a href='AdminRanks'>Forum Control</a>" + "</li>" + "<li>"
						+ "<a href='AdminReports'>Reports</a>" + "</li>" + "</ul>"
						+ "<p id='logout'><a href='Logout'>Logout</a></p>" + "</div>" + "<div id='wrapper'>"
						+ "<div id='content-wrapper'>" + "<div id='tableWrapper'>"
						+ "<table class='table table-striped tablesorter' id='myTable'>" + "<thead>" + "<tr>"
						+ "<th>Username</th>" + "<th>Ban Reason</th>" + "<th>Ban Date</th>" + "<th>Ban End</th>"
						+ "<th>Banned by</th>" + "<th>&nbsp;</th>" + "</tr>" + "</thead>" + "<tbody>");

		Database db;
		ArrayList<BansModel> bans = new ArrayList<BansModel>();
		try {
			db = new Database(0);
			bans = db.getBansModel(bso);
			db.getAppeal();
			for (BansModel ban : bans) {
				pw.append("<tr>");
				pw.append("<td>" + ban.getiGN() + "</td>");
				pw.append("<td>" + ban.getReason() + "</td>");
				pw.append("<td>" + ban.getStartDate() + "</td>");
				pw.append("<td>" + ban.getEndDate() + "</td>");
				pw.append("<td>" + ban.getAdmin() + "</td>");

				pw.append("<td><a href='HistoryAdminBans?user=" + ban.getiGN() + "'><button>History</button></a>");
				AppealModel apm = db.getAppealsByBanID(ban.getBanID());

				if (apm != null) {
					if (apm.getApproval() == 0 && !ban.isPardoned()
							&& ban.getEndDate().getTime() > System.currentTimeMillis()) {
						pw.append("<a href='AppealView?user=" + ban.getiGN() + "&appealID=" + apm.getAppealID() + "'>"
								+ "<button>Read</button>" + "</a>");
					}
				}

				pw.append("</td>");
				pw.append("</tr>");
			}
			if (bans.size() < 10) {
				for (int i = 0; i < (10 - bans.size()); i++) {
					pw.append("<tr>" + "<td>&nbsp;</td>" + "<td>&nbsp;</td>" + "<td>&nbsp;</td>" + "<td>&nbsp;</td>"
							+ "<td>&nbsp;</td>" + "<td>&nbsp;</td></tr>");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (bans.size() == 0) {
			for (int i = 0; i < 10; i++) {
				pw.append(
						"<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			}
		}

		pw.append("</tbody>" + "</table>" + "</div>" + "<form>" + "<div id='fourbox'>" + "<div id='search'>"
				+ "<p>Search Username: <div id='textboxes'><input type='text' name='username'>" + "</input></div></p>"
				+ "</div>" + "<div id='search'>"
				+ "<p>Search Ban By: <div id='textboxes'><input type='text' name='admin'>" + "</input></div></p>"
				+ "</div>" + "<div id='search'>"
				+ "<p>Ban Start Between<div id='textboxes'><input type='date' name='banStart1'></input></div></p>"
				+ "<p>And <div id='textboxes'><input type='date' name='banStart2'></input></div></p>" + "</div>"
				+ "</div>" + "<div id='fourbox'>" + "<div id='search'>"
				+ "<p>Search Reasons: <div id='textboxes'><input type='text' name='reason'>" + "</input></div></p>"
				+ "</div>" + "<div id='search'>"
				+ "<p>Ban End Between<div id='textboxes'><input type='date' name='banEnd1'></input></div></p>"
				+ "<p>And <div id='textboxes'><input type='date' name='banEnd2'></input></div></p>" + "</div>"
				+ "</div>" + "<div id='fourbox'>" + "<div id='search'>"
				+ "<button id='searchButton' type='submit'>Search</button>" + "</div>" + "</div>" + "</div>" + "</form>"
				+ "<div id='footer'>" + "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
				+ "<p>We like food</p>" + "<p>"
				+ "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>" + "</p>"
				+ "</div>" + "</body>" + "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
