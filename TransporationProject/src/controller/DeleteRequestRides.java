package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
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


@WebServlet("/DeleteRequestRides")
public class DeleteRequestRides extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public DeleteRequestRides() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean kek = false;
		String deleteusername = request.getParameter("username");
		
		if (request.getParameter("username") == null){
			kek = true;
		}
		System.out.println(deleteusername);
		try {
			
			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			String sql ="DELETE FROM RequestRide WHERE Username = '" + deleteusername + "'";
			//System.out.println("deleting the stupid username out of the table");
			stmt.executeUpdate(sql);
			if (kek == false){
			HttpSession userSession = request.getSession();
			String username = (String) userSession.getAttribute("username");
			String sql1 ="DELETE FROM OfferRide WHERE Username = '" + username + "'";
			stmt.executeUpdate(sql1);
			}
			
			
		}catch (Exception e) {
			return;
		}
		 if (kek){
			System.out.println("kekeke");
			rd = request.getRequestDispatcher("/NoUserContacted.jsp");
			rd.forward(request, response);
		} 
		 else{
			rd = request.getRequestDispatcher("/UserIsContacted.jsp");
			rd.forward(request, response);
		 }
		
		}
		
		
		
	}