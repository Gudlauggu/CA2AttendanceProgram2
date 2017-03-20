/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

import java.util.ArrayList;

/**
 *
 * @author Mecaa
 */
public class Teacher extends Person
  {

    private ArrayList<Course> courses = new ArrayList();

    public Teacher(String username, String email, int id, String password, String name)
      {
        super(username, email, id, password, name);
      }

    public ArrayList<Course> getCourses()
      {
        return courses;
      }

    public void setCourses(ArrayList<Course> courses)
      {
        this.courses = courses;
      }

  }
