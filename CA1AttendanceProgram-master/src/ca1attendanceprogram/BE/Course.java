/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BE;

import java.util.ArrayList;

/**
 *
 * @author Peder
 */
public class Course
{

    private String name;
    private int id;
    ArrayList<Lesson> lessons = new ArrayList();

    public Course(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLessons(ArrayList<Lesson> lessons)
      {
        this.lessons = lessons;
      }

    public ArrayList<Lesson> getLessons()
      {
        return lessons;
      }
    

}
