package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.encoder.Encode;

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
 * Servlet implementation class Subscription
 */
public class Subscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
		String reall = username;
		String omg = request.getParameter("jjk");//getting filtered name from another page
		if(omg != null && !omg.isEmpty()) {
			reall = omg.replaceAll("\\s","");
		}
		out.println(
				"<!DOCTYPE html>"
						+ "<html>"
						+ "<head>"
						+ "<meta charset='ISO-8859-1'>"
						+ "<meta name='viewport'"
						+ "	content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
						+ "<!-- Page Title -->"
						+ "<title>Forum</title>"
						+ "<!-- Latest compiled and CSS -->");
						out.print("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css' integrity='sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb' crossorigin='anonymous'>");
						out.print("		<!-- Optional Script (Icons) -->");
						out.print("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
						out.print("		<!-- My Own Script -->");
						out.print("		<script src='script/Subscription.js' defer></script>"
						+ "<!-- My Style Sheet -->"
						+ "<link rel='stylesheet' type='text/css' href='css/Subscription.css' />"
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
				+ "					<button style='font-size:25px; cursor:pointer; border:solid; border-radius: 20px 20px 2px 2px; float:left;' class='btn' onclick='goFor()'>Forum</button>"
				+ "					<button style='font-size:25px; cursor:pointer; border:solid; border-radius: 20px 20px 2px 2px; float:left; background-color: white;' class='btn' onclick='gosub()'>My Subscription</button>"
				+ "					<button style='font-size:25px; cursor:pointer; border:solid; border-radius: 20px 20px 2px 2px; float:left;' class='btn' onclick='gotre()'>Trending</button>"
				+ "					<button style='font-size:25px; cursor:pointer; border-color:blue; border-radius: 5px; background-color:red; border:solid;' id='creatingnew' onclick='gonext()' class='btn'>Create New Thread</button>"
				+ "				</p>"
				+ "				</div>"
				+ "				<div id='wholecomments'>"
				+ "					<div id='peoples'>"
				+ "						<p id='paraa'>SUBSCRIPTIONS</p>");



		ArrayList<String> naming = new ArrayList<String>();
		try {
			Database db = new Database(2);
			ArrayList<SubscriptionModel> sm = new ArrayList<SubscriptionModel>();


			sm = db.getSubscriptionModel();
			for(SubscriptionModel a:sm) {
				if(username.equals(a.getSubs())) {
					naming.add(a.getIGN());
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (naming != null || !(naming.isEmpty())) {
			for(String s:naming) {
				out.println(
						"		<form id='thisis' action='Subscription2' method='get'>"
								+ "			<div class='everyone' onclick=\"filter('" + s + "')\">");










				try {
					Database k = new Database(2);
					DatabaseUserModel dumm = new DatabaseUserModel();
					dumm = k.getUserProfile(s);


					if (dumm.getProfilePic() != 0) {

						out.print("<div><img src='Image/" + k.getImageByImageID(dumm.getProfilePic()) + "' style='border-radius:50%;' height='70' width='70' style='border-radius:50%;' /></div>");

					}
					else {
						out.print("<div><img src='images/profile.png' style='border-radius:50%;' height='70' width='70'/></div>");
					}
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}











				out.println(
						//  "			<div><img src='images/tzuyu.jpg' height='70' width='70' style='border-radius:50%;' /></div>"
								"			<div style='font-size:30px; padding-left:3%; '>" + s + "</div>"
								+ "			<input id='omgosh' type='hidden' name='jjk' />"
								+ " 		</div>"
								+ "		</form>"
								+ "		<div class='deleting' style='padding-top:2%;'>"
								+ "     	<button style='width:100%; border:none; cursor:pointer; ' onclick=\"removingSub('" + s + "')\">Unsubscribe</button>"
								+ "		</div>"
								+ "<hr>");

			}
		}
		if(naming.size() == 0) {
			out.println("<p>You have not subscribe to anyone yet!</p>");
		}


		out.println(

				"					</div>"







				//unSUB hidden content
				+ "			<div id='overlay1'>"
				+ "				<div id='backgd1'>"
				+ "					<p id='uniquee'>Are you sure you want to unsubscribe ?</p><hr>"
				+ "					<form id='unsubbing' action='Subscription' method='Post'>"
				+ "						<input type='hidden' id='getthiss' name='theUN'></input>"
				+ "						<input type='hidden' value='" + username + "' name='theUN1'></input>"
				+ "	    				<div>"
				+ "	    					<button type='button'  onclick='unSUB()' class='btn' style='background-color:red; font-size:25px; cursor:pointer;' >UNSUBSCRIBE</button>"
				+ "	    					<button type='button' onclick='backk()' class='btn' style='background-color:white; font-size:25px; cursor:pointer;'>Cancel</button>"
				+ "	    				</div>"
				+ "				  	</form>"
				+ "				  </div>"
				+ "			</div>"






						+ "					<div id='forumm'>"//start forum div
				);//start of filtered forum




		try {
			Database dbms = new Database(2);
			ArrayList<ForumPostModel> fa = dbms.getForumModel();
			ArrayList<CommentModel> cmm = dbms.getCommentModel();
			FileTableModel ftm = new FileTableModel();
			DatabaseUserModel dumm = new DatabaseUserModel();

			boolean haveOrNot = false;
			for(ForumPostModel qw: fa){
				System.out.println("Before loop " + qw.getiGN());
				if(reall.equals(qw.getiGN())) {
					haveOrNot = true;
					System.out.println("Hello " + reall);
					System.out.println("Hello2 " + qw.getiGN());
					int count = 0;
					// Loop through all "his" post
					for(CommentModel df: cmm) {
						if(qw.getPostID() == df.getPostID()) {
							count ++;
							
						}
					}



					ArrayList<ForumVoteModel> fvm = new ArrayList<ForumVoteModel>();
					fvm = dbms.getForumVoteModel();

					int canvote = 0;
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
									+ "										<h2 style='color:blue; cursor:pointer;' onclick='submit(this)'>" + Encode.forHtml(qw.getThread()) + "</h2>"
									+ "										<input type='hidden' name='ForumPostID' value='" + qw.getPostID() + "'></input>"
									+ "									</form>"//form ends here
									+ "							</div>"
									+ "							<div class='subDescription'>"
									+ "								<p>" + Encode.forHtml(qw.getDescription()) + "</p>"
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
									+ "									</form>"//form ends here
									+ "								</p>"
									+ "							</div>"
									+ "						</div>"
									+ "						<div class='author'>"
									+ "							<div class='profilepic'>");

					dumm = dbms.getUserProfile(qw.getiGN());


					if (dumm.getProfilePic() != 0) {
						out.print("<img src='Image/" + dbms.getImageByImageID(dumm.getProfilePic()) + "' style='border-radius:50%;' height='70' width='70'/>");
					}
					else {
						out.print("<img src='images/profile.png' style='border-radius:50%;' height='70' width='70'/>");
					}


					out.println(
							"							</div>"
									+ "							<div id='name'>" + qw.getiGN() + "</div>");
					
					
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
					Date d = new Date();
					String inputString1 = dateFormat.format(qw.getDate());
					String inputString2 = dateFormat.format(d);
					try {
					    Date date1 = dateFormat.parse(inputString1);
					    Date date2 = dateFormat.parse(inputString2);
					    long diff = date2.getTime() - date1.getTime();
					    if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) == 0) {
					    	 out.println( "	 <div id='date'>" + "Posted Recently" + "</div>");
					    }
					    else if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) != 0)
					    out.println( "<div id='date'>" + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " days ago</div>");
					} catch (ParseException e) {
					    e.printStackTrace();
					}



					out.println(
							"				</div>"
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
							);
				}//close if
			}//close loop
			if(!haveOrNot) {
				out.println("<div id='herehere' style='height: 100%; display: flex; justify-content: center; align-items: center;'><p>You have not created any Forum Post Yet!</p></div>");
			}
		}//close try
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





		//end of filtered forum
		out.println(
				"					</div>"		//end forum div
				+ "				</div>"
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
				+ "	</div>");
				out.print("		<!-- Some required javascript -->");
				out.print("		<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>");
				out.print("		<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js' integrity='sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh' crossorigin='anonymous'></script>");
				out.print("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js' integrity='sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ' crossorigin='anonymous'></script>"
				+ "</body>"
				+ "</html>"
				);

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updown = request.getParameter("upordown"); //Total Upvotes
		PrintWriter out = response.getWriter();
		String unSUb = request.getParameter("theUN");
		String unsub = request.getParameter("theUN1");
		//String updown = request.getParameter("upordown");
		System.out.println(updown);
		//goes into the voting thing
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
			}
			else if(unSUb != null && !unSUb.isEmpty()){
				SubscriptionModel sm = new SubscriptionModel();
				sm.setIGN(unSUb);
				sm.setSubs(unsub);
				db.deleteSubscription(sm);
				response.sendRedirect("Subscription");
			}else {
				System.out.println("nope");
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
