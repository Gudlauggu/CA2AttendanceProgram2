/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gudla
 */
public class TeacherHandler
  {

    SQLConnectionManager conManager;

    public TeacherHandler()
      {
        conManager = new SQLConnectionManager();
      }

    public ArrayList<String> getAllTeachers()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher]";
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
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getTeachId()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher]";
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
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getTeachName()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher]";
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
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getTeachUsername()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> studUsername = new ArrayList<>();
            while (rs.next())
              {
                studUsername.add(rs.getString("username"));
              }
            return studUsername;
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getTeachEmail()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher]";
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
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<String> getTeachPassword()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher]";
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
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public boolean checkRightPassword(String username, String password)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher] WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            System.out.println(rs.getString("password"));
            return password.equals(rs.getString("password"));
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return false;
          }
      }

    public ArrayList<String> getTeacher(String username)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher] WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> oneTeacher = new ArrayList<>();
            while (rs.next())
              {
                oneTeacher.add(rs.getString("id"));
                oneTeacher.add(rs.getString("name"));
                oneTeacher.add(rs.getString("username"));
                oneTeacher.add(rs.getString("email"));
                oneTeacher.add(rs.getString("password"));
              }
            return oneTeacher;
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

  }
