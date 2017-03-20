/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z2Data.DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author essam.nasr
 */
public class Config {
    public static void main(String[] args){
        OutputStream fouts=null;
        try {
            Properties prop=new Properties();
            fouts = new FileOutputStream("DBConfig.properties");
            prop.setProperty("user_name", "");
            prop.setProperty("password", "");
            prop.setProperty("server_url", "");
            prop.setProperty("database_name", "");
            prop.store(fouts, null);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fouts.close();
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
