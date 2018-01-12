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
import database.PBKDF2;
import database.model.DatabaseUserModel;
import database.model.LoginModel;

/**
 * Servlet implementation class LoginPage
 */
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
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
		+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>"
		+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>"
		+ "		<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet'>"
		+ "		<title>Login Page</title>"
		+ "		<!-- Latest compiled and CSS -->"
		+ "		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" 
		+ "		integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' "
		+ "		crossorigin='anonymous'>"
		+ "		<!-- Optional theme -->"
		+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
		+ "		<!-- My Own Script -->"
		+ "		<!-- <script src='../script/'></script> -->"
		+ "		<!-- My Style Sheet -->"
		+ "	<link rel='stylesheet' type='text/css' href='css/LoginPage.css' />"
		+ "	</head>"
		+ "	<body>"
		+ "		<div class='container'>"
		+ "	<img src=''>"
		+ "	<h2>Sign In</h2>"
		+ "		<form action='Login' method='POST'>"
		+ "			<div class='form-input'>"
		+ "				<span><i class='fa fa-envelope' aria-hidden='true'></i></span>"
		+ "				<input type='text' name='username'"
		+ "				placeholder='Enter Email'>"
		+ "			</div>"
		+ "			<div class ='form-input'>"
		+ "				<span><i class='fa fa-unlock-alt fa-lg' aria-hidden='true'></i></span>"
		+ "				<input type='password' name='password'"
		+ "				placeholder='Enter Password'>"
		+ "			</div>"
		+ "			<div class='form-input'>"
		+ "				<div class='login-button'>"
		+ "					<input type='submit' name='submit' value='LOGIN'>"
		+ "				</div>"
		+ "			</div>"
		+ "		</form>"
		+ "		<div class='form-input'>"
		+ "		<a href='../html/Registration.html'>Create Account</a>"
		+ "		<a href='#'>Forget Password</a>"
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
		PrintWriter out = response.getWriter();
		
		try 
		{
			String enteredEmail = request.getParameter("username");
			String enteredPassword = request.getParameter("password");
			
			try 
			{
				Database db = new Database(0);
				LoginModel lm = db.getLogin(enteredPassword, enteredEmail);
				DatabaseUserModel dum = db.getIGNbyEmail(lm.getEmail());
								
				// Hash the password
			    byte[] hash = PBKDF2.pbkdf2(enteredPassword.toCharArray(), PBKDF2.fromHex(lm.getSalt()), PBKDF2.PBKDF2_ITERATIONS, PBKDF2.HASH_BYTES);
				    
			    if (lm.getEmail().equals(enteredEmail) && lm.getPassword().equals(PBKDF2.toHex(hash)) )
			    {
					System.out.println("Login Success!");
					HttpSession session = request.getSession();
					session.setAttribute("username", dum.getiGN());
					response.sendRedirect("Forum");
			    }
			    else
			    {
			    	System.out.println("Login fail!");
			    	out.println("<script type=\"text/javascript\">");
					out.println("alert('Invalid username or password.');");
					out.println("</script>");
			    }
				
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) 
			{
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

}
