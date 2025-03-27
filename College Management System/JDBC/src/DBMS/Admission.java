package DBMS;
import JAVAMAIN.*;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Admission {
    int sr_no ;
    String fullname , date_Of_Birth ,gender , phonenumber , email , address , course ,previousschool , recentqualification;
    double percentage;
    String aadharnumber;
    Blob aadharImage , marksheetImage , lcImage , candidateImage ;

    ConnectionCollage coc ;
    PreparedStatement pst ;
    CheckInputs check = new CheckInputs();
    public Admission() throws  Exception{
        coc = new ConnectionCollage();
    }
    public Admission(String fullname, String date_Of_Birth, String gender, String phonenumber, String email,
            String address,String course, String previousschool, String recentqualification, double percentage, String aadharnumber,Blob aadharImage, Blob marksheetImage, Blob lcImage, Blob candidateImage) throws Exception {
        this.fullname = fullname;
        this.date_Of_Birth = date_Of_Birth;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.course = course;
        this.previousschool = previousschool;
        this.recentqualification = recentqualification;
        this.percentage = percentage;
        this.aadharnumber = aadharnumber;
        this.aadharImage = aadharImage;
        this.marksheetImage = marksheetImage;
        this.lcImage = lcImage;
        this.candidateImage = candidateImage;
        coc = new ConnectionCollage();

    }
    public int getSr_no() {
        return sr_no;
    }
    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getdate_Of_Birth() {
        return date_Of_Birth;
    }
    public void setdate_Of_Birth(String date_Of_Birth) {
        this.date_Of_Birth = date_Of_Birth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPreviousschool() {
        return previousschool;
    }
    public void setPreviousschool(String previousschool) {
        this.previousschool = previousschool;
    }
    public String getRecentqualification() {
        return recentqualification;
    }
    public void setRecentqualification(String recentqualification) {
        this.recentqualification = recentqualification;
    }
    public double getPercentage() {
        return percentage;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    public String getAadharnumber() {
        return aadharnumber;
    }
    public void setAadharnumber(String aadharnumber) {
        this.aadharnumber = aadharnumber;
    }

    public Blob getAadharImage() {
        return aadharImage;
    }
    public void setAadharImage(Blob aadharImage) {
        this.aadharImage = aadharImage;
    }
    public Blob getMarksheetImage() {
        return marksheetImage;
    }
    public void setMarksheetImage(Blob marksheetImage) {
        this.marksheetImage = marksheetImage;
    }
    public Blob getLcImage() {
        return lcImage;
    }
    public void setLcImage(Blob lcImage) {
        this.lcImage = lcImage;
    }
    public Blob getCandidateImage() {
        return candidateImage;
    }
    public void setCandidateImage(Blob candidateImage) {
        this.candidateImage = candidateImage;
    }
    public void applyAdmisssion() throws  Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Full Name:");
        String fullname = sc.nextLine();
        String dateOfBirth = check.checkDate();
        System.out.println("Enter Gender:");
        String gender = sc.nextLine();
        String phoneNumber = check.validateMobileNumber();
        System.out.println("Enter Email:");
        String email = sc.nextLine();
        System.out.println("Enter Address:");
        String address = sc.nextLine();
        System.out.println("Enter you course:");
        String course = check.courseName();
        System.out.println("Enter Recent Qualification:");
        String recentQualification = sc.nextLine();
        System.out.println("Enter Percentage:");
        double percentage = sc.nextDouble();
        String aadharNumber = check.validateAadharNumber();
        sc.nextLine();
        System.out.println("Enter Path to Aadhar Image:");
        String aadharImage = sc.nextLine();
        System.out.println("Enter Path to Marksheet Image:");
        String marksheetImage = sc.nextLine();
        System.out.println("Enter Path to LC Image:");
        String lcImage = sc.nextLine();
        System.out.println("Enter Path to Candidate Image:");
        String candidateImage = sc.nextLine();

        String sql = "INSERT INTO admissions (fullname, date_Of_Birth, gender, phonenumber, email, address,course, recentqualification, percentage, aadharnumber,aadharImage,marksheetImage , lcImage ,candidateImage) VALUES ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?) order by percentage";
        pst = coc.con.prepareStatement(sql);
        pst.setString(1, fullname);
        pst.setString(2, dateOfBirth);
        pst.setString(3, gender);
        pst.setString(4, phoneNumber);
        pst.setString(5, email);
        pst.setString(6, address);
        pst.setString(7, course);
        pst.setString(8, recentQualification);
        pst.setDouble(9, percentage);
        pst.setString(10, aadharNumber);

        FileInputStream fis = new FileInputStream(aadharImage+".jpg");
        pst.setBlob(11, fis);

        FileInputStream fis1 = new FileInputStream(marksheetImage+".jpg");
        pst.setBlob(12, fis1);

        FileInputStream fis2 = new FileInputStream(lcImage+".jpg");
        pst.setBlob(13, fis2);

        FileInputStream fis3 = new FileInputStream(candidateImage+".jpg");
        pst.setBlob(14, fis3);
        
        pst.executeUpdate();
    }

    public void deleteApplication(String aadhar) throws Exception {
        String query = "DELETE FROM admissions WHERE aadharnumber = ?";
        pst = coc.con.prepareStatement(query);
        pst.setString(1, aadhar);
        int rs =pst.executeUpdate();

        if (rs > 0) {
            System.out.println("Delete Success");
        } else {
            System.out.println("Delete Failed");
        }
    }


    public void displayByPercentage() throws Exception
    {
        String query = "SELECT * FROM admissions order by percentage desc";
        pst = coc.con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("SR No: " + rs.getInt(1));
            System.out.println("Full Name: " + rs.getString(2));
            System.out.println("Date Of Birth: " + rs.getString(3));
            System.out.println("Gender: " + rs.getString(4));
            System.out.println("Phone Number: " + rs.getString(5));
            System.out.println("Email: " + rs.getString(6));
            System.out.println("Address: " + rs.getString(7));
            System.out.println("Recent Qualification: " + rs.getString(8));
            System.out.println("Percentage: " + rs.getDouble(9));
            System.out.println("Aadhar Number: " + rs.getString(10));

            FileOutputStream fos = new FileOutputStream("AadharImage"+rs.getInt(1)+".jpg");
            Blob b = rs.getBlob(11);
            fos.write(b.getBytes(1,(int)b.length()));
            fos.close();

            FileOutputStream fos1 = new FileOutputStream("MarksheetImage"+rs.getInt(1)+".jpg");
            Blob b1 = rs.getBlob(12);
            fos1.write(b1.getBytes(1,(int)b1.length()));
            fos1.close();

            FileOutputStream fos2 = new FileOutputStream("LCImage"+rs.getInt(1)+".jpg");
            Blob b2 = rs.getBlob(13);
            fos2.write(b2.getBytes(1,(int)b2.length()));
            fos2.close();

            FileOutputStream fos3 = new FileOutputStream("CandidateImage"+rs.getInt(1)+".jpg");
            Blob b3 = rs.getBlob(14);
            fos3.write(b3.getBytes(1,(int)b3.length()));
            fos3.close();

        }
    }

    public void updateDetails(String aadhar) throws Exception {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("------Update Admission Details------");
        System.out.println();

        String checkSql = "SELECT * FROM admissions WHERE AadharNumber = ?";
        pst = coc.con.prepareStatement(checkSql);
        pst.setString(1, aadhar);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("No record found for Aadhar Number: " + aadhar);
            return;
        }
    
        
        System.out.println("Press 1 to update Full Name");
        System.out.println("Press 2 to update Date of Birth");
        System.out.println("Press 3 to update Gender");
        System.out.println("Press 4 to update Phone Number");
        System.out.println("Press 5 to update Email");
        System.out.println("Press 6 to update Address");
        System.out.println("Press 7 to update Course");
        System.out.println("Press 8 to update Previous School");
        System.out.println("Press 9 to update Percentage");
        System.out.println("Press 10 to update Aadhar Number");
        System.out.println("Press 11 to update Aadhar Image");
        System.out.println("Press 12 to update Marksheet Image");
        System.out.println("Press 13 to update LC Image");
        System.out.println("Press 14 to update Candidate Image");
        int choice = check.checkInput();
    
        String updateSql = "";
        switch (choice) {
            case 1:
                System.out.print("Enter new Full Name: ");
                String newFullName = sc.nextLine();
                updateSql = "UPDATE admissions SET fullname = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newFullName);
                pst.setString(2, aadhar);
                break;
            case 2:
                String newDateOfBirth = check.checkDate();
                updateSql = "UPDATE admissions SET date_Of_Birth = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newDateOfBirth);
                pst.setString(2, aadhar);
                break;
            case 3:
                System.out.print("Enter new Gender: ");
                String newGender = sc.nextLine();
                updateSql = "UPDATE admissions SET gender = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newGender);
                pst.setString(2, aadhar);
                break;
            case 4:
                String newPhoneNumber = check.validateMobileNumber();
                updateSql = "UPDATE admissions SET phonenumber = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newPhoneNumber);
                pst.setString(2, aadhar);
                break;
            case 5:
                System.out.print("Enter new Email: ");
                String newEmail = sc.nextLine();
                updateSql = "UPDATE admissions SET email = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newEmail);
                pst.setString(2, aadhar);
                break;
            case 6:
                System.out.print("Enter new Address: ");
                String newAddress = sc.nextLine();
                updateSql = "UPDATE admissions SET address = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newAddress);
                pst.setString(2, aadhar);
                break;
            case 7:
                System.out.print("Enter your course: ");
                String course = check.courseName();
                updateSql = "UPDATE admissions SET course = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, course);
                pst.setString(2, aadhar);
                break;
            case 8:
                System.out.print("Enter new Recent Qualification: ");
                String newRecentQualification = sc.nextLine();
                updateSql = "UPDATE admissions SET recentqualification = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setString(1, newRecentQualification);
                pst.setString(2, aadhar);
                break;
            case 9:
                System.out.print("Enter new Percentage: ");
                double newPercentage = sc.nextDouble();
                updateSql = "UPDATE admissions SET percentage = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setDouble(1, newPercentage);
                pst.setString(2, aadhar);
                break;
            case 10:
                System.out.print("Enter new Aadhar Number: ");
                long newAadharNumber = sc.nextLong();
                updateSql = "UPDATE admissions SET AadharNumber = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setLong(1, newAadharNumber);
                pst.setString(2, aadhar);
                break;
            case 11:
                System.out.print("Enter new Aadhar Image path (e.g., path/to/image.jpg): ");
                String newAadharImage = sc.nextLine();
                updateSql = "UPDATE admissions SET aadharImage = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                FileInputStream fis = new FileInputStream(newAadharImage+".jpg");
                pst.setBlob(1, fis);
                pst.setString(2, aadhar);
                break;
            case 12:
                System.out.print("Enter new Marksheet Image path (e.g., path/to/image.jpg): ");
                String newMarksheetImage = sc.nextLine();
                updateSql = "UPDATE admissions SET marksheetImage = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                FileInputStream fis1 = new FileInputStream(newMarksheetImage+".jpg");
                pst.setBlob(1, fis1);
                pst.setString(2, aadhar);
                break;
            case 13:
                System.out.print("Enter new LC Image path (e.g., path/to/image.jpg): ");
                String newLcImage = sc.nextLine();
                updateSql = "UPDATE admissions SET lcImage = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                FileInputStream fis2 = new FileInputStream(newLcImage+".jpg");
                pst.setBlob(1, fis2);
                pst.setString(2, aadhar);
                break;
            case 14:
                System.out.print("Enter new Candidate Image path (e.g., path/to/image.jpg): ");
                String newCandidateImage = sc.nextLine();
                updateSql = "UPDATE admissions SET candidateImage = ? WHERE AadharNumber = ?";
                pst = coc.con.prepareStatement(updateSql);
                FileInputStream fis3 = new FileInputStream(newCandidateImage+".jpg");
                pst.setBlob(1, fis3);
                pst.setString(2, aadhar);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        int updateRs = pst.executeUpdate();
        if (updateRs > 0) {
            System.out.println("Admission record updated successfully.");
        } else {
            System.out.println("Failed to update admission record.");
        }
    }

    public void searchAadhar(String aadhar) throws Exception
    {
        String query = "SELECT * FROM admissions WHERE AadharNumber = ?";
        pst = coc.con.prepareStatement(query);
        pst.setString(1, aadhar);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("SR No: " + rs.getInt(1));
            System.out.println("Full Name: " + rs.getString(2));
            System.out.println("Date Of Birth: " + rs.getString(3));
            System.out.println("Gender: " + rs.getString(4));
            System.out.println("Phone Number: " + rs.getString(5));
            System.out.println("Email: " + rs.getString(6));
            System.out.println("Address: " + rs.getString(7));
            System.out.println("Course: " + rs.getString(8));
            System.out.println("Recent Qualification: " + rs.getString(9));
            System.out.println("Percentage: " + rs.getDouble(10));
            System.out.println("Aadhar Number: " + rs.getString(11));

            FileOutputStream fos = new FileOutputStream("AadharImage"+rs.getInt(1)+".jpg");
            Blob b = rs.getBlob(12);
            fos.write(b.getBytes(1,(int)b.length()));
            fos.close();

            FileOutputStream fos1 = new FileOutputStream("MarksheetImage"+rs.getInt(1)+".jpg");
            Blob b1 = rs.getBlob(13);
            fos1.write(b1.getBytes(1,(int)b1.length()));
            fos1.close();

            FileOutputStream fos2 = new FileOutputStream("LCImage"+rs.getInt(1)+".jpg");
            Blob b2 = rs.getBlob(14);
            fos2.write(b2.getBytes(1,(int)b2.length()));
            fos2.close();

            FileOutputStream fos3 = new FileOutputStream("CandidateImage"+rs.getInt(1)+".jpg");
            Blob b3 = rs.getBlob(15);
            fos3.write(b3.getBytes(1,(int)b3.length()));
            fos3.close();


        }
    }
    public void displayCourseWise(String course) throws Exception
    {
        String query = "SELECT * FROM admissions WHERE course = ?";
        pst = coc.con.prepareStatement(query);
        pst.setString(1, course);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("SR No: " + rs.getInt(1));
            System.out.println("Full Name: " + rs.getString(2));
            System.out.println("Date Of Birth: " + rs.getString(3));
            System.out.println("Gender: " + rs.getString(4));
            System.out.println("Phone Number: " + rs.getString(5));
            System.out.println("Email: " + rs.getString(6));
            System.out.println("Address: " + rs.getString(7));
            System.out.println("Course: " + rs.getString(8));
            System.out.println("Recent Qualification: " + rs.getString(9));
            System.out.println("Percentage: " + rs.getDouble(10));
            System.out.println("Aadhar Number: " + rs.getString(11));

            FileOutputStream fos = new FileOutputStream("AadharImage"+rs.getInt(1)+".jpg");
            Blob b = rs.getBlob(12);
            fos.write(b.getBytes(1,(int)b.length()));
            fos.close();

            FileOutputStream fos1 = new FileOutputStream("MarksheetImage"+rs.getInt(1)+".jpg");
            Blob b1 = rs.getBlob(13);
            fos1.write(b1.getBytes(1,(int)b1.length()));
            fos1.close();

            FileOutputStream fos2 = new FileOutputStream("LCImage"+rs.getInt(1)+".jpg");
            Blob b2 = rs.getBlob(14);
            fos2.write(b2.getBytes(1,(int)b2.length()));
            fos2.close();

            FileOutputStream fos3 = new FileOutputStream("CandidateImage"+rs.getInt(1)+".jpg");
            Blob b3 = rs.getBlob(15);
            fos3.write(b3.getBytes(1,(int)b3.length()));
            fos3.close();


        }


    }

    public void searchPercentage(double Percentage) throws Exception
    {
        String query = "SELECT * FROM admissions WHERE percentage > ?";
        pst = coc.con.prepareStatement(query);
        pst.setDouble(1, percentage);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("SR No: " + rs.getInt(1));
            System.out.println("Full Name: " + rs.getString(2));
            System.out.println("Date Of Birth: " + rs.getString(3));
            System.out.println("Gender: " + rs.getString(4));
            System.out.println("Phone Number: " + rs.getString(5));
            System.out.println("Email: " + rs.getString(6));
            System.out.println("Address: " + rs.getString(7));
            System.out.println("Course: " + rs.getString(8));
            System.out.println("Recent Qualification: " + rs.getString(9));
            System.out.println("Percentage: " + rs.getDouble(10));
            System.out.println("Aadhar Number: " + rs.getString(11));

            FileOutputStream fos = new FileOutputStream("AadharImage"+rs.getInt(1)+".jpg");
            Blob b = rs.getBlob(12);
            fos.write(b.getBytes(1,(int)b.length()));
            fos.close();

            FileOutputStream fos1 = new FileOutputStream("MarksheetImage"+rs.getInt(1)+".jpg");
            Blob b1 = rs.getBlob(13);
            fos1.write(b1.getBytes(1,(int)b1.length()));
            fos1.close();

            FileOutputStream fos2 = new FileOutputStream("LCImage"+rs.getInt(1)+".jpg");
            Blob b2 = rs.getBlob(14);
            fos2.write(b2.getBytes(1,(int)b2.length()));
            fos2.close();

            FileOutputStream fos3 = new FileOutputStream("CandidateImage"+rs.getInt(1)+".jpg");
            Blob b3 = rs.getBlob(15);
            fos3.write(b3.getBytes(1,(int)b3.length()));
            fos3.close();

        }
    }

    
}
