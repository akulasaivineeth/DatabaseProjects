package Perform;

import java.io.*;
import java.sql.*;
//import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


//Servlet implementation class editions
@WebServlet("/insert")
public class insertNewMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String phno = request.getParameter("phno");
		String address = request.getParameter("address");
		
		//String bnames[] = name.split(" ");
		try {
			// OracleDatabase Connection
      	   Connection con = DBConnection.getConnection();
	                      
	            String query = "INSERT INTO Fall22_S003_15_Members VALUES(?,?,?,?,?)";
		            
	            PreparedStatement stmt = con.prepareStatement(query);
	            
	            stmt.setString(1, id);
	            stmt.setString(2, fname);
	            stmt.setString(3, lname);
	            stmt.setString(4, phno);
	            stmt.setString(5, address);
	
	            
	            stmt.executeUpdate();
	            
	            //Close the connection
	            con.close();
	            
	            out.println("<h3><center>New Member "+id+" " + fname+" "+lname+" has been successfully added</center></h3>");
		}
		catch (Exception e) {
			
	}
	            
	         
	           
   }
 }

	
