package forum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import database.model.FileTableModel;
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
		out.println(""
				+ "<html>"
				+ "<form action='testinfo' method='post'>"
				+ "<input type='file' name='datafile'>"
				+ "</input>"
				+ "<input type='submit' value='Click here to submit'>"
				+ "</input>"
				+ "</form>"
				+ "</html>"
				
				);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String paths = request.getParameter("datafile");
		File f = new File(paths);
		String realpath = f.getAbsolutePath();
		File ff = new File(realpath);
		System.out.println(realpath);
		FileTableModel ft = new FileTableModel();
		/*
		 byte[] b = new byte[(int) f.length()];
         try {
               FileInputStream fileInputStream = new FileInputStream(f);
               fileInputStream.read(b);
               for (int i = 0; i < b.length; i++) {
                           System.out.print((char)b[i]);
                }
          } catch (FileNotFoundException e) {
                      System.out.println("File Not Found.");
                      e.printStackTrace();
          }
          catch (IOException e1) {
                   System.out.println("Error Reading The File.");
                    e1.printStackTrace();
          }
          */
		
		//ft.setData(b);
		//ft.setFileName(f.getName());
		//ft.setFileSize(f.length());
		
		System.out.println(ff.length());
		System.out.println(f.length());
		System.out.println(ff.getName());
		/*try {
			Database db = new Database(2);
			db.insertFileTable(ft);
			out.println(""
					+ "<p>Success</p>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}


