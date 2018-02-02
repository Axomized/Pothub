package admin;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import database.Database;
import database.model.ReportModel;

public class ReportToURL {
	public static String execute(ReportModel report) throws SQLException, FileNotFoundException, ClassNotFoundException, UnsupportedEncodingException{
		Database db = new Database(0);
		String build = "";
		if(report.getEvidenceType().equals("Event")){
			build += "EventOfEventPage/";
			build += db.getEventNameFromEventID(report.getEvidence());
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
}
