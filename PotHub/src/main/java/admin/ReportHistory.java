package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adminSearch.ReportSearchObject;
import database.Database;
import database.model.ReportModel;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/HistoryAdminReports")
public class ReportHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("user")==null){
    		response.sendRedirect("AdminLogin");
    		return;
		}
		
		PrintWriter pw = response.getWriter();
		ReportSearchObject rso = new ReportSearchObject();
		String subjectUser = "user";
		if(request.getParameter("user") != null){
			subjectUser=request.getParameter("user");
			rso.setiGNReceive(subjectUser);
		}
		if(request.getParameter("dateIn1")!=null && request.getParameter("dateIn1").length()>0){
			rso.setDateInOpen(Date.valueOf(request.getParameter("dateIn1")));
		}
		if(request.getParameter("dateIn2")!=null && request.getParameter("dateIn2").length()>0){
			rso.setDateInClose(Date.valueOf(request.getParameter("dateIn2")));
		}
		if(request.getParameter("verdict")!=null){
			rso.setGuiltyOrNot(Integer.parseInt(request.getParameter("verdict")));
		}
		if(request.getParameter("evidenceType")!=null && request.getParameter("evidenceType").length()>0){
			rso.setEvidenceType(request.getParameter("evidenceType"));
		}
		pw.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
+"<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>"
+"<head>"
+ "<!-- Favicon -->"
+ "<link rel='icon' href='images/crab.gif' type='image/gif'>"
+ "<link rel='icon' href='images/crab.png' type='image/x-icon'>"
+"<title>"+subjectUser+"'s Reports</title>"
+"<meta http-equiv='content-language' content='en-us' />"
+"<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
+"<link rel='stylesheet' type='text/css' media='screen' href='css/banscreen.css' />"
+"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>"
+"<script src='//cdnjs.cloudflare.com/ajax/libs/tether/1.3.1/js/tether.min.js'></script>"
+"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css' integrity='sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ' crossorigin='anonymous'>"
+"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js' integrity='sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn' crossorigin='anonymous'></script>"
+"<script src='script/jquery.tablesorter.min.js'></script>"
+"</head>"
+"<body>"
+"<div id='header'>"
  +"<h1>Admin Panel</h1>"
+"</div>"
+ "<div id='navigation'>"
+"<ul>"
+"<li>"+"<a href='AdminGeneral'>General</a>"+"</li>"
+"<li>"+"<a href='AdminBans'>Bans & Appeals</a>"+"</li>"
+"<li>"+"<a href='AdminDonations'>Donations</a>"+"</li>"
+"<li>"+"<a href='AdminRanks'>Forum Control</a>"+"</li>"
+"<li>"+"<a href='AdminReports'>Reports</a>"+"</li>"
+"</ul>"
+"<p id='logout'><a href='Logout'>Logout</a></p>"
+ "</div>"
+"<div id='wrapper'>"
  +"<div id='content-wrapper'>"
    + "<div id='tableWrapper'>"
    +"<table class='table table-striped'>"
    +"<thead>"
        +"<tr>"
            +"<th>Reporter</th>"
            +"<th>Type</th>"
            +"<th>Reason</th>"
            +"<th>Date In</th>"
            +"<th>Verdict</th>"
            +"<th>&nbsp;</th>"
        +"</tr>"
    +"</thead>"
    +"<tbody>");
            
		Database db;
		ArrayList<ReportModel> reports = new ArrayList<ReportModel>();
		try {
			db = new Database(0);
			reports = db.getManyReports(rso);
			
			for(ReportModel rep:reports){
				pw.append("<tr>");
				pw.append("<td>"+rep.getiGNSend()+"</td>");
				pw.append("<td>"+rep.getEvidenceType()+"</td>");
				pw.append("<td>"+rep.getReason()+"</td>");
				pw.append("<td>"+rep.getDate()+"</td>");
				if(rep.isGuiltyOrNot()==0){
					pw.append("<td>Undecided</td>");
				}
				else if(rep.isGuiltyOrNot()==1){
					pw.append("<td>Innocent</td>");
				}
				else{
					pw.append("<td>Guilty</td>");
				}
				
				if(rep.isGuiltyOrNot()==0||rep.isGuiltyOrNot()==1){
					pw.append("<td><form method='post'><input type='hidden' name='whatDo' value='convict'/><input type='hidden' name='whoDo' value='"+subjectUser+"'/><input type='hidden' name='reportID' value='"+rep.getReportID()+"'></input><button type='submit'>Convict</button></form>");
				}
				if(rep.isGuiltyOrNot()==2){
					pw.append("<td><form method='post'><input type='hidden' name='whatDo' value='pardon'/><input type='hidden' name='whoDo' value='"+subjectUser+"'/><input type='hidden' name='reportID' value='"+rep.getReportID()+"'/><button type='submit'>Pardon</button></form>");
				}
				
				pw.append("</td>");
				pw.append("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		if(reports.size()<10){
			for(int i = 0; i < (10-reports.size());i++){
			pw.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			}
		}
		if(reports.size()==0){
			for(int i = 0; i < 10;i++){
				pw.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
				}
		}
		
pw.append("</tbody>"
+"</table>"
+"</div>"
+"<form>"
   +"<div id='fourbox'>"
	+"<div id='search'>"
	+"<p>Search Reporter: <div id='textboxes'><input type='text' name='search'>"+"</input></div></p>"
	+"</div>"
	+ "<div id='search'>"
	+"<p>Date in between: <div id='textboxes'><input type='date' name='dateIn1'></input></div></p>"
	+"<p>And <div id='textboxes'><input type='date' name='dateIn2'></input></div></p>"
	+"</div>"
   +"</div>"
   +"<div id='fourbox'>"
	+"<div id='search'>"
	+"<div id='radios'>"
	+"<p>Verdict: </p>" 
	+"<input type='radio' name='verdict' value='1'></input>  Innocent"
	+"<input type='radio' name='verdict' value='2'></input>  Convicted"
	+"<input type='radio' name='verdict' value='0'></input>  Undecided"
	+"</div>"
	+"</div>"
	+"<div id='search'>"
	+"<p>Evidence type: <select>"
   	+"<option>Comment</option>"
   	+"<option>Message</option>"
   	+"<option>Forum Post</option>"
   	+"<option>Potcast</option>"
   	+"</select>"
   	+"</div></p>"
	+"</div>"
	   +"<div id='fourbox'>"
	   +"<div id='search'>"
	   +"<button id='searchButton' type='submit'>Search</button>"
	   +"</div>"
	   +"</div>"
   +"</div>"
   +"</div>"
   +"</form>"
+"<div id='footer'>"
  +"<p>Copyright &copy; 2017 &ndash; 2018 PotHub. All rights reserved. </p>"
  +"<p>We like food</p>"
  +"<p>" +"<a href='#'>Terms of Service</a> | <a href='#'>Privacy</a> | <a href='#'>Support</a>"+"</p>"
+"</div>"
+"</body>"
+"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Database db = new Database(2);
			if(request.getParameter("whatDo").equals("pardon")){
				db.pardonReport(Integer.parseInt(request.getParameter("reportID")));
			}
			if(request.getParameter("whatDo").equals("convict")){
				db.convictUser(false, Integer.parseInt(request.getParameter("reportID")), "Admin");
			}

			response.sendRedirect("HistoryAdminReports?user="+request.getParameter("whoDo"));	
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
