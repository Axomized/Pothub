package image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.model.ImageTableModel;

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String FILENAME = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
		try {
			Database db = new Database(0);
			ImageTableModel fTM = db.getImageTableByImageName(FILENAME);
			
			File file = File.createTempFile(FILENAME, ".tmp");
			
			if(fTM != null) {
				final FileOutputStream FOS = new FileOutputStream(file);
				FOS.write(fTM.getImageData());
			    
			    response.setHeader("Content-Type", getServletContext().getMimeType(FILENAME));
			    
			    final FileInputStream IN = new FileInputStream(file);
			    final OutputStream OUT = response.getOutputStream();

			       byte[] buf = new byte[1024];
			       int count = 0;
			       while ((count = IN.read(buf)) >= 0) {
			    	   OUT.write(buf, 0, count);
			      }
			   FOS.close();
			   OUT.close();
			   IN.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// I lazy create new servlet so using this to get user's profile picture using iGN (Wx)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String IGN = request.getParameter("iGN");
		try {
			response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
			response.setCharacterEncoding("UTF-8");
			
			final Database DB = new Database(0);
			
			final String FILENAME = DB.getUserProfilePic(IGN);
			if(FILENAME != null || !"".equals(FILENAME)) {
				final String PLACEMENT = request.getParameter("Placement"); // If I use it for the ranking
				if(PLACEMENT != null && !PLACEMENT.isEmpty()) {
					response.getWriter().write(FILENAME + "~" + PLACEMENT);
				}else {
					response.getWriter().write(FILENAME);
				}
			} else {
				response.getWriter().write("Empty");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
