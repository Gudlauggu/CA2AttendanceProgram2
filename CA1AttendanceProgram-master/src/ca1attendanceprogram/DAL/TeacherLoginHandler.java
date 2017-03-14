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
    TeacherHandler th = new TeacherHandler();
    
    private ArrayList<Teacher> teacher = new ArrayList();

    public TeacherLoginHandler()
      {
      }
    
    

    public Teacher LoginChecker(String username, String password)
      {
          for (ArrayList<String> string : th.getTeachUsername())
            {
              
            if (string.get(0).equals(username))
              {
                if (th.checkRightPassword(Integer.parseInt(string.get(1)), password))
                  {
                    ArrayList<String> oneTeacher = th.getTeacher(string.get(1));
                    int id = Integer.parseInt(oneTeacher.get(0));
                    String name = oneTeacher.get(1);
                    String teacherUsername = oneTeacher.get(2);
                    String Email = oneTeacher.get(3);
                    String teacherPassword = oneTeacher.get(4);
                    
                            //UserName, Email, id, password, name
                    Teacher thisTeacher = new Teacher(teacherUsername, Email, id, teacherPassword, name);
                    return thisTeacher;
                    
                  }
              }
            }
          
        return null;
      }
}
