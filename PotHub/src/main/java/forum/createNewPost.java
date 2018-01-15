package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.ForumPostModel;

/**
 * Servlet implementation class createNewPost
 */
public class createNewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createNewPost() {
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
				+ "		<!-- Optional theme -->"
				+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
				+ "		<!-- My Own Script -->"
				+ "		<script src='script/createNewPost.js'></script>"
				+ "		<!-- My Style Sheet -->"
				+ "		<link rel='stylesheet' type='text/css' href='css/newpost.css' />"
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
				+ "				<div id='content'>"
				+ "					<form action='SuccessPage' method='post'>"
				+ "					  <div class='form-group'>"
				+ "					    <label for='exampleFormControlInput1'>Forum Title</label>"
				+ "					    <input type='text' class='form-control' id='exampleFormControlInput1' name='Forumtitle'>"
				+ "					  </div>"
				+ "					  <div class='form-group'>"
				+ "					    <label for='exampleFormControlTextarea1'>Enter Your Forum Description</label>"
				+ "					    <textarea class='form-control' id='exampleFormControlTextarea1' rows='3' name='Forumdescription'></textarea>"
				+ "					  </div>"
				+ "					  <div class='form-group'>"
				
				
				+ "						<div class='dropdown'>" 
				+ "  						<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" 
				+ "    							Dropdown button" 
				+ "  						</button>" 
				+ "  						<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>" 
				+ "    							<a onclick='showT()' class='dropdown-item' href='#'>Text</a>" 
				+ "    							<a onclick='showV()' class='dropdown-item' href='#'>Video</a>" 
				+ "    							<a onclick='showP()' class='dropdown-item' href='#'>Picture</a>" 
				+ "    							<a onclick='showU()' class='dropdown-item' href='#'>Link</a>" 
				+ "    							<a onclick='showF()' class='dropdown-item' href='#'>File/Document</a>" 
				+ "  						</div>" 
				+ "						</div>"
				
				
				+"					  <div class='form-group'>"	
				+"					    <p id='text1' style='display:none;'>Enter your text here: <textarea class='form-control' id='exampleFormControlTextarea1' rows='3' name='words'></textarea>"
				+"						<i onclick='closeT()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  	<p id='video1' style='display:none;'>Submit your picture here: <input type='file' name='vid' accept='image/*'>"
				+"						<i onclick='closeV()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  	<p id='image1' style='display:none;'>Submit your video here: <input type='file' name='pic' accept='image/*'>"
				+"						<i onclick='closeI()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  	<p id='link1' style='display:none;' >Enter the url here: <input type='url' name='link'>"
				+"						<i onclick='closeU()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  	<p id='file1' style='display:none;'>Submit your file here: <input type='file' name='file' accept='image/*'>"
				+"						<i onclick='closeF()' class='fa fa-close' style='font-size:18px'></i>"	
				+"						</p>"
				+"					  </div>"	
				
				+ "					  </div>"
				+ "					  <div class='form-group'>"
				+ "					  <div id='attachingfile'>"
				+ "					  	<p>Set icon image for post</p>"
				+ "					  	 <input type='file' name='pic' accept='image/*'>"
				+ "					  </div>"
				+ "					  <div>"
				+ "					  <button type='submit' id='postBtn' onclick='submitting()' cursor:pointer;' class='btn'>Post/Submit</button>"
				+ "					  <button id='cancelBtn' formaction='Forum' cursor:pointer;' class='btn'>Cancel</button>"
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
		

				
		
	}

}
