<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>leggi righe</title>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style type="text/css">
input[type=text], input[type=number], input[type=date], input[type=time],
	input[type="password"], input[type="select"] {
	width: 50%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

select {
	width: 50%;
	padding: 16px 20px;
	border: none;
	border-radius: 4px;
	background-color: #f1f1f1;
}

input[type=radio] {
	width: 24px;
	height: 24px;
	position: relative;
	top: 6px;
}

input[type=submit] {
	width: 25%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=reset] {
	width: 25%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
	input [type=submit]: hover{  
	background-color: #45a049;
}

input[type=reset]:hover {
	background-color: #45a049;
}

</style>

</head>



<body>
	<h1 align="center">Leggi records id utente e date</h1>
	<br>


	
<div align="center">

	<form action="leggirighe" method="get">
	<label>Inserisci data inizio</label><br> <input type="date" name="start" required><br>
	<label>Inserisci data fine</label><br> <input type="date" name="end" required><br>
		<label>Inserisci l'id utente</label><br><input type="number" name="idUser" required><br><br>
		<input type="submit" value="Cerca">
	</form>
</div>
</body>
</html>