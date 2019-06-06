<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca Utente</title>

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
	<h1 align="center">Ricerca informazioni utente</h1>
	<br>
	<h3 align="center">Data: ${serverTime}</h3>
	
	<!-- <br>
			<h4 align="center">
				<i>-- inserire almeno uno dei seguenti campi -- <br>
					(ID_Utente, Data, Tipo di Orario)
				</i>
			</h4>
			<br> -->



	<!-- 	<p>
						<label>ID_User:</label><br> <input type="text"
							placeholder="Inserisci Id_User" name="Id_user">
					</p>

					<p>
						<label>Tipo di permesso:</label><br> <select name="scelta">
							<option value="scelta" selected>---scegli---</option>
							<option value="w - Work">w - Work</option>
							<option value="h - Holiday">h - Holiday</option>
							<option value="p - Permesso">p - Permesso</option>
							<option value="m - Malattia">m - Malattia</option>
						</select>
					</p>
 				-->
<div align="center">
	<form action="data" method="get">

		<label>Inserisci la data</label><br> <input type="date" name="date" required><br>
		<label>Inserisci l'id utente</label><br><input type="number" name="id" required><br><br>
		<div>
		<input type="submit" value="Conferma"> 
		<input type="reset" value="Annulla">
		</div>	
	</form>
</div>
</body>
</html>