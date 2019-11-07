<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Address Form</title>
</head>
<body>

<form action="registeraddress" method="post">

	ID_UTENTE : <input type="text" name="id_user" value=""><br>
	INDIRIZZO : <input type="text" name="street" value=""><br>
	CITTA' : <input type="text" name="city" value=""><br>
	PROVINCIA : <input type="text" name="province" value=""><br>
	CAP : <input type="text" name="cap" value=""><br>
	NAZIONE : <input type="text" name="country" value=""><br>
	
	<input type="submit" value="INVIA">
</form>

</body>
</html>