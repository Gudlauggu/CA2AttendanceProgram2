/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

import java.util.Random;
import java.util.logging.Logger;

/**
 *
 * @author Mecaa
 */
public class Student extends Person {

    private String absencePercentage;
    private String attendingTest;
    private int aClass;

    public Student(String username, String email, int id, String password, String name, int aClass) {
        super(username, email, id, password, name);
        this.aClass=aClass;
        
    }

    public String getAttendingTest() {
       return attendingTest;
    }

    public void setAttendingTest(String attendingTest) {
        this.attendingTest = attendingTest;
    }

    public String getAbsencePercentage() {
        return absencePercentage;
    }

    public void setAbsencePercentage(String absencePercentage) {
        this.absencePercentage = absencePercentage;
    }
    

}
