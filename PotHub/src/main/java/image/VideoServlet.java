package image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.FileTableModel;

public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String FILENAME = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
		try {
			Database db = new Database(0);
			FileTableModel fTM = db.getFileTableByFileName(FILENAME);
			
			File file = File.createTempFile(FILENAME, ".tmp");
			
			if(fTM != null) {
				FileOutputStream fos = new FileOutputStream(file);
			    fos.write(fTM.getData());
			    
			    response.setHeader("Content-Type", getServletContext().getMimeType(FILENAME));
			    
				FileInputStream in = new FileInputStream(file);
				OutputStream out = response.getOutputStream();

			       byte[] buf = new byte[1024];
			       int count = 0;
			       while ((count = in.read(buf)) >= 0) {
			         out.write(buf, 0, count);
			      }
			   fos.close();
			   out.close();
			   in.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// Sorry again... I using this for ending my Event (Wx)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final String EVENTNAME = request.getParameter("eventName");
			final String STATUS = request.getParameter("status");
			final Database DB = new Database(2);
			DB.setEventStatus(EVENTNAME, STATUS);
			
			response.getWriter().write("Success");
		} catch(Exception e) {
			response.sendRedirect("/PotHub/EventPage");
		}
	}

}
