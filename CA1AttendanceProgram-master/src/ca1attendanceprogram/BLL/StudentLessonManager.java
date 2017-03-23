/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;

import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.DAL.StudentLessonHandler;
import java.util.ArrayList;

/**
 *
 * @author Peder
 */
public class StudentLessonManager
{

    private final StudentLessonHandler sLHandler = new StudentLessonHandler();

    public ArrayList<String> getAllStudentLesssons()
    {
        return sLHandler.getAllStudentLessons();
    }

    public ArrayList<String> getLessonId()
    {
        return sLHandler.getLessonId();
    }

    ArrayList<String> getStudentId()
    {
        return sLHandler.getStudentId();
    }

    ArrayList<String> getAttending()
    {
        return sLHandler.getAttending();
    }
}
