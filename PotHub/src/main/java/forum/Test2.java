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
		out.println("<html>"
				//+ "<p>file name is " + ftm.getFileName() + "</p>"
				//+ "<p><a href='"+ ftm.getFileName() + "' download>Download here!</a></p>"
				+ "<iframe style='width:80%; height:80%; 'src='https://docs.google.com/gview?url=http://111.65.44.227:8080/PotHub/Video/" + "Hello MR TEO.docx" + " &embedded=true'></iframe>" 
				+ "</html>"
				+ "");
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
