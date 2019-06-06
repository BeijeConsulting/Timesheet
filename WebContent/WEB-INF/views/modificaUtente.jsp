<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


		<h3 class="center">Modifica Utente </h3>      
			<div class="container">
		<div class="row">
			<form class="col s12" action="modificaDati" method="post">
				
				<div class="row">
					<div class="input-field col s6">
				
            <input id="ID_utente" type="number" name="id"  min="1">
            <label for="ID_utente">ID Utente</label>
					</div>
          
				<div class="input-field col s6">
				<input class="btn blue" type="submit" value="Conferma">
				</div>
				</div>
			</form>
		</div>
	</div>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  
</body>
</html>