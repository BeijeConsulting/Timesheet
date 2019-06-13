<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<html>

<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

  <title>Benvenuti</title>

  <style type="text/css">
    .beije-logo {
      height: 10em;
      width: 10em;
    }

    .sfondo {
      background-color: #201D1D;
    }
  </style>

</head>

<body>

 <nav>
  <div class="nav-wrapper sfondo center">
    <a href="#"><img class="responsive-img beije-logo" src="img/logo.png"></a>
  </div>
</nav>

<div class="container">
  <div class="center">

    <h1>Benvenuto Admin</h1>
    <i class="large material-icons prefix">face</i><br><br><br><br>

    <div class="row">

      <div class="col s6">
         <form class="col s12" action="DA-INSERIRE" method="post">
        <a class="waves-effect waves-light btn-large sfondo"><i class="material-icons left">person</i>Gestione Utenti</a>
      </form>
      </div>

      <div class="col s6">
         <form class="col s12" action="DA-INSERIRE" method="post">
        <a class="waves-effect waves-light btn-large sfondo"><i class="material-icons left">domain</i>Gestione Timetable</a>
      </form>
      </div>

    </div>
  </div>
  </div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>