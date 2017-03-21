/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Model;

import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.BLL.TeacherManager;

/**
 *
 * @author gudla
 */
public class TeacherModel
{
    private TeacherManager teacherManager = new TeacherManager();  
    
    
    public void changePassword(String password, String username)
     {
       teacherManager.changePassword(password, username);
     }
   
   public Teacher LoginChecker(String username, String password)
     {
       return teacherManager.LoginChecker(username, password);
     }
}
