package forum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
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
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;

import database.Database;
import database.model.CommentModel;
import database.model.FileTableModel;
import database.model.ForumPostModel;

/**
 * Servlet implementation class testinfo
 */

public class testinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public testinfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(""
				+ "<html>"
				+ "<head>"
				+ "</head>"
				+ "<body>"
				+ "<form action='testinfo' enctype='multipart/form-data' method='post'>"
				+ "<input type='file' name='HiMrTeo'>"
				+ "<input type='submit' value='Click here to submit'>"
				+ "</form>"
				+ "</body>"
				+ "</html>"
				
				);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("HiMrTeo");
		System.out.println(filePart.getSubmittedFileName());
		//String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
       // byte[] thumbnailBytes = IOUtils.toByteArray(filePart.getInputStream());
		
		//FileTableModel ft = new FileTableModel(0, fileName, thumbnailBytes);
		//System.out.println("Name: " + ft.getFileName() + "\nByte: " + ft.getData());
		
		//File file = new File("C:\\Users\\Wei Xuan\\Desktop\\mountain.jpeg");
	
		
		
		//ft.setData(b);
		//ft.setFileName(f.getName());
		//ft.setFileSize(f.length());
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


