package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/AdminGeneral")
public class AdminPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPanel() {
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
		try{
		Database db = new Database(0);
	
		if(session==null||session.getAttribute("username")==null){
    		response.sendRedirect("AdminLogin");
    		return;
		}
		else if(db.getPermissionForIGN((String)session.getAttribute("username"))==2){
			
		PrintWriter pw = response.getWriter();
		pw.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
		+ "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>"
		+ "<head>"
		+ "<!-- Favicon -->"
		+ "<link rel='icon' href='images/crab.gif' type='image/gif'>"
		+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>"
		+ "<title>PotHub Admin</title>"
		+ "<meta http-equiv='content-language' content='en-us' />"
		+ "<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
		+ "<link rel='stylesheet' type='text/css' media='screen' href='css/adminpanelfront.css' />"
		+ "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>"
		+ "</head>"
		+ "<body id='babout'>"
		+ "<div id='header'>"
		+ "<h1> Admin Panel</h1>"
		+ "</div>"
		+ "<div id='navigation'>"
		+"<ul>"
		+"<li>"+"<a href='AdminGeneral'>General</a>"+"</li>"
		+"<li>"+"<a href='AdminBans'>Bans & Appeals</a>"+"</li>"
		+"<li>"+"<a href='AdminDonations'>Donations</a>"+"</li>"
		+"<li>"+"<a href='AdminRanks'>Forum Control</a>"+"</li>"
		+"<li>"+"<a href='AdminReports'>Reports</a>"+"</li>"
		+"</ul>"
		+"<p id='logout'><a href='Logout'>Logout</a></p>"
		+ "</div>"
		+ "<div id='wrapper'>"
 		+ "<div id='content-wrapper'>"
  		+ "<div id='content'>"
		+ "<h4>User privilege</h4>");
		
		pw.append("<div id='piechart' style='width: 400px; height: 400px;'></div>");
		int[] privNumbers = db.getPrivilegedUserNumbers();
		pw.append("<script>"
				+ "google.charts.load('current', {'packages':['corechart']});"
				+ "google.charts.setOnLoadCallback(drawChart);"
				+ "function drawChart() {"

				+ "var data = google.visualization.arrayToDataTable(["
				+ "['Task', 'Loyal users'],"
				+ "['Loyal',     " + privNumbers[0] + "],"
				+ "['Unproven',  " + privNumbers[1] +"]"
				+ "]);"

				+ "var options = {"
				+ "title: 'My Users',"
				+ "backgroundColor: '#f8eee7'"
				+ "};"

				+ "var chart = new google.visualization.PieChart(document.getElementById('piechart'));"

				+ "chart.draw(data, options);"
				+ "}</script>");
		
		pw.append( "</div>"
		+ "<div id='content'>"
		+ "<h4>Recent Pardons</h4>");
		
		pw.append("<div id='piechart2' style='width: 400px; height: 400px;'></div>");
		int[] forgiveNumbers = db.getForgiveness();
		pw.append("<script>"
				+ "google.charts.load('current', {'packages':['corechart']});"
				+ "google.charts.setOnLoadCallback(drawChart);"
				+ "function drawChart() {"

				+ "var data = google.visualization.arrayToDataTable(["
				+ "['Status', 'BansAndAppeals'],"
				+ "['Pardoned',     " + forgiveNumbers[0] + "],"
				+ "['Unpardoned',  " + forgiveNumbers[1] +"]"
				+ "]);"

				+ "var options = {"
				+ "title: 'Pardoned and unpardoned bans',"
				+ "colors: ['yellow','green'],"
				+ "backgroundColor: '#f8eee7'"
				+ "};"

				+ "var chart = new google.visualization.PieChart(document.getElementById('piechart2'));"

				+ "chart.draw(data, options);"
				+ "}</script>");
		
		pw.append( "</div>"
		+ "</div>"
		+ "<div id='sidebar-wrapper'>"
		+ "<div id='sidebar'>"
		+ "<h4>New users in the last 4 weeks</h4>"
		+ "<div id='content'>");
		
		pw.append("<div id='chart_div' style='width: 750px; height: 750px;'></div>");
		
		int[] joins = db.getJoinDatesForGraph();
		pw.append("<script>google.charts.load('current', {packages: ['corechart', 'line']});"
		+ "google.charts.setOnLoadCallback(drawCurveTypes);"

		+ "function drawCurveTypes() {"
		+ "      var data = new google.visualization.DataTable();"
		+ "      data.addColumn('number', 'Week');"
		+ "      data.addColumn('number', 'Joins');"

		+ "      data.addRows(["
		+ "       [1,"+joins[0]+"],"
		+ "       [2,"+joins[1]+"],"
		+ "       [3,"+joins[2]+"],"
		+ "       [4,"+joins[3]+"],"
		+ "      ]);"

		+ "      var options = {"
		+ "        hAxis: {"
		+ "          title: 'Time'"
		+ "        },"
		+ "        vAxis: {"
		+ "          title: 'Joins'"
		+ "        },"
		+ "        series: {"
		+ "          1: {curveType: 'function'}"
		+ "        },"
		+ "			colors: ['purple','orange'],"
		+ "			backgroundColor: '#f8eee7'"
		+ "      };"

		+ "      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));"
		+ "      chart.draw(data, options);"
		+ "    }</script>");
		
		pw.append( "</div>"
		+ "</div>"
		+ "</div>"
		+ "<div id='footer'>"
		+ "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
		+ "<p>We like food</p>"
		+ "<p> <a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a></p>"
		+ "</div>"
		+ "</div>"
		+ "</body>"
		+ "</html>");
	}
		else{
			response.sendRedirect("AdminLogin");
			return;
		}
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
