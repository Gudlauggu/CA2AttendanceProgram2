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

    public ArrayList<ArrayList<String>> getStudUsername()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<ArrayList<String>> students = new ArrayList<>();
            while (rs.next())
              {
                                
                ArrayList<String> oneStudent =  new ArrayList<>();
                oneStudent.add(rs.getString("username"));
                oneStudent.add(rs.getString("id"));
                students.add(oneStudent);
              }
            return students;
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

    public boolean checkRightPassword(int id, String password)
      {
        try (Connection con = conManager.getConnection())
          {
              System.out.println("ID = "+ id);
            String query = "SELECT * FROM [Student] WHERE id = " + id;
              System.out.println(query);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query); 
            rs.next();
            System.out.println(rs.getString("id"));
              
            return password.equals(rs.getString("password"));
          } 
        catch (SQLException sqle)
          {
            System.err.println(sqle);
            return false;
          }
      }

    public ArrayList<String> getStudent(String id)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Student] WHERE id =" + id;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> students = new ArrayList<>();
            while (rs.next())
              {
                students.add(rs.getString("id"));
                students.add(rs.getString("Name"));
                students.add(rs.getString("username"));
                students.add(rs.getString("email"));
                students.add(rs.getString("password"));

              }
            return students;
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

}
