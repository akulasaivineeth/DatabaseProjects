package Perform;

import java.io.*;
import java.sql.*;
//import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


//Servlet implementation class editions
@WebServlet("/editions")
public class editions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("bname");
		String name1=name.replaceAll("&#[1-9];", "");
		
		//String bnames[] = name.split(" ");
		try {
			// OracleDatabase Connection
      	   Connection con = DBConnection.getConnection();
	                      
	            String query = "SELECT b.name,be.edition "+
	            		"FROM Fall22_S003_15_BOOKS b,FALL22_S003_15_BOOKS_EDITION be "+
	            		"WHERE  b.name= ? AND b.book_id=be.book_id ";
		            
	            PreparedStatement stmt = con.prepareStatement(query);
	            
	            stmt.setString(1, name1);
	
	            //Get the result along with column names
	            ResultSet res = stmt.executeQuery();
	            out.println("<h3><center>EDITIONS OF A BOOK</center></h3>");
            		
	                while (res.next())
	                {
	                    String BookName = res.getString("name") ;
	                    String BookEdition = res.getString("edition");
	                  
	                    out.print("bookName:"+BookName+"  ");
	                    out.print("bookEdition:"+BookEdition);	                                        
	                    out.println("</br>" ); 
	                }
	            
	            //Close the connection
	            con.close();
		}
		catch (Exception e) {
			
	}
	            
	         
	           
   }
 }

	
