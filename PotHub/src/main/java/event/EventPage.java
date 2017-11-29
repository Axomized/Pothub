package event;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EventPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>"
				+ "<html>"
				+ "	<head>"
				+ "		<meta charset='ISO-8859-1'>"
				+ "		<meta name='viewport'"
				+ "			content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
				+ "		<!-- Page Title -->"
				+ "		<title>Event</title>"
				+ "		<!-- Latest compiled and minified CSS -->"
				+ "		<link rel='stylesheet'"
				+ "			href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'"
				+ "			integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u'"
				+ "			crossorigin='anonymous'>"
				+ "		<!-- Optional theme -->"
				+ "		<link rel='stylesheet'"
				+ "			href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css'"
				+ "			integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp'"
				+ "			crossorigin='anonymous'>"
				+ "		<title>Home Pages</title>"
				+ "		<!-- My Own Script -->"
				+ "		<script src='script/'></script>"
				+ "		<!-- My Style Sheet -->"
				+ "		<link rel='stylesheet' type='text/css' href='css/EventPage.css' />"
				+ "	</head>"
				+ "	<body>"
				+ "		<!--  Navigation Bar -->"
				+ "		<div id='header'>"
				+ "			<div id='companyTitle'>"
				+ "				<h1>PotHub</h1>"
				+ "			</div>"
				+ "			<div id='profilePic'>"
				+ "				<img src='images/cat.png' height='50' width='50'/>"
				+ "			</div>"
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
				+ "				</div>"
				+ "				<div id='content'>"
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
				+ "		<!-- jQuery library -->"
				+ "		<script"
				+ "			src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
				+ "		<!-- Latest compiled and minified JavaScript -->"
				+ "		<script"
				+ "			src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'"
				+ "			integrity='sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa'"
				+ "			crossorigin='anonymous'></script>"
				+ "	</body>"
				+ "</html>"
);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
