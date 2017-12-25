package event;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;

import database.Database;

@MultipartConfig
public class CreateEventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateEventPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Database db = new Database(0);
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>"
					+ "<html>"
					+ "	<head>"
					+ "		<meta charset='UTF-8'>"
					+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
					+ "		<!-- Favicon -->"
					+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>"
					+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>"
					+ "		<link rel='icon' href='http://localhost:8080/PotHub/images/crab.png?v=2' type='image/x-icon'>"
					+ "		<!-- Page Title -->"
					+ "		<title>Default Title</title>"
					+ "		<!-- Latest compiled and CSS -->"
					+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
					+ "		<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.standalone.css' integrity='sha256-P1wC4IE9L+kzf2qwueaK/jdj186d/Q05Q8ITF9vr9Ok=' crossorigin='anonymous' />"
					+ "		<!-- Optional theme -->"
					+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
					+ "		<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>"
					+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js' integrity='sha256-7Ls/OujunW6k7kudzvNDAt82EKc/TPTfyKxIE5YkBzg=' crossorigin='anonymous'></script>"
					+ "		<!-- My Own Script -->"
					+ "		<script src='script/CreateEventPage.js'></script>"
					+ "		<!-- My Style Sheet -->"
					+ "		<link rel='stylesheet' type='text/css' href='css/CreateEventPage.css' />"
					+ "	</head>"
					+ "	<body>"
					+ "		<!--  Navigation Bar -->"
					+ "		<div id='header'>"
					+ "			<div id='companyTitle'>"
					+ "				<h1>PotHub</h1>"
					+ "			</div>"
					+ "			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>"
					+ "				<div id='profilePic'>"
					+ "					<img src='images/profile.png' height='50' width='50'/>"
					+ "					<span id='welcomeSpan'>Welcome, [Placeholder]</span>"
					+ "				</div>"
					+ "				<div id='profileDropdownDiv'>"
					+ "					<a href='Profile.html'>Profile</a>"
					+ "					<a href='LoginPage.html'>Logout</a>"
					+ "				</div>"
					+ "			</div>"
					+ "		</div>"
					+ "		<div id='navigation'>"
					+ "			<ul>"
					+ "				<li id='lhome'><a href='Forum.html'>Home</a></li>"
					+ "				<li id='lprivatemessage'><a href='#01'>Private Message</a></li>"
					+ "				<li class='dropdown'>"
					+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>"
					+ "			        <ul class='dropdown-menu'>"
					+ "			          <li><a href='/EventPage'>Events</a></li>"
					+ "			          <li><a href='/MyEventPage'>My Events</a></li>"
					+ "			        </ul>"
					+ "			    </li>"
					+ "				<li class='dropdown'>"
					+ "			        <a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>"
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
					+ "		<div id='wrapper'>"
					+ "			<form method='post' enctype='multipart/form-data'>"
					+ "				<div id='thumbnail-container' class='form-group'>"
					+ "					<p><b>Thumbnail</b></p>"
					+ "					<div id='thumbnail-container-container'>"
					+ "						<img src='images/crab.jpg' alt='crab' height='100px' width='200px' id='chosenThumbnail'>"
					+ "						<div id='thumbnail-container-buttons'>"
					+ "							<input type='button' class='btn' value='Select' onclick='showDefault()'> or"
					+ "							<input type='file' class='form-control-file' value='Upload' accept='image/*' id='fileUpload' name='thumbnailUpload'>"
					+ "							<input type='hidden' id='fileNum' name='DefaultNumber' value='1'>"
					+ "						</div>"
					+ "					</div>"
					+ "					<div id='default-thumbnail'>"
					+ "						<img src='images/crab.jpg' alt='crab' height='50px' width='100px' onclick='changeThumbnail(this, 1)'>"
					+ "						<img src='images/foodss.jpg' alt='crab' height='50px' width='100px' onclick='changeThumbnail(this, 2)'>"
					+ "						<img src='images/bii.jpg' alt='crab' height='50px' width='100px' onclick='changeThumbnail(this, 3)'>"
					+ "					</div>"
					+ "				</div>"
					+ "				<div id='eventname-container' class='row form-group'>"
					+ "					<div id='eventname' class='col form-group'>"
					+ "						<p><b>Event Name</b></p>"
					+ "						<input type='text' class='form-control' name='EventName' required>"
					+ "					</div>"
					+ "					<div id='maxamtofppt' class='col form-group'>"
					+ "						<p><b>Max Participants</b></p>"
					+ "						<input type='number' class='form-control' name='MaxAmtPpt' required>"
					+ "					</div>"
					+ "					<div id='autoaccept' class='col form-group'>"
					+ "						<p><b>Auto Accept Request</b></p>"
					+ "						<input type='checkbox' class='form-check-input' name='AutoAccept'>"
					+ "					</div>"
					+ "					<div id='postalcode' class='col form-group'>"
					+ "						<p><b>Postal Code</b></p>"
					+ "						<input type='text' id='postalInput' class='form-control' name='PostalCode' required>"
					+ "					</div>"
					+ "					<div id='eventaddress' class='col form-group'>"
					+ "						<p><b>Address</b></p>"
					+ "						<input type='text' class='form-control' id='mainAddress' name='Address'><br>"
					+ "						<input type='text' class='form-control' name='AdditionalAddress'>"
					+ "					</div>"
					+ "				</div>"
					+ "				<div id='description-container' class='form-group row'>"
					+ "					<p><b>Description</b></p>"
					+ "					<textarea id='description' class='form-control' placeholder='Type here...' name='EventDesc'></textarea>"
					+ "				</div>"
					+ "				<div id='guest-container' class='form-group'>"
					+ "					<p><b>Guest</b></p>"
					+ "					<div>"
					+ "						<div id='guest-container-left-container'>"
					+ "							<input type='text' id='textguestname' list='guestnamelist' autocomplete='off' class='form-control'>"
					+ "							<input type='hidden' id='guestNameList' name='GuestName'>"
					+ "							<datalist id='guestnamelist'>");
			
			for(String s:db.getDatabaseUserIGN()) {
				out.println("<option>" + s + "</option>");
			}
			
			out.println("						</datalist>"
					+ "						</div>"
					+ "						<div id='guest-container-right-container'>"
					+ "						</div>"
					+ "					</div>"
					+ "				</div>"
					+ "				<div id='date-container' class='form-group'>"
					+ "					<p><b>Date/Time</b></p>"
					+ "					<div id='datetime-container'>"
					+ "						<div class='input-group date'>"
					+ "							<input type='text' name='EventDate' class='form-control' data-provide='datepicker' data-date-format='dd/mm/yyyy' data-date-autoclose='true' data-date-start-date='today' data-date-max-view-mode='2' data-date-clear-Btn='true'>"
					+ "							<span class='input-group-addon'>"
					+ "								<i class='fa fa-calendar' aria-hidden='true'></i>"
					+ "							</span>"
					+ "						</div>"
					+ "						<input type='time' name='EventTime' class='form-control' id='timeinput'>"
					+ "					</div>"
					+ "				</div>"
					+ "				<div id='gallery-container'>"
					+ "					<p><b>Gallery</b></p>"
					+ "					<div class='btn btn-primary' id='gallery-add'>"
					+ "					    <span>Add</span>"
					+ "					    <input type='file' id='upload' multiple='true' name='EventGallery'/>"
					+ "					</div>"
					+ "					<div id='gallery' class='row'>"
					+ "					</div>"
					+ "				</div>"
					+ "				<div id='button-container'>"
					+ "					<input type='button' class='btn' value='Preview'>"
					+ "					<input type='submit' value='Submit' class='btn btn-success'>"
					+ "				</div>"
					+ "			</form>"
					+ "		</div>"
					+ "		<div id='footer'>"
					+ "			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>"
					+ "			<p>We like food</p>"
					+ "			<p>"
					+ "				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
					+ "			</p>"
					+ "		</div>"
					+ "		<!-- Optional Scripts for Bootstrap -->"
					+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
					+ "		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
					+ "	</body>"
					+ "</html>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("DefaultNumber").isEmpty()) {
			Part filePart = request.getPart("thumbnailUpload");
			byte[] thumbnailBytes = IOUtils.toByteArray(filePart.getInputStream());
		}else {
			int thumbnailNumber = Integer.parseInt(encodeString(request.getParameter("DefaultNumber")));
		}
		
		String eventName = encodeString(request.getParameter("EventName"));
		String amtOfPeople = encodeString(request.getParameter("MaxAmtPpt"));
		String autoAccept = encodeString(request.getParameter("AutoAccept"));
		String postalCode = encodeString(request.getParameter("PostalCode"));
		String mainAddress = encodeString(request.getParameter("Address"));
		String secondaryAddress = encodeString(request.getParameter("AdditionalAddress"));
		String eventDesc = encodeString(request.getParameter("EventDesc"));
		String guestName = encodeString(request.getParameter("GuestName"));
		String eventDate = encodeString(request.getParameter("EventDate"));
		String eventTime = encodeString(request.getParameter("EventTime"));
		
		List<Part> fileParts = request.getParts().stream().filter(part -> "EventGallery".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
	    for (Part p : fileParts) {
	        byte[] gallaryFileBytes = IOUtils.toByteArray(p.getInputStream());
	    }
	}

	private String encodeString(String line) throws UnsupportedEncodingException {
		return URLEncoder.encode(line, "UTF-8");
	}
}
