package Perform;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//Servlet implementation 
@WebServlet("/fetchdata")
public class maxPenality extends HttpServlet{	
  private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
    try{
    	
         String Rows = request.getParameter("Rows");
         String select = request.getParameter("select");
                  
         String[] inparr = select.split(" ");
           try
           {
        	   //validating input is an integer
        	   Integer.parseInt(Rows);
        	   // OracleDatabase Connection
        	   Connection con = DBConnection.getConnection();
	                      
	            String query = "SELECT *"+
	            " FROM Fall22_S003_15_MEMBERS"+
	            " WHERE mem_id in      (SELECT mem_id FROM(SELECT mem_id, MAX(amount) as MAX_PENALTY"+
	                                    " FROM Fall22_S003_15_MEMBERS_RETURNS_BOOKS"+
	                                    " where amount is not null"+
	                                    " Group by mem_id order by MAX_PENALTY desc Fetch First ? rows only))";
		            
	            PreparedStatement stmt = con.prepareStatement(query);
	            
	            stmt.setString(1, Rows);
	            
	            //Get the result along with column names
	            ResultSet res = stmt.executeQuery();
	            ResultSetMetaData resMetaData = res.getMetaData();
	            out.println("<center>MAXIMUM PENANTY</center>");            	            
	            HashMap<String,String> hm = new HashMap<>();{
	            			
	                while (res.next())
	                {
	                    String memberID = resMetaData.getColumnName(1) + "      "+res.getString("mem_id") ;
	                    String firstName = resMetaData.getColumnName(2)+"      "+res.getString("fname");
	                    String lastName = resMetaData.getColumnName(3)+"      "+res.getString("lname");
	                    String phoneNumber = resMetaData.getColumnName(4)+"      "+res.getString("ph_no");
	                    String address = resMetaData.getColumnName(5)+"      "+res.getString("address");                    
	                   
	                    hm.put("id", memberID);
	                    hm.put("fname",firstName);
	                    hm.put("lname",lastName);
	                    hm.put("mobile", phoneNumber);
	                    hm.put("address", address);
	                    	                                        
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
           catch(Exception e) {
        	   out.print("Please enter a number");
           }
            
        }
        
    catch (Exception e){
    	out.print("Error :"+e.getMessage());
    }

    }

}

