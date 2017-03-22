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
    private Student student;
    private Lesson lesson;
    private String attendence;
    private String studentName;
    private String lessonName;
    
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

    public String getAttendence()
      {
        return attendence;
      }

    public void setAttendence(int attendingInt)
      {
        switch(attendingInt){
            case 0:
                attendence = ABSENT;
                break;
            case 1:
                attendence = ATTENDING;
                break;
            case 2:
                attendence = MERCY_REQUESTED;
                break;
        
        }
        
      }

    public String getStudentName()
      {
        studentName = student.getName();
        return studentName;
      }

    public void setStudentName()
      {
        studentName = student.getName();
      }

    public String getLessonName()
      {
        lessonName = lesson.getLessonName();
        return lessonName;
      }

    public void setLessonName()
      {
        
        lessonName = lesson.getLessonName();
      }
    
    
    
   
  }
