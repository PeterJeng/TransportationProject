package model;

import java.sql.*;

public class Authenticator {
	
	//check to see that all the fields in createAccount is filled. If any of the REQUIRED filled is empty, return false.
	public static boolean validData(String fname, String lname, String username, String password, String confirmPassword, String RUID){
		if (fname.isEmpty()) {
			return false;
		} else if (lname.isEmpty()) {
			return false;
		} else if (username.isEmpty()) {
			return false;
		} else if (password.isEmpty()) {
			return false;
		} else if (confirmPassword.isEmpty()) {
			return false;
		} else if (RUID.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	//create a user account in the transportation project DB with the given values
	public String createAccount(String RUID, String username, String password, String confirmPassword,  String email, String address, String fname, String lname){
		try {

			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			
			boolean validData = validData(fname, lname, username, password, confirmPassword, RUID);
			
			if(validData == false){
				con.close();
				return "missing fields";
			}
			
			//first check if the query returned an empty set. In other words, a null row
			if(password.compareTo(confirmPassword) != 0){	//check to see password = confirmPassword
				con.close();
				return "password does not match";
			}
			else{
				//Make a SELECT query from the table specified by the 'username' parameter at the loginPage
				String checkUsername = "SELECT * FROM User WHERE username = '" + username +"'";
				//Run the query against the database.
				ResultSet result = stmt.executeQuery(checkUsername);
				//check to see if username is already in database, fail if it existed previously
				if(result.isBeforeFirst() == true){
					con.close();
					return "duplicate username";
				}
				else{
					//create the insert statement
					String insertNewAccount = "INSERT INTO transportationProject.User(RUID, Username, Password, Email, Address, FirstName, LastName)"+ 
											" VALUES ('" + RUID + "', '"+ username+ "', '" + password + "', '" + email + "', '" + address + "', '" + fname + "', '" + lname + "');"; 
					
					String insertNewStat = "INSERT INTO transportationProject.UserStats(RUID)" + " VALUES ('" + RUID + "');"; 
											
					//update database						
					stmt.executeUpdate(insertNewAccount);
					stmt.executeUpdate(insertNewStat);
					con.close();
					return "success";
				}
			}

			//close the connection.
			

		} catch (Exception e) {
			return "failed";
		}
		

	}
	
	//check the login information with information stored in the DB. Return error if information does not match
	public String loginAuthenticator(String username, String password){
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
			String str = "SELECT * FROM User WHERE username = '" + username + "' AND password = '" + password + "'";
			//Run the query on the database.
			ResultSet result = stmt.executeQuery(str);
			
			//first check if the query returned an empty set. In other words, a null row
			if (result.isBeforeFirst() == false) {
				con.close();
				return "fail";
			} else {
				//accessed database successfully and the account information existed in the database
				con.close();
				return "success";
			}


		} catch (Exception e) {
			return "fail";
		}
	}

	public String resetPasswordAuthenticator(String username, String RUID){
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
				con.close();
				return "success";
			}


		} catch (Exception e) {
			return "fail";
		}
		
	}
}
