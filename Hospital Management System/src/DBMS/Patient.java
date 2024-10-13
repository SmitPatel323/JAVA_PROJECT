package DBMS;

import JAVAMAIN.*;
import java.sql.*;
import java.util.*;

public class Patient {
    Scanner sc = new Scanner(System.in);
    private int patient_id;
    private String patient_fullname;
    private int patient_age;
    private String patient_address;
    private String patient_contact_no;
    private String patient_gender;
    private String patient_suffer;
    DBConnect dc;
    PreparedStatement pst;

    public static List<Patient> patients = new LinkedList<>();

    public Patient() throws Exception {
        dc = new DBConnect();
    }

    public Patient(String patient_fullname, int patient_age, String patient_address,
            String patient_contact_no, String patient_gender, String patient_suffer) throws Exception {
        this.patient_fullname = patient_fullname;
        this.patient_age = patient_age;
        this.patient_address = patient_address;
        this.patient_contact_no = patient_contact_no;
        this.patient_gender = patient_gender;
        this.patient_suffer = patient_suffer;
        dc = new DBConnect();
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_fullname() {
        return patient_fullname;
    }

    public void setPatient_fullname(String patient_fullname) {
        this.patient_fullname = patient_fullname;
    }

    public int getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(int patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }

    public String getPatient_contact_no() {
        return patient_contact_no;
    }

    public void setPatient_contact_no(String patient_contact_no) {
        this.patient_contact_no = patient_contact_no;
    }

    public String getPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(String patient_gender) {
        this.patient_gender = patient_gender;
    }

    public String getPatient_suffer() {
        return patient_suffer;
    }

    public void setPatient_suffer(String patient_suffer) {
        this.patient_suffer = patient_suffer;
    }

    public static List<Patient> getPatients() {
        return patients;
    }

    public static void setPatients(List<Patient> patients) {
        Patient.patients = patients;
    }

    public void addPatient() throws Exception {
        System.out.println("Enter patient fullname=");
        String patient_fullname = sc.nextLine();
        System.out.println("Enter patient age=");
        int patient_age = sc.nextInt();
        System.out.println("Enter patient address=");
        String patient_address = sc.next();
        sc.nextLine();
        String patient_contact_no;
        do {
            System.out.println("Enter patient contact_no=");
            patient_contact_no = sc.next();
            if (patient_contact_no.length() != 10) {
                System.out.println("Invalid input. Please enter a 10-digit phone number.");
            } else if (patient_contact_no.charAt(0) == '0') {
                System.out.println("Invalid input. Please enter a phone number that starts with a digit other than 0.");
            }
        } while (patient_contact_no.length() != 10 || patient_contact_no.charAt(0) == '0');

        System.out.println("Enter patient gender=");
        String patient_gender = sc.next();
        sc.nextLine();
        System.out.println("Enter patient suffer=");
        String patient_suffer = sc.next();
        sc.nextLine();

        String sql = "insert into patients (patient_fullname , patient_age , patient_address , patient_contact_no , patient_gender , patient_suffer) values (? , ? , ? , ? , ? , ?)";
        pst = dc.con.prepareStatement(sql);
        pst.setString(1, patient_fullname);
        pst.setInt(2, patient_age);
        pst.setString(3, patient_address);
        pst.setString(4, patient_contact_no);
        pst.setString(5, patient_gender);
        pst.setString(6, patient_suffer);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Insertion success");
        } else {
            System.out.println("Insertion failed");
        }
    }

