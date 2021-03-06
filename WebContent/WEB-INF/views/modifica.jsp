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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifica Dati</title>
</head>


<body>
	<h1 align="center">Modifica i dati del modulo</h1>

	<p align="center">Data: ${serverTime}</p>
	
	<div align="center">
	
		<form action="user" method="post">
			
			<label class="w3-text-grey">ID utente</label><br> 
			<input type="number" name="idUser" value=${timetable.idUser} required><br> 
			
			<label class="w3-text-grey">Tipo:</label> <br> 
			<select class="w3-text-grey" name="type">
				<option value="g">Giornata lavorativa</option>
				<option value="p">Permesso</option>
				<option value="f">Ferie</option>
				<option value="m">Malattia</option>
				<option value="h">FestivitÓ</option>
			</select>
			
			
			<br> <br> 
			
			<label class="w3-text-grey">Data</label> <br>
			<input type="date" name="date" value=${timetable.getDate()} placeholder="yyyy/MM/dd"> <br> 
			
			<label class="w3-text-grey">Orario di inzio</label> <br> 
			<input type="text" name="start1" maxlength="5" value=${timetable.getStart1()} placeholder="09:00"><br> 
			
			<label class="w3-text-grey">Orario di fine</label> <br> 
			<input type="text" name="end1"  maxlength="5" value=${timetable.getEnd1()} placeholder="13:00"><br> 
			
			<label class="w3-text-grey">Secondo orario di inizio</label> <br> 
			<input type="text" name="start2"  maxlength="5" value=${timetable.getStart2()} placeholder="14:00"><br> 
			
			<label class="w3-text-grey">Secondo orario di fne</label> <br> 
			<input type="text" name="end2"  maxlength="5" value=${timetable.getEnd2()} placeholder="18:00"><br>

			<input type="submit" value="Inserisci">

		</form>
	</div>
</body>

</html>