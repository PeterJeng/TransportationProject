package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Advertisement {

        public String saveAdToDatabase(String photoName, InputStream inputStream){
            
            try {

            // Create a connection string
            String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
            // Load JDBC driver - the interface standardizing the connection
            // procedure. Look at WEB-INF\lib for a mysql connector jar file,
            // otherwise it fails.
            Class.forName("com.mysql.jdbc.Driver");

            // Create a connection to your DB
            Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");

            // Create a SQL statement
            Statement stmt = con.createStatement();
            
            //change all values of onDisplay to 0, to indicate ads that are not displayed.
            String turnOffSafeUpdate = "SET SQL_SAFE_UPDATES = 0;";
            stmt.execute(turnOffSafeUpdate);
            String changeDisplayValue = "UPDATE Advertisement SET onDisplay = 0 WHERE onDisplay = 1; ";
            stmt.executeUpdate(changeDisplayValue);
            String turnOnSafeUpdate =  "SET SQL_SAFE_UPDATES = 1;";
            stmt.execute(turnOnSafeUpdate);
            
            String sql = "INSERT INTO transportationProject.Advertisement (photoName, photo, onDisplay) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);

            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setString(1, photoName);
                statement.setBlob(2, inputStream);
                statement.setInt(3, 1);
            }

            // sends the statement to the database server
            int row = statement.executeUpdate();
            
            
            
            //row indicates number of rows inserted into table
            if (row > 0) {
                con.close();
                return "success";
            } else {
                con.close();
                return "fail";
            }

        } catch (Exception e) {
            return "failed";
        }
            
        }
        
        public String deleteAdFromDatabase(String photoName){

            try {

            // Create a connection string
            String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
            // Load JDBC driver - the interface standardizing the connection
            // procedure. Look at WEB-INF\lib for a mysql connector jar file,
            // otherwise it fails.
            Class.forName("com.mysql.jdbc.Driver");

            // Create a connection to your DB
            Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");
            
            Statement stmt = con.createStatement();

            
            String checkPhoto = "SELECT * FROM Advertisement WHERE photoName = '" + photoName +"';"; 
            //Run the query against the database.
            ResultSet result = stmt.executeQuery(checkPhoto);
            //check to see if photo is already in database, fail if it does not
            
            if(result.isBeforeFirst() == true){
                String turnOffSafeUpdate = "SET SQL_SAFE_UPDATES = 0;";
                stmt.execute(turnOffSafeUpdate);
                String sql = "DELETE FROM Advertisement WHERE photoName = '" + photoName + "'";
                stmt.executeUpdate(sql);
                String turnOnSafeUpdate =  "SET SQL_SAFE_UPDATES = 1;";
                stmt.execute(turnOnSafeUpdate);
                
                con.close();
                return "success";
            }
            else{
                con.close();
                return "photo DNE";
            }
            

            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        }
            
        public String selectAdToDisplay(String photoName){
            try {

                // Create a connection string
                String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
                // Load JDBC driver - the interface standardizing the connection
                // procedure. Look at WEB-INF\lib for a mysql connector jar file,
                // otherwise it fails.
                Class.forName("com.mysql.jdbc.Driver");

                // Create a connection to your DB
                Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");
                
                Statement stmt = con.createStatement();

                
                String checkPhoto = "SELECT * FROM Advertisement WHERE photoName = '" + photoName +"';"; 
                //Run the query against the database.
                ResultSet result = stmt.executeQuery(checkPhoto);
                //check to see if photo is already in database, fail if it does not
                
                if(result.isBeforeFirst() == true){
                    String turnOffSafeUpdate = "SET SQL_SAFE_UPDATES = 0;";
                    stmt.execute(turnOffSafeUpdate);
                    String changeDisplayValue = "UPDATE Advertisement SET onDisplay = 1 WHERE photoName = '" + photoName + "'" ;
                    stmt.executeUpdate(changeDisplayValue);
                    String turnOnSafeUpdate =  "SET SQL_SAFE_UPDATES = 1;";
                    stmt.execute(turnOnSafeUpdate);
                    
                    con.close();
                    return "success";
                }
                else{
                    con.close();
                    return "photo DNE";
                }
                

                } catch (Exception e) {
                    e.printStackTrace();
                    return "error";
                }
        }
            
        public byte[] displayAd(){
            try {

                // Create a connection string
                String url = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
                // Load JDBC driver - the interface standardizing the connection
                // procedure. Look at WEB-INF\lib for a mysql connector jar file,
                // otherwise it fails.
                Class.forName("com.mysql.jdbc.Driver");

                // Create a connection to your DB
                Connection con = DriverManager.getConnection(url, "peterEleseRandy", "xd123cs336");
                
                Statement stmt = con.createStatement();

                
                String retrievePhoto = "SELECT * FROM Advertisement WHERE onDisplay = '1';"; 
                //Run the query against the database.
                ResultSet result = stmt.executeQuery(retrievePhoto);
                //check to see if photo is already in database, fail if it does not
                
                if(result.isBeforeFirst() == true){
                    con.close();
                    return result.getBytes("photo");
                }
                else{
                    con.close();
                    return null;
                }
                

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
        }
            
}