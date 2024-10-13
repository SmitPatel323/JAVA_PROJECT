package JAVAMAIN;

import DBMS.*;
import DS.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public class Hospital {
    HashMap<String, Patient> patientData = new HashMap<>();

    public void loadPatientsData() throws Exception {
        DBConnect dc = new DBConnect();
        String sql = "select * from patients";
        PreparedStatement pst = dc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String patient_fullname = rs.getString("patient_fullname");
            int patient_age = rs.getInt("patient_age");
            String patient_address = rs.getString("patient_address");
            String patient_contact_no = rs.getString("patient_contact_no");
            String patient_gender = rs.getString("patient_gender");
            String patient_suffer = rs.getString("patient_suffer");
            Patient p = new Patient(patient_fullname, patient_age, patient_address, patient_contact_no, patient_gender,
                    patient_suffer);
            patientData.put(patient_fullname, p);
        }
        dc.con.close();
    }

    List<Doctor> doctorData = new LinkedList<>();

    public void loadDoctorData() throws Exception {
        DBConnect dc = new DBConnect();
        String sql = "SELECT * FROM doctors";
        PreparedStatement pst = dc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int doctor_id = rs.getInt("doctor_id");
            String doctor_fullname = rs.getString("doctor_fullname");
            String doctor_specialization = rs.getString("doctor_specialization");
            String doctor_contact_no = rs.getString("doctor_contact_no");

            Doctor d = new Doctor(doctor_fullname, doctor_specialization, doctor_contact_no);
            d.setDoctor_id(doctor_id);
            doctorData.add(d);
        }
    }

    List<Nurse> nurseData = new LinkedList<>();

    public void loadNurseData() throws Exception {
        DBConnect dc = new DBConnect();
        String sql = "SELECT * FROM nurses";
        PreparedStatement pst = dc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int nurse_id = rs.getInt("nurse_id");
            String nurse_fullname = rs.getString("nurse_fullname");
            String nurse_contact_no = rs.getString("nurse_contact_no");
            int room_number = rs.getInt("room_number");
            int patient_id = rs.getInt("patient_id");

            Nurse n = new Nurse(nurse_fullname, nurse_contact_no, room_number, patient_id);
            n.setNurse_id(nurse_id);
            nurseData.add(n);
        }
    }

    static Room r;

    public static void main(String[] args) throws Exception {
        DoctorLinkedList doctorList = new DoctorLinkedList();
        RoomBST bst = new RoomBST();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        int choice1 = 0;
        int ch = 0;
        Patient p;
        Doctor d;
        Nurse n;
        String username1=null;
        int password1=0;
        int count=0;
        // to insert some room in room table
        /*
         * try
         * {
         * Room r = new Room();
         * 
         * // Adding predefined rooms
         * for (int i = 1; i <= 3; i++)
         * {
         * Room generalWard = new Room("General Ward", i,100.00);
         * generalWard.addRoom();
         * }
         * for (int i = 1; i <= 4; i++)
         * {
         * Room privateWard = new Room("Private Ward", i + 3,200.00);
         * privateWard.addRoom();
         * }
         * for (int i = 1; i <= 5; i++)
         * {
         * Room icuWard = new Room("ICU", i + 7,500.00);
         * icuWard.addRoom();
         * }
         * }
         * catch (Exception e)
         * {
         * e.printStackTrace();
         * }
         */

        Login l = new Login();
        while (!l.isAuthenticated()) {
            Thread.sleep(1000);
        }
        if (l.isAuthenticated()) {
            do {
                try {
                    System.out.println("Press 1 to Manage Patient");
                    System.out.println("Press 2 to manage Doctor");
                    System.out.println("Press 3 to manage Nurse");
                    System.out.println("Press 4 to manage Room");
                    System.out.println("Press 5 to exit!");
                    System.out.println("Enter your choice=");
                    ch = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next();
                    continue;
                }
                switch (ch) {
                    case 1:
                        do {
                            try {
                                System.out.println("----Patient Management----");
                                System.out.println(
                                        "1-add patient \t 2-delete patient \t 3-view patient \t 4.update patient \t 5.searchPatientByName \t 6.to viewMedicalHistory \t 7.exit!");
                                System.out.println("Enter your choice=");

                                choice = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input! Please enter a number.");
                                sc.next();
                                continue;
                            }
                            switch (choice) {
                                case 1:
                                    p = new Patient();
                                    p.addPatient();
                                    break;
                                case 2:
                                    p = new Patient();
                                    System.out.println("Enter patient_id of whose data you want to get=");
                                    int patient_id1 = sc.nextInt();
                                    p.deletePatient(patient_id1);
                                    break;
                                case 3:
                                    p = new Patient();
                                    p.viewPatient();
                                    break;
                                case 4:
                                    p = new Patient();
                                    p.updatePatient();
                                    break;
                                case 5:
                                    p = new Patient();
                                    p.searchPatientByName();
                                    break;
                                case 6:
                                    MedicalHistory mh = new MedicalHistory();
                                    mh.viewMedicalHistory();
                                    break;
                                case 7:
                                    System.out.println("Exiting Patient Management..");
                                    break;
                                default:
                                    System.out.println("Enter valid choice.");
                                    break;
                            }
                        } while (choice != 7);

                        break;
                        
                    case 2:
                    do{
                        System.out.println();
                        System.out.println("---Doctor Login---");
                        System.out.println("Enter username:");
                        username1=sc.next();
                        System.out.println("Enter password:");
                        password1=sc.nextInt();
                        if(username1.equals("doctor") && password1==4321 )
                        {
                            count=1;
                            System.out.println("Login success");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println("Login failed");
                        }
                    }while(count!=1);
                    if(count==1)
                    {
                        do {
                            try {
                                System.out.println("Doctor Management");
                                System.out.println("1. Add Doctor");
                                System.out.println("2. Delete Doctor");
                                System.out.println("3. View Doctors");
                                System.out.println("4. Update Doctor");
                                System.out.println("5. Search Doctor by Name");
                                System.out.println("6. Inspect patient by id");
                                System.out.println("7. Schedule appointment");
                                System.out.println("8. Update appointment");
                                System.out.println("9. Delete appointment");
                                System.out.println("10.View Appointment");
                                System.out.println("11.Use LinkedList to insert,delete,display");
                                System.out.println("12. Give,View,delete Precription");
                                System.out.println("13.Add,view medicalHistory");
                                System.out.println("14. Exit");
                                System.out.print("Enter your choice: ");
                                choice = sc.nextInt();
                                sc.nextLine();
                            } catch (Exception e) {
                                System.out.println("Invalid input! Please enter a number.");
                                sc.next();
                                continue;
                            }

                            switch (choice) {
                                case 1:
                                    d = new Doctor();
                                    d.addSpecialistDoctor();
                                    break;

                                case 2:
                                    System.out.println("Enter doctor ID to delete:");
                                    int doctor_id_to_delete = sc.nextInt();
                                    d = new Doctor();
                                    d.deleteDoctor(doctor_id_to_delete);
                                    break;

                                case 3:
                                    d = new Doctor();
                                    d.viewDoctors();
                                    break;

                                case 4:
                                    d = new Doctor();
                                    d.updateDoctor();
                                    break;

                                case 5:
                                    System.out.println("Enter doctor fullname to search:");
                                    String search_doctor_name = sc.nextLine();
                                    d = new Doctor();
                                    d.searchDoctorByName(search_doctor_name);
                                    break;

                                case 6:
                                    System.out.print("Enter patient ID to inspect: ");
                                    int patientId = sc.nextInt();
                                    sc.nextLine();
                                    d = new Doctor();
                                    d.inspectPatient(patientId);
                                    break;
                                case 7:
                                    Scanner scanner = new Scanner(System.in);
                                    System.out.println("Enter patient ID:");
                                    int patientID = scanner.nextInt();
                                    System.out.println("Enter doctor ID:");
                                    int doctorID = scanner.nextInt();
                                    System.out.println("Enter appointment date (yyyy-mm-dd):");
                                    String dateInput = scanner.next();
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                                    Date appointmentDate = simpleDateFormat.parse(dateInput);
                                    java.sql.Date sqlDate = new java.sql.Date(appointmentDate.getTime());
                                    System.out.println("Enter appointment time (hh:mm:ss):");
                                    String timeInput = scanner.next();
                                    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm:ss");
                                    Date appointmentTime = simpleTimeFormat.parse(timeInput);
                                    // Convert Date to Time
                                    java.sql.Time sqlTime = new java.sql.Time(appointmentTime.getTime());
                                    Appointment appointment = new Appointment();
                                    appointment.scheduleAppointment(patientID, doctorID, sqlDate, sqlTime);
                                    break;

                                case 8:
                                    scanner = new Scanner(System.in);
                                    System.out.println("Enter patient ID:");
                                    int patientID3 = scanner.nextInt();
                                    System.out.println("Enter doctor ID:");
                                    int doctorID3 = scanner.nextInt();
                                    System.out.println("Enter appointment date (yyyy-mm-dd):");
                                    String dateInput1 = scanner.next();
                                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
                                    Date appointmentDate1 = simpleDateFormat1.parse(dateInput1);
                                    java.sql.Date sqlDate1 = new java.sql.Date(appointmentDate1.getTime()); // to
                                                                                                            // convert
                                                                                                            // java.util.Date
                                                                                                            // object to
                                                                                                            // a
                                                                                                            // java.sql.Date
                                                                                                            // as it
                                                                                                            // will give
                                                                                                            // error in
                                                                                                            // Date
                                                                                                            // sqlDate1=new
                                                                                                            // Date(appointmentDate1.getTime());
                                    System.out.println("Enter appointment time (hh:mm:ss):");
                                    String timeInput1 = scanner.next();
                                    SimpleDateFormat simpleTimeFormat1 = new SimpleDateFormat("hh:mm:ss");
                                    Date appointmentTime1 = simpleTimeFormat1.parse(timeInput1);
                                    // Convert Date to Time
                                    // java.sql.Time sqlTime1 = new java.sql.Time(appointmentTime1.getTime());
                                    Time sqlTime1 = new Time(appointmentTime1.getTime());
                                    try {
                                        Appointment appointment1 = new Appointment();
                                        appointment1.updateAppointment(patientID3, doctorID3, sqlDate1, sqlTime1);
                                    } catch (Exception e) {
                                        System.out.println("Error parsing date and time: " + e.getMessage());
                                    }
                                    break;

                                case 9:
                                    System.out.println("Enter appointment ID to delete:");
                                    int appointmentID = sc.nextInt();
                                    Appointment appointment2 = new Appointment();
                                    appointment2.deleteAppointment(appointmentID);
                                    break;
                                case 10:
                                    appointment = new Appointment();
                                    appointment.viewAppointment();
                                    break;

                                case 11:
                                
                                    do {
                                        try {
                                            System.out.println("\nHospital Management System");
                                            System.out.println("1. Add a new doctor");
                                            System.out.println("2. Remove a doctor");
                                            System.out.println("3. Display all doctors");
                                            System.out.println("4. Exit");
                                            System.out.print("Enter your choice: ");

                                            choice = sc.nextInt();
                                            sc.nextLine();
                                        } catch (Exception e) {
                                            System.out.println("Invalid input! Please enter a number.");
                                            sc.next();
                                            continue;
                                        }
                                        switch (choice) {
                                            case 1:
                                                System.out.print("Enter doctor ID: ");
                                                int id = sc.nextInt();
                                                sc.nextLine();
                                                System.out.print("Enter doctor full name: ");
                                                String fullName = sc.nextLine();
                                                String doctorFName = "Dr." + fullName;
                                                System.out.print("Enter doctor specialization: ");
                                                String specialization = sc.nextLine();
                                                System.out.println("Enter doctor contact no:");
                                                String contact = sc.next();
                                                doctorList.addDoctor(id, doctorFName, specialization, contact);
                                                System.out.println("Doctor added successfully!");
                                                break;

                                            case 2:
                                                System.out.print("Enter the ID of the doctor to remove: ");
                                                int removeId = sc.nextInt();
                                                doctorList.removeDoctor(removeId);
                                                System.out.println("Doctor removed successfully");
                                                break;

                                            case 3:
                                                System.out.println("--------All Doctors------");
                                                doctorList.displayDoctors();
                                                break;

                                            case 4:
                                                System.out.println("Exiting the LinkedList Doctor Management..");
                                                break;

                                            default:
                                                System.out.println("Enter valid choice.");
                                        }
                                    } while (choice != 4);
                                    break;

                                case 12:
                                    do {
                                        try {
                                            System.out.println("Press 1 to give prescription to patient:");
                                            System.out.println("Press 2 to view prescription given to patient:");
                                            System.out.println("Press 3 to delete prescription given to patient:");
                                            System.out.println("Enter 4 to exit prescription managing!");
                                            System.out.println("Enter your choice-");
                                            choice = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println("Invalid input! Please enter a number.");
                                            sc.next();
                                            continue;
                                        }
                                        switch (choice) {
                                            case 1:
                                                d = new Doctor();
                                                d.givePrescription();
                                                break;

                                            case 2:
                                                Prescription pre = new Prescription();
                                                pre.viewPrescription();
                                                break;
                                            case 3:
                                                pre = new Prescription();
                                                pre.deletePrescription();
                                                break;
                                            case 4:
                                                System.out.println("Exiting Preciption managing..");
                                                break;
                                            default:
                                                System.out.println("Enter valid choice.");
                                                break;
                                        }
                                    } while (choice != 4);

                                    break;
                                case 13:
                                    do {
                                        try {
                                            System.out.println("Press 1 to add medicalHistory records=");
                                            System.out.println("Press 2 to view medicalHistory records=");
                                            System.out.println("Press 3 to exit medicalhistory managing..");
                                            System.out.println("Enter your choice:");
                                            choice = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println("Invalid input! Please enter a number.");
                                            sc.next();
                                            continue;
                                        }
                                        switch (choice) {
                                            case 1:
                                                MedicalHistory mh = new MedicalHistory();
                                                mh.addMedicalHistory();
                                                break;
                                            case 2:
                                                mh = new MedicalHistory();
                                                mh.viewMedicalHistory();
                                                break;
                                            case 3:
                                                System.out.println("Exiting medicalHistory managing..");
                                                break;
                                            default:
                                                System.out.println("Enter valid choice");
                                        }
                                    } while (choice != 3);
                                    break;
                                case 14:
                                    System.out.println("Exiting Doctor Management.");
                                    break;

                                default:
                                    System.out.println("Enter valid choice.");
                                    break;
                            }
                        } while (choice != 14);
                        break;
                    }
                    

                    
                    

                    case 3:
                        do {
                            try {
                                System.out.println("\n---Nurse Management System ---");
                                System.out.println(
                                        "1. Add Nurse \t 2. View Nurses \t 3. Update Nurse \t 4. Delete Nurse \t 5. Allot Nurse to Room \t 6. Allot Nurse to Patient \t 7. Exit");
                                System.out.print("Enter your choice: ");
                                choice = sc.nextInt();
                                sc.nextLine();
                            } catch (Exception e) {
                                System.out.println("Invalid input! Please enter a number.");
                                sc.next();
                                continue;
                            }
                            n = new Nurse();
                            switch (choice) {
                                case 1:
                                    n.addNurse();
                                    break;
                                case 2:
                                    n.displayNurses();
                                    break;
                                case 3:
                                    n = new Nurse();
                                    n.updateNurse();
                                    break;
                                case 4:
                                    System.out.print("Enter nurse ID: ");
                                    int deleteNurseId = sc.nextInt();
                                    sc.nextLine();
                                    n = new Nurse();
                                    n.deleteNurse(deleteNurseId);
                                    break;
                                case 5:
                                    System.out.print("Enter nurse ID: ");
                                    int allotNurseId = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Enter room number: ");
                                    int allotRoomNumber = sc.nextInt();
                                    sc.nextLine();
                                    n = new Nurse();
                                    n.allotNurseToRoom(allotNurseId, allotRoomNumber);
                                    break;
                                case 6:
                                    System.out.println("Enter patient ID:");
                                    int patientID = sc.nextInt();
                                    System.out.println("Enter nurse ID:");
                                    int nurseID = sc.nextInt();
                                    n.assignPatientToNurse(patientID, nurseID);
                                    break;

                                case 7:
                                    System.out.println("Exiting Nurse Management...");
                                    break;

                                default:
                                    System.out.println("Enter valid choice.");
                            }
                        } while (choice != 7);
                        break;

                    case 4:
                        do {
                            try {
                                System.out.println("\n---Room Management System ---");
                                System.out.println(
                                        "1. Add New Room \t 2. Allot Room to Patient \t 3. View All Room Details \t 4. Check Patient in Room \t 5. Update Room \t 6. Delete Room \t 7. Discharge Patient \t 8.use BST to insert,delete,display,search \t 9. Exit");
                                System.out.print("Enter your choice: ");

                                choice = sc.nextInt();
                                sc.nextLine(); 
                            } catch (Exception e) {
                                System.out.println("Invalid input! Please enter a number.");
                                sc.next();
                                continue;
                            }

                            switch (choice) {
                                case 1:
                                    try {
                                    } catch (Exception e) {
                                        System.out.println("Error initializing room: " + e.getMessage());
                                        return;
                                    }
                                    try {
                                        System.out.print("Enter room type (General Ward, Private Ward, ICU): ");
                                        String roomType = sc.nextLine();
                                        System.out.print("Enter room number: ");
                                        int roomNumber = sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("Enter fees: ");
                                        int fees = sc.nextInt();
                                        sc.nextLine();
                                        r = new Room();
                                        // r.addRoom();
                                        if (fees == 100 && roomType.equals("General Ward")
                                                || roomType.equals("General")) {
                                            r = new Room(roomType, roomNumber, (double) fees);
                                            r.addRoom();

                                        } else if (fees == 200 && roomType.equals("Private Ward")
                                                || roomType.equals("Private")) {
                                            r = new Room(roomType, roomNumber, (double) fees);
                                            r.addRoom();

                                        } else if (fees == 500 && roomType.equals("ICU")
                                                || roomType.equals("ICU Ward")) {
                                            r = new Room(roomType, roomNumber, (double) fees);
                                            r.addRoom();

                                        } else {
                                            System.out.println(
                                                    "Enter valid fees either 100/200/500 & valid ward either General/Private/ICU");
                                        }

                                    } catch (Exception e) {
                                        System.out.println("Error adding room: " + e.getMessage());
                                    }
                                    break;

                                case 2:
                                    try {
                                        System.out.print("Enter room number to allot: ");
                                        int roomNumber = sc.nextInt();
                                        System.out.print("Enter patient ID: ");
                                        int patientId = sc.nextInt();
                                        sc.nextLine(); 
                                        r = new Room();
                                        r.setRoom_number(roomNumber);
                                        r.allotRoomToPatient(roomNumber, patientId);
                                    } catch (SQLException e) {
                                        System.out.println("Error allotting room: " + e.getMessage());
                                    }
                                    break;

                                case 3:
                                    try {
                                        r = new Room();
                                        r.viewRoomDetails();
                                    } catch (Exception e) {
                                        System.out.println("Error viewing room details: " + e.getMessage());
                                    }
                                    break;

                                case 4:
                                    try {
                                        System.out.print("Enter room number to check: ");
                                        int roomNumber = sc.nextInt();
                                        sc.nextLine();
                                        r = new Room();
                                        r.checkPatientInRoom(roomNumber);
                                    } catch (SQLException e) {
                                        System.out.println("Error checking patient in room: " + e.getMessage());
                                    }
                                    break;

                                case 5:
                                    try {
                                        System.out.print("Enter room ID to update: ");
                                        int roomId = sc.nextInt();
                                        sc.nextLine(); 
                                        System.out.print("Enter new room type: ");
                                        String newRoomType = sc.nextLine();

                                        r.updateRoom(roomId, newRoomType);
                                    } catch (SQLException e) {
                                        System.out.println("Error updating room: " + e.getMessage());
                                    }
                                    break;

                                case 6:
                                    try {
                                        System.out.print("Enter room no to delete: ");
                                        int roomId = sc.nextInt();
                                        sc.nextLine();
                                        r = new Room();
                                        r.deleteRoom(roomId);
                                    } catch (SQLException e) {
                                        System.out.println("Error deleting room: " + e.getMessage());
                                    }
                                    break;

                                case 7:
                                    try {
                                        System.out.print("Enter room number to discharge patient: ");
                                        int roomNumber = sc.nextInt();
                                        sc.nextLine();
                                        r = new Room();
                                        r.dischargePatient(roomNumber);
                                    } catch (SQLException e) {
                                        System.out.println("Error discharging patient: " + e.getMessage());
                                    }
                                    break;

                                case 8:
                                    do {
                                        try {
                                            System.out.println("1. Insert Room");
                                            System.out.println("2. Delete Room");
                                            System.out.println("3. Search Room");
                                            System.out.println("4. Display All Rooms");
                                            System.out.println("5. Exit");
                                            System.out.print("Enter your choice: ");
                                            choice1 = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println("Invalid input! Please enter a number.");
                                            sc.next();
                                            continue;
                                        }
                                        switch (choice1) {
                                            case 1:

                                                System.out.print("Enter Room ID: ");
                                                int id = sc.nextInt();
                                                System.out.print("Enter Room Type: ");
                                                String type = sc.next();
                                                System.out.print("Enter Room Number: ");
                                                int number = sc.nextInt();
                                                System.out.print("Enter Patient ID: ");
                                                int patientId = sc.nextInt();
                                                System.out.print("Enter Fees: ");
                                                double fee = sc.nextDouble();
                                                bst.insertRoom(id, type, number, patientId, fee);
                                                break;
                                            case 2:
                                                System.out.print("Enter Room ID to Delete: ");
                                                id = sc.nextInt();
                                                bst.deleteRoom(id);
                                                break;
                                            case 3:
                                                System.out.print("Enter Room ID to Search: ");
                                                int id1 = sc.nextInt();
                                                RoomBST.Room foundRoom = bst.searchRoom(id1);
                                                if (foundRoom != null) {
                                                    System.out.println("Room Found: " + foundRoom.getRoom_id());
                                                } else {
                                                    System.out.println("Room Not Found");
                                                }
                                                break;
                                            case 4:
                                                bst.displayAllRooms();
                                                break;
                                            case 5:
                                                System.out.println("Exiting Room Management...");
                                                break;
                                            default:
                                                System.out.println("Enter valid choice!");
                                        }
                                    } while (choice1 != 5);
                                    break;
                                case 9:
                                    System.out.println("Exiting the Room Management..");
                                    break;

                                default:
                                    System.out.println("Enter valid choice.");
                            }
                        } while (choice != 9);
                        break;

                    case 5:
                        System.out.println("Exiting Hospital Management..");
                        System.out.println("THANK YOU");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter valid choice.");
                }
            } while (ch != 5);
        }sc.close();
    }
}
