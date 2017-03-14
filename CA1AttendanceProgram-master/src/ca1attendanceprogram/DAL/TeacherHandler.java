/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import java.sql.Connection;
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

            ArrayList<String> teachers = new ArrayList<>();
            while (rs.next())
              {
                String teacherString = "";
                teacherString += rs.getString("id") + " ";
                teacherString += rs.getString("Name") + " ";
                teacherString += rs.getString("username") + " ";
                teacherString += rs.getString("email") + " ";
                teacherString += rs.getString("password");

                teachers.add(teacherString);
              }
            return teachers;
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

            ArrayList<String> teacher = new ArrayList<>();
            while (rs.next())
              {
                String teacherString = "";
                teacherString += rs.getString("id");

                teacher.add(teacherString);
              }
            return teacher;
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

            ArrayList<String> teacher = new ArrayList<>();
            while (rs.next())
              {
                String teacherString = "";
                teacherString += rs.getString("Name");

                teacher.add(teacherString);
              }
            return teacher;
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }

    public ArrayList<ArrayList<String>> getTeachUsername()
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<ArrayList<String>> teacher = new ArrayList<>();
            while (rs.next())
              {
                                
                ArrayList<String> oneTeacher =  new ArrayList<>();
                oneTeacher.add(rs.getString("username"));
                oneTeacher.add(rs.getString("id"));
                teacher.add(oneTeacher);
              }
            return teacher;
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

            ArrayList<String> teacher = new ArrayList<>();
            while (rs.next())
              {
                String teacherString = "";

                teacherString += rs.getString("email");

                teacher.add(teacherString);
              }
            return teacher;
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

            ArrayList<String> teacher = new ArrayList<>();
            while (rs.next())
              {
                String teacherString = "";
                teacherString += rs.getString("password");

                teacher.add(teacherString);
              }
            return teacher;
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
            String query = "SELECT * FROM [Teacher] WHERE id = " + id;
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

    public ArrayList<String> getTeacher(String id)
      {
        try (Connection con = conManager.getConnection())
          {
            String query = "SELECT * FROM [Teacher] WHERE id =" + id;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> teacher = new ArrayList<>();
            while (rs.next())
              {
                teacher.add(rs.getString("id"));
                teacher.add(rs.getString("Name"));
                teacher.add(rs.getString("username"));
                teacher.add(rs.getString("email"));
                teacher.add(rs.getString("password"));

              }
            return teacher;
          } catch (SQLException sqle)
          {
            System.err.println(sqle);
            return null;
          }
      }
}
