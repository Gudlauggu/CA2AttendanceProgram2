/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Model;

import ca1attendanceprogram.BE.Person;
import ca1attendanceprogram.BLL.LoginManager;

/**
 *
 * @author gudla
 */
public class LoginModel
{
     private LoginManager loginManager = new LoginManager(); 
     
     public Person LoginChecker(String username, String password)
       {
         return loginManager.LoginChecker(username, password);
       }
}
