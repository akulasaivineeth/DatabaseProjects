package Perform;

import java.io.*;
import java.sql.*;
//import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


//Servlet implementation class editions
@WebServlet("/display")
public class projectTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("tname");

		int i ;
		
		try {
			// OracleDatabase Connection
      	   Connection con = DBConnection.getConnection();
	                      
	            String query = "SELECT * from "+name;
		            
	            PreparedStatement stmt = con.prepareStatement(query);
	
	            //Get the result data
	            ResultSet res = stmt.executeQuery();
	         // Get the ResultSetMetaData.  This will be used for the column headings
	            ResultSetMetaData resMetaData = res.getMetaData();
	      

	    		// Get the number of columns in the result set
	    		int numColumns = resMetaData.getColumnCount();
	    		out.println("<center><h1>Contents of the table are</h1></center>");
	    		// Display column headings
	    		for (i = 1; i <= numColumns; i++) 
	    			
	    		{
	    			if (i > 1) out.print(", ");
	    			   		
	    			out.println(resMetaData.getColumnLabel(i));
	    			
	    		}
	    		out.println("<br>\n-------------------------------------<br>");

	    		while (res.next()) 
	    		{	    		
	    			// column data and displaying
	    			for (i = 1; i <= numColumns; i++) 
	    			{
	    				if (i > 1);
	    				out.println(res.getString(i));
	    				out.print(", ");
	    				
	    			}
	    			out.println("<br>");
	    			
	    		}
	    		
	            //Close the connection
	            con.close();
		}
		catch (Exception e) {
			
	}
	            	         	           
   }
 }

	
