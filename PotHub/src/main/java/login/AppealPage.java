package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.AppealModel;
import database.model.BansModel;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/AppealPage")
public class AppealPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppealPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Database db;
		try {
			db = new Database(0);

		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendRedirect(request.getHeader("referer"));
			return;
		}
		String ign = (String) session.getAttribute("ign");
		
		boolean validUser = false;
		String messageToShow = "";
		ArrayList<BansModel> bans = db.getBansForUser(ign);
		for(BansModel ban : bans){
			if(ban.getEndDate().getTime()>System.currentTimeMillis()&&!ban.isPardoned()){//Found an active ban
				if(db.getAppealsByBanID(ban.getBanID())!=null){//Found an active appeal
					messageToShow = "<h2>You've already sent your appeal. Please wait for it to be reviewed.</h2>";
					validUser=true;
				}
				else{
					messageToShow = "<h2>You've been banned for: "
							+ ban.getReason()
							+ "</h2>"
							+ "<p>Send us an appeal if you believe there has been an error.</p>"
							+ "		<form method='POST'>"
							+ "		<textarea cols='35' rows='8' maxlength='500' autofocus name='message' required></textarea>"	
							+ "		<input type='submit'></input>"	
							+ "		</form>";
					validUser=true;
				}
			}
		}
		
		if(!validUser){
			response.sendRedirect(request.getHeader("referer"));
		}
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
		+ "		<!-- My Style Sheet -->"
		+ "	<link rel='stylesheet' type='text/css' href='css/AppealPage.css' />"
		+ "	</head>"
		+ "	<body>"
		+ "		<div class='container'>");
		
		pw.append(messageToShow);
		
		pw.append("	</div>"
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
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String ign = (String) session.getAttribute("ign");
		String message = request.getParameter("message");
		
		try{
		Database db = new Database(2);
		int banID = 0;
		
		ArrayList<BansModel> bans = db.getBansForUser(ign);
		for(BansModel ban : bans){
			if(ban.getEndDate().getTime()>System.currentTimeMillis()&&!ban.isPardoned()){
				banID=ban.getBanID();
			}
		}
		AppealModel appeal = new AppealModel();
		appeal.setApproval(0);
		appeal.setBanID(banID);
		appeal.setiGN(ign);
		appeal.setMessage(message);
		appeal.setReceiveDate(new Date(System.currentTimeMillis()));
		
		db.addAppeal(appeal);
		
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Thanks for your appeal. We will get back to you soon.');");
		out.println("window.location.href = 'Login'");
		out.println("</script>");
		}
		catch(ClassNotFoundException | SQLException e){
			
		}
	}

}
