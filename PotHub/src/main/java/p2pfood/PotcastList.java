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
						+ "<!-- Page Title -->"
						+ "<title>Potcast List</title>"
						+ "<!-- Latest compiled and CSS -->"
						+ "<link rel='stylesheet'"
						+ "	href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'"
						+ "	integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ'"
						+ "	crossorigin='anonymous'>"
						+ "<!-- Optional theme -->"
						+ "<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
						+ "<!-- My Own Script -->"
						+ "<script src='script/forum.js'></script>"
						+ "<!-- My Style Sheet -->"
						+ "<link rel='stylesheet' type='text/css' href='css/p2plist.css' />"
						+ "</head>"
						+ "<body>"
						+ "	<!--  Navigation Bar -->"
						+ "	<div id='header'>"
						+ "		<div id='companyTitle'>"
						+ "			<h1>PotHub</h1>"
						+ "		</div>"
						+ "		<div id='profilePic'>"
						+ "			<img src='images/cat.png' height='50' width='50' />"
						+ "		</div>"
						+ "	</div>"
						+ "	<div id='navigation'>"
						+ "		<div class='container-fluid'>"
						+ "			<ul class='nav navbar-nav'>"
						+ "				<li id='lhome'><a href='Forum.html'>Home</a></li>"
						+ "				<li id='lprivatemessage'><a href='PrivateMesage.html'>Private Message</a></li>"
						+ "				<li id='levent'><a href='EventPage.html'>Event</a></li>"
						+ "				<li class='dropdown'>"
						+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Podcast</a>"
						+ "			        <ul class='dropdown-menu'>"
						+ "			          <li><a href='#'>Active PotCasts</a></li>"
						+ "			          <li><a href='#'>Start a PotCast</a></li>"
						+ "			          <li><a href='#'>My PotCast</a></li>"
						+ "			          <li><a href='#'>Joined PotCast</a></li>"
						+ "			        </ul>"
						+ "			      </li>"
						+ "				<li id='ldonate'><a href='Donation.html'>Donate</a></li>"
						+ "			</ul>"
						+ "		</div>"
						+ "	</div>"
						+ "<div id='wrapper'>" + "<div id='secondHeader'>" + "<h2>Potcast</h2>"
						+ "<div id='searchBar'</div>" + "<p>Search Titles: </p>" + "<input type='text'></input>"
						+ "<p>Sort Results By: </p>" + "<div id='search'>" + "<div id='radios'>"
						+ "<p><input type='radio' name='search'></input>  Active Bids</p>"
						+ "<p><input type='radio' name='search'></input>  Bid Closing Time</p>"
						+ "<p><input type='radio' name='search'></input>  Pickup Time</p>"
						+ "<p><input type='radio' name='search'></input>  Address</p>" + "</div>" + "</div>"
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
						+ "<div class='row3'>1234 Street Road #9-34, 465432</div>" + "</div>" + "</div>"
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
						+ "<div class='row3'>12 Road Street #13-34, 465213</div>" + "</div>" + "</div>"

						+ "<div id='displayUnit'>"
						+ "<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
						+ "<div id='column1'>" + "<div class='row1 foodTitle'>Chili Shrimp</div>"
						+ "<div class='row1'>Gordon, 4960CR</div>" + "</div>" + "<div id='column2'>"
						+ "<div class='row2'>7/8 Bids, 4pm 1-1-2018</div>" + "<div class='row2'>$20</div>" + "</div>"
						+ "<div id='column2'>" + "<div class='row2'>8pm</div>"
						+ "<div class='row3'>1234 Street Road #9-34, 465432</div>" + "</div>" + "</div>" + "</div>"

						+ "<div id='footer'>"
						+ "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
						+ "<p>We like food</p>" + "<p>"
						+ "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
						+ "</p>" + "</div>" 						+ "	<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js'"
						+ "		integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n'"
						+ "		crossorigin='anonymous'></script>"
						+ "	<script"
						+ "		src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js'"
						+ "		integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb'"
						+ "		crossorigin='anonymous'></script>"
						+ "	<script"
						+ "		src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'"
						+ "		integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn'"
						+ "		crossorigin='anonymous'></script>"
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
