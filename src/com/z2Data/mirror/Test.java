/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z2Data.mirror;

import com.z2Data.DB.CRUID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author essam.nasr
 */
public class Test {
    static CRUID mainop=new CRUID();
    static Map<String,String> urlsDataMap=mainop.selectRemoteUrls();
    public static void main(String[] args) {
        if (args.length > 0) {
            String oldDirToReplace = args[0];
            String newDirToReplace = args[1];
            try {
                FileCheckMirror checker = new FileCheckMirror(urlsDataMap,oldDirToReplace, newDirToReplace);
                checker.updateFileData();
            } catch (Exception ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static void runThreadsMode(int numOfThreds,String oldString,String newString) {
        /*
        -get top 10000 
        -divide result by 10
        -initalize 10 threads and pass 1000 element
        */
        
        Thread t=null;
        
        //divide map to 5 lists
        List<Map<String,String>> mapList=getdividedMapsList(urlsDataMap,numOfThreds);
        for (Map<String, String> map : mapList) {
            t=new Thread(new FileCheckMirror(map, oldString, newString));
        }
        
    }
    public static List<Map<String,String>> getdividedMapsList(Map<String,String> urlsDataMap,int divisionFactor) {
        
        return null;
    }
}
