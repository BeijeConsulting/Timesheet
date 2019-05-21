<%@page import="java.util.Date"%>

<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    
    
    
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>My Web Project</title>
</head>

<body>
<div class="container">
 
<h4 class="center"> Inserisci i tuoi dati</h4> <br>

    <div class="row">
    <form class="col s12" action="rubrica" method="post">
      <div class="row">
        <div class="input-field col s6">
          <input id="first_name" type="text" name="firstname">
          <label for="first_name">Inserisci nome</label>
        </div>
        <div class="input-field col s6">
          <input id="last_name" type="text" name="lastname">
          <label for="last_name">Inserisci cognome</label>
        </div>
      </div>

      <div class="row">
        <div class="input-field col s12">
          <input id="email" type="email" class="validate" name="email">
          <label for="email">Inserisci email</label>
        </div>
      </div>

      <div class="row">
        <div class="input-field col s12">
          <input id="phone" type="text" name="phone">
          <label for="phone">Inserisci numero telefono</label>
        </div>
      </div>

      <div class="row">
        <div class="input-field col s12">
          <input id="cod_fisc" type="text" name="fiscalcode">
          <label for="cod_fisc">Inserisci codice fiscale</label>
        </div>
      </div>

      <div class="row">
        <div class="input-field col s12">
          <input id="password" type="password" name="password">
          <label for="password">Inserisci password</label>
        </div>
      </div>
	
       <input class="btn blue" type="submit" value="INVIO">

    </form>
  </div>

  </div>
  
<%-- 
<%
Connection con;
Class.forName("com.mysql.jdbc.Driver");
con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/beije?serverTimezone=CET", "root", "Beije05");
PreparedStatement ps=(PreparedStatement)con.prepareStatement("SELECT * from user");
ResultSet rs=ps.executeQuery();
while(rs.next()){
	String username=rs.getString("first_name");
	String password=rs.getString("password");
	out.println("Username "+username+" Pass "+password+"<hr>");
}
%>


<jsp:useBean id="user" class="it.beije.web.bean.User" scope="session"></jsp:useBean>


SET
<jsp:setProperty property="firstName" name="user" /> 
<% //utente.setFirstName(request.getParameter("firstname")); %>
<jsp:setProperty property="lastName" name="user" param="lastname"/>  

<jsp:setProperty property="lastName" name="user"/>  
<jsp:setProperty property="phone" name="user"/> --%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


</body>
</html>