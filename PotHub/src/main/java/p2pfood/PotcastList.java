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
@WebServlet("/p2plist")
public class PotcastList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PotcastList() {
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
		pw.append("<!DOCTYPE html>"
						+ "<html>"
						+ "<head>"
						+ "<meta charset='ISO-8859-1'>"
						+ "<meta name='viewport'"
						+ "	content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
						+ "<!-- Favicon -->"
						+ "<link rel='icon' href='images/crab.gif' type='image/gif'>"
						+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>"
						+ "<!-- Page Title -->"
						+ "<title>Potcast List</title>"
						+ "<!-- Latest compiled and CSS -->"
						+ " <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'>"
						+ "	<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js'></script>"
						+ "	<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
						+ "	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'></script>"
						+ "<!-- Optional theme -->"
						+ "<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
						+ "<!-- My Own Script -->"
						+ "<script src='script/p2plist.js'></script>"
						+ "<!-- My Style Sheet -->"
						+ "<link rel='stylesheet' type='text/css' href='css/p2plist.css' />"
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
						+ "				<li id='lhome'><a href='html/Forum.html'>Home</a></li>"
						+ "				<li id='lprivatemessage'><a href='html/PrivateMesage.html'>Private Message</a></li>"
						+ "				<li id='levent'><a href='html/EventPage.html'>Event</a></li>"
						+ "				<li class='dropdown'>"
						+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Podcast</a>"
						+ "			        <ul class='dropdown-menu'>"
						+ "			          <li><a href='p2plist'>Active PotCasts</a></li>"
						+ "			          <li><a href='p2preg'>Start a PotCast</a></li>"
						+ "			          <li><a href='p2pmy'>My PotCast</a></li>"
						+ "			          <li><a href='p2pjoined'>Joined PotCast</a></li>"
						+ "			        </ul>"
						+ "			      </li>"
						+ "				<li id='ldonate'><a href='html/Donation.html'>Donate</a></li>"
						+ "			</ul>"
						+ "		</div>"
						+ "	</div>"
						+ "<div id='wrapper'>" + "<div id='secondHeader'>" + "<h2>Potcast</h2>"
						+ "<div id='searchBar'</div>" + "<p>Search Titles: </p>" + "<input type='text'></input>"
						+ "<p>Sort Results By: </p>" + "<div id='search'>" + "<div id='radios'>"
						+ "<ul>"
						+ "<li><input type='radio' name='search' id='radioActiveBids'></input><label for='radioActiveBids'>Active Bids</label></li>"
						+ "<li><input type='radio' name='search' id='radioClosingTime'></input><label for='radioClosingTime'>Bid Closing Time</label></li>"
						+ "<li><input type='radio' name='search' id='radioPickupTime'></input><label for='radioPickupTime'>Pickup Time</label></li>"
						+ "<li><input type='radio' name='search' id='radioPrice'></input><label for='radioPrice'>Price</label></li>"
						+ "<li><input type='radio' name='search' id='radioCR'></input><label for='radioCR'>Cooking Rating</label></li>"
						+ "</ul>"
						+ "</div>"
						+ "<div id='radios2'>"
						+ "<ul>"
						+ "<li><input type='radio' name='search' id='radioAscend'></input><label for='radioAscend'>Ascending</label></li>"
						+ "<li><input type='radio' name='search' id='radioDescend'></input><label for='radioDescend'>Descending</label></li>"
						+ "</ul>"
						+ "</div>"
						+ "</div>"
						+ "</div></div>" + "<h1>Closing soon:</h1>" + "<div id='displayUnit'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Cocktail Shrimp</div>"
						+ "<div class='row1'>Big Matt, 3960CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>6/6 Bids, 5pm 1-1-2018</div>" + "<div class='row2'>$12</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>7.30pm</div>"
						+ "<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>" + "</div>" + "</div>"

						+ "<a href='p2pdetail'>" + "<div id='displayUnit'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Chunk of Beef, Split 11 Ways</div>"
						+ "<div class='row1'>Matt, 1960CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>9/11 Bids, 5pm 1-1-2018</div>" + "<div class='row2'>$8</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>7.30pm</div>"
						+ "<div class='row3'>123 Road Street #09-23, Singapore 445532</div>" + "</div>" + "</div>"
						+ "</a>"

						+ "<div id='displayUnit'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Chili Shrimp</div>"
						+ "<div class='row1'>Gordon, 4960CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>7/8 Bids, 4pm 1-1-2018</div>" + "<div class='row2'>$20</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>8pm</div>"
						+ "<div class='row3'>#9-34 Singapore, 465432</div>" + "</div>" + "</div>"
						+ "<h1>Active Potcasts: </h1>" + "<div id='displayUnit'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Cocktail Shrimp</div>"
						+ "<div class='row1'>Big Matt, 3960CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>6/6 Bids, 5pm 1-1-2018</div>" + "<div class='row2'>$12</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>7.30pm</div>"
						+ "<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>" + "</div>" + "</div>"

						+ "<div id='displayUnit'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Black Pepper Shrimp</div>"
						+ "<div class='row1'>Matt, 1960CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>1/3 Bids, 5pm 1-1-2018</div>" + "<div class='row2'>$8</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>7.30pm</div>"
						+ "<div class='row3'>#13-34 Singapore, 465213</div>" + "</div>" + "</div>"

						+ "<div id='displayUnit'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Chili Shrimp</div>"
						+ "<div class='row1'>Gordon, 4960CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>7/8 Bids, 4pm 1-1-2018</div>" + "<div class='row2'>$20</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>8pm</div>"
						+ "<div class='row3'>#9-34 Singapore, 465432</div>" + "</div>" + "</div>" + "</div>"

						+ "<div id='footer'>"
						+ "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
						+ "<p>We like food</p>" + "<p>"
						+ "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
						+ "</p>" + "</div>" 						
						+ "</body>" + "</html>");
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
