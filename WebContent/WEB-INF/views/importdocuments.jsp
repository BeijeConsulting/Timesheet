<!DOCTYPE html>
<html>
<head>
<title>Insert Data</title>

<style>
a {
	font: normal 16px/normal "Lucida Console", Monaco, monospace;
}

p {
	font: normal 16px/normal "Lucida Console", Monaco, monospace;
}

textarea {
	resize: none;
	border: 1px solid #b7b7b7;
	-webkit-border-radius: 10px;
	border-radius: 10px;
	font: normal 16px/normal "Lucida Console", Monaco, monospace;
	color: rgba(0, 142, 198, 1);
	box-shadow: 2px 2px 2px 0 rgba(0, 0, 0, 0.2);
	text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.66);
}

.displayDivs {
	display: none;
}

ul {
	text-align: center;
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
	width: 100%;
	display: table;
	text-align: center;
	border-radius: 20px 20px;
}

li {
	float: left;
	border-right: 1px solid #bbb;
}

li:last-child {
	border-right: none;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li












 












a
























:hover
























:not












 












(
.active












 












)
{
background-color
























:












 












#111
























;
}
.active {
	background-color: #4CAF50;
}

nav {
	display: inline-block;
	text-align: center;
	list-style-type: none;
}

.container {
	display: inline-block;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	position: relative;
	padding: 10px 20px;
	border: 1px solid #b7b7b7;
	-webkit-border-radius: 20px;
	border-radius: 20px;
	font: normal 16px/normal "Lucida Console", Monaco, monospace;
	color: rgba(0, 142, 198, 1);
	-o-text-overflow: clip;
	text-overflow: clip;
	background: rgba(252, 252, 252, 1);
	-webkit-box-shadow: 2px 2px 2px 0 rgba(0, 0, 0, 0.2);
	box-shadow: 2px 2px 2px 0 rgba(0, 0, 0, 0.2);
	text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.66);
	-webkit-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
	-moz-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
	-o-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
	transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
}

h2 {
	color: #c9d0d4;
	font-family: 'Helvetica Neue', sans-serif;
	font-size: 46px;
	font-weight: 100;
	line-height: 50px;
	letter-spacing: 1px;
	padding: 0 0 40px;
}

