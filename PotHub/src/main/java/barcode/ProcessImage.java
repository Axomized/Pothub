package barcode;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class ProcessImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ProcessImage() {
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
			
			try {
				LuminanceSource source = new BufferedImageLuminanceSource(img);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Reader reader = new MultiFormatReader();
				Result result = reader.decode(bitmap);
				
				response.getWriter().append("True," + result.getText());
			} catch (NotFoundException e) {
				response.getWriter().append("False");
			} catch (ChecksumException e) {
				e.printStackTrace();
			} catch (FormatException e) {
				e.printStackTrace();
			}finally{
				br.close();
			}

			File outputfile = new File(System.getProperty("user.home") + "\\Documents\\GitHub\\PotHub\\PotHub\\src\\main\\webapp\\images\\image.png");
			ImageIO.write(img, "png", outputfile);
		}
		
	}

}
