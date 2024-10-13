package DBMS;

import JAVAMAIN.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;

public class MedicalHistory {
    private int medical_history_id;
    private int patient_id;
    private int doctor_id;
    private int prescription_id;
    private String medicine_name;
    private String patient_fullname;
    private int patient_age;
    private String patient_address;
    private String patient_contact_no;
    private String patient_gender;
    private String patient_suffer;
    private String doctor_fullname;
    private String doctor_specification;
    private String doctor_contact_no;
    DBConnect dc;

    public MedicalHistory(int patient_id, int doctor_id, int prescription_id, String medicine_name,
            String patient_fullname, int patient_age, String patient_address, String patient_contact_no,
            String patient_gender, String patient_suffer, String doctor_fullname, String doctor_specification,
            String doctor_contact_no, DBConnect dc, Scanner sc) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.prescription_id = prescription_id;
        this.medicine_name = medicine_name;
        this.patient_fullname = patient_fullname;
        this.patient_age = patient_age;
        this.patient_address = patient_address;
        this.patient_contact_no = patient_contact_no;
        this.patient_gender = patient_gender;
        this.patient_suffer = patient_suffer;
        this.doctor_fullname = doctor_fullname;
        this.doctor_specification = doctor_specification;
        this.doctor_contact_no = doctor_contact_no;
        this.dc = dc;
        this.sc = sc;
    }

    Scanner sc = new Scanner(System.in);

    public MedicalHistory() {
    }

    public int getMedical_history_id() {
        return medical_history_id;
    }

    public void setMedical_history_id(int medical_history_id) {
        this.medical_history_id = medical_history_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
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

    public String getDoctor_fullname() {
        return doctor_fullname;
    }

    public void setDoctor_fullname(String doctor_fullname) {
        this.doctor_fullname = doctor_fullname;
    }

    public String getDoctor_specification() {
        return doctor_specification;
    }

    public void setDoctor_specification(String doctor_specification) {
        this.doctor_specification = doctor_specification;
    }

    public String getDoctor_contact_no() {
        return doctor_contact_no;
    }

    public void setDoctor_contact_no(String doctor_contact_no) {
        this.doctor_contact_no = doctor_contact_no;
    }

    public DBConnect getDc() {
        return dc;
    }

    public void setDc(DBConnect dc) {
        this.dc = dc;
    }

    public void addMedicalHistory() throws Exception {
        dc = new DBConnect();
        sc = new Scanner(System.in);

        System.out.println("Enter patient_id:");
        int patientId = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter doctor_id:");
        int doctorId = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter prescription_id:");
        int prescriptionId = sc.nextInt();
        sc.nextLine();

        try {

            String query = "INSERT INTO medical_history (patient_id,doctor_id,prescription_id, medicine_name,patient_fullname, patient_age, patient_address, patient_contact_no, patient_gender, patient_suffer, doctor_fullname, doctor_specialization, doctor_contact_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
            PreparedStatement pstmt = dc.con.prepareStatement(query);
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setInt(3, prescriptionId);

            PreparedStatement stmt2 = dc.con.prepareStatement("SELECT medicine_name FROM prescriptions WHERE prescription_id = ?");
            stmt2.setInt(1, prescriptionId);
            ResultSet result1 = stmt2.executeQuery();
            if (result1.next()) {
                String medicineName = result1.getString("medicine_name");
                pstmt.setString(4, medicineName);
            } else {
                throw new Exception("Prescription ID not found");
            }

            PreparedStatement stmt3 = dc.con.prepareStatement("SELECT patient_fullname, patient_age, patient_address, patient_contact_no, patient_gender, patient_suffer FROM patients WHERE patient_id = ?");
            stmt3.setInt(1, patientId);
            ResultSet result2 = stmt3.executeQuery();
            if (result2.next()) {
                String patientFullname = result2.getString("patient_fullname");
                pstmt.setString(5, patientFullname);
                int patientAge = result2.getInt("patient_age");
                pstmt.setInt(6, patientAge);
                String patientAddress = result2.getString("patient_address");
                pstmt.setString(7, patientAddress);
                String patientContactNo = result2.getString("patient_contact_no");
                pstmt.setString(8, patientContactNo);
                String patientGender = result2.getString("patient_gender");
                pstmt.setString(9, patientGender);
                String patientSuffer = result2.getString("patient_suffer");
                pstmt.setString(10, patientSuffer);
            } else {
                throw new Exception("Patient ID not found");
            }

            PreparedStatement stmt4 = dc.con.prepareStatement("SELECT doctor_fullname, doctor_specialization, doctor_contact_no FROM doctors WHERE doctor_id = ?");
            stmt4.setInt(1, doctorId);
            ResultSet result3 = stmt4.executeQuery();
            if (result3.next()) {
                String doctorFullname = result3.getString("doctor_fullname");
                pstmt.setString(11, doctorFullname);
                String doctorSpecialization = result3.getString("doctor_specialization");
                pstmt.setString(12, doctorSpecialization);
                String doctorContactNo = result3.getString("doctor_contact_no");
                pstmt.setString(13, doctorContactNo);
            } else {
                throw new Exception("Doctor ID not found");
            }

            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                System.out.println("Insert success in medical history table");
            } else {
                System.out.println("Insert failed in medical history table");
            }
            dc.con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading database driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error adding medical history: " + e.getMessage());
        }
    }

    public void viewMedicalHistory() throws Exception {
        sc = new Scanner(System.in);
        System.out.println("Enter medicalHistory ID:");
        int medicalHistoryId = sc.nextInt();
        dc = new DBConnect();

    try {
            PreparedStatement pst = dc.con.prepareStatement("SELECT * FROM medical_history WHERE medical_history_id = ?"); 
            {
            pst.setInt(1, medicalHistoryId);
            ResultSet result = pst.executeQuery();
            while (result.next()) {

                System.out.println("Medical History id=" + result.getInt(1));
                System.out.println("Patient id=" + result.getInt(2));
                System.out.println("Doctor id=" + result.getInt(3));
                System.out.println("Precription id=" + result.getInt(4));
                System.out.println("Medicine name=" + result.getString(5));
                System.out.println("Patient fullname=" + result.getString(6));
                System.out.println("Patient age=" + result.getInt(7));
                System.out.println("Patient address=" + result.getString(8));
                System.out.println("Patient contact no=" + result.getString(9));
                System.out.println("Patient gender=" + result.getString(10));
                System.out.println("Patient suffer=" + result.getString(11));
                System.out.println("Doctor fullname=" + result.getString(12));
                System.out.println("Doctor specification=" + result.getString(13));
                System.out.println("Doctor contact no=" + result.getString(14));
                System.out.println("Time=" + result.getTimestamp(15));

                //to store data in data.txt file
                System.out.println("Enter filepath=");
                String filepath=sc.next();
                FileWriter writer = new FileWriter(filepath);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write("Medical History id=" + result.getInt(1) + "\n");
                bw.write("Patient id=" + result.getInt(2) + "\n");
                bw.write("Doctor id=" + result.getInt(3) + "\n");
                bw.write("Precription id=" + result.getInt(4) + "\n");
                bw.write("Medicine name=" + result.getString(5) + "\n");
                bw.write("Patient fullname=" + result.getString(6) + "\n");
                bw.write("Patient age=" + result.getInt(7) + "\n");
                bw.write("Patient address=" + result.getString(8) + "\n");
                bw.write("Patient contact no=" + result.getString(9) + "\n");
                bw.write("Patient gender=" + result.getString(10) + "\n");
                bw.write("Patient suffer=" + result.getString(11) + "\n");
                bw.write("Doctor fullname=" + result.getString(12) + "\n");
                bw.write("Doctor specification=" + result.getString(13) + "\n");
                bw.write("Doctor contact no=" + result.getString(14) + "\n");
                bw.write("Time=" + result.getTimestamp(15) + "\n");
                bw.close();
            }
        }dc.con.close();   
        } catch (Exception e) {
            System.err.println("Error viewing medical history: " + e.getMessage());
        }
    }
}
    



