package forum;

import java.io.File;
import java.io.FileInputStream;
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
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.CommentModel;
import database.model.DatabaseUserModel;
import database.model.FileTableModel;
import database.model.ForumPostModel;
import login.BanChecker;

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
						+ "		<div id='wrapper'>"
						+ "			<div id='content-wrapper'>"
						+ "				<div id='content' style='width:98%;'>");
						
						
						
						
						
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
						//+ "							<p></p>"//for displaying text
						+ "							<p><a href='" + qw.getForumURL() + "'></a></p>");// for displaying URL);
						
						
						
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
									out.println("<img src='Video/" +  fileName + "' width='200' height='200' />");
								}
								else if(ext.equalsIgnoreCase(".mp4") || ext.equalsIgnoreCase(".webm") || ext.equalsIgnoreCase(".ogg")) {
									out.println("<video src='Video/" +  fileName + "' autoplay loop controls width='200' height='200'/>");
									out.println("</div></div>");
								}
								
								else {
								  out.println(
									 "<div class=\"dropdown\">" + 
									 "  <button class=\"btn btn-secondary dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" + 
									 fileName + 
									 "  </button>" + 
									 "  <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">" + 
									 "    <a class=\"dropdown-item\" onclick='showPreview()'>Preview</a>" + 
									 "    <form action='discussion' method='POST' id='goto'><a class=\"dropdown-item\" onclick='showDownload()'>Download</a><input type='hidden' name='sdsd' value='" + fileName + "' /></form>" + 
									 "  </div>" + 
									 "</div>"
								   + "<iframe id='thiss' style='width:50%; height:50%; display:none; 'src='https://docs.google.com/gview?url=http://119.74.135.44:8080/PotHub/Video/" + fileName + " &embedded=true'></iframe>"
										  );
									/*File tempFile = File.createTempFile(fileName, ".tmp", null);
									FileOutputStream fos = new FileOutputStream(tempFile);
									fos.write(d.getFileData());
									out.println("");*/
								}
							
							
							}
							
						}
						else {
							out.println("<p>Nothing to display</p>");
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
							
						out.println( "</div>"
									+ "				</div>");
						
						
						try {
							Database d = new Database(2);
							System.out.println(username + ddd);
							if(d.getWhetherCanCommentCommentModel(username, ddd)) {
								out.println(
										 
										  "					<div id='firstly'>"
										+ "						<form action='discussion' method='POST'>"	
										+ "						<textarea class='form-control' id='exampleFormControlTextarea1' rows='3' name='rtor'></textarea>"
										+ "						<input type='hidden' name='myign' value='" + username + "'>"
										+ "						<input type='hidden' name='jesus' value='" + ddd  + "'>"
										+ "						<input type='submit' value='Post/Submit' id='postBtn' cursor:pointer;' class='btn'>"
										+ "						</form>"
										+ "					</div>");
							}
							else {
								System.out.println("cannot comment alr");
							}
							
							
						}
						catch (SQLException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						out.println( "<div id='comments'>");

						
						
						
						
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
				/*		+ "								<div class='reaction'>"
						+ "									<div id='upvote'><i class='fa fa-arrow-up fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='count' style='align-items: center;'>1029</div>"
						+ "									<div id='downvote'><i class='fa fa-arrow-down fa-2x' aria-hidden='true'></i></div>"
						+ "									<div id='reports'><a href='#'>Report</a></div>"
						+ "								</div>"*/
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
											
						 
						  "			</div>"
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
		String download = request.getParameter("sdsd");//filename
		if(download == null || download.isEmpty()) {
			
		String haha = request.getParameter("rtor");
		String myign = request.getParameter("myign");
		int id = Integer.parseInt(request.getParameter("jesus"));
		java.sql.Date date1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		try {
			Database ttttt = new Database(2);
			CommentModel cm = new CommentModel();
			cm.setPostID(id);
			cm.setDate(date1);
			cm.setDescription(haha);
			cm.setiGN(myign);
			ttttt.addComment(cm);
			out.println(
					"<!DOCTYPE html>"
							+ "<html>"
							+ "<p>Success commenting</p>"
							+ "</html>"
						);			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}//end the if statement
		
		//download the file
		else {
			try {
			Database df = new Database(2);
			FileTableModel ftm = new FileTableModel();
			ftm = df.getFileTableByFileName(download);
			File tempFile = File.createTempFile(download, ".tmp", null);
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(ftm.getData());
			response.setContentType("text/html");
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + ftm.getFileName() +"\"");
			FileInputStream stream = new FileInputStream(tempFile);
			int i;   
			while ((i=stream.read()) != -1) {  
			out.write(i);   
			}   
			stream.close(); 
			}
			catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	

}
