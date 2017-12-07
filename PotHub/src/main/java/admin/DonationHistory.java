package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PrintWriter pw = response.getWriter();
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
+"<li>"+"<a href='AdminForumControl'>Forum Control</a>"+"</li>"
+"<li>"+"<a href='AdminReports'>Reports</a>"+"</li>"
+"</ul>"
+"<p id='logout'><a href='AdminLogin'>Logout</a></p>"
+ "</div>"
+"<div id='wrapper'>"
  +"<div id='content-wrapper'>"
  +"<h1>Showing Donation History for: USERNAME</h1>"
    + "<div id='tableWrapper'>"
    +"<table class='table table-striped'>"
    +"<thead>"
        +"<tr>"
            +"<th>Recipient</th>"
            +"<th>Donation Amount</th>"
            +"<th>Donation Date</th>"
        +"</tr>"
    +"</thead>"
    +"<tbody>"
    	+"<tr>"
            +"<td>Matt</td>"
            +"<td>$10</td>"
            +"<td>30/10/2017 20:15</td>"
        +"</tr>"+"<tr>"
            +"<td>Appeal</td>"
            +"<td>$10</td>"
            +"<td>30/10/2017 20:15</td>"
        +"</tr>"+"<tr>"
	        +"<td>Inactive Ban</td>"
	        +"<td>$10</td>"
	        +"<td>30/10/2017 20:15</td>"
	    +"</tr>"
        +"<tr>"
	        +"<td>Pardon</td>"
	        +"<td>$10</td>"
	        +"<td>30/10/2017 20:15</td>"
	    +"</tr>"+"<tr>"
	        +"<td>Appeal</td>"
	        +"<td>$10</td>"
	        +"<td>30/10/2017 20:15</td>"
	    +"</tr>"+"<tr>"
	        +"<td>Inactive Ban</td>"
	        +"<td>$10</td>"
	        +"<td>30/10/2017 20:15</td>"
	    +"</tr>"
    +"</tbody>"
+"</table>"
+"</div>"
   +"<div id='fourbox'>"
	+"<div id='search'>"
	+"<p>Donation Amount Between<div id='textboxes'><input type='number'></input></div></p>"
	+"<p>And <div id='textboxes'><input type='number'></input></div></p>"
	+"</div>"
	+ "<div id='search'>"
	+"<p>Donation Date Between<div id='textboxes'><input type='date'></input></div></p>"
	+"<p>And <div id='textboxes'><input type='date' name='banStart2'></input></div></p>"
	+"</div>"
   +"</div>"
   +"<div id='fourbox'>"
   +"<div id='search'>"
   +"<button><</button><button>></button>"
   +"</div>"
   +"<div id='search'>"
   +"<button id='searchButton'>Search</button>"
   +"</div>"
   +"</div>"
+"</div>"
+"</div>"
+"<div id='footer'>"
  +"<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
  +"<p>We like food</p>"
  +"<p>" +"<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"+"</p>"
+"</div>"
+"</body>"
+"</html>");
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