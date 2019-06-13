<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 class="center">Interfaccia Temporanea, cosa sei?</h1>

<div class="right">

		<form action="http://localhost:8080/Timesheet/homepage" method="get">
				<input class="btn green" type="submit" value="Admin">
			</form>
</div>
<div class="left">

		<form action="http://localhost:8080/Timesheet/" method="get"> <!--da aggiungere jsp utente-->
				<input class="btn green" type="submit" value="User">
			</form>
</div>

		
</body>
</html>