<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stats</title>
</head>
<body>

<table border = "1" cellpadding = "5" width = "300">
	<tr>
		<td>RUID:</td>
		<td>${user.getRUID()}</td>
	</tr> 
	<tr>
		<td>Rides Taken:</td>
		<td>${user.getRidesTaken()}</td>
	</tr> 
	<tr>
		<td>Rides Completed:</td>
		<td>${user.getRidesCompleted()}</td>
	</tr>
	<tr>
		<td>Rating:</td>
		<td>${user.getRating()}</td>
	</tr> 
	<tr>
		<td>Rank:</td>
		<td>${user.getRank()}</td>
	</tr>
	<tr>
		<td>Rewards:</td>
		<td>${user.getReward()}</td>
	</tr>
</table>

<br>
<a href="AccountMainPage.jsp">Return to User Main Page</a><br>

</body>
</html>