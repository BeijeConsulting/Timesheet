<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>

 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
 
<title>Home</title>
</head>
<body>

	<h1 class="center" > 	
	<a class="btn-floating btn-large pulse"><i class="material-icons">account_circle</i></a>
	Benvenuto Utente
	<a class="btn-floating btn-large pulse"><i class="material-icons">account_circle</i></a></h1>

<div class="container">
	<br><br><P>Hai loggato nel: ${serverTime}.</p><br><br>

    <div class="row">
      <div class="col s3">
	<form action="inserisci_utente" method="post">
        <input class="btn green" type="submit" value="Inserisci utente">
	</form>
	</div>
	
	  <div class="col s3">
		<form action="modifica_utente" method="post">
        <input class="btn green" type="submit" value="modifica utente">
	</form>
	</div>
	
	  <div class="col s3">
		<form action="cancella_utente" method="post">
        <input class="btn green" type="submit" value="cancella utente">
	</form>
	</div>
	
	  <div class="col s3">
			<form action="cerca_utente" method="post">
        <input class="btn green" type="submit" value="cerca utente">
	</form>
	</div>
	</div>
	
	<div class="center">
	<img src="https://media1.tenor.com/images/ef11141f4d7f362866fcaa96030bffdc/tenor.gif"/>
	</div>
	
	
	</div>
</body>
</html>
