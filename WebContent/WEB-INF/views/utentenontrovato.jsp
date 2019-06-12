<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Utente non trovato</title>

<style>
div.message.warning {
	
	border-color: #E87C29
}

input[type=submit] {
  width: 20%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

</style>
</head>
<body bgcolor="lightgrey">
	<div class="message warning" align="center">
		<h2>Non è stato trovato nessun utente con l'id inserito!</h2>
		<a href="https://github.com/BeijeConsulting/Timesheet/blob/gruppo2/WebContent/WEB-INF/views/img/warning.png"></a>
	</div>
	<div align="center">
		<h3>Vuoi riprovare ?</h3>
		<form action="NonTiAbbiamoTrovato" method="get">
			<input type="submit" value="Riprova">
		</form>
	</div>
</body>
</html>