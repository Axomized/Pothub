package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.AppealModel;
import database.model.BansModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/AppealView")
public class AppealView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppealView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String subjectUser = request.getParameter("user");
		String appealID = request.getParameter("appealID");

		if (subjectUser == null || appealID == null) {
			response.sendRedirect("AdminBans");
			return;
		}

		PrintWriter pw = response.getWriter();
		pw.append(
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
						+ "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>" + "<head>"
						+ "<!-- Favicon -->" + "<link rel='icon' href='images/crab.gif' type='image/gif'>"
						+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>" + "<title>" + subjectUser
						+ "'s Appeal</title>" + "<meta http-equiv='content-language' content='en-us' />"
						+ "<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
						+ "<link rel='stylesheet' type='text/css' media='screen' href='css/banscreen.css' />"
						+ "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
						+ "<script src='//cdnjs.cloudflare.com/ajax/libs/tether/1.3.1/js/tether.min.js'></script>"
						+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
						+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
						+ "</head>" + "<body id='babout'>" + "<div id='header'>" + "<h1>Admin Panel</h1>" + "</div>"
						+ "<div id='navigation'>" + "<ul>" + "<li>" + "<a href='AdminGeneral'>General</a>" + "</li>"
						+ "<li>" + "<a href='AdminBans'>Bans & Appeals</a>" + "</li>" + "<li>"
						+ "<a href='AdminDonations'>Donations</a>" + "</li>" + "<li>"
						+ "<a href='AdminRanks'>Forum Control</a>" + "</li>" + "<li>"
						+ "<a href='AdminReports'>Reports</a>" + "</li>" + "</ul>"
						+ "<p id='logout'><a href='AdminLogin'>Logout</a></p>" + "</div>" + "<div id='wrapper'>"
						+ "<div id='content-wrapper'>" + "<h1>Showing appeal #" + appealID + " from " + subjectUser
						+ "</h1>");
		Database db;
		try {
			db = new Database(0);
			AppealModel appeal = db.getAppealsByAppealID(Integer.parseInt(appealID));
			BansModel ban = db.getBansByID(appeal.getBanID());

			pw.append("<p id='appealBlock'>" + appeal.getMessage() + "</p>" + "<p id='appealBlock'>Sent on "
					+ appeal.getReceiveDate() + "</p>" + "<p id='appealBlock'>Initially banned for: " + ban.getReason()
					+ "</p>" + "<div id='appealBlock'>" + "<form method='post'>"
					+ "<input type='hidden' name ='action' value='pardon'></input>"
					+ "<input type='hidden' name ='appealID' value='" + appeal.getAppealID() + "'></input>"
					+ "<input type='hidden' name='ign' value='" + ban.getiGN() + "'>"
					+ "</input><button type='submit'>Pardon</button></form>" + "<form method='post'>"
					+ "<input type='hidden' name ='action' value='ignore'></input>"
					+ "<input type='hidden' name ='appealID' value='" + appeal.getiGN() + "'></input>"
					+ "<input type='hidden' name='ign' value='" + ban.getiGN() + "'>"
					+ "</input><button type='submit'>Ignore</button></form>" + "</div>" + "</div>" + "</div>"

					+ "<div id='footer'>" + "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
					+ "<p>We like food</p>" + "<p>"
					+ "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>" + "</p>"
					+ "</div>" + "</body>" + "</html>");
		} catch (ClassNotFoundException e) {
			response.sendRedirect(request.getHeader("referer"));
			e.printStackTrace();
		} catch (SQLException e) {
			response.sendRedirect(request.getHeader("referer"));
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Database db = new Database(2);

			if (request.getParameter("action") != null 
					|| request.getParameter("username") != null
					|| request.getParameter("appealID") != null) {

				if (request.getParameter("action").equals("ignore")) {
					db.ignoreUser(request.getParameter("ign"));
					db.verdictOnAppeal(Integer.parseInt(request.getParameter("appealID")), 2);
				}
				if (request.getParameter("action").equals("pardon")) {
					db.pardonUser(request.getParameter("ign"));
					db.verdictOnAppeal(Integer.parseInt(request.getParameter("appealID")), 1);
				}
			}
			
			response.sendRedirect("AdminBans");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}