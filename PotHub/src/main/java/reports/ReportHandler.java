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

import adminSearch.SearchSanitizer;
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
		return;
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
				response.sendRedirect(request.getHeader("referer"));
				return;
			}
			else if(request.getParameter("reason")!=null){
				ReportModel rep = new ReportModel();
				
				if(request.getParameter("evidenceType").equals("Forum")){
					String test = db.getForumModelByID(Integer.parseInt(request.getParameter("evidence"))).getiGN();
					if(test!=null&&test.length()>0){
					rep.setiGNReceive(test);
					}
					else{
						response.sendRedirect(request.getHeader("referer"));
						return;
					}
				}
				
				else if(request.getParameter("evidenceType").equals("Event")){
					String test = db.getPersonWithEventID(Integer.parseInt(request.getParameter("evidence")));
					if(test!=null&&test.length()>0){
					rep.setiGNReceive(test);
					}
					else{
						response.sendRedirect(request.getHeader("referer"));
						return;
					}
				}
				
				else if(request.getParameter("evidenceType").equals("Potcast")){
					String test = db.getPotcastByID(Integer.parseInt(request.getParameter("evidence"))).getiGN();
					if(test!=null&&test.length()>0){
						rep.setiGNReceive(test);
					}
					else{
						response.sendRedirect(request.getHeader("referer"));
						return;
					}
				}
				
				rep.setDate(new Date(System.currentTimeMillis()));
				rep.setEvidence(Integer.parseInt(request.getParameter("evidence")));
				rep.setEvidenceType(request.getParameter("evidenceType"));
				rep.setGuiltyOrNot(0);
				rep.setiGNSend((String) session.getAttribute("username"));

				if(request.getParameter("reason").equals("Others")){
					if(request.getParameter("othersText")!=null
							&&SearchSanitizer.sanitise(request.getParameter("othersText")).replace(" ", "").length()>0){
						rep.setReason(request.getParameter("othersText"));
					}
					else{
						response.sendRedirect(request.getHeader("referer"));
						return;
					}
				}
				else{
					rep.setReason(request.getParameter("reason"));
				}
				
				db.addReport(rep);
			}

			response.sendRedirect(request.getHeader("referer"));
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getHeader("referer"));
			return;
		}
	}
}
