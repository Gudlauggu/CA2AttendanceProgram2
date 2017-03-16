/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;

import ca1attendanceprogram.BE.Person;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.DAL.StudentLoginHandler;
import ca1attendanceprogram.DAL.TeacherLoginHandler;

/**
 *
 * @author gudla
 */
public class LoginManager
{

    private StudentLoginHandler studentLoginHandler = new StudentLoginHandler();
    private TeacherLoginHandler teacherLoginHandler = new TeacherLoginHandler();

    public Person LoginChecker(String username, String password)
      {

        if (studentLoginHandler.LoginChecker(username, password) != null)
          {
            return studentLoginHandler.LoginChecker(username, password);
          } else
          {
            return teacherLoginHandler.LoginChecker(username, password);
          }
      }

}
