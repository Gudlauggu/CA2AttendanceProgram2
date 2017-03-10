/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;


import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.DAL.StudentLoginHandler;

/**
 *
 * @author gudla
 */
public class LoginManager
{
    private StudentLoginHandler studentLoginHandler = new StudentLoginHandler();

    public Student LoginChecker(String username, String password) {
        return studentLoginHandler.LoginChecker(username, password);    
    }
}
