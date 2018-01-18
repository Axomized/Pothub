package forum;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;

import database.Database;
import database.model.FileTableModel;

@MultipartConfig(fileSizeThreshold=8024*1024*2, maxFileSize=8024*1024*10, maxRequestSize=8024*1024*50)
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Test2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "</head>"
				+ "<body>"
				+ "<form enctype='multipart/form-data' method='post'>"
				+ "<input type='file' name='HiMrTeo'>"
				+ "<input type='submit' value='Click here to submit'>"
				+ "</form>"
				+ "<video src='/PotHub/Video/Afraid_of_Technology.mp4' autoplay muted loop controls></video>"
				+ "</body>"
				+ "</html>"
		);
		out.close();
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
		Part filePart = request.getPart("HiMrTeo");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        byte[] thumbnailBytes = IOUtils.toByteArray(filePart.getInputStream());
        FileTableModel teo = new FileTableModel(0, fileName, thumbnailBytes);
        System.out.println(fileName + " " + thumbnailBytes + " " + filePart.getSize() + "GB");
        try {
			Database db = new Database(2);
			db.insertFileTable(teo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
