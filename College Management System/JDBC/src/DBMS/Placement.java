package DBMS;
import JAVAMAIN.*;
import java.sql.*;
import java.util.*;

public class Placement {
    int placementId , enrollmentNo , passingyear ;
    String studentname , coursename , deptname , companyname;
    long packageamt;

    ConnectionCollage coc;
    PreparedStatement pst;
    CheckInputs check = new CheckInputs();

    public Placement() throws Exception {
        coc = new ConnectionCollage();
    }

    public Placement(int placementId,int enrollmentNo, int passingyear, String studentname, String coursename, String deptname, String companyname, long packageamt) throws Exception {
        this.enrollmentNo = enrollmentNo;
        this.placementId=placementId;
        this.passingyear = passingyear;
        this.studentname = studentname;
        this.coursename = coursename;
        this.deptname = deptname;
        this.companyname = companyname;
        this.packageamt = packageamt;
        coc = new ConnectionCollage();
    }

    // Getter and Setter methods
    public int getPlacementId() {
        return placementId;
    }

    public void setPlacementId(int placementId) {
        this.placementId = placementId;
    }

    public int getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(int enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public int getPassingyear() {
        return passingyear;
    }

    public void setPassingyear(int passingyear) {
        this.passingyear = passingyear;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public long getPackageAmt() {
        return packageamt;
    }

    public void setPackageAmt(long packagamt) {
        this.packageamt = packageamt;
    }

    public void insertPlacement(Connection con) throws Exception {
        if(searchByPlacementId(placementId, con))
        {
            System.out.println("Placement already exists");
            return;
        }
        String sql = "INSERT INTO placement (placementId, enrollmentNo, studentname, coursename, deptname, packageamt, companyname, passingyear) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setInt(1, placementId);
        pst.setInt(2, enrollmentNo);
        pst.setString(3, studentname);
        pst.setString(4, coursename);
        pst.setString(5, deptname);
        pst.setLong(6, packageamt);
        pst.setString(7, companyname);
        pst.setInt(8, passingyear);

        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Insert Success");
        } else {
            System.out.println("Insert Failed");
        }
    }

    public void deletePlacement(int enrollment,Connection con) throws Exception {
        String sql = "DELETE FROM placement WHERE enrollmentNo = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, enrollment);

        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Delete Success");
        } else {
            System.out.println("Delete Failed");
        }
    }
    public void updatePlacement(int enrollment,Connection con) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Update Placement Details------");
        System.out.println();

