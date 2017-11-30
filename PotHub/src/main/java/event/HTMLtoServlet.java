package event;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HTMLtoServlet {
	public static void main(String[] args) throws IOException {
		String s = "";
		FileReader Hi = new FileReader("src/main/webapp/html/Forum.html"); // Replace
																			// this
																			// and
																			// you
																			// are
																			// good
																			// to
																			// go
		BufferedReader in = new BufferedReader(Hi);

		String str;
		boolean Hi2 = true;
		while ((str = in.readLine()) != null) {
			str = str.replaceAll("\"", "\'");
			if (Hi2) {
				s += "\"" + str + "\"\n";
				Hi2 = false;
			} else {
				s += "+ \"" + str + "\"\n";
			}
		}
		s = s.replaceAll("href=\'../css", "href=\'css");
		s = s.replaceAll("src=\'../script", "src=\'script");
		s = s.replaceAll("src=\'../images", "src=\'images");
		in.close();

		StringSelection selection = new StringSelection(s);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);

		System.out.println("String copied to clipboard.");
	}

}
