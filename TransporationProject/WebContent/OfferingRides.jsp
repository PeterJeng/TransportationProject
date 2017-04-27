<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
String destinationn = request.getParameter("destination");
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://transportationproject.c7dtxm2i40gp.us-east-1.rds.amazonaws.com:3306/transportationProject";
//String dbName = "transportationProject";
String userId = "peterEleseRandy";
String password = "xd123cs336";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Rides that You Can Offer To</strong></font></h2>

<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>Username</b></td>
<td><b>Departure</b></td>
<td><b>Location</b></td>
<td><b>Time</b></td>
<td><b>Destination</b></td>
</tr>

<%
try{ 
connection = DriverManager.getConnection(connectionUrl, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM RequestRide WHERE destination = '" + destinationn + "'";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="#DEB887">

<td><%=resultSet.getString("username") %></td>
<td><%=resultSet.getString("departure") %></td>
<td><%=resultSet.getString("location") %></td>
<td><%=resultSet.getString("time") %></td>
<td><%=resultSet.getString("destination") %></td>


</tr>



<% 
}
resultSet = statement.executeQuery(sql);
%>
<center>
    <h1> Please select which user you want to contact:</h1>
    <form method = "post" action = "DeleteRequestRides">
        <select name = "username">
        <%while(resultSet.next()){ %>
            <option><%= resultSet.getString("username")%></option>
        <% } %>
        </select>
        <input type="submit" value="Submit">
        </form>
</center>






<%
}catch (Exception e) {
e.printStackTrace();
}
%>

</table>

<br>
<a href="AccountMainPage.jsp">Return to User Main Page</a><br>