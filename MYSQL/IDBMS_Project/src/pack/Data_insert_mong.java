package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class Data_insert_mong {
	static int id=1;
	public static void main(String[] args) throws UnknownHostException {
		String name="kalamm";
	
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		java.sql.PreparedStatement ps = null;
		
try {
		Class.forName("com.mysql.jdbc.Driver");
	} 
catch (ClassNotFoundException e)
	{
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
try {

	connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/pegasus","root", "Kiran86");
				
	
for(int i=0;i<1000;i++)
	{
			
		DB db2 = mongo.getDB("cricketblog");
					DBCollection table = db2.getCollection("posts");

					JSONObject jsonObj  = new JSONObject();
					Date insertingdate=new Date();
					
				String insertTableSQL = "INSERT INTO post"
						+ "(title,username, posted_date, posted_data) VALUES"
						+ "(?,?,?,?)";
			 ps = connection.prepareStatement(insertTableSQL);
				
				String title=texttitle();
				
				String data=textdata();
				String[] splited = data.split("\\s+");
						
				ArrayList<String> tagdata = new ArrayList<String>();
				for(int k=0;k<5;k++)
				{
					
				System.out.println(splited[i]);
									
				tagdata.add(splited[k+1]);
				
			
				}
				
				
				jsonObj.put("post_id",id++);
				jsonObj.put("title",title);
				jsonObj.put("date",insertingdate);
				jsonObj.put("author",name);
				jsonObj.put("description",data);
				jsonObj.put("tags",tagdata);
				
				System.out.println(jsonObj);
				DBObject dbObject= (DBObject)JSON.parse(jsonObj.toString());
				//DBObject dbObject1= (DBObject)JSON.parse(a2.toString());
				table.insert(dbObject);

				// RDBMS
				ps.setString(1,title);
				
				ps.setString(2,name);
			
				ps.setString(4,data);
			
				ps.setDate(3, java.sql.Date.valueOf("2016-04-15"));
				
				ps.executeUpdate();
		
		}
				ps.close();
	
				
			}
			catch(Exception e)
			{
			System.out.println("Exception");
			e.printStackTrace();
			}
			
	}
		
	public static String textdata()
	{
		ArrayList<String> words = new ArrayList(); 

		Random numGen = new Random(); 
		String sentence="";
		try { 
		File aFile = new File("Dictionary.txt");
		String curWord = "";
		Scanner reader = new Scanner(aFile); 
		while (reader.hasNext()) { 
		curWord = reader.next(); 
		if (curWord.length() <= 6) { 
		words.add(curWord); 
		}

		}


		for(int i = 0; i < 3000; i++)
		{
		try { 
		int rand = Math.abs(numGen.nextInt(words.size())); 
		sentence=sentence+" "+words.get(rand);
		} 
		catch (IllegalArgumentException iae) { 
		System.out.println("There are no results to be shown"); 
		System.exit(1); 
		} 

		} 
		System.out.println(sentence);
		}
		catch (FileNotFoundException e) { 

		System.out.println("Error: " + e); 

		} 
	return sentence;
	}



	public static String texttitle()
	{
		ArrayList<String> words = new ArrayList(); 

		Random numGen = new Random(); 
		String sentence="";
		try { 
		File aFile = new File("Dictionary.txt");
		String curWord = "";
		Scanner reader = new Scanner(aFile); 
		while (reader.hasNext()) { 
		curWord = reader.next(); 
		if (curWord.length() <= 5) { 
		words.add(curWord); 
		}

		}


		for(int i = 0; i < 10; i++)
		{
		try { 
		int rand = Math.abs(numGen.nextInt(words.size())); 
		sentence=sentence+" "+words.get(rand);
		} 
		catch (IllegalArgumentException iae) { 
		System.out.println("There are no results to be shown"); 
		System.exit(1); 
		} 

		} 
		System.out.println(sentence);
		}
		catch (FileNotFoundException e) { 

		System.out.println("Error: " + e); 

		} 
	return sentence;
	}





		
	}

