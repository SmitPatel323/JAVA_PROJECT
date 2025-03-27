package DS;

import DBMS.*;
import JAVAMAIN.*;

import java.sql.*;


public class FacultyLL {
    
    class FacultyNode {
        Faculty faculty;
        FacultyNode next;
    
        public FacultyNode(Faculty faculty) {
            this.faculty = faculty;
            this.next = null;
        }
    }
    public FacultyNode head;

    public FacultyLL() {
        this.head = null;
    }

   
    public void addFaculty(Faculty faculty) {
        if(!searchFaculty(faculty.getFacultyId()))
        {
            FacultyNode newNode = new FacultyNode(faculty);
            if (head == null) {
                head = newNode;
            } else {
                FacultyNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            
        }
        else
        {
            System.out.println("Faculty already exists in the list");
        }
    }

  
    public void deleteStudentLL(int facultyId) 
    {
        
        if(head==null)
        {
            System.out.println("List is empty");
        }
        else
        {
            int flag=0;
            FacultyNode temp1=head;
            while(temp1!=null)
            {
                if(temp1.faculty.getFacultyId()==facultyId)
                {
                    flag=1;
                    break;
                }
                temp1=temp1.next;
            }
            if(flag==1)
            {
                if(head.faculty.getFacultyId()==facultyId)
                {
                    head=head.next;
                }
                else
                {
                    FacultyNode temp=head;
                    while(temp.next.faculty.getFacultyId()!=facultyId)
                    {
                        temp=temp.next;
                    }
                    temp.next=temp.next.next;
                }
                System.out.println(facultyId + " is deleted");
                
            }
            else
            {
                System.out.println("faculty id no not found");
            }

        }
        
    }

    
    
    public boolean searchFaculty(int facultyId)
    {
        if(head==null)
        {
            return false;
        }
        else
        {
            int flag=0;
            FacultyNode temp1=head;
            while(temp1!=null)
            {
                if(temp1.faculty.getFacultyId()==facultyId)
                {
                    flag=1;
                    break;
                }
                temp1=temp1.next;
            }
            if(flag==1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public Faculty searchFacultyById(int facultyId) 
    {
        FacultyNode current = head;
        while (current != null) 
        {
            if (current.faculty.getFacultyId() == facultyId) 
            {
                return current.faculty;
            }
            current = current.next;
        }
        System.out.println("Faculty with ID " + facultyId + " not found in the linked list.");
        return null;
    }
    
}

