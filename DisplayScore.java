package com.MiniProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DisplayScore   {
	
	Connection connection = null;
	PreparedStatement ps = null;
	
	
	public void presentScore() throws SQLException {
	
			try {
		
					ConnectionTest Test = new ConnectionTest();
					connection = Test.getConnectionDetails();
	
					ps = connection.prepareStatement("select * from student ");
	
					ResultSet rs = ps.executeQuery();
	
					while(rs.next()) {
		
						System.out.println("student Id :" + rs.getInt(1));
						System.out.println("student Name :" + rs.getString(2));
						System.out.println("student Score :" + rs.getInt(3));
						System.out.println("------------------------");
					}
	
					Scanner scan = new Scanner(System.in);
	
					System.out.println("Enter User Id");
					int Id = scan.nextInt();
	
					ps = connection.prepareStatement("select * from student where id = ?");
					ps.setInt(1, Id);
	
	
					// submit sql statement to db
					ResultSet result = ps.executeQuery();
	
					while(result.next()) {
	  
						System.out.println("student Name :" + result.getString(2));
						System.out.println("student Score :" + result.getInt(3));
					}
					result.close();
	
			} catch (Exception e) {
				System.out.println(e);
			} finally {
		
				connection.close();
				ps.close();
			}

		
	}

}