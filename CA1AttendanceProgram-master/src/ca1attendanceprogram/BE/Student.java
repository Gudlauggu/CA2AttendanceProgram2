/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Mecaa
 */
public class Student extends Person
  {

    private String absencePercentage;
    private String attendingTest;
    private int aClass;
    private BufferedImage studentImage;

    public Student(String username, String email, int id, String password, String name, int aClass, BufferedImage img)
      {
        super(username, email, id, password, name);
        this.aClass = aClass;
        studentImage = img;
      }

    public String getAttendingTest()
      {
        return attendingTest;
      }

    public void setAttendingTest(String attendingTest)
      {
        this.attendingTest = attendingTest;
      }

    public int getaClass()
      {
        return aClass;
      }

    public void setaClass(int aClass)
      {
        this.aClass = aClass;
      }

    public BufferedImage getStudentImage()
      {
        return studentImage;
      }

    public void setStudentImage(BufferedImage studentImage)
      {
        this.studentImage = studentImage;
      }

    public String getAbsencePercentage()
      {
        return absencePercentage;
      }

    public void setAbsencePercentage(String absencePercentage)
      {
        this.absencePercentage = absencePercentage;
      }

    public void setAbsencePercentage(ArrayList<String> studentLesson)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

  }
