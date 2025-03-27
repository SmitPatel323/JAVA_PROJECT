package DBMS;
import JAVAMAIN.*;
import java.sql.*;
import java.util.*;

public class Timetable {
    int sr_no ;
    String day , lec_time , subject_D1 , faculty_D1 ,classroom_D1  , subject_D2  ,faculty_D2, classroom_D2 ,subject_D3  ,faculty_D3 , classroom_D3 ; 

    ConnectionCollage coc ;
    PreparedStatement pst;
    CheckInputs check = new CheckInputs();

    public Timetable() throws Exception{
        coc = new ConnectionCollage();
    }
    public Timetable(String day, String lec_time, String subject_D1, String faculty_D1, String classroom_D1,
            String subject_D2, String faculty_D2, String classroom_D2, String subject_D3, String faculty_D3,
            String classroom_D3) throws Exception{
        this.day = day;
        this.lec_time = lec_time;
        this.subject_D1 = subject_D1;
        this.faculty_D1 = faculty_D1;
        this.classroom_D1 = classroom_D1;
        this.subject_D2 = subject_D2;
        this.faculty_D2 = faculty_D2;
        this.classroom_D2 = classroom_D2;
        this.subject_D3 = subject_D3;
        this.faculty_D3 = faculty_D3;
        this.classroom_D3 = classroom_D3;
        coc = new ConnectionCollage();
    }
    public int getSr_no() {
        return sr_no;
    }
    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getLec_time() {
        return lec_time;
    }
    public void setLec_time(String lec_time) {
        this.lec_time = lec_time;
    }
    public String getSubject_D1() {
        return subject_D1;
    }
    public void setSubject_D1(String subject_D1) {
        this.subject_D1 = subject_D1;
    }
    public String getFaculty_D1() {
        return faculty_D1;
    }
    public void setFaculty_D1(String faculty_D1) {
        this.faculty_D1 = faculty_D1;
    }
    public String getClassroom_D1() {
        return classroom_D1;
    }
    public void setClassroom_D1(String classroom_D1) {
        this.classroom_D1 = classroom_D1;
    }
    public String getSubject_D2() {
        return subject_D2;
    }
    public void setSubject_D2(String subject_D2) {
        this.subject_D2 = subject_D2;
    }
    public String getFaculty_D2() {
        return faculty_D2;
    }
    public void setFaculty_D2(String faculty_D2) {
        this.faculty_D2 = faculty_D2;
    }
    public String getClassroom_D2() {
        return classroom_D2;
    }
    public void setClassroom_D2(String classroom_D2) {
        this.classroom_D2 = classroom_D2;
    }
    public String getSubject_D3() {
        return subject_D3;
    }
    public void setSubject_D3(String subject_D3) {
        this.subject_D3 = subject_D3;
    }
    public String getFaculty_D3() {
        return faculty_D3;
    }
    public void setFaculty_D3(String faculty_D3) {
        this.faculty_D3 = faculty_D3;
    }
    public String getClassroom_D3() {
        return classroom_D3;
    }
    public void setClassroom_D3(String classroom_D3) {
        this.classroom_D3 = classroom_D3;
    }

    public void insertTimetable() throws SQLException {
        String sql = "INSERT INTO timetable (day, lec_time, subject_D1, faculty_D1, classroom_D1, subject_D2, faculty_D2, classroom_D2, subject_D3, faculty_D3, classroom_D3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) order by day";
        pst = coc.con.prepareStatement(sql);
        pst.setString(1, day);
        pst.setString(2, lec_time);
        pst.setString(3, subject_D1);
        pst.setString(4, faculty_D1);
        pst.setString(5, classroom_D1);
        pst.setString(6, subject_D2);
        pst.setString(7, faculty_D2);
        pst.setString(8, classroom_D2);
        pst.setString(9, subject_D3);
        pst.setString(10, faculty_D3);
        pst.setString(11, classroom_D3);
        int rs = pst.executeUpdate();
        if(rs>0)
        {
            System.out.println("Insert Success");
        }
        else
        {
            System.out.println("Insert Failed");
        }
        
    }

