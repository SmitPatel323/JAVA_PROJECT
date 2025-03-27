package DBMS;
import JAVAMAIN.*;
import java.sql.*;
import java.util.*;

public class CompanyPlacement extends Placement {
    ConnectionCollage coc ; 
    
    public CompanyPlacement(int placementId, int enrollmentNo, int passingyear, String studentname, String coursename, String deptname, String companyname, long packageamt) throws Exception {
        super(placementId, enrollmentNo, passingyear, studentname, coursename, deptname, companyname, packageamt);
        coc = new ConnectionCollage();
    }

    public CompanyPlacement() throws Exception {
        coc = new ConnectionCollage();
    }

    public void displayPlacementsByCompany() throws Exception {
        System.out.println("Enter company name to search for placements:");
        Scanner sc = new Scanner(System.in);
        String companyName = sc.next();

        String sql = "SELECT * FROM placement WHERE companyname = ?";
        PreparedStatement pst = coc.con.prepareStatement(sql);
        pst.setString(1, companyName);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Placement ID: " + rs.getInt(1));
            System.out.println("Enrollment No: " + rs.getInt(2));
            System.out.println("Student Name: " + rs.getString(3));
            System.out.println("Course Name: " + rs.getString(4));
            System.out.println("Department: " + rs.getString(5));
            System.out.println("Package Amount: " + rs.getLong(6));
            System.out.println("Company Name: " + rs.getString(7));
            System.out.println("Passing Year: " + rs.getInt(8));
            System.out.println("--------------------------------------------------");
        }
    }
}

