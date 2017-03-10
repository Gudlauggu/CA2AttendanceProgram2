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
          for (ArrayList<String> string : sh.getStudUsername())
            {
              
            if (string.get(0).equals(username))
              {
                if (sh.checkRightPassword(Integer.parseInt(string.get(1)), password))
                  {
                    ArrayList<String> oneStudent = sh.getStudent(string.get(1));
                    int id = Integer.parseInt(oneStudent.get(0));
                    String name = oneStudent.get(1);
                    String studentUsername = oneStudent.get(2);
                    String Email = oneStudent.get(3);
                    String studentPassword = oneStudent.get(4);
                    
                            //UserName, Email, id, password, name
                    Student thisStudent = new Student(studentUsername,Email, id, studentPassword, name);
                    return thisStudent;
                    
                  }
              }
            }
          
        return null;
      }
}
