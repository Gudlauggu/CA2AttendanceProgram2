/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;

import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.DAL.TeacherHandler;
import ca1attendanceprogram.DAL.TeacherLoginHandler;

/**
 *
 * @author gudla
 */
public class TeacherManager
{
    private static TeacherHandler teachHandler = new TeacherHandler();
    private static TeacherLoginHandler teachLogHandler = new TeacherLoginHandler();

    public void changePassword(String password, String username)
     {
       teachHandler.changePassword(password, username);
     }
   
   public Teacher LoginChecker(String username, String password)
     {
       return teachLogHandler.LoginChecker(username, password);
     }
    
}
