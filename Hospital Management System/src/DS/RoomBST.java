package DS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomBST {
    public class Room {
        private int room_id;
        private String room_type; // General Ward, Private Ward, ICU
        private int room_number;
        private int patient_id; 
        private double fees;

        public Room() {
            // Default constructor
            room_id = 0;
            room_type = "";
            room_number = 0;
            patient_id = 0;
            fees = 0.0;
        }

        public Room(int room_id, String room_type, int room_number, int patient_id, double fees) {
            
            this.room_id = room_id;

            this.room_type = room_type;
            this.room_number = room_number;
            this.patient_id = patient_id;
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

        public int getPatient_id() {
            return patient_id;
        }

        public void setPatient_id(int patient_id) {
            this.patient_id = patient_id;
        }

        public double getFees() {
            return fees;
        }

        public void setFees(double fees) {
            this.fees = fees;
        }
    }

    public class Node {
        private Room value;
        private Node left;
        private Node right;

        public Node(Room value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public RoomBST() {
        root = null;
    }

    public void insertRoom(int room_id, String room_type, int room_number, int patient_id, double fees) {
        Room room = new Room(room_id, room_type, room_number, patient_id, fees);
        root = insertRecursive(root, room);
        room = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            String query = "INSERT INTO rooms (room_id, room_type, room_number, patient_id, fees) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, room_id);
            pstmt.setString(2, room_type);
            pstmt.setInt(3, room_number);
            pstmt.setInt(4, patient_id);
            pstmt.setDouble(5, fees);
            pstmt.executeUpdate();
            conn.close();
            System.out.println("Room inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error inserting room into the database: " + e.getMessage());
        }
    }

    private Node insertRecursive(Node current, Room room) {
        if (current == null) {
            return new Node(room);
        }

        if (room.getRoom_id() < current.value.getRoom_id()) {
            current.left = insertRecursive(current.left, room);
        } else if (room.getRoom_id() > current.value.getRoom_id()) {
            current.right = insertRecursive(current.right, room);
        } else {
            System.out.println("Room ID already exists in the tree.");
        }

        return current;
    }

    public void deleteRoom(int room_id) {
        root = deleteRecursive(root, room_id);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            String query = "DELETE FROM rooms WHERE room_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, room_id);
            pstmt.executeUpdate();
            conn.close();
            System.out.println("Room deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error deleting room from the database: " + e.getMessage());
        }
    }

    private Node deleteRecursive(Node current, int room_id) {
        if (current == null) {
            return null;
        }

        if (room_id < current.value.getRoom_id()) {
            current.left = deleteRecursive(current.left, room_id);
        } else if (room_id > current.value.getRoom_id()) {
            current.right = deleteRecursive(current.right, room_id);
        } else {
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            Node temp = getMinValueNode(current.right);
            current.value.setRoom_id(temp.value.getRoom_id());
            current.right = deleteRecursive(current.right, temp.value.getRoom_id());
        }

        return current;
    }

    private Node getMinValueNode(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public Room searchRoom(int room_id) {
        return searchRecursive(root, room_id);
    }

    public Room searchRecursive(Node current, int room_id) {
        if (current == null) {
            return null;
        }

        if (current.value.getRoom_id() == room_id) {
            return current.value;
        }

        if (room_id < current.value.getRoom_id()) {
            return searchRecursive(current.left, room_id);
        } else {
            return searchRecursive(current.right, room_id);
        }
    }

    public void displayAllRooms() {
        inorder(root);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            String query = "SELECT * FROM rooms";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {

                System.out.println("Room ID: " + result.getInt(1) + ", " + "Room Type: " + result.getString(2) + ", "
                        + "Room Number: " + result.getInt(3) + ", " + "Patient ID: " + result.getInt(4) + ", "
                        + "Fees: " + result.getDouble(5));
            }
            conn.close();
        } catch (SQLException e) {

            System.out.println("Error displaying all rooms in the database: " + e.getMessage());
        }
    }

    public void display() {

        inorder(root);

    }

    public void inorder(Node root) {

        if (root != null) {

            inorder(root.left);

            System.out.print(root.value.toString() + ",");

            inorder(root.right);

        }

    }
}
