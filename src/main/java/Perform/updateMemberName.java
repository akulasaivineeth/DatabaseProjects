package Perform;

import java.io.*;
import java.sql.*;
//import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


//Servlet implementation class editions
@WebServlet("/update")
public class updateMemberName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("fname");
		String memberID = request.getParameter("id");
		
		
		//String bnames[] = name.split(" ");
		try {
			// OracleDatabase Connection
      	   Connection con = DBConnection.getConnection();
	                      
	            String query = "UPDATE Fall22_S003_15_Members "+
	            				"SET fname = ? "+
	            				"WHERE  mem_id = ? ";
		            
	            PreparedStatement stmt = con.prepareStatement(query);
	            
	            stmt.setString(1, name);
	            stmt.setString(2, memberID);
	
	            
	            stmt.executeUpdate();
	            
	            //Close the connection
	            con.close();
	            
	            out.println("<h3><center>"+ memberID+"had been successfully added</center></h3>");
		}
		catch (Exception e) {
			
	}
	            
	         
	           
   }
 }

	
