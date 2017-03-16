/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
    java.util.Date d;

    public LessonHandler()
      {
        this.conManager = new SQLConnectionManager();
      }

    public void LessonCreator()
      {
        try (Connection con = conManager.getConnection())
          {
//
//            cal = Calendar.getInstance();
//            d = new java.util.Date();
//            cal.setTimeInMillis(d.getTime());
//            String query = "INSERT INTO [Lesson](datetime, students) VALUES (?, ?)";
//            PreparedStatement stmt = con.prepareStatement(query);
//            cal.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
//            stmt.setTimestamp(1, new java.sql.Timestamp(cal.getTimeInMillis()));
//            stmt.setString(2, "Name");
//            stmt.execute();
          } catch (SQLException ex)
          {
            Logger.getLogger(LessonHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

    public ArrayList<Course> getLessons(int teacherid)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Course] WHERE teacherid=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, teacherid);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Course> courses = new ArrayList();
            while(rs.next()){
                courses.add(new Course(rs.getString("name"), rs.getInt("id")));
            }
              for (Course course : courses)
                {
                  String query2 = "SELECT * FROM [Lesson] WHERE id = ?";
                  PreparedStatement stmt2 = con.prepareStatement(query2);
                  stmt.setInt(1, course.getId());
                  ResultSet rs2 = stmt.executeQuery();
                  while(rs2.next()){
                      Timestamp timestamp = rs.getTimestamp("datetime");
                      Calendar calendar = Calendar.getInstance();
                      calendar.setTimeInMillis(timestamp.getTime());
                  course.getLessons().add(new Lesson(rs.getInt("lessonid"), rs.getInt("courseid"), calendar));
                  }
                }
            
           
            return courses;
          } catch (SQLException ex)
          {
            Logger.getLogger(LessonHandler.class.getName()).log(Level.SEVERE, null, ex);

            return null;
          }
      }
  }
