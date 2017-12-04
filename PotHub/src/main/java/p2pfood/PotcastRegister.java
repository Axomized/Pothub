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
@WebServlet("/p2preg")
public class PotcastRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PotcastRegister() {
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
				+ "<script src='script/p2plist.js'></script>"
				+ "<!-- My Style Sheet -->"
				+ "<link rel='stylesheet' type='text/css' href='css/p2preg.css' />"
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
				+ "<div id='wrapper'>" + "<div id='secondHeader'>" + "<h2>Start a PotCast</h2>" + "</div>"
				+ "<div id='form'>" + "<div class='formElement'>" + "<p>Food title</p>"
				+ "<input type='text' class='long'></input>" + "</div>" + "<div class='formElement'>" + "<p>Description</p>"
				+ "<input type='text' id='descBox'></input>" + "</div>" + "<div class='formElement'>"
				+ "<p>Portions available</p>" + "<input type='number'></input>"
				+ "<p>Starting Price Per Portion</p>" + "<input type='number'></input>" + "</div>"
				+ "<div class='formElement'>" + "<p>Bid closing time</p>" + "<input type='time' class='veryShort'></input>"
				+ "<p>Collection time</p>" + "<input type='time' class='veryShort'></input>" + "</div>"
				+ "<div class='formElement'>" + "<p>Address</p>" + "<input type='text' class='long'></input>"
				+ "<p>Postal Code</p>" + "<input type='text' class='short'></input>" + "</div>" + "<div class='formElement'>"
				+ "<button>Submit</button>" + "</div>" + "</div>" + "</div>"
				
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
