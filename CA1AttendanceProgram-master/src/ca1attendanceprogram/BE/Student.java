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

    public Student(String username, String email, int id, String password, String name) {
        super(username, email, id, password, name);
        Random rand = new Random();
        absencePercentage = rand.nextInt(101) + "";
       if (rand.nextInt(2) == 1) {
            attendingTest = "Absent";
        } else if (rand.nextInt(2) == 0) {
            attendingTest = "Attending";
        } else {
            attendingTest = "Absent(Mercy Requested)";
       }
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
