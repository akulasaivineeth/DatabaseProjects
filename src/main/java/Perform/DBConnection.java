package Perform;
//This code is based on code from Jake Olefsky,
import java.sql.*;

public class DBConnection 
    {
        public static String username = new String("sxa8631");
        public static String password = new String("Hgd3sd4f7hTdrd8s"); 
    
        static public Connection getConnection() {
            Connection con = null;
try{
    //Register the driver
    Class.forName("oracle.jdbc.driver.OracleDriver");
} catch(ClassNotFoundException e){}
try{
    //get the connection 
    con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@acaddbprod.uta.edu:1523/pcse1p.data.uta.edu", username, password);
}catch(Exception e){}

return con;
}
    }

