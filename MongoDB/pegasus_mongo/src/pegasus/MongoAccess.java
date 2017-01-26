package pegasus;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.json.JSONObject;


import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

/**
 * Servlet implementation class MongoAccess
 */
@WebServlet("/MongoAccess")
public class MongoAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MongoAccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("in the get method");
		
		String username=request.getParameter("username");
  	    String password=request.getParameter("password");
		
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		

		
		try {
		DB db2 = mongo.getDB("cricketblog");
		DBCollection table = db2.getCollection("user");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("username",username);
		searchQuery.put("password", password);
System.out.println("before the cursor");
		DBCursor cursor = table.find(searchQuery);
		
		if(!cursor.hasNext()){
			
//System.out.println("wrong username or password");
			


			System.out.println("in the if condition");
			
		}
		else
		{
			
			System.out.println("in the else condition");
			//response.sendRedirect("Blog.jsp");
	/*		getServletConfig().getServletContext().getRequestDispatcher(
			        "/Blog.jsp").forward(request,response);*/
			getServletConfig().getServletContext().getRequestDispatcher(
			        "/check.jsp").forward(request,response);
		}
		
		
		while (cursor.hasNext()) {
		//	System.out.println("inside while block");
			//System.out.println(cursor.next());
		}
		} catch (Exception e) {
 	    	 
 	    	//System.out.println(e.printStackTrace(null););
 	    	System.out.println("IN thecatch method");
 	    	//System.out.println("error"+e.printStackTrace());
 	      //throw e;
 	    } finally {
 	      //close();
 	    }
		
		/*try{

			DB db2 = mongo.getDB("cricketblog");
			DBCollection table = db2.getCollection("posts");

			JSONObject jsonObj  = new JSONObject();
			Date insertingdate=new Date();
			String[] tags = {"tag1","tag2","tag3","tag4","tag5"};
			
			
			jsonObj.put("post_id",1);
			jsonObj.put("title","test title");
			jsonObj.put("date",insertingdate);
			jsonObj.put("author","manikanta");
			jsonObj.put("description","test description");
			jsonObj.put("tags",tags);
			
			
			System.out.println(jsonObj);
			DBObject dbObject= (DBObject)JSON.parse(jsonObj.toString());
			//DBObject dbObject1= (DBObject)JSON.parse(a2.toString());
			table.insert(dbObject);
		}
		catch(Exception e)
		{
			System.out.println("you are in insert catch");
		}*/

		
		
		
		

				
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
