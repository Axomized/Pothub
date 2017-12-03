package forum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class discussion
 */
public class discussion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public discussion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE html>"
						+ "<html>"
						+ "	<head>"
						+ "		<meta charset='ISO-8859-1'>"
						+ "		<meta name='viewport'"
						+ "			content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
						+ "		<!-- Page Title -->"
						+ "		<title>Default Title</title>"
						+ "		<!-- Latest compiled and CSS -->"
						+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
						+ "		<!-- Optional theme -->"
						+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
						+ "		<!-- My Own Script -->"
						+ "		<script src='script/'></script>"
						+ "		<!-- My Style Sheet -->"
						+ "		<link rel='stylesheet' type='text/css' href='css/comment1.css' />"
						+ "	</head>"
						+ "	<body>"
						+ "		<!--  Navigation Bar -->"
						+ "		<div id='header'>"
						+ "			<div id='companyTitle'>"
						+ "				<h1>PotHub</h1>"
						+ "			</div>"
						+ "			<div id='profilePic'>"
						+ "				<img src='images/profile.png' height='50' width='50'/>"
						+ "				<span id='welcomeSpan'>Welcome, [Placeholder]</span>"
						+ "			</div>"
						+ "		</div>"
						+ "		<div id='navigation'>"
						+ "			<ul>"
						+ "				<li id='lhome'><a href='#00'>Home</a></li>"
						+ "				<li id='lprivatemessage'><a href='#01'>Private Message</a></li>"
						+ "				<li id='levent'><a href='#02'>Event</a></li>"
						+ "				<li id='lpeer2peer'><a href='#03'>Peer-2-Peer</a></li>"
						+ "				<li id='ldonate'><a href='#04'>Donate</a></li>"
						+ "			</ul>"
						+ "		</div>"
						+ "		<div id='wrapper'>"
						+ "			<div id='content-wrapper'>"
						+ "				<div id='content'>"
						+ "					<div id='title111'>"
						+ "						<div class='iconpic'><img src='images/MAC.png' height='80' width='80'/></div>"
						+ "						<div class='text1'>"
						+ "							<div id='title'><h2 style='color:blue'>Why is MacDonaldssss Healthy</h2></div>"
						+ "							<div id='name'>Submitted by: Chou Tzu Yu</div>"
						+ "							<div id='date'>5 hours ago</div>"
						+ "						</div>"
						+ "					</div>"
						+ "					<div id='comments'>"
						+ "						<div class='mycomment'>"
						+ "							<div id='author'> "
						+ "								<div id='profilepic'><img src='images/tzuyu.jpg' height='60' width='60'/></div>"
						+ "								<div class='tgt'>"
						+ "									<div id='name'>Chou Tzu Yuuu</div>"
						+ "									<div id='date'>A year ago</div>"
						+ "									"
						+ "								</div>"
						+ "								<div id='reaction'>"
						+ "									<div id='upvote'><i class='fa fa-arrow-up fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='count' style='align-items: center;'>1029</div>"
						+ "									<div id='downvote'><i class='fa fa-arrow-down fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='reports'><a href='#'>Report</a></div>"
						+ "								</div>"
						+ "							</div>"
						+ "							<div id='authorcomments'>"
						+ "								<p>When talking about fast food giants, nothing can be bigger â€“ and more infamous â€“ than McDonald's. Those two 'golden arches' sign"
						+ "									are so abundant and so well-known all over the world, that some toddlers"
						+ "								 	 can even recognize it even before they are able to speak full sentences. "
						+ "									In 2014, over 36,258 McDonald's restaurants operate worldwide,1 serving over 69 million people every day. From its humble"
						+ "									beginnings in the 1940s, the brand has now grown to be a multi-billion dollar company with an estimated value of over $85 billion."
						+ "								</p>"
						+ "							</div>"
						+ "							"
						+ "						</div>"
						+ "						<hr>"
						+ "						<div class='mycomment'>"
						+ "							<div id='author'> "
						+ "								<div id='profilepic'><img src='images/tzuyu.jpg' height='60' width='60'/></div>"
						+ "								<div class='tgt'>"
						+ "									<div id='name'>Chou Tzu Yu</div>"
						+ "									<div id='date'>A year ago</div>"
						+ "								</div>"
						+ "								<div id='reaction'>"
						+ "									<div id='upvote'><i class='fa fa-arrow-up fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='count'>1029</div>"
						+ "									<div id='upvote'><i class='fa fa-arrow-down fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='reports'><a href='#'>Report</a></div>"
						+ "								</div>"
						+ "							</div>"
						+ "							<div id='authorcomments'>"
						+ "								<p>When talking about fast food giants, nothing can be bigger â€“ and more infamous â€“ than McDonald's. Those two 'golden arches' sign"
						+ "									are so abundant and so well-known all over the world, that some toddlers"
						+ "								 	 can even recognize it even before they are able to speak full sentences. "
						+ "									In 2014, over 36,258 McDonald's restaurants operate worldwide,1 serving over 69 million people every day. From its humble"
						+ "									beginnings in the 1940s, the brand has now grown to be a multi-billion dollar company with an estimated value of over $85 billion."
						+ "								</p>"
						+ "							</div>"
						+ "							"
						+ "						</div>"
						+ "						<hr>"
						+ "						<div class='mycomment'>"
						+ "							<div id='author'> "
						+ "								<div id='profilepic'><img src='images/tzuyu.jpg' height='60' width='60'/></div>"
						+ "								<div class='tgt'>"
						+ "									<div id='name'>Chou Tzu Yu</div>"
						+ "									<div id='date'>A year ago</div>"
						+ "								</div>"
						+ "								<div id='reaction'>"
						+ "									<div id='upvote'><i class='fa fa-arrow-up fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='count'>1029</div>"
						+ "									<div id='upvote'><i class='fa fa-arrow-down fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='reports'><a href='#'>Report</a></div>"
						+ "								</div>"
						+ "							</div>"
						+ "							<div id='authorcomments'>"
						+ "								<p>When talking about fast food giants, nothing can be bigger â€“ and more infamous â€“ than McDonald's. Those two 'golden arches' sign"
						+ "									are so abundant and so well-known all over the world, that some toddlers"
						+ "								 	 can even recognize it even before they are able to speak full sentences. "
						+ "									In 2014, over 36,258 McDonald's restaurants operate worldwide,1 serving over 69 million people every day. From its humble"
						+ "									beginnings in the 1940s, the brand has now grown to be a multi-billion dollar company with an estimated value of over $85 billion."
						+ "								</p>"
						+ "							</div>"
						+ "						</div>"
						+ "					</div>"
						+ "				</div>"
						+ "				<div id='content'>"
						+ "				</div>"
						+ "			</div>"
						+ "		</div>"
						+ "		<div id='footer'>"
						+ "			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>"
						+ "			<p>We like food</p>"
						+ "			<p>"
						+ "				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
						+ "			</p>"
						+ "		</div>"
						+ "		<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
						+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
						+ "		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
						+ "	</body>"
						+ "</html>"


				);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
