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
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Dati inseriti</h2> <br>

     <div class="row">
      <form class="col s12" action="confermaaddress" method="post">
        <div class="row">
          
           <div class="input-field col s6">
            <input type="number" name="user.id" value ="${address.id_user}"required>
            <label for="user.id" class="pink-text"><b>ID UTENTE</b></label>
          </div>
          <div class="input-field col s6">
            <input type="text" name="street" value ="${address.street}"required>
            <label for="street" class="pink-text"><b>INDIRIZZO</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s6">
            <input type="text" name="city" value ="${address.city}" required>
            <label for="city" class="pink-text"><b>CITTA'</b></label>
          </div>
        
          <div class="input-field col s6">
            <input type="text" name="province" value ="${address.province}"required>
            <label for="province" class="pink-text"><b>PROVINCIA</b></label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input type="text" name="cap" value ="${address.cap}"required>
            <label for="cap" class="pink-text"><b>CAP</b></label>
          </div>
        
          <div class="input-field col s6">
            <input type="text" name="country" value ="${address.country}"required>
            <label for="country" class="pink-text"><b>NAZIONE</b></label>
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