package JAVAMAIN;

public class StudentData {
    public int studentid , enrollmentNo , courseId; 
    public String name , batch , date_Of_Birth , address , phoneNumber , email , gender , course ; 
    ConnectionCollage coc;

    public StudentData() {
        
    }

    public StudentData(int enrollmentNo,String batch, String name, String date_Of_Birth, String address, String phoneNumber, String email, String gender, String couse , int courseId)  {
        this.enrollmentNo = enrollmentNo;
        this.batch = batch;
        this.name = name;
        this.date_Of_Birth = date_Of_Birth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.course = couse;
        this.courseId = courseId;
       
    }
}