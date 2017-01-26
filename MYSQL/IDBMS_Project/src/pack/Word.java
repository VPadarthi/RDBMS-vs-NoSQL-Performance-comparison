package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Word {
public static void main(String[] args) {
	
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = null;
		String[] tagdata;
		String s = null;
		PreparedStatement ps = null;
		
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/pegasus","root", "Kiran86");
				
				PreparedStatement statement = connection.prepareStatement("select post_id,posted_data from post ");
				System.out.println("After prepare statement");
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next())
				{					
					System.out.println("IN the while loop");
					s=resultSet.getString("posted_data");
					int j=resultSet.getInt("post_id");
					//s="lets check what happens for this in the middle of the";
					String[] splited = s.split("\\s+");
					for(int i=0;i<6;i++)
					{
					System.out.println(splited[i]);
					if(splited[i].equals(""))
					{
						System.out.println("in the if condition");
						
					}
					else{
					String insertTableSQL = "INSERT INTO tag"
							+ "(post_id,tag_name) VALUES"
							+ "(?,?)";
				ps = connection.prepareStatement(insertTableSQL);
					ps.setInt(1,j);
					ps.setString(2,splited[i]);
					ps.executeUpdate();
					}
					
					}
					
					System.out.println("data from string");
					ps.close();
				}
	String str = s;
	String[] splited = str.split("\\s+");
	for(int i=0;i<splited.length;i++)
				
	{
	
	System.out.println(splited[i]);
	}
	}
	catch(Exception e)
	{
		System.out.println("in the catch block");
	}
}

}
