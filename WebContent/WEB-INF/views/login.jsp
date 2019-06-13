
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<html>

<head>

	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>

	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

	<title>Login</title>

	<style type="text/css">
		.beije-logo {
			height: 10em;
			width: 10em;
		}

		.sfondo {
			background-color: #201D1D;
		}


		.bordo{
			width :100%;
			height: 100;
			margin: auto;
			text-align: center;
			border-style: solid;
			border-width: 2px;
			border-color: #201D1D;
		}

		body{
			background-image: url("img/logo_beije_30.png");
			background-repeat: no-repeat;
			background-position: center;


		}


	</style>

</head>

<body>

	<nav>
		<div class="nav-wrapper sfondo">
			<a href="#"><img class="responsive-img beije-logo" src="img/logo.png"></a>
		</div>
	</nav>


	<div class="container">
		<div class="center">

			<h1>Login    <i class="medium material-icons prefix">account_circle</i><br><br></h1>


			<div class="row">



				<div class="row">
					<form class="col s12" action="conferma_dati" method="post">


						<div class="row">
							<div class="input-field col s12">
								<i class="material-icons prefix">mail</i>
								<input id="email" type="email" class="validate" name="personalEmail">
								<label for="email" class="black-text"><b>Inserisci email</b></label>
							</div>
							<div class="input-field col s12">
								<i class="material-icons prefix">vpn_key</i>
								<input id="password" type="password" name="password" required>
								<label for="password" class="black-text"><b>Inserisci password</b></label>

							</div>
</div>
						</form>
					</div>


						<div class="row">
							<form class="col s12 right" action="DA-INSERIRE" method="post">
								<a class="waves-effect waves-light btn-large sfondo"><i class="material-icons left">person</i>Loggo</a>
							</form>

						</div>

					</div>


				</div>
			</div>
		


		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

	</body>
	</html>