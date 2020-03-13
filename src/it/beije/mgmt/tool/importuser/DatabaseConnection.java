package it.beije.mgmt.tool.importuser;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 


public class DatabaseConnection { 
	protected static Connection initializeDatabase() 
		throws SQLException, ClassNotFoundException 
	{ 
		
		String dbURL = "jdbc:mysql://beijedev.ccc2op6yesad.eu-west-1.rds.amazonaws.com:3306/"; 
		
		String dbName = "beijedb?serverTimezone=CET"; 
		String dbUsername = "beije"; 
		String dbPassword = "beije001"; 

		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword); 
		return con; 
	} 
} 
