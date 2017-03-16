/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Model;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BLL.LessonManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mecaa
 */
public class LessonModel
  {
    private LessonManager lessonManager = new LessonManager();
    
    public ObservableList<Course> LessonGetter(int teacherid){
     ObservableList<Course> lessons = FXCollections.observableArrayList();
     lessons.addAll(lessonManager.teacherLessons(teacherid));
     return lessons;
    }
  }
