package p2pfood;

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
@WebServlet("/p2pjoined")
public class PotcastJoined extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PotcastJoined() {
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
		pw.append(
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
						+ "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>" + "<head>"
						+ "<title>My Potcasts</title>" + "<meta http-equiv='content-language' content='en-us' />"
						+ "<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
						+ "<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
						+ "<link rel='stylesheet'"
						+ "href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'"
						+ "integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ'"
						+ "crossorigin='anonymous'>"
						+ "<link rel='stylesheet' type='text/css' media='screen' href='css/p2pmy.css' />" 
						+ "<script src='script/forum.js'></script>"
						+ "</head>"
						+ "<body id='babout'>"
						+ "<div id='header'>"
						+ "<div id='companyTitle'>"
						+ "<h1>PotHub</h1>"
						+ "</div>"
						+ "<div id='profilePic'>"
						+ "<img src='images/cat.png' height='50' width='50' />"
						+ "</div>"
						+ "</div>"
						+ "<div id='navigation'>"
						+ "<div class='container-fluid'>"
						+ "	<ul class='nav navbar-nav'>"
						+ "		<li id='lhome'><a href='Forum.html'>Home</a></li>"
						+ "		<li id='lprivatemessage'><a href='PrivateMesage.html'>Private Message</a></li>"
						+ "		<li id='levent'><a href='EventPage.html'>Event</a></li>"
						+ "		<li class='dropdown'>"
						+ "	        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Podcast</a>"
						+ "	        <ul class='dropdown-menu'>"
						+ "	          <li><a href='#'>Active PotCasts</a></li>"
						+ "	          <li><a href='#'>Start a PotCast</a></li>"
						+ "	          <li><a href='#'>My PotCast</a></li>"
						+ "	          <li><a href='#'>Joined PotCast</a></li>"
						+ "	        </ul>"
						+ "	      </li>"
						+ "		<li id='ldonate'><a href='Donation.html'>Donate</a></li>"
						+ "	</ul>"
						+ "</div>"
						+ "</div>"
						+ "<div id='wrapper'>" + "<div id='secondHeader'>" + "<h2>My Potcasts</h2>"
						+ "<div id='searchBar'</div>" + "<p>Search Titles: </p>" + "<input type='text'></input>"
						+ "<p>Sort Results By: </p>" + "<div id='search'>" + "<div id='radios'>"
						+ "<p><input type='radio' name='search'></input>  Recent</p>"
						+ "<p><input type='radio' name='search'></input>  Active Bids</p>"
						+ "<p><input type='radio' name='search'></input>  Bid Closing Time</p>"
						+ "<p><input type='radio' name='search'></input>  Pickup Time</p>"
						+ "<p><input type='radio' name='search'></input>  Address</p>" + "</div>" + "</div>" + "</div>"
						+ "<div id='displayUnit' class='inactive'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Chicken Curry</div>"
						+ "<div class='row1'>Single Bob, 2370CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>6/6 Bids, 5pm 1-10-2016</div>" + "<div class='row2'>$12</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>7.30pm</div>"
						+ "<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>" + "</div>" + "</div>"

						+ "<div id='displayUnit' class='inactive'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Bad Time Shrimp</div>"
						+ "<div class='row1'>Big Catt, 3060CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>3/6 Bids, 5pm 1-9-2016</div>" + "<div class='row2'>$12</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>7.30pm</div>"
						+ "<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>" + "</div>" + "</div>"
						+ "<div id='displayUnit' class='inactive'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>7 Shots of Vodka Shrimp</div>"
						+ "<div class='row1'>Phoebe, 4060CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>1/5 Bids, 5pm 1-8-2016</div>" + "<div class='row2'>$12</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>7.30pm</div>"
						+ "<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>" + "</div>" + "</div>"
						+ "<div id='displayUnit' class='inactive'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Dog Food</div>"
						+ "<div class='row1'>Dewy, 2460CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>2/6 Bids, 5pm 1-7-2016</div>" + "<div class='row2'>$11</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>7.30pm</div>"
						+ "<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>" + "</div>" + "</div>" + "</div>"
						+ "</div>" + "<div id='footer'>"
						+ "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
						+ "<p>We like food</p>" + "<p>"
						+ "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
						+ "</p>" + "</div>" + "</body>" + "</html>");
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
