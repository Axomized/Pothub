package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
import database.model.SubscriptionModel;

/**
 * Servlet implementation class Subscription
 */
public class Subscription2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subscription2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int canvote = 1;
		String username = "";
		HttpSession session = request.getSession(false);
		if (session != null) {
			username = (String)session.getAttribute("username");
		}
		else {
			response.sendRedirect("Login");
		}
		String omg = request.getParameter("jjk");//getting filtered name from another page
		String reall = omg.replaceAll("\\s","");
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
						+ "<script src='script/Subscription.js'></script>"
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
						+ "			<div id='profilePic'>"
						+ "				<img src='images/profile.png' height='50' width='50'/>"
						+ "				<span id='welcomeSpan'>Welcome, " + username + "</span>"
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
						+ "				<li id='lprivatemessage'><a href='html/ComingSoon.html'>Private Message</a></li>");
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
									
										out.print("<div><img src='Image/" + k.getImageByImageID(dumm.getProfilePic()) + "' height='70' width='70' style='border-radius:50%;' /></div>");
									
								}
								else {
									out.print("<div><img src='images/profile.png' height='70' width='70'/></div>");
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
										+ "     	<button style='width:100%; border:none; cursor:pointer;' onclick=\"removingSub('" + s + "')\">Unsubscribe</button>"
										+ "		</div>"
										+ "<hr>");
								
							}
						}
						
						
						out.println(
						  "						<div class='everyone'>"
						+ "							<div><img src='images/tzuyu.jpg' height='70' width='70' style='border-radius:50%;' /></div>"
						+ "							<div> Default Name </div>"
						+ "                     </div><hr>"
						+ "					</div>"
						
						
						
						
						
						
						
						//unSUB hidden content
						+ "			<div id='overlay1'>"
						+ "				<div id='backgd1'>"
						+ "					<p id='uniquee'>Are you sure you want to unsubscribe?</p><hr>"
						+ "					<form id='unsubbing' action='Subscription2' method='Post'>"
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
							
							for(ForumPostModel qw: fa){	
								if(reall.equals(qw.getiGN().replaceAll("\\s",""))) {
								int count = 0;
								for(CommentModel df: cmm) {
									if(qw.getPostID() == df.getPostID()) {
										count ++;
									}
								}
								
								
								
								ArrayList<ForumVoteModel> fvm = new ArrayList<ForumVoteModel>();
								fvm = dbms.getForumVoteModel();
								for(ForumVoteModel f:fvm) {
									if(f.getPostID() == qw.getPostID()) {
										if(f.getiGN().equals(username)) {
											canvote = 0;
										}
										else {
											canvote = 1;
										}
									}
									else {
										canvote = 1;
									}
								}
								out.println(
						  
						  "					<div class='cb'>" );
						if(canvote == 1) {
							out.println(	
						  "						<div class='voting'>"
						+ "						<form id='gonext' action='Subscription2' method='Post'>"	
						+ "						<input type='hidden' name='hisname' value=' " + username +"'>"
						+ "						<input type='hidden' name='hisid' value=' " + qw.getPostID() + "'>"
						+ "						<input type='hidden' name='upordown' id='yesorno' >"
						+ "							<p onclick='upfirst()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-up fa-3x' aria-hidden='true'></i>"
						+ "							</p>"
						+ "							<p id='firstcount' style='text-align: center;'>" + qw.getUpvotes() + "</p>"
						+ "							<p onclick='downfirst()' style='text-align: center;'>"
						+ "								<i class='fa fa-arrow-down fa-3x' aria-hidden='true'></i>"
						+ "							</p>"		
						+ "						</form>"
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
						+ "								<h2 style='color: blue' onclick='location.href='discussion';'>" + qw.getThread() + "</h2>"
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
						+ "									<form action='discussion'><button type='submit' style='cursor:pointer' class='btn'>Reply</button>"
						+ "									<input type='hidden' name='ForumPostID' value='" + qw.getPostID() + "'></input></form>"
						+ "									<a onclick='showsth()' href='#reports' style='margin-right: 2%;'>Report</a><a href='#' >Share</a>"
						+ "								</p>"
						+ "							</div>"
						+ "						</div>"
						+ "						<div class='author'>"
						+ "							<div class='profilepic'>"
						+ "								<img src='images/tzuyu.jpg' height='70' width='70' />"
						+ "							</div>"
						+ "							<div id='name'>" + qw.getiGN() + "</div>"
						+ "							<div id='date'>" + qw.getDate() + "</div>");
						
						
						
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
								}//close loop
								}//close if statement for filtering	
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String unSUb = request.getParameter("theUN");
		String unsub = request.getParameter("theUN1");
		String updown = request.getParameter("upordown");
		System.out.println(updown);
		if(updown != null && !updown.isEmpty())
		{
		String validate = updown.replaceAll("\\s","");
		String ignn = request.getParameter("hisname");
		String ign = ignn.replaceAll("\\s","");
		String pos = request.getParameter("hisid");
		String neww = pos.replaceAll("\\s","");
		int pid = Integer.valueOf(neww);
		Timestamp tsm = new Timestamp(System.currentTimeMillis());
		try {
			Database db = new Database(2);
			ForumVoteModel fvm = new ForumVoteModel();
			fvm.setiGN(ign);
			fvm.setPostID(pid);
			fvm.setDate(tsm);
			db.addForumVote(fvm);
			int votes = db.getVotes(pid);
			if(validate.equals("up")) {
			votes ++;
			db.updateVotes(votes, pid);
			out.println("<html>"
					+ "<p>SUCCESS UPVOTING</p>"
					+ "</html>");
			}
			else {
				votes --;
				db.updateVotes(votes, pid);
				out.println("<html>"
						+ "<p>SUCCESS DOWNVOTING</p>"
						+ "</html>");
			}
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//goes into the unsubscribe thing
		}
		else if(unSUb != null && !unSUb.isEmpty()){
			try {
				Database db = new Database(2);
				SubscriptionModel sm = new SubscriptionModel();
				sm.setIGN(unSUb);
				sm.setSubs(unsub);
				db.deleteSubscription(sm);
				out.println("<html>"
						+ "<p>SUCCESS UNSUBSCRIBING</p>"
						+ "</html>");
				
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		else {
			System.out.println("nope");
		}
		
		
		
	}

}
