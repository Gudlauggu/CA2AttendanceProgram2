/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Course;
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

    public ArrayList<StudentLesson> getAllStudentLessons()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Studentlesson]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<StudentLesson> studentLessons = new ArrayList<>();
            while (rs.next())
              {
                Student student = studHandler.getStudentBasedOnId(rs.getInt("studentid"));
                Lesson lesson = lessonHandler.getOneLessonFromID(rs.getInt("lessonid"));
                StudentLesson studentLesson = new StudentLesson(student, lesson, rs.getInt("attending"));

                studentLessons.add(studentLesson);
              }
            return studentLessons;
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public StudentLesson getOneStudentLessonFromLessonAndStudent(Student student, Lesson lesson)
      {
        try (Connection con = conManager.getConnection())
          {

            String query = "SELECT * FROM [StudentLesson] WHERE studentid = ? AND lessonid = ? ";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, student.getId());
            pstmt.setInt(2, lesson.getLessonId());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return new StudentLesson(student, lesson, rs.getInt("attending"));
          }
        catch (Exception e)
          {
          }
        return null;
      }

    public ArrayList<StudentLesson> getStudentLessonBasedOnCourse(Course course)
      {
        ArrayList<StudentLesson> studentlessons = new ArrayList();
        try (Connection con = conManager.getConnection())
          {

            String query = "SELECT * FROM [lesson] WHERE courseID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, course.getId());
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

    public ArrayList<StudentLesson> getStudentLessonFromStudent(Student student)
      {
        ArrayList<StudentLesson> studentlessons = new ArrayList();
        try (Connection con = conManager.getConnection())
          {

            String query = "SELECT * FROM [studentLesson] WHERE studentid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, student.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
              {

                int lessid = rs.getInt("lessonid");
                Lesson lesson = lessonHandler.getOneLessonFromID(lessid);
                StudentLesson studLess = new StudentLesson(student, lesson, rs.getInt("attending"));
                studentlessons.add(studLess);
              }

          }
        catch (SQLException ex)
          {
            Logger.getLogger(StudentLessonHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
        return studentlessons;
      }

    public void setStudentAttendence(StudentLesson studLess, int attendInt)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "UPDATE [studentlesson] SET attending = ? WHERE lessonid=? AND studentid =?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, attendInt);
            int lessId = studLess.getLesson().getLessonId();
            pstmt.setInt(2, lessId);
            int studId = studLess.getStudent().getId();
            pstmt.setInt(3, studId);
            pstmt.execute();
          }
        catch (SQLException ex)
          {
            Logger.getLogger(StudentLessonHandler.class.getName()).log(Level.SEVERE, null, ex);
          }

      }

  }
