/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.DAL.LessonHandler;
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
  }
