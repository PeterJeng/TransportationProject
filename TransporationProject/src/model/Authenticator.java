package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

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
	public static boolean validRequestRide(String username, String departure, String destination, String time, String location){
		if (departure.isEmpty()) {
			return false;
		} else if (destination.isEmpty()) {
			return false;
		} else if (time.isEmpty()) {
			return false;
		} else if (location.isEmpty()) {
			return false;
		}  else if (username.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	public static boolean validOfferRide(String username, String departure, String destination, String time, String seats, String location, String additionalinformation){
		System.out.println("checking boolean");
		if (departure.isEmpty()) {
			System.out.println("departure empty");
			return false;
		} else if (destination.isEmpty()) {
			System.out.println("dest empty");
			return false;
		} else if (time.isEmpty()) {
			System.out.println("time empty");
			return false;
		} else if (location.isEmpty()) {
			System.out.println("location empty");
			return false;
		}  else if (username.isEmpty()) {
			System.out.println("ruid empty");
			return false;
		}else if (seats.isEmpty()) {
			System.out.println("seats empty");
			return false;
		} else if (additionalinformation.isEmpty()) {
			System.out.println("add empty");
			return false;
		} 
		else {
			System.out.println("BOOLEAN WORKS");
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
			
			boolean validData = validData(fname, lname, username, password, confirmPassword, RUID); // validate data so that none of em is empty
			
			if(validData == false){ // disconnect from db and tell controller tha something's missing
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
				String checkUsername = "SELECT * FROM User WHERE username = '" + username +"'"; // check if existing user is in the database. if does, close connection
				//Run the query against the database.
				ResultSet result = stmt.executeQuery(checkUsername);
				//check to see if username is already in database, fail if it existed previously
				if(result.isBeforeFirst() == true){
					con.close();
					return "duplicate username";
				}
				else{
					String type = "User";
					//create the insert statement. inserts every single piece of information from the controller is put into the database
					String insertNewAccount = "INSERT INTO transportationProject.User(RUID, Username, Password, Email, Address, FirstName, LastName, Type)"+ 
											" VALUES ('" + RUID + "', '"+ username+ "', '" + password + "', '" + email + "', '" + address + "', '" + fname + "', '" + lname + "', '"+ type + "');"; 
					
					String insertNewStat = "INSERT INTO transportationProject.UserStats(RUID)" + " VALUES ('" + RUID + "');"; 
					//only need to insert ruid, so every single value should be 0. 						
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
	
	public String updateAccount(String username, String password, String confirmPassword,  String email, String address, String fname, String lname){
		try {

			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			
			String ruidQuery = "SELECT RUID FROM User WHERE Username = '" + username + "';";
			ResultSet ruidResult = stmt.executeQuery(ruidQuery);
			ruidResult.next();
			String RUID = ruidResult.getString("RUID");
			
			boolean validData = validData(fname, lname, username, password, confirmPassword, RUID); // validate data so that none of em is empty
			
			if(validData == false){ // disconnect from db and tell controller tha something's missing
				con.close();
				return "missing fields";
			}
			else if(password.compareTo(confirmPassword) != 0){
				con.close();
				return "password does not match";
			}
			else{
				String updateAccount = "UPDATE User SET Password = '" + password + "', Email = '" + email + "', Address = '" + address + "', FirstName = '" + fname + "', LastName = '" + lname + "' WHERE RUID = '" + RUID + "';";			
				stmt.executeUpdate(updateAccount);
				con.close();
				return "success";
			}
			

			//close the connection.
			

		} catch (Exception e) {
			return "failed";
		}
		

	}
	
	public String createStaffAccount(String RUID, String username, String password, String confirmPassword,  String email, String address, String fname, String lname){
		try {

			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			
			boolean validData = validData(fname, lname, username, password, confirmPassword, RUID); // validate data so that none of em is empty
			
			if(validData == false){ // disconnect from db and tell controller tha something's missing
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
				String checkUsername = "SELECT * FROM User WHERE Username = '" + username +"';"; // check if existing user is in the database. if does, close connection
				//Run the query against the database.
				ResultSet result = stmt.executeQuery(checkUsername);
				//check to see if username is already in database, fail if it existed previously
				if(result.isBeforeFirst() == true){
					con.close();
					return "duplicate username";
				}
				else{
					String type = "Staff";
					//create the insert statement. inserts every single piece of information from the controller is put into the database
					String insertNewAccount = "INSERT INTO transportationProject.User(RUID, Username, Password, Email, Address, FirstName, LastName, Type)"+ 
											" VALUES ('" + RUID + "', '"+ username+ "', '" + password + "', '" + email + "', '" + address + "', '" + fname + "', '" + lname + "', '"+ type + "');"; 
					//TEST
					System.out.println(insertNewAccount);
					//String insertNewStat = "INSERT INTO transportationProject.UserStats(RUID)" + " VALUES ('" + RUID + "');"; 
					//only need to insert ruid, so every single value should be 0. 						
					//update database						
					stmt.executeUpdate(insertNewAccount);
					System.out.println("TEST");
					//stmt.executeUpdate(insertNewStat);
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
			String str = "SELECT * FROM User WHERE Username = '" + username + "' AND Password = '" + password + "';";
			//Run the query on the database.
			ResultSet result = stmt.executeQuery(str);
			//first check if the query returned an empty set. In other words, a null row
			if (result.isBeforeFirst() == false) {
				con.close();
				return "fail";
			}
			else{
				//accessed database successfully and the account information existed in the database
				
				result.next();
				
				if(result.getBoolean("Locked") == false){
					String test = result.getString("Type");
					
					con.close();
					return test;
				}
				else return "Locked";
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

	public String requestridetable(String username, String departure, String destination, String time,  String location){
		try {

			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			
			boolean validDataRequest = validRequestRide(username, departure, destination, time, location); // validate data so that none of em is empty
			System.out.println(validDataRequest);
			if(validDataRequest == false){ // disconnect from db and tell controller tha something's missing
				con.close();
				return "missing fields";
			}
			
			//first check if the query returned an empty set. In other words, a null row
				//Make a SELECT query from the table specified by the 'username' parameter at the loginPage
				String checkUsername = "SELECT * FROM RequestRide WHERE username = '" + username +"'"; // check if existing user is in the database. if does, close connection
				//Run the query against the database.
				ResultSet result = stmt.executeQuery(checkUsername);
				//check to see if username is already in database, fail if it existed previously
				if(result.isBeforeFirst() == true){
					con.close();
					return "You can only request one ride!";
				}
				else{
					System.out.println("print this");
					//create the insert statement. inserts every single piece of information from the controller is put into the database
					String insertRequestRide = "INSERT INTO transportationProject.RequestRide(username, departure, location, time, destination)"+ 
											" VALUES ('" + username + "', '"+ departure + "', '" + location + "', '" + time + "', '" + destination + "');"; 
					//String insertNewAccount
					
					//String insertNewStat = "INSERT INTO transportationProject.UserStats(RUID)" + " VALUES ('" + RUID + "');"; 
					//only need to insert ruid, so every single value should be 0. 						
					//update database						
					stmt.executeUpdate(insertRequestRide);
				//	stmt.executeUpdate(insertNewStat);
					con.close();
					return "success";
				}
			

			//close the connection.
			

		} catch (Exception e) {
			return "failed";
		}
		

	}
	public String offerridetable(String username, String departure, String destination, String time,  String seats, String location, String additionalinformation){
		try {

			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			
			boolean validOfferRequest = validOfferRide(username, departure, destination, time, seats, location, additionalinformation); // validate data so that none of em is empty
			System.out.println(validOfferRequest);
			if(validOfferRequest == false){ // disconnect from db and tell controller tha something's missing
				con.close();
				return "missing fields";
			}
			
			//first check if the query returned an empty set. In other words, a null row
				//Make a SELECT query from the table specified by the 'username' parameter at the loginPage
				String checkUsername = "SELECT * FROM OfferRide WHERE username = '" + username +"'"; // check if existing user is in the database. if does, close connection
				//Run the query against the database.
				ResultSet result = stmt.executeQuery(checkUsername);
				//check to see if username is already in database, fail if it existed previously
				if(result.isBeforeFirst() == true){
					con.close();
					return "You can only offer one ride!";
				}
				else{
					System.out.println("print this");
					//create the insert statement. inserts every single piece of information from the controller is put into the database
					String insertOfferRide = "INSERT INTO transportationProject.OfferRide(username, departure, destination, time, seats, location, additionalinformation)"+ 
											" VALUES ('" + username + "', '"+ departure + "', '" + destination + "', '" + time + "', '" + seats + "', '" + location + "', '" + additionalinformation + "');"; 
					//String insertNewAccount
					
					//String insertNewStat = "INSERT INTO transportationProject.UserStats(RUID)" + " VALUES ('" + RUID + "');"; 
					//only need to insert ruid, so every single value should be 0. 						
					//update database						
					stmt.executeUpdate(insertOfferRide);
				//	stmt.executeUpdate(insertNewStat);
					con.close();
					
					return "success";
				}
			

			//close the connection.
			

		} catch (Exception e) {
			return "failed";
		}
		

	}
	
	
	
	public String userLockAuthenticator(String username, String comment){
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
			String str = "SELECT * FROM User WHERE username = '" + username + "';";
			//Run the query on the database.
			ResultSet result = stmt.executeQuery(str);
			
			//first check if the query returned an empty set. In other words, a null row
			if (result.isBeforeFirst() == false) {
				con.close();
				return "fail";
			} else {
				//accessed database successfully and the account information existed in the database
				
				//create the insert statement. inserts every single piece of information from the controller is put into the database
				String updateLockInfo = "UPDATE transportationProject.User"+ 
										" SET Locked = 1, Comments = '" + comment + "' WHERE Username = '" + username + "';";
										
				//update database						
				stmt.executeUpdate(updateLockInfo);
				
				con.close();
				return "success";
			}


		} catch (Exception e) {
			return "fail";
		}
		
	}
	
	public String userUnlockAuthenticator(String username, String comment){
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
			String str = "SELECT * FROM User WHERE username = '" + username + "';";
			//Run the query on the database.
			ResultSet result = stmt.executeQuery(str);
			
			//first check if the query returned an empty set. In other words, a null row
			if (result.isBeforeFirst() == false) {
				con.close();
				return "fail";
			} else {
				//accessed database successfully and the account information existed in the database
				
				//create the insert statement. inserts every single piece of information from the controller is put into the database
				String updateLockInfo = "UPDATE transportationProject.User"+ 
										" SET Locked = 0, Comments = '" + comment + "' WHERE Username = '" + username + "';";
										
				//update database						
				stmt.executeUpdate(updateLockInfo);
				
				con.close();
				return "success";
			}


		} catch (Exception e) {
			return "fail";
		}
		
	}
	
	public String manualPasswordResetAuthenticator(String username){
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
			String str = "SELECT * FROM User WHERE username = '" + username + "';";
			//Run the query on the database.
			ResultSet result = stmt.executeQuery(str);
			
			//first check if the query returned an empty set. In other words, a null row
			if (result.isBeforeFirst() == false) {
				con.close();
				return "fail";
			} else {
				//accessed database successfully and the account information existed in the database
				
				//create the insert statement. inserts every single piece of information from the controller is put into the database
				String updateLockInfo = "UPDATE transportationProject.User"+ 
										" SET Password = 'password' WHERE Username = '" + username + "';";
										
				//update database						
				stmt.executeUpdate(updateLockInfo);
				
				con.close();
				return "success";
			}


		} catch (Exception e) {
			return "fail";
		}
		
	}
	
	public String passwordResetAuthenticator(String username, String RUID){
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
			String str = "SELECT * FROM User WHERE Username = '" + username + "' AND RUID = " + RUID + ";";
			//Run the query on the database.
			ResultSet result = stmt.executeQuery(str);
			
			//first check if the query returned an empty set. In other words, a null row
			if (result.isBeforeFirst() == false) {
				con.close();
				return "fail";
			} else {
				//accessed database successfully and the account information existed in the database
				
				//create the insert statement. inserts every single piece of information from the controller is put into the database
				String updateLockInfo = "UPDATE transportationProject.User"+ 
										" SET Password = 'password' WHERE Username = '" + username + "';";
										
				//update database						
				stmt.executeUpdate(updateLockInfo);
				
				con.close();
				return "success";
			}


		} catch (Exception e) {
			return "fail";
		}
		
	}
		

}


