package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.CommentModel;
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
		out.println(
		"<html>"
		+ "<p> hello </p>"
		+ "						<form action='testinfo' method='POST'>"	
		+ "						<textarea class='form-control' id='exampleFormControlTextarea1' rows='3' name='rtor'></textarea>"
		+"						<button type='submit' id='postBtn' cursor:pointer;' class='btn'>Post/Submit</button>"
		+ "						</form>"		
				);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String haha = request.getParameter("rtor");
		java.sql.Date date1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		out.println(
		"<html>"
		+ "<p> uploading into server </p>"						
				);
		
		try {
			Database ttttt = new Database(2);
			CommentModel cm = new CommentModel();
			cm.setPostID(1);
			cm.setComment1(10);
			cm.setDate(date1);
			cm.setDescription(haha);
			cm.setiGN("GordonRamsey");
			ttttt.addComment(cm);
			out.println("<html>Success</html>");
		
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


