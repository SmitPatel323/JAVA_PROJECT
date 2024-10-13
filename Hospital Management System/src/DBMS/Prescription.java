package DBMS;

import JAVAMAIN.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prescription {

    Scanner sc = new Scanner(System.in);
    private int prescription_id;
    private int patient_id;
    private String medicine_name;
    private String dosage;
    private int doctor_id;
    private String suffer;
    private DBConnect dc;
    private PreparedStatement pst;

    public Prescription() throws Exception {
        dc = new DBConnect();
    }

    public static List<Prescription> prescriptions = new ArrayList<>();

    public Prescription(int patient_id, String medicine_name, String dosage, int doctor_id, String suffer)
            throws Exception {
        this.patient_id = patient_id;
        this.medicine_name = medicine_name;
        this.dosage = dosage;
        this.doctor_id = doctor_id;
        this.suffer = suffer;
        dc = new DBConnect();
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getSuffer() {
        return suffer;
    }

    public void setSuffer(String suffer) {
        this.suffer = suffer;
    }

    public static List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public static void setPrescriptions(List<Prescription> prescriptions) {
        Prescription.prescriptions = prescriptions;
    }

    
    public void viewPrescription() throws Exception {
        System.out.println("Enter precription id=");
        int prescription_id=sc.nextInt();
        String sql = "SELECT * FROM prescriptions WHERE prescription_id=?";
        pst = dc.con.prepareStatement(sql);
        pst.setInt(1, prescription_id);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println("Prescription ID: " + rs.getInt("prescription_id"));
            System.out.println("Patient ID: " + rs.getInt("patient_id"));
            System.out.println("Medicine Name: " + rs.getString("medicine_name"));
            System.out.println("Dosage: " + rs.getString("dosage"));
            System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
            System.out.println("Suffer: " + rs.getString("patient_suffer"));
            System.out.println("Time="+rs.getTimestamp("Time"));
            System.out.println();
        }
    }
    public void deletePrescription() throws Exception
    {
        System.out.println("Enter prescription id to delete=");
        int del_id=sc.nextInt();
        String sql="delete from prescriptions where prescription_id=?";
        PreparedStatement pst=dc.con.prepareStatement(sql);
        pst.setInt(1,del_id);
        boolean rs=pst.execute();
        if(!rs)
        {
            System.out.println("Delete success");
        }
        else
        {
            System.out.println("Delete failed");
        }
    }

    public void addPrescription() throws Exception {
        sc = new Scanner(System.in);
        System.out.print("Enter patient ID: ");
        int patient_id = sc.nextInt();
        System.out.print("Enter medicine name: ");
        String medicine_name = sc.next();
        System.out.print("Enter dosage: ");
        String dosage = sc.next();
        System.out.print("Enter doctor ID: ");
        int doctor_id = sc.nextInt();

        PreparedStatement stmt = dc.con.prepareStatement("INSERT INTO prescriptions (patient_id, medicine_name, dosage, doctor_id, patient_suffer) VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, patient_id);
        stmt.setString(2, medicine_name);
        stmt.setString(3, dosage);
        stmt.setInt(4, doctor_id);

        PreparedStatement stmt2 = dc.con.prepareStatement("SELECT patient_suffer FROM patients WHERE patient_id = ?");
        stmt2.setInt(1, patient_id);
        ResultSet result = stmt2.executeQuery();
        if (result.next()) {
            String suffer = result.getString("patient_suffer");
            stmt.setString(5, suffer);
        } else {
            throw new Exception("Patient ID not found");
        }
        stmt.executeUpdate();
    }
}

