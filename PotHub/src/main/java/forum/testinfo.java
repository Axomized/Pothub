package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.ForumPostModel;

/**
 * Servlet implementation class testinfo
 */

public class testinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			Database ttttt = new Database(2);
			ForumPostModel fp = new ForumPostModel();
			fp.setDate(timestamp);
			fp.setDescription("blah blah blah");
			fp.setFileAttachment(null);
			fp.setiGN("GordonRamsey");
			fp.setPicture(1);
			fp.setThread("darren is handsome");
			fp.setUpvotes(69696969);
			ttttt.addForumPost(fp);
			out.println("<html>"
					+ "<p> hello </p>"
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
