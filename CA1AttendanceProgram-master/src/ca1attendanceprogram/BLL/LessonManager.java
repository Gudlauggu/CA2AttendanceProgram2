/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.DAL.LessonHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mecaa
 */
public class LessonManager
  {

    private LessonHandler lHandler = new LessonHandler();

    public List<Course> teacherLessons(Teacher teacher)
      {
        return lHandler.getLessonsFromTeacher(teacher);
      }
    public boolean createLesson(Course course){
        return lHandler.createNewLesson(course);
    }
     public Lesson getNewestLesson(){
         return lHandler.getNewestLesson();
     }
     public ArrayList<Lesson> getAllCourseLessonsFromLessonAndStudent(Student student, Lesson lesson)
      {
        return lHandler.getAllCourseLessonsFromLessonAndStudent(student, lesson);
      }
  }
