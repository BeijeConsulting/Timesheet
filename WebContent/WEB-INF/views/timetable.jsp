<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
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

<title>Home</title>
</head>
<body>
	<h1 align="center">Compilazione modulo</h1>

	<p align="center">Data: ${serverTime}</p>

	<div align="center">

		<form action="user" method="post">


			<label class="w3-text-grey">ID utente</label><br> <input
				type="number" name="idUser" value="12345" required><br>

			<label class="w3-text-grey">Tipo:</label> <br>
		 <select class="w3-text-grey" name="type">
				<option value="L">Giornata lavorativa</option>
				<option value="P">Permesso</option>
				<option value="F">Ferie</option>
				<option value="M">Malattia</option>
				<option value="F">Festività</option>
			</select>
			
			
			 <br> <br> <label class="w3-text-grey">Data</label> <br>
			<input type="date" name="date" value="2019-1-1"> <br>

			<label class="w3-text-grey">Orario di inizio</label> <br> <input
				type="text" name="start1" maxlength="8" value="09:00:00"><br>

			<label class="w3-text-grey">Orario di fine</label> <br> <input
				type="text" name="end1" maxlength="8" value="13:00:00"><br>

			<label class="w3-text-grey">Secondo orario di inizio</label> <br>
			<input type="text" name="start2" maxlength="8" value="14:00:00"><br>

			<label class="w3-text-grey">Secondo orario di fine</label> <br>
			 <input type="text" name="end2" maxlength="8" value="18:00:00"><br>


			<input type="submit" value="Inserisci">


		</form>
	</div>
</body>


</html>
