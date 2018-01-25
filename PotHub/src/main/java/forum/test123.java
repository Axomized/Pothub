package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.ForumPostModel;
import database.model.ForumVoteModel;
import database.model.SubscriptionModel;

/**
 * Servlet implementation class test123
 */
public class test123 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test123() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int canvote = 0;
		String username = "SwagPower";
		try {
			Database dbms = new Database(2);
			ArrayList<ForumPostModel> fa = new ArrayList<ForumPostModel>();
			fa = dbms.getForumModel();
			ArrayList<ForumVoteModel> fvm = new ArrayList<ForumVoteModel>();
			ArrayList<String> people = new ArrayList<String>();
			fvm = dbms.getForumVoteModel();
			for(ForumVoteModel l:fvm) {
				people.add(l.getiGN());
			}
			for(ForumPostModel qw: fa){	//loop 5 times
				if(people.contains(qw.getiGN()) && people.contains(username)) {
					
					}
				}
				
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
