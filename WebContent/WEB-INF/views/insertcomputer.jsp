<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<html>
<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Inserimento dati</title>
</head>

<body>
  <div class="container">
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Inserisci un nuovo pc</h2> <br>

    <div class="row">
      <form class="col s12" action="confirmdatacomputer" method="post">
        <div class="row">
          
          <div class="input-field col s6">
            <input id="model" type="text" name="model" required>
            <label for="model" class="pink-text"><b>Modello</b></label>
          </div>
          <div class="input-field col s6">
            <input id="brand" type="text" name="brand" required>
            <label for="brand" class="pink-text"><b>Marca</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s6">
            <input id="cpu" type="text" name="cpu" required>
            <label for="cpu" class="pink-text"><b>CPU</b></label>
          </div>
        
          <div class="input-field col s6">
            <input id="ram" type="number" class="validate" name="ram" required>
            <label for="ram" class="pink-text"><b>Ram</b></label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input id="hard_disk" type="text" name="hardDisk" value="" required>
            <label for="hard_disk" class="pink-text"><b>Hard-disk</b></label>
          </div>

          <div class="input-field col s6">
            <input id="serial_number" type="text" name="serialNumber" required>
            <label for="serial_number" class="pink-text"><b>Numero seriale</b></label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input id="operating_system" type="text" name="operatingSystem" value="" required>
            <label for="operating_system" class="pink-text"><b>Sistema operativo</b></label>
        </div>
        
          <div class="input-field col s6">
            <input id="note" type="text" name="note" value="">
            <label for="note">Note</label>
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
</html>