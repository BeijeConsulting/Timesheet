<html>
	<head>
		<title>TEST API</title>
	</head>
	<body>
		<script>
		
			function search() {
				let id = document.getElementById("search_id").value;
				console.log(id);
				fetch('https://jsonplaceholder.typicode.com/todos/' + id)
				  .then(response => response.json())
				  .then(json => {
					console.log(json);
					document.getElementById("showresults").innerHTML = JSON.stringify(json);
					console.log("userId = " + json.userId);
				  })
			}

			
		</script>
		INSERISCI ID : <input type="text" id="search_id" value="1"><br>
		<input type="button" onclick="search()" value="FETCH">
		<br>
		<br>		
		<br>
		RESULTS:<br>
		<p id="showresults">...</p>
	</body>
</html>
