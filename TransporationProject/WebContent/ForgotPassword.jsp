<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>

Confirm user:
<form method = "post" action = "ForgotPasswordController"> 
    Username: <input type = "text" name = "username">
    <br>
    RUID: <input type = "text" name = "RUID">
    <br>
    
<input type = "submit" value = "Authenticate">    
</form>

</body>
</html>