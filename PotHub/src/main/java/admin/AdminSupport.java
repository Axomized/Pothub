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
@WebServlet("/AdminSupport")
public class AdminSupport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSupport() {
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
+"<title>PotHub Forum Control</title>"
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
            +"<th>Ticket ID</th>"
            +"<th>Username</th>"
            +"<th>Date In</th>"
            +"<th>Resolve Status</th>"
        +"</tr>"
    +"</thead>"
    +"<tbody>"
        +"<tr>"
    	+"<td>000001</td>"
        +"<td>Repenting Raphael</td>"
        +"<td>1-1-2017 20:45</td>"

        +"<td>Open<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Close</button></td>"
    +"</tr>"+"<tr>"
		+"<td>000002</td>"
		+"<td>Wonky Wei Xuan</td>"
		+"<td>1-1-2017 20:50</td>"

		+"<td>Close<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Reopen</button></td>"
	+"</tr>"
    +"<tr>"
    	+"<td>000003</td>"
        +"<td>Repenting Raphael</td>"
        +"<td>1-1-2017 20:45</td>"

        +"<td>Open<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Close</button></td>"
    +"</tr>"+"<tr>"
		+"<td>000004</td>"
		+"<td>Wonky Wei Xuan</td>"
		+"<td>1-1-2017 20:50</td>"

		+"<td>Close<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Reopen</button></td>"
	+"</tr>"
    +"<tr>"
    	+"<td>000005</td>"
        +"<td>Repenting Raphael</td>"
        +"<td>1-1-2017 20:45</td>"

        +"<td>Open<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Close</button></td>"
    +"</tr>"+"<tr>"
		+"<td>000006</td>"
		+"<td>Wonky Wei Xuan</td>"
		+"<td>1-1-2017 20:50</td>"

		+"<td>Close<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Reopen</button></td>"
	+"</tr>"
    +"<tr>"
    	+"<td>000007</td>"
        +"<td>Repenting Raphael</td>"
        +"<td>1-1-2017 20:45</td>"

        +"<td>Open<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Close</button></td>"
    +"</tr>"+"<tr>"
		+"<td>000008</td>"
		+"<td>Wonky Wei Xuan</td>"
		+"<td>1-1-2017 20:50</td>"

		+"<td>Close<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Reopen</button></td>"
	+"</tr>"
    +"<tr>"
    	+"<td>000009</td>"
        +"<td>Repenting Raphael</td>"
        +"<td>1-1-2017 20:45</td>"

        +"<td>Open<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Close</button></td>"
    +"</tr>"+"<tr>"
		+"<td>00000A</td>"
		+"<td>Wonky Wei Xuan</td>"
		+"<td>1-1-2017 20:50</td>"

		+"<td>Close<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Reopen</button></td>"
	+"</tr>"
    +"<tr>"
    	+"<td>00000B</td>"
        +"<td>Repenting Raphael</td>"
        +"<td>1-1-2017 20:45</td>"

        +"<td>Open<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Close</button></td>"
    +"</tr>"+"<tr>"
		+"<td>00000C</td>"
		+"<td>Wonky Wei Xuan</td>"
		+"<td>1-1-2017 20:50</td>"

		+"<td>Close<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Reopen</button></td>"
	+"</tr>"
    +"<tr>"
    	+"<td>00000D</td>"
        +"<td>Repenting Raphael</td>"
        +"<td>1-1-2017 20:45</td>"

        +"<td>Open<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Close</button></td>"
    +"</tr>"+"<tr>"
		+"<td>00000E</td>"
		+"<td>Wonky Wei Xuan</td>"
		+"<td>1-1-2017 20:50</td>"

		+"<td>Close<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Reopen</button></td>"
	+"</tr>"
    +"<tr>"
    	+"<td>00000F</td>"
        +"<td>Repenting Raphael</td>"
        +"<td>1-1-2017 20:45</td>"

        +"<td>Open<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Close</button></td>"
    +"</tr>"+"<tr>"
		+"<td>000010</td>"
		+"<td>Wonky Wei Xuan</td>"
		+"<td>1-1-2017 20:50</td>"

		+"<td>Close<a href='SupportTicketView'><button>Read</button></a><a href='HistoryAdminSupport'><button>History</button></a><button>Reopen</button></td>"
	+"</tr>"
+"</tbody>"
+"</table>"
+"</div>"
+"</div>"
+"<div id='fourbox'>"
	+"<div id='search'>"
	+"<p>Search Ticket ID: <div id='textboxes'><input type='text' name='search'>"+"</input></div></p>"
	+"</div>"
	+"<div id='search'>"
	+"<p>Search Username: <div id='textboxes'><input type='text' name='search'>"+"</input></div></p>"
	+"</div>"
	+ "<div id='search'>"
	+"<p>Ticket Date Between<div id='textboxes'><input type='date' name='banStart1'></input></div></p>"
	+"<p>And <div id='textboxes'><input type='date' name='banStart2'></input></div></p>"
	+"</div>"
+"</div>"
+"<div id='fourbox'>"
	+"<div id='search'>"
	+"<div id='radios'>"
	+"<p><input type='radio' name='search'></input>  Resolved</p>"
	+"<p><input type='radio' name='search'></input>  Unresolved</p>"
	+"</div>"
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
