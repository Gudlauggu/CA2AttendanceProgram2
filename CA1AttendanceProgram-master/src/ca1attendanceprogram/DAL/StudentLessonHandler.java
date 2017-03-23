/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Peder
 */
public class StudentLessonHandler
{

    SQLConnectionManager conManager;

    public StudentLessonHandler()
    {
        conManager = new SQLConnectionManager();
    }

    public ArrayList<String> getAllStudentLessons(Student student)
    {
        try (Connection con = conManager.getConnection())
        {
            String query = "SELECT * FROM [Studentlesson]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> studentLessons = new ArrayList<>();
            while (rs.next())
            {
                String studentLessonString = "";
                studentLessonString += rs.getString("lessonid") + " ";
                studentLessonString += rs.getString("studentid") + " ";
                studentLessonString += rs.getString("attending") + " ";

                studentLessons.add(studentLessonString);
            }
            return studentLessons;
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }
    }

    public ArrayList<String> getLessonId()
    {
        try (Connection con = conManager.getConnection())
        {
            String query = "SELECT * FROM [Studentlesson]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> studentLessons = new ArrayList<>();
            while (rs.next())
            {
                String studentLessonString = "";
                studentLessonString += rs.getString("lessonid") + " ";

                studentLessons.add(studentLessonString);
            }
            return studentLessons;
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }

    }

    public ArrayList<String> getStudentId()
    {
        try (Connection con = conManager.getConnection())
        {
            String query = "SELECT * FROM [Studentlesson]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> studentLessons = new ArrayList<>();
            while (rs.next())
            {
                String studentLessonString = "";
                studentLessonString += rs.getString("studentid") + " ";

                studentLessons.add(studentLessonString);
            }
            return studentLessons;
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }

    }

    public ArrayList<String> getAttending()
    {
        try (Connection con = conManager.getConnection())
        {
            String query = "SELECT * FROM [Studentlesson]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> studentLessons = new ArrayList<>();
            while (rs.next())
            {
                String studentLessonString = "";
                studentLessonString += rs.getString("attending") + " ";

                studentLessons.add(studentLessonString);
            }
            return studentLessons;
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }

    }
}
