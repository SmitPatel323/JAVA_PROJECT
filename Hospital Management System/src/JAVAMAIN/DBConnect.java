package JAVAMAIN;

import java.sql.*;
public class DBConnect 
{
    String driverName="com.mysql.cj.jdbc.Driver";
    String dbURL = "jdbc:mysql://localhost:3306/hospital";
    String dbUser = "root";
    String dbPass = "";
    public Connection con ;

    public DBConnect() throws Exception
    {
        Class.forName(driverName);
        con=DriverManager.getConnection(dbURL, dbUser, dbPass);
        if(con!=null)
        {
            System.out.println("Connection success");
        }
        else
        {
            System.out.println("Connection failed");
        }
    }
}

