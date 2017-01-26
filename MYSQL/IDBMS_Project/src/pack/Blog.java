package pack;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	//	System.out.println("in do post method");
		String message = "Hello World";
		
			
		
		
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
		 
		 
		 
	
		// System.out.println("Button selected...................."+button_selected);
		PrintWriter out = response.getWriter();
	//	System.out.println("Please enter correct details..");
		
		String ent_text=request.getParameter("searchtext");
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		//System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

			try {
				   long startTime1 = System.currentTimeMillis();
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/pegasus","root", "Kiran86");
			
 if(selection==1)
	{
	 
	 
	 long startTime3 = System.currentTimeMillis();
	 
		/*	System.out.println("in the selection block 1");

			System.out.println("entered text not empty and title contains");
			System.out.println("in the if condition");*/
			PreparedStatement statement = connection.prepareStatement("select * from post where title like ?");    
			statement.setString(1, "%" + ent_text + "%"); 
	//	System.out.println("After query");
			ResultSet resultSet = statement.executeQuery();
	
		
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>Cricket Blog</title></head>");
			out.println("<body>");
			out.println("<h1>Cricket Blog</h1>");
			out.println("<h2>Your search results...</h2>");
		
	while(resultSet.next())
		{
			
				String posted_data=resultSet.getString("posted_data");
				@SuppressWarnings("unused")
				String post_id = resultSet.getString("post_id");
				String Title = resultSet.getString("Title");
				String username = resultSet.getString("username");
				Date posted_date = resultSet.getDate("posted_date");
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
			i++;
		}
		
		out.println("</body>");
	    out.println("</html>");
	    long startTime4 = System.currentTimeMillis();
	    System.out.println("MySQL Total time when searched in TITLE Content "+(startTime4-startTime3));
	}

 
 // Search in the title and Blog Contents
 
  if(selection==2)
	 {
	  
	  long startTime5 = System.currentTimeMillis();
				//System.out.println("in the if condition where title and blog selected");
			//	System.out.println("blog content"+s_blog);
				PreparedStatement statement = connection.prepareStatement("select * from post where title like ? or posted_data like ?");    
				statement.setString(1, "%" + ent_text + "%"); 
				statement.setString(2, "%" + ent_text + "%"); 

				ResultSet resultSet = statement.executeQuery();
    
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head><title>Cricket Blog</title></head>");
				out.println("<body>");
				out.println("<h1>Cricket Blog</h1>");
				out.println("<h2>Your search results...</h2>");
	
	
		while(resultSet.next())
			{
				String posted_data=resultSet.getString("posted_data");

				@SuppressWarnings("unused")
	      		String post_id = resultSet.getString("post_id");
				String Title = resultSet.getString("Title");
				String username = resultSet.getString("username");
				Date posted_date = resultSet.getDate("posted_date");
	      
	      
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

				i++;
		//System.out.println("i value"+i);
		
			}
	
				out.println("</body>");
				out.println("</html>");
				long startTime6 = System.currentTimeMillis();
				System.out.println(" MySQL Total time when searched in TITLE and Blog Content "+(startTime6-startTime5));
	}
			
  

  
 if(selection==3)
 	{
	 
	 long startTime7 = System.currentTimeMillis();
	 	//		System.out.println("in the if condition where title tag and blog sleected");
		
	 			PreparedStatement statement = connection.prepareStatement("select p.post_id,p.username,p.title,p.posted_date,p.posted_data from post p, tag t where t.tag_name = ? and t.post_id=p.post_id union"
					+ "                          select p.post_id,p.username,p.title,p.posted_date,p.posted_data from post p where p.title like ? or p.posted_Data like ?");
	 			statement.setString(1, ent_text); 
	 			statement.setString(2, "%" + ent_text + "%");
	 			statement.setString(3, "%" + ent_text + "%");
	//	System.out.println("After query");
	 			ResultSet resultSet = statement.executeQuery();
	
	 			out.println("<!DOCTYPE html>");
	 			out.println("<html>");
	 			out.println("<head><title>Cricket Blog</title></head>");
	 			out.println("<body>");
	 			out.println("<h1>Cricket Blog</h1>");
	 			out.println("<h2>Your search results...</h2>");
		
		
	while(resultSet.next())
		{
				String posted_data=resultSet.getString("posted_data");

				@SuppressWarnings("unused")
				String post_id = resultSet.getString("post_id");
				String Title = resultSet.getString("Title");
				String username = resultSet.getString("username");
				Date posted_date = resultSet.getDate("posted_date");
		      
		      
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

				i++;
				//System.out.println("i value"+i);
			
		}
		
				out.println("</body>");
				out.println("</html>");
				 
				long startTime8 = System.currentTimeMillis();
				System.out.println("MySQL Total time when searched in TITLE , Tag and Blog Content "+(startTime8-startTime7)); 
	}
 
 
 //Search in Title Blog  Tag and Author Contents
 
 if(selection==4)
 	{

	 long startTime9 = System.currentTimeMillis();
	 
			//	System.out.println("in the if condition where title tag and blog  author sleected");
	
				PreparedStatement statement = connection.prepareStatement("select posted_data,username,posted_date,title from post p, tag t where t.tag_name = ? and t.post_id=p.post_id union"
				+ "                          select posted_data,username,posted_date,title from post where title like ? or posted_Data like ? union "
						+"select posted_data,username,posted_date,title from post where username=?");
				statement.setString(1, ent_text); 
				statement.setString(2, "%" + ent_text + "%");
				statement.setString(3, "%" + ent_text + "%");
				statement.setString(4, ent_text);

				ResultSet resultSet = statement.executeQuery();
				out.println("<!DOCTYPE html>");
	 			out.println("<html>");
	 			out.println("<head><title>Cricket Blog</title></head>");
	 			out.println("<body>");
	 			out.println("<h1>Cricket Blog</h1>");
	 			out.println("<h2>Your search results...</h2>");

	while(resultSet.next())
		{
		
		
				String posted_data=resultSet.getString("posted_data");

				
				String Title = resultSet.getString("Title");
				String username = resultSet.getString("username");
				Date posted_date = resultSet.getDate("posted_date");
      
      
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
				out.println();
			
		
		}
	
	
	long startTime10 = System.currentTimeMillis();
	System.out.println("MySQL total time when searched in TITLE,Tag,Author and Blog Content "+(startTime10-startTime9));
	 }
	

 
 
/* if(ent_text.isEmpty())
			{
			PreparedStatement statement = connection.prepareStatement("select * from post");    
		
			System.out.println("in the if method");
			ResultSet resultSet = statement.executeQuery();
			out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head><title>Cricket Blog</title></head>");
		    out.println("<body>");
		    out.println("<h1>Cricket Blog</h1>");
		    out.println("<h2>Your search results...</h2>");
			
			
			while(resultSet.next())
			{
				//System.out.println("IN the while loop");
				String posted_data=resultSet.getString("posted_data");

			      @SuppressWarnings("unused")
				String post_id = resultSet.getString("post_id");
			      String Title = resultSet.getString("Title");
			      String username = resultSet.getString("username");
			      Date posted_date = resultSet.getDate("posted_date");
			      
			      
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
		}
			
			out.println("</body>");
		    out.println("</html>");
		    long startTime2 = System.currentTimeMillis();
		    System.out.println("total time is"+(startTime2-startTime1));
			}*/
		//	System.out.println("before the tile");
			//if(!(ent_text.isEmpty())&&s_title.equals("Title")&&!(s_blog.equals("Blog_Content"))&&!(s_tag.equals("Tag"))&&!(s_a.equals("Author")))
				/*if(!(ent_text.isEmpty())&&s_title.equals("Title")&&(s_blog==null)&&(s_a==null)&&(s_tag==null))	
			{
				System.out.println("entered text not empty and title contains");
				System.out.println("in the if condition");
			PreparedStatement statement = connection.prepareStatement("select * from post where title like ?");    
			statement.setString(1, "%" + ent_text + "%"); 
		//	System.out.println("After query");
			ResultSet resultSet = statement.executeQuery();
		
			
			   long startTime3 = System.currentTimeMillis();
			out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head><title>Cricket Blog</title></head>");
		    out.println("<body>");
		    out.println("<h1>Cricket Blog</h1>");
		    out.println("<h2>Your search results...</h2>");
			
			while(resultSet.next())
			{
				
				String posted_data=resultSet.getString("posted_data");

			      String post_id = resultSet.getString("post_id");
			      String Title = resultSet.getString("Title");
			      String username = resultSet.getString("username");
			      Date posted_date = resultSet.getDate("posted_date");
			      
			      
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
				i++;
				//System.out.println("i value"+i);
				
			}
			
			out.println("</body>");
		    out.println("</html>");
		    long startTime4 = System.currentTimeMillis();
		    System.out.println("total time for title "+(startTime4-startTime3));
			}
	
				
*/		
	/*	if(!(ent_text.isEmpty())&&s_title.equals("Title")&&(s_blog.equals("Blog_Content"))&&(s_tag==null)&&(s_a==null))
			{
				System.out.println("in the if condition where title and blog selected");
				System.out.println("blog content"+s_blog);
			PreparedStatement statement = connection.prepareStatement("select * from post where title like ? or posted_data like ?");    
			statement.setString(1, "%" + ent_text + "%"); 
			statement.setString(2, "%" + ent_text + "%"); 
	
			ResultSet resultSet = statement.executeQuery();
		    
			out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head><title>Cricket Blog</title></head>");
		    out.println("<body>");
		    out.println("<h1>Cricket Blog</h1>");
		    out.println("<h2>Your search results...</h2>");
			
			
			while(resultSet.next())
			{
				String posted_data=resultSet.getString("posted_data");

			      @SuppressWarnings("unused")
				String post_id = resultSet.getString("post_id");
			      String Title = resultSet.getString("Title");
			      String username = resultSet.getString("username");
			      Date posted_date = resultSet.getDate("posted_date");
			      
			      
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

				i++;
				//System.out.println("i value"+i);
				
			}
			
			out.println("</body>");
		    out.println("</html>");
			
			
			}*/
			
		//	System.out.println("i value"+i);
			
		/*if(!(ent_text.isEmpty())&&s_title.equals("Title")&&s_tag.equals("Tag")&&(s_blog.equals("Blog_Content"))&&(s_a==null))
			{
				System.out.println("in the if condition where title tag and blog sleected");
			//PreparedStatement statement = connection.prepareStatement("select posted_data from post p, tag t where t.tag_name =? and t.post_id=p.post_id or p.title like ? or p.posted_Data like ?");
				PreparedStatement statement = connection.prepareStatement("select p.post_id,p.username,p.title,p.posted_date,p.posted_data from post p, tag t where t.tag_name = ? and t.post_id=p.post_id union"
						+ "                          select p.post_id,p.username,p.title,p.posted_date,p.posted_data from post p where p.title like ? or p.posted_Data like ?");
			statement.setString(1, ent_text); 
			statement.setString(2, "%" + ent_text + "%");
			statement.setString(3, "%" + ent_text + "%");
		//	System.out.println("After query");
			ResultSet resultSet = statement.executeQuery();
		
			out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head><title>Cricket Blog</title></head>");
		    out.println("<body>");
		    out.println("<h1>Cricket Blog</h1>");
		    out.println("<h2>Your search results...</h2>");
			
			
			while(resultSet.next())
			{
				String posted_data=resultSet.getString("posted_data");

			      @SuppressWarnings("unused")
				String post_id = resultSet.getString("post_id");
			      String Title = resultSet.getString("Title");
			      String username = resultSet.getString("username");
			      Date posted_date = resultSet.getDate("posted_date");
			      
			      
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

				i++;
				System.out.println("i value"+i);
				
			}
			
			out.println("</body>");
		    out.println("</html>");
			
			
			}
			*/
			
/*		if(!(ent_text.isEmpty())&&s_title.equals("Title")&&s_tag.equals("Tag")&&(s_blog.equals("Blog_Content"))&&(s_a.equals("Author")))
		{
			System.out.println("in the if condition where title tag and blog sleected");
		//PreparedStatement statement = connection.prepareStatement("select posted_data from post p, tag t where t.tag_name =? and t.post_id=p.post_id or p.title like ? or p.posted_Data like ?");
			PreparedStatement statement = connection.prepareStatement("select posted_data from post p, tag t where t.tag_name = ? and t.post_id=p.post_id union"
					+ "                          select posted_data from post where title like ? or posted_Data like ?");
		statement.setString(1, ent_text); 
		statement.setString(2, "%" + ent_text + "%");
		statement.setString(3, "%" + ent_text + "%");
	//	System.out.println("After query");
		ResultSet resultSet = statement.executeQuery();
	
		while(resultSet.next())
		{
			//System.out.println("IN the while loop");
			
			String s=resultSet.getString("posted_data");
		//	System.out.println("data"+s);
			out.println(s);
			out.println();
			i++;
			System.out.println("i value"+i);
			
		}
		
		}*/
		
			
		}
		catch(Exception e)
		{
			System.out.println("int he exception");
			e.printStackTrace();
		}

        request.setAttribute("message", message);
		
	}

	
}