        String checkSql = "SELECT * FROM placement WHERE enrollmentNO = ?";
        pst = con.prepareStatement(checkSql);
        pst.setInt(1, enrollment);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("No record found for Placement ID: " + enrollment);
            return;
        }

        System.out.println("Press 1 to update Student Name");
        System.out.println("Press 2 to update Course Name");
        System.out.println("Press 3 to update Department Name");
        System.out.println("Press 4 to update Company Name");
        System.out.println("Presss 5 to update Package Amount");
        System.out.println("Press 6 to update Passing Year");
        int choice = check.checkInput();
        sc.nextLine();  

        String updateSql = "";
        switch (choice) {
            case 1:
                System.out.print("Enter new Student Name: ");
                String newStudentName = sc.nextLine();
                updateSql = "UPDATE placement SET studentname = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newStudentName);
                pst.setInt(2, enrollment);
                break;
            case 2:
                System.out.print("Enter new Course Name: ");
                String newCourseName = sc.nextLine();
                updateSql = "UPDATE placement SET coursename = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newCourseName);
                pst.setInt(2, enrollment);
                break;
            case 3:
                System.out.print("Enter new Department Name: ");
                String newDeptName = sc.nextLine();
                updateSql = "UPDATE placement SET deptname = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newDeptName);
                pst.setInt(2, enrollment);
                break;
            case 4:
                System.out.print("Enter new Company Name: ");
                String newCompanyName = sc.nextLine();
                updateSql = "UPDATE placement SET companyname = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newCompanyName);
                pst.setInt(2, enrollment);
                break;
            case 5:
                System.out.print("Enter new Package Amount: ");
                long newPackageAmt = sc.nextLong();
                updateSql = "UPDATE placement SET packageamt = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setLong(1, newPackageAmt);
                pst.setInt(2, enrollment);
                break;
            case 6:
                System.out.print("Enter new Passing Year: ");
                int newPassingYear = sc.nextInt();
                updateSql = "UPDATE placement SET passingyear = ? WHERE enrollmentNO = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newPassingYear);
                pst.setInt(2, enrollment);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        int updateRs = pst.executeUpdate();
        if (updateRs > 0) {
            System.out.println("Placement record updated successfully.");
        } else {
            System.out.println("Failed to update placement record.");
        }
    }

    // Display method
    public void displayPlacementDetails(Connection con) throws Exception {
        System.out.println("----------All Placement Data---------------");

        String sql = "SELECT * FROM placement ORDER BY placementId";

        pst = con.prepareStatement(sql);

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
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println();
        }
    }
 
    public void searchByEnrollmentNo(int enrollmentNo,Connection con) throws Exception {
        String sql = "{call SearchByEnrollment(?)}";
        CallableStatement cst =con.prepareCall(sql);
        cst.setInt(1, enrollmentNo);

        ResultSet rs = cst.executeQuery();
        if (rs.next()) {
            System.out.println("Placement ID: " + rs.getInt(1));
            System.out.println("Enrollment No: " + rs.getInt(2));
            System.out.println("Student Name: " + rs.getString(3));
            System.out.println("Course Name: " + rs.getString(4));
            System.out.println("Department: " + rs.getString(5));
            System.out.println("Package Amount: " + rs.getLong(6));
            System.out.println("Company Name: " + rs.getString(7));
            System.out.println("Passing Year: " + rs.getInt(8));
        } else {
            System.out.println("No records found for Enrollment No: " + enrollmentNo);
        }
    }
    public void searchByPackage(long salary,Connection con) throws Exception {
        String sql = "{call SearchByPackage(?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setLong(1, packageamt);

        ResultSet rs = cst.executeQuery();
        if (rs.next()) {
            System.out.println("Placement ID: " + rs.getInt(1));
            System.out.println("Enrollment No: " + rs.getInt(2));
            System.out.println("Student Name: " + rs.getString(3));
            System.out.println("Course Name: " + rs.getString(4));
            System.out.println("Department: " + rs.getString(5));
            System.out.println("Package Amount: " + rs.getLong(6));
            System.out.println("Company Name: " + rs.getString(7));
            System.out.println("Passing Year: " + rs.getInt(8));
        } else {
            System.out.println("No records found for Enrollment No: " + enrollmentNo);
        }
    }

    public boolean searchByPlacementId(int placementId, Connection con) throws Exception {
        String sql = "SELECT 1 FROM placement WHERE placementId = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, placementId);
    
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false; 
        }
    }
    

    
    
    //DS :- BINARY SEARCH TREE
    
    
    public class BSTNode {
        Placement data;
        BSTNode left, right;
    
        public BSTNode(Placement data) {
            this.data = data;
            left = right = null;
        }
    }
       
    public BSTNode root; 

    
    public void insertBST(Placement placement) {
        root = insertRec(root, placement);
    }

    public BSTNode insertRec(BSTNode root, Placement placement) {
        if (root == null) {
            root = new BSTNode(placement);
            return root;
        }

        if (placement.getPackageAmt() < root.data.getPackageAmt()) {
            root.left = insertRec(root.left, placement);
        } else if (placement.getPackageAmt() > root.data.getPackageAmt()) {
            root.right = insertRec(root.right, placement);
        }

        return root;
    }

   
    public void deleteBST(long packageAmt) {
        root = deleteRec(root, packageAmt);
    }

    public BSTNode deleteRec(BSTNode root, long packageAmt) {
        if (root == null) {
            return root;
        }

        if (packageAmt < root.data.getPackageAmt()) {
            root.left = deleteRec(root.left, packageAmt);
        } else if (packageAmt > root.data.getPackageAmt()) {
            root.right = deleteRec(root.right, packageAmt);
        } else {
            
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);

            root.right = deleteRec(root.right, root.data.getPackageAmt());
        }

        return root;
    }

    public Placement minValue(BSTNode root) {
        Placement minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    public void inorder() {
        inorderRec(root);
    }

    public void inorderRec(BSTNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.data.getPackageAmt() + " ");
            inorderRec(root.right);
        }
    }

    public boolean searchBST(long packageAmt) {
        return searchRec(root, packageAmt);
    }

    public boolean searchRec(BSTNode root, long packageAmt) {
        if (root == null) {
            return false;
        }

        if (packageAmt < root.data.getPackageAmt()) {
            return searchRec(root.left, packageAmt);
        } else if (packageAmt > root.data.getPackageAmt()) {
            return searchRec(root.right, packageAmt);
        }

        return true;
    }

    public void displayPlacementsByCompany() throws Exception {
    }

    
}