/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Mecaa
 */
public class SettingHandler
  {

    public ArrayList<String> loadProp()
      {
        Properties prop = new Properties();
        ArrayList<String> strings = new ArrayList();
        InputStream input = null;

        try
          {
            String propFileName = "config.properties";
            input = new FileInputStream("Resources/" + propFileName);

            // load a properties file
            prop.load(input);
            if (prop.getProperty("RememberUP").equals("True"))
              {
                strings.add(prop.getProperty("user"));
                strings.add(prop.getProperty("password"));
              }
          }
        catch (IOException ex)
          {
            ex.printStackTrace();
          }
        finally
          {
            if (input != null)
              {
                try
                  {
                    input.close();
                  }
                catch (IOException e)
                  {
                    e.printStackTrace();
                  }
              }
          }
        return strings;
      }

    public void saveUsername(String username, String password)
      {
        Properties prop = new Properties();
        OutputStream output = null;

        try
          {

            output = new FileOutputStream("Resources/config.properties");

            // set the properties value
            prop.setProperty("RememberUP", "True");
            prop.setProperty("user", username);
            prop.setProperty("password", password);

            // save properties to project root folder
            prop.store(output, null);

          }
        catch (IOException io)
          {
            io.printStackTrace();
          }
        finally
          {
            if (output != null)
              {
                try
                  {
                    output.close();
                  }
                catch (IOException e)
                  {
                    e.printStackTrace();
                  }
              }

          }
      }

    public void resetFile()
      {
        Properties prop = new Properties();
        OutputStream output = null;

        try
          {

            output = new FileOutputStream("Resources/config.properties");

            // set the properties value
            prop.setProperty("RememberUP", "False");
            prop.setProperty("user", "Username");
            prop.setProperty("password", "P");

            // save properties to project root folder
            prop.store(output, null);

          }
        catch (IOException io)
          {
            io.printStackTrace();
          }
        finally
          {
            if (output != null)
              {
                try
                  {
                    output.close();
                  }
                catch (IOException e)
                  {
                    e.printStackTrace();
                  }
              }

          }
      }
  }
