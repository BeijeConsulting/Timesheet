<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title> Pagina di conferma</title>
</head>
<body>
<div class="container">
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Dati inviati</h2> <br>
 
 	<ul>
 		<li ><label for="id" class="pink-text"><b>ID </b></label>${address.id}</li>
  		<li><label for="user.id" class="pink-text"><b>ID UTENTE </b></label>${address.id_user}</li>
  		<li><label for="street" class="pink-text"><b>INDIRIZZO </b></label>${address.street}</li>
  		<li><label for="city" class="pink-text"><b>CITTA' </b></label>${address.city}</li>
  		<li><label for="province" class="pink-text"><b>PROVINCIA </b></label>${address.province}</li>
  		<li><label for="cap" class="pink-text"><b>CAP </b></label>${address.cap}</li>
  		<li><label for="country" class="pink-text"><b>NAZIONE </b></label>${address.country}</li>
  
	</ul> 
</div>
  
  

  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>

</body>
</html>