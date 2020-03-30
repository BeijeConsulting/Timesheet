<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Inserimento indirizzo</title>
</head>
<body>
<div class="container">
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Inserisci l'indirizzo</h2> <br>

    <div class="row">
      <form class="col s12" action="registeraddress" method="post">
        <div class="row">
          
          <div class="input-field col s6">
            <input type="number" name="idUser" required>
            <label for="id_user" class="pink-text"><b>ID UTENTE</b></label>
          </div>
          <div class="input-field col s6">
            <input type="text" name="street" required>
            <label for="street" class="pink-text"><b>INDIRIZZO</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s6">
            <input type="text" name="city" required>
            <label for="city" class="pink-text"><b>CITTA'</b></label>
          </div>
        
          <div class="input-field col s6">
            <input type="text" name="province" maxlength="2" required>
            <label for="province" class="pink-text"><b>PROVINCIA</b></label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input type="text" name="cap" required>
            <label for="cap" class="pink-text"><b>CAP</b></label>
          </div>
        
          <div class="input-field col s6">
            <input type="text" name="country" maxlength="2" required>
            <label for="country" class="pink-text"><b>NAZIONE</b></label>
          </div>
        </div>
        
        <div class="center">
        <input class="btn-large blue" type="submit" value="INVIO">
		</div>
		
      </form>
    </div>

  </div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<script>
/*function invia() {
	console.log("OK");
var obj = {"id": "34",
    	"id_user": "34",
    	"street": "Via Prova",
    	"city": "Milano",
    	"province": "MI",
    	"cap": "200092",
    	"country": "it",
    	"start_date": "",
    	"end_date": "",
    	"type": ""
    	};
fetch('http://localhost:8080/Timesheet/registeraddress', {
    method: 'POST',
    body: JSON.stringify(obj)
	
})
}*/
</script>
</body>

</body>
</html>