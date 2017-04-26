<h2 align="left">Thank you. Please write a message to this user. The message will be sent to their Message System.</h2>

<form method = "post" action = "SendMessageControllerr"> 
	Username: <input type = "text" name = "username">
	<br>
	Message: <textarea name="message" cols="30" rows="10"></textarea>
	
<input type = "submit" value = "Send">	
</form>

<h2 align="left">After your ride, please take a few seconds to rate the other person:</h2>

<form method = "post" action="RatingController">
  <select name="rating">
    <option value=1>1</option>
    <option value=2>2</option>
    <option value=3>3</option>
    <option value=4>4</option>
    <option value=5>5</option>
  </select>
  <input type="submit" value="Submit">
</form>
