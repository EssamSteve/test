/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z2Data.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author essam.nasr
 */
public class CRUID {
    ResultSet rs;
    Statement stm;
    Connection con;

    public CRUID() {
        con=DBConnection.getConnection();
    }
    
    //return top 10000 and update their status with "InProgress"
    public Map<String, String> selectRemoteUrls(){
        String query="";
        try{
        stm=con.createStatement();
        rs=stm.executeQuery(query);
        while (rs.next()) {
            //Object nextElement = en.nextElement();
        }
        }catch(SQLException ex){
            System.err.println("Error in getting top 10000 url data from DB:------>"+ex);
        }
            return null;
    }

    public boolean updateUrlDownloadFlag(Map<String,String> keyFlagsMap) {
       
        return true;
    }

}
