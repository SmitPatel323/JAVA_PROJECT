package DBMS;
import DS.*;
import JAVAMAIN.*;
import java.sql.*;
import java.util.Scanner;



public class Faculty {
    int facultyId , salary , isStatus , experience;
    String name , gender , phoneNumber , email , date_of_hire , fsubjct , department , courseid  ;
    ConnectionCollage coc;
    PreparedStatement pst;
    CheckInputs check = new CheckInputs();

    public Faculty(){
        

    }

    public Faculty(int facId,int salary,int isStatus, String name, String gender, String phoneNumber, String email,
            String date_of_hire, String fsubjct, String department, String courseid, int experience ) throws Exception {
        this.facultyId = facId;
        this.salary = salary;
        this.isStatus = isStatus;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.date_of_hire = date_of_hire;
        this.fsubjct = fsubjct;
        this.department = department;
        this.courseid = courseid;
        this.experience = experience;
        this.coc = new ConnectionCollage();
       
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getdate_of_hire() {
        return date_of_hire;
    }

    public void setdate_of_hire(String date_of_hire) {
        this.date_of_hire = date_of_hire;
    }

    public String getfsubjct() {
        return fsubjct;
    }

    public void setfsubjct(String fsubjct) {
        this.fsubjct = fsubjct;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public int getexperience() {
        return experience;
    }

    public void setexperience(int experience) {
        this.experience = experience;
    }

    public int getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(int isStatus) {
        this.isStatus = isStatus;
    }

    static FacultyLL fac1 = new FacultyLL();
    
    public void insertFaculty(Connection con) throws Exception
    {
        String sql = "INSERT INTO faculty (name, gender, phoneNumber, email, date_of_hire, fsubjct, department, courseid, salary, experience , isStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, gender);
        pst.setString(3, phoneNumber);
        pst.setString(4, email);
        pst.setString(5, date_of_hire);
        pst.setString(6, fsubjct);
        pst.setString(7, department);
        pst.setString(8, courseid);
        pst.setInt(9, salary);
        pst.setInt(10, experience);
        pst.setInt(11, isStatus);

        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Insert Success");
        } else {
            System.out.println("Insert Failed");
        }
            
    }

    public void deletefaculty(int facultyId,Connection con) throws Exception {
        
        String sql = "DELETE FROM faculty WHERE facultyId = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, facultyId);
        
        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Delete Success");
        } else {
            System.out.println("Delete Failed");
        }
            
    }

    public void displayFacultyDetails(Connection con) throws Exception
    {
        System.out.println("----------All Faculty Data---------------");

        String sql = "SELECT * FROM faculty ORDER BY facultyId";

        pst = con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Faculty ID: " + rs.getInt(1)); 
            System.out.println("Name: " + rs.getString(2));
            System.out.println("Gender: " + rs.getString(3));
            System.out.println("Phone Number: " + rs.getString(4)); 
            System.out.println("Email: " + rs.getString(5));
            System.out.println("Date of Hired: " + rs.getString(6)); 
            System.out.println("Subject: " + rs.getString(7));
            System.out.println("Department: " + rs.getString(8)); 
            System.out.println("Course ID: " + rs.getString(9));
            System.out.println("Salary: " + rs.getInt(10));
            System.out.println("Experience: " + rs.getInt(11));
            System.out.println("Status: " + rs.getInt(12));//for faculty on leave or not use 0 or 1
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }
    
    public void updateFacultyDetails(int facultyId,Connection con) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        Faculty fac = new Faculty();
        System.out.println("------Update details---");
        System.out.println();

