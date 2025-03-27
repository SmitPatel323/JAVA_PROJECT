package DBMS;
import JAVAMAIN.*;
import java.sql.*;
import java.util.Scanner;

public class Attendence {
    int attendence_id ;
    String batch , name;
    int enrollmentNo;
    int attendLec_Java,totalLec_java;
    double percentage_Java;
    int attendLec_DS,totalLec_DS;
    double percentage_DS;
    int attendLec_DBMS,totalLec_DBMS;
    double percentage_DBMS;
    int attendLec,totalLec;
    double overall_Percentage;

    ConnectionCollage coc;
    PreparedStatement pst;
    CheckInputs check = new CheckInputs();
    public Attendence()  throws Exception{
        coc = new ConnectionCollage();
    }
    public Attendence(String batch, String name, int enrollmentNo, int attendLec_Java, int totalLec_java,
            double percentage_Java, int attendLec_DS, int totalLec_DS, double percentage_DS, int attendLec_DBMS,
            int totalLec_DBMS, double percentage_DBMS, int attendLec, int totalLec, double overall_Percentage) throws Exception {
        this.batch = batch;
        this.name = name;
        this.enrollmentNo = enrollmentNo;
        this.attendLec_Java = attendLec_Java;
        this.totalLec_java = totalLec_java;
        this.percentage_Java = percentage_Java;
        this.attendLec_DS = attendLec_DS;
        this.totalLec_DS = totalLec_DS;
        this.percentage_DS = percentage_DS;
        this.attendLec_DBMS = attendLec_DBMS;
        this.totalLec_DBMS = totalLec_DBMS;
        this.percentage_DBMS = percentage_DBMS;
        this.attendLec = attendLec;
        this.totalLec = totalLec;
        this.overall_Percentage = overall_Percentage;
        coc = new ConnectionCollage();
    }
    
    public Attendence(int attendLec_Java, int totalLec_java, double percentage_Java, int attendLec_DS, int totalLec_DS,
            double percentage_DS, int attendLec_DBMS, int totalLec_DBMS, double percentage_DBMS, int attendLec,
            int totalLec, double overall_Percentage) throws Exception {
        this.attendLec_Java = attendLec_Java;
        this.totalLec_java = totalLec_java;
        this.percentage_Java = percentage_Java;
        this.attendLec_DS = attendLec_DS;
        this.totalLec_DS = totalLec_DS;
        this.percentage_DS = percentage_DS;
        this.attendLec_DBMS = attendLec_DBMS;
        this.totalLec_DBMS = totalLec_DBMS;
        this.percentage_DBMS = percentage_DBMS;
        this.attendLec = attendLec;
        this.totalLec = totalLec;
        this.overall_Percentage = overall_Percentage;
        coc = new ConnectionCollage();
    }
    public int getAttendence_id() {
        return attendence_id;
    }
    public void setAttendence_id(int attendence_id) {
        this.attendence_id = attendence_id;
    }
    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getEnrollmentNo() {
        return enrollmentNo;
    }
    public void setEnrollmentNo(int enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }
    public int getAttendLec_Java() {
        return attendLec_Java;
    }
    public void setAttendLec_Java(int attendLec_Java) {
        this.attendLec_Java = attendLec_Java;
    }
    public int getTotalLec_java() {
        return totalLec_java;
    }
    public void setTotalLec_java(int totalLec_java) {
        this.totalLec_java = totalLec_java;
    }
    public double getPercentage_Java() {
        return percentage_Java;
    }
    public void setPercentage_Java(double percentage_Java) {
        this.percentage_Java = percentage_Java;
    }
    public int getAttendLec_DS() {
        return attendLec_DS;
    }
    public void setAttendLec_DS(int attendLec_DS) {
        this.attendLec_DS = attendLec_DS;
    }
    public int getTotalLec_DS() {
        return totalLec_DS;
    }
    public void setTotalLec_DS(int totalLec_DS) {
        this.totalLec_DS = totalLec_DS;
    }
    public double getPercentage_DS() {
        return percentage_DS;
    }
    public void setPercentage_DS(double percentage_DS) {
        this.percentage_DS = percentage_DS;
    }
    public int getAttendLec_DBMS() {
        return attendLec_DBMS;
    }
    public void setAttendLec_DBMS(int attendLec_DBMS) {
        this.attendLec_DBMS = attendLec_DBMS;
    }
    public int getTotalLec_DBMS() {
        return totalLec_DBMS;
    }
    public void setTotalLec_DBMS(int totalLec_DBMS) {
        this.totalLec_DBMS = totalLec_DBMS;
    }
    public double getPercentage_DBMS() {
        return percentage_DBMS;
    }
    public void setPercentage_DBMS(double percentage_DBMS) {
        this.percentage_DBMS = percentage_DBMS;
    }
    public int getAttendLec() {
        return attendLec;
    }
    public void setAttendLec(int attendLec) {
        this.attendLec = attendLec;
    }
    public int getTotalLec() {
        return totalLec;
    }
    public void setTotalLec(int totalLec) {
        this.totalLec = totalLec;
    }
    public double getOverall_Percentage() {
        return overall_Percentage;
    }
    public void setOverall_Percentage(double overall_Percentage) {
        this.overall_Percentage = overall_Percentage;
    }
    
