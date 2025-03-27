package JAVAMAIN ;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class CheckInputs {
    Scanner sc= new Scanner(System.in);
    String loanType = "";

    public int checkInput() throws Exception
    {
        int input = 0;
        boolean check = true;
        while(check)
        {
            try {
                System.out.print("Enter Your choice : ");
                input = sc.nextInt();
                check = false;
            } catch (Exception e) {
                System.out.println("Invalid input ! Please Enter again data");
                sc.nextLine();
            }
        }
        return input;
    }
    public String validateMobileNumber() throws Exception {
        String mobileNumber;

        while (true) {
            System.out.print("Enter your mobile number: ");
            mobileNumber = sc.next();

            if (mobileNumber.length() != 10) {
                System.out.println("Invalid mobile number: Length is not 10 digits");
                continue;  
            }

            boolean isValid = true;
            for (char c : mobileNumber.toCharArray()) {
                if (!Character.isDigit(c)) {
                    System.out.println("Invalid mobile number: Contains non-digit characters");
                    isValid = false;
                }
            }

            if (isValid) {
                System.out.println("Valid mobile number");
                return mobileNumber;
            }
        }

    }

    public String validateAadharNumber() throws Exception {
        String mobileNumber;

        while (true) {
            System.out.print("Enter your Aadhar number: ");
            mobileNumber = sc.next();

            if (mobileNumber.length() != 12) {
                System.out.println("Invalid Aadhar number: Length is not 12 digits");
                continue;  
            }

            boolean isValid = true;
            for (char c : mobileNumber.toCharArray()) {
                if (!Character.isDigit(c)) {
                    System.out.println("Invalid Aadhar number: Contains non-digit characters");
                    isValid = false;
                }
            }

            if (isValid) {
                System.out.println("Valid Aadhar number");
                return mobileNumber;
            }
        }

    }
    public String checkDate() {
        String date ="";
        boolean isValid = true;
        while(isValid)
        {
            System.out.println();
            System.out.print("Enter Date of Birth(YYYY-MM-DD) :");
            date = sc.next();
            if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Invalid date: Incorrect format. Please use yyyy-mm-dd.");
                continue;
            }
    
            String[] parts = date.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
    
            if (month < 1 || month > 12) {
                System.out.println("Invalid date: Month must be between 01 and 12.");
                continue;

            }
    
            if (day < 1 || day > 31) {
                System.out.println("Invalid date: Day must be between 01 and 31.");
                continue;

            }
    
            if (month == 2) {
                boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                if (day > 29 || (day == 29 && !isLeapYear)) {
                    
                    System.out.println("Invalid date: February does not have " + day + " days in the year " + year + ".");
                    continue;

                }
            }
    
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                System.out.println("Invalid date: The month " + month + " does not have " + day + " days.");
                continue;
            }

            LocalDate birthDate = LocalDate.of(year, month, day);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate, currentDate).getYears();

            if (age < 16) {
                System.out.println("Invalid date: You must be at least 16 years old. You are only " + age + " years old.");
                continue; 
            }
            break;
        }
        return date ;
    
    }
    
    public String checkDateOnly() {
        String date ="";
        boolean isValid = true;
        while(isValid)
        {
            System.out.println();
            System.out.print("Enter Date(YYYY-MM-DD) :");
            date = sc.next();
            if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Invalid date: Incorrect format. Please use yyyy-mm-dd.");
                continue;
            }
    
            String[] parts = date.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
    
            if (month < 1 || month > 12) {
                System.out.println("Invalid date: Month must be between 01 and 12.");
                continue;

            }
    
            if (day < 1 || day > 31) {
                System.out.println("Invalid date: Day must be between 01 and 31.");
                continue;

            }
    
            if (month == 2) {
                boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                if (day > 29 || (day == 29 && !isLeapYear)) {
                    
                    System.out.println("Invalid date: February does not have " + day + " days in the year " + year + ".");
                    continue;

                }
            }
    
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                System.out.println("Invalid date: The month " + month + " does not have " + day + " days.");
                continue;
            }
            break;
        }
        return date ;
    
    }

    String course ="";
    public String courseName() throws Exception
    {
        System.out.println();
        System.out.println("Press 1 for CE");
        System.out.println("Press 2 for Mechanical");
        System.out.println("Press 3 for BCA");
        System.out.println("Press 4 for B.Com");
        System.out.println("Press 5 for MBA");
        int choice = checkInput();
        switch (choice) {
            case 1:
                course = "CE";
                break;
            case 2:
                course = "Mechanical";
                break;
            case 3:
                course = "BCA";
                break;
            case 4:
                course = "B.Com";
                break;
            case 5:
                course = "MBA";
                break;
        
            default:System.out.println("Invalid choice");
                break;
        }
        return course;
    }

    public int courseId() throws Exception
    {
        int courseId =0;
        if(course.equalsIgnoreCase("CE"))
        {
            courseId = 16151;
        }
        else if(course.equalsIgnoreCase("Mechanical"))
        {
            courseId = 16152;
        }
        else if(course.equalsIgnoreCase("BCA"))
        {
            courseId = 15121;
        }
        if(course.equalsIgnoreCase("B.Com"))
        {
            courseId = 15131;
        }
        if(course.equalsIgnoreCase("CE"))
        {
            courseId = 14141;
        }
        return courseId;
    }
}
