package DBMS;
import JAVAMAIN.*;
import java.sql.*;
import java.util.Scanner;

public class Department {
    int deptId , status;
    String departmentName , departmentHead , email , contactNumber;
    ConnectionCollage coc;
    PreparedStatement pst ;
    CheckInputs check = new CheckInputs();
    public Department() throws Exception {
        coc = new ConnectionCollage();
    }
    public Department(int deptId, String departmentName, String departmentHead, String email, String contactNumber ,int status) throws Exception {
        this.deptId = deptId;
        this.status = status;
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
        this.email = email;
        this.contactNumber = contactNumber;
        coc = new ConnectionCollage();

    } 
    
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getDepartmentHead() {
        return departmentHead;
    }
    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public void insertDepartment() throws Exception
    {
        String sql = "INSERT INTO Department (deptId, departmentName, departmentHead, email, contactNumber, status) VALUES (?, ?, ?, ?, ?, ?)";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, deptId);
        pst.setString(2, departmentName);
        pst.setString(3, departmentHead);
        pst.setString(4, email);
        pst.setString(5, contactNumber);
        pst.setInt(6, status);

        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Insert Success");
        } else {
            System.out.println("Insert Failed");
        }
    }


    public void displayDepartmentDetails() throws Exception
    {
        System.out.println("----------All Department Data---------------");

        String sql = "SELECT * FROM Department ORDER BY deptId";
        pst = coc.con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
           System.out.println("Department ID: " + rs.getInt(1)); 
           System.out.println("Department Name: " + rs.getString(2)); 
           System.out.println("Department Head: " + rs.getString(3)); 
           System.out.println("Email: " + rs.getString(4));
           System.out.println("Contact Number: " + rs.getString(5));
           System.out.println("Status: " + rs.getInt(6)); 
           System.out.println();
           System.out.println("--------------------------------------------------");
           System.out.println();
        }
    }

    public void deleteDepartment(int deptID) throws Exception
    {
        String deleteTableSql = "DELETE FROM Department WHERE deptId = ?";
        pst = coc.con.prepareStatement(deleteTableSql);
        pst.setInt(1, deptID);
        
        int tableResult = pst.executeUpdate();
        
        if (tableResult > 0) {
            System.out.println("Data successfully deleted from the Department table.");
        } else {
            System.out.println("Failed to delete data from the Department table.");
        }
    }
    
    public void updateDetails(int deptId) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Update details---");
        System.out.println();

        String checkSql = "SELECT * FROM Department WHERE deptId = ?";
        pst = coc.con.prepareStatement(checkSql);
        pst.setInt(1, deptId);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("Department with ID " + deptId + " not found.");
            return;
        }

        System.out.println("Press 1 to update Department Name");
        System.out.println("Press 2 to update Department Head");
        System.out.println("Press 3 to update Department Email");
        System.out.println("Press 4 to update Department Contact Number");
        System.out.println("Press 5 to update Department Status");
        int choice = check.checkInput();
        String updateSql = "";
        switch (choice) {
            case 1:
                System.out.print("Enter new Department Name: ");
                String newDeptName = sc.nextLine();
                updateSql = "UPDATE Department SET departmentName = ? WHERE deptId = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newDeptName);
                pst.setInt(2, deptId);
                break;
            case 2:
                System.out.print("Enter new Department Head: ");
                String newDeptHead = sc.nextLine();
                updateSql = "UPDATE Department SET departmentHead = ? WHERE deptId = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newDeptHead);
                pst.setInt(2, deptId);
                break;
            case 3:
                System.out.print("Enter new Department Email: ");
                String newEmail = sc.nextLine();
                updateSql = "UPDATE Department SET email = ? WHERE deptId = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newEmail);
                pst.setInt(2, deptId);
                break;
            case 4:
                String newContactNumber = check.validateMobileNumber();
                updateSql = "UPDATE Department SET contactNumber = ? WHERE deptId = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newContactNumber);
                pst.setInt(2, deptId);
                break;
            case 5:
                System.out.print("Enter new Department Status (1 for active, 0 for inactive): ");
                int newStatus = sc.nextInt();   
                updateSql = "UPDATE Department SET status = ? WHERE deptId = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newStatus);
                pst.setInt(2, deptId);
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
