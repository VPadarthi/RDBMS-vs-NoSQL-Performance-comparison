package pegasus;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Blog
 */
@WebServlet("/Blog")
public class Blog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Blog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//System.out.println("in do post method");
		String message = "Hello World";
		
	/*	String name=request.getParameter("fname");
		System.out.println("selected fname is "+name);*/
		
		
		
		int i=0;
		int selection=0;
		 int button_selected = Integer.parseInt(request.getParameter("Query selected"));
		
 switch(button_selected)
	 {
		 
		 case 1:
			 		selection=1;
			 		//System.out.println("in the switch case 1");
			 		break;
		 case 2:
			 		selection=2;
			 		//System.out.println("in the switch case 2");
			 		break;
		 case 3:
			 		selection=3;
			 		//System.out.println("in the switch case 3");
			 		break;
		 case 4:
			 		selection=4;
			 	//	System.out.println("in the switch case 4");
			 		break;
			
		 default:
			 		selection=0;
			 		break;		 
		 
		 
	}
		 
		 
		 
	
	//	 System.out.println("Button selected...................."+button_selected);
		PrintWriter out = response.getWriter();
	//	System.out.println("Please enter correct details..");
		
		String ent_text=request.getParameter("searchtext");
	//	System.out.println("enteretd text"+ent_text);


		//System.out.println("MySQL JDBC Driver Registered!");
	

			try {
				   long startTime1 = System.currentTimeMillis();
		
			
 if(selection==1)
	{
			
	 long startTime3 = System.currentTimeMillis();
	 MongoClient mongo = new MongoClient( "localhost" , 27017 );
	 //DB db = mongo.getDB("cricketblog");
	 
	 MongoDatabase db2 = mongo.getDatabase("cricketblog");
	 //DBCollection table = db.getCollection("posts");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("title", java.util.regex.Pattern.compile(ent_text));

		//DBCursor cursor = table.find(searchQuery);
		
		FindIterable<Document> iterable = db2.getCollection("posts").find(searchQuery);
                   //new Document("title",java.util.regex.Pattern.compile(ent_text)));
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>Cricket Blog</title></head>");
		out.println("<body>");
		out.println("<h1>Cricket Blog</h1>");
		out.println("<h2>Your search results...</h2>");
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		
		    	//System.out.println(document);
		    	String Title=document.getString("title");
		    	String username=document.getString("author");
		    	String posted_date=document.getString("date");
		    	String posted_data=document.getString("description");
		    	out.print("<b>");
				out.print("Title: "+Title);
				out.print("</b>");
				out.print("<br>");
				out.print("by ");
				out.print("<i>");
				out.print(username);
				out.print("</i>");
				out.print(" - "+posted_date);
				out.print("<br>");
				out.print("<br>");
				out.print(posted_data);
				out.print("<br>");
				out.print("<hr>");
				out.print("<br>");
				out.print("<br>");
		
		    }});
		out.println("</body>");
	    out.println("</html>");
		
	    long startTime4 = System.currentTimeMillis();
	    System.out.println("Mongo Total time when searched in TITLE Content "+(startTime4-startTime3));
	
		
	
	
	
	}
		

 
  if(selection==2)
	 {

	  long startTime5 = System.currentTimeMillis();
	  
		 MongoClient mongo = new MongoClient( "localhost" , 27017 );
		 //DB db = mongo.getDB("cricketblog");
		 
		 MongoDatabase db2 = mongo.getDatabase("cricketblog");
		 //DBCollection table = db.getCollection("posts");

			BasicDBObject searchQuery = new BasicDBObject();
			BasicDBObject searchQuery2 = new BasicDBObject();
			//BasicDBObject searchQuery3 = new BasicDBObject();
			searchQuery.put("title", java.util.regex.Pattern.compile(ent_text));
			searchQuery2.put("description", java.util.regex.Pattern.compile(ent_text));
			//searchQuery3.put("tags", java.util.regex.Pattern.compile(ent_text));
			
			BasicDBList or=new BasicDBList();
			or.add(searchQuery);
			or.add(searchQuery2);
			
			BasicDBObject Query = new BasicDBObject("$or",or);

			//DBCursor cursor = table.find(searchQuery);
			
			FindIterable<Document> iterable = db2.getCollection("posts").find(Query);
	                   //new Document("title",java.util.regex.Pattern.compile(ent_text)));
			
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>Cricket Blog</title></head>");
			out.println("<body>");
			out.println("<h1>Cricket Blog</h1>");
			out.println("<h2>Your search results...</h2>");
			
			iterable.forEach(new Block<Document>() {
			    @Override
			    public void apply(final Document document) {
			
			    	//System.out.println(document);
			    	String Title=document.getString("title");
			    	String username=document.getString("author");
			    	String posted_date=document.getString("date");
			    	String posted_data=document.getString("description");
			    	out.print("<b>");
					out.print("Title: "+Title);
					out.print("</b>");
					out.print("<br>");
					out.print("by ");
					out.print("<i>");
					out.print(username);
					out.print("</i>");
					out.print(" - "+posted_date);
					out.print("<br>");
					out.print("<br>");
					out.print(posted_data);
					out.print("<br>");
					out.print("<hr>");
					out.print("<br>");
					out.print("<br>");
			
			    }});
			out.println("</body>");
		    out.println("</html>");
			
		    long startTime6 = System.currentTimeMillis();
			
		    System.out.println(" Mongo Total time when searched in TITLE and Blog Content "+(startTime6-startTime5));
	 }
			
  

  
 if(selection==3)
 	{

	 long startTime7 = System.currentTimeMillis();
		
	 MongoClient mongo = new MongoClient( "localhost" , 27017 );
	 //DB db = mongo.getDB("cricketblog");
	 
	 MongoDatabase db2 = mongo.getDatabase("cricketblog");
	 //DBCollection table = db.getCollection("posts");

		BasicDBObject searchQuery = new BasicDBObject();
		BasicDBObject searchQuery2 = new BasicDBObject();
		BasicDBObject searchQuery3 = new BasicDBObject();
		searchQuery.put("title", java.util.regex.Pattern.compile(ent_text));
		searchQuery2.put("description", java.util.regex.Pattern.compile(ent_text));
		searchQuery3.put("tags", java.util.regex.Pattern.compile(ent_text));
		
		BasicDBList or=new BasicDBList();
		or.add(searchQuery);
		or.add(searchQuery2);
		or.add(searchQuery3);
		
		BasicDBObject Query = new BasicDBObject("$or",or);

		//DBCursor cursor = table.find(searchQuery);
		
		FindIterable<Document> iterable = db2.getCollection("posts").find(Query);
                   //new Document("title",java.util.regex.Pattern.compile(ent_text)));
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>Cricket Blog</title></head>");
		out.println("<body>");
		out.println("<h1>Cricket Blog</h1>");
		out.println("<h2>Your search results...</h2>");
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		
		    	//System.out.println(document);
		    	String Title=document.getString("title");
		    	String username=document.getString("author");
		    	String posted_date=document.getString("date");
		    	String posted_data=document.getString("description");
		    	out.print("<b>");
				out.print("Title: "+Title);
				out.print("</b>");
				out.print("<br>");
				out.print("by ");
				out.print("<i>");
				out.print(username);
				out.print("</i>");
				out.print(" - "+posted_date);
				out.print("<br>");
				out.print("<br>");
				out.print(posted_data);
				out.print("<br>");
				out.print("<hr>");
				out.print("<br>");
				out.print("<br>");
		
		    }});
		out.println("</body>");
	    out.println("</html>");
		
		long startTime8 = System.currentTimeMillis();
		System.out.println("Mongo Total time when searched in TITLE , Tag and Blog Content "+(startTime8-startTime7));
		
 	}
 
 
 //Search in Title Blog  Tag and Author Contents
 
 if(selection==4)
 	{


	 long startTime9 = System.currentTimeMillis();
		
	 MongoClient mongo = new MongoClient( "localhost" , 27017 );
	 //DB db = mongo.getDB("cricketblog");
	 
	 MongoDatabase db2 = mongo.getDatabase("cricketblog");
	 //DBCollection table = db.getCollection("posts");

		BasicDBObject searchQuery = new BasicDBObject();
		BasicDBObject searchQuery2 = new BasicDBObject();
		BasicDBObject searchQuery3 = new BasicDBObject();
		BasicDBObject searchQuery4 = new BasicDBObject();
		searchQuery.put("title", java.util.regex.Pattern.compile(ent_text));
		searchQuery2.put("description", java.util.regex.Pattern.compile(ent_text));
		searchQuery3.put("tags", java.util.regex.Pattern.compile(ent_text));
		searchQuery4.put("author", java.util.regex.Pattern.compile(ent_text));
		
		BasicDBList or=new BasicDBList();
		or.add(searchQuery);
		or.add(searchQuery2);
		or.add(searchQuery3);
		or.add(searchQuery4);
		
		BasicDBObject Query = new BasicDBObject("$or",or);

		//DBCursor cursor = table.find(searchQuery);
		
		FindIterable<Document> iterable = db2.getCollection("posts").find(Query);
                   //new Document("title",java.util.regex.Pattern.compile(ent_text)));
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>Cricket Blog</title></head>");
		out.println("<body>");
		out.println("<h1>Cricket Blog</h1>");
		out.println("<h2>Your search results...</h2>");
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		
		    	//System.out.println(document);
		    	String Title=document.getString("title");
		    	String username=document.getString("author");
		    	String posted_date=document.getString("date");
		    	String posted_data=document.getString("description");
		    	out.print("<b>");
				out.print("Title: "+Title);
				out.print("</b>");
				out.print("<br>");
				out.print("by ");
				out.print("<i>");
				out.print(username);
				out.print("</i>");
				out.print(" - "+posted_date);
				out.print("<br>");
				out.print("<br>");
				out.print(posted_data);
				out.print("<br>");
				out.print("<hr>");
				out.print("<br>");
				out.print("<br>");
		
		    }});
		out.println("</body>");
	    out.println("</html>");
	    long startTime10 = System.currentTimeMillis();
		System.out.println("Mongo total time when searched in TITLE,Tag,Author and Blog Content "+(startTime10-startTime9));
	
 	}
	
 
 			
		}
		catch(Exception e)
		{
			System.out.println("in the exception");
			e.printStackTrace();
		}

        request.setAttribute("message", message);
		
	}

	
}



