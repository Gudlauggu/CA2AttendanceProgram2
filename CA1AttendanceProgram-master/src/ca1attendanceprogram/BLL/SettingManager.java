/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.BLL;

import ca1attendanceprogram.DAL.SettingHandler;
import java.util.ArrayList;

/**
 *
 * @author Mecaa
 */
public class SettingManager
  {

    private SettingHandler sHandler = new SettingHandler();

    public void rememberUsername(String username, String password)
      {
        sHandler.saveUsername(username, password);
      }

    public ArrayList<String> getSettings()
      {
        return sHandler.loadProp();
      }

    public void resetSettings()
      {
        sHandler.resetFile();
      }
  }
