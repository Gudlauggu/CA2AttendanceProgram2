/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Model;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.StudentLesson;
import ca1attendanceprogram.BLL.StudentLessonManager;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Peder
 */
public class StudentLessonModel
  {

    private final StudentLessonManager studentLessonManager = new StudentLessonManager();

    public StudentLesson getOneStudentLessonFromLessonAndStudent(Student student, Lesson lesson)
      {
        return studentLessonManager.getOneStudentLessonFromLessonAndStudent(student, lesson);
      }

    public ArrayList<StudentLesson> getStudentLessonBasedOnStudent(Student student)
      {
        return studentLessonManager.getStudentLessonBasedOnStudent(student);
      }

    public ArrayList<StudentLesson> getStudentLessonBasedOnCourse(Course course)
      {
        return studentLessonManager.getStudentLessonBasedOnCourse(course);
      }

    public void setStudentAttendence(StudentLesson studLess, int attendid)
      {
        studentLessonManager.setStudentAttendence(studLess, attendid);
      }

    public String getAllAbsenceAsPercentage(Student student)
      {
        ArrayList<StudentLesson> studLessons = getStudentLessonBasedOnStudent(student);
        double absentNumber = 0;
        for (StudentLesson studentLesson : studLessons)
          {
            if (studentLesson.getAttendint() != 1)
              {
                absentNumber += 1;
              }
          }

        double percent;
        percent = (absentNumber / studLessons.size()) * 100;
        percent = (double) Math.round(percent * 10d) / 10d;
        return percent + "%";
      }

    public String getAbsenceForCurrentCourse(Student student, Lesson lesson)
      {
        ArrayList<Lesson> lessons = new LessonModel().getAllCourseLessonsFromLessonAndStudent(student, lesson);
        ArrayList<StudentLesson> studLessons = getStudentLessonBasedOnStudent(student);
        double absentNumber = 0;
        double lessNumber = 0;
        for (StudentLesson studentLesson : studLessons)
          {
            for (Lesson les : lessons)
              {
                if (studentLesson.getLesson().getLessonId() == les.getLessonId())
                  {
                    if (studentLesson.getAttendint() != 1)
                      {
                        absentNumber += 1;
                      }
                    lessNumber += 1;
                  }
              }

          }

        double percent;
        percent = (absentNumber / lessNumber) * 100;
        percent = (double) Math.round(percent * 10d) / 10d;
        return percent + "%";
      }

    public ArrayList<StudentLesson> SortByDate(LocalDate date, Course course)
      {
        ArrayList<StudentLesson> studReturn = new ArrayList();
        ArrayList<StudentLesson> studLess = getStudentLessonBasedOnCourse(course);
        for (StudentLesson studLes : studLess)
          {
            if (studLes.getDateForSort().equals(date))
              {
                studReturn.add(studLes);
              }
          }
        return studReturn;
      }
  }
