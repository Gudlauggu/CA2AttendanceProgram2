/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mecaa
 */
public class StudentLesson
  {

    private Student student;
    private Lesson lesson;
    private final static String ABSENT = "Absent";
    private final static String ATTENDING = "Attending";
    private final static String MERCY_REQUESTED = "Absent(Mercy Requested)";
    private int attendint;
    private String teacherName;
    private final StringProperty lessonName = new SimpleStringProperty();
    private final StringProperty attendence = new SimpleStringProperty();
    private final StringProperty studentName = new SimpleStringProperty();
//
//    public static enum attending
//      {
//        ATTENDING, ABSENT, MERCY;
//      }

    public StudentLesson(Student student, Lesson lesson, int attendint)
      {
        this.student = student;
        setStudentName(student.getName());
        this.lesson = lesson;
        setLessonName(lesson.getLessonName());
        teacherName = lesson.getTeacherName();

        setDateString(lesson.getCal().getTime());
        this.attendint = attendint;
        setAttendence(attendint);
      }

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
        return attendence.get();
      }

    public StringProperty attendenceProperty()
      {
        return attendence;
      }

    public void setAttendence(int attendingInt)
      {
        switch (attendingInt)
          {
            case 0:
                attendence.set(ABSENT);
                break;
            case 1:
                attendence.set(ATTENDING);
                break;
            case 2:
                attendence.set(MERCY_REQUESTED);
                break;

          }

      }

    public String getLessonName()
      {
        return lessonName.get();
      }

    public void setLessonName(String value)
      {
        lessonName.set(value);
      }

    public StringProperty lessonNameProperty()
      {
        return lessonName;
      }
    private final StringProperty dateString = new SimpleStringProperty();

    public String getDateString()
      {
        return dateString.get();
      }

    public void setDateString(Date date)
      {

        Format formatter;
        formatter = new SimpleDateFormat("dd-MMM E HH:mm");
        String s = formatter.format(date);
        dateString.set(s);
      }

    public LocalDate getDateForSort()
      {
        LocalDate lDate = lesson.getCal().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return lDate;
      }

    public StringProperty dateStringProperty()
      {
        return dateString;
      }

    public int getAttendint()
      {
        return attendint;
      }

    public void setAttendint(int attendint)
      {
        this.attendint = attendint;
      }

    public String getTeacherName()
      {
        return lesson.getTeacherName();
      }

    public String getStudentName()
      {
        return studentName.get();
      }

    public void setStudentName(String value)
      {
        studentName.set(value);
      }

    public StringProperty studentNameProperty()
      {
        return studentName;
      }

  }
