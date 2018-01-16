package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.ForumPostModel;

/**
 * Servlet implementation class SuccessPage
 */
public class SuccessPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String forumT = request.getParameter("Forumtitle");
		String forumD = request.getParameter("Forumdescription");
		String file = request.getParameter("file");
		//int picc = Integer.parseInt(request.getParameter("pic"));
		String url = request.getParameter("link");
		String words = request.getParameter("words");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			Database ttttt = new Database(2);
			ForumPostModel fp = new ForumPostModel();
			fp.setDate(timestamp);
			fp.setDescription(forumD);
			fp.setFileAttachment(null);
			fp.setiGN("GordonRamsey");
			fp.setPicture(1);
			fp.setThread(forumT);
			fp.setUpvotes(0);
			fp.setForumNormalText(words);
			fp.setForumURL(url);
			ttttt.addForumPost(fp);
			out.println("<html>Success</html>");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
