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
import java.util.ArrayList;

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
import database.model.ForumPostModel;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;

@MultipartConfig(fileSizeThreshold=8024*1024*2, maxFileSize=8024*1024*10, maxRequestSize=8024*1024*50)
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String userid = "haha";
       
    public Test2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		userid = request.getParameter("ddee");
		String id = (String) request.getSession().getAttribute("userid");
		System.out.println("Session Id for this page" + id);
		out.println("<html>"
				+ "<head>"
				+ "</head>"
				+ "<body>"
				+ "<p>Page2</p>"
				+ "<form action='Test2' method='POST'>"
				+ "<input type='text' name='dde'></input>"
				+ "<input type='hidden' name='ll' value='" + userid + "'></input>"
				+ "<input type='submit' value='submit'></input>"
				+ "</form>"
				+ "</body>"
				+ "</html>");
		request.getSession().setAttribute("userid", null);
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String haha = request.getParameter("dde");
		String userr = request.getParameter("ll");
		System.out.println(haha);
		System.out.println(userr);
		request.getSession().setAttribute("userid", userr);
		response.sendRedirect("Test2");
		
	 }
}
