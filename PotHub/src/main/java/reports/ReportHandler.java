package reports;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.ReportModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/reportHandler")
public class ReportHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Forum");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			Database db = new Database(2);
			if (session == null) {
				response.sendRedirect("Login");
			}

			ArrayList<ReportModel> reports = db.getReportsFromOneUser(request.getParameter("username"));
			ArrayList<ReportModel> relevantReports = new ArrayList<ReportModel>();
			
			for(ReportModel report:reports){
				if(report.isGuiltyOrNot()==0){
					relevantReports.add(report);
				}
			}

			if(relevantReports.size()>=2){
				System.out.println("Too many reports");
				response.sendRedirect(request.getHeader("referer"));
			}
			else{
				ReportModel rep = new ReportModel();
				rep.setDate(new Date(System.currentTimeMillis()));
				rep.setEvidence(Integer.parseInt(request.getParameter("evidence")));
				rep.setEvidenceType(request.getParameter("evidenceType"));
				rep.setGuiltyOrNot(0);
				rep.setiGNSend((String) session.getAttribute("username"));
				rep.setReason(request.getParameter("reason"));
				
				if(request.getParameter("evidenceType").equals("Forum")){
					rep.setiGNReceive(db.getForumModelByID(Integer.parseInt(request.getParameter("evidence"))).getiGN());
				}
				
				else if(request.getParameter("evidenceType").equals("Event")){
					rep.setiGNReceive((db.getPersonWithEventID(Integer.parseInt(request.getParameter("evidence")))));
				}
				
				else if(request.getParameter("evidenceType").equals("Potcast")){
					rep.setiGNReceive(db.getPotcastByID(Integer.parseInt(request.getParameter("evidence"))).getiGN());
				}
				
				db.addReport(rep);
			}

			response.sendRedirect(request.getHeader("referer"));
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getHeader("referer"));
		}
	}
}
