package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.ForumPostModel;

/**
 * Servlet implementation class Forum
 */
public class Forum extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forum() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE html>"
						+ "<html>"
						+ "<head>"
						+ "<meta charset='ISO-8859-1'>"
						+ "<meta name='viewport'"
						+ "	content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
						+ "<!-- Page Title -->"
						+ "<title>Forum</title>"
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
						+ "<link rel='stylesheet' type='text/css' href='css/Forum.css' />"
						+ "</head>"
						+ "<body>"
						+ "	<!--  Navigation Bar -->"
						+ "	<div id='header'>"
						+ "		<div id='companyTitle'>"
						+ "			<h1>PotHub</h1>"
						+ "		</div>"
						
						+ "		<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>"
						+ "			<div id='profilePic'>"
						+ "				<img src='images/profile.png' height='50' width='50'/>"
						+ "				<span id='welcomeSpan'>Welcome, [Placeholder]</span>"
						+ "			</div>"
						+ "			<div id='profileDropdownDiv'>"
						+ "				<a href='html/Profile.html'>Profile</a>"
						+ "				<a href='html/LoginPage.html'>Logout</a>"
						+ "			</div>"
						+ "		</div>"	
						
						+ "	</div>"
						+ "	<div id='navigation'>"
						+ "		<div class='container-fluid'>"
						+ "			<ul class='nav navbar-nav'>"
						+ "				<li id='lhome'><a href='Forum'>Home</a></li>"
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
						+ "	<div id='wrapper'>"
						+ "		<div id='content-wrapper'>"
						+ "			<div id='content'>"
						+ "				<div id='personalinfo' style='background:url('../images/foodss.jpg'); height:20%;'>"
						+ "				<p style='font-size:50px; text-align:center;'>"
						+ "					Welcome Guest"
						+ "				</p>"
						+ "				<p style='text-align:right; width:98%;'>"
						+ "					<button style='font-size:25px; cursor:pointer;' id='creatingnew' onclick='gonext()' class='btn'>NEW POST</button>		"
						+ "				</p>"
						+ "				</div>"
						+ "				<div id='wholecomments'>"
						+ "					<div class='cb'>" //First div
						+ "						<div class='voting'>"
						+ "							<p onclick='upfirst()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-up fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "							<p id='firstcount' style='text-align: center;'>5</p>"
						+ "							<p onclick='downfirst()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-down fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "						</div>"
						+ "						<div class='iconpic'>"
						+ "							<img src='images/MAC.png' height='80' width='80' />"
						+ "						</div>"
						+ "						<div class='info'>"
						+ "							<div class='title'>"
						+ "								<h2 style='color: blue' onclick='location.href='discussion';'>Why is MacDonalds Healthy</h2>"
						+ "							</div>"
						+ "							<div class='subDescription'>"
						+ "								<p>Betweenn Mc Spicy and Mc Salad, which would you choose for"
						+ "									a healthy meal and more</p>"
						+ "							</div>"
						+ "							<div class='commentscount'>"
						+ "								<p onclick='location.href='discussion';' style='font-family:' Comic SansMS', cursive, sans-serif;'>31"
						+ "									<i class=\'fa fa-comments-o\' style=\'font-size:24px\'></i>"
						+ "								<p>"
						+ "							</div>"
						+ "							<div class='reporting'>"
						+ "								<p>"
						+ "									<a href='discussion' style='margin-right: 2%;'>Reply</a><a onclick='showsth()' href='#reports' style='margin-right: 2%;'>Report</a><a href='#' >Share</a>"
						+ "								</p>"
						+ "							</div>"
						+ "						</div>"
						+ "						<div class='author'>"
						+ "							<div class='profilepic'>"
						+ "								<img src='images/tzuyu.jpg' height='70' width='70' />"
						+ "							</div>"
						+ "							<div id='name'>Chou Tzu FISH</div>"
						+ "							<div id='date'>5 hours ago</div>"
						+ "						</div>"
						+ "					</div>");
						
						
						
						
						try {
							Database dbms = new Database(2);
							ArrayList<ForumPostModel> fa = dbms.getForumModel();
							for(ForumPostModel qw: fa){
								out.println(
							
						  "					<div class='cb'>" 
						+ "						<div class='voting'>"
						+ "							<p onclick='upfirst()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-up fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "							<p id='firstcount' style='text-align: center;'>5</p>"
						+ "							<p onclick='downfirst()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-down fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "						</div>"
						+ "						<div class='iconpic'>"
						+ "							<img src='images/MAC.png' height='80' width='80' />"
						+ "						</div>"
						+ "						<div class='info'>"
						+ "							<div class='title'>"
						+ "								<h2 style='color: blue' onclick='location.href='discussion';'>" + qw.getThread() + "</h2>"
						+ "							</div>"
						+ "							<div class='subDescription'>"
						+ "								<p>Betweenn Mc Spicy and Mc Salad, which would you choose for"
						+ "									a healthy meal and more</p>"
						+ "							</div>"
						+ "							<div class='commentscount'>"
						+ "								<p onclick='location.href='discussion';' style='font-family:' Comic SansMS', cursive, sans-serif;'>31"
						+ "									<i class=\'fa fa-comments-o\' style=\'font-size:24px\'></i>"
						+ "								<p>"
						+ "							</div>"
						+ "							<div class='reporting'>"
						+ "								<p>"
						+ "									<a href='discussion' style='margin-right: 2%;'>Reply</a><a onclick='showsth()' href='#reports' style='margin-right: 2%;'>Report</a><a href='#' >Share</a>"
						+ "								</p>"
						+ "							</div>"
						+ "						</div>"
						+ "						<div class='author'>"
						+ "							<div class='profilepic'>"
						+ "								<img src='images/tzuyu.jpg' height='70' width='70' />"
						+ "							</div>"
						+ "							<div id='name'>" + qw.getiGN() + "</div>"
						+ "							<div id='date'>" + qw.getDate() + "</div>"
						+ "						</div>"
						+ "					</div>");
							}
						}
					 catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						
						
						
						
						
						
						
						
						out.println(
						  "					<div class='cb'>"  // Second Div
						+ "						<div class='voting'>"
						+ "							<p id='secondup' onclick='upsecond()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-up fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "							<p id='secondcount'style='text-align: center;'>19</p>"
						+ "							<p id='seconddown' onclick='downsecond()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-down fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "						</div>"
						+ "						<div class='iconpic'>"
						+ "							<img src='images/kfc.png' height='80' width='80' />"
						+ "						</div>"
						+ "						<div class='info'>"
						+ "							<div id='title'>"
						+ "								<h2 style='color: blue'>KFC 1for1 Fried Chicken</h2>"
						+ "							</div>"
						+ "							<div id='subDescription'>"
						+ "								<p>What a rare chance for fat people to become fatter</p>"
						+ "							</div>"
						+ "							<div id='commentscount'>"
						+ "								<p style='font-family:' Comic SansMS', cursive, sans-serif;'>1292"
						+ "									<i class=\'fa fa-comments-o\' style=\'font-size:24px\'></i>"
						+ "								<p>"
						+ "							</div>"
						+ "							<div id='reporting'>"
						+ "								<p>"
						+ "									<a href='#' style='margin-right: 2%;'>Reply</a><a href='#' style='margin-right: 2%;'>Report</a><a href='#' >Share</a>"
						+ "								</p>"
						+ "							</div>"
						+ "						</div>"
						+ "						<div class='author'>"
						+ "							<div id='profilepic'>"
						+ "								<img src='images/eric chou1.jpg' height='70' width='70' />"
						+ "							</div>"
						+ "							<div id='name'>Eric Chou</div>"
						+ "							<div id='date'>10 hours ago</div>"
						+ "						</div>"
						+ "					</div>"
						+ "					<div class='cb'>"   // Third Div
						+ "						<div class='voting'>"
						+ "							<p id='thirdup' onclick='upthird()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-up fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "							<p id='thirdcount' style='text-align: center;'>1920292</p>"
						+ "							<p id='thirddown' onclick='downthird()'style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-down fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "						</div>"
						+ "						<div class='iconpic'>"
						+ "							<img src='images/crab.jpg' height='80' width='80' />"
						+ "						</div>"
						+ "						<div class='info'>"
						+ "							<div id='title'>"
						+ "								<h2 style='color: blue'>WOOOOO Crabs I LOVE CRABS</h2>"
						+ "							</div>"
						+ "							<div id='subDescription'>"
						+ "								<p>Where to get crabs in Singapore that costs below $10,"
						+ "									find out more here!</p>"
						+ "							</div>"
						+ "							<div id='commentscount'>"
						+ "								<p style='font-family:' Comic SansMS', cursive, sans-serif;'>12231"
						+ "									<i class=\'fa fa-comments-o\' style=\'font-size:24px\'></i>"
						+ "								<p>"
						+ "							</div>"
						+ "							<div id='reporting'>"
						+ "								<p>"
						+ "									<a href='#' style='margin-right: 2%;'>Reply</a><a href='#' style='margin-right: 2%;'>Report</a><a href='#' >Share</a>"
						+ "								</p>"
						+ "							</div>"
						+ "						</div>"
						+ "						<div class='author'>"
						+ "							<div id='profilepic'>"
						+ "								<img src='images/tzuyu.jpg' height='70' width='70' />"
						+ "							</div>"
						+ "							<div id='name'>Chou Tzu Yu</div>"
						+ "							<div id='date'>A year ago</div>"
						+ "						</div>"
						+ "					</div>"
						+ "				</div>"
						+ "			</div>"
						+ "			<div id='overlay'>"
						+ "				<div id='backgd'>"
						+ "					<p>May we know your reason for reporting?</p>"
						+ "					<form>"
						+ "				    	<div class='checkbox'>"
						+ "				      		<label><input type='checkbox' value=''>This post contains spams</label>"
						+ "				   		 </div>"
						+ "				    	<div class='checkbox'>"
						+ "				     		<label><input type='checkbox' value=''>This post contains abusive or harmful words</label>"
						+ "				    	</div>"
						+ "				    	<div class='checkbox'>"
						+ "				      		<label><input type='checkbox' value=''>This post is not relevant to food</label>"
						+ "				    	</div>"
						+ "				    	<div class='checkbox'>"
						+ "				      		<label><input type='checkbox' value=''>This post does not contains wrong informations</label>"
						+ "				   		 </div>"
						+ "				   		 <div class='form-group'>"
						+ "	     					 <label for='Other Comment'>Comment:</label>"
						+ "	      					<textarea class='form-control' rows='5' id='comment'></textarea>"
						+ "	    				</div>"
						+ "	    				<div>"
						+ "	    					<button type='button'  onclick='success()' class='btn'>Submit</button>"
						+ "	    					<button type='button' onclick='cancell()' class='btn'>Cancel</button>"
						+ "	    				</div>"
						+ "				  	</form>"
						+ "				  </div>"
						+ "			</div>"
						+ "		</div>"
						+ "	</div>"
						+ "	<div id='footer'>"
						+ "		<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>"
						+ "		<p>We like food</p>"
						+ "		<p>"
						+ "			<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a"
						+ "				href='#'>Support</a>"
						+ "		</p>"
						+ "	</div>"
						+ "	<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js'"
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
						+ "</body>"
						+ "</html>"
						);
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
