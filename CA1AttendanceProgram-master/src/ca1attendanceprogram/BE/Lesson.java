/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mechaa
 */
public class Lesson
  {

    private ArrayList<Student> students = new ArrayList();
    private Calendar cal;
    private int lessonId;
    private int courseId;

    public Lesson(int lessonId, int courseId, Calendar cal)
      {
        this.lessonId = lessonId;
        this.courseId = courseId;
        this.cal = cal;
      }

    public ArrayList<Student> getStudents()
      {
        return students;
      }

    public void setStudents(ArrayList<Student> students)
      {
        this.students = students;
      }

    public Calendar getCal()
      {
        return cal;
      }

    public void setCal(Calendar cal)
      {
        this.cal = cal;
      }

    public int getLessonId()
      {
        return lessonId;
      }

    public void setLessonId(int lessonId)
      {
        this.lessonId = lessonId;
      }

    public int getCourseId()
      {
        return courseId;
      }

    public void setCourseId(int courseId)
      {
        this.courseId = courseId;
      }
    
  }
