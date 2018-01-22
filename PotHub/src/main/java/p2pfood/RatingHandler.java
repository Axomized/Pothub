package p2pfood;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/RatingHandler")
public class RatingHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RatingHandler() {
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
		try {
			response.setContentType("text/html");
			HttpSession session = request.getSession(false);
			Database db = new Database(2);
			if (session == null || request.getParameter("rating")==null) {
				response.sendRedirect("Login");
			}
			else{
				String username = (String) session.getAttribute("username");
				String targetUser = db.getPotcastByID(Integer.parseInt(request.getParameter("potcastID"))).getiGN();
				int currentRank = db.getCookingRankFrom(targetUser);

				db.updateBid(username, Integer.parseInt(request.getParameter("potcastID")), Integer.parseInt(request.getParameter("rating")));
				db.setDatabaseUserRank(targetUser, currentRank + RankCalculator.getRankChange(currentRank, Integer.parseInt(request.getParameter("rating"))));
				System.out.println(currentRank);
				System.out.println(currentRank + RankCalculator.getRankChange(currentRank, Integer.parseInt(request.getParameter("rating"))));
			}
			response.sendRedirect("p2plist");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("p2pdetail?potcastID=" + request.getParameter("potcastID"));
		}
	}
}
