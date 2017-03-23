/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.StudentLesson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peder
 */
public class StudentLessonHandler
  {

    LessonHandler lessonHandler;
    StudentHandler studHandler;
    SQLConnectionManager conManager;

    public StudentLessonHandler()
      {
        studHandler = new StudentHandler();
        lessonHandler = new LessonHandler();
        conManager = new SQLConnectionManager();
      }

    public ArrayList<String> getAllStudentLessons()
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

    public ArrayList<StudentLesson> getStudentLessonBasedOnCourse(int courseid)
      {
        ArrayList<StudentLesson> studentlessons = new ArrayList();
        try (Connection con = conManager.getConnection())
          {

            String query = "SELECT * FROM [lesson] WHERE courseID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, courseid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
              {
                query = "SELECT * FROM [studentlesson] WHERE lessonid = ?";
                pstmt = con.prepareStatement(query);
                int lessid = rs.getInt("lessonid");
                pstmt.setInt(1, lessid);
                ResultSet rsTwo = pstmt.executeQuery();
                Lesson lesson = lessonHandler.getOneLessonFromID(lessid);
                while (rsTwo.next())
                  {
                    Student student = studHandler.getStudentBasedOnId(rsTwo.getInt("studentid"));
                    StudentLesson studLess = new StudentLesson(student, lesson, rsTwo.getInt("attending"));
                    studentlessons.add(studLess);
                  }

              }

          }
        catch (SQLException ex)
          {
            Logger.getLogger(StudentLessonHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
        return studentlessons;
      }

  }
