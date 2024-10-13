package DBMS;

import JAVAMAIN.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Appointment {

    Scanner sc=new Scanner(System.in);
    private int patientId;
    private int doctorId;
    private Date appointmentDate;
    private Date appointmentTime;

    DBConnect dc;

    public Appointment() throws Exception {
        dc=new DBConnect();
    }
    public Appointment(int patientId, int doctorId, Date appointmentDate, Date appointmentTime) throws Exception {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        dc=new DBConnect();
    }
    
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    
    public void scheduleAppointment(int patientID, int doctorID, Date appointmentDate, Time appointmentTime) throws Exception 
    {

        Date sqlDate = new Date(appointmentDate.getTime());
        Time sqlTime = new Time(appointmentTime.getTime());
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = dc.con.prepareStatement(sql);
        pst.setInt(1, patientID);
        pst.setInt(2, doctorID);
        pst.setDate(3, sqlDate);
        pst.setTime(4, sqlTime);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Appointment scheduled successfully");
        } else {
            System.out.println("Error scheduling appointment");
        }
    }

        public void viewAppointment() throws Exception 
        {
            System.out.println("Enter patient ID:");
            int patientID = sc.nextInt();
            System.out.println("Enter doctor ID:");
            int doctorID = sc.nextInt();
            String sql = "SELECT * FROM appointments WHERE patient_id = ? AND doctor_id = ?";
            PreparedStatement pst = dc.con.prepareStatement(sql);
            pst.setInt(1, patientID);
            pst.setInt(2, doctorID);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                Date appointmentDate = rs.getDate("appointment_date");
                Time appointmentTime = rs.getTime("appointment_time");
                System.out.println("------------All Appointments Data---------");
                System.out.println("Patient ID: " + patientID);
                System.out.println("Doctor ID: " + doctorID);
                System.out.println("Appointment Date: " + appointmentDate);
                System.out.println("Appointment Time: " + appointmentTime);
                System.out.println();
            } else {
                System.out.println("No appointment found for this patient with id="+patientID +" and doctor with id="+doctorID);
                System.out.println();
            }
        }
        public void deleteAppointment(int appointmentID) {
        try {
            String query = "DELETE FROM appointments WHERE appointment_id = ?";
            PreparedStatement pstmt = dc.con.prepareStatement(query);
            pstmt.setInt(1, appointmentID);
            pstmt.executeUpdate();
            System.out.println("Appointment with id="+appointmentID+"is deleted");
            System.out.println();
            dc.con.close();
        } catch (Exception e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
            System.out.println();
        }
    }
    public void updateAppointment(int patientID, int doctorID, Date appointmentDate, Time appointmentTime) throws Exception {
        Date sqlDate=new Date(appointmentDate.getTime());
        Time sqlTime=new Time(appointmentTime.getTime());
        String sql = "UPDATE appointments SET appointment_date = ?, appointment_time = ? WHERE patient_id = ? AND doctor_id = ?";
        PreparedStatement pst = dc.con.prepareStatement(sql);
        pst.setDate(1, sqlDate);
        pst.setTime(2, sqlTime);
        pst.setInt(3, patientID);
        pst.setInt(4, doctorID);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Appointment updated successfully");
            System.out.println();
        } else {
            System.out.println("Error updating appointment");
            System.out.println();
        }
    }   
}
