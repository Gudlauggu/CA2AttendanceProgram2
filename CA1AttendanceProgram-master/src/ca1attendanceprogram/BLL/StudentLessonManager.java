/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.StudentLesson;
import ca1attendanceprogram.DAL.StudentLessonHandler;
import java.util.ArrayList;

/**
 *
 * @author Peder
 */
public class StudentLessonManager
{

    private final StudentLessonHandler sLHandler = new StudentLessonHandler();

    public ArrayList<StudentLesson> getStudentLessonBasedOnStudent(Student student)
      {
        return sLHandler.getStudentLessonFromStudent(student);
      }

    public ArrayList<StudentLesson> getStudentLessonBasedOnCourse(Course course)
      {
        return sLHandler.getStudentLessonBasedOnCourse(course);
      }

    public void setStudentAttendence(StudentLesson studLess, int attendid)
      {
        sLHandler.setStudentAttendence(studLess, attendid);
      }
    public StudentLesson getOneStudentLessonFromLessonAndStudent(Student student, Lesson lesson)
      {
        return sLHandler.getOneStudentLessonFromLessonAndStudent(student, lesson);
      }
      
}
