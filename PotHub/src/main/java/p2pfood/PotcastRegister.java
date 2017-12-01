package p2pfood;

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
@WebServlet("/p2preg")
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
		PrintWriter pw = response.getWriter();
		pw.append(
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
						+ "<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>" + "<head>"
						+ "<title>Start a Potcast</title>" + "<meta http-equiv='content-language' content='en-us' />"
						+ "<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
						+ "<link rel='stylesheet' type='text/css' media='screen' href='css/p2preg.css' />" + "</head>"
						+ "<body id='babout'>" + "<div id='header'>" + "<h1>PotHub</h1>"
						+ "</div>" + "<div id='navigation'>" + "<ul>" + "<li>"
						+ "<a href='p2plist'>Active Potcasts</a>" + "</li>" + "<li>"
						+ "<a href='p2preg'>Start a Potcast</a>" + "</li>" + "<li>" + "<a href='p2pmy'>My Potcasts</a>"
						+ "</li>" + "<li>" + "<a href='p2pjoined'>Joined Potcasts</a>" + "</li>" + "</ul>" 
						+"<p id='logout'><a href='html/LoginPage.html'>Logout</a></p>"
						+ "</div>"
						+ "<div id='wrapper'>" + "<div id='secondHeader'>" + "<h2>Start a PotCast</h2>" + "</div>"
						+ "<div id='form'>" + "<div class='formElement'>" + "<p>Food title</p>"
						+ "<input type='text'></input>" + "</div>" + "<div class='formElement'>" + "<p>Description</p>"
						+ "<input type='text' id='descBox'></input>" + "</div>" + "<div class='formElement'>"
						+ "<p>Portions available</p>" + "<input type='number'></input>"
						+ "<p>Starting Price Per Portion</p>" + "<input type='number'></input>" + "</div>"
						+ "<div class='formElement'>" + "<p>Bid closing time</p>" + "<input type='time'></input>"
						+ "<p>Collection time</p>" + "<input type='time'></input>" + "</div>"
						+ "<div class='formElement'>" + "<p>Address</p>" + "<input type='text'></input>"
						+ "<p>Postal Code</p>" + "<input type='text'></input>" + "</div>" + "<div class='formElement'>"
						+ "<button>Submit</button>" + "</div>" + "</div>" + "</div>" + "<div id='footer'>"
						+ "<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
						+ "<p>We like food</p>" + "<p>"
						+ "<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"
						+ "</p>" + "</div>" + "</body>" + "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
