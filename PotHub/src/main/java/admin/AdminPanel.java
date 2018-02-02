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
		+ "<h4>Recent Bans</h4>"
		+ "<dl>"
		+ "<dt>Wei Xuan</dt>"
		+ "<dd>Spam</dd>"
		+ "<dt>Matt</dt>"
		+ "<dd>Persistent Vulgarities</dd>"
		+ "<dt>XuanZheng</dt>"
		+ "<dd>Inappropriate messages</dd>"
		+ "<dt>&nbsp;</dt>"
		+ "<dd>&nbsp;</dd>"
		+ "<dt>&nbsp;</dt>"
		+ "<dd>&nbsp;</dd>"
		+ "</dl>"
		+ "</div>"
		+ "<div id='content'>"
		+ "<h4>Recent Pardons</h4>"
		+ "<dl>"
		+ "<dt>Darren</dt>"
		+ "<dd>Harassment</dd>"
		+ "<dt>Xiang Jing</dt>"
		+ "<dd>Multiple Scams</dd>"
		+ "<dt>&nbsp;</dt>"
		+ "<dd>&nbsp;</dd>"
		+ "<dt>&nbsp;</dt>"
		+ "<dd>&nbsp;</dd>"
		+ "<dt>&nbsp;</dt>"
		+ "<dd>&nbsp;</dd>"
		+ "</dl>"
		+ "</div>"
		+ "</div>"
		+ "<div id='sidebar-wrapper'>"
		+ "<div id='sidebar'>"
		+ "<h4>Numbers</h4>"
		+ "<div id='content'>"
		+ "<dl>"
		+ "<dt>New Members / 24 Hours</dt><dd>100</dd>"
		+ "<dt>New Members / 7 Days</dt><dd>500</dd>"
		+ "<dt>Active Members / 24 Hours</dt><dd>200</dd>"
		+ "<dt>Active Members / 7 Days</dt><dd>400</dd>"
		+ "<dt>Total Members</dt><dd>2400</dd>"
		+ "</dl>"
		+ "</div>"
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
