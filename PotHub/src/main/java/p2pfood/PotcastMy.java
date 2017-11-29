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
@WebServlet("/p2pmy")
public class PotcastMy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PotcastMy() {
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
+"<link rel='stylesheet' type='text/css' media='screen' href='css/p2pmy.css' />"
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
+"<div id='left-wrap'>"
  +"<img height=184 width =184 src='http://www.telegraph.co.uk/content/dam/news/2016/11/27/dump-tower-twitter_trans_NvBQzQNjv4BqeY8zn44CJx5co60z9sMGTUWjmulo7wva9c-kqRbE-Zc.jpg'>"
  +"<p>Having a good time at YOUR CURRENT ESTIMATED ADDRESS? Would food make it better?</p>"
  +"<p>Go get something to eat!</p>"
+"</div>"
+"<div id='wrapper'>"
+"<div id='secondHeader'>"
+"<h2>My Potcasts</h2>"
+"<div id='searchBar'</div>"
+"<p>Search Titles: </p>"
+"<input type='text'></input>"
+"<p>Sort Results By: </p>"
+"<div id='search'>"
+"<div id='radios'>"
+"<p><input type='radio' name='search'></input>  Recent</p>"
+"<p><input type='radio' name='search'></input>  Active Bids</p>"
+"<p><input type='radio' name='search'></input>  Bid Closing Time</p>"
+"<p><input type='radio' name='search'></input>  Pickup Time</p>"
+"<p><input type='radio' name='search'></input>  Address</p>"
+"</div>"
+"</div>"
+"</div>"
+"<div id='displayUnit' class='active'>"
+"<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
+"<div id='column1'>"
+"<div class='row1 foodTitle'>Curry Shrimp</div>"
+"<div class='row1'>Big Matt, 4460CR</div>"
+"</div>"
+"<div id='column2'>"
+"<div class='row2'>6/6 Bids, 5pm 1-11-2018</div>"
+"<div class='row2'>$12</div>"
+"</div>"
+"<div id='column2'>"
+"<div class='row2'>7.30pm</div>"
+"<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>"
+"</div>"
+"</div>"
+"<div id='displayUnit' class='inactive'>"
	+"<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
+"<div id='column1'>"
	+"<div class='row1 foodTitle'>Good Shrimp</div>"
	+"<div class='row1'>Big Matt, 2370CR</div>"
+"</div>"
+"<div id='column2'>"
	+"<div class='row2'>6/6 Bids, 5pm 1-10-2018</div>"
	+"<div class='row2'>$12</div>"
+"</div>"
+"<div id='column2'>"
	+"<div class='row2'>7.30pm</div>"
	+"<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>"
+"</div>"
+"</div>"

+"<div id='displayUnit' class='inactive'>"
+"<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
+"<div id='column1'>"
+"<div class='row1 foodTitle'>Bad Time Shrimp</div>"
+"<div class='row1'>Big Matt, 3060CR</div>"
+"</div>"
+"<div id='column2'>"
+"<div class='row2'>6/6 Bids, 5pm 1-9-2018</div>"
+"<div class='row2'>$12</div>"
+"</div>"
+"<div id='column2'>"
+"<div class='row2'>7.30pm</div>"
+"<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>"
+"</div>"
+"</div>"
+"<div id='displayUnit' class='inactive'>"
+"<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
+"<div id='column1'>"
+"<div class='row1 foodTitle'>7 Shots of Vodka Shrimp</div>"
+"<div class='row1'>Big Matt, 4060CR</div>"
+"</div>"
+"<div id='column2'>"
+"<div class='row2'>6/6 Bids, 5pm 1-8-2018</div>"
+"<div class='row2'>$12</div>"
+"</div>"
+"<div id='column2'>"
+"<div class='row2'>7.30pm</div>"
+"<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>"
+"</div>"
+"</div>"
+"<div id='displayUnit' class='inactive'>"
+"<div id='thumbnailBox'><img height=100 width =100 src='images/crab.gif'></div>"
+"<div id='column1'>"
+"<div class='row1 foodTitle'>Amazing Shrimp</div>"
+"<div class='row1'>Big Matt, 2460CR</div>"
+"</div>"
+"<div id='column2'>"
+"<div class='row2'>6/6 Bids, 5pm 1-7-2018</div>"
+"<div class='row2'>$12</div>"
+"</div>"
+"<div id='column2'>"
+"<div class='row2'>7.30pm</div>"
+"<div class='row3'>123 CookingIsFunRoad #12-34, 465423</div>"
+"</div>"
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
