/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Model;

import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BLL.StudentManager;

/**
 *
 * @author gudla
 */
public class StudentModel
{
    private StudentManager studentManager = new StudentManager();  
    
    
    public void changePassword(String password, String username)
     {
       studentManager.changePassword(password, username);
     }
   
   public Student LoginChecker(String username, String password)
     {
       return studentManager.LoginChecker(username, password);
     }
   
   public Student getStudentBasedOnUsername(String username)
     {
       return studentManager.getStudentBasedOnUsername(username);
     }
   
   
}
