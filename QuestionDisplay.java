package com.MiniProject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuestionDisplay implements Question {
	
	Connection connection = null;
	PreparedStatement ps = null;           

	@Override
	public void Quiz() throws SQLException {
		      
		          int a = 1 ;
		         int totalScore = 0;
		         int rightAnswer = 0;
		         int wrongAnswer = 0;
		
		   try {
			   
			   //Create Connection class object
			   		ConnectionTest test = new ConnectionTest();
			   		connection = test.getConnectionDetails();
				
				// Create the prepared statement
			   		ps = connection.prepareStatement("select * from question ORDER BY RAND()");
				
				//Submit the sql statement to db
			   		ResultSet rs = ps.executeQuery();
			   
				// Create Scanner class for take student name from user
			   		Scanner sc = new Scanner(System.in); 
		    		
			   		System.out.println("Enter student name");
			   		String username = sc.next();
		    		
			   		System.out.println("------------------- * Start Quiz * ------------------");
	
			   		while(rs.next()) {
				
			   			System.out.println("Question:"+a+":" +rs.getString("Question"));
			   			System.out.println(" A :"+rs.getString("A"));
			   			System.out.println(" B :"+rs.getString("B"));
			   			System.out.println(" C :"+rs.getString("C"));		
			   			System.out.println(" D :"+rs.getString("D"));
			   			System.out.println();
			   			String useranswer = "";

			   			char ans;
			   			System.out.println("Enter your answer");       // take answer from user 
			   			Scanner scan = new Scanner(System.in);
			   			ans = scan.next().charAt(0);
	            
			   			switch(ans)
			   			{
			   			case 'A':
			   				useranswer = rs.getString("A");
			   				break;
			   			case 'B':
			   				useranswer = rs.getString("B");
			   				break;
			   			case 'C':
			   				useranswer = rs.getString("C");
			   				break;
			   			case 'D':
			   				useranswer = rs.getString("D");
			   				break;
			   			default:break;
			   			}
	            
			   			if(useranswer.equals(rs.getString("answer"))) {
			   				System.out.println(" Correct Answer");
			   				totalScore++;
			   				a++;
			   				rightAnswer++;
			   			}
			   			else{
					
			   				System.out.println("Wrong Answer");
			   				wrongAnswer++;
			   				a++;
			   				System.out.println("--------------------------------------------------------------------");
			   			}
			
			   		}
			   		System.out.println("Total right answer :"+rightAnswer);
			   		System.out.println("Total wrong answer :"+wrongAnswer);
			   		System.out.println("Total Score is:"+totalScore);
		    	
		      	
		   // Store Studentname and score into db
			   		ps = connection.prepareStatement("insert into student(Studentname , score)values(?,?)");
			   		ps.setString(1,username);
			   		ps.setInt(2, totalScore);
		    
			   		int r = ps.executeUpdate();
			   		System.out.println("Record is inserted :" +r);
		    
			   		rs.close();
		    
	          // Display the Grade bases on student score
			   		if(totalScore > 8){
			   			System.out.println("  Grade is Class A");
			   		}
			   		else if(totalScore > 6){
			            System.out.println("Grade is Class B");
			        }
			        else if(totalScore == 5) {
			        	System.out.println("Grade is Class C ");
			        }
			        else {
			            System.out.println(" Fail ");
			        }
		   
			   		System.out.println("-------------------------------------------------------------------");
	    		  
		   } catch(Exception e) {
			   System.out.println(e);
		   }finally{
			   connection.close();
			   ps.close();
				}		
	
}
	
public static void main(String[] args) throws SQLException {
  
		QuestionDisplay display = new QuestionDisplay();
    		display.Quiz();
    		
    		
    		DisplayScore rs = new DisplayScore();
    		rs.presentScore();
	}

	
}
