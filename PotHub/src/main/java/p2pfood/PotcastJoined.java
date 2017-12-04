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
		pw.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
				+ "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>" + "<head>"
				+ "<title>My Potcasts</title>" + "<meta http-equiv='content-language' content='en-us' />"
				+ "<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
				+ "<!-- Latest compiled and CSS -->"
				+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
				+ "<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
				+ "<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
				+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
				+ "<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
				+ "<!-- My Own Script -->"
				+ "<script src='script/p2pmy.js'></script>"
				+ "<!-- My Style Sheet -->"
				+ "<link rel='stylesheet' type='text/css' href='css/p2pmy.css' />"
				+ "</head>"
				+ "<body>"
				+ "	<!--  Navigation Bar -->"
				+ "		<div id='header'>"
				+ "<div id='companyTitle'>"
				+ "<h1>PotHub</h1>"
				+ "</div>"
				+ "<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>"
				+ "<div id='profilePic'>"
				+ "<img src='images/profile.png' height='50' width='50'/>"
				+ "<span id='welcomeSpan'>Welcome, [Placeholder]</span>"
				+ "</div>"
				+ "<div id='profileDropdownDiv'>"
				+ "<a href='Profile.html'>Profile</a>"
				+ "<a href='LoginPage.html'>Logout</a>"
				+ "</div>"
				+ "</div>"
				+ "</div>"
				+ "	<div id='navigation'>"
				+ "		<div class='container-fluid'>"
				+ "			<ul class='nav navbar-nav'>"
				+ "				<li id='lhome'><a href='Forum.html'>Home</a></li>"
				+ "				<li id='lprivatemessage'><a href='PrivateMesage.html'>Private Message</a></li>"
				+ "				<li id='levent'><a href='EventPage.html'>Event</a></li>"
				+ "				<li class='dropdown'>"
				+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Podcast</a>"
				+ "			        <ul class='dropdown-menu'>"
				+ "			          <li><a href='p2plist'>Active PotCasts</a></li>"
				+ "			          <li><a href='p2preg'>Start a PotCast</a></li>"
				+ "			          <li><a href='p2pmy'>My PotCast</a></li>"
				+ "			          <li><a href='p2pjoined'>Joined PotCast</a></li>"
				+ "			        </ul>"
				+ "			      </li>"
				+ "				<li id='ldonate'><a href='Donation.html'>Donate</a></li>"
				+ "			</ul>"
				+ "		</div>"
				+ "	</div>"
				+ "<div id='wrapper'>" + "<div id='secondHeader'>" + "<h2>My Potcasts</h2>"
				+ "<div id='searchBar'</div>" + "<p>Search Titles: </p>" + "<input type='text'></input>"
				+ "<p>Sort Results By: </p>" + "<div id='search'>" + "<div id='radios'>"
				+ "<p><input type='radio' name='search'></input>  Recent</p>"
				+ "<p><input type='radio' name='search'></input>  Active Bids</p>"
				+ "<p><input type='radio' name='search'></input>  Bid Closing Time</p>"
				+ "<p><input type='radio' name='search'></input>  Pickup Time</p>"
				+ "<p><input type='radio' name='search'></input>  Address</p>" + "</div>" + "</div>" + "</div></div>"
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
				+ "<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>" + "</div></div></div>"+"<div id='footer'>"
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
