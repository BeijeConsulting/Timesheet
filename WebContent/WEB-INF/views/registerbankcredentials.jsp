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
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Dati inseriti</h2> <br>

    < <div class="row">
      <form class="col s12" action="confermabankcredentials" method="post">
        <div class="row">
          
          <div class="input-field col s6">
            <input type="number" name="user.id" value ="${bank_credentials.user.id}">
            <label for="user.id" class="pink-text"><b>ID UTENTE</b></label>
          </div>
          <div class="input-field col s6">
            <input type="text" name="accountholder" value ="${bank_credentials.accountholder}">
            <label for="accountholder" class="pink-text"><b> TITOLARE DEL CONTO</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s6">
            <input type="text" name="iban" value ="${bank_credentials.iban}">
            <label for="iban" class="pink-text"><b>IBAN</b></label>
          </div>
        
          <div class="input-field col s6">
            <input type="text" name="swift" value ="${bank_credentials.swift}" >
            <label for="swift" class="pink-text"><b>CODICE SWIFT</b></label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input type="date" name="start_date" value ="${bank_credentials.start_date}">
            <label for="start_date" class="pink-text"><b>DATA D'INIZIO</b></label>
          </div>
        
          <div class="input-field col s6">
            <input type="date" name="end_date" value ="${bank_credentials.end_date}">
            <label for="end_date" class="pink-text"><b>DATA DI FINE</b></label>
          </div>
        </div>
        
        	<div class="input-field col s6">
            <input type="text" name="notes" value ="${bank_credentials.notes}">
            <label for="notes" class="pink-text"><b>NOTE</b></label>
          </div>
        </div>
        <div class="center">
        <input class="btn-large blue" type="submit" value="CONFERMA">
		</div>
		
      </form>
    </div> 
  </div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>

</body>
</html>