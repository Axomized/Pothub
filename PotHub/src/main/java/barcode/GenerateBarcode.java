package barcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

public class GenerateBarcode {

	public static void main(String[] args) throws FileNotFoundException, IOException, WriterException {
		int width = 400;
		int height = 100;
		 
		BitMatrix bitMatrix = new Code128Writer().encode("Hello Mr Ang", BarcodeFormat.CODE_128,width,height,null);
		MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File(System.getProperty("user.home") + "\\Documents\\GitHub\\PotHub\\PotHub\\src\\main\\webapp\\images\\generatedBarcode.png")));
 }

}
