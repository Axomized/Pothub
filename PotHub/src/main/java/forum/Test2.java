package forum;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;

import database.Database;
import database.model.FileTableModel;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;

@MultipartConfig(fileSizeThreshold=8024*1024*2, maxFileSize=8024*1024*10, maxRequestSize=8024*1024*50)
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Test2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				//+ "<script src='https://www.google.com/recaptcha/api/challenge?k=6LdoEUIUAAAAAFPLD3IhU98g25qAWdFezJEnOD0f'></script>"
				+ "<script src='https://www.google.com/recaptcha/api.js'></script>"
				+ "<script> function recaptchaCallback(){ alert('please check the captcha!!'); return false; }</script>"
				+ "</head>"
				+ "<body>"
				+"<form action='Test2' method='post'>"
				//+ "<textarea name='recaptcha_challenge_field' rows='3' cols='40'></textarea>"
				//+ " <input type='hidden' name='recaptcha_response_field' value='manual_challenge'>"
				+  "Username: <input type='text' name='user'> <br>"
				+  "Password: <input type='password' name='pwd'> <br>"
				+  "<div class='g-recaptcha' data-callback='recaptchaCallback' data-sitekey='6LdoEUIUAAAAAFPLD3IhU98g25qAWdFezJEnOD0f'></div>"
				+  "<br> <input type='submit' value='Login'>"	    
			    +  "</form>"
	
				+ "</body>"
				+ "</html>"
		);
		out.close();
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get request parameters for userID and password
			String user = request.getParameter("user");
			String pwd = request.getParameter("pwd");
			// get reCAPTCHA request param
			String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			System.out.println(gRecaptchaResponse);
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			// logging example
			System.out.println("User=" + user + "::password=" + pwd + "::Captcha Verify"+verify);

			
		
	 }
}
