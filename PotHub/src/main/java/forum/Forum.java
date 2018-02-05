package forum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.CommentModel;
import database.model.DatabaseUserModel;
import database.model.FileTableModel;
import database.model.ForumPostModel;
import database.model.ForumVoteModel;
import database.model.LogsModel;
import database.model.SubscriptionModel;
import login.BanChecker;

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
		String username = "";

		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
			if(BanChecker.isThisGuyBanned(username)){
				response.sendRedirect("Login");
				return;
			}
		}
		else {
			response.sendRedirect("Login");
		}
		System.out.println(username);
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
						+ "<script src=\"http://code.jquery.com/jquery-3.3.1.min.js\" integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\" crossorigin=\"anonymous\"></script>"
						+ "<script src='script/forum.js' defer></script>"
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
						+ "			<div id='profilePic'>");

		try {
			Database dc = new Database(2);
			DatabaseUserModel dumdum = dc.getUserProfile(username);
			if (dumdum.getProfilePic() != 0) {
				out.print("<img src='Image/" + dc.getImageByImageID(dumdum.getProfilePic()) + "' style='border-radius:50%;' height='50' width='50'/>");
			}
			else {
				out.print("<img src='images/profile.png' class='roundProfilePic' height='50' width='50'/>");
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		out.println(
				"				<span id='welcomeSpan'>Welcome, " + username + "</span>"
						+ "			</div>"
						+ "			<div id='profileDropdownDiv'>"
						+ "				<a href='Profile'>Profile</a>"
						+ "				<a href='Logout'>Logout</a>"
						+ "			</div>"
						+ "		</div>"	

						+ "	</div>"
						+ "	<div id='navigation'>"
						+ "		<div class='container-fluid'>"
						+ "			<ul class='nav navbar-nav'>"
						+ "				<li id='lhome'><a href='Forum'>Home</a></li>"
				);
		out.print("					<li class='dropdown'>");
		out.print("		        		<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>");
		out.print("			        	<ul class='dropdown-menu'>");
		out.print("			        		<li><a href='EventPage'>Events</a></li>");
		out.print("		        			<li><a href='MyEventPage'>My Events</a></li>");
		out.print("			        	</ul>");
		out.print("		    		</li>");
		out.print("				<li class='dropdown'>"
				+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Podcast</a>"
				+ "			        <ul class='dropdown-menu'>"
				+ "			          <li><a href='p2plist'>Active PotCasts</a></li>"
				+ "			          <li><a href='p2preg'>Start a PotCast</a></li>"
				+ "			          <li><a href='p2pmy'>My PotCast</a></li>"
				+ "			          <li><a href='p2pjoined'>Joined PotCast</a></li>"
				+ "			        </ul>"
				+ "			      </li>"
				+ "				<li id='ldonate'><a href='Donation'>Donate</a></li>"
				+ "			</ul>"
				+ "		</div>"
				+ "	</div>"
				+ "	<div id='wrapper'>"
				+ "		<div id='content-wrapper'>"
				+ "			<div id='content'>"
				+ "				<div id='personalinfo' style='background:url('../images/foodss.jpg'); height:20%;'>"
				+ "				<p style='font-size:50px; text-align:center;'>"
				+ "				Welcome " + username + ""
				+ "				</p>"
				+ "				<p style='text-align:right; width:98%; padding-left:2%;'>"
				+ "					<button style='font-size:25px; cursor:pointer; border:solid; border-radius: 20px 20px 2px 2px; float:left; background-color:white;' class='btn' onclick='gofor()'>Forum</button>"
				+ "					<button style='font-size:25px; cursor:pointer; border:solid; border-radius: 20px 20px 2px 2px; float:left;' class='btn' onclick='gosub()'>My Subscription</button>"
				+ "					<button style='font-size:25px; cursor:pointer; border:solid; border-radius: 20px 20px 2px 2px; float:left;' class='btn' onclick='gotre()'>Trending</button>"
				+ "					<button style='font-size:25px; cursor:pointer; border-color:blue; border-radius: 5px; background-color:red; border:solid;' id='creatingnew' onclick='gonext()' class='btn'>Create New Thread</button>		"
				+ "				</p>"
				+ "				</div>"
				+ "				<div id='wholecomments'>"
				);




		try {
			Database dbms = new Database(2);
			ArrayList<ForumPostModel> fa = dbms.getForumModel();
			ArrayList<CommentModel> cmm = dbms.getCommentModel();
			ArrayList<SubscriptionModel> smm = dbms.getSubscriptionModel();
			FileTableModel ftm = new FileTableModel();
			DatabaseUserModel dumm = new DatabaseUserModel();

			ArrayList<String> checkingg = new ArrayList<String>();


			for(ForumPostModel qw: fa){	
				int count = 0;
				int po = 1;
				int canvote = 0;
				for(CommentModel df: cmm) {
					if(qw.getPostID() == df.getPostID()) {
						count ++;
					}
				}

				if(dbms.getWhetherCanVoteForumVoteModel(username, qw.getPostID())) { // so this is no duplicate? yea oki go try
					canvote = 1;
				}

				out.println(

						"					<div class='cb'>" );
				if(canvote == 1) {
					out.println(	
							"						<div class='voting'>"
									+ "						<input type='hidden' name='hisname' value='" + username +"'>"
									+ "						<input type='hidden' name='hisid' value='" + qw.getPostID() + "'>"						
									+ "						<input type='hidden' name='upordown' id='yesorno' >"
									+ "							<p onclick='upfirst(this)' style='text-align: center;'>"
									+ "								<i style='cursor:pointer;' class='fa fa-arrow-up fa-3x' aria-hidden='true'></i>"
									+ "							</p>"
									+ "							<p id='firstcount' style='text-align: center;'>" + qw.getUpvotes() + "</p>"
									+ "							<p onclick='downfirst(this)' style='text-align: center;'>"
									+ "								<i style='cursor:pointer;' class='fa fa-arrow-down fa-3x' aria-hidden='true'></i>"
									+ "							</p>"		
									+ "						</div>"
									+ "						<div class='iconpic'>"
							);
				}
				else if(canvote == 0) {
					out.println("<div class='voting'>"
							+ "<p style='text-align: center;'>"
							+ "<i class='fa fa-arrow-up fa-3x' aria-hidden='true'></i>"
							+ "</p>"
							+ "<p id='firstcount' style='text-align: center;'>" + qw.getUpvotes() + "</p>"
							+ "<p style='text-align: center;'>"
							+ "<i class='fa fa-arrow-down fa-3x' aria-hidden='true'></i>"
							+ "</p>"
							+"</div>"
							+"<div class='iconpic'>"
							);
				}

				if(qw.getPicture() == 1) {
					out.println("<img src='images/MAC.png' height='80' width='80' />");
				}
				else {
					ftm = dbms.getFileTableByFileID(qw.getPicture());
					out.println("<img src='Video/" +  ftm.getFileName() + "' height='80' width='80' />");
				}


				//+ "							<img src='images/MAC.png' height='80' width='80' />"



				out.println(
						"						</div>"
								+ "						<div class='info'>"
								+ "							<div class='title'>"
								+ "									<form action='discussion'>"
								+ "										<h2 style='color:blue; cursor:pointer;' onclick='submit(this)'>" + qw.getThread() + "</h2>"
								+ "										<input type='hidden' name='ForumPostID' value='" + qw.getPostID() + "'></input>"
								+ "									</form>"//form ends here
								+ "							</div>"
								+ "							<div class='subDescription'>"
								+ "								<p>" + qw.getDescription() + "</p>"
								+ "							</div>"
								+ "							<div class='commentscount'>"
								+ "								<p onclick='location.href='discussion';' style='font-family:' Comic SansMS', cursive, sans-serif;'>" + count +""
								+ "									<i class=\'fa fa-comments-o\' style=\'font-size:24px\'></i>"
								+ "								<p>"
								+ "							</div>"
								+ "							<div class='reporting'>"
								+ "								<p>"
								+ "									<form action='discussion' id='dd1'>"
								+ "									<button type='submit' style='cursor:pointer' class='btn'>Reply</button>"
								+ "									<input type='hidden' name='ForumPostID' value='" + qw.getPostID() + "'></input>"
								+ "									<a onclick=\"showsth('" + qw.getPostID() + ", " + qw.getiGN() + "')\"  style='margin-right: 2%;'>Report</a>"
								+ "									</form>"//form ends here
								+ "								</p>"
								+ "							</div>"
								+ "						</div>"
								+ "						<div class='author'>"
								+ "							<div class='profilepic'>");
				//String fileName = dbms.getUserProfilePic(qw.getiGN());
				dumm = dbms.getUserProfile(qw.getiGN());


				if (dumm.getProfilePic() != 0) {
					out.print("<img src='Image/" + dbms.getImageByImageID(dumm.getProfilePic()) + "' height='70' width='70'/>");
				}
				else {
					out.print("<img src='images/profile.png' height='70' width='70'/>");
				}





				//+ "								<img src='images/tzuyu.jpg' height='70' width='70' />"
				out.print("							</div>"
						+ "							<div id='name'>" + qw.getiGN() + "</div>");

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				out.println( "							<div id='date'>" + dateFormat.format(qw.getDate()) + "</div>");

				for(SubscriptionModel pp :smm) {
					if(pp.getSubs().replaceAll("\\s","").equals(username.replaceAll("\\s",""))) {
						checkingg.add(pp.getIGN()); // arraylist of people he subbed to
					}
				}
				for(String fd : checkingg) {
					if(fd.replaceAll("\\s","").equals(qw.getiGN())) {// check if the author is inside the arralist
						po = 2;
					}
				}

				if(qw.getiGN().replaceAll("\\s","").equals(username.replaceAll("\\s",""))) {
					out.println("						</div>"
							+ "					</div>");
				}
				else if(po == 2) {
					out.println(
							"							<div class='subscribe'>"
							//+ "								<form action='Forum' method='post'>"
							+ "									<button style='border:none; background-color:white;' type='submit'>Subscribed</button>"
							//+ "									<input type='hidden' name='nnn' value='" +  username +"'></input>"
							//+ "									<input type='hidden' name='mmm' value='" + qw.getiGN()  + "'></input>"
							//+ "								</form>"
							+ "							</div>"//close div for subscribe
							+ "						</div>"
							+ "					</div>");
				}
				else {

					out.println(
							"							<div class='subscribe'>"
									+ "								<form action='Forum' method='post'>"
									+ "									<button style='border:none; background-color:red; cursor:pointer;' type='submit'>Subscribe</button>"
									+ "									<input type='hidden' name='nnn' value='" +  username +"'></input>"
									+ "									<input type='hidden' name='mmm' value='" + qw.getiGN()  + "'></input>"
									+ "								</form>"
									+ "							</div>"//close div for subscribe
									+ "						</div>"
									+ "					</div>");


				}
			}//close loop
		}//close try
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}






		//report


		out.println(
				"				</div>"
						+ "			</div>"
						+ "			<div id='overlay'>"
						+ "				<div id='backgd'>"
						+ "					<p>May we know your reason for reporting?</p>"
						+ "					<form id='whyareyou' action='SuccessReporting' method='POST'>"
						+ "						<input type='hidden' id='storeReport' name='forumID'></input>"
						+ "						<input type='hidden' value='" + username + "' name='ignsend'></input>"
						+ "						<input type='hidden' id='kimtan' name='whyyoureport'></input>"
						//+ "						<input type='hidden' id='kimjung' name='forumID'></input>"
						+ "				    	<div class='checkbox'>"
						+ "				      		<label><input type='checkbox' value='This post contains spams' id='Check1' onclick='selectOnlyThis(this.id)'>This post contains spams</label>"
						+ "				   		 </div>"
						+ "				    	<div class='checkbox'>"
						+ "				     		<label><input type='checkbox' value='This post contains abusive or harmful words' id='Check2' onclick='selectOnlyThis(this.id)'>This post contains abusive or harmful words</label>"
						+ "				    	</div>"
						+ "				    	<div class='checkbox'>"
						+ "				      		<label><input type='checkbox' value='This post is not relevant to food' id='Check3' onclick='selectOnlyThis(this.id)'>This post is not relevant to food</label>"
						+ "				    	</div>"
						+ "				    	<div class='checkbox'>"
						+ "				      		<label><input type='checkbox' value='This post does not contains wrong informations' id='Check4' onclick='selectOnlyThis(this.id)'>This post does not contains wrong informations</label>"
						+ "				   		 </div>"
						+ "				   		 <div class='form-group'>"
						+ "	     					 <label for='Other Comment'>Comment:</label>"
						+ "	      					<textarea onclick='checkforcheck()' class='form-control' rows='5' id='comment' name='somanytext'></textarea>"
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
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String updown = request.getParameter("upordown"); //Total Upvotes
		System.out.println(updown);
		try {
			Database db = new Database(2);
			if(updown != null && !updown.isEmpty()){
				String ignn = request.getParameter("hisname"); //IGN
				Integer pos = Integer.valueOf(request.getParameter("hisid")); //PostID
				Timestamp tsm = new Timestamp(System.currentTimeMillis());
				ForumVoteModel fvm = new ForumVoteModel();
				LogsModel lm = new LogsModel();
				fvm.setiGN(ignn);
				fvm.setPostID(pos);
				fvm.setDate(tsm);
				db.addForumVote(fvm);
				db.updateVotes(Integer.parseInt(updown), pos);
				ForumPostModel fpm = new ForumPostModel();
				fpm = db.getForumModelByID(pos);
				String name3 = fpm.getiGN();
				DatabaseUserModel dum = db.getUserProfile(name3);
				int totalpoints = dum.getPoints();
				totalpoints ++;
				db.addDatabaseUserPoints(totalpoints, name3);
				DatabaseUserModel dum2 = db.getUserProfile(name3);
				if(dum2.getPoints() > 49 && !dum.isPriviledged()) {
					db.updateIsPrivileged(true, name3);

					lm.setiGN(name3);
					lm.setLogDate(Timestamp.from(Instant.now()));
					lm.setiPAddress(lm.getClientIP(request));
					lm.setLogType("Others");
					lm.setLogActivity(name3 + " became privileged");
					db.insertLogs(lm);
				}
			}else {
				String ign11 = request.getParameter("nnn");
				String author1 = request.getParameter("mmm");
				SubscriptionModel sm = new SubscriptionModel();
				sm.setIGN(author1);
				sm.setSubs(ign11);
				db.insertSubscription(sm);
				response.sendRedirect("Forum");
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
