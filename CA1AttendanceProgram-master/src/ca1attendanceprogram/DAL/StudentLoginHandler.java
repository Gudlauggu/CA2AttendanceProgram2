/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Student;
import java.util.ArrayList;

/**
 *
 * @author gudla
 */
public class StudentLoginHandler
  {

    StudentHandler sh = new StudentHandler();

    private ArrayList<Student> student = new ArrayList();

    public StudentLoginHandler()
      {
      }

    public Student LoginChecker(String username, String password)
      {
        for (String string : sh.getStudUsername())
          {
            System.out.println(string);
            if (string.equals(username))
              {
                if (sh.checkRightPassword(string, password))
                  {
                    return sh.getStudentBasedOnUsername(string);
                  }
              }
          }

        return null;
      }
  }
