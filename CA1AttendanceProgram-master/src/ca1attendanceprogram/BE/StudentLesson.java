/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

/**
 *
 * @author Mecaa
 */
public class StudentLesson
  {
    Student student;
    Lesson lesson;
    String Attending;
    private final static String ABSENT= "Absent";
    private final static String ATTENDING = "Attending";
    private final static String MERCY_REQUESTED ="Absent(Mercy Requested)";

    public Student getStudent()
      {
        return student;
      }

    public void setStudent(Student student)
      {
        this.student = student;
      }

    public Lesson getLesson()
      {
        return lesson;
      }

    public void setLesson(Lesson lesson)
      {
        this.lesson = lesson;
      }

    public String getAttending()
      {
        return Attending;
      }

    public void setAttending(String Attending)
      {
        this.Attending = Attending;
      }
    
    
   
  }
