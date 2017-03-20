/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z2Data.DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author essam.nasr
 */
public class DBConnection {
    public static Connection getConnection(){
       try {
        Properties prop=new Properties();
        InputStream finps=new FileInputStream("DBconfig");
        prop.load(finps);
        String userName=prop.getProperty("user_name");
        String pass=prop.getProperty("password");
        String serverUrl=prop.getProperty("server_url");
        String dbName=prop.getProperty("database_name");
        String url=getUrl(serverUrl,dbName);
            return DriverManager.getConnection(userName,pass,url);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    public static String getUrl(String hostUrl,String dbName) {
        String urlCon="jdbc:microsoft:sqlserver://"+hostUrl+":1433;DatabaseName="+dbName;
        return urlCon;
    }
}
