package DBMS;
import JAVAMAIN.*;
import java.sql.*;
import java.util.Scanner;

public class Marksheet {
    int marksheet_id, enrollmentNo ,semester,status ;
    String name , coursename , grade , academic_year, batch;
    float spi;
    ConnectionCollage coc;
    PreparedStatement pst;
    CheckInputs check = new CheckInputs();
    public Marksheet() throws Exception {
        coc = new ConnectionCollage();

    }

    public Marksheet(String batch ,int enrollmentNo, int semester, String name, String coursename, String grade,
            String academic_year, float spi,int status) throws Exception {
        this.batch = batch;
        this.enrollmentNo = enrollmentNo;
        this.semester = semester;
        this.name = name;
        this.coursename = coursename;
        this.grade = grade;
        this.academic_year = academic_year;
        this.spi = spi;
        this.status = status;
        
        coc = new ConnectionCollage();
    }
    

    public Marksheet( String coursename,int semester, String grade, float spi, String academic_year,int status) throws Exception {
        this.status = status;
        this.semester = semester;
        this.coursename = coursename;
        this.grade = grade;
        this.academic_year = academic_year;
        this.spi = spi;
        coc = new ConnectionCollage();
    }

    public int getMarksheet_id() {
        return marksheet_id;
    }

    public void setMarksheet_id(int marksheet_id) {
        this.marksheet_id = marksheet_id;
    }

    public int getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(int enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public float getSpi() {
        return spi;
    }

    public void setSpi(float spi) {
        this.spi = spi;
    }
    
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void insertMarks(int enrollmentNo) throws Exception
    {
        String checkSql = "SELECT COUNT(*) FROM marksheet WHERE enrollmentNo = ?";
        pst = coc.con.prepareStatement(checkSql);
        pst.setInt(1, enrollmentNo);
        ResultSet rs = pst.executeQuery();
        
        rs.next();
        int count = rs.getInt(1);
        
        if (count > 0) {
            
            String sql = "Update marksheet set coursename = ? , semester = ? , grade = ? , spi = ? , academic_year=? , status =?  where enrollmentNo = ?";
    
            pst = coc.con.prepareStatement(sql);
            pst.setString(1, coursename);
            pst.setInt(2, semester);
            pst.setString(3, grade);
            pst.setFloat(4, spi);
            pst.setString(5, academic_year);
            pst.setInt(6, status);
            pst.setInt(7, enrollmentNo);


            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Attendance record inserted successfully.");
            } else {
                System.out.println("Failed to insert attendance record.");
            }
        } 
        else {
            System.out.println("Enrollment number does not exist.");
        }
    }
    
