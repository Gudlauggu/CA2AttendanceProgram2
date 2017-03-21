/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Model;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Teacher;
import ca1attendanceprogram.BLL.LessonManager;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mecaa
 */
public class LessonModel
  {

    private LessonManager lessonManager = new LessonManager();

    public ObservableList<Course> LessonIntoObservable(Teacher teacher)
      {
        ObservableList<Course> lessons = FXCollections.observableArrayList();
        lessons.addAll(teacher.getCourses());
        return lessons;
      }

    public void setCoursesForTeacher(Teacher teacher)
      {
        ArrayList<Course> courses = new ArrayList();
        courses.addAll(lessonManager.teacherLessons(teacher));
        teacher.setCourses(courses);

      }

    public boolean createLesson(Teacher teacher, String courseName)
      {
          for (Course course : teacher.getCourses())
            {
              if(course.getName().equals(courseName)){
              return lessonManager.createLesson(course);
              }
            }
          return false;
      }
  }
