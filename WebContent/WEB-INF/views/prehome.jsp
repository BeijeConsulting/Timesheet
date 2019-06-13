<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style type="text/css">
input[type=text], input[type=number], input[type=date], input[type=time], input[type="password"],
	input[type="select"] {
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
	width: 50%;
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
}
</style>
<meta charset="ISO-8859-1">
<title>Benvenuto</title>
</head>
<body>
<h1 align="center">Scegli l'opzione che vorresti eseguire</h1>

	<p align="center">Data: ${serverTime}</p>
	
	<div align="center">
			<form action="hometimetable" method="post">
				
			<input type="submit" value="Inserisci">
		</form>
		<form action="ricerca" method="post">
			<input type="submit"value="Ricerca/Modifica Utente">
		</form>
	</div>	
</body>
</html>