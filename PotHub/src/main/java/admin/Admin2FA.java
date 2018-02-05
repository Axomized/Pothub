package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.Database;
import database.model.AdminSessionModel;
import database.model.AdminTwoFAModel;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/AdminLogon")
public class Admin2FA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin2FA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.print(
				"<!DOCTYPE html>"
		+ " <html>"
		+ "	 <head>"
		+ "		<meta charset='UTF-8'>"
		+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
		+ "		<!-- Favicon -->"
		+ "		<link rel='icon' href='images/crab.gif' type='image/gif'>"
		+ "		<link rel='icon' href='images/crab.png' type='image/x-icon'>"
		+ "		<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet'>"
		+ "		<title>Login Page</title>"
		+ "		<!-- Latest compiled and CSS -->"
		+ "		<link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" 
		+ "		integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' "
		+ "		crossorigin='anonymous'>"
		+ "		<!-- Optional theme -->"
		+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
		+ "		<!-- My Own Script -->"
		+ "		<!-- <script src='../script/'></script> -->"
		+ "		<!-- My Style Sheet -->"
		+ "	<link rel='stylesheet' type='text/css' href='css/AdminPage.css' />"
		+ " <script src='https://www.google.com/recaptcha/api.js'></script>"
		+ "	</head>"
		+ "	<body>"
		+ "		<div class='container'>"
		+ "	<img src='images/tomato.jpg'>"
		+ "	<h2>Enter your 2FA Key sent to your email</h2>"
		+ "		<form action='AdminLogon' method='POST'>"
		+ "			<div class='form-input'>"
		+ "				<span><i class='fa fa-envelope' aria-hidden='true'></i></span>"
		+ "				<input type='text' name='twofakey'"
		+ "				placeholder='Two FA Key'>"
		+ "			</div>"
		+ "			<div class='form-input'>"
		+ "				<div class='login-button'>"
		+ "					<input type='submit' name='submit' value='LOGIN'>"
		+ "				</div>"
		+ "			</div>"
		+ "		</form>"
		+ "		<div class='form-input'>"
		+ "		<a href='/PotHub/Registration'>Create Account</a>"
		+ "		<a href='/PotHub/ForgetPassword'>Forget Password</a>"
		+ "		</div>"
		+ "	</div>"
		+ "	<div id='footer'>"
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
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(false);
		PrintWriter pw = response.getWriter();
		
		try {
			Database db = new Database(2);
		
		if(session==null||session.getAttribute("username")==null){
    		response.sendRedirect("AdminLogin");
    		return;
		}
		else if(db.getPermissionForIGN((String)session.getAttribute("username"))==2){
		
		String twoFAKey = request.getParameter("twofakey");
		String username = (String) session.getAttribute("username");
		
			AdminTwoFAModel atfam = new AdminTwoFAModel();
			atfam.setiGN(username);
			atfam.setTwoFAKey(twoFAKey);
			atfam.setSessionID(session.getId());
			
			int auth = db.authTwoFA(atfam);
			if(auth==2){
				response.sendRedirect("AdminLogin");
				return;
			}
			else if(auth==1){
				AdminSessionModel asm = new AdminSessionModel();
				asm.setiGN(username);
				asm.setSessionID(session.getId());
				asm.setTerminated(false);
				asm.setTimeAuthenticated(new Timestamp(System.currentTimeMillis()));
				
				db.killAllAdminSession(username);
				db.addAdminSession(asm);
				db.killAdminTwoFA(session.getId());
				
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('You have successfully logged in. Welcome!');");
				pw.println("window.location.href = 'AdminGeneral'");
				pw.println("</script>");
				return;
			}else{
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Invalid code!');");
				pw.println("window.location.href = 'AdminLogon'");
				pw.println("</script>");
				return;
			}
		
		}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