    public void deleteTimeTable(int sr_no) throws Exception
    {
        String sql = "DELETE FROM timetable WHERE sr_no = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, sr_no);
        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Delete Success");
        } else {
            System.out.println("Delete Failed");
        }
    }
    public void displayDetails() throws Exception
    {
        String sql = "SELECT * FROM timetable";
        pst = coc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            System.out.println("Sr_no: " + rs.getInt(1));
            System.out.println("Day: " + rs.getString(2));
            System.out.println("Lecture Time: " + rs.getString(3));
            System.out.println("Subject D1: " + rs.getString(4));
            System.out.println("Faculty D1: " + rs.getString(5));
            System.out.println("Classroom D1: " + rs.getString(6));
            System.out.println("Subject D2: " + rs.getString(7));
            System.out.println("Faculty D2: " + rs.getString(8));
            System.out.println("Classroom D2: " + rs.getString(9));
            System.out.println("Subject D3: " + rs.getString(10));
            System.out.println("Faculty D3: " + rs.getString(11));
            System.out.println("Classroom D3: " + rs.getString(12));
            System.out.println("------------------------------------------------");
        }
    }

    public void updateTimeTable(int sr_no) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("------ Update Timetable Details ------");
        System.out.println();

        String checkSql = "SELECT * FROM timetable WHERE sr_no = ?";
        pst = coc.con.prepareStatement(checkSql);
        pst.setInt(1, sr_no);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("No record found for Sr_no: " + sr_no);
            return;
        }

        System.out.println("Press 1 to update Day");
        System.out.println("Press 2 to update Lecture Time");
        System.out.println("Press 3 to update Subject for D1");
        System.out.println("Press 4 to update Faculty for D1");
        System.out.println("Press 5 to update Classroom for D1");
        System.out.println("Press 6 to update Subject for D2");
        System.out.println("Press 7 to update Faculty for D2");
        System.out.println("Press 8 to update Classroom for D2");
        System.out.println("Press 9 to update Subject for D3");
        System.out.println("Press 10 to update Faculty for D3");
        System.out.println("Press 11 to update Classroom for D3");
        int choice = check.checkInput();

        String updateSql = "";
        switch (choice) {
            case 1:
                System.out.print("Enter new Day: ");
                String newDay = sc.nextLine();
                updateSql = "UPDATE timetable SET day = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newDay);
                pst.setInt(2, sr_no);
                break;
            case 2:
                System.out.print("Enter new Lecture Time: ");
                String newLecTime = sc.nextLine();
                updateSql = "UPDATE timetable SET lec_time = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newLecTime);
                pst.setInt(2, sr_no);
                break;
            case 3:
                System.out.print("Enter new Subject for D1: ");
                String newSubjectD1 = sc.nextLine();
                updateSql = "UPDATE timetable SET subject_D1 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newSubjectD1);
                pst.setInt(2, sr_no);
                break;
            case 4:
                System.out.print("Enter new Faculty for D1: ");
                String newFacultyD1 = sc.nextLine();
                updateSql = "UPDATE timetable SET faculty_D1 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newFacultyD1);
                pst.setInt(2, sr_no);
                break;
            case 5:
                System.out.print("Enter new Classroom for D1: ");
                String newClassroomD1 = sc.nextLine();
                updateSql = "UPDATE timetable SET classroom_D1 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newClassroomD1);
                pst.setInt(2, sr_no);
                break;
            case 6:
                System.out.print("Enter new Subject for D2: ");
                String newSubjectD2 = sc.nextLine();
                updateSql = "UPDATE timetable SET subject_D2 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newSubjectD2);
                pst.setInt(2, sr_no);
                break;
            case 7:
                System.out.print("Enter new Faculty for D2: ");
                String newFacultyD2 = sc.nextLine();
                updateSql = "UPDATE timetable SET faculty_D2 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newFacultyD2);
                pst.setInt(2, sr_no);
                break;
            case 8:
                System.out.print("Enter new Classroom for D2: ");
                String newClassroomD2 = sc.nextLine();
                updateSql = "UPDATE timetable SET classroom_D2 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newClassroomD2);
                pst.setInt(2, sr_no);
                break;
            case 9:
                System.out.print("Enter new Subject for D3: ");
                String newSubjectD3 = sc.nextLine();
                updateSql = "UPDATE timetable SET subject_D3 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newSubjectD3);
                pst.setInt(2, sr_no);
                break;
            case 10:
                System.out.print("Enter new Faculty for D3: ");
                String newFacultyD3 = sc.nextLine();
                updateSql = "UPDATE timetable SET faculty_D3 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newFacultyD3);
                pst.setInt(2, sr_no);
                break;
            case 11:
                System.out.print("Enter new Classroom for D3: ");
                String newClassroomD3 = sc.nextLine();
                updateSql = "UPDATE timetable SET classroom_D3 = ? WHERE sr_no = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newClassroomD3);
                pst.setInt(2, sr_no);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        int updateRs = pst.executeUpdate();
        if (updateRs > 0) {
            System.out.println("Timetable record updated successfully.");
        } else {
            System.out.println("Failed to update timetable record.");
        }
    }

}
