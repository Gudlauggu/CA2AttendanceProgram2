/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;

import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.DAL.StudentHandler;
import ca1attendanceprogram.DAL.StudentLoginHandler;
import java.util.ArrayList;

/**
 *
 * @author gudla
 */
public class StudentManager
{
    private static StudentHandler studHandler = new StudentHandler();
    private static StudentLoginHandler studLogHandler = new StudentLoginHandler();
    

    public ArrayList<String> getAllStudents() {
        return studHandler.getAllStudents();
    }
    
    public ArrayList<String> getStudUsernames() {
        return studHandler.getStudUsername();
    }
    
    public ArrayList<String> getStudEmails() {
        return studHandler.getStudEmail();
    }
    public ArrayList<String> getStudIds() {
        return studHandler.getStudId();
            }
    
    public ArrayList<String> getStudPasswords() {
        return studHandler.getStudPassword();
    }
    
    public ArrayList<String> getStudNames() {
        return studHandler.getStudName();
    }

   public void changePassword(String password, String username)
     {
       studHandler.changePassword(password, username);
     }
   
   public Student LoginChecker(String username, String password)
     {
       return studLogHandler.LoginChecker(username, password);
     }
   
   public Student getStudentBasedOnUsername(String username)
     {
       return studHandler.getStudentBasedOnUsername(username);
     }
    
    
    
}