package Perform;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


//Servlet implementation class editions
@WebServlet("/maxBooksBorrowed")
public class maxBooksBorrowed extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String select = request.getParameter("select");
		
		String inparr[] = select.split(" ");
		try {
			// OracleDatabase Connection
      	   Connection con = DBConnection.getConnection();
	                      
	            String query = "select DISTINCT B.BOOK_ID, B.name, B.DATE_OF_BORROW "+
	            		"from FALL22_S003_15_BOOKS B "+
	            		"where B.DATE_OF_BORROW = "+
	            							"(select date_of_borrow "+
	            		                    "from FALL22_S003_15_BOOKS "+
	            		                    "group by date_of_borrow "+
	            		                    "having count(date_of_borrow) = ALL (select max(count(date_of_borrow)) "+
	            		                                                         "from FALL22_S003_15_BOOKS "+
	            		                                                         "group by date_of_borrow)) ";
	            		
		            
	            PreparedStatement stmt = con.prepareStatement(query);
	            	
	            //Get the result along with column names
	            ResultSet res = stmt.executeQuery();
	            ResultSetMetaData resMetaData = res.getMetaData();
	            
	            HashMap<String,String> hm = new HashMap<>();{
	            	out.println("<h3><center>BORROWED MORE THAN 1 TIME</center></h3>");	
	                while (res.next())
	                {
	                    String bookID = resMetaData.getColumnName(1) + "      "+res.getString("BOOK_ID") ;
	                    String bookName = resMetaData.getColumnName(2)+"      "+res.getString("name");
	                    String borrowDate = resMetaData.getColumnName(3)+"      "+res.getString("DATE_OF_BORROW");
                                   
	                    hm.put("bid", bookID);
	                    hm.put("bname",bookName);
	                    hm.put("borrowdate",borrowDate);

	                    	                                        
	                    for(String x : inparr)
	                    {	                    	
	                        out.print("<li>"+hm.get(x)+"</li>");	                        	                        
	                    }
	                    
	                    out.println("<ul>");
	                    //out.println("</br>" ); 
	                    out.println("</ul>");
	                    out.println("<div style=margin-left:25%;padding:1px 16px;height:1000px;>");
                        out.println("</div>");
	                }
	            }
	            
	            //Close the connection
	            con.close();
		}
		catch (Exception e) {
			
	}
	            
	         
	           
   }
 }

	
