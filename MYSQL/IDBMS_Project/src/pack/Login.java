package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		request.setAttribute("servletName", username); // value assigning to send data to next page
		String dbusername;
		String dbpassword=null;
		String role=null;
		//System.out.println("username"+username);
		
		//System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

	//	System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/pegasus","root", "Kiran86");
			
			PreparedStatement statement = connection.prepareStatement("select password from user where username = ?");    
			statement.setString(1, username); 
		//	System.out.println("After query");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				dbpassword=resultSet.getString("password");
				
			/*	System.out.println("login_count"+i);
				System.out.println("phone"+phone);
				System.out.println("password"+dbpassword);
				System.out.println("admin role"+role);*/
			}
		//System.out.println("dbpasswords"+dbpassword);
			if(dbpassword.equals(password))
			{
				
					getServletConfig().getServletContext().getRequestDispatcher(
				        "/Blog.jsp").forward(request,response);
				//System.out.println("after request sent");
				
			}
			else
			{
				
				
				PrintWriter out = response.getWriter();
				System.out.println("Please enter correct details..");
				
			}
		}
		catch(Exception e)
		{
			
			
		}
	}

}
