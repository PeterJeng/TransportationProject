package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResetPassword {
	
	public String resetPassword(String username, String RUID, String password){
		try {
			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();

			//Make a SELECT query from the table specified by the 'username' parameter at the loginPage
			String str = "SELECT * FROM User WHERE username = '" + username + "' AND RUID = '" + RUID + "'";
			//Run the query on the database.
			ResultSet result = stmt.executeQuery(str);
			
			//first check if the query returned an empty set. In other words, a null row
			if (result.isBeforeFirst() == false) {
				con.close();
				return "fail";
			} else {
				//accessed database successfully and the account information existed in the database
				String reset = "UPDATE User SET Password = '" + password + "' WHERE RUID = '" + RUID + "'";
				stmt.executeUpdate(reset);
				con.close();
				return "success";
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
