package Perform;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//Servlet implementation 
@WebServlet("/returnDate")
public class returnDate extends HttpServlet{	
  private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
    try{
    	
    	 String id = request.getParameter("id");
         String select = request.getParameter("select");
                  
         String[] inparr = select.split(" ");

        	   // OracleDatabase Connection
        	   Connection con = DBConnection.getConnection();
	                      
	            String query = "select  mem_id, name, date_of_borrow, date_of_borrow+14 as date_of_return "+
	            		"from  FALL22_S003_15_BOOKS "+
	            		"where mem_id = ?";
				            
	            PreparedStatement stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            
	            stmt.setString(1, id);
	            
	            
	            //Get the result along with column names
	            ResultSet res = stmt.executeQuery();
	            ResultSetMetaData resMetaData = res.getMetaData();
	            if (res.next() == false){
	            	out.print(id+" <h3>has not borrowed any book</h3>");
	            }
	            else {
	            	res. beforeFirst();
	            	
	            out.println("<h3><center>BORROW DATE AND RETURN DATE</center></h3>");            	            
	            HashMap<String,String> hm = new HashMap<>();{
	            	
	                while (res.next())
	                {
	                    String memberID = resMetaData.getColumnName(1) + "      "+res.getString("mem_id") ;
	                    String name = resMetaData.getColumnName(2)+"      "+res.getString("name");
	                    String borrowDate = resMetaData.getColumnName(3)+"      "+res.getString("date_of_borrow");
	                    String returnDate = resMetaData.getColumnName(4)+"      "+res.getString("date_of_return");
                                      
	                    hm.put("id", memberID);
	                    hm.put("name",name);
	                    hm.put("borrowdate",borrowDate);
	                    hm.put("returndate", returnDate);
               
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
	            }
	          //Close the connection
	            con.close();	           
	            
	            
	            	
	            
        }
        
    catch (Exception e){
    	out.print("Error :"+e.getMessage());
    }

    }

}


