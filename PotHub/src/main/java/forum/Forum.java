package forum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Forum
 */
public class Forum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE html>"
				+"<html>"
				+	"<head>"
				+		"<meta charset='ISO-8859-1'>"
				+		"<meta name='viewport'"
				+			"content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
				+		"<!-- Page Title -->"
				+		"<title>Forum</title>"
				+		"<!-- Latest compiled and CSS -->"
				+		"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
				+		"<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
				+		"<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
				+		"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
				+		"<!-- Optional theme -->"
				+		"<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
				+		"<!-- My Own Script -->"
				+		"<script src='../script/'></script>"
				+		"<!-- My Style Sheet -->"
				+		"<link rel='stylesheet' type='text/css' href='css/DefaultCss.css' />"
				+	"</head>"
				+	"<body>"
				+		"<!--  Navigation Bar -->"
				+		"<div id='header'>"
				+			"<div id='companyTitle'>"
				+				"<h1>PotHubb</h1>"
				+			"</div>"
				+			"<div id='profilePic'>"
				+				"<img src='images/cat.png' height='50' width='50'/>"
				+			"</div>"
				+		"</div>"
				+		"<div id='navigation'>"
				+			"<ul>"
				+				"<li id='lhome'><a href='#00'>Home</a></li>"
				+				"<li id='lprivatemessage'><a href='#01'>Private Message</a></li>"
				+				"<li id='levent'><a href='#02'>Event</a></li>"
				+				"<li id='lpeer2peer'><a href='#03'>Peer-2-Peer</a></li>"
				+				"<li id='ldonate'><a href='#04'>Donate</a></li>"
				+			"</ul>"
				+		"</div>"
				+		"<div id='wrapper'>"
				+			"<div id='content-wrapper'>"
				+				"<div id='content'>"
				+				"</div>"
				+				"<div id='content'>"
				+				"</div>"
				+			"</div>"
				+		"</div>"
				+		"<div id='footer'>"
				+			"<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved.</p>"
				+			"<p>We like foodddddd</p>"
				+			"<p>"
				+				"<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
				+			"</p>"
				+		"</div>"
				+	"</body>"
				+"</html>"
				);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
