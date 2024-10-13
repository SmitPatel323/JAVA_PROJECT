package DBMS;

public class CardiologistDoctor implements SpecialistDoctor {

    private String doctor_fullname;
    private String doctor_specialization;
    private String doctor_contact_no;

    
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

    
    public CardiologistDoctor() {
    }

    public CardiologistDoctor(String doctor_fullname, String doctor_specialization, String doctor_contact_no) throws Exception {
        this.doctor_fullname = doctor_fullname;
        this.doctor_specialization = doctor_specialization;
        this.doctor_contact_no = doctor_contact_no;
    }


    public void addSpecialistDoctor() throws Exception {
        System.out.println("Enter doctor fullname:");
        String doctor_fullname = sc.nextLine();
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

        System.out.println("Adding cardiologist specialist...");
        String sql = "INSERT INTO doctors (doctor_fullname, doctor_specialization, doctor_contact_no) VALUES (?, ?, ?)";
        Doctor pst = new Doctor();
        pst.pst = pst.dc.con.prepareStatement(sql);
        pst.pst.setString(1, "Dr. "+doctor_fullname);
        pst.pst.setString(2, "Cardiology");
        pst.pst.setString(3, doctor_contact_no);
        int rs = pst.pst.executeUpdate();
        if (rs > 0) {
            System.out.println("Cardiologist specialist added successfully.");
        } else {
            System.out.println("Insertion failed.");
        }
    }
        
    }
