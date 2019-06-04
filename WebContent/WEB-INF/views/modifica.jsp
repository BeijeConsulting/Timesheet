<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
input[type=text],input[type=date],input[type=time],[type="password"] {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=radio] {
clip: rect(1px, 1px, 1px, 1px);
}

select {
	width: 50%;
	padding: 16px 20px;
	border: none;
	border-radius: 4px;
	background-color: #f1f1f1;
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
	<%-- <h1>Compilazione modulo: </h1>

	<P>Data: ${serverTime}.</p>

	<form action="user" method="post">

		<label class="w3-text-grey">ID utente</label><br> <input type="text" name="id_user" value=${timetable.id_user}><br>
		
		<label class="w3-text-grey">Password</label> <br><input type="password" name="password" minlength="8" required placeholder="min 8 caratteri"><br>
		
		<label class="w3-text-grey">Tipo</label><br> 
		
		<input type="radio" name="type" value="g" checked> <label>Giornata lavorativa</label><br> 
		
		<input type="radio" name="type" value="p"> <label class="w3-text-black">Permeso</label>	<br> 
		
		<input type="radio" name="type" value="f"> <label class="w3-text-black">Ferie</label> <br> 
		
		<input type="radio" name="type" value="m"> <label class="w3-text-black">Malattia</label> <br><br> 
		
		<input type="radio" name="type" value="h"> <label class="w3-text-black">Festività</label> <br><br> 
		
		<label class="w3-text-grey">Data (yyyy/MM/dd):</label> <br> <input type="date" name="date" value=${timetable.getDate()}> <br> 
		
		<label class="w3-text-grey">Orario di inzio</label> <br> <input type="time" name="start1" maxlength="5" value=${timetable.getStart1()}  placeholder="09:00"><br>
		
		<label class="w3-text-grey">Orario di fine</label> <br> <input type="time" name="end1" value=${timetable.getEnd1()}  placeholder="13:00"><br>
		
		<label class="w3-text-grey">Secondo orario di inizio</label> <br> <input type="time" name="start2" value=${timetable.getStart2()}  placeholder="14:00"><br> 
		
		<label class="w3-text-grey">Secondo orario di fne</label> <br> <input type="time" name="end2" value=${timetable.getEnd2()}  placeholder="18:00"><br> 
		
		<input type="submit" value="Inserisci">

	</form> --%>
	
	
	
	
	<h1 align="center">Modifica i dati del modulo</h1>

	<p align="center">Data: ${serverTime}.</p>
	
	<div align="center">
	
		<form action="user" method="post">
			
			<label class="w3-text-grey">ID utente</label><br> 
			<input type="text" name="id_user" value=${timetable.id_user} required><br> 
			
			<label class="w3-text-grey">Password</label> <br> 
			<input type="password" name="password" minlength="8" required placeholder="min 8 caratteri"><br> 
			
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
			<input type="date" name="date" value=${timetable.getDate()} placeholder="yyyy/MM/dd"> <br> 
			
			<label class="w3-text-grey">Orario di inzio</label> <br> 
			<input type="time" name="start1" maxlength="5" value=${timetable.getStart1()} placeholder="09:00"><br> 
			
			<label class="w3-text-grey">Orario di fine</label> <br> 
			<input type="time" name="end1" value=${timetable.getEnd1()} placeholder="13:00"><br> 
			
			<label class="w3-text-grey">Secondo orario di inizio</label> <br> 
			<input type="time" name="start2" value=${timetable.getStart2()} placeholder="14:00"><br> 
			
			<label class="w3-text-grey">Secondo orario di fne</label> <br> 
			<input type="time" name="end2" value=${timetable.getEnd2()} placeholder="18:00"><br>

			<input type="submit" value="Inserisci">

		</form>
	</div>
</body>

</html>