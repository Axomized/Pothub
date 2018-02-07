package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.ReportToURL;
import database.Database;
import database.model.ReportModel;

/**
 * Servlet implementation class SuccessReporting
 */
public class SuccessReporting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessReporting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Database db = new Database(2);
		
			Date date1 = new Date(System.currentTimeMillis());
			String reportdes = "NOTHING";
			ArrayList <String> haha = new ArrayList<String>();
			String ignreceive = request.getParameter("ignsend"); //person reporting
			String postID = request.getParameter("forumID");
			String whyreport = request.getParameter("whyyoureport");//checkbox report
			String textreport = request.getParameter("somanytext");//text report
			Scanner sc = new Scanner(postID);
			sc.useDelimiter(",");
			haha.add(sc.next());
			haha.add(sc.next());
			sc.close();
			String id = haha.get(0);//id of forum post
			String ignsend = haha.get(1);//person reported
			if(whyreport.isEmpty() || whyreport == null) {
				reportdes = textreport;
			}
			else {
				reportdes = whyreport;
			}
			System.out.println("The reason for you to report is : " + reportdes);
			
			ReportModel rm = new ReportModel();
			rm.setEvidenceType("Forum");
			rm.setReason(reportdes);
			rm.setEvidence(Integer.parseInt(id));
			rm.setiGNReceive(ignsend);
			rm.setiGNSend(ignreceive);
			rm.setDate(date1);
			rm.setReportID(0);
			rm.setGuiltyOrNot(0);
			
			ReportToURL.execute(rm);
			db.addReport(rm);
			
			response.sendRedirect("Forum");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
