/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author gudla
 */
public class SQLConnectionHandler
{
    private static SQLServerDataSource DS = new SQLServerDataSource();

    public SQLConnectionHandler()
      {
        setupDataSource();
      }
    
    public Connection getConnection() throws SQLServerException
      {
        return DS.getConnection();
      }
    
    
    private void setupDataSource()
      {
        DS.setDatabaseName("Kahani_Attendance");
        DS.setUser("CS2016B_11");
        DS.setPassword("CS2016B_11");
        DS.setPortNumber(1433);
        DS.setServerName("10.176.111.31");
      }
}
