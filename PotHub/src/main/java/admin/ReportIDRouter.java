package admin;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet("/AdminRouter")
public class ReportIDRouter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportIDRouter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try{
		Database db = new Database(0);
		
		if(session==null||session.getAttribute("username")==null){
    		response.sendRedirect("AdminLogin");
    		return;
		}
		
		if(request.getParameter("evidence")==null||request.getParameter("evidenceType")==null){
			response.sendRedirect(request.getHeader("referer"));
			return;
		}
		
		else if(db.getPermissionForIGN((String)session.getAttribute("username"))==2){
			response.sendRedirect(ReportToURL.execute(new ReportModel(request.getParameter("evidenceType"),Integer.parseInt(request.getParameter("evidence")))));
			return;
		}
		else{
    		response.sendRedirect("AdminLogin");
    		return;
		}
		}
		catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
