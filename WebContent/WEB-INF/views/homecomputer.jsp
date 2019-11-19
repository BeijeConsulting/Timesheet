<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>

 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
 
<title>HomeComputer</title>
</head>
<body>

	<h1 class="center" > 	
	<a class="btn-floating btn-large pulse"><i class="material-icons">account_circle</i></a>
	Benvenuto Utente
	<a class="btn-floating btn-large pulse"><i class="material-icons">account_circle</i></a></h1>

<div class="container">

    <div class="row">
      <div class="col s3">
	<form action="insertcomputer" method="get">
        <input class="btn green" type="submit" value="Inserisci Computer">
	</form>
	</div>
	
	  <div class="col s3">
		<form action="idmodifycomputer" method="get">
        <input class="btn green" type="submit" value="modifica computer">
	</form>
	</div>
	
	  <div class="col s3">
		<form action="deletecomputer" method="get">
        <input class="btn green" type="submit" value="Cancella computer">
	</form>
	</div>
	
	  <div class="col s3">
			<form action="searchcomputer" method="get">
        <input class="btn green" type="submit" value="Cerca computer">
        
	</form>
	</div>
	
		<div class="col s3">
			<form action="assigncomputer" method="get">
        <input class="btn green" type="submit" value="Assegna computer">
        </form>
        </div>
	</div>
	
	<div class="center">
	<img src="https://media1.tenor.com/images/ef11141f4d7f362866fcaa96030bffdc/tenor.gif"/>
	</div>
	
	
	</div>
</body>
</html>
