package model;

import java.sql.*;

public class User {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private int RUID;
	private int ridesTaken;
	private int ridesCompleted;
	private int rating;
	private int rank;
	private String reward;
	

	
	
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
		
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
			String retrieveRUID = "SELECT * FROM User WHERE username = '" + username +"';"; //retrieving the information by typing in the username
			//Run the query against the database.
			ResultSet result = stmt.executeQuery(retrieveRUID);
			
			//from the result of the query, update the fields of this instance of user with data from the DB
			while(result.next()){
				this.RUID = result.getInt("RUID");		
				this.firstName = result.getString("FirstName");
				this.lastName = result.getString("LastName");
			}
			
			//Uses RUID as a FK and find the user stats
			String retrieveUserData = "SELECT * FROM UserStats WHERE RUID = '" + RUID + "'";
			result = stmt.executeQuery(retrieveUserData);
		
			while(result.next()){
				this.ridesTaken = result.getInt("RidesTaken");	
				this.ridesCompleted = result.getInt("RidesCompleted");
				this.rating = result.getInt("Rating");
				this.rank = result.getInt("Rank");
				this.reward = result.getString("Rewards");
			}
			// ELLY'S PORTION
			/* String retrieveUserRequestRide = "SELECT * FROM RequestRide WHERE username = '" + username + "'";
			result = stmt.executeQuery(retrieveUserRequestRide);
			while(result.next()){
				this.Rdestination = result.getString("destination");	
				this.Rdeparture = result.getString("departure");
				this.Rlocation = result.getString("location");
				this.Rtime = result.getString("time");
				
			}
			String retrieveUserOfferRide = "SELECT * FROM OfferRide WHERE username = '" + username + "'";
			result = stmt.executeQuery(retrieveUserOfferRide);
			while(result.next()){
					this.Odestination = result.getString("destination");	
					this.Odeparture = result.getString("departure");
					this.Olocation = result.getString("location");
					this.Otime = result.getString("time");
					this.Oseats = result.getString("seats");
					this.Oadditionalinformation = result.getString("additionalinformation"); 
				} */
				
				
		
			con.close();

		} catch (Exception e) {
		}
		
	}
	/*public void RequestedRide(){
		try {

			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			String retrieveUserRequestRide = "SELECT * FROM RequestRide WHERE username = '" + username + "'";
			ResultSet result = stmt.executeQuery(retrieveUserRequestRide);
			while(result.next()){
				this.Rdestination = result.getString("destination");	
				this.Rdeparture = result.getString("departure");
				this.Rlocation = result.getString("location");
				this.Rtime = result.getString("time");
				
			}
			
		}catch (Exception e) {
		}
		
	}
	public void OfferedRide(){
		try {

			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			String retrieveUserOfferRide = "SELECT * FROM OfferRide WHERE username = '" + username + "'";
			ResultSet result = stmt.executeQuery(retrieveUserOfferRide);
			while(result.next()){
					this.Odestination = result.getString("destination");	
					this.Odeparture = result.getString("departure");
					this.Olocation = result.getString("location");
					this.Otime = result.getString("time");
					this.Oseats = result.getString("seats");
					this.Oadditionalinformation = result.getString("additionalinformation"); 
				}
		}catch (Exception e) {
		}
	} 
	public void RequestAvailableRides(){
		try {

			//Create a connection string
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			String MatchingDestinations = "SELECT O.username FROM OfferRide O, RequestRide R WHERE R.destination = O.destination";
			ResultSet matched = stmt.executeQuery(MatchingDestinations);
		//	int count = 0;
			while (matched.next()){
		//		count++;
				String matchedd = matched.getString("username");
				String OfferRideDeparture = "SELECT departure FROM OfferRide WHERE username = '" + matchedd + "'";
				String OfferRideDestination = "SELECT destination FROM OfferRide WHERE username = '" + matchedd + "'";
				String OfferRideTime = "SELECT time FROM OfferRide WHERE username = '" + matchedd + "'";
				String OfferRideSeats = "SELECT seats FROM OfferRide WHERE username = '" + matchedd + "'";
				String OfferRideLocation = "SELECT location FROM OfferRide WHERE username = '" + matchedd + "'";
				String OfferRideAdditionalinformation = "SELECT additionalinformation FROM OfferRide WHERE username = '" + matchedd + "'";
				
				
			
			}
			
			

		}catch (Exception e) {
		}
	} */
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRUID() {
		return RUID;
	}

	public void setRUID(int rUID) {
		RUID = rUID;
	}

	public int getRidesTaken() {
		return ridesTaken;
	}

	public void setRidesTaken(int ridesTaken) {
		this.ridesTaken = ridesTaken;
	}

	public int getRidesCompleted() {
		return ridesCompleted;
	}

	public void setRidesCompleted(int ridesCompleted) {
		this.ridesCompleted = ridesCompleted;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void requestRide(){
	}
	}

