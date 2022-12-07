package Perform;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//Servlet implementation 
@WebServlet("/cube")
public class cube extends HttpServlet{	
  private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
    try{
    	
         String select = request.getParameter("select");
                  
         String[] inparr = select.split(" ");

        	   // OracleDatabase Connection
        	   Connection con = DBConnection.getConnection();
	                      
	            String query = "SELECT mem_id, book_id, SUM(amount) as TOTAL\n"
	            		+ "FROM Fall22_S003_15_MEMBERS_RETURNS_BOOKS\n"
	            		+ "WHERE amount IS NOT NULL\n"
	            		+ "GROUP BY CUBE(mem_id,book_id)";
				            
	            PreparedStatement stmt = con.prepareStatement(query);
	                       
	            //Get the result along with column names
	            ResultSet res = stmt.executeQuery();
	            ResultSetMetaData resMetaData = res.getMetaData();
	            out.println("<h3><center>total amount for all possible combinations of the memberid and bookid</center></h3>");            	            
	            HashMap<String,String> hm = new HashMap<>();{
	            		
	                while (res.next())
	                {
	                    String memberID = resMetaData.getColumnName(1) + "      "+res.getString("mem_id") ;
	                    String book_id = resMetaData.getColumnName(2)+"      "+res.getString("book_id");
	                    String TOTAL = resMetaData.getColumnName(3)+"      "+res.getString("TOTAL");
                                      
	                    hm.put("id", memberID);
	                    hm.put("bid",book_id);
	                    hm.put("total",TOTAL);
               
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
        
    catch (Exception e){
    	out.print("Error :"+e.getMessage());
    }

    }

}

