package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AvailableRides {
	private String Rdeparture;
	private String Rlocation;
	private String Rtime;
	private String Rdestination;
	
	private String Odeparture;
	private String Olocation;
	private String Otime;
	private String Odestination;
	private String Oseats;
	private String Oadditionalinformation;
	private Boolean matchingdestination = false;
	
	public AvailableRides(){
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
				String matchedd = matched.getString("username"); // get username
				String OfferRideDeparture = "SELECT departure FROM OfferRide WHERE username = '" + matchedd + "'";
				ResultSet matched0 = stmt.executeQuery(OfferRideDeparture);
				String n1 = matched0.getString("departure");
				String OfferRideDestination = "SELECT destination FROM OfferRide WHERE username = '" + matchedd + "'";
				ResultSet matched1 = stmt.executeQuery(OfferRideDestination);
				String n2 = matched1.getString("destination");
				String OfferRideTime = "SELECT time FROM OfferRide WHERE username = '" + matchedd + "'";
				ResultSet matched2 = stmt.executeQuery(OfferRideTime);
				String n3 = matched2.getString("time");
				String OfferRideSeats = "SELECT seats FROM OfferRide WHERE username = '" + matchedd + "'";
				ResultSet matched3 = stmt.executeQuery(OfferRideSeats);
				String n4 = matched3.getString("seats");
				String OfferRideLocation = "SELECT location FROM OfferRide WHERE username = '" + matchedd + "'";
				ResultSet matched4 = stmt.executeQuery(OfferRideLocation);
				String n5 = matched4.getString("location");
				String OfferRideAdditionalinformation = "SELECT additionalinformation FROM OfferRide WHERE username = '" + matchedd + "'";
				ResultSet matched5 = stmt.executeQuery(OfferRideAdditionalinformation);
				String n6 = matched5.getString("additionalinformation");
				
				OfferRideInformation(matchedd, n1, n2, n3, n4, n5, n6);
				
				
			
			}
			
			

		}catch (Exception e) {
		}
}
	public void OfferRideInformation(String username, String departure, String destination, String time, String seats, String location, String additionalinformation){
		getUsername(username);
		getDeparture(departure);
		getDestination(destination);
		getTime(time);
		getSeats(seats);
		getLocation(location);
		getAdditionalinformation(additionalinformation);
	}
	public String getUsername(String username){
		return username;
	}
	public String getDeparture(String departure){
		return departure;
	}
	public String getDestination(String destination){
		return destination;
	}
	public String getTime(String time){
		return time;
	}
	public String getSeats(String seats){
		return seats;
	}
	public String getLocation(String location){
		return location;
	}
	public String getAdditionalinformation(String additionalinformation){
		return additionalinformation;
	}
	
}
