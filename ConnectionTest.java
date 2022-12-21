package com.MiniProject;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {

	Connection conncetion = null ;
	public Connection getConnectionDetails() {
	
		
		try {
			
			// Load the establish
			Class.forName("com.mysql.jdbc.Driver");
			
			conncetion = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Shirsath@2510");	
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		return conncetion;		
	}
}
