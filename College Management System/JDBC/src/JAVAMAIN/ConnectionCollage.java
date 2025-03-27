package JAVAMAIN;

import java.sql.*;

public class ConnectionCollage
{
    String drivername = "com.mysql.cj.jdbc.Driver";

    String dbURL = "jdbc:mysql://localhost:3306/collage";
    String dbUser = "root";
    String dbPass = "";

    public Connection con ;

    public ConnectionCollage() throws Exception
    {
        Class.forName(drivername);

        con = DriverManager.getConnection(dbURL, dbUser, dbPass);
        if(con!=null)
        {
            System.out.println("Connection Success");
        }
        else
        {
            System.out.println("Connextion Failed");
        }
    }
}
