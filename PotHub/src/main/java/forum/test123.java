package forum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.FileTableModel;
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
		try {
			Database db = new Database(2);
			//String fileName = db.getFileNameByFileID(15);
			FileTableModel ftm = new FileTableModel();
			ftm = db.getFileTableByFileID(15);
			File tempFile = File.createTempFile(ftm.getFileName(), ".tmp", null);
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(ftm.getData());
			response.setContentType("text/html");
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + ftm.getFileName() +"\"");
			FileInputStream stream = new FileInputStream(tempFile);
			int i;   
			while ((i=stream.read()) != -1) {  
			out.write(i);   
			}   
			stream.close(); 
			
		
			out.println("<html>"
					//+ "<p>file name is " + ftm.getFileName() + "</p>"
					//+ "<p><a href='"+ ftm.getFileName() + "' download>Download here!</a></p>"
					+ "<iframe style='width:80%; height:80%; 'src='https://docs.google.com/gview?url=http://58.182.48.127:8080/PotHub/Video/" + ftm.getFileName() + " &embedded=true'></iframe>" 
					+ "</html>"
					+ "");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
