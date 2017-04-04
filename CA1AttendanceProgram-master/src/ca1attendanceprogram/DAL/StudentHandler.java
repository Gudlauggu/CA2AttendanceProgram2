/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Student;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author gudla
 */
public class StudentHandler
  {

    SQLConnectionHandler conManager;

    public StudentHandler()
      {
        conManager = new SQLConnectionHandler();
      }

    public ArrayList<String> getAllStudents()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> students = new ArrayList<>();
            while (rs.next())
              {
                String studentString = "";
                studentString += rs.getString("id") + " ";
                studentString += rs.getString("Name") + " ";
                studentString += rs.getString("username") + " ";
                studentString += rs.getString("email") + " ";
                studentString += rs.getString("password");

                students.add(studentString);
              }
            return students;
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getStudId()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> students = new ArrayList<>();
            while (rs.next())
              {
                String studentString = "";
                studentString += rs.getString("id");

                students.add(studentString);
              }
            return students;
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getStudName()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> students = new ArrayList<>();
            while (rs.next())
              {
                String studentString = "";
                studentString += rs.getString("Name");

                students.add(studentString);
              }
            return students;
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getStudUsername()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> studUsername = new ArrayList<>();
            while (rs.next())
              {
                studUsername.add(rs.getString("username"));
              }
            return studUsername;
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getStudEmail()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> students = new ArrayList<>();
            while (rs.next())
              {
                String studentString = "";

                studentString += rs.getString("email");

                students.add(studentString);
              }
            return students;
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getStudPassword()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> students = new ArrayList<>();
            while (rs.next())
              {
                String studentString = "";
                studentString += rs.getString("password");

                students.add(studentString);
              }
            return students;
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public boolean checkRightPassword(String username, String password)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student] WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            return password.equals(rs.getString("password"));
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return false;
          }
      }

    public void changePassword(String password, String username)
      {
        try (Connection con = conManager.getConnection())
          {
            String sqlQuery = "UPDATE Student SET password=? WHERE username=?";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);

            pstmt.setString(1, password);
            pstmt.setString(2, username);
            pstmt.execute();

          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
          }
      }

    public Student getStudentBasedOnUsername(String username)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student] WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);

            return getStudentFromResults(pstmt);
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public Student getStudentBasedOnId(int id)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student] WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            return getStudentFromResults(pstmt);
          }
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public Student getStudentFromResults(PreparedStatement pstmt) throws SQLException
      {
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int idStud = rs.getInt("id");
        String nameStud = rs.getString("name");
        String usernameStud = rs.getString("username");
        String emailStud = rs.getString("email");
        String passwordStud = rs.getString("password");
        int classId = rs.getInt("classid");
        byte[] bytes = rs.getBytes("image");
        BufferedImage newImage;
        if (bytes != null)
          {
            try
              {
                ByteArrayInputStream bais;
                bais = new ByteArrayInputStream(rs.getBytes("image"));

                newImage = ImageIO.read(bais);
              }
            catch (IOException ex)
              {
                Logger.getLogger(StudentHandler.class.getName()).log(Level.SEVERE, null, ex);
                newImage = null;
              }
          }
        else
          {
            newImage = null;
          }
        Student student = new Student(usernameStud, emailStud, idStud, passwordStud, nameStud, classId, newImage);
        return student;
      }

    public ArrayList<Integer> getAllStudentIdBasedOnClass(int classid, Connection con) throws SQLException
      {
        String query = "SELECT * FROM [student] WHERE classid = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, classid);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<Integer> studentsId = new ArrayList();
        while (rs.next())
          {
            studentsId.add(rs.getInt("id"));
          }
        return studentsId;
      }

  }
