package DBMS;

import JAVAMAIN.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Room {
    private int room_id;
    private String room_type; // General Ward, Private Ward, ICU
    private int room_number;
    private int patient_id; 
    private double fees;
    Scanner sc = new Scanner(System.in);

    DBConnect dc;
    PreparedStatement pst;

    public static List<Room> rooms = new ArrayList<>();

    public Room() throws Exception {
        dc = new DBConnect();
    }

    public Room(String room_type, int room_number, double fees) throws Exception {
        this.room_type = room_type;
        this.room_number = room_number;
        this.fees = fees;
        dc = new DBConnect();
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    
    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public Room getRoom(int roomNumber) {
        Room room = rooms.get(roomNumber);
        return room;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void checkRoom() {
        try {
            for (int i = 1; i <= 3; i++) {
                Room generalWard = new Room("General Ward", i, 100.00);
                generalWard.addRoom();
            }
            
            for (int i = 1; i <= 4; i++) {
                Room privateWard = new Room("Private Ward", i + 3, 200.00);
                System.out.println("Hii");
                privateWard.addRoom();
            }
            for (int i = 1; i <= 5; i++) {
                Room icuWard = new Room("ICU", i + 7, 500.00);
                icuWard.addRoom();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addRoom() throws Exception {
        if (roomExists(room_number)) {
            throw new Exception("Room already exists.");
        }
        String sql = "INSERT INTO rooms (room_type, room_number,fees) VALUES (?, ?, ?)";
        pst = dc.con.prepareStatement(sql);
        pst.setString(1, room_type);
        pst.setInt(2, room_number);
        pst.setDouble(3, fees);

        try {
            int rs = pst.executeUpdate();
            if (rs > 0) {
                System.out.println("Room added successfully");
            } else {
                throw new Exception("Insertion failed");
            }
        } catch (Exception e) {
            throw new Exception("Error occurred while inserting room.");
        }
    }

    public void viewRoomDetails() throws Exception {
        System.out.println("------All Rooms Data------");
        String sql = "SELECT * FROM rooms";
        pst = dc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Room ID: " + rs.getInt("room_id"));
            System.out.println("Room Type: " + rs.getString("room_type"));
            System.out.println("Room Number: " + rs.getInt("room_number"));
            System.out.println("Fees: " + rs.getDouble("fees"));
            int patientId = rs.getInt("patient_id");
            if (patientId == 0) {
                System.out.println("Patient ID: Unoccupied");
            } else {
                System.out.println("Patient ID: " + patientId);
            }
            System.out.println("----------------------");
        }
    }

    public void checkPatientInRoom(int room_number) throws Exception {
        String sql = "SELECT * FROM rooms WHERE room_number = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setInt(1, room_number);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            this.room_id = rs.getInt("room_id");
            this.room_type = rs.getString("room_type");
            this.room_number = rs.getInt("room_number");
            this.patient_id = rs.getInt("patient_id");

            if (this.patient_id == 0) {
                System.out.println("Room " + room_number + " is currently unoccupied.");
            } else {
                System.out.println("Patient ID " + this.patient_id + " is in Room " + room_number);
            }
        } else {
            throw new Exception("Room with number " + room_number + " does not exist.");
        }
    }

    public boolean isOccupied() throws Exception {
        try {
            String sql = "SELECT * FROM rooms WHERE room_number != 0";
            pst = dc.con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Failed to execute SQL query: " + e.getMessage());
            return false;
        }
    }

    public boolean patientExists(int patient_id) throws Exception {
        try {
            String sql = "SELECT patient_id FROM patients WHERE patient_id = ?";
            pst = dc.con.prepareStatement(sql);
            pst.setInt(1, patient_id);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Failed to execute SQL query: " + e.getMessage());
            return false;
        }
    }

    public boolean roomExists(int room_number) throws Exception {
        try {
            String sql = "SELECT room_number FROM rooms WHERE room_number = ?";
            pst = dc.con.prepareStatement(sql);
            pst.setInt(1, room_number);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Failed to execute SQL query: " + e.getMessage());
            return false;
        }
    }

    public void allotRoomToPatient(int room_number, int patient_id) throws Exception {
        if (!roomExists(room_number)) {
            System.out.println("Room with number " + room_number + " does not exist.");
            return;
        }
        if (!patientExists(patient_id)) {
            System.out.println("Patient with ID " + patient_id + " does not exist.");
            return;
        }
        try {
            String sql = "SELECT * FROM rooms WHERE room_number = ?";
            pst = dc.con.prepareStatement(sql);
            pst.setInt(1, room_number);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int existing_patient_id = rs.getInt("patient_id");
                if (existing_patient_id != 0) {
                    System.out.println("Room " + room_number + " is currently occupied by Patient with ID "
                            + existing_patient_id + ".");
                } else {
                    sql = "UPDATE rooms SET patient_id = ?, fees = 0.0 WHERE room_number = ?";
                    pst = dc.con.prepareStatement(sql);
                    pst.setInt(1, patient_id);
                    pst.setInt(2, room_number);
                    pst.executeUpdate();
                    System.out.println("Patient with ID " + patient_id + " has been alloted Room " + room_number + ".");
                }
            } else {
                System.out.println("Room with number " + room_number + " does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to execute SQL query: " + e.getMessage());
        }
    }

    public void updateRoom(int room_id, String new_room_type) throws Exception {
        String sql = "UPDATE rooms SET room_type = ? WHERE room_id = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setString(1, new_room_type);
        pst.setInt(2, room_id);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Room updated successfully.");
        } else {
            throw new Exception("Update failed.");
        }
    }

    public void deleteRoom(int room_number) throws Exception {
        String sql = "DELETE FROM rooms WHERE room_number = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setInt(1, room_number);
        int rs = pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Room " + room_number + " deleted successfully.");
        } else {
            throw new Exception("Room with number " + room_number + " does not exist.");
        }
    }

    public void searchRoomById(int room_id) throws Exception {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";
        pst = dc.con.prepareStatement(sql);
        pst.setInt(1, room_id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            System.out.println("Room ID: " + rs.getInt("room_id"));
            System.out.println("Room Type: " + rs.getString("room_type"));
            System.out.println("Room Number: " + rs.getInt("room_number"));
            System.out.println("Patient ID: " + rs.getInt("patient_id"));
            System.out.println("Fees: " + rs.getDouble("fees"));
        } else {
            throw new Exception("Room with ID " + room_id + " does not exist.");
        }
    }

    public void dischargePatient(int room_number) throws Exception {
        if (!roomExists(room_number)) {
            System.out.println("Room with number " + room_number + " does not exist.");
            return;
        }
        try {
            String sql = "SELECT * FROM rooms WHERE room_number = ?";
            pst = dc.con.prepareStatement(sql);
            pst.setInt(1, room_number);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int patient_id = rs.getInt("patient_id");
                if (patient_id == 0) {
                    System.out.println("Room " + room_number + " is currently unoccupied.");
                } else {
                    sql = "UPDATE rooms SET patient_id = NULL, fees = 0 WHERE room_number = ?";
                    pst = dc.con.prepareStatement(sql);
                    pst.setInt(1, room_number);
                    pst.executeUpdate();
                    System.out.println(
                            "Patient with ID " + patient_id + " has been discharged from Room " + room_number + ".");
                }
            } else {
                System.out.println("Room with number " + room_number + " is currently unoccupied.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to execute SQL query: " + e.getMessage());
        }
    }

}
