<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Advertisement Page</title>
</head>
<body>

Upload Advertisement:
<br>
(Automatically uploads current advertisement to user page)
<br>
<form method="POST" enctype="multipart/form-data" action="UploadAdvertisementController">
  Photo name: <input type = "text" name = "photoName">
  <br>

  File to upload: <input type="file" name="photo">
  <br/>
  <input type="submit" value="Upload"> 
</form>

<br>

Delete Advertisement:
<form method="POST" action="DeleteAdvertisementController">
  Photo name: <input type = "text" name = "photoName">
  <br>
  <input type="submit" value="Delete"> 
</form>

<br>
Select advertisement to display:
<form method="POST" action="SelectAdvertisementController">
  Photo name: <input type = "text" name = "photoName">
  <br>
  <input type="submit" value="Select"> 
</form>

</body>
</html>