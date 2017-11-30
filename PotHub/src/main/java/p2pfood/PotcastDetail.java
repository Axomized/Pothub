package p2pfood;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Forum
 */
@WebServlet("/p2pdetail")
public class PotcastDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PotcastDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
+"<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en' lang='en'>"
+"<head>"
+"<title>My Potcasts</title>"
+"<meta http-equiv='content-language' content='en-us' />"
+"<meta http-equiv='content-type' content='text/html; charset=utf-8' />"
+"<link rel='stylesheet' type='text/css' media='screen' href='css/p2pdetail.css' />"
+"</head>"
+"<body id='babout'>"
+"<div id='header'>"
+"<a href='adminpanel.html'>"
+"<h1>PotHub</h1>"
+"</a>"
+"</div>"
+"<div id='navigation'>"
  +"<ul>"
    +"<li>"+"<a href='p2plist'>Active Potcasts</a>"+"</li>"
    +"<li>"+"<a href='p2preg'>Start a Potcast</a>"+"</li>"
    +"<li>"+"<a href='p2pmy'>My Potcasts</a>"+"</li>"
    +"<li>"+"<a href='p2pjoined'>Joined Potcasts</a>"+"</li>"
  +"</ul>"
+"</div>"
+"<div id='wrapper'>"
+"<div id='foodAndMap'>"
+"<img height=400 width =400 src='http://www.seriouseats.com/recipes/assets_c/2015/05/Anova-Steak-Guide-Sous-Vide-Photos15-beauty-thumb-1500xauto-423558.jpg' id='foodPicture'>"
+"<iframe src='//www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3988.6633501112483!2d103.84712935026964!3d1.3786592618614608!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31da16eb64b0249d%3A0xe5f10ff680eed942!2sNanyang+Polytechnic!5e0!3m2!1sen!2sus!4v1512025708851"
+"&zoom=17"
+"&key=AIzaSyDmftQ7JHdzj22y3wlP01IH_LlTgFQ3JOE' id='foodPicture'>"
+"</iframe>"
+"</div>"
+"<div id='foodText'>"
+"<div id='foodTitle'>"
+"<p>Chunk of Beef, Split 11 Ways</p>"
+"</div>"
+"<div id='foodPrice'>"
+"<p>$8</p>"
+"</div>"
+"<div id='foodDesc'>"
+"<p>Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef Beef </p>"
+"</div>"
+"<div id='foodBits'>"
+"<p>9/11 Bids, closing 5pm 1-1-2018</p>"
+"</div>"
+"<div id='foodBits'>"
+"<p>Pickup 7.30pm</p>"
+"</div>"
+"<div id='foodDesc'>"
+"<p>123 Road Street #09-23, Singapore 445532</p>"
+"</div>"
+"<div id='foodBuyers'>"
+"<p>Buyers: </p>"
+"<ul>"
+"<li>Bob1</li>"
+"<li>Bob2</li>"
+"<li>Bob3</li>"
+"<li>Bob4</li>"
+"<li>Bob5</li>"
+"<li>Bob6</li>"
+"<li>Bob7</li>"
+"<li>Bob8</li>"
+"<li>Bob9</li>"
+ "</ul>"
+"</div>"
+"<div id='foodDesc'>"
+"<input type='number' id='bidNumberBox'></input>"
+"<button>Bid!</button>"
+"</div>"
+"</div>"
+"</div>"
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
