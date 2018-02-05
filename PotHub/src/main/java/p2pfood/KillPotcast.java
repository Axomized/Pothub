package p2pfood;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.model.PotcastModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/deletePotcast")
public class KillPotcast extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KillPotcast() {
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
		System.out.println("KILLING!");
		try{
		HttpSession session = request.getSession(false);
		Database db = new Database(2);
		String username = (String) session.getAttribute("username");
		
		if(session==null||username==null||request.getParameter("potcastID")==null){
			response.sendRedirect("p2plist");
			return;
		}
		
		PotcastModel pm = db.getPotcastByID(Integer.parseInt((String) request.getParameter("potcastID")));
	
		if(pm.getiGN().equals(username)&&pm.getBidStopTime().getTime()>System.currentTimeMillis()){
			db.deletePotcast(pm.getPotcastID());
		}
		else if(db.getPermissionForIGN(username)>0){
			db.deletePotcast(pm.getPotcastID());
		}
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
		response.sendRedirect("p2plist");
		return;
	}
}
