package DBMS;
import JAVAMAIN.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Course {
    int courseid , fees , seats , status;
    String coursename , department , years , prerequisites ;
    ConnectionCollage coc;
    PreparedStatement pst ;
    CheckInputs check = new CheckInputs();
    public Course() throws Exception {
        coc = new ConnectionCollage();
    }
    public Course(int courseid, String coursename, String department, int fees, int seats,String years,
            String prerequisites , int status) throws Exception {
        this.courseid = courseid;
        this.fees = fees;
        this.seats = seats;
        this.status = status;
        this.coursename = coursename;
        this.department = department;
        this.years = years;
        this.prerequisites = prerequisites;
        coc = new ConnectionCollage();
    }
    
    public int getCourseid() {
        return courseid;
    }
    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }
    public int getFees() {
        return fees;
    }
    public void setFees(int fees) {
        this.fees = fees;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCoursename() {
        return coursename;
    }
    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getYears() {
        return years;
    }
    public void setYears(String years) {
        this.years = years;
    }
    public String getPrerequisites() {
        return prerequisites;
    }
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void insertCourse() throws Exception
    {
        String sql = "INSERT INTO course (courseid, coursename, department, fees, seats, years, prerequisites, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, courseid);
        pst.setString(2, coursename);
        pst.setString(3, department);
        pst.setInt(4, fees);
        pst.setInt(5, seats);
        pst.setString(6, years);
        pst.setString(7, prerequisites);
        pst.setInt(8, status);

        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Insert Success");
        } else {
            System.out.println("Insert Failed");
        }
            
          
    }
    public void deleteCourse(int courseid1) throws Exception
    {
        String sql = "DELETE FROM course WHERE courseid = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, courseid1);

        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Delete Success");
        } else {
            System.out.println("Delete Failed");
        }
            
    }
    

    public void displayCourseDetails() throws Exception {
        System.out.println("----------All Course Data---------------");

        String sql = "SELECT * FROM active_courses ORDER BY courseid";

        pst = coc.con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Course ID: " + rs.getInt(1));
            System.out.println("Course Name: " + rs.getString(2));
            System.out.println("Department: " + rs.getString(3));
            System.out.println("Fees: " + rs.getInt(4));
            System.out.println("Seats: " + rs.getInt(5));
            System.out.println("Years: " + rs.getString(6));
            System.out.println("Prerequisites: " + rs.getString(7));
            System.out.println("Status: " + rs.getInt(8));
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }

    public void displayCourse() throws Exception {
        System.out.println("----------All Course Data---------------");

        String sql = "SELECT * FROM course ORDER BY courseid";

        pst = coc.con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Course ID: " + rs.getInt(1));
            System.out.println("Course Name: " + rs.getString(2));
            System.out.println("Department: " + rs.getString(3));
            System.out.println("Fees: " + rs.getInt(4));
            System.out.println("Seats: " + rs.getInt(5));
            System.out.println("Years: " + rs.getString(6));
            System.out.println("Prerequisites: " + rs.getString(7));
            System.out.println("Status: " + rs.getInt(8));
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }

    public void displayCourseByName(String coursename) throws Exception {
        System.out.println("----------All Course Data---------------");

        String sql = "SELECT * FROM course where coursename = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setString(1, coursename);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Course ID: " + rs.getInt(1));
            System.out.println("Course Name: " + rs.getString(2));
            System.out.println("Department: " + rs.getString(3));
            System.out.println("Fees: " + rs.getInt(4));
            System.out.println("Seats: " + rs.getInt(5));
            System.out.println("Years: " + rs.getString(6));
            System.out.println("Prerequisites: " + rs.getString(7));
            System.out.println("Status: " + rs.getInt(8));
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }

    public void updateCourseDetails(int courseId) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Update details---");
        System.out.println();

        String checkSql = "SELECT * FROM course WHERE courseid = ?";
        pst = coc.con.prepareStatement(checkSql);
        pst.setInt(1, courseId);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("Course with ID " + courseId + " not found.");
            return;
        }

        System.out.println("Press 1 to update Course Name");
        System.out.println("Press 2 to update Department");
        System.out.println("Press 3 to update Fees");
        System.out.println("Press 4 to update Seats");
        System.out.println("Press 5 to update Years");
        System.out.println("Press 6 to update Prerequisites");
        System.out.println("Press 7 to update Status");
        int choice = check.checkInput();
        int newStatus = 0  ;
        int newFees = 0;

        String updateSql = "";
        switch (choice) {
            case 1:
                System.out.print("Enter new Course Name: ");
                String newCourseName = sc.nextLine();
                updateSql = "UPDATE course SET coursename = ? WHERE courseid = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newCourseName);
                pst.setInt(2, courseId);
                break;
            case 2:
                System.out.print("Enter new Department: ");
                String newDepartment = sc.nextLine();
                updateSql = "UPDATE course SET department = ? WHERE courseid = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newDepartment);
                pst.setInt(2, courseId);
                break;
            case 3:
                System.out.print("Enter new Fees: ");
                newFees = sc.nextInt();
                updateSql = "UPDATE course SET fees = ? WHERE courseid = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newFees);
                pst.setInt(2, courseId);
                break;
            case 4:
                System.out.print("Enter new Seats: ");
                int newSeats = sc.nextInt();
                updateSql = "UPDATE course SET seats = ? WHERE courseid = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newSeats);
                pst.setInt(2, courseId);
                break;
            case 5:
                System.out.print("Enter new Years: ");
                String newYears = sc.nextLine();
                updateSql = "UPDATE course SET years = ? WHERE courseid = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newYears);
                pst.setInt(2, courseId);
                break;
            case 6:
                System.out.print("Enter new Prerequisites: ");
                String newPrerequisites = sc.nextLine();
                updateSql = "UPDATE course SET prerequisites = ? WHERE courseid = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newPrerequisites);
                pst.setInt(2, courseId);
                break;
            case 7:
                System.out.print("Enter new Status (1 for active, 0 for inactive): ");
                newStatus = sc.nextInt();
                updateSql = "UPDATE course SET status = ? WHERE courseid = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newStatus);
                pst.setInt(2, courseId);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
            }

            int updateRs = pst.executeUpdate();
            if (updateRs > 0) {
                System.out.println("Update Success");
            } else {
                System.out.println("Update Failed");
            }
            
    }

}

