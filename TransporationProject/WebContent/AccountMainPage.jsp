<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
</head>
<body>

<% out.println("Main Page"); %> 
<br>
Welcome ${requestScope['user'].username}! To access the information, please click the links below:
<br>
<br/>
<a href="RequestRide.jsp">Request Ride</a><br>

<a href="OfferRide.jsp">Offer Ride</a><br>

<a href="SeeStats.jsp">See Stats</a><br>

<a href="EditInformation.jsp">Edit Information</a>


</body>
</html>