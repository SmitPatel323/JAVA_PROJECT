package DBMS;
import JAVAMAIN.*;
import java.sql.*;
import java.util.Scanner;

public class Student {
    int studentid , enrollmentNo , courseId; 
    String batch , name , date_Of_Birth , address , phoneNumber , email , gender , course ; 
    ConnectionCollage coc ;
    PreparedStatement pst;

    CheckInputs check = new CheckInputs();
    public Student() {
        
    }

    

    public Student(int enrollmentNo,String batch, String name, String date_Of_Birth, String address, String phoneNumber,
            String email, String gender, String course , int courseId) throws Exception {
        this.enrollmentNo = enrollmentNo;
        this.batch = batch;
        this.name = name;
        this.date_Of_Birth = date_Of_Birth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.course = course;
        this.courseId = courseId;
        this.coc = new ConnectionCollage();
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }


    public int getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(int enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_Of_Birth() {
        return date_Of_Birth;
    }

    public void setDate_Of_Birth(String date_Of_Birth) {
        this.date_Of_Birth = date_Of_Birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String couse) {
        this.course = couse;
    }
    

    
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public void insertStudent(Connection con) throws Exception
    {
        if(searchByStudentId(enrollmentNo, con))
        {
            System.out.println("Student already exists");
            return;
        }
        String tableInsertSql = "INSERT INTO student (enrollmentNo, batch, name, date_Of_Birth, address, phoneNumber, email, gender, course, courseId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(tableInsertSql);
        pst.setInt(1, enrollmentNo);
        pst.setString(2, batch);
        pst.setString(3, name);
        pst.setString(4, date_Of_Birth);
        pst.setString(5, address);
        pst.setString(6, phoneNumber);
        pst.setString(7, email);
        pst.setString(8, gender);
        pst.setString(9, course);
        pst.setInt(10, courseId);

        int tableResult = pst.executeUpdate();
        if (tableResult > 0) {
            System.out.println("Insert Success");
        } else {
            System.out.println("Insert Failed");
        }
            
        
    }
    

    public void displayStudent(Connection con) throws Exception 
    {
        System.out.println("----------All Student Data---------------");

        String sql = "SELECT * FROM student";

        pst =con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            System.out.println("Student ID: " + rs.getInt(1)); 
            System.out.println("Batch:" + rs.getString(2));
            System.out.println("Name: " + rs.getString(3)); 
            System.out.println("Date Of Birth: " + rs.getString(4)); 
            System.out.println("Address: " + rs.getString(5));
            System.out.println("Phone Number: " + rs.getString(6)); 
            System.out.println("Email: " + rs.getString(7)); 
            System.out.println("Gender: " + rs.getString(8)); 
            System.out.println("Enrollment No: " + rs.getInt(9)); 
            System.out.println("Course: " + rs.getString(10)); 
            System.out.println("Course ID: " + rs.getInt(11)); 
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();

        }
    }


    public void deleteStudent(int enrollmentNo,Connection con) throws Exception {
        
        String tableDeleteSql = "DELETE FROM student WHERE enrollmentNo = ?";
        pst = con.prepareStatement(tableDeleteSql);
        pst.setInt(1, enrollmentNo);
        
        int tableResult = pst.executeUpdate();
        if (tableResult > 0) {
            System.out.println("Delete Success");
        } else {
            System.out.println("Delete Failed");
        }
            
        
    }

    public void updateStudentDetails(int enrollmentNo,Connection con) throws Exception 
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("------Update details---");
        System.out.println();