        String checkSql = "SELECT * FROM faculty WHERE facultyId = ?";
        pst = con.prepareStatement(checkSql);
        pst.setInt(1, facultyId);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("Faculty with ID " + facultyId + " not found.");
            return;
        }

        int updateChoice;
        do {
            System.out.println("1. Update Name");
            System.out.println("2. Update Gender");
            System.out.println("3. Update Phone Number");
            System.out.println("4. Update Email");
            System.out.println("5. Update Date of Hired");
            System.out.println("6. Update Subject");
            System.out.println("7. Update Department");
            System.out.println("8. Update Course ID");
            System.out.println("9. Update Salary");
            System.out.println("10. Update Experience");
            System.out.println("11. Update Status");
            System.out.println("12. Done Updating");
            updateChoice = check.checkInput();

            String updateSql;
            switch (updateChoice) {
                case 1:
                    System.out.print("Enter new Name: ");
                    String name = sc.nextLine();
                    updateSql = "UPDATE faculty SET name = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, name);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setName(name);
                    break;
                case 2:
                    System.out.print("Enter new Gender: ");
                    String gender = sc.nextLine();
                    updateSql = "UPDATE faculty SET gender = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, gender);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setGender(gender);
                    break;
                case 3:
                    String phoneNumber = check.validateMobileNumber();
                    updateSql = "UPDATE faculty SET phoneNumber = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, phoneNumber);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setPhoneNumber(phoneNumber);
                    break;
                case 4:
                    System.out.print("Enter new Email: ");
                    String email = sc.nextLine();
                    updateSql = "UPDATE faculty SET email = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, email);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setEmail(email);
                    break;
                case 5:
                    String date_of_hire = check.checkDateOnly();
                    updateSql = "UPDATE faculty SET date_of_hire = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, date_of_hire);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setdate_of_hire(date_of_hire);
                    break;
                case 6:
                    System.out.print("Enter new Subject: ");
                    String fsubjct = sc.nextLine();
                    updateSql = "UPDATE faculty SET fsubjct = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, fsubjct);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setfsubjct(fsubjct);
                    break;
                case 7:
                    System.out.print("Enter new Department: ");
                    String department = sc.nextLine();
                    updateSql = "UPDATE faculty SET department = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, department);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setDepartment(department);
                    break;
                case 8:
                    System.out.print("Enter new Course ID: ");
                    String courseid = sc.nextLine();
                    updateSql = "UPDATE faculty SET courseid = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setString(1, courseid);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setCourseid(courseid);
                    break;
                case 9:
                    System.out.print("Enter new Salary: ");
                    int salary = sc.nextInt();
                    sc.nextLine();
                    updateSql = "UPDATE faculty SET salary = ? WHERE facultyId = ?";
                    pst =con.prepareStatement(updateSql);
                    pst.setInt(1, salary);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setSalary(salary);
                    break;
                case 10:
                    System.out.print("Enter new Experience: ");
                    int experience = sc.nextInt();
                    updateSql = "UPDATE faculty SET experience = ? WHERE facultyId = ?";
                    pst =con.prepareStatement(updateSql);
                    pst.setInt(1, experience);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setexperience(experience);
                    break;
                case 11:
                    System.out.print("Enter new Status (1 for active, 0 for inactive): ");
                    int isStatus = sc.nextInt();
                    sc.nextLine(); 
                    updateSql = "UPDATE faculty SET isStatus = ? WHERE facultyId = ?";
                    pst = con.prepareStatement(updateSql);
                    pst.setInt(1, isStatus);
                    pst.setInt(2, facultyId);
                    pst.executeUpdate();
                    fac.setIsStatus(isStatus);
                    break;
                case 12:
                    System.out.println();
                    System.out.println("Finished updating.");
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (updateChoice != 12);
    }


    public void searchFacultyBySubject(String subject,Connection con) throws Exception {
        String sql = "SELECT * FROM faculty WHERE fsubjct = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, subject);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Faculty ID: " + rs.getInt(1));
            System.out.println("Name: " + rs.getString(2));
            System.out.println("Gender: " + rs.getString(3));
            System.out.println("Phone Number: " + rs.getString(4));
            System.out.println("Email: " + rs.getString(5));
            System.out.println("Date of Hired: " + rs.getString(6));
            System.out.println("Subject: " + rs.getString(7));
            System.out.println("Department: " + rs.getString(8));
            System.out.println("Course ID: " + rs.getString(9));
            System.out.println("Salary: " + rs.getInt(10));
            System.out.println("Experience: " + rs.getInt(11));
            System.out.println("Status: " + rs.getInt(12));
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }

    public void searchFacultyByCourseId(String courseId,Connection con) throws Exception {
        String sql = "SELECT * FROM faculty WHERE courseid = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, courseId);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Faculty ID: " + rs.getInt(1));
            System.out.println("Name: " + rs.getString(2));
            System.out.println("Gender: " + rs.getString(3));
            System.out.println("Phone Number: " + rs.getString(4));
            System.out.println("Email: " + rs.getString(5));
            System.out.println("Date of Hired: " + rs.getString(6));
            System.out.println("Subject: " + rs.getString(7));
            System.out.println("Department: " + rs.getString(8));
            System.out.println("Course ID: " + rs.getString(9));
            System.out.println("Salary: " + rs.getInt(10));
            System.out.println("Experience: " + rs.getInt(11));
            System.out.println("Status: " + rs.getInt(12));
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }

    public void searchFacultyByDepartment(String department,Connection con) throws Exception {
        String sql = "SELECT * FROM faculty WHERE department = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, department);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Faculty ID: " + rs.getInt(1));
            System.out.println("Name: " + rs.getString(2));
            System.out.println("Gender: " + rs.getString(3));
            System.out.println("Phone Number: " + rs.getString(4));
            System.out.println("Email: " + rs.getString(5));
            System.out.println("Date of Hired: " + rs.getString(6));
            System.out.println("Subject: " + rs.getString(7));
            System.out.println("Department: " + rs.getString(8));
            System.out.println("Course ID: " + rs.getString(9));
            System.out.println("Salary: " + rs.getInt(10));
            System.out.println("Experience: " + rs.getInt(11));
            System.out.println("Status: " + rs.getInt(12));
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }

    public void displayFacultyfromview(Connection con) throws Exception {
        String sql = "Select * from faculty_experience order by experience";
        pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Faculty ID: " + rs.getInt(1));
            System.out.println("Name: " + rs.getString(2));
            System.out.println("Gender: " + rs.getString(3));
            System.out.println("Phone Number: " + rs.getString(4));
            System.out.println("Email: " + rs.getString(5));
            System.out.println("Date of Hired: " + rs.getString(6));
            System.out.println("Subject: " + rs.getString(7));
            System.out.println("Department: " + rs.getString(8));
            System.out.println("Course ID: " + rs.getString(9));
            System.out.println("Salary: " + rs.getInt(10));
            System.out.println("Experience: " + rs.getInt(11));
            System.out.println("Status: " + rs.getInt(12));
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }

    
    public boolean searchByFacultyId(int facultyId, Connection con) throws Exception {
        String sql = "SELECT 1 FROM placement WHERE facultyId = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, facultyId);
    
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false; 
        }
    }
    
}

