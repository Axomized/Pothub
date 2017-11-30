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
@WebServlet("/AdminReports")
public class ReportPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportPanel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
+"<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>"
+"<head>"
+"<title>PotHub Reports</title>"
+"<meta http-equiv='content-language' content='en-us' />"
+"<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
+"<link rel='stylesheet' type='text/css' media='screen' href='css/banscreen.css' />"
+"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
+"<script src='//cdnjs.cloudflare.com/ajax/libs/tether/1.3.1/js/tether.min.js'></script>"
+"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
+"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
+"</head>"
+"<body>"
+"<div id='header'>"
  +"<h1>Admin Panel</h1>"
+"</div>"
+"<div id='navigation'>"
  +"<ul>"
    +"<li>"+"<a href='AdminGeneral'>General</a>"+"</li>"
    +"<li>"+"<a href='AdminBans'>Bans & Appeals</a>"+"</li>"
    +"<li>"+"<a href='AdminDonations'>Donation History</a>"+"</li>"
    +"<li>"+"<a href='AdminForumControl'>Forum Control</a>"+"</li>"
    +"<li>"+"<a href='AdminSupport'>Support Tickets</a>"+"</li>"
    +"<li>"+"<a href='AdminReports'>Reports</a>"+"</li>"
  +"</ul>"
+"</div>"
+"<div id='wrapper'>"
  +"<div id='content-wrapper'>"
    + "<div id='tableWrapper'>"
    +"<table class='table table-striped'>"
    +"<thead>"
        +"<tr>"
            +"<th>Reporter</th>"
            +"<th>Reported</th>"
            +"<th>Evidence Type</th>"
            +"<th>Date In</th>"
            +"<th>Verdict</th>"
        +"</tr>"
    +"</thead>"
    +"<tbody>"
        +"<tr>"
            +"<td>Dewy</td>"
            +"<td>Phoebe</td>"
            +"<td>Comment</td>"
            +"<td>30/11/2017 20:15</td>"
            +"<td>Innocent<button>Convict</button>"

            +"<a href='HistoryAdminReports'><button>History</button></a></td>"
        +"</tr>"+"<tr>"
            +"<td>Really Sorry Raynard</td>"
            +"<td>Michael Hockenberries</td>"
            +"<td>Comment</td>"
            +"<td>30/11/2017 20:15</td>"
            +"<td>Convicted<button>Innocent</button>"

            +"<a href='HistoryAdminReports'><button>History</button></a></td>"
        +"</tr>"+"<tr>"
            +"<td>Sorry Sophie</td>"
            +"<td>Michael Hockenberries</td>"
            +"<td>Comment</td>"
            +"<td>30/11/2017 20:15</td>"
            +"<td>Undecided<button>Innocent</button><button>Convict</button>"

            +"<a href='HistoryAdminReports'><button>History</button></a></td>"
        +"</tr>"+"<tr>"
	        +"<td>Matt</td>"
	        +"<td>Michael Hockenberries</td>"
	        +"<td>Comment</td>"
	        +"<td>30/11/2017 20:15</td>"
	        +"<td>Undecided<button>Innocent</button><button>Convict</button>"

	        +"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
	    +"</tr>"+"<tr>"
	        +"<td>Matt</td>"
	        +"<td>Michael Hockenberries</td>"
	        +"<td>Comment</td>"
	        +"<td>30/11/2017 20:15</td>"
	        +"<td>Undecided<button>Innocent</button><button>Convict</button>"

	        +"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
		    +"<td>Matt</td>"
		    +"<td>Michael Hockenberries</td>"
		    +"<td>Comment</td>"
		    +"<td>30/11/2017 20:15</td>"
		    +"<td>Undecided<button>Innocent</button><button>Convict</button>"

		    +"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
		    +"<td>Matt</td>"
		    +"<td>Michael Hockenberries</td>"
		    +"<td>Comment</td>"
		    +"<td>30/11/2017 20:15</td>"
		    +"<td>Undecided<button>Innocent</button><button>Convict</button>"

		    +"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"+"<tr>"
			+"<td>Matt</td>"
			+"<td>Michael Hockenberries</td>"
			+"<td>Comment</td>"
			+"<td>30/11/2017 20:15</td>"
			+"<td>Undecided<button>Innocent</button><button>Convict</button>"

			+"<a href='HistoryAdminReports'><button>History</button></a>"+"</td>"
		 +"</tr>"
    +"</tbody>"
+"</table>"
+"</div>"
   +"<div id='fourbox'>"
	+"<div id='search'>"
	+"<p>Search Reporter: <div id='textboxes'><input type='text' name='search'>"+"</input></div></p>"
	+"</div>"
	+"<div id='search'>"
	+"<p>Search Reported: <div id='textboxes'><input type='text' name='search'>"+"</input></div></p>"
	+"</div>"
	+ "<div id='search'>"
	+"<p>Date in between: <div id='textboxes'><input type='date' name='banStart1'></input></div></p>"
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
