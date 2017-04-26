package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

@WebServlet("/RatingController")
public class RatingController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
	public RatingController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		int rating = Integer.parseInt(request.getParameter("rating"));
		
		RequestDispatcher rd = null;
		try{
			String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
			//Load JDBC driver - the interface standardizing the connection procedure. Look at WEB-INF\lib for a mysql connector jar file, otherwise it fails.
			Class.forName("com.mysql.jdbc.Driver");

			//Create a connection to your DB
			Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

			//Create a SQL statement
			Statement stmt = con.createStatement();
			HttpSession userID = request.getSession();
			String userRUID = (String) userID.getAttribute("KeepTrackOfOffer");
		//	String sql ="Update UserStats Set Rating = Rating + 1 WHERE RUID = '" + userRUID + "'";
			String insert = "INSERT INTO RatingAvg (RUID, Rating) VALUES ('"+ userRUID + "', " + rating + ")";
			
		//	System.out.println("deleting the stupid username out of the table");
			stmt.executeUpdate(insert);
			String getAvg = "SELECT avg(Rating) FROM RatingAvg WHERE RUID = '" + userRUID + "'";
			ResultSet avg  = stmt.executeQuery(getAvg);
			int avgRating = 0;
			while (avg.next()){
				
				avgRating = avg.getInt("avg(Rating)");
			}
			 System.out.println("after getting the average");
			String addAvg = "UPDATE UserStats SET Rating = " + avgRating + " WHERE RUID = '" + userRUID + "'";
			System.out.println("line 61");
			stmt.executeUpdate(addAvg);
			System.out.println("line 62");
			//Statement stmt = con.createStatement(
			//         ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		//	String update = "SET @rank=0; SELECT @rank:=@rank+1 AS rank, RUID, Rating FROM UserStats ORDER BY Rating DESC";
			String update = "SELECT RUID, Rating, FIND_IN_SET( Rating, (SELECT GROUP_CONCAT( Rating ORDER BY Rating DESC ) FROM UserStats )) AS rank FROM UserStats";
		//	System.out.println("line 63");
			ResultSet resultUpdate = stmt.executeQuery(update);
		//	System.out.println("line 64");
			int updateRank = 0;
			String userRUIDD = "";
			//String updatee = "";
		//	 System.out.println("line 66");
			 Statement stmtt = con.createStatement();
			while(resultUpdate.next()){
			//	System.out.println("rolling");
			//	 System.out.println("stuckhere");
				updateRank = resultUpdate.getInt("rank");
			//	System.out.println("rank is " + updateRank);
				userRUIDD = resultUpdate.getString("RUID");
			//	System.out.println("RUID is " + userRUIDD);
				
				String updatee = "UPDATE UserStats SET Rank = " + updateRank + " WHERE RUID = '" + userRUIDD + "'";
				 stmtt.executeUpdate(updatee); 
				
			}
		
		
			rd = request.getRequestDispatcher("/ThankYouPage.jsp");
			rd.forward(request, response);
			
		
		//	System.out.println("failed to login");
		
		
		}catch (Exception e) {
			return;
		}
		
	}
}