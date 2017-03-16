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
                    ArrayList<String> oneStudent = sh.getStudent(string);
                    int id = Integer.parseInt(oneStudent.get(0));
                    String name = oneStudent.get(1);
                    String studentUsername = oneStudent.get(2);
                    String Email = oneStudent.get(3);
                    String studentPassword = oneStudent.get(4);
                    int aClass = Integer.parseInt(oneStudent.get(5));
                    
                            //UserName, Email, id, password, name
                    Student thisStudent = new Student(studentUsername,Email, id, studentPassword, name,aClass);
                    return thisStudent;
                    
                  }
              }
            }
          
        return null;
      }
}
