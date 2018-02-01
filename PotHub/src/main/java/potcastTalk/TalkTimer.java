package potcastTalk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.PotcastModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/potcastTalk")
public class TalkTimer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Timer timer;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TalkTimer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
    public void init() throws ServletException {
  		try {
          Database db = new Database(0);
          ArrayList<PotcastModel> potcastsToEmail = db.getPotcastStartCookingEmail();
        		  
      	  timer = new Timer(true);
          for(PotcastModel i : potcastsToEmail){
        	  	TimerTask timerTask = new EmailTask(i);
          		timer.schedule(timerTask, i.getBidStopTime());
          }
		} catch (FileNotFoundException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void addToTimer(PotcastModel pot){
    	TimerTask timerTask = new EmailTask(pot);
    	timer.schedule(timerTask, pot.getBidStopTime());
    	System.out.println(timerTask+" "+pot.getBidStopTime());
    }
}
