/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.GUI.Model;

import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.StudentLesson;
import ca1attendanceprogram.BLL.StudentLessonManager;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Peder
 */
public class StudentLessonModel
{

    private final StudentLessonManager studenLessonManager = new StudentLessonManager();

    public ObservableList<String> StudentLessonIntoObservable(Student student)
    {
        ObservableList<String> studentLessons = FXCollections.observableArrayList();
        studentLessons.addAll(student.getAbsencePercentage());
        return studentLessons;
    }

    public void setStudentLessons(Student student)
    {
        ArrayList<String> studentLesson = new ArrayList();
        studentLesson.addAll(studenLessonManager.getAllStudentLesssons(student));
        student.setAbsencePercentage(studentLesson);
    }
}
