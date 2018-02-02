package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.DatabaseUserModel;
import database.model.LoginModel;
import login.PBKDF2;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
		+ "	<h2>Sign In</h2>"
		+ "		<form action='AdminLogin' method='POST'>"
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
		+ " <div class='g-recaptcha' data-sitekey='6Ldq7EIUAAAAANNbKYrxspUbr9X6eN1r6y2sHDxb'></div>"
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
		PrintWriter out = response.getWriter();
		
		
			String enteredEmail = request.getParameter("username");
			String enteredPassword = request.getParameter("password");
			try 
			{
				Database db = new Database(0);
			    
			    if(db.getEmail(enteredEmail) == true)
			    {
			    	LoginModel lm = db.getLogin(enteredPassword, enteredEmail);
					DatabaseUserModel dum = db.getDatabaseUserByEmail(lm.getEmail());
					
					// Hash the password
				    byte[] hash = PBKDF2.pbkdf2(enteredPassword.toCharArray(), PBKDF2.fromHex(lm.getSalt()), PBKDF2.PBKDF2_ITERATIONS, PBKDF2.HASH_BYTES);
			    	
			    	if (lm.getEmail().equals(enteredEmail) && lm.getPassword().equals(PBKDF2.toHex(hash)) )
			    	{
			    		HttpSession session = request.getSession();
			    		
			    		if (dum.getUserPermission()!=2){
					    	out.println("<script type=\"text/javascript\">");
							out.println("alert('Invalid username or password.');");
							out.println("</script>");
							doGet(request, response);
			    		}
			    		else 
			    		{
			    			
			    			// get reCAPTCHA request param
			    			String gRecaptchaResponse = request
			    					.getParameter("g-recaptcha-response");
			    			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			    			
			    			if(verify){
				    		session.setMaxInactiveInterval(900);
			    			session.setAttribute("username", dum.getiGN());
			    			if (!lm.isPasswordResetted())
				    		{
			    				out.println("<script type=\"text/javascript\">");
			    				out.println("alert('You have successfully logged in. Welcome!');");
			    				out.println("window.location.href = 'AdminGeneral'");
			    				out.println("</script>");
				    		}
			    			}
			    			else{
						    	System.out.println("Captcha fail!");
						    	out.println("<script type=\"text/javascript\">");
								out.println("alert('Invalid username or password.');");
								out.println("</script>");
								doGet(request, response);
								return;
			    			}
			    		}
			    	}
			    	
			    	else
				    {
				    	System.out.println("Login fail!");
				    	out.println("<script type=\"text/javascript\">");
						out.println("alert('Invalid username or password.');");
						out.println("</script>");
						doGet(request, response);
						return;
				    }
			    }
			    
			    else
			    {
			    	System.out.println("Login fail!");
			    	out.println("<script type=\"text/javascript\">");
					out.println("alert('Invalid username or password.');");
					out.println("</script>");
					doGet(request, response);
					return;
			    }
				
			 
		} catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
	}

}
