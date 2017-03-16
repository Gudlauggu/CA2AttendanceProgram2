/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Teacher;
import java.util.ArrayList;

/**
 *
 * @author gudla
 */
public class TeacherLoginHandler
{
    TeacherHandler sh = new TeacherHandler();
    
    private ArrayList<Teacher> student = new ArrayList();

    public TeacherLoginHandler()
      {
      }
    
    

    public Teacher LoginChecker(String username, String password)
      {
          for (String string : sh.getTeachUsername())
            {
                System.out.println(string);
            if (string.equals(username))
              {
                if (sh.checkRightPassword(string, password))
                  {
                    ArrayList<String> oneTeacher = sh.getTeacher(string);
                    int id = Integer.parseInt(oneTeacher.get(0  ));
                    String name = oneTeacher.get(1);
                    String teachUsername = oneTeacher.get(2);
                    String Email = oneTeacher.get(3);
                    String teachPassword = oneTeacher.get(4);
                    
                            //String username, String email, int id, String password, String name
                    Teacher thisTeacher = new Teacher(teachUsername,Email, id, teachPassword, name);
                    return thisTeacher;
                    
                  }
              }
            }
          
        return null;
      }
}
