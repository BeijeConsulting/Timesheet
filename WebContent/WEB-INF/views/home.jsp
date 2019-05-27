<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</p>

	<form action="user" method="post">
	
	id_user: <br>
	<input type="text" name="id_user"><br>
	Password: <br><input type="password" name="password"><br>
	Tipo di dato:<br>
	<input type="radio" name="Tipo" value="giornata lavorativa" checked> Giornata lavorativa<br>
	<input type="radio" name="Tipo" value="permesso"> Permeso <br>
	<input type="radio" name="Tipo" value="ferie"> Ferie<br>
	<input type="radio" name="Tipo" value="malattia"> Malattia<br>
	Data (yyyy/MM/dd): <br>
	<input type="date" name="data">
       
	Orario di inzio: <br>
	<input type="text" name="orariodiinizio"><br>
	Orario di fine: <br>
	<input type="text" name="orariodifine"><br>
	Secondo orario di inizio: <br>
	<input type="text" name="secondoorariodiinizio"><br>
	Secondo orario di fne: <br>
	<input type="text" name="secondoorariodifine"><br>
	<input type="submit" value="Inserisci">
	</form>
</body>
</html>
