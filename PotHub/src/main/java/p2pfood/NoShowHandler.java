package p2pfood;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.PotcastModel;
import database.model.ReportModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/noShowHandler")
public class NoShowHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoShowHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("p2plist");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		HttpSession session = request.getSession(false);
		Database db = new Database(2);
		ReportModel rm = new ReportModel();
		
		String username = (String) session.getAttribute("username");
		
		if(session==null||username==null||request.getParameter("bidder")==null||request.getParameter("bidon")==null){
			response.sendRedirect("p2plist");
			return;
		}
		
		if(db.didGuyBidOnPotcast(request.getParameter("bidder"), Integer.parseInt(request.getParameter("bidon")))){
			
			if(db.isGuyBidOnPotcastReported(request.getParameter("bidder"), username, Integer.parseInt(request.getParameter("bidon")))){
				System.out.println("Report already!");
				response.sendRedirect("p2plist");
				return;
			}
			
			PotcastModel pm = db.getPotcastByID(Integer.parseInt((String) request.getParameter("bidon")));
			if(pm.getiGN().equals(username)&&pm.getPickupTime().getTime()+(long)3600000<System.currentTimeMillis()){
				rm.setDate(new Date(System.currentTimeMillis()));
				rm.setEvidence(pm.getPotcastID());
				rm.setEvidenceType("Potcast Bid");
				rm.setGuiltyOrNot(0);
				rm.setiGNReceive(request.getParameter("bidder"));
				rm.setiGNSend(username);
				rm.setReason("No show");
				
				db.addReport(rm);
				
				System.out.println("Report success!");
			}
			else{
				System.out.println("Bad creds");
			}
		}
		else{
			System.out.println("Wrong person");
		}
		
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
		response.sendRedirect("p2plist");
		return;
	}
}
