package DS;

import JAVAMAIN.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorLinkedList 
{
    DBConnect dc;

    static class Doctor 
    {
        private int doctorId;
        private String fullName;
        private String specialization;
        private String contactNo;

        public Doctor(int doctor_id,String fullName, String specialization, String contactNo) {
            this.doctorId=doctor_id;
            this.fullName = fullName;
            this.specialization = specialization;
            this.contactNo = contactNo;
        }

        public Doctor() {
            
        }

        public int getDoctorId() {
            return doctorId;
        }

        public String getFullName() {
            return fullName;
        }

        public String getSpecialization() {
            return specialization;
        }

        public String getContactNo() {
            return getContactNo();
        }

    }

    
    class Node {
        Node next;
        Doctor doctor;

        Node(Doctor doctor) {
            this.doctor = doctor;
            next = null;
        }
    }

    Node head = null;

    /*addLast*/public void addDoctor(int doctor_id,String fullName, String specialization, String contactNo) throws Exception {
        Doctor newDoctor = new Doctor(doctor_id,fullName, specialization, contactNo);
        dc = new DBConnect();
        Node newNode = new Node(newDoctor);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        try {
            PreparedStatement stmt = dc.con.prepareStatement("INSERT INTO doctors (doctor_fullname, doctor_specialization, doctor_contact_no) VALUES (?, ?, ?)");
            stmt.setString(1, fullName);
            stmt.setString(2, specialization);
            stmt.setString(3, contactNo);
            stmt.executeUpdate();
            dc.con.close();
        } catch (SQLException e) {
            System.out.println("Error inserting doctor into database: " + e.getMessage());
        }
    }

    public void displayDoctors() {
        if (head == null) {
            System.out.println("List is empty");
        }
        Node temp = head;
        while (temp != null) {
            System.out.println("Doctor ID: " + temp.doctor.doctorId);
            System.out.println("Doctor Full Name: " + temp.doctor.fullName);
            System.out.println("Doctor Specialty: " + temp.doctor.specialization);
            System.out.println("Doctor Contact Number: " + temp.doctor.contactNo);
            System.out.println();
            temp = temp.next;
        }
    }

    public void removeDoctor(int doctorId) throws Exception {
        dc = new DBConnect();
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            if (temp.doctor.doctorId == doctorId) {
                if (prev != null) {
                    prev.next = temp.next;
                } else {
                    head = temp.next;
                }
                try {
                    PreparedStatement stmt = dc.con.prepareStatement("DELETE FROM doctors WHERE doctor_id = ?");
                    stmt.setInt(1, doctorId);
                    stmt.executeUpdate();
                    dc.con.close();
                } catch (SQLException e) {
                    System.out.println("Error removing doctor from database: " + e.getMessage());
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        try {
            PreparedStatement stmt = dc.con.prepareStatement("SELECT * FROM doctors WHERE doctor_id = ?");
            stmt.setInt(1, doctorId);
            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                System.out.println("Doctor not found in the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing doctor from database: " + e.getMessage());
        }
    }
}

