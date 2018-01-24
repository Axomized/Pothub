package barcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

public class GenerateBarcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final int WIDTH = 1200;
			final int HEIGHT = 400;
			String username = "";
			final HttpSession SESSION = request.getSession(false);
	        if (SESSION != null) {
	            username = (String) SESSION.getAttribute("username");
	        }
	        else {
	            response.sendRedirect("Login");
	        }
			
			File file = File.createTempFile("Hello", ".tmp");
			final FileOutputStream FOS = new FileOutputStream(file);
		   
		    BitMatrix bitMatrix = new Code128Writer().encode(username, BarcodeFormat.CODE_128, WIDTH, HEIGHT, null);
		    MatrixToImageWriter.writeToStream(bitMatrix, "png", FOS);
		    
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
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
}
