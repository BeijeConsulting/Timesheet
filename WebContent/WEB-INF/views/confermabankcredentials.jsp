<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Pagina di conferma</title>
</head>
<body>
<div class="container">
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Dati inviati</h2> <br>

	<ul>
 		<li><label for="user.id" class="pink-text"><b>ID </b></label>${bank_credentials.id}</li>
  		<li><label for="user.id" class="pink-text"><b>ID UTENTE </b></label>${bank_credentials.user.id}</li>
  		<li><label for="accountholder" class="pink-text"><b> TITOLARE DEL CONTO </b></label>${bank_credentials.accountholder}</li>
  		<li><label for="iban" class="pink-text"><b>IBAN </b></label>${bank_credentials.iban}</li>
  		<li> <label for="swift" class="pink-text"><b>CODICE SWIFT </b></label>${bank_credentials.swift}</li>
  		<li><label for="start_date" class="pink-text"><b>DATA D'INIZIO </b></label>${bank_credentials.start_date}</li>
  		<li><label for="end_date" class="pink-text"><b>DATA DI FINE </b></label>${bank_credentials.end_date}</li>
  		<li><label for="notes" class="pink-text"><b>NOTE </b></label>${bank_credentials.notes}</li>
  
	</ul> 
 </div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>

</body>
</html>