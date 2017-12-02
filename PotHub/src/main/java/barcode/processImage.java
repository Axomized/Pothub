package barcode;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

@WebServlet("/processImage")
public class processImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public processImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			byte[] imageData = Base64.decodeBase64(sCurrentLine.substring(22));
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
			File outputfile = new File("C:/Users/Wei Xuan/Desktop/image.png");
			ImageIO.write(img, "png", outputfile);
			response.getWriter().append("True"); //If Barcode scanning is successful, Return true
		}
		
	}

}