.myButton {
	box-shadow: 0px 10px 14px -7px #3e7327;
	background: linear-gradient(to bottom, #77b55a 5%, #72b352 100%);
	background-color: #77b55a;
	border-radius: 4px;
	border: 1px solid #4b8f29;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 13px;
	font-weight: bold;
	padding: 6px 12px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #5b8a3c;
}

.myButton:hover {
	background: linear-gradient(to bottom, #72b352 5%, #77b55a 100%);
	background-color: #72b352;
}

.myButton:active {
	position: relative;
	top: 1px;
}
</style>

</head>
<body>

	<script type="text/javascript">
		function displayInsert() {
			var y = document.getElementById("insertBlock");
			y.style.display = (y.style.display == 'none' || y.style.display == '') ? 'block'
					: 'none';
		}

		function displayUpdate() {
			var y = document.getElementById("updateBlock");
			y.style.display = (y.style.display == 'none' || y.style.display == '') ? 'block'
					: 'none';
		}

		function displayDelete() {
			var y = document.getElementById("deleteBlock");
			y.style.display = (y.style.display == 'none' || y.style.display == '') ? 'block'
					: 'none';
		}

		function displaySelect() {
			var y = document.getElementById("selectBlock");
			y.style.display = (y.style.display == 'none' || y.style.display == '') ? 'block'
					: 'none';
		}

		function displayUpload() {
			var y = document.getElementById("uploadBlock");
			y.style.display = (y.style.display == 'none' || y.style.display == '') ? 'block'
					: 'none';
		}

		function displayNone() {
			var x = document.getElementsByClassName("displayDivs");
			var i;
			for (i = 0; i < x.length; i++) {
				x[i].style.display = 'none';
			}
		}
	</script>

	<div id="rounded" align="center">
		<nav>
			<ul>
				<li><a class="active" href="#HOME" onclick="displayNone();">HOME</a></li>
				<li><a href="#INSERT" id="insertDisplay"
					onclick="displayNone();displayInsert()">INSERT</a></li>
				<li><a href="#UPDATE" id="updateDisplay"
					onclick="displayNone();displayUpdate()">UPDATE</a></li>
				<li><a href="#DELETE" id="deleteDisplay"
					onclick="displayNone();displayDelete()">DELETE</a></li>
				<li><a href="#SELECT" id="selectDisplay"
					onclick="displayNone();displaySelect()">SELECT</a></li>
				<li><a href="#UPLOAD" id="uploadDisplay"
					onclick="displayNone();displayUpload()">UPLOAD</a></li>
			</ul>
		</nav>
	</div>

	<!-- FORM PER L'INSERIMENTO DI RECORD SU DB -->
	<div id="insertBlock" class="displayDivs" align="center">
		<br /> <br /> <br />
		<form action="insertdata" method="post" class="container">
			<br />
			<p>Description:</p>
			<input type="text" name="description" /> <br /> <br /> <br /> <input
				class="myButton" type="submit" />
		</form>
	</div>

	<br />

	<!-- FORM PER L'AGGIORNAMENTO DI RECORD SU DB -->
	<div id="updateBlock" class="displayDivs" align="center">
		<br /> <br /> <br />
		<form action="updatedata" method="post" class="container">
			<p>ID:</p>
			<input type="text" name="id" /> <br />
			<p>Description:</p>
			<input type="text" name="description" /> <br /> <br /> <br /> <input
				class="myButton" type="submit" />
		</form>
	</div>

	<!-- FORM PER LA CANCELLAZIONE DI RECORD SU DB -->
	<div id="deleteBlock" class="displayDivs" align="center">
		<br /> <br /> <br />
		<form action="deletedata" method="post" class="container">
			<p>ID:</p>
			<input type="text" name="id" /> <br /> <br /> <br /> <input
				class="myButton" type="submit" />
		</form>
	</div>

	<!-- FORM PER LA INTERROGAZIONE SU DB -->
	<br />
	<br />
	<br />
	<div id="selectBlock" class="displayDivs" align="center">
		<form action="selectdata" method="post" class="container">
			<p>ID:</p>
			<input type="text" name="id" /> <input class="myButton"
				type="submit" />
		</form>

		<!-- FORM PER LA INTERROGAZIONE SU DB -->
		<h2>Select all</h2>
		<form action="selectalldata" method="post">
			<input type="submit" class="myButton" />
		</form>
	</div>

	<div id="uploadBlock" class="displayDivs" align="center">
		<h2>Documents Upload</h2>
		<form action="insertdata" method="post" id="insertData">
			<input type="text" list="doc_type" name="doctype" class="container"
				placeholder="Document Type">
			<datalist id="doc_type">
				<option value="documento di identità"></option>
				<option value="certificato di residenza"></option>
				<option value="altro documento"></option>
			</datalist>
			<input type="text" list="file_ext" name="file_ext" class="container"
				placeholder="Document Extension">
			<datalist id="file_ext">
				<option value="PDF"></option>
				<option value="PNG"></option>
				<option value="BMP"></option>
				<option value="JPG"></option>
				<option value="TIFF"></option>
			</datalist>
			<br /> <br />
			<textarea name="description" rows="5" cols="80"
				placeholder="Description"></textarea>
			<br /> <br /> <input type="submit" class="myButton">
		</form>
		<br /> <br />
		<form action="./UploadServlet" method="post"
			enctype="multipart/form-data">
			<input type="file" name="file" size="50" /> <br /> <br /> <input
				type="submit" value="Upload File" class="myButton" />
		</form>
	</div>



</body>
</html>