package event;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;

import database.Database;
import database.model.EventModel;

@MultipartConfig(fileSizeThreshold=(1024*1024*10), maxFileSize=(1024*1024*25), maxRequestSize=1024*1024*5*5*4)
public class CreateEventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private byte[] header;
    private byte[] footer;
    
    public void init(ServletConfig config) throws ServletException{
    	StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("	<head>");
		sb.append("		<meta charset='UTF-8'>");
		sb.append("		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
		sb.append("		<!-- Favicon -->");
		sb.append("		<link rel='icon' href='images/crab.gif' type='image/gif'>");
		sb.append("		<link rel='icon' href='images/crab.png?v=2' type='image/x-icon'>");
		sb.append("		<!-- Page Title -->");
		sb.append("		<title>Create Event Page</title>");
		sb.append("		<!-- Latest compiled and CSS -->");
		sb.append("		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>");
		sb.append("		<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.standalone.css' integrity='sha256-P1wC4IE9L+kzf2qwueaK/jdj186d/Q05Q8ITF9vr9Ok=' crossorigin='anonymous' />");
		sb.append("		<!-- My Own Script -->");
		sb.append("		<script src='script/CreateEventPage.min.js' defer></script>");
		sb.append("		<!-- My Style Sheet -->");
		sb.append("		<link rel='stylesheet' type='text/css' href='css/CreateEventPage.css' />");
		sb.append("	</head>");
		sb.append("	<body>");
		sb.append("		<!--  Navigation Bar -->");
		sb.append("		<div id='header'>");
		sb.append("			<div id='companyTitle'>");
		sb.append("				<p>PotHub</p>");
		sb.append("			</div>");
		sb.append("			<div id='profilePicWrapDiv' onmouseover='showProfileDropdown()' onmouseout='hideProfileDropdown()'>");
		sb.append("				<div id='profilePic'>");
		
		header = sb.toString().getBytes();
		sb.delete(0, sb.length());
		
		sb.append("							</datalist>");
		sb.append("						</div>");
		sb.append("						<div id='guest-container-right-container'>");
		sb.append("						</div>");
		sb.append("					</div>");
		sb.append("				</div>");
		sb.append("				<div id='date-container' class='form-group color-container'>");
		sb.append("					<p><b>Date/Time</b><span class='requiredInput'>*</span></p>");
		sb.append("					<div id='datetime-container'>");
		sb.append("						<div class='input-group date'>");
		sb.append("							<input type='text' id='eventDate' name='EventDate' class='form-control' autocomplete='off' data-provide='datepicker' data-date-format='dd/mm/yyyy' data-date-autoclose='true' data-date-start-date='today' data-date-max-view-mode='2' data-date-clear-Btn='true'>");
		sb.append("							<span class='input-group-addon'>");
		sb.append("								<i class='fa fa-calendar' aria-hidden='true'></i>");
		sb.append("							</span>");
		sb.append("						</div>");
		sb.append("						<input type='time' name='EventTime' class='form-control' id='timeinput'>");
		sb.append("					</div>");
		sb.append("				</div>");
		sb.append("				<div id='gallery-container' class='color-container'>");
		sb.append("					<p><b>Gallery</b></p>");
		sb.append("					<div class='btn btn-primary' id='gallery-add'>");
		sb.append("					    <span>Add</span>");
		sb.append("					    <input type='file' id='upload' accept='image/*' name='EventGallery' multiple>");
		sb.append("					</div>");
		sb.append("					<div id='gallery' class='row'>");
		sb.append("					</div>");
		sb.append("				</div>");
		sb.append("				<div id='button-container'>");
		sb.append("					<input type='button' class='btn' value='Preview' onclick='showPreview()'>");
		sb.append("					<input type='submit' value='Submit' class='btn btn-success'>");
		sb.append("				</div>");
		sb.append("			</form>");
		sb.append("			<div id='popup-container'>");
		sb.append("				<i class='fa fa-window-close fa-3x' aria-hidden='true' id='closeBtn'></i>");
		sb.append(				"<div id='closeBtnDiv'></div>");
		sb.append("				<iframe src='html/EventofEventPage.html' id='iframeEvent'></iframe>");
		sb.append("			</div>");
		sb.append("		</div>");
		sb.append("		<div id='footer'>");
		sb.append("			<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>");
		sb.append("			<p>We like food</p>");
		sb.append("			<p>");
		sb.append("				<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>");
		sb.append("			</p>");
		sb.append("		</div>");
		sb.append("		<!-- Optional Scripts for Bootstrap -->");
		sb.append("		<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>");
		sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>");
		sb.append("		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>");
		sb.append("		<!-- Optional theme -->");
		sb.append("		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>");
		sb.append("		<script src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js' integrity='sha256-7Ls/OujunW6k7kudzvNDAt82EKc/TPTfyKxIE5YkBzg=' crossorigin='anonymous'></script>");
		sb.append("	</body>");
		sb.append("</html>");
		footer = sb.toString().getBytes();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        HttpSession session = request.getSession(false);
	        String username = "";
	        if (session != null) {
	        	username = (String)session.getAttribute("username");
	        }
	        else {
	            response.sendRedirect("Login");
	        }
	        
			Database db = new Database(0);
			response.setContentType("text/html");
			ServletOutputStream out = response.getOutputStream();
			out.write(header);
			StringBuffer sb = new StringBuffer();
			String currentProfilePic = db.getUserProfilePic(username);
			if(currentProfilePic != null) {
				sb.append("				<img src='Image/" + currentProfilePic + "' alt='ProfilePicture' height='50' width='50'/>");
			}else {
				sb.append("					<img src='images/profile.png' alt='ProfilePicture' height='50' width='50'/>");
			}
			sb.append("					<span id='welcomeSpan'>Welcome, " + username + "</span>");
			sb.append("				</div>");
			sb.append("				<div id='profileDropdownDiv'>");
			sb.append("					<a href='Profile'>Profile</a>");
			sb.append("					<a href='Logout'>Logout</a>");
			sb.append("				</div>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div id='navigation'>");
			sb.append("			<div class='container-fluid'>");
			sb.append("				<ul>");
			sb.append("					<li id='lhome'><a href='Forum'>Home</a></li>");
			sb.append("					<li id='lprivatemessage'><a href='html/ComingSoon.html'>Private Message</a></li>");
			sb.append("					<li class='dropdown'>");
			sb.append("		        		<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Event</a>");
			sb.append("			        	<ul class='dropdown-menu'>");
			sb.append("			        		<li><a href='EventPage'>Events</a></li>");
			sb.append("		        			<li><a href='MyEventPage'>My Events</a></li>");
			sb.append("			        	</ul>");
			sb.append("		    		</li>");
			sb.append("					<li class='dropdown'>");
			sb.append("			       	 	<a class='dropdown-toggle' data-toggle='dropdown' href='#'>Potcast</a>");
			sb.append("			        	<ul class='dropdown-menu'>");
			sb.append("			          		<li><a href='p2plist'>Active PotCasts</a></li>");
			sb.append("			          		<li><a href='p2preg'>Start a PotCast</a></li>");
			sb.append("			          		<li><a href='p2pmy'>My PotCast</a></li>");
			sb.append("			          		<li><a href='p2pjoined'>Joined PotCast</a></li>");
			sb.append("			       		</ul>");
			sb.append("			      	</li>");
			sb.append("					<li id='ldonate'><a href='Donation'>Donate</a></li>");
			sb.append("				</ul>");
			sb.append("			</div>");
			sb.append("		</div>");
			sb.append("		<div id='wrapper'>");
			sb.append("			<form method='post' enctype='multipart/form-data' onsubmit='return validateForm()'>");
			sb.append("				<div id='thumbnail-container' class='row form-group'>");
			sb.append("					<div id='eventname' class='color-container'>");
			sb.append("						<p><b>Event Name</b><span class='requiredInput'>*</span></p>");
			sb.append("						<input type='text' class='form-control' name='EventName' id='eventName' required>");
			sb.append("					</div>");
			sb.append("					<div id='thumbnail-main' class='col form-group color-container'>");
			sb.append("						<p><b>Thumbnail</b><span class='requiredInput'>*</span></p>");
			sb.append("						<div id='thumbnail-container-container'>");
			sb.append("							<img src='images/wood.jpeg' alt='crab' height='100' width='200' id='chosenThumbnail'>");
			sb.append("							<div id='thumbnail-container-buttons'>");
			sb.append("								<input type='button' class='btn' value='Select' onclick='showDefault()'> or");
			sb.append("								<input type='file' class='form-control-file' accept='image/*' id='fileUpload' name='thumbnailUpload'>");
			sb.append("								<input type='hidden' id='fileNum' name='DefaultNumber' value='1'>");
			sb.append("							</div>");
			sb.append("						</div>");
			sb.append("					</div>");
			sb.append("					<div id='default-thumbnail'>");
			sb.append("						<img src='images/wood.jpeg' alt='crab' height='50' width='100' onclick='changeThumbnail(this, 4)'>");
			sb.append("						<img src='images/blue.jpeg' alt='food' height='50' width='100' onclick='changeThumbnail(this, 5)'>");
			sb.append("						<img src='images/mountain.jpeg' alt='SomeKoreanStar' height='50' width='100' onclick='changeThumbnail(this, 6)'>");
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("				<div id='eventname-container' class='row form-group'>");
			sb.append("					<div class='color-container col form-group'>");
			sb.append("						<div id='maxamtofppt'>");
			sb.append("							<p><b>Max Participants</b><span class='requiredInput'>*</span></p>");
			sb.append("							<input type='number' class='form-control' name='MaxAmtPpt' min='1' max='1000' required>");
			sb.append("						</div>");
			sb.append("						<div id='autoaccept' class='col form-group'>");
			sb.append("							<p><b>Auto Accept Request</b></p>");
			sb.append("							<input type='checkbox' class='form-check-input' name='AutoAccept'>");
			sb.append("						</div>");
			sb.append("					</div>");
			sb.append("					<div class='color-container col form-group'>");
			sb.append("						<div id='postalcode'>");
			sb.append("							<p><b>Postal Code</b><span class='requiredInput'>*</span></p>");
			sb.append("							<input type='text' id='postalInput' class='form-control' name='PostalCode'>");
			sb.append("						</div>");
			sb.append("						<div id='eventaddress' class='col form-group'>");
			sb.append("							<p><b>Address</b><span class='requiredInput'>*</span></p>");
			sb.append("							<input type='text' class='form-control' id='mainAddress' name='Address'><br>");
			sb.append("						</div>");
			sb.append("						<div class='col form-group'>");
			sb.append("							<p><b>Additional Address</b></p>");
			sb.append("							<input type='text' class='form-control' id='additionalAddress' name='AdditionalAddress'>");
			sb.append("						</div>");
			sb.append("					</div>");
			sb.append("				</div>");
			sb.append("				<div id='description-container' class='form-group row color-container'>");
			sb.append("					<p><b>Description</b></p>");
			sb.append("					<textarea id='description' class='form-control' placeholder='Type here...' name='EventDesc'></textarea>");
			sb.append("				</div>");
			sb.append("				<div id='guest-container' class='form-group'>");
			sb.append("					<p><b>Guest</b></p>");
			sb.append("					<div>");
			sb.append("						<div id='guest-container-left-container'>");
			sb.append("							<input type='text' id='textguestname' list='guestnamelist' autocomplete='off' class='form-control'>");
			sb.append("							<input type='hidden' id='guestNameList' name='GuestName'>");
			sb.append("							<datalist id='guestnamelist'>");
			for(String s:db.getDatabaseUserIGN()) {
				sb.append("<option>" + s + "</option>");
			}
			
			out.write(sb.toString().getBytes());
			out.write(footer);
			out.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
    		String currentIGN = (String)session.getAttribute("username");
			
			Database db = new Database(2);
			EventModel eM = new EventModel();
			String thumbnailNumberInput = request.getParameter("DefaultNumber");
			if(thumbnailNumberInput.isEmpty() || "".equals(thumbnailNumberInput)) {
				Part filePart = request.getPart("thumbnailUpload");
				
				String fileName = encodeString(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
				byte[] thumbnailBytes = IOUtils.toByteArray(filePart.getInputStream());
				
				eM.setThumbnail(db.addPictureWithDupeCheck(fileName, thumbnailBytes));
			}else {
				int thumbnailNumber = Integer.parseInt(thumbnailNumberInput);
				eM.setThumbnail(thumbnailNumber);
			}
			
			String eventName = encodeString(request.getParameter("EventName"));
			eM.setEventName(eventName);
			
			int amtOfPeople = Integer.parseInt(request.getParameter("MaxAmtPpt"));
			eM.setMax_No_People(amtOfPeople);
			
			if(request.getParameter("AutoAccept") == null)
				eM.setAutoAccept(false);
			else
				eM.setAutoAccept(true);
			
			String postalCode = encodeString(request.getParameter("PostalCode"));
			eM.setPostalCode(postalCode);
			
			String address = encodeString(request.getParameter("Address") + "` " + request.getParameter("AdditionalAddress"));
			eM.setVenue(address);
			
			String eventDesc = encodeString(request.getParameter("EventDesc"));
			eM.setDescription(eventDesc);
			
			ArrayList<String> guestName = stringToArrayList(encodeString(request.getParameter("GuestName")));
			eM.setGuestArray(guestName);
			
			String eventDate = request.getParameter("EventDate");
			String eventTime = request.getParameter("EventTime");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(eventDate + " " + eventTime));
			cal.set(Calendar.MILLISECOND, 0);
			new Timestamp(cal.getTimeInMillis());
			Timestamp dateOfEvent = new Timestamp(cal.getTimeInMillis());
			eM.setDate(dateOfEvent);
			
			ArrayList<String> fileListArray = new ArrayList<String>();
			List<Part> fileParts = request.getParts().stream().filter(part -> "EventGallery".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
		    for (Part p : fileParts) {
		        String fileName = encodeString(Paths.get(p.getSubmittedFileName()).getFileName().toString());
		        byte[] gallaryFileBytes = IOUtils.toByteArray(p.getInputStream());
				
				fileListArray.add(String.valueOf(db.addPictureWithDupeCheck(fileName, gallaryFileBytes)));
		    }
		    eM.setFileListArray(fileListArray);
		    eM.setiGN(currentIGN); // Current IGN
		    eM.setStatus("H");
		    db.insertCreateEvent(eM);
		    
		    response.sendRedirect("MyEventPage");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private String encodeString(String line) throws UnsupportedEncodingException {
		return URLEncoder.encode(line, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
	}
	
	private ArrayList<String> stringToArrayList(String line){
		ArrayList<String> array = new ArrayList<String>();
		Scanner sc = new Scanner(line);
		sc.useDelimiter(",");
		while(sc.hasNext()) {
			array.add(sc.next());
		}
		sc.close();
		return array;
	}
}
