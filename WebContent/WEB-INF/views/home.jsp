<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
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

<title>Home</title>
</head>
<body>
	<h1 align="center">Compilazione modulo</h1>

	<p align="center">Data: ${serverTime}</p>
	
	<div align="center">
	
		<form action="user" method="post">
			
			<label class="w3-text-grey">ID utente</label><br> 
			<input type="number" name="id_user" value="12345" required><br> 
			
			<label class="w3-text-grey">Password</label> <br> 

			<input type="password" name="password" pattern=".{8,}"  title="Almeno 8 caratteri!"  placeholder="min 8 caratteri" required><br> 

			
			<label class="w3-text-grey">Tipo:</label> <br> 
			<select class="w3-text-grey">
				<option value="g">Giornata lavorativa</option>
				<option value="p">Permesso</option>
				<option value="f">Ferie</option>
				<option value="m">Malattia</option>
				<option value="h">Festività</option>
			</select>
			
			<!-- <div align="center">
				<table align="center">
					<tr>
						<td><input type="radio" name="type" value="g" checked>
							<label>Giornata lavorativa</label></td>
					</tr>

					<tr>
						<td><input type="radio" name="type" value="p"> <label
							class="w3-text-black">Permeso</label>
						<td>
					</tr>

					<tr>
						<td><input type="radio" name="type" value="f"> <label
							class="w3-text-black">Ferie</label></td>
					</tr>

					<tr>
						<td><input type="radio" name="type" value="m"> <label
							class="w3-text-black">Malattia</label></td>
					</tr>

					<tr>
						<td><input type="radio" name="type" value="h"> <label
							class="w3-text-black">Festività</label></td>
					</tr>

				</table>
			</div> -->

			<!-- <div align="center">
					<p>
						<input type="radio" name="type" value="g" checked>
							<label>Giornata lavorativa</label>
					</p>

					<p>
						<input type="radio" name="type" value="p"> <label
							class="w3-text-black">Permeso</label>
					</p>

					<p>
						<input type="radio" name="type" value="f"> <label
							class="w3-text-black">Ferie</label>
					</p>

					<p>
						<input type="radio" name="type" value="m"> <label
							class="w3-text-black">Malattia</label>
					</p>

					<p>
						<input type="radio" name="type" value="h"> <label
							class="w3-text-black">Festività</label>
					</p>
			</div> -->

			<br> <br> 
			
			<label class="w3-text-grey">Data</label> <br>
			<input type="date" name="date" value="2019-1-1" placeholder="yyyy/MM/dd" > <br> 
			
			<label class="w3-text-grey">Orario di inzio</label> <br> 
			<input type="time" name="start1" maxlength="5" value="09:00" placeholder="09:00"><br> 
			
			<label class="w3-text-grey">Orario di fine</label> <br> 
			<input type="time" name="end1" value="13:00" placeholder="13:00"><br> 
			
			<label class="w3-text-grey">Secondo orario di inizio</label> <br> 
			<input type="time" name="start2" value="14:00" placeholder="14:00"><br> 
			
			<label class="w3-text-grey">Secondo orario di fne</label> <br> 
			<input type="time" name="end2" value="18:00" placeholder="18:00"><br>

			<input type="submit" value="Inserisci">
			
			
		</form>
	</div>
</body>





















</html>
