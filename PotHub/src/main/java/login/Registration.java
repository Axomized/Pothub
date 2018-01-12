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

import database.Database;
import database.PBKDF2;
import database.model.DatabaseUserModel;

/**
 * Servlet implementation class Registration2
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
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
		+ "	<html>"
		+ "	<head>"
		+ "		<meta charset='UTF-8'>"
		+ "		<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>"
		+ "		<!-- Favicon -->"
		+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.gif' type='image/gif'>"
		+ "		<link rel='icon' href='https://localhost/PotHub/images/crab.png?v=2' type='image/x-icon'>"
		+ "		<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet'>"
		+ "		<!-- Page Title -->"
		+ "		<title>Registration Page</title>"
		+ "		<!-- Latest compiled and CSS -->"
		+ "		<link rel='stylesheet' "
		+ "		href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' "
		+ "		integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' "
		+ "		crossorigin='anonymous'>"
		+ "		<!-- Optional theme -->"
		+ "		<script src='https://use.fontawesome.com/aff6d7353c.js'></script>"
		+ "		<!-- My Own Script -->"
		+ "		<script src='../script/SignUp.js'></script>"
		+ "		<!-- My Style Sheet -->"
		+ "		<link rel='stylesheet' type='text/css' href='css/Registration.css' />"
		+ "	</head>"
		+ "	<body>"
		+ "		<h1>Create Your Account</h1>"
		+ "		<form action='Registration' method='POST'>"
		+ "			<input type='text' name='email' placeholder='Enter your email'>"
		+ "			<input type='password' name='password' placeholder='Create a password'>"
		+ "			<input type='password' name='password2' placeholder='Confirm your password'>"
		+ "			<input type='text' name='name' placeholder='Enter your name'>"
		+ "			<input type='text' name='contact' placeholder='Enter your phone number'>"
		+ "			<input type='text' name='address' placeholder='Enter your postal code'>"
		+ "		<input type='text' name='unitno' placeholder='Enter your unit number'>"		
		+ "			<select name='gender'>"
		+ "				<option value='Male'>Male</option>"
		+ " 			<option value='Female'>Female</option>"
		+ "			</select>"
		+ "		<input type='submit' name='submit' value='SUBMIT'>"
		+ "		</form>"
		+ "		<div id='footer'>"
		+ "	  		<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
		+ "	  		<p>We like food</p>"
		+ "	  		<p> <a href='#''>Terms of Service</a> | <a href='#''>Privacy</a> | <a href='#''>Support</a></p>"
		+ "		</div>"
		+ "	<script src='https://code.jquery.com/jquery-3.1.1.slim.min.js' integrity='sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n' crossorigin='anonymous'></script>"
		+ "		<script src='https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js' integrity='sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb' crossorigin='anonymous'></script>"
		+ "		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
		+ "	</body>"
		+ " </html>"
		);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		DatabaseUserModel dum = new DatabaseUserModel();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("password2");
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String unitno = request.getParameter("unitno");
		String gender = request.getParameter("gender");
						
		if(!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(.[a-zA-Z]{2,})$"))
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter your email address in the format someone@example.com.');");
			out.println("</script>");
		}
			
		
		else if(password.length() == 0 ||
				password.length() < 7 ||
				!password.matches(".*[A-Z].*") || //checks if password has upper case letter
				!password.matches(".*[a-z].*") || //checks if password has lower case letter
				!password.matches(".*\\d.*") ||  //checks if password has numbers
				!password.matches("[a-zA-Z0-9 ]*") || //checks if password has special characters
				password.contains(" ")) //checks if password contains spaces
		{
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Password must contain at least 8 characters and one of each of the following: uppercase letter, lowercase letter, number.');");
			out.println("</script>");
		}
		
		else if(!confirmPassword.equals(password.toString()))
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter the same exact password twice.');");
			out.println("</script>");
		}
		
		else if(name.length() == 0)
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter your name.');");
			out.println("</script>");
		}
		
		else if(contact.length() != 8 ||
				!contact.matches("[0-9]+")) //checks if number contains all numbers only
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter a valid contact number.');");
			out.println("</script>");
		}
		
		else if(address.length() != 6 ||
				!address.matches("[0-9]+"))
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Invalid postal code.');");
			out.println("</script>");
		}
		
		else if(unitno.length() == 0)
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter your unit number.');");
			out.println("</script>");
		}
					
		else{
			
			try 
			{				
				dum.setEmail(email);
				dum.setiGN(name);
				dum.setContact_No(contact);
				dum.setAddress(address);
				dum.setUnitNo(unitno);
				dum.setGender(gender.charAt(0));	
				
				PBKDF2.createHash(password, email);

								
			} 
			catch (NoSuchAlgorithmException | InvalidKeySpecException e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				Database db = new Database(1);
				db.insertRegistration(dum);
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			out.println("<script type=\"text/javascript\">");
			out.println("alert('You have successfully registered!');");
			out.println("</script>");
			response.sendRedirect("LoginPage");
		}
			
	}

}
