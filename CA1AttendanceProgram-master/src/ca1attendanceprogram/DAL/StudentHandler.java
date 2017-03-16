/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Student;
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
public class StudentHandler
  {

    SQLConnectionManager conManager;

    public StudentHandler()
      {
        conManager = new SQLConnectionManager();
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
          } catch (SQLException sqle)
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
          } catch (SQLException sqle)
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
          } catch (SQLException sqle)
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
          } catch (SQLException sqle)
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
          } catch (SQLException sqle)
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
            String query = "SELECT * FROM [Student] WHERE username = ?";
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

    public ArrayList<String> getStudent(String username)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student] WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> oneStudent = new ArrayList<>();
            while (rs.next())
              {
                oneStudent.add(rs.getString("id"));
                oneStudent.add(rs.getString("name"));
                oneStudent.add(rs.getString("username"));
                oneStudent.add(rs.getString("email"));
                oneStudent.add(rs.getString("password"));
                oneStudent.add(rs.getString("classid"));
              }
            return oneStudent;
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

  }