    public void deleteMarksheet(int enrollmentNo) throws Exception
    {
        String sql = "DELETE FROM marksheet WHERE enrollmentNo = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, enrollmentNo);

        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Delete Success");
        } else {
            System.out.println("Delete Failed");
        }

    }

    public void displayMarksheet() throws Exception
    {
        System.out.println("----------All Marksheet Data---------------");

        String sql = "SELECT * FROM marksheet ORDER BY marksheet_id";

        pst = coc.con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Marksheet ID: " + rs.getInt(1));
            System.out.println("Batch :"+rs.getString(2)); 
            System.out.println("Enrollment No: " + rs.getInt(3));
            System.out.println("Student Name: " + rs.getString(4));
            System.out.println("Course Name: " + rs.getString(5)); 
            System.out.println("Semester: " + rs.getInt(6));
            System.out.println("Grade: " + rs.getString(7)); 
            System.out.println("SPI: " + rs.getFloat(8));
            System.out.println("Academic Year: " + rs.getString(9)); 
            System.out.println("Status: " + rs.getInt(10));
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }

    public void changeToPass(int enrollmentNo) throws Exception {
        String sql = "UPDATE marksheet SET status = 1 WHERE marksheet_id = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, marksheet_id);

        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Status changed to Pass");
        } else {
            System.out.println("Failed to change status to Pass");
        }
    }

    public void changeToFail(int enrollmentNo) throws Exception {
        String sql = "UPDATE marksheet SET status = 0 WHERE marksheet_id = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, marksheet_id);

        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Status changed to Fail");
        } else {
            System.out.println("Failed to change status to Fail");
        }
    }

    public void searchMarksheet(int enrollmentNo) throws Exception
    {
        String sql = "SELECT * FROM marksheet WHERE enrollmentNo = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, enrollmentNo);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            System.out.println("Marksheet ID: " + rs.getInt(1));
            System.out.println("Batch: " + rs.getString(2));
            System.out.println("Enrollment No: " + rs.getInt(3));
            System.out.println("Student Name: " + rs.getString(4));
            System.out.println("Course Name: " + rs.getString(5));
            System.out.println("Semester: " + rs.getInt(6));
            System.out.println("Grade: " + rs.getString(7));
            System.out.println("SPI: " + rs.getFloat(8));
            System.out.println("Academic Year: " + rs.getString(9));
            System.out.println("Status: " + rs.getInt(10));
        } else {
            System.out.println("Marksheet not found for Enrollment No: " + enrollmentNo);
        }
    }

    public void searchMarksheetBySPI(float spi) throws Exception
    {
        String sql = "{CALL searchMarksheetBySPI(?)}";
            CallableStatement cst = coc.con.prepareCall(sql);
            cst.setFloat(1, spi);

        ResultSet rs = cst.executeQuery();
        if (rs.next()) {
            System.out.println("Marksheet ID: " + rs.getInt(1));
            System.out.println("Batch: " + rs.getString(2));
            System.out.println("Enrollment No: " + rs.getInt(3));
            System.out.println("Student Name: " + rs.getString(4));
            System.out.println("Course Name: " + rs.getString(5));
            System.out.println("Semester: " + rs.getInt(6));
            System.out.println("Grade: " + rs.getString(7));
            System.out.println("SPI: " + rs.getFloat(8));
            System.out.println("Academic Year: " + rs.getString(9));
            System.out.println("Status: " + rs.getInt(10));
        } else {
            System.out.println("Marksheet not found for Enrollment No: " + enrollmentNo);
        }
    }

    public void updateMarksheet(int enrollment) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Update Marksheet Details------");
        System.out.println();

        String checkSql = "SELECT * FROM marksheet WHERE enrollmentNo = ?";
        pst = coc.con.prepareStatement(checkSql);
        pst.setInt(1, enrollment);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("No record found for Marksheet ID: " + enrollmentNo);
            return;
        }

        System.out.println("Press 1 to update Course Name");
        System.out.println("Press 2 to update Semester");
        System.out.println("Press 3 to update Grade");
        System.out.println("Press 4 to update SPI");
        System.out.println("Press 5 to update Academic Year");
        System.out.println("Press 6 to update Status");
        int choice = check.checkInput();
        sc.nextLine();

        String updateSql = "";
        switch (choice) {
            case 1:
                System.out.print("Enter new Course Name: ");
                String newCourseName = sc.nextLine();
                updateSql = "UPDATE marksheet SET coursename = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newCourseName);
                pst.setInt(2, enrollment);
                break;
            case 2:
                System.out.print("Enter new Semester: ");
                int newSemester = sc.nextInt();
                updateSql = "UPDATE marksheet SET semester = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newSemester);
                pst.setInt(2, enrollment);
                break;
            case 3:
                System.out.print("Enter new Grade: ");
                String newGrade = sc.nextLine();
                updateSql = "UPDATE marksheet SET grade = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newGrade);
                pst.setInt(2, enrollment);
                break;
            case 4:
                System.out.print("Enter new SPI: ");
                float newSpi = sc.nextFloat();
                updateSql = "UPDATE marksheet SET spi = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setFloat(1, newSpi);
                pst.setInt(2, enrollment);
                break;
            case 5:
                System.out.print("Enter new Academic Year: ");
                String newAcademicYear = sc.nextLine();
                updateSql = "UPDATE marksheet SET academic_year = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newAcademicYear);
                pst.setInt(2, enrollment);
                break;
            case 6:
                System.out.print("Enter new Status (1 for Pass, 0 for Fail): ");
                int newStatus = sc.nextInt();
                updateSql = "UPDATE marksheet SET status = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newStatus);
                pst.setInt(2, enrollment);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        int updateRs = pst.executeUpdate();
        if (updateRs > 0) {
            System.out.println("Marksheet record updated successfully.");
        } else {
            System.out.println("Failed to update marksheet record.");
        }
    }
}
