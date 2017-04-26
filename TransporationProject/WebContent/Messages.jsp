<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.util.List" %>
	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>


<%
//System.out.println(username);
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

<h2> Write a Message:</h2>
<form method = "post" action = "SendMessageController"> 
	Username: <input type = "text" name = "username">
	<br>
	Message: <textarea name="message" cols="30" rows="10"></textarea>
	
<input type = "submit" value = "Send">	
</form>

<h2><font><strong>Your Inbox:</strong></font></h2>

<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>To</b></td>
<td><b>From</b></td>
<td><b>Message
</b></td>
</tr>


<%
try{ 
	HttpSession sesh = request.getSession();
	String username = (String) sesh.getAttribute("username");
	System.out.println(username);
connection = DriverManager.getConnection(connectionUrl, userId, password);
statement=connection.createStatement();
//User user = getAttribute("Username");
//String getUser = user.getUsername();
String sql ="SELECT * FROM MessageSystem WHERE Too = '" +  username + "'";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="#DEB887">
<td><%=resultSet.getString("Too") %></td>
<td><%=resultSet.getString("From") %></td>
<td><%=resultSet.getString("Message") %></td>



</tr>



<% 
}
resultSet = statement.executeQuery(sql);
%>







<%
}catch (Exception e) {
e.printStackTrace();
}
%>

</table>