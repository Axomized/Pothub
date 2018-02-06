package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;

import database.Database;
import database.model.DatabaseUserModel;
import database.model.FileTableModel;
import database.model.ForumPostModel;
import database.model.LogsModel;
import login.BanChecker;


@MultipartConfig(fileSizeThreshold=8024*1024*2, maxFileSize=8024*1024*10, maxRequestSize=8024*1024*50)
public class createNewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public createNewPost() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		PrintWriter out = response.getWriter();

		out.println(
				"<!DOCTYPE html>"
						+ "<html>"
						+ "	<head>"
						+ "		<meta charset='UTF-8'>"
						+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
						+ "		<!-- Favicon -->"
						+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>"
						+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>"
						+ "		<!-- Page Title -->"
						+ "		<title>Creating new post</title>"
						+ "		<!-- Latest compiled and CSS -->"
						+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
						+ "		<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
						+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
						+ "		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
						+ "		<script src='https://www.google.com/recaptcha/api.js'></script>"
						+ "		<!-- Optional theme -->"
						+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
						+ "		<!-- My Own Script -->"
						+ "		<script src='script/createNewPost.js'></script>"
						+ "		<!-- My Style Sheet -->"
						+ "		<link rel='stylesheet' type='text/css' href='css/newpost.css' />"
						+ "	</head>"
						+ "	<body>"
						+ "		<!--  Navigation Bar -->"
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
		out.print(
				"				<li class='dropdown'>"
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
						+ "				<div id='content'>"
						+ "					<form enctype='multipart/form-data' method='post'>"
						+ "					  <div class='form-group'>"
						+ "					    <label for='exampleFormControlInput1'>Forum Title *</label>"
						+ "					    <input type='text' class='form-control' id='exampleFormControlInput1' name='Forumtitle'>"
						+ "					  </div>"
						+ "					  <div class='form-group'>"
						+ "					    <label for='exampleFormControlTextarea1'>Enter Your Forum Description *</label>"
						+ "					    <textarea class='form-control' id='exampleFormControlTextarea1' rows='3' name='Forumdescription'></textarea>"
						+ "					  </div>"
						+ "					  <div class='form-group'>"
						+ "						<div class='dropdown'>" 
						+ "  						<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" 
						+ "    							Select Upload Type" 
						+ "  						</button>" 
						+ "  						<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>" 
						//+ "    							<a onclick='showT()' class='dropdown-item' href='#'>Text</a>" 
						+ "    							<a onclick='showV()' class='dropdown-item' href='#'>Video</a>" 
						+ "    							<a onclick='showP()' class='dropdown-item' href='#'>Picture</a>" 
						+ "    							<a onclick='showU()' class='dropdown-item' href='#'>Link</a>" 
						+ "    							<a onclick='showF()' class='dropdown-item' href='#'>File/Document</a>" 
						+ "  						</div>" 
						+ "						</div>"


				+"					  <div class='form-group'>"	
				/*+"					    <p id='text1' style='display:none;'>Enter your text here: <textarea class='form-control' id='exampleFormControlTextarea1' rows='3' name='words'></textarea>"
				+"						<i onclick='closeT()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"*/
				+"					  	<p id='video1' style='display:none; margin-top: 5px;'>Submit your video here: <input type='file' name='vid' accept='.mp4, .webm, .ogg'>"
				+"						<i onclick='closeV()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  	<p id='image1' style='display:none; margin-bottom: 5px; margin-top: 5px;'>Submit your picture here: <input type='file' name='pic123' accept='.png, .jpeg, .jpg, .gif''>"
				+"						<i onclick='closeI()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  	<p id='link1' style='display:none;' >Enter the url here: <input type='url' name='link'>"
				+"						<i onclick='closeU()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  	<p id='file1' style='display:none; margin-top: 5px;'>Submit your file here: <input type='file' name='file69' accept='.txt, .docx'>"
				+"						<i onclick='closeF()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  </div>"	

				+ "					  </div>"
				+ "					  <div class='form-group'>"
				+ "					  <div id='attachingfile'>"
				+ "					  	<p>Set thumbnail picture *</p>"
				+ "					  	 <input type='file' name='iconpic' id='needit' accept='.png, .jpeg, .jpg, .gif'>"
				+ "					  </div>"
				+ "					  <div>"
				+  "				  <div class='g-recaptcha' data-callback='recaptchaCallback' data-sitekey='6LdFc0QUAAAAADUloNx3_5EV-l82I7s-fcEW1Arm'></div>"//for localhost: 6LdoEUIUAAAAAFPLD3IhU98g25qAWdFezJEnOD0f //for azure: 6LdFc0QUAAAAADUloNx3_5EV-l82I7s-fcEW1Arm
				+ "					  <input type='submit' id='postBtn' onclick='return submitting()' value='Post/Submit' style='cursor:pointer;' class='btn' disabled>"
				+ "					  <input type='button' id='cancelBtn' formaction='Forum' value='Cancel' style='cursor:pointer;' class='btn'>"
				+ "					  </div>"
				+ "					  </div>"
				+ "					</form>"
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
				+ "	</body>"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username2 = "";
		HttpSession session = request.getSession(false);
		if (session != null) {
			username2 = (String)session.getAttribute("username");
		}
		else {
			response.sendRedirect("Login");
		}
		// get reCAPTCHA request param
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println(gRecaptchaResponse);
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
		System.out.println("Captcha Verify:" + verify);//checking the captcha
		String haha = "";
		PrintWriter out = response.getWriter();
		String forumT = request.getParameter("Forumtitle");
		String forumD = request.getParameter("Forumdescription");
		//int picc = Integer.parseInt(request.getParameter("pic"));
		String url = request.getParameter("link"); //Set if empty put null ohh
		//String words = request.getParameter("words"); //Set if empty put null
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			Database db = new Database(2);
			LogsModel lm = new LogsModel();
			int latest = db.getFileCount();
			int checks = 1;
			int checking = db.getFileCount();
			ForumPostModel fp = new ForumPostModel();
			fp.setForumURL(url);
			fp.setDate(timestamp);
			fp.setDescription(forumD);
			fp.setFileAttachment(null); //set as id of file
			fp.setiGN(username2);
			fp.setThread(forumT);
			fp.setUpvotes(0);
			fp.setForumNormalText(null);
			/*if(words.isEmpty() || words.equals("")) {
				fp.setForumNormalText(null);
			}
			else {
			fp.setForumNormalText(words);
			}
			 */


			//for picture upload
			Part picc = request.getPart("pic123");
			if(picc.getSize() > 0) {
				String fileName = Paths.get(picc.getSubmittedFileName()).getFileName().toString();
				latest++;
				haha = haha + latest + ";";
				byte[] thumbnailBytes = IOUtils.toByteArray(picc.getInputStream());
				FileTableModel teo = new FileTableModel(0, fileName, thumbnailBytes);
				db.insertFileTable(teo);
			}

			//for video upload
			Part vidd = request.getPart("vid");
			if(vidd.getSize() > 0) {
				String fileName1 = Paths.get(vidd.getSubmittedFileName()).getFileName().toString();
				latest++;
				haha = haha + latest + ";";
				byte[] thumbnailBytes1 = IOUtils.toByteArray(vidd.getInputStream());
				FileTableModel teo1 = new FileTableModel(0, fileName1, thumbnailBytes1);
				db.insertFileTable(teo1);
			}

			//for file upload
			Part filee = request.getPart("file69");
			if(filee.getSize() > 0) {
				String fileName2 = Paths.get(filee.getSubmittedFileName()).getFileName().toString();
				latest++;
				haha = haha + latest + ";";
				byte[] thumbnailBytes2 = IOUtils.toByteArray(filee.getInputStream());
				FileTableModel teo2 = new FileTableModel(0, fileName2, thumbnailBytes2);
				db.insertFileTable(teo2);
			}

			//for icon picture upload
			Part images = request.getPart("iconpic");
			if(images.getSize() > 0) {
				checks ++;
				String fileName3 = Paths.get(images.getSubmittedFileName()).getFileName().toString();
				latest++;
				byte[] thumbnailBytes3 = IOUtils.toByteArray(images.getInputStream());
				FileTableModel teo3 = new FileTableModel(0, fileName3, thumbnailBytes3);
				db.insertFileTable(teo3);
			}

			fp.setFileAttachment(haha);
			if(latest == checking || checks == 1) {
				fp.setPicture(1);
			}
			else {
				fp.setPicture(latest);
			}
			db.addForumPost(fp);
			lm.setiGN(username2);
			lm.setLogDate(Timestamp.from(Instant.now()));
			lm.setiPAddress(lm.getClientIP(request));
			lm.setLogType("Forum");
			lm.setLogActivity(username2 + " created a thread - " + "\"" + forumT + "\"");
			db.insertLogs(lm);
			response.sendRedirect("Forum");


		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Please xz...");
			e.printStackTrace();
		}
	}
}
