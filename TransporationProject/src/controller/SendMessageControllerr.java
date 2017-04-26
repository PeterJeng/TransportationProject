package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Authenticator;
import model.User;

import sun.text.normalizer.ICUBinary.Authenticate;


@WebServlet("/SendMessageControllerr")
public class SendMessageControllerr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public SendMessageControllerr() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String getusername = request.getParameter("username");
		String message = request.getParameter("message");
		HttpSession getthename = request.getSession();
		String yourusername = (String) getthename.getAttribute("username");
		try {
			
			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			String sql ="INSERT MessageSystem VALUES ('" + getusername + "', '" + yourusername + "', '" + message + "')";
			//System.out.println("deleting the stupid username out of the table");
			stmt.executeUpdate(sql);
			
		

		//Authenticator authenticator = new Authenticator();
		//String result = 
			
			
			
			
		//authenticator.offerridetable(username, departure, destination, time, seats, location, additionalinformation);
		
		} catch (Exception e) {
			return;
		}
		response.sendRedirect("UserIsContacted.jsp");
	}
}