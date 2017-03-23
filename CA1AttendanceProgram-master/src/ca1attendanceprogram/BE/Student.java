/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

=======
>>>>>>> origin/master
/**
 *
 * @author Mecaa
 */
public class Student extends Person
  {

    private String absencePercentage;
    private String attendingTest;
    private int aClass;

    public Student(String username, String email, int id, String password, String name, int aClass)
      {
        super(username, email, id, password, name);
        this.aClass = aClass;

      }

    public String getAttendingTest()
      {
        return attendingTest;
      }

    public void setAttendingTest(String attendingTest)
      {
        this.attendingTest = attendingTest;
      }

    public String getAbsencePercentage()
      {
        return absencePercentage;
      }

    public void setAbsencePercentage(String absencePercentage)
      {
        this.absencePercentage = absencePercentage;
<<<<<<< HEAD
    }

    public void setAbsencePercentage(ArrayList<String> studentLesson)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
=======
      }
>>>>>>> origin/master

  }
