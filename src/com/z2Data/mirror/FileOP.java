/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z2Data.mirror;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author essam.nasr
 */
public class FileOP {
    
    public static Map<String,String> readFileHashMap(String filePath) throws FileNotFoundException {
        Map<String,String> filedataHashMap=new HashMap();
        BufferedReader reader=new BufferedReader(new FileReader(new File(filePath)));
        String line=null;
        String kv[]=null;
        try {
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                 kv=line.split("\t");
                 filedataHashMap.put(kv[0], kv[1]);
            }
             reader.close();
        } catch (IOException e) {
            System.err.println("Exception File reader:" + e);
        }
      
        return filedataHashMap;
    }
    
    public static void updateFile(String filePath,int[] input) throws IOException {
        File file=new File(filePath);
        File outFile=new File("D:\\File Mirror Data\\inputResult.txt");
        BufferedWriter writer=new BufferedWriter(new FileWriter(outFile));
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line=null;
        for (int i = 0; i < input.length; i++) {
           line=reader.readLine();
           writer.write(line+"\t"+input[i]);
           //String afterUpdate=reader.readLine();
           writer.newLine();
        }
       writer.close();
       reader.close();
    }
}
