package admin;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import database.Database;
import database.model.ReportModel;

public class ReportToURL {
	public static String execute(ReportModel report) throws SQLException, FileNotFoundException, ClassNotFoundException, UnsupportedEncodingException{
		Database db = new Database(0);
		String build = "";
		if(report.getEvidenceType().equals("Event")){
			build += "EventOfEventPage/";
			build += encodeString(db.getEventNameFromEventID(report.getEvidence()));
		}
		else if(report.getEvidenceType().equals("Forum")){
			build += "discussion?ForumPostID=";
			build += report.getEvidence();
		}
		else if(report.getEvidenceType().equals("Potcast")){
			build += "p2pdetail?potcastID=";
			build += report.getEvidence();
		}
		
		return build;
	}
	
	private static String encodeString(String line) throws UnsupportedEncodingException {
		return URLEncoder.encode(line, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
	}
}
