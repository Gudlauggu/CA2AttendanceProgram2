/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Mechaa
 */
public class Lesson {

    private String teacher;
    private ArrayList<Student> students = new ArrayList();
    private Date date;
    private String name;

    public Lesson(String teacher, String name) {
        Random rand = new Random();
        this.teacher = teacher;

        Calendar cal = Calendar.getInstance();
        int month = rand.nextInt(12);
        int year;
        while (month < 3 && month > 7) {
            month = rand.nextInt(12);
        }
        if (month < 4) {
            year = 2017;
        } else {
            year = 2016;
        }

        cal.set(year, month, rand.nextInt(28) + 1); //Year, month and day of month
        date = cal.getTime();
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Date getDate() {
        return date;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
