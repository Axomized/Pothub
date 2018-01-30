package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.LoginModel;

/**
 * Servlet implementation class ForceChangePassword1
 */
public class ForceChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForceChangePassword() {
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
						+"		<html>"
						+"		<head>"
						+"			<meta charset='UTF-8'>"
						+"			<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
						+"			<!-- Favicon -->"
						+"			<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>"
						+"			<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>"
						+"			<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet'>"
						+"			<!-- Page Title -->"
						+"			<title>Force Change Password Page</title>"
						+"			<!-- Latest compiled and CSS -->"
						+"			<link rel='stylesheet' "
						+"			href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' "
						+"			integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' "
						+"			crossorigin='anonymous'>"
						+"			<!-- Optional theme -->"
						+"			<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
						+"			<!-- My Own Script -->"
						+"			<!-- My Style Sheet -->"
						+"			<link rel='stylesheet' type='text/css' href='css/Registration.css' />"
						+"		</head>"
						+"		<body>"
						+"			<h1>Reset Password</h1>"
						+"			<form method='post' action='ForceChangePassword'>"
						+"				<span color='#49274a'>New Password:</span>"
						+"				<input type='password' name='password' placeholder='Enter new password'>"
						+"				<span color='#49274a'>Retye New Password:</span>"			
						+"				<input type='password' name='password2' placeholder='Confirm new password'>"
						+"				<input type='submit' name='submit' value='SUBMIT'>"
						+"			</form>"
						+"			<div id='footer'>"
						+"		  		<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
						+"		  		<p>We like food</p>"
						+"		  		<p> <a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a></p>"
						+"			</div>"
						+"			<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
						+"			<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
						+"			<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
						+"		</body>"
						+"		</html>"
						);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();	
		
		String enteredPassword = request.getParameter("password");
		String confirmPassword = request.getParameter("password2");
		
		HttpSession session = request.getSession();
		String email = session.getAttribute("username").toString();
		System.out.println(email);
		
		if (enteredPassword.equals(confirmPassword))
		{
			try {
				Database db = new Database(2);
		    	LoginModel lm = db.getLogin(enteredPassword, email);
			    byte[] hash = PBKDF2.pbkdf2(enteredPassword.toCharArray(), PBKDF2.fromHex(lm.getSalt()), PBKDF2.PBKDF2_ITERATIONS, PBKDF2.HASH_BYTES);
				db.updateChangedPassword(PBKDF2.toHex(hash), email);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('You have successfully changed your password!');");
				out.println("window.location.href = 'Login'");
				out.println("</script>");			
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter the same exact password twice.');");
			out.println("</script>");
			doGet(request, response);
		}
	}

}