    public void insertAttendence(int enrollmentNo) throws Exception {
        String checkSql = "SELECT COUNT(*) FROM attendence WHERE enrollmentNo = ?";
        pst = coc.con.prepareStatement(checkSql);
        pst.setInt(1, enrollmentNo);
        ResultSet rs = pst.executeQuery();
        
        rs.next();
        int count = rs.getInt(1);
        
        if (count > 0) {
            
            String sql = "Update attendence set attendLec_Java=? , totalLec_java = ? , percentage_Java = ? , attendLec_DS = ? , totalLec_DS = ? , percentage_DS = ? , attendLec_DBMS = ? , totalLec_DBMS = ? , percentage_DBMS = ? , attendLec = ? , totalLec = ? , Overall_Percentage = ? where enrollmentNo = ?";
            pst = coc.con.prepareStatement(sql);
            pst.setInt(1, attendLec_Java);
            pst.setInt(2, totalLec_java);
            pst.setDouble(3, percentage_Java);
            pst.setInt(4, attendLec_DS);
            pst.setInt(5, totalLec_DS);
            pst.setDouble(6, percentage_DS);
            pst.setInt(7, attendLec_DBMS);
            pst.setInt(8, totalLec_DBMS);
            pst.setDouble(9, percentage_DBMS);
            pst.setInt(10, attendLec);
            pst.setInt(11, totalLec);
            pst.setDouble(12, overall_Percentage);
            pst.setInt(13,enrollmentNo);

            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("attendence record inserted successfully.");
            } else {
                System.out.println("Failed to insert attendence record.");
            }
        } else {
            System.out.println("Enrollment number does not exist.");
        }
    }

    public void deleteAttendence(int enrollmentNo) throws Exception {
        String sql = "DELETE * FROM Attendence WHERE enrollmentNo = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, enrollmentNo);
        int rs = pst.executeUpdate();
        if(rs>0)
        {
            System.out.println("Delete sucess");
        }
        else
        {
            System.out.println("Delete Failed");
        }

    }

    public void displayAttendence() throws Exception {
        String sql = "SELECT * FROM Attendence";
        pst = coc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println("Attendence Id : "+rs.getInt(1));
            System.out.println("Enrollment No: " + rs.getInt(4));
            System.out.println("Batch: " + rs.getString(2));
            System.out.println("Name: " + rs.getString(3));
            System.out.println("Attend Lec Java: " + rs.getInt(5));
            System.out.println("Total Lec Java: " + rs.getInt(6));
            System.out.println("Percentage Java: " + rs.getDouble(7));
            System.out.println("Attend Lec DS: " + rs.getInt(8));
            System.out.println("Total Lec DS: " + rs.getInt(9));
            System.out.println("Percentage DS: " + rs.getDouble(10));
            System.out.println("Attend Lec DBMS: " + rs.getInt(11));
            System.out.println("Total Lec DBMS: " + rs.getInt(12));
            System.out.println("Percentage DBMS: " + rs.getDouble(13));
            System.out.println("Attend Lec: " + rs.getInt(14));
            System.out.println("Total Lec: " + rs.getInt(15));
            System.out.println("Percentage: " + rs.getDouble(16));
            System.out.println("----------");
            System.out.println();
        }
    }
    public void displayLessAttendence() throws Exception {
        String sql = "SELECT * FROM less_Attendence";
        pst = coc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println("Attendence Id : "+rs.getInt(1));
            System.out.println("Enrollment No: " + rs.getInt(4));
            System.out.println("Batch: " + rs.getString(2));
            System.out.println("Name: " + rs.getString(3));
            System.out.println("Attend Lec Java: " + rs.getInt(5));
            System.out.println("Total Lec Java: " + rs.getInt(6));
            System.out.println("Percentage Java: " + rs.getDouble(7));
            System.out.println("Attend Lec DS: " + rs.getInt(8));
            System.out.println("Total Lec DS: " + rs.getInt(9));
            System.out.println("Percentage DS: " + rs.getDouble(10));
            System.out.println("Attend Lec DBMS: " + rs.getInt(11));
            System.out.println("Total Lec DBMS: " + rs.getInt(12));
            System.out.println("Percentage DBMS: " + rs.getDouble(13));
            System.out.println("Attend Lec: " + rs.getInt(14));
            System.out.println("Total Lec: " + rs.getInt(15));
            System.out.println("Percentage: " + rs.getDouble(16));
            System.out.println("----------");
            System.out.println();
        }
    }
    public void displayCompleteAttendence() throws Exception {
        String sql = "SELECT * FROM complete_attendence";
        pst = coc.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println("Attendence Id : "+rs.getInt(1));
            System.out.println("Enrollment No: " + rs.getInt(4));
            System.out.println("Batch: " + rs.getString(2));
            System.out.println("Name: " + rs.getString(3));
            System.out.println("Attend Lec Java: " + rs.getInt(5));
            System.out.println("Total Lec Java: " + rs.getInt(6));
            System.out.println("Percentage Java: " + rs.getDouble(7));
            System.out.println("Attend Lec DS: " + rs.getInt(8));
            System.out.println("Total Lec DS: " + rs.getInt(9));
            System.out.println("Percentage DS: " + rs.getDouble(10));
            System.out.println("Attend Lec DBMS: " + rs.getInt(11));
            System.out.println("Total Lec DBMS: " + rs.getInt(12));
            System.out.println("Percentage DBMS: " + rs.getDouble(13));
            System.out.println("Attend Lec: " + rs.getInt(14));
            System.out.println("Total Lec: " + rs.getInt(15));
            System.out.println("Percentage: " + rs.getDouble(16));
            System.out.println("----------");
            System.out.println();
        }
    }


    public void searchAttendence(int enrollmentNo) throws SQLException {
        String sql = "SELECT * FROM Attendence WHERE enrollmentNo = ?";
        pst = coc.con.prepareStatement(sql);
        pst.setInt(1, enrollmentNo);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            System.out.println("Attendence Id : "+rs.getInt(1));
            System.out.println("Enrollment No: " + rs.getInt(4));
            System.out.println("Batch: " + rs.getString(2));
            System.out.println("Name: " + rs.getString(3));
            System.out.println("Attend Lec Java: " + rs.getInt(5));
            System.out.println("Total Lec Java: " + rs.getInt(6));
            System.out.println("Percentage Java: " + rs.getDouble(7));
            System.out.println("Attend Lec DS: " + rs.getInt(8));
            System.out.println("Total Lec DS: " + rs.getInt(9));
            System.out.println("Percentage DS: " + rs.getDouble(10));
            System.out.println("Attend Lec DBMS: " + rs.getInt(11));
            System.out.println("Total Lec DBMS: " + rs.getInt(12));
            System.out.println("Percentage DBMS: " + rs.getDouble(13));
            System.out.println("Attend Lec: " + rs.getInt(14));
            System.out.println("Total Lec: " + rs.getInt(15));
            System.out.println("Percentage: " + rs.getDouble(16));
            System.out.println("----------");
            System.out.println();
        } else {
            System.out.println("No record found for enrollment number: " + enrollmentNo);
        }
    }

    public void searchByPercentage(double percentage) throws Exception
    {
        String sql = "{CALL searchByPercentage(?)}";
        CallableStatement cst = coc.con.prepareCall(sql);
        cst.setDouble(1, percentage);

        ResultSet rs = cst.executeQuery();

        if (rs.next()) {
            System.out.println("Attendence Id : "+rs.getInt(1));
            System.out.println("Enrollment No: " + rs.getInt(4));
            System.out.println("Batch: " + rs.getString(2));
            System.out.println("Name: " + rs.getString(3));
            System.out.println("Attend Lec Java: " + rs.getInt(5));
            System.out.println("Total Lec Java: " + rs.getInt(6));
            System.out.println("Percentage Java: " + rs.getDouble(7));
            System.out.println("Attend Lec DS: " + rs.getInt(8));
            System.out.println("Total Lec DS: " + rs.getInt(9));
            System.out.println("Percentage DS: " + rs.getDouble(10));
            System.out.println("Attend Lec DBMS: " + rs.getInt(11));
            System.out.println("Total Lec DBMS: " + rs.getInt(12));
            System.out.println("Percentage DBMS: " + rs.getDouble(13));
            System.out.println("Attend Lec: " + rs.getInt(14));
            System.out.println("Total Lec: " + rs.getInt(15));
            System.out.println("Percentage: " + rs.getDouble(16));
            System.out.println("----------");
            System.out.println();
        } else {
            System.out.println("No record found for enrollment number: " + enrollmentNo);
        }
    }

    public void updateAttendence(int enrollmentNo) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Update Attendence Details------");
        System.out.println();

        String checkSql = "SELECT * FROM Attendence WHERE enrollmentNo = ?";
        pst = coc.con.prepareStatement(checkSql);
        pst.setInt(1, enrollmentNo);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            System.out.println("No record found for enrollment number: " + enrollmentNo);
            return;
        }

        System.out.println("Press 1 to update Attend Lec Java");
        System.out.println("Press 2 to update Total Lec Java");
        System.out.println("Press 3 to update Attend Lec DS");
        System.out.println("Press 4 to update Total Lec DS");
        System.out.println("Press 5 to update Attend Lec DBMS");
        System.out.println("Press 6 to update Total Lec DBMS");
        int choice = check.checkInput();

        String updateSql = "";
        switch (choice) {
            case 1:
                System.out.print("Enter new Attend Lec Java: ");
                int newAttendLecJava = sc.nextInt();
                double percentage_Java = ((double)newAttendLecJava/(double)rs.getInt(6))*100;
                int attendLec = (newAttendLecJava +rs.getInt(8) +rs.getInt(11));
                double overall_Percentage = ((double)attendLec/rs.getInt(15))*100;
                updateSql = "UPDATE Attendence SET attendLec_Java = ?  , percentage_Java = ? ,attendLec = ? , overall_Percentage = ?  WHERE enrollmentNo = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newAttendLecJava);
                pst.setDouble(2,percentage_Java);
                pst.setInt(3,attendLec);
                pst.setDouble(4,overall_Percentage);
                pst.setInt(5, enrollmentNo);
                break;
            case 2:
                System.out.print("Enter new Total Lec Java: ");
                int newTotalLecJava = sc.nextInt();
                double percentage_Java1 = ((double)rs.getInt(5)/(double)newTotalLecJava)*100;
                int totalLec = (totalLec_java + rs.getInt(9)+ rs.getInt(12));
                double overall_Percentage1 = (rs.getInt(14)/totalLec)*100;
                updateSql = "UPDATE Attendence SET totalLec_java = ? , percentage_Java = ? , totalLec = ? , overall_Percentage = ?  WHERE enrollmentNo = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newTotalLecJava);
                pst.setDouble(2,percentage_Java1);
                pst.setInt(3,totalLec);
                pst.setDouble(4,overall_Percentage1);
                pst.setInt(5, enrollmentNo);
                break;
            case 3:
                System.out.print("Enter new Attend Lec DS: ");
                int newAttendLecDS = sc.nextInt();
                double percentage_DS = ((double)newAttendLecDS/(double)rs.getInt(9))*100;
                int attendLec2 = (rs.getInt(5) +newAttendLecDS +rs.getInt(11));
                double overall_Percentage3 = ((double)attendLec2/rs.getInt(15))*100;
                updateSql = "UPDATE Attendence SET attendLec_DS = ? ,  percentage_Ds = ? ,attendLec = ? , overall_Percentage = ?  WHERE enrollmentNo = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newAttendLecDS);
                pst.setDouble(2,percentage_DS);
                pst.setInt(3,attendLec2);
                pst.setDouble(4,overall_Percentage3);
                pst.setInt(5, enrollmentNo);
                break;
            case 4:
                System.out.print("Enter new Total Lec DS: ");
                int newTotalLecDS = sc.nextInt();
                double percentage_DS1 = ((double)rs.getInt(8)/(double)newTotalLecDS)*100;
                int totalLec2 = (rs.getInt(6) + newTotalLecDS+ rs.getInt(12));
                double overall_Percentage4 = (rs.getInt(14)/totalLec2)*100;
                updateSql = "UPDATE Attendence SET totalLec_DS = ? ,percentage_Ds = ?  , totalLec = ? , overall_Percentage = ?  WHERE enrollmentNo = ?";

                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newTotalLecDS);
                pst.setDouble(2,percentage_DS1);
                pst.setInt(3,totalLec2);
                pst.setDouble(4,overall_Percentage4);
                pst.setInt(5, enrollmentNo);
                break;
            case 5:
                System.out.print("Enter new Attend Lec DBMS: ");
                int newAttendLecDBMS = sc.nextInt();
                double percentage_DBMS = ((double)newAttendLecDBMS/(double)rs.getInt(12))*100;
                int attendLec3 = (rs.getInt(5)+rs.getInt(8) +newAttendLecDBMS );
                double overall_Percentage5 = ((double)attendLec3/rs.getInt(15))*100;
                updateSql = "UPDATE Attendence SET attendLec_DBMS = ?,percentage_Dbms = ? , ,attendLec = ? , overall_Percentage = ?   WHERE enrollmentNo = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newAttendLecDBMS);
                pst.setDouble(2,percentage_DBMS);
                pst.setInt(3,attendLec3);
                pst.setDouble(4,overall_Percentage5);
                pst.setInt(5, enrollmentNo);
                break;
            case 6:
                System.out.print("Enter new Total Lec DBMS: ");
                int newTotalLecDBMS = sc.nextInt();
                double percentage_DBMS1 = ((double)rs.getInt(11)/(double)newTotalLecDBMS)*100;
                int totalLec3 = (rs.getInt(6) + rs.getInt(12)+ newTotalLecDBMS);
                double overall_Percentage6 = (rs.getInt(14)/totalLec3)*100;
                updateSql = "UPDATE Attendence SET totalLec_DBMS = ? ,percentage_Dbms = ? ,  totalLec = ? , overall_Percentage = ?  WHERE enrollmentNo = ?";
                pst = coc.con.prepareStatement(updateSql);
                pst.setInt(1, newTotalLecDBMS);
                pst.setDouble(2,percentage_DBMS1);
                pst.setInt(3,totalLec3);
                pst.setDouble(4,overall_Percentage6);
                pst.setInt(5, enrollmentNo);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        int updateRs = pst.executeUpdate();
        if (updateRs > 0) {
            System.out.println("Attendence record updated successfully.");
        } else {
            System.out.println("Failed to update Attendence record.");
        }
    }
}
