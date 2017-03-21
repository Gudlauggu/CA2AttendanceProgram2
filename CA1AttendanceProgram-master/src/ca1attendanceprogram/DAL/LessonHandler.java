/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Student;
import ca1attendanceprogram.BE.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peder
 */
public class LessonHandler
  {

    SQLConnectionManager conManager;
    Calendar cal;

    StudentHandler sh = new StudentHandler();

    public LessonHandler()
      {
        this.conManager = new SQLConnectionManager();
      }

    public List<Course> getLessonsFromTeacher(Teacher teacher)
      {
        try (Connection con = conManager.getConnection())
          {
            List<Course> courses = getCourses(con, teacher.getId());
            for (Course course : courses)
              {
                String query2 = "SELECT * FROM [Lesson] WHERE lessonid = ?";
                PreparedStatement pstmtLessons = con.prepareStatement(query2);
                pstmtLessons.setInt(1, course.getId());
                ResultSet rsLesson = pstmtLessons.executeQuery();
                while (rsLesson.next())
                  {
                    Timestamp timestamp = rsLesson.getTimestamp("datetime");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(timestamp.getTime());
                    Lesson lesson = new Lesson(rsLesson.getInt("lessonid"),
                            rsLesson.getInt("courseid"), calendar);
                    lesson.setStudents((ArrayList) getStudentsInLessons(con, lesson));
                    course.getLessons().add(lesson);

                  }

              }

            return courses;
          }
        catch (SQLException ex)
          {
            Logger.getLogger(LessonHandler.class.getName()).log(Level.SEVERE, null, ex);

            return null;
          }
      }

    private List<Course> getCourses(Connection con, int teacherid) throws SQLException
      {
        String query = "SELECT * FROM [Course] WHERE teacherid=?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, teacherid);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Course> courses = new ArrayList();
        while (rs.next())
          {
            courses.add(new Course(rs.getString("name"), rs.getInt("id")));
          }
        return courses;
      }

    private List<Student> getStudentsInLessons(Connection con, Lesson lesson) throws SQLException
      {
        String query = "SELECT * FROM [StudentLesson] WHERE lessonid=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, lesson.getLessonId());
        ResultSet rs = pstmt.executeQuery();
        ArrayList<Integer> studentsid = new ArrayList();
        while (rs.next())
          {
            studentsid.add(rs.getInt("studentid"));
          }
        ArrayList<Student> students = new ArrayList();
        for (Integer integer : studentsid)
          {
            students.add(sh.getStudentBasedOnId(integer));

          }
        return students;
      }

    public boolean createNewLesson(Course course)
      {
        try (Connection con = conManager.getConnection())
          {
            int classid = getClassIdFromCourse(course, con);
            String query = "INSERT INTO [lesson] (datetime,courseid) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
            Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            pstmt.setTimestamp(1, timestamp);
            pstmt.setInt(2, course.getId());
            pstmt.execute();
            query = "SELECT top 1 * FROM [lesson] WHERE courseID = ?  ORDER BY lessonid DESC ";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, course.getId());
            
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int lessonid = rs.getInt("lessonid");
            for (Integer integer : sh.getAllStudentIdBasedOnClass(classid, con))
              {
                query = "INSERT INTO [studentlesson] (lessonid,studentid,attending)VALUES(?,?,?)";
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1, lessonid);
                pstmt.setInt(2, integer);
                pstmt.setInt(3, 0);
                pstmt.execute();
              }
            ;
          }
        catch (SQLException ex)
          {
            Logger.getLogger(LessonHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
        return false;
      }

    private int getClassIdFromCourse(Course course, Connection con) throws SQLException
      {
        String query = "SELECT * FROM [Course] WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, course.getId());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt("classid");
      }
  }
