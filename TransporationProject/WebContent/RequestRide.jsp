<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request Ride</title> <%-- Title names the tab--%>
</head>
<body>
Request Ride <%-- Actual title user see on the webpage --%>
<br> <%-- New line, essentially a /n --%>


<%-- Action needs to link to a jsp page that pulls information from database and check if the information is valid--%>
<form action = "RequestRideController" method = "POST"> 
	Departure*(Where you are currently located): <input type = "text" name = "departure">
	<br>
	Destination*(Which campus you want to be in. Ex: "Busch", "Livingston", "CookDoug", "CollegeAve"): <input type = "text" name = "destination">
	<br>
	Time(Ex: 8:00pm)*: <input type = "text" name = "time">
	<br>
	Specify Location*(Address): <input type = "text" name = "location">
	<br>

<input type = "submit" value = "Submit">	
</form>


<br>
* Indicates required field

</body>
</html>