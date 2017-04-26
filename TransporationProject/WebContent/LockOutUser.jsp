<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lock Out User</title>
</head>
<body>
Lock out user page
<br>

<form method = "post" action = "LockOutUserController"> 
	
	Username: <input type = "text" name = "username">
	<br>
	Comments: <input type = "text" name = "comments">
	<br>
	<input type = "submit" name="action" value = "Lock User Account">
	<br>
	<input type = "submit" name="action" value = "Unlock User Account">

</form>


</body>
</html>