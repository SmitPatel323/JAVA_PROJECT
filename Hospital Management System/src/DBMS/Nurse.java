package DBMS;

import JAVAMAIN.*;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Nurse {

    Scanner sc = new Scanner(System.in);
    private int nurse_id;
    private String nurse_fullname;
    private String nurse_contact_no;
    private int room_number;
    private int patient_id;

    DBConnect dc;
    PreparedStatement pst;

    public static List<Nurse> nurses = new LinkedList<>();

    public Nurse() throws Exception {
        dc = new DBConnect();
    }

    public Nurse(String nurse_fullname, String nurse_contact_no, int room_number, int patient_id) throws Exception {
        this.nurse_fullname = nurse_fullname;
        this.nurse_contact_no = nurse_contact_no;
        this.room_number = room_number;
        this.patient_id = patient_id;
        dc = new DBConnect();
    }

    public int getNurse_id() {
        return nurse_id;
    }

    public void setNurse_id(int nurse_id) {
        this.nurse_id = nurse_id;
    }

    public String getNurse_fullname() {
        return nurse_fullname;
    }

    public void setNurse_fullname(String nurse_fullname) {
        this.nurse_fullname = nurse_fullname;
    }

    public String getNurse_contact_no() {
        return nurse_contact_no;
    }

    public void setNurse_contact_no(String nurse_contact_no) {
        this.nurse_contact_no = nurse_contact_no;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public static List<Nurse> getNurses() {
        return nurses;
    }

    public static void setNurses(List<Nurse> nurses) {
        Nurse.nurses = nurses;
    }

    public void addNurse() throws Exception {
        System.out.println("Enter nurse fullname:");
        String nurse_fullname = sc.nextLine();
        String nurse_contact_no;
        do {
            System.out.println("Enter nurse contact no:"); 
            nurse_contact_no=sc.next();
            if (nurse_contact_no.length() != 10) {
                System.out.println("Invalid input. Please enter a 10-digit phone number.");
            } else if (nurse_contact_no.charAt(0) == '0') {
                System.out.println("Invalid input. Please enter a phone number that starts with a digit other than 0.");
            }
        } while (nurse_contact_no.length() != 10 || nurse_contact_no.charAt(0) == '0');
        String sql = "{ call insertData(?,?)}";
        CallableStatement cst = dc.con.prepareCall(sql);
        cst.setString(1, nurse_fullname);
        cst.setString(2, nurse_contact_no);
        cst.execute();
        System.out.println("Insert success");
    }

    public void displayNurses() throws Exception {
        System.out.println("------All Nurses Data------");
        String query = "DROP VIEW IF EXISTS V_NURSES";
        pst = dc.con.prepareStatement(query);
        pst.execute();

        query = "CREATE VIEW V_NURSES AS SELECT * FROM nurses";
        pst = dc.con.prepareStatement(query);
        pst.execute();

        String selectQuery = "SELECT * FROM V_NURSES";
        pst = dc.con.prepareStatement(selectQuery);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Nurse ID: " + rs.getInt("nurse_id"));
            System.out.println("Nurse Full Name: " + rs.getString("nurse_fullname"));
            System.out.println("Contact No: " + rs.getString("nurse_contact_no"));
            System.out.println("Room Number: " + rs.getInt("room_number"));
            System.out.println("Patient ID: " + rs.getInt("patient_id"));
            System.out.println("----------------------");
        }
    }
    
    public void updateNurse() throws SQLException {
        System.out.print("Enter nurse_fullname as to update the details= ");
        String nurseFullname = sc.next();
        sc.nextLine(); 
        System.out.print("Enter new nurse name: ");
        String newName = sc.nextLine();
        System.out.print("Enter new contact number: ");
        String newContact = sc.nextLine();
        String sql = "UPDATE nurses SET nurse_fullname = ?, nurse_contact_no = ? WHERE nurse_fullname = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setString(1, newName);
        pst.setString(2, newContact);
        pst.setString(3, nurseFullname);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Update success");
        } else {
            System.out.println("Update failed");
        }
    }

    public void deleteNurse(int nurse_id) throws SQLException {
        String sql = "DELETE FROM nurses WHERE nurse_id = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setInt(1, nurse_id);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Deletion success");
        } else {
            System.out.println("Deletion failed");
        }
    }
    
    public void allotNurseToRoom(int nurseId, int roomNumber) throws SQLException {
        String sql = "UPDATE nurses SET room_number = ? WHERE nurse_id = ?";
        PreparedStatement pst = dc.con.prepareStatement(sql);
        pst.setInt(1, roomNumber);
        pst.setInt(2, nurseId);
        int rsUpdate = pst.executeUpdate();
        if (rsUpdate > 0) {
            System.out.println("Nurse alloted successfully to the room.");
        } else {
            System.out.println("Nurse allotment failed.");
        }
    }

    
    public void assignPatientToNurse(int patientID, int nurseID) throws SQLException {
        String sql = "UPDATE nurses SET patient_id = ? WHERE nurse_id = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setInt(1, patientID);
        pst.setInt(2, nurseID);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Patient assigned successfully to the nurse.");
        } else {
            System.out.println("Patient assignment failed.");
        }
    }

}