    public void deletePatient(int patient_id) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";
        try (PreparedStatement pst = dc.con.prepareStatement(sql)) {
            pst.setInt(1, patient_id);

            int rs = pst.executeUpdate();

            if (rs > 0) {
                System.out.println("Deletion success");
            } else {
                System.out.println("Deletion failed");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void viewPatient() throws Exception {

        String sql = "{call displayData()}";
        CallableStatement cst = dc.con.prepareCall(sql);
        ResultSet rs = cst.executeQuery();
        System.out.println("------All Patients Data------");
        while (rs.next()) {
            System.out.println("Patient ID: " + rs.getInt("patient_id"));
            System.out.println("Patient_fullname=" + rs.getString(2));
            System.out.println("Patient age=" + rs.getInt(3));
            System.out.println("Patient address=" + rs.getString(4));
            System.out.println("Patient contact no=" + rs.getString(5));
            System.out.println("Patient gender=" + rs.getString(6));
            System.out.println("Patient suffering from=" + rs.getString(7));
            System.out.println("----------------------");
        }
    }

    public void updatePatient() {
        System.out.println("Enter patient_fullname as to update the details:");
        String fullname = sc.next();
        System.out.println("Enter patient updated fullname:");
        String updated_fullname = sc.next();
        sc.nextLine();
        System.out.println("Enter patient updated age :");
        int updated_age = sc.nextInt();
        System.out.println("Enter patient updated address:");
        String updated_address = sc.next();
        sc.nextLine();
        System.out.println("Enter patient updated contact_no :");
        String updated_contact_no = sc.next();
        sc.nextLine();
        System.out.println("Enter patient updated gender :");
        String updated_gender = sc.next();
        System.out.println("Enter patient updated suffer :");
        String updated_suffer = sc.next();
        sc.nextLine();
        String sql = "UPDATE patients SET patient_fullname = ?, patient_age = ?, patient_address = ?, patient_contact_no = ?, patient_gender = ?, patient_suffer = ? WHERE patient_fullname = ?";

        try (PreparedStatement pst = dc.con.prepareStatement(sql)) {
            pst.setString(1, updated_fullname);
            pst.setInt(2, updated_age);
            pst.setString(3, updated_address);
            pst.setString(4, updated_contact_no);
            pst.setString(5, updated_gender);
            pst.setString(6, updated_suffer);
            pst.setString(7, fullname);

            int rs = pst.executeUpdate();

            if (rs > 0) {
                System.out.println("Update success");
            } else {
                System.out.println("Update failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchPatientByName() {
        System.out.println("Enter patient_fullname of whose data you want to get=");
        String patient_name = sc.nextLine();

        String sql = "SELECT * FROM patients WHERE patient_fullname = ?";
        try (PreparedStatement pst = dc.con.prepareStatement(sql)) {
            pst.setString(1, patient_name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    System.out.println("Patient ID: " + rs.getInt("patient_id"));
                    String patient_fullname = rs.getString("patient_fullname");
                    int patient_age = rs.getInt("patient_age");
                    String patient_address = rs.getString("patient_address");
                    String patient_contact_no = rs.getString("patient_contact_no");
                    String patient_gender = rs.getString("patient_gender");
                    String patient_suffer = rs.getString("patient_suffer");

                    System.out.println("Patient Full Name: " + patient_fullname);
                    System.out.println("Patient Age: " + patient_age);
                    System.out.println("Patient Address: " + patient_address);
                    System.out.println("Patient Contact No: " + patient_contact_no);
                    System.out.println("Patient Gender: " + patient_gender);
                    System.out.println("Patient Suffer: " + patient_suffer);
                }
            } else {
                System.out.println("No records found with patient name=" + patient_name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*//USE IT IF YOU WANT
     * public String getAssignedDoctor(int patient_id)
     * {
     * String sql = "SELECT doctor_id FROM appointments WHERE patient_id = ?";
     * try (PreparedStatement pst = dc.con.prepareStatement(sql))
     * {
     * pst.setInt(1, patient_id);
     * ResultSet rs = pst.executeQuery();
     * if (rs.next())
     * {
     * return rs.getString("doctor_id");
     * }
     * else
     * {
     * return "No doctor assigned";
     * }
     * }
     * catch (SQLException e)
     * {
     * e.printStackTrace();
     * return "Error occurred";
     * }
     * }
     */

}
