package model;

public class User {
	private String username;
	private String password;
	private int RUID;
	private int ridesTaken;
	private int ridesCompleted;
	private int rating;
	private int Rank;
	private String Reward;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
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
		return Rank;
	}

	public void setRank(int rank) {
		Rank = rank;
	}

	public String getReward() {
		return Reward;
	}

	public void setReward(String reward) {
		Reward = reward;
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
}
