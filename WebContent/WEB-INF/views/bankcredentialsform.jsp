<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Inserimento credenziali bancarie</title>
</head>
<body>
<div class="container">
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Inserisci credenziali bancarie</h2> <br>

    <div class="row">
      <form class="col s12" action="registercredentials" method="post">
        <div class="row">
          
          <div class="input-field col s6">
            <input type="number" name="user.id" required>
            <label for="user.id" class="pink-text"><b>ID UTENTE</b></label>
          </div>
          <div class="input-field col s6">
            <input type="text" name="accountholder" required>
            <label for="accountholder" class="pink-text"><b> TITOLARE DEL CONTO</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s6">
            <input type="text" name="iban" required>
            <label for="iban" class="pink-text"><b>IBAN</b></label>
          </div>
        
          <div class="input-field col s6">
            <input type="text" name="swift" maxlength="11" required>
            <label for="swift" class="pink-text"><b>CODICE SWIFT</b></label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input type="date" name="start_date" required>
            <label for="start_date" class="pink-text"><b>DATA D'INIZIO</b></label>
          </div>
        
          <div class="input-field col s6">
            <input type="date" name="end_date" >
            <label for="end_date" class="pink-text"><b>DATA DI FINE</b></label>
          </div>
        </div>
        
        	<div class="input-field col s6">
            <input type="text" name="notes" >
            <label for="notes" class="pink-text"><b>NOTE</b></label>
          </div>
        </div>
        <div class="center">
        <input class="btn-large blue" type="submit" value="INVIO">
		</div>
		
      </form>
    </div>

  </div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>

</body>
</html>