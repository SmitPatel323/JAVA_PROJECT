package JAVAMAIN;
import DBMS.*;
import DS.*;
import java.sql.*;
import java.util.*;



public class Main  {
    
    
    static FacultyLL facLL = new FacultyLL();
    
    void loadFacultyData() throws Exception
    {
        ConnectionCollage coc = new ConnectionCollage();
        String sql = "SELECT * FROM faculty ";
        PreparedStatement pst = coc.con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int facultyId = rs.getInt(1); 
            String name = rs.getString(2);
            String gender = rs.getString(3);
            String phoneNo = rs.getString(4); 
            String email = rs.getString(5);
            String doh = rs.getString(6); 
            String fSubject = rs.getString(7);
            String department = rs.getString(8); 
            String courseId = rs.getString(9);
            int salary = rs.getInt(10);
            int experience = rs.getInt(11);
            int isStatus = rs.getInt(12);

            Faculty fac = new Faculty(facultyId , salary, isStatus, name, gender, phoneNo, email, doh, fSubject, department, courseId, experience);
            facLL.addFaculty(fac);
            
        }
        
    }


    static StudentData student1;
    
    static Student student = new Student()  ;

    static void loadStudentData(Connection con) throws Exception
    {
        String sql = "SELECT * FROM student";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) 
        {
            String batch = rs.getString(2);
            String studentName = rs.getString(3);
            String dob = rs.getString(4);
            String address = rs.getString(5);
            String studentContactNumber = rs.getString(6);
            String studentEmail = rs.getString(7);
            String gender = rs.getString(8);
            int enrollmentNo = rs.getInt(9);
            String course = rs.getString(10);
            int studentCourseId = rs.getInt(11);

            student1= new StudentData(enrollmentNo, batch, studentName, dob, address, studentContactNumber, studentEmail, gender, course, studentCourseId);
            student.addStudentLL(student1);
        }
    }

    static Faculty faculty = new Faculty();

    public static Connection con;
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String drivername = "com.mysql.cj.jdbc.Driver";
    
        String dbURL = "jdbc:mysql://localhost:3306/collage";
        String dbUser = "root";
        String dbPass = "";
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
        ConnectionCollage coc = new ConnectionCollage();
        
        Main m = new Main();
        m.loadStudentData(con);
        m.loadFacultyData();

        Department depatment;
        Course course;
        CheckInputs check = new CheckInputs();

       
        Marksheet marksheet;
        Admission admission;
        Placement placement;
        Timetable timetable;
        boolean b =true;
        boolean c =true;
        boolean d =true;
        boolean g =true;
        boolean x =true;
        boolean s =true;
        boolean t =true;
        boolean y =true;
        boolean o =true;
        boolean p =true;
        boolean k =true;
        boolean u =true;
        boolean n =true;
        while(b)
        {
            System.out.println("-------Welcome to Collage-------");
            System.out.println();
            System.out.println("Press 1 for Collage Admin");
            System.out.println("Press 2 for Visitor or Apply for Admission");
            System.out.println("Press 3 for Exit");
            int choice = check.checkInput();
            switch(choice)
            {
                case 1:
                    try {
                        for(int i = 0 ; i<3 ;i++)
                    {
                        System.out.print("Enter your User Id :");
                        String userid = sc.nextLine();
                        System.out.print("Enter your Password :");
                        int pass = sc.nextInt();
                        sc.nextLine();
                        if(userid.equals("ABC123") && pass == 12345)
                        {
                            System.out.println("--------SucessFully Login-----------");
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid User Id or Password. Chances"+(2-i));
                            if(i==2)
                            {
                                System.out.println("Thank You");
                                System.exit(0);
                            }
                            try {
                                System.out.println("Please wait before your next attempt...");
                                Thread.sleep(2000); 
                            } catch (InterruptedException e) {
                                System.out.println("An error occurred while pausing.");
                            }
                        }
                    }
                    } catch (Exception e) {
                        System.out.println("Enter Valid choice");
                        System.exit(0);
                    }
                    while(c)
                    {
                       
                        System.out.println("-------Welcome to Collage Admin-------");
                        System.out.println();
                        System.out.println("Press 1 for Department");
                        System.out.println("Press 2 for Course");
                        System.out.println("Press 3 for Faculty");
                        System.out.println("Press 4 for Student");
                        System.out.println("Press 5 for Timetable");
                        System.out.println("Press 6 for Attendence");
                        System.out.println("Press 7 for Marksheet");
                        System.out.println("Press 8 for Placement");
                        System.out.println("Press 9 for View Admission");
                        System.out.println("Press 10 for Exit");
                        System.out.println();
                        int choice1 = check.checkInput();
                        switch (choice1) {
                            

                            case 1:
                                while(d){
                                    System.out.println();
                                    System.out.println("-------Welcome to Department------------");
                                    System.out.println();
                                    System.out.println("Press 1 for Insert Department");
                                    System.out.println("Press 2 for Delete Department");
                                    System.out.println("press 3 for Display Department");
                                    System.out.println("press 4 for Update Department Information");
                                    System.out.println("Press 5 for Exit the Department");
                                    System.out.println();
                                    int choice2 = check.checkInput();
                                    try {
                                        switch (choice2) {
                                            case 1:
                                                System.out.println("--------Insert Data------");
                                                System.out.println();
                                                System.out.print("Enter Department Id : ");
                                                int deptid = sc.nextInt();
                                                System.out.print("Enter Department Name : ");
                                                String departmentName = sc.next();
                                                System.out.print("Enter Department Head : ");
                                                String departmentNead = sc.next();
                                                System.out.print("Enter Department email : ");
                                                String email = sc.next();
                                                sc.nextLine();
                                                String contactNumber = check.validateMobileNumber();
                                                System.out.println("Enter Department Status like start and close enter 1 or 0");
                                                int status = sc.nextInt();
                                                System.out.println();
                                                
                                                depatment = new Department(deptid, departmentName, departmentNead, email, contactNumber, status);
                                                depatment.insertDepartment();
                                                break;
                                            case 2:
                                                System.out.println();
                                                System.out.print("Enter DeptId you want to delete : ");
                                                int deptId = sc.nextInt();
                                                depatment = new Department();
                                                depatment.deleteDepartment(deptId);
                                                    
                                                break;
    
                                            case 3 :
                                                System.out.println("-------Display The Department Details------");
                                                System.out.println();
                                                depatment = new Department();
                                                depatment.displayDepartmentDetails();
                                                break;
                                            case 4 :
                                                System.out.print("-----You Choose Update Data-----");
                                                System.out.println();
                                                System.out.print("Enter DeptId you want to Update the Data : ");
                                                int deptId1 = sc.nextInt();
                                                depatment = new Department();
                                                depatment.updateDetails(deptId1);
                                                break;
    
                                            
    
                                            case 5 :
                                                System.out.println("---------Exit ! Thank You------------");
                                                d = false;
                                                break;
                                                
                                            default:
                                                System.out.println();
                                                System.out.println("-------Enter Valid Choice----------");
                                                System.out.println();
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("--This Department Id already exist-----");
                                    }
                                        
                                }

                                break;
                                
                                case 2:
                                    while(g)
                                    {
                                        System.out.println("-----WelcomE to Cource-------");
                                        System.out.println();
                                        System.out.println("Press 1 Insert Course Data");
                                        System.out.println("Press 2 Delete Course Data");
                                        System.out.println("Press 3 Display Course Details");
                                        System.out.println("Press 4 Update Course Details");
                                        System.out.println("Press 5 search by Course Name");
                                        System.out.println("Press 6 Exit");
                                        System.out.println();
                                        int choice3 = check.checkInput();
                                        try {
                                            switch (choice3) {
                                                case 1:
                                                    System.out.println("--------Insert Data------");
                                                    System.out.println();
                                                    System.out.print("Enter Course Id: ");
                                                    int courseId = sc.nextInt();
                                                    sc.nextLine();  
                                                    System.out.print("Enter Course Name: ");
                                                    String courseName = sc.nextLine();
                                                    System.out.print("Enter Department: ");
                                                    String department = sc.nextLine();
                                                    System.out.print("Enter Fees: ");
                                                    int fees = sc.nextInt();
                                                    System.out.print("Enter Seats: ");
                                                    int seats = sc.nextInt();
                                                    sc.nextLine(); 
                                                    System.out.print("Enter Years: ");
                                                    String years = sc.nextLine();
                                                    System.out.print("Enter Prerequisites: ");
                                                    String prerequisites = sc.nextLine();
                                                    System.out.print("Enter Status (1 for active, 0 for inactive): ");
                                                    int status = sc.nextInt();
                            
                                                    course = new Course(courseId,courseName, department, fees, seats,  years, prerequisites ,status);
                                                    course.insertCourse();
                                                    break;
                                                case 2:
                                                    System.out.print("Enter Course Id you want to delete: ");
                                                    int courseid = sc.nextInt();
                                                    course = new Course();
                                                    course.deleteCourse(courseid);
                                                    break;
                                                case 3:
                                                    System.out.println("-----Display Data--------");
                                                    System.out.println();
                                                    course = new Course();
                                                    course.displayCourse();
                                                    break;
                                                case 4:
                                                    System.out.print("Enter Course Id you want to update: ");
                                                    int courseid2 = sc.nextInt();
                                                    course = new Course();
                                                    course.updateCourseDetails(courseid2);
                                                    break;
                                                case 5:
                                                    System.out.print("Enter Course Name to Search: ");
                                                    String searchCourseName = sc.next();
                                                    course = new Course();
                                                    course.displayCourseByName(searchCourseName);
                                                    break;
                                                case 6:
                                                    System.out.println("-----Exit! Thank You---");
                                                    g=false;
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice! Please try again.");
                                                    break;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Course Information Current not Working ");
                                        }
                                    }
                                    break;

                                case 3:
                                    while(x)
                                    {
                                        System.out.println("Welcome to Collage Faculty");
                                        System.out.println();
                                        System.out.println("Press 1 Insert Faculty");
                                        System.out.println("Press 2 Remove Faculty");
                                        System.out.println("Press 3 display Faculty experience Wise");
                                        System.out.println("Press 4 Display Faculty Details");
                                        System.out.println("Press 5 Update Faculty Details");
                                        System.out.println("Press 6 Search Faculty");
                                        System.out.println("Press 7 Exit");
                                        int choice4 = check.checkInput();
                                        try {
                                            switch (choice4) {
                                                case 1:
                                                    System.out.println("--------Insert Data------");
                                                    System.out.print("Enter Faculty Id: ");
                                                    int facultyid = sc.nextInt();
                                                    sc.nextLine(); 

                                                    System.out.print("Enter Name: ");
                                                    String name = sc.nextLine();
                                                    System.out.print("Enter Gender: ");
                                                    String gender = sc.nextLine();
                                                    String phoneNumber = check.validateMobileNumber();
                                                    System.out.print("Enter Email: ");
                                                    String email = sc.nextLine();
                                                    String date_of_hired = check.checkDateOnly();
                                                    System.out.print("Enter Subject: ");
                                                    String fsubject = sc.nextLine();
                                                    System.out.print("Enter Department: ");
                                                    String department = sc.nextLine();
                                                    System.out.print("Enter Course ID: ");
                                                    String courseid = sc.nextLine();
                                                    System.out.print("Enter Salary: ");
                                                    int salary = sc.nextInt();
                                                    sc.nextLine(); 
                                                    System.out.print("Enter Experience: ");
                                                    int experience = sc.nextInt();
                                                    System.out.print("Enter Status (1 for active, 0 for inactive): ");
                                                    int isStatus = sc.nextInt();

                                                    Faculty fac = new Faculty(facultyid,salary, isStatus, name, gender, phoneNumber, email, date_of_hired, fsubject, department, courseid, experience);
                                                    faculty = new Faculty(facultyid, salary, isStatus, name, gender, phoneNumber, email, date_of_hired, fsubject, department, courseid, experience);
                                                    facLL.addFaculty(fac);
                                                    faculty.insertFaculty(con);
                                                    
                                                    break;
                                                case 2:
                                                    System.out.print("Enter Faculty Id you want to delete: ");
                                                    int facultyId = sc.nextInt();
                                                    sc.nextLine();
                                                    facLL.deleteStudentLL(facultyId);
                                                    if(!facLL.searchFaculty(facultyId))
                                                    {

                                                        faculty.deletefaculty(facultyId,con);
                                                    }
                                                    break;
                                                case 3:
                                                    faculty = new Faculty();
                                                    faculty.displayFacultyfromview(con);
                                                    break;
                                                case 4:
                                                    faculty = new Faculty();
                                                    faculty.displayFacultyDetails(con);
                                                    break;
                                                case 5:
                                                    System.out.print("Enter Faculty Id you want to update: ");
                                                    int facultyId1 = sc.nextInt();
                                                    faculty = new Faculty();
                                                    faculty.updateFacultyDetails(facultyId1,con);
                                                    break;
                                                case 6:
                                                    System.out.println("------Search by:-----");
                                                    System.out.println("1. Subject");
                                                    System.out.println("2. Course ID");
                                                    System.out.println("3. Department");
                                                    int searchChoice = check.checkInput();
                                                    switch (searchChoice) {
                                                        case 1:
                                                            System.out.print("Enter Subject: ");
                                                            String subject = sc.nextLine();
                                                            faculty = new Faculty();
                                                            faculty.searchFacultyBySubject(subject,con);
                                                            break;
                                                        case 2:
                                                            System.out.print("Enter Course ID: ");
                                                            String searchCourseId = sc.nextLine();
                                                            faculty = new Faculty();
                                                            faculty.searchFacultyByCourseId(searchCourseId,con);
                                                            break;
                                                        case 3:
                                                            System.out.print("Enter Department: ");
                                                            String searchDepartment = sc.nextLine();
                                                            faculty = new Faculty();
                                                            faculty.searchFacultyByDepartment(searchDepartment,con);
                                                            break;
                                                        default:
                                                            System.out.println("Invalid choice! Please try again.");
                                                            break;
                                                    }
                                                    break;
                                                case 7:
                                                    System.out.println("Exiting...");
                                                    x=false;
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice! Please try again.");
                                                    break;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("------ Enter Valid Values----"+e.getMessage() );
                                        }
                                    }
                                break;

                                case 4 :
                                    while(s)
                                    {
                                        System.out.println("------Welcome to Collage Student -----");
                                        System.out.println();
                                        System.out.println("----------Student Management System----------");
                                        System.out.println("press 1 Insert Student");
                                        System.out.println("press 2 Display All Students");
                                        System.out.println("press 3 Delete Student");
                                        System.out.println("press 4 Update Student Details");
                                        System.out.println("press 5 Search Student");
                                        System.out.println("perss 6 Exit");
                                        int choice5 = check.checkInput();

                                        try {
                                            switch (choice5) {
                                                case 1:
                                                    
                                                    System.out.print("Enter Enrollment No: ");
                                                    int enrollmentNo = sc.nextInt();
                                                    sc.nextLine(); 
                                                    System.out.print("Enter Batch: ");
                                                    String batch = sc.nextLine();
                                                    System.out.print("Enter Name: ");
                                                    String name = sc.nextLine();
                                                    String dateOfBirth = check.checkDate();
                                                    System.out.print("Enter Address: ");
                                                    String address = sc.nextLine();
                                                    String phoneNumber = check.validateMobileNumber();
                                                    System.out.print("Enter Email: ");
                                                    String email = sc.nextLine();
                                                    System.out.print("Enter Gender: ");
                                                    String gender = sc.nextLine();
                                                    System.out.println("Choose your course");
                                                    String coursename = check.courseName();
                                                    int courseId = check.courseId();
                                                    
                                                    student1 = new StudentData(enrollmentNo, batch, name, dateOfBirth, address, phoneNumber, email, gender, coursename, courseId);
                                                    student = new Student(enrollmentNo, batch, name, dateOfBirth, address, phoneNumber, email, gender, coursename, courseId);

                                                    student.addStudentLL(student1);
                                                    student.insertStudent(con);

                                                    break;

                                                case 2:
                                                    System.out.println("-------Display Data------");
                                                    System.out.println();
                                                    student = new Student();
                                                    student.displayStudent(con);
                                                    break;

                                                case 3:
                                                    System.out.print("Enter Enrollment No to delete: ");
                                                    int delEnrollmentNo = sc.nextInt();
                                                    sc.nextLine();
                                                    student = new Student();
                                                    
                                                    student.deleteStudent(delEnrollmentNo,con);
                                                    if(student.searchStudent(delEnrollmentNo)){

                                                        student.deleteStudent(delEnrollmentNo,con);
                                                    }
                                                    break;

                                                case 4:
                                                    System.out.print("Enter Enrollment No to update Any Data: ");
                                                    int updateEnrollmentNo = sc.nextInt();
                                                    sc.nextLine(); 
                                                    student = new Student();
                                                    student.updateStudentDetails(updateEnrollmentNo,con);
                                                    break;

                                                case 5:
                                                    System.out.println("----------Search Student----------");
                                                    System.out.println("Press 1 for Search by Enrollment No");
                                                    System.out.println("Press 2 for Search by Batch");
                                                    System.out.println("Press 3 for Search by Course ID");
                                                    System.out.println("Press 4 for Exit");
                                                    int searchChoice = check.checkInput();
                                                    
                                            
                                                    try {
                                                        switch (searchChoice) {
                                                            case 1:
                                                                System.out.print("Enter Enrollment No to search: ");
                                                                int enrollmentNo1 = sc.nextInt();
                                                                sc.nextLine(); 
                                                                student = new Student();
                                                                student.searchStudentByEnrollmentNo(enrollmentNo1,con);
                                                                break;
                                            
                                                            case 2:
                                                                System.out.print("Enter Batch to search: ");
                                                                String batch1 = sc.nextLine();
                                                                student = new Student();
                                                                student.searchStudentByBatch(batch1,con);
                                                                break;
                                            
                                                            case 3:
                                                                System.out.print("Enter Course ID to search: ");
                                                                int courseId1 = sc.nextInt();
                                                                sc.nextLine();
                                                                student = new Student();
                                                                student.searchStudentByCourseId(courseId1,con);
                                                                break;

                                                            case 4 :
                                                                System.out.println("-----Exit! Thank You----"); 
                                                                s = false;
                                                                break;
                                            
                                                            default:
                                                                System.out.println("Invalid search choice! Please try again.");
                                                                break;
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Student System not working");
                                                    }
                                                    break;

                                                case 6:
                                                    System.out.println("----Exit Searching! Thank You----");
                                                    s = false;
                                                    break;

                                                default:
                                                    System.out.println("Invalid choice! Please try again.");
                                                    break;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Student System Enter Valid values ");
                                        }
                                        
                                        
                                    }
                                break;
                                
                                case 5:
                                    System.out.println();
                                    timetable = new Timetable();
                                    System.out.println("----------Welcome to TimeTable--------");
                                    while(u)
                                    {
                                        System.out.println("1. Insert Timetable");
                                        System.out.println("2. Delete Timetable");
                                        System.out.println("3. Display All Timetables");
                                        System.out.println("4. Update Timetable");
                                        System.out.println("5. Exit");
                                        int choice6 = check.checkInput();
    
                                        try {
                                            switch (choice6) {
                                                case 1:
                                                    System.out.print("Enter Day: ");
                                                    String day = sc.nextLine();
                                                    System.out.print("Enter Lecture Time: ");
                                                    String lecTime = sc.nextLine();
                                                    System.out.print("Enter Subject D1: ");
                                                    String subjectD1 = sc.nextLine();
                                                    System.out.print("Enter Faculty D1: ");
                                                    String facultyD1 = sc.nextLine();
                                                    System.out.print("Enter Classroom D1: ");
                                                    String classroomD1 = sc.nextLine();
                                                    System.out.print("Enter Subject D2: ");
                                                    String subjectD2 = sc.nextLine();
                                                    System.out.print("Enter Faculty D2: ");
                                                    String facultyD2 = sc.nextLine();
                                                    System.out.print("Enter Classroom D2: ");
                                                    String classroomD2 = sc.nextLine();
                                                    System.out.print("Enter Subject D3: ");
                                                    String subjectD3 = sc.nextLine();
                                                    System.out.print("Enter Faculty D3: ");
                                                    String facultyD3 = sc.nextLine();
                                                    System.out.print("Enter Classroom D3: ");
                                                    String classroomD3 = sc.nextLine();
    
                                                    Timetable newtimetable = new Timetable(day, lecTime, subjectD1, facultyD1, classroomD1,
                                                            subjectD2, facultyD2, classroomD2, subjectD3, facultyD3, classroomD3);
                                                    newtimetable.insertTimetable();
                                                    break;
    
                                                case 2:
                                                    System.out.print("Enter Sr_no to delete: ");
                                                    int srNoDelete = sc.nextInt();
                                                    sc.nextLine();  
                                                    timetable.deleteTimeTable(srNoDelete);
                                                    break;
    
                                                case 3:
                                                    timetable.displayDetails();
                                                    break;
    
                                                case 4:
                                                    
                                                    System.out.print("Enter Sr_no to update: ");
                                                    int srNoUpdate = sc.nextInt();
                                                    sc.nextLine();  
                                                    timetable.updateTimeTable(srNoUpdate);
                                                    break;
    
                                                case 5:
                                                    System.out.println("Exit ! Thank You ");
                                                    u = false;
                                                    break;
    
                                                default:
                                                    System.out.println("Invalid choice. Please try again.");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("----------Enter Valid Value-------" );
                                        }
                                    }
            

                                break;

                                case 6:
                                    System.out.println();
                                    System.out.println("--------Welcome to Attendence----------" );
                                    try {
                                        Attendence attendance = new Attendence();
                                        System.out.println("------Welcome to Attendance Management---------");
                            
                                        while (y) {
                                            System.out.println("\n1. Insert Attendance");
                                            System.out.println("2. Delete Attendance");
                                            System.out.println("3. Display All Attendances");
                                            System.out.println("4. Search Attendance by Enrollment No.");
                                            System.out.println("5. Update Attendance");
                                            System.out.println("6. View Less Attendence");
                                            System.out.println("7. View Complete Attendence");
                                            System.out.println("8. Exit");
                                            int choice7 = check.checkInput();
                            
                                            switch (choice7) {
                                                case 1:
                                                    System.out.println("Enter Enrollment Number to add attendance:");
                                                    int enrollmentNo = sc.nextInt();
                                                    sc.nextLine();
                                                    
                                                    System.out.println();
                                                    System.out.println("Total Attend java lecture");
                                                    int attendLec_Java = sc.nextInt();
                                                    System.out.println("Total  java lecture");
                                                    int totalLec_java = sc.nextInt();
                                                    double percentage_Java = ((double)attendLec_Java/(double)totalLec_java)*100;
                                                    System.out.println("Total Attend DS lecture");
                                                    int attendLec_DS = sc.nextInt();
                                                    System.out.println("Total DS lecture");
                                                    int totalLec_DS = sc.nextInt();
                                                    double percentage_DS= ((double)attendLec_DS/(double)totalLec_DS)*100;
                                                    System.out.println("Total Attend DBMS lecture");
                                                    int attendLec_DBMS = sc.nextInt();
                                                    System.out.println("Total DBMS lecture");
                                                    int totalLec_DBMS = sc.nextInt();
                                                    double percentage_DBMS= ((double)attendLec_DBMS/(double)totalLec_DBMS)*100;

                                                    int attendLec = (attendLec_Java +attendLec_DS +attendLec_DBMS);
                                                    int totalLec = (totalLec_java + totalLec_DS+ totalLec_DBMS);
                                                    double percenatage = ((double)attendLec/(double)totalLec)*100;

                                                    Attendence newAttendance = new Attendence(attendLec_Java, totalLec_java, percentage_Java, attendLec_DS, totalLec_DS, percentage_DS, attendLec_DBMS, totalLec_DBMS, percentage_DBMS, attendLec, totalLec, percenatage);
                                                    newAttendance.insertAttendence(enrollmentNo);
                                                   
                                                    break;
                            
                                                case 2:
                                                    System.out.print("Enter Enrollment No to delete: ");
                                                    int enrollmentNoToDelete = sc.nextInt();
                                                    attendance.deleteAttendence(enrollmentNoToDelete);
                                                    break;
                            
                                                case 3:
                                                    attendance.displayAttendence();
                                                    break;
                            
                                                case 4:
                                                    System.out.print("Enter Enrollment No to search: ");
                                                    int enrollmentNoToSearch = sc.nextInt();
                                                    attendance.searchAttendence(enrollmentNoToSearch);
                                                    break;
                            
                                                case 5:
                                                    System.out.print("Enter Enrollment No to update: ");
                                                    int enrollmentNoToUpdate = sc.nextInt();
                                                    attendance.updateAttendence(enrollmentNoToUpdate);
                                                    break;
                            
                                                case 6:
                                                    System.out.println("------Less Attendence-------");
                                                    attendance.displayLessAttendence();
                                                    break;
                                                case 7:
                                                    System.out.println("------Complete Attendence-----");
                                                    attendance.displayCompleteAttendence();
                                                    break;
                                                case 8:
                                                    System.out.println("Exiting...");
                                                    y = false;
                                                    break;
                            
                                                default:
                                                    System.out.println("Invalid choice! Please try again.");
                                            }
                                        }
                                    } catch (Exception e) {
                                        System.out.println("-----Attendance System Enter Valid Values-----: " +e.getMessage());
                                    }
                                
                                break;

                                case 7:
                                    System.out.println();
                                    try {
                                        marksheet = new Marksheet();
                                        System.out.println("------Werlcome to marksheet---------");

                            
                                        while (t) {
                                            System.out.println("1. Insert Marksheet");
                                            System.out.println("2. Delete Marksheet");
                                            System.out.println("3. Display All Marksheets");
                                            System.out.println("4. Search Marksheet by Enrollment No.");
                                            System.out.println("5. Search Marksheet by SPI");
                                            System.out.println("6. Update Marksheet");
                                            System.out.println("7. Change Status to Pass");
                                            System.out.println("8. Change Status to Fail");
                                            System.out.println("9. Exit");
                                            int choice8 = check.checkInput();
                                            sc.nextLine();
                            
                                            switch (choice8) {
                                                case 1:
                                                    System.out.println("Enter Enrollment Number You want to add marksheet");
                                                    int enrollmentNo = sc.nextInt();
                                                    sc.nextLine();
                                                   
                                                        System.out.print("Enter Semester: ");
                                                        int semester = sc.nextInt();
                                                        sc.nextLine(); 
                                                        System.out.print("Enter Course Name: ");
                                                        String courseName = check.courseName();
                                                        System.out.print("Enter Grade: ");
                                                        String grade = sc.nextLine();
                                                        System.out.print("Enter Academic Year: ");
                                                        String academicYear = sc.nextLine();
                                                        System.out.print("Enter SPI: ");
                                                        float spi = sc.nextFloat();
                                                        System.out.print("Enter Status (1 for Pass, 0 for Fail): ");
                                                        int status = sc.nextInt();
                                
                                                        Marksheet newMarksheet = new Marksheet(courseName, semester, grade,  spi , academicYear, status);
                                                        newMarksheet.insertMarks(enrollmentNo);
                                                    
                                                    
                                                    break;
                            
                                                case 2:
                                                    System.out.print("Enter Enrollment No to delete: ");
                                                    int enrollmentNo6 = sc.nextInt();
                                                    marksheet.deleteMarksheet(enrollmentNo6);
                                                    break;
                            
                                                case 3:
                                                    marksheet.displayMarksheet();
                                                    break;
                            
                                                case 4:
                                                    System.out.print("Enter Enrollment No to search: ");
                                                    int enrollmentNo1 = sc.nextInt();
                                                    marksheet.searchMarksheet(enrollmentNo1);
                                                    break;
                            
                                                case 5:
                                                    System.out.print("Enter SPI to search: ");
                                                    float spi1 = sc.nextFloat();
                                                    marksheet.searchMarksheetBySPI(spi1);
                                                    break;
                            
                                                case 6:
                                                    System.out.print("Enter Enrollment No to update: ");
                                                    int enrollmentNo3 = sc.nextInt();
                                                    marksheet.updateMarksheet(enrollmentNo3);
                                                    break;
                            
                                                case 7:
                                                    System.out.print("Enter Enrollment No to change status to Pass: ");
                                                    int enrollmentNo4 = sc.nextInt();
                                                    marksheet.changeToPass(enrollmentNo4);
                                                    break;
                            
                                                case 8:
                                                    System.out.print("Enter Enrollment No to change status to Fail: ");
                                                    int enrollmentNo5 = sc.nextInt();
                                                    marksheet.changeToFail(enrollmentNo5);
                                                    break;
                            
                                                case 9:
                                                    
                                                    System.out.println("Exit Thank You");
                                                    t=false;
                                                    return;
                            
                                                default:
                                                    System.out.println("Invalid choice! Please try again.");
                                            }
                                        }
                                    } catch (Exception e) {
                                        System.out.println("--------Marksheet System Enter Valid Values------");
                                    }
                                break;

                                case 8:
                                    System.out.println();
                                    System.out.println("--------Welcome to Placement");
                                    try {
                                        placement = new Placement(); 
                                        
                                        System.out.println("------Welcome to Placement Management---------");
                            
                                        while (o) {
                                            System.out.println("\n1. Insert Placement");
                                            System.out.println("2. Delete Placement");
                                            System.out.println("3. Display All Placements");
                                            System.out.println("4. Search Placements");
                                            System.out.println("5. Update Placement");
                                            System.out.println("6. Exit");
                                            int choice8 = check.checkInput();
                                            switch (choice8) {
                                                case 1:
                                                    System.out.print("Enter Placement id Number: ");
                                                    int placementId = sc.nextInt();
                                                    System.out.print("Enter Enrollment Number: ");
                                                    int enrollmentNo = sc.nextInt();
                                                    sc.nextLine(); 
                                                    System.out.print("Enter Passing Year: ");
                                                    int passingyear = sc.nextInt();
                                                    sc.nextLine(); 
                                                    System.out.print("Enter Student Name: ");
                                                    String studentname = sc.nextLine();
                                                    System.out.print("Enter Course Name: ");
                                                    String coursename = sc.nextLine();
                                                    System.out.print("Enter Department Name: ");
                                                    String deptname = sc.nextLine();
                                                    System.out.print("Enter Company Name: ");
                                                    String companyname = sc.nextLine();
                                                    System.out.print("Enter Package Amount: ");
                                                    long packageamt = sc.nextLong();
                            
                                                    placement = new Placement(placementId,enrollmentNo, passingyear, studentname, coursename, deptname, companyname, packageamt);
                                                    placement.insertBST(placement);
                                                    placement.insertPlacement(con);
                                                    break;
                            
                                                case 2:
                                                    System.out.print("Enter Enrollment Number to delete: ");
                                                    int enrollmentToDelete = sc.nextInt();
                                                    placement.deletePlacement(enrollmentToDelete,con);
                                                    break;
                            
                                                case 3:
                                                    placement.displayPlacementDetails(con);
                                                    break;
                            
                                                case 4:
                                                    System.out.println("-----Search Options-----");
                                                    System.out.println("1. Search by Enrollment Number");
                                                    System.out.println("2. Search by Company Name");
                                                    System.out.println("3. Search by Package Amount");
                                                    System.out.print("Enter your choice: ");
                                                    int searchChoice = sc.nextInt();
                                                    sc.nextLine(); 
                                                    switch (searchChoice) {
                                                        case 1:
                                                            System.out.print("Enter Enrollment Number to search: ");
                                                            int enrollmentToSearch = sc.nextInt();
                                                            placement.searchByEnrollmentNo(enrollmentToSearch,con);
                                                            break;
                            
                                                        case 2:
                                                            Placement placement1 = new CompanyPlacement();
                                                            placement1.displayPlacementsByCompany();
                                                            break;
                            
                                                        case 3:
                                                            System.out.print("Enter Package Amount to search: ");
                                                            long packageToSearch = sc.nextLong();
                                                            placement.searchByPackage(packageToSearch,con);
                                                            break;
                            
                                                        default:
                                                            System.out.println("Invalid choice! Please try again.");
                                                    }
                                                    break;
                            
                                                case 5:
                                                    System.out.print("Enter Enrollment Number to update: ");
                                                    int enrollmentToUpdate = sc.nextInt();
                                                    placement.updatePlacement(enrollmentToUpdate,con);
                                                    break;
                            
                                                case 6:
                                                    System.out.println("Exiting...");
                                                    o = false;
                                                    break;
                            
                                                default:
                                                    System.out.println("Invalid choice! Please try again.");
                                            }
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Placement System is not working: ");
                                    }
                                break;

                                case 9:
                                System.out.println();
                                System.out.println("View all Admissions");
                                Admission admission5 = new Admission();
                                try {
                                    boolean add = true;
                                    while(add){
                                        System.out.println("1. View all Admissions");
                                        System.out.println("2. View all Course Wise  Admissions");
                                        System.out.println("3. Exit");
                                        int choice25 = check.checkInput();
                                        switch (choice25) {
                                            case 1:
                                            System.out.println("------Display all Application In Percentage Order------");
                                            admission5.displayByPercentage();
                                            break;
                    
                                            case 2:
                                            System.out.println("Enter Couse name You Display");
                                            String course1 = check.courseName();
                                            admission5.displayCourseWise(course1);
                                            break;
                    
                                            case 3 :
                                            System.out.println("Exit ! Thank You");
                                            add = false;
                                            break;
                                            default:System.out.println("Invalid Choice ! Enter Again");
                                                break;
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Enter Value is Invalid");
                                }
                                
                                break;
                                case 10:
                                System.out.println();
                                System.out.println("Exit in Admin!Thank You");
                                c=false;
                                break;
                        }

                    }
                    break;

                case 2 :
                while(k)
                {
                    System.out.println("-------Welcome to Visitor & Apply for Visitor-------");
                    System.out.println();
                    System.out.println("Press 1 for Apply to Admission");
                    System.out.println("Press 2 for View  Departments");
                    System.out.println("Press 3 for View Courses");
                    System.out.println("Press 4 for View Placements");
                    System.out.println("Press 5 for Exit");
                    System.out.println();
                    int choice9 = check.checkInput();
                    
                    switch (choice9) {
                        case 1:
                            System.out.println();
                            System.out.println("-------Apply to Admission-------");
                            try {
                                admission = new Admission();
                    
                                while (n) {
                                    System.out.println("------ Admission System ------");
                                    System.out.println("1. Apply for Admission");
                                    System.out.println("2. Delete Application");
                                    System.out.println("3. Display All Applications");
                                    System.out.println("4. Display Applications by Percentage");
                                    System.out.println("5. Exit");
                                    
                                    int choice10 = check.checkInput();
                    
                                    switch (choice10) {
                                        case 1:
                                            
                                            admission.applyAdmisssion();
                                            System.out.println("Application Submitted Successfully!");
                                            break;
                    
                                        case 2:
                                            
                                            System.out.println("Enter Aadhar Number to Delete:");
                                            String deleteAadhar = sc.next();
                                            admission.deleteApplication(deleteAadhar);
                                            break;
                    
                                        
                                        case 3:
                                            System.out.println("Enter Aadhar Number to Update:");
                                            String updateAadhar = sc.next();
                                            sc.nextLine(); 
                                            admission.updateDetails(updateAadhar);
                                            break;
                    
                                        case 4:
                                            System.out.println("Enter Aadhar Number to Search:");
                                            String searchAadhar = sc.nextLine();
                                            admission.searchAadhar(searchAadhar);
                                            break;
                    
                                        case 5:
                                            System.out.println("Exiting...");
                                            n=false;
                                            break;
                    
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Admission Process Current not Working"+e.getMessage());
                            }
                            
                            break;
                        case 2:
                            System.out.println();
                            System.out.println("-------Display The Department Details------");
                            System.out.println();
                            depatment = new Department();
                            depatment.displayDepartmentDetails();

                            break;
                        case 3:
                            System.out.println();
                            System.out.println("-----Display the Course Details--------");
                            System.out.println();
                            course = new Course();
                            course.displayCourseDetails();
                            break;
                        case 4:
                        System.out.println();
                        System.out.println("--------Welcome to Placement");
                        placement = new Placement();
                        try {
                            while(p)
                        {
                            System.out.println("Press 1 Display Placement Details");
                            System.out.println("Press 2 Search by Company Name");
                            System.out.println("Press 3 Search by Package Amount");
                            System.out.println("Press 4 Exit");
                            int searchChoice = check.checkInput();
                            switch (searchChoice) {
                                case 1:
                                    System.out.println();
                                    System.out.println("-----Display the Placement");
                                    placement.displayPlacementDetails(con);
                                    break;

                                case 2:
                                    CompanyPlacement placement1 = new CompanyPlacement();
                                    placement1.displayPlacementsByCompany();
                                    break;

                                case 3:
                                    System.out.print("Enter Package Amount to search: ");
                                    long packageToSearch = sc.nextLong();
                                    placement.searchByPackage(packageToSearch,con);
                                    break;
                                case 4:
                                    System.out.println();
                                    System.out.println("Exit Placement ! Thank You");
                                    p = false;
                                    break;

                                default:
                                    System.out.println("Invalid choice! Please try again.");
                            }
                        
                            
                        }
                        } catch (Exception e) {
                            System.out.println("Enter Valid Choice"+e.getMessage());
                        }
                        break;

                        case 5:
                        System.out.println("Exit ! Thank You");
                        k=false;
                        break;
                    }


                        
                }
                break;

                case 3 : 
                System.out.println();
                System.out.println("--------Exit ! Thank you ---------");
                b= false;
                break;


            }
        }
    }
}

