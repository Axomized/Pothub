package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adminSearch.DonationSearchObject;
import database.Database;
import database.model.DonationModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/HistoryAdminDonations")
public class DonationHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonationHistory() {
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

		if(session==null||session.getAttribute("user")==null){
    		response.sendRedirect("AdminLogin");
    		return;
		}
		else if(db.getPermissionForIGN((String)session.getAttribute("user"))!=2){
    		response.sendRedirect("AdminLogin");
    		return;
		}
		else if(db.getPermissionForIGN((String)session.getAttribute("user"))==2){
		
		PrintWriter pw = response.getWriter();
		
		DonationSearchObject dso = new DonationSearchObject();
		
		String userSubject = request.getParameter("user");
		if(request.getParameter("amount1") != null && !request.getParameter("amount1").equals("")){
			dso.setDonationAmountOpen(BigDecimal.valueOf(Long.parseLong(request.getParameter("amount1"))));
		}
		if(request.getParameter("amount2") != null && !request.getParameter("amount2").equals("")){
			dso.setDonationAmountClose(BigDecimal.valueOf(Long.parseLong(request.getParameter("amount2"))));
		}
		if(request.getParameter("date1")!=null && request.getParameter("date1").length()>0){
			dso.setDonationDateOpen(Date.valueOf(request.getParameter("date1")));
		}
		if(request.getParameter("date2")!=null && request.getParameter("date2").length()>0){
			dso.setDonationDateClose(Date.valueOf(request.getParameter("date2")));
		}
		if(request.getParameter("recipient")!=null){
			dso.setOnBehalf(request.getParameter("recipient"));
		}
		pw.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
+"<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>"
+"<head>"
+ "<!-- Favicon -->"
+ "<link rel='icon' href='images/crab.gif' type='image/gif'>"
+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>"
+"<title>PotHub Donation History</title>"
+"<meta http-equiv='content-language' content='en-us' />"
+"<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
+"<link rel='stylesheet' type='text/css' media='screen' href='css/banscreen.css' />"
+"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
+"<script src='//cdnjs.cloudflare.com/ajax/libs/tether/1.3.1/js/tether.min.js'></script>"
+"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
+"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
+"</head>"
+"<body id='babout'>"
+"<div id='header'>"
  +"<h1>Admin Panel</h1>"
+"</div>"
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
+"<div id='wrapper'>"
  +"<div id='content-wrapper'>"
  +"<h1>Showing Donation History for: "+userSubject+"</h1>"
    + "<div id='tableWrapper'>"
    +"<table class='table table-striped'>"
    +"<thead>"
        +"<tr>"
            +"<th>Recipient</th>"
            +"<th>Donation Amount</th>"
            +"<th>Donation Date</th>"
        +"</tr>"
    +"</thead>"
    +"<tbody>");

	try {
		if(userSubject!=null){
			dso.setiGN(userSubject);
		}
		ArrayList<DonationModel> donations = db.getDonationModel(dso);
		
		for(DonationModel dm : donations){
			pw.append("<tr>");
			
			if(dm.getOnBehalf()!=null){
				pw.append("<td>"+dm.getOnBehalf()+"</td>");
			}
			else{
				pw.append("<td>"+dm.getiGN()+"</td>");
			}
			pw.append("<td>"+dm.getDonationAmount()+"</td>");
			pw.append("<td>"+dm.getDonationDate()+"</td>");
			pw.append("</tr>");
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
		
    pw.append("</tbody>"
+"</table>"
+"</div>"
+"<form>"
   +"<div id='fourbox'>"
	+"<div id='search'>"
	+"<p>Donation Amount Between<div id='textboxes'><input type='number' name='amount1'></input></div></p>"
	+"<p>And <div id='textboxes'><input type='number' name='amount2'></input></div></p>"
	+"</div>"
	+ "<div id='search'>"
	+"<p>Donation Date Between<div id='textboxes'><input type='date' name='date1'></input></div></p>"
	+"<p>And <div id='textboxes'><input type='date' name='date2'></input></div></p>"
	+"</div>"
   +"</div>"
   +"<div id='fourbox'>"
   +"<div id='search'>"
	+"<p>Recipient<div id='textboxes'><input type='text' name='recipient'></input></div></p>"
   +"</div>"
   +"</div>"
   +"<div id='fourbox'>"
   +"<div id='search'>"
   +"<button id='searchButton' type='submit'>Search</button>"
   +"</div>"
   +"</div>"
+"</div>"
+"</div>"
+"</form>"
+"<div id='footer'>"
  +"<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
  +"<p>We like food</p>"
  +"<p>" +"<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"+"</p>"
+"</div>"
+"</body>"
+"</html>");
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