/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Course;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

            cal = Calendar.getInstance();
            d = new java.util.Date();
            cal.setTimeInMillis(d.getTime());
            String query = "INSERT INTO [Lessons](datetime, students) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            cal.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
            stmt.setDate(1, new java.sql.Date(cal.getTimeInMillis()));
            stmt.setString(2, "Name");
            stmt.execute();
            System.out.println(query);
            System.out.println(cal);
        }

        catch (SQLException ex)
        {
            Logger.getLogger(LessonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
