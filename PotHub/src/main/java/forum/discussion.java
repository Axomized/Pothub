package forum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.CommentModel;
import database.model.ForumPostModel;

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
		String ForumID = request.getParameter("ForumPostID");
		int ddd = Integer.parseInt(ForumID);
		System.out.println(ForumID);
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
						+ "		<script src='script/discussion.js'></script>"
						+ "		<!-- My Style Sheet -->"
						+ "		<link rel='stylesheet' type='text/css' href='css/comment1.css' />"
						+ "	</head>"
						+ "	<body>"
						+ "		<!--  Navigation Bar -->"
						+ "		<div id='header'>"
						+ "			<div id='companyTitle'>"
						+ "				<h1>PotHub</h1>"
						+ "			</div>"
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
						+ "				<div id='content' style='width:150%;'>");
						
						
						
						
						
							try {
								Database dbms = new Database(2);
								ArrayList<ForumPostModel> fa = dbms.getForumModel();
								for(ForumPostModel qw: fa){
									if(qw.getPostID() == ddd) {
								
						out.println(
						  "					<div id='title111'>"
						+ "						<div class='iconpic'><img src='images/MAC.png' height='80' width='80'/></div>"
						+ "						<div class='text1'>"
						+ "							<div id='title'><h2 style='color:blue'>" + qw.getThread() +"</h2></div>"
						+ "							<div id='name'>Submitted by:" + qw.getiGN() + "</div>"
						+ "							<div id='date'>" + qw.getDate() + "</div>"
						+ "						</div>"
						+ "						<div>"
						+ "							<p></p>"//for displaying text
						+ "							<p><a href=''></a></p>");// for displaying URL);
						
						
						
						if(!(qw.getFileAttachment().equals(null)) || !(qw.getFileAttachment().equals(""))) {
							String ii = qw.getFileAttachment();
							ArrayList <Integer> IDs = new ArrayList<Integer>();
							Scanner sc = new Scanner(ii);
							sc.useDelimiter(";");
							while(sc.hasNext()) {
								IDs.add(sc.nextInt());
							}
							
							for(int hi: IDs) {
								String fileName = dbms.getFileNameByFileID(hi);
								int extensionPos = fileName.lastIndexOf('.');
								String ext = fileName.substring(extensionPos);
								if(ext.equalsIgnoreCase(".png") || ext.equalsIgnoreCase(".jpeg") || ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".gif")) {
									out.println("<img src='/PotHub/Video/" +  fileName + "' width='200' height='200' />");
								}
								else if(ext.equalsIgnoreCase(".mp4") || ext.equalsIgnoreCase(".webm") || ext.equalsIgnoreCase(".ogg")) {
									out.println("<video src='/PotHub/Video/" +  fileName + "' autoplay loop controls width='200' height='200'/>");
									out.println("</div></div>");
								}
								/*else {
									File tempFile = File.createTempFile(fileName, ".tmp", null);
									FileOutputStream fos = new FileOutputStream(tempFile);
									fos.write(d.getFileData());
									out.println("");
								}
							*/
							
							}
							
						}
									}
								}
								}
							 catch (SQLException e) {
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						
						out.println(
						  "					<div>"
						+ "						<form action='discussion' method='POST'>"	
						+ "						<textarea class='form-control' id='exampleFormControlTextarea1' rows='3' name='rtor'></textarea>"
						+ "						<input type='hidden' name='jesus' value='" + ddd  + "'>"
						+ "						<input type='submit' value='Post/Submit' id='postBtn' cursor:pointer;' class='btn'>"
						+ "						</form>"
						+ "					</div>"
						+ "					<div id='comments'>"
						);

						
						
						
						
						try {
							Database ttttt = new Database(2);
							ArrayList<CommentModel> cc = ttttt.getCommentModel();
							for(CommentModel d:cc) {
								if(d.getPostID() == ddd)
						
						out.println(
						
						  "						<div class='mycomment'>"
						+ "							<div class='author'> "
						+ "								<div class='profilepic'><img src='images/tzuyu.jpg' height='60' width='60'/></div>"
						+ "								<div class='tgt'>"
						+ "									<div>" + d.getiGN() +"</div>"
						+ "									<div>" + d.getDate() + "</div>"
						+ "									"
						+ "								</div>"
						+ "								<div class='reaction'>"
						+ "									<div id='upvote'><i class='fa fa-arrow-up fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='count' style='align-items: center;'>1029</div>"
						+ "									<div id='downvote'><i class='fa fa-arrow-down fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='reports'><a href='#'>Report</a></div>"
						+ "								</div>"
						+ "							</div>"
						+ "							<div id='authorcomments'>"
						+ "								<p>" + d.getDescription()
						+ "								</p>"
						+ "							</div>"
						+ "						</div>"
						+ "						<hr>");
							}
							
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
						
						
						
						out.println(
											
						  "					</div>"
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
		PrintWriter out = response.getWriter();
		String haha = request.getParameter("rtor");
		int id = Integer.parseInt(request.getParameter("jesus"));
		java.sql.Date date1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		try {
			Database ttttt = new Database(2);
			CommentModel cm = new CommentModel();
			cm.setPostID(id);
			cm.setDate(date1);
			cm.setDescription(haha);
			cm.setiGN("GordonRamsey");
			ttttt.addComment(cm);
		
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
						+ "		<script src='script/discussion.js'></script>"
						+ "		<!-- My Style Sheet -->"
						+ "		<link rel='stylesheet' type='text/css' href='css/comment1.css' />"
						+ "	</head>"
						+ "	<body>"
						+ "		<!--  Navigation Bar -->"
						+ "		<div id='header'>"
						+ "			<div id='companyTitle'>"
						+ "				<h1>PotHub</h1>"
						+ "			</div>"
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
						+ "				<div id='content' style='width:150%;'>"
						+ "					<div id='title111'>"
						+ "						<div class='iconpic'><img src='images/MAC.png' height='80' width='80'/></div>"
						+ "						<div class='text1'>"
						+ "							<div id='title'><h2 style='color:blue'>Why is MacDonaldssss Healthy</h2></div>"
						+ "							<div id='name'>Submitted by: Chou Tzu Yu</div>"
						+ "							<div id='date'>5 hours ago</div>"
						+ "						</div>"
						+ "					</div>"
						+ "					<div id='comments'>"
						);

						
						
						
						
						try {
							Database ttttt = new Database(2);
							ArrayList<CommentModel> cc = ttttt.getCommentModel();
							for(CommentModel d:cc) {
								if(d.getPostID() == id) {
						out.println(
						
						  "						<div class='mycomment'>"
						+ "							<div class='author'> "
						+ "								<div class='profilepic'><img src='images/tzuyu.jpg' height='60' width='60'/></div>"
						+ "								<div class='tgt'>"
						+ "									<div>" + d.getiGN() +"</div>"
						+ "									<div>" + d.getDate() + "</div>"
						+ "									"
						+ "								</div>"
						+ "								<div class='reaction'>"
						+ "									<div id='upvote'><i class='fa fa-arrow-up fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='count' style='align-items: center;'>1029</div>"
						+ "									<div id='downvote'><i class='fa fa-arrow-down fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='reports'><a href='#'>Report</a></div>"
						+ "								</div>"
						+ "							</div>"
						+ "							<div id='authorcomments'>"
						+ "								<p>" + d.getDescription()
						+ "								</p>"
						+ "							</div>"
						+ "						</div>"
						+ "						<hr>");
							}
							}
							
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
						
						
						
						out.println(
											
						  "					</div>"
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
	
	
	

}
