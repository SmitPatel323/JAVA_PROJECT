package DBMS;

import JAVAMAIN.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Doctor implements SpecialistDoctor {

    Scanner sc = new Scanner(System.in);
    private int doctor_id;
    private String doctor_fullname;
    private String doctor_specialization;
    private String doctor_contact_no;
    // private int patient_id; // Foreign key to link with Patient

    DBConnect dc;
    PreparedStatement pst;
    int choice = 0;

    public static List<Doctor> doctors = new LinkedList<>();
    // HashMap<Integer, Doctor> doctors = new HashMap<>();

    public Doctor() throws Exception {
        dc = new DBConnect();
    }

    public Doctor(String doctor_fullname, String doctor_specialization, String doctor_contact_no) throws Exception {
        this.doctor_fullname = doctor_fullname;
        this.doctor_specialization = doctor_specialization;
        this.doctor_contact_no = doctor_contact_no;
        dc = new DBConnect();
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_fullname() {
        return doctor_fullname;
    }

    public void setDoctor_fullname(String doctor_fullname) {
        this.doctor_fullname = doctor_fullname;
    }

    public String getDoctor_specialization() {
        return doctor_specialization;
    }

    public void setDoctor_specialization(String doctor_specialization) {
        this.doctor_specialization = doctor_specialization;
    }

    public String getDoctor_contact_no() {
        return doctor_contact_no;
    }

    public void setDoctor_contact_no(String doctor_contact_no) {
        this.doctor_contact_no = doctor_contact_no;
    }

    public static List<Doctor> getDoctors() {
        return doctors;
    }

    public static void setDoctors(List<Doctor> doctors) {
        Doctor.doctors = doctors;
    }

    public void addDoctor() throws Exception {
        System.out.println("Enter doctor fullname:");
        String doctor_fullname = sc.nextLine();
        String doctorFName = "Dr." + doctor_fullname;
        System.out.println("Enter doctor specialization:");
        String doctor_specialization = sc.nextLine();
        String doctor_contact_no;
        do {
            System.out.println("Enter doctor contact number:");
            doctor_contact_no = sc.next();
            if (doctor_contact_no.length() != 10) {
                System.out.println("Invalid input. Please enter a 10-digit phone number.");
            } else if (doctor_contact_no.charAt(0) == '0') {
                System.out.println("Invalid input. Please enter a phone number that starts with a digit other than 0.");
            }
        } while (doctor_contact_no.length() != 10 || doctor_contact_no.charAt(0) == '0');

        String sql = "INSERT INTO doctors (doctor_fullname, doctor_specialization, doctor_contact_no) VALUES (?, ?, ?)";
        pst = dc.con.prepareStatement(sql);
        pst.setString(1, doctorFName);
        pst.setString(2, doctor_specialization);
        pst.setString(3, doctor_contact_no);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Doctor added successfully.");
        } else {
            System.out.println("Insertion failed.");
        }
    }

    public void viewDoctors() throws Exception {
        System.out.println("------All Doctors Data------");
        String sql = "SELECT * FROM doctors";
        pst = dc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
            System.out.println("Doctor Full Name: " + rs.getString("doctor_fullname"));
            System.out.println("Specialization: " + rs.getString("doctor_specialization"));
            System.out.println("Contact No: " + rs.getString("doctor_contact_no"));
            System.out.println("----------------------");
        }
    }

    public void updateDoctor() throws SQLException {
        System.out.println("Enter doctor_fullname as to update the details=");
        String fullname = sc.next();
        sc.nextLine();
        System.out.println("Enter updated fullname:");
        String updated_fullname = sc.nextLine();
        String doctorFName = "Dr." + updated_fullname;
        System.out.println("Enter updated specialization:");
        String updated_specialization = sc.nextLine();
        System.out.println("Enter updated contact number:");
        String updated_contact_no = sc.nextLine();
        String sql = "UPDATE doctors SET doctor_fullname = ?, doctor_specialization = ?, doctor_contact_no = ? WHERE doctor_fullname = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setString(1, doctorFName);
        pst.setString(2, updated_specialization);
        pst.setString(3, updated_contact_no);
        pst.setString(4, fullname);
        try {
            int rs = pst.executeUpdate();
            if (rs > 0) {
                System.out.println("Update success");
            } else {
                System.out.println("Update failed");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());

        }
    }

    public void deleteDoctor(int doctor_id) throws SQLException {
        String sql = "DELETE FROM doctors WHERE doctor_id = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setInt(1, doctor_id);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Deletion success");
        } else {
            System.out.println("Deletion failed");
        }
    }

    public void searchDoctorByName(String doctor_fullname) throws SQLException {
        String sql = "SELECT * FROM doctors WHERE doctor_fullname = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setString(1, doctor_fullname);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
            System.out.println("Doctor Full Name: " + rs.getString("doctor_fullname"));
            System.out.println("Specialization: " + rs.getString("doctor_specialization"));
            System.out.println("Contact No: " + rs.getString("doctor_contact_no"));
        } else {
            System.out.println("Doctor not found");
        }
    }

    public void inspectPatient(int patient_id) throws SQLException {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setInt(1, patient_id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            System.out.println("Patient ID: " + rs.getInt("patient_id"));
            System.out.println("Patient Full Name: " + rs.getString("patient_fullname"));
            System.out.println("Patient Age: " + rs.getInt("patient_age"));
            System.out.println("Patient Address: " + rs.getString("patient_address"));
            System.out.println("Patient Contact No: " + rs.getString("patient_contact_no"));
            System.out.println("Patient Gender: " + rs.getString("patient_gender"));
            System.out.println("Patient Suffer: " + rs.getString("patient_suffer"));
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void givePrescription() throws Exception {

        Prescription p = new Prescription();
        p.addPrescription();

        System.out.println("Prescription added successfully.");
    }

    @Override
    public void addSpecialistDoctor() throws Exception {
        SpecialistDoctor s;
        do {
            try {
                System.out.println("Enter specialist you want to add: 1.Orthopadic \t 2.Cardiologist \t 3.exit!  ");
                System.out.println("Enter your choice:");
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1:
                    s = new OrthopadicDoctor();
                    s.addSpecialistDoctor();
                    break;
                case 2:
                    s = new CardiologistDoctor();
                    s.addSpecialistDoctor();
                    break;
                default:
                    System.out.println("Enter valid choice.");
                    break;
            }
        } while (choice != 3);

    }
}