        String checkSql = "SELECT * FROM student WHERE enrollmentNo = ?";
        pst = con.prepareStatement(checkSql);
        pst.setInt(1, enrollmentNo);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("Student with enrollment number " + enrollmentNo + " not found.");
            return;
        }

        int updateChoice;
        do {
            System.out.println("1. Update Name");
            System.out.println("2. Update Date Of Birth");
            System.out.println("3. Update Address");
            System.out.println("4. Update Phone Number");
            System.out.println("5. Update Email");
            System.out.println("6. Update Gender");
            System.out.println("7. Update Course");
            System.out.println("8. Update Course ID");
            System.out.println("9. Update Batch");
            System.out.println("10. Done Updating");
            updateChoice = check.checkInput();
            sc.nextLine(); 

            String updateSql;
            switch (updateChoice) {
                case 1:
                    System.out.print("Enter new Name: ");
                    String name = sc.nextLine();
                    updateSql = "UPDATE student SET name = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, name);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentName(enrollmentNo, name);
                    break;
                case 2:
                    String date_Of_Birth = check.checkDate();
                     updateSql = "UPDATE student SET date_Of_Birth = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, date_Of_Birth);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentDOB(enrollmentNo, date_Of_Birth);
                    break;
                case 3:
                    System.out.print("Enter new Address: ");
                    String address = sc.nextLine();
                    updateSql = "UPDATE student SET address = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, address);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentAddress(enrollmentNo, address);
                    break;
                case 4:
                    String phoneNumber = check.validateMobileNumber();
                    updateSql = "UPDATE student SET phoneNumber = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, phoneNumber);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentPhoneNo(enrollmentNo, phoneNumber);
                    break;
                case 5:
                    System.out.print("Enter new Email: ");
                    String email = sc.nextLine();
                    updateSql = "UPDATE student SET email = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, email);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentEmail(enrollmentNo, email);
                    break;
                case 6:
                    System.out.print("Enter new Gender: ");
                    String gender = sc.nextLine();
                    updateSql = "UPDATE student SET gender = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, gender);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentGender(enrollmentNo, gender);
                    break;
                case 7:
                    System.out.print("Enter new Course: ");
                    String course = sc.nextLine();
                    updateSql = "UPDATE student SET course = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, course);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentCourse(enrollmentNo, course);
                    break;
                case 8:
                    System.out.print("Enter new Course ID: ");
                    int courseId = sc.nextInt();
                    sc.nextLine(); 
                    updateSql = "UPDATE student SET courseId = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setInt(1, courseId);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentCourseId(enrollmentNo, courseId);
                    break;
                case 9:
                    System.out.print("Enter new Batch: ");
                    String batch = sc.nextLine();
                    updateSql = "UPDATE student SET batch = ? WHERE enrollmentNo = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, batch);
                    pst.setInt(2, enrollmentNo);
                    pst.executeUpdate();
                    updateStudentBatch(enrollmentNo, batch);
                    break;
                case 10:
                    System.out.println("Finished updating.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
            System.out.println();
        } while (updateChoice != 10);
    }
    
    public void searchStudentByEnrollmentNo(int enrollmentNo,Connection con) throws Exception {
        String sql = "SELECT * FROM student WHERE enrollmentNo = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, enrollmentNo);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            printStudentDetails(rs);
        }
    }

    public void searchStudentByBatch(String batch,Connection con) throws Exception {
        String sql = "SELECT * FROM student WHERE batch = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, batch);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            printStudentDetails(rs);
        }
    }

    public void searchStudentByCourseId(int courseId,Connection con) throws Exception {
        String sql = "SELECT * FROM student WHERE courseId = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, courseId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            printStudentDetails(rs);
        }
    }

    public void printStudentDetails(ResultSet rs) throws Exception {
        System.out.println("Student ID: " + rs.getInt(1)); 
            System.out.println("Batch:" + rs.getString(2));
            System.out.println("Name: " + rs.getString(3)); 
            System.out.println("Date Of Birth: " + rs.getString(4)); 
            System.out.println("Address: " + rs.getString(5));
            System.out.println("Phone Number: " + rs.getString(6)); 
            System.out.println("Email: " + rs.getString(7)); 
            System.out.println("Gender: " + rs.getString(8)); 
            System.out.println("Enrollment No: " + rs.getInt(9)); 
            System.out.println("Course: " + rs.getString(10)); 
            System.out.println("Course ID: " + rs.getInt(11)); 
            System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println();
    }

    public boolean searchByStudentId(int enrollmentNo, Connection con) throws Exception {
        String sql = "SELECT 1 FROM placement WHERE EnrollmentNo = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, enrollmentNo);
    
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false; 
        }
    }

    // Student linkedList

    public class StudentLL{
        StudentData student;
        public StudentLL next;
        public StudentLL(StudentData student)
        {
            this.student = student;
            this.next = null;
        }

    }
    public StudentLL head=null;

    public void addStudentLL(StudentData s) 
    {
        if(searchStudent(s.enrollmentNo))
        {
            System.out.println("Student already exists");
        }
        else
        {

            StudentLL newStudent = new StudentLL(s);
            if(head==null)
            {
                head = newStudent;
            }
            else
            {
                StudentLL temp = head;
                while(temp.next!=null)
                {
                    temp = temp.next;
                }
                temp.next = newStudent;
            }
        }
    }

    public void deleteStudentLL(int enrollno) 
    {
        
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                    flag=1;
                    break;
                }
                temp1=temp1.next;
            }
            if(flag==1)
            {
                if(head.student.enrollmentNo==enrollno)
                {
                    head=head.next;
                }
                else
                {
                    StudentLL temp=head;
                    while(temp.next.student.enrollmentNo!=enrollno)
                    {
                        temp=temp.next;
                    }
                    temp.next=temp.next.next;
                }
                System.out.println(enrollno + " is deleted");
                
            }
            else
            {
                System.out.println("Roll no not found");
            }

        }
        
    }

    public void updateStudentName(int enrollno , String sname ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.name=sname;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("Name is updated sucessfully!");
                
                
            }
        }
    }

    public void updateStudentDOB(int enrollno , String dob ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.date_Of_Birth=dob;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("date Of Birth is updated sucessfully!");
                
                
            }
        }
                    

    }

    public void updateStudentAddress(int enrollno , String address ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.address=address;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("address is updated sucessfully!");
                
                
            }
        }
                    

    }

    public void updateStudentPhoneNo(int enrollno , String phoneno ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.phoneNumber=phoneno;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("Phone number is updated sucessfully!");
                
                
            }
        }
                    

    }

    public void updateStudentEmail(int enrollno , String email ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.email=email;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("Email is updated sucessfully!");
                
                
            }
        }
                    

    }

    public void updateStudentGender(int enrollno , String gender ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.gender=gender;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("gender is updated sucessfully!");
                
                
            }
        }
                    

    }

    public void updateStudentCourse(int enrollno , String course ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.course=course;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("course is updated sucessfully!");
                
                
            }
        }
                    
    }

    public void updateStudentCourseId(int enrollno , int courseId ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.courseId=courseId;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("course id is updated sucessfully!");
                
                
            }
        }
                    

    }

    public void updateStudentBatch(int enrollno , String batch ) 
    {
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)  
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                   temp1.student.batch=batch;
                   flag=1;
                }
                temp1=temp1.next;
            }
            if(flag==0)
            {
                System.out.println("enrollment no not found");
            }
            else{
                System.out.println("batch is updated sucessfully!");
                
                
            }
        }
                    

    }

    public boolean searchStudent(int enrollno)
    {
        if(head==null)
        {
            return false;
        }
        else
        {
            int flag=0;
            StudentLL temp1=head;
            while(temp1!=null)
            {
                if(temp1.student.enrollmentNo==enrollno)
                {
                    flag=1;
                    break;
                }
                temp1=temp1.next;
            }
            if(flag==1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
}
