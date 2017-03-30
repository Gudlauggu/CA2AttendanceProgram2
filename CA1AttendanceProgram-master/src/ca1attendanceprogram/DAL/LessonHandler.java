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
import java.text.SimpleDateFormat;
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

    SQLConnectionHandler conManager;
    Calendar cal;

    StudentHandler sh = new StudentHandler();

    public LessonHandler()
      {
        this.conManager = new SQLConnectionHandler();
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

    public Lesson getNewestLesson()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT top 1 * FROM [lesson]  ORDER BY lessonid DESC ";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Timestamp timestamp = rs.getTimestamp("datetime");
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp.getTime());
            Lesson lesson = new Lesson(rs.getInt("lessonid"), rs.getInt("courseid"), cal);
            query = "SELECT * FROM [course] WHERE id = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, lesson.getCourseId());
            rs = pstmt.executeQuery();
            rs.next();
            lesson.setLessonName(rs.getString("name"));
            query = "SELECT * FROM [teacher] WHERE id = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, rs.getInt("teacherid"));
            rs = pstmt.executeQuery();
            rs.next();
            lesson.setTeacherName(rs.getString("name"));
            return lesson;
          }
        catch (SQLException ex)
          {
            Logger.getLogger(LessonHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
      }

    public Lesson getOneLessonFromID(int lessonId)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Lesson] WHERE lessonid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, lessonId);
            Lesson lesson = getLessonFromResult(pstmt);
            lesson.setLessonName(getCourseNameFromLesson(lesson, con));
            return lesson;
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    private Lesson getLessonFromResult(PreparedStatement pstmt) throws SQLException
      {
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int lessonid = rs.getInt("lessonid");
        int courseid = rs.getInt("courseid");
        Timestamp timestamp = rs.getTimestamp("datetime");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        Lesson lesson = new Lesson(lessonid, courseid, calendar);
        return lesson;
      }

    private String getCourseNameFromLesson(Lesson lesson, Connection con) throws SQLException
      {
        String query = "SELECT * FROM [course] where id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, lesson.getCourseId());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getString("name");

      }

    public ArrayList<Lesson> getAllCourseLessonsFromLessonAndStudent(Student student, Lesson lesson)
      {
        ArrayList<Lesson> lessons = new ArrayList();
        try (Connection con = conManager.getConnection())
          {
              String query = "SELECT * FROM [lesson] WHERE courseid = ?";
              PreparedStatement pstmt = con.prepareStatement(query);
              pstmt.setInt(1, lesson.getCourseId());
              ResultSet rs = pstmt.executeQuery();
              while(rs.next()){
              lessons.add(getOneLessonFromID(rs.getInt("lessonid")));
              }
          }
        catch (SQLException ex)
          {
            Logger.getLogger(LessonHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
        return lessons;
      }
  }
