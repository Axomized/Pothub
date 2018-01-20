package event;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsMinify {
	public static void main(String[] args) throws IOException {
		String fileName = "MyEventPage"; //Replace file name
		FileReader reader = new FileReader("src/main/webapp/script/" + fileName + ".js");
		
		BufferedReader in = new BufferedReader(reader);
		
		//Reading file
		String s = "";
		String str;
		while ((str = in.readLine()) != null) {
			s += str.trim();
		}
		s = s.replaceAll("\\s+", "");
		in.close(); //Close BufferedReader
		reader.close(); //Close FileReader
		
		//Create file if not existing
		File file = new File("src/main/webapp/script/" + fileName + ".min.js");
		file.createNewFile();
		
		//Write to File
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(s);
	    writer.close();
	}

}
