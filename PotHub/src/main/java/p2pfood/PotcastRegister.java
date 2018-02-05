package p2pfood;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;
import adminSearch.SearchSanitizer;
import database.Database;
import database.model.LogsModel;
import database.model.PotcastModel;
import login.BanChecker;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/p2preg")
@MultipartConfig
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
		HttpSession session = request.getSession(false);
		String username = "";

		if (session != null) {
			username = (String) session.getAttribute("username");
		} else {
			response.sendRedirect("Login");
			return;
		}
		
		if(BanChecker.isThisGuyBanned(username)){
			response.sendRedirect("Login");
			return;
		}

		try {
			Database db = new Database(0);
			PrintWriter pw = response.getWriter();
			pw.append("<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset='ISO-8859-1'>" + "<meta name='viewport'"
					+ "	content='width=device-width, initial-scale=1, shrink-to-fit=no'>" + "<!-- Favicon -->"
					+ "<link rel='icon' href='images/crab.gif' type='image/gif'>"
					+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>" + "<!-- Page Title -->"
					+ "<title>Start a Potcast</title>" + "<!-- Latest compiled and CSS -->"
					+ " <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'>"
					+ "	<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js'></script>"
					+ "	<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
					+ "	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js'></script>"
					+ "<!-- Optional theme -->" + "<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "<!-- My Own Script -->" + "<script src='script/p2preg.js'></script>" + "<!-- My Style Sheet -->"
					+ "<link rel='stylesheet' type='text/css' href='css/p2preg.css' />" + "</head>" + "<body>"
					+ "	<!--  Navigation Bar -->" + "		<div id='header'>" + "<div id='companyTitle'>"
					+ "<h1>PotHub</h1>" + "</div>"
					+ "<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>"
					+ "<div id='profilePic'>" + "<img src='images/profile.png' height='50' width='50'/>"
					+ "<span id='welcomeSpan'>Welcome, " + username + " </span>" + "</div>"
					+ "<div id='profileDropdownDiv'>");

			pw.append("<a href='Logout'>Logout</a>");

			pw.append("</div>" + "</div>" + "</div>" + "	<div id='navigation'>"
					+ "		<div class='container-fluid'>" 
					+ "<ul class='nav navbar-nav'>"
					+ "				<li id='lhome'>"
					+ "				<a href='Forum'>Home</a></li>"
					+ "				<li class='dropdown'>"
					+ "			        	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>"
					+ "			        	<ul class='dropdown-menu'>"
					+ "				        	<li><a href='/PotHub/EventPage'>Events</a></li>"
					+ "				        	<li><a href='/PotHub/MyEventPage'>My Events</a></li>"
					+ "			        	</ul>"
					+ "			    	</li>"
					+ "				<li class='dropdown'>"
					+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>"
					+ "			        <ul class='dropdown-menu'>"
					+ "			          <li><a href='p2plist'>Active Potcasts</a></li>"
					+ "			          <li><a href='p2preg'>Start a Potcast</a></li>"
					+ "			          <li><a href='p2pmy'>My Potcast</a></li>"
					+ "			          <li><a href='p2pjoined'>Joined Potcast</a></li>" + "			        </ul>"
					+ "			      </li>"
					+ "				<li id='ldonate'><a href='Donation'>Donate</a></li>" 
					+ "			</ul>" 	
					+ "		</div>" + "	</div>");

			if(db.getNumberOfPotcastsFrom(username)<1&&db.getUserPriviledge(username)){
				pw.append("<div id='wrapper'>" + "<div id='secondHeader'>" + "<h2>Start a Potcast</h2>" + "</div>"
						+ "<form method='post' method='post' enctype='multipart/form-data'>" + "<div id='form'>"
						+ "<div class='formElement'>" + "<p>Food title</p>"
						+ "<input type='text' class='long' name='title' required></input>" + "</div>"
						+ "<div class='formElement'>" + "<p>Description</p>"
						+ "<textarea name='description' required maxlength='255'></textarea>" + "</div>"

						+ "<div class='formElement'>" + "<p>Portions available</p>"
						+ "<input type='number' name='portions' required></input>" + "<p>Starting Price Per Portion</p>"
						+ "<input type='number' name ='ppp' required></input>" + "</div>"

						+ "<div class='formElement'>" + "<p>Bid closing time</p>"
						+ "<input type='datetime-local' name='bidStopTime' required value='"+TimestampConverter.longToDateTimeString(System.currentTimeMillis())+"'></input>" + "<p>Collection time</p>"
						+ "<input type='datetime-local' name='pickupTime' required value='"+TimestampConverter.longToDateTimeString(System.currentTimeMillis())+"'></input>" + "</div>"

						+ "<div class='formElement'>" + "<p>Picture</p>"
						+ "<input type='file' name='picture' accept='image/*' required></input>" + "</div>"

						+ "<div class='formElement'>" + "<button>Submit</button>" + "</div>" + "</div>" + "</form>"
						+ "</div>");

			} else if(db.getUserPriviledge(username)){
				pw.append("<div id='wrapper'>" + "<div id='rejectMessage'>"
						+ "<h2>You have the maximum number of active Potcasts!</h2>"
						+ "<p>Have fun serving your visitors first!</p>"
						+ "</div>" + "</div>");
			}
			else{
				pw.append("<div id='wrapper'>" + "<div id='secondHeader'>"
						+ "<h2>Only established users may start potcasts.</h2>"
						+ "</div>" + "</div>");
			}
			pw.append("<div id='footer'>" + "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
					+ "<p>We like food</p>" + "<p>"
					+ "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>" + "</p>"
					+ "</div>" + "</body>" + "</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("Login");
			return;
		}
		Database db;
		try {
			db = new Database(2);

			if(db.getNumberOfPotcastsFrom((String)session.getAttribute("username"))>0){
				response.sendRedirect("p2plist");
				return;
			}
			// Defaults
			PotcastModel pcm = new PotcastModel();
			LogsModel lm = new LogsModel();
			if (request.getParameter("title") != null && request.getParameter("description") != null
					&& request.getParameter("portions") != null && request.getParameter("ppp") != null
					&& request.getParameter("bidStopTime") != null && request.getParameter("pickupTime") != null
					&& SearchSanitizer.sanitise(request.getParameter("title")).length()>0) {
				// Timestamps
				Timestamp ts1 = TimestampConverter.stringToTimestamp(request.getParameter("bidStopTime"));
				Timestamp ts2 = TimestampConverter.stringToTimestamp(request.getParameter("pickupTime"));
				
				if(ts1.getTime()<ts2.getTime()
						&&ts1.getTime()>System.currentTimeMillis()
						&&ts2.getTime()>System.currentTimeMillis()
						&&Integer.parseInt(request.getParameter("portions"))>0
						&&Integer.parseInt(request.getParameter("portions"))<100
						&&Integer.parseInt(request.getParameter("ppp"))<1000
						&&Integer.parseInt(request.getParameter("ppp"))>0){

				pcm.setiGN((String) session.getAttribute("username"));
				pcm.setTitle(SearchSanitizer.sanitise(request.getParameter("title")));
				pcm.setDescription(request.getParameter("description"));
				pcm.setMaxBids(Integer.parseInt(request.getParameter("portions")));
				pcm.setMinBid(Integer.parseInt(request.getParameter("ppp")));

				pcm.setStartingCR(db.getCookingRankFrom((String) session.getAttribute("username")));

					pcm.setBidStopTime(ts1);
					pcm.setPickupTime(ts2);

				// Image upload
				Part filePart = request.getPart("picture");

				String fileName = encodeString(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
				byte[] thumbnailBytes = IOUtils.toByteArray(filePart.getInputStream());

				pcm.setPicture(db.addPictureWithDupeCheck(fileName, thumbnailBytes));

				// TODO: Add sanitizer for description
				db.addPotcast(pcm);
				
				lm.setiGN((String)session.getAttribute("username"));
				lm.setLogDate(Timestamp.from(Instant.now()));
				lm.setiPAddress(lm.getClientIP(request));
				lm.setLogType("Potcast");
				lm.setLogActivity((String)session.getAttribute("username") + " created potcast - " + "\"" + SearchSanitizer.sanitise(request.getParameter("title")) + "\"");
				db.insertLogs(lm);
				
				PrintWriter out = response.getWriter();
		    	out.println("<script type=\"text/javascript\">");
				out.println("alert('Success!');");
				out.println("</script>");
				}			
				else{
					PrintWriter out = response.getWriter();
			    	out.println("<script type=\"text/javascript\">");
					out.println("alert('Invalid input, Please try again.');");
					out.println("</script>");
				}
				}
			else{
				PrintWriter out = response.getWriter();
		    	out.println("<script type=\"text/javascript\">");
				out.println("alert('Invalid input. Please try again.');");
				out.println("</script>");
			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | ParseException e1) {
			PrintWriter out = response.getWriter();
	    	out.println("<script type=\"text/javascript\">");
			out.println("alert('Invalid input! Please try again.');");
			out.println("</script>");
		}
		doGet(request, response);
	}
	
	private String encodeString(String line) throws UnsupportedEncodingException {
		return URLEncoder.encode(line, "UTF-8");
	}
}
