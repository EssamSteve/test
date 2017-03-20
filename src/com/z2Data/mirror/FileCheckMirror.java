/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z2Data.mirror;

import com.z2Data.DB.CRUID;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author essam.nasr
 */
public class FileCheckMirror implements Runnable{
    CRUID op;
    Map<String, String> inputMapUrl_HasCode;
    String oldDirString;
    String newDirString;
    public FileCheckMirror(Map<String, String> inputMapUrl_HasCode,String oldDirString,String newDirString) {
        this.oldDirString=oldDirString;
        this.newDirString=newDirString;
        this.inputMapUrl_HasCode=inputMapUrl_HasCode;
    }
    
    
    public synchronized void updateFileData(){
        //int flags[]=new int[new HashMap<>(inputMapUrl_HasCode).size()];
       // int i=0;
        String key_url,remoteHashCode =null; 
        String localFile = null;
        for (Map.Entry<String, String> entry : inputMapUrl_HasCode.entrySet()) {
             key_url = entry.getKey();
             remoteHashCode = entry.getValue();
             localFile = getLocalDir(key_url,oldDirString,newDirString);
            int downloaded=fileExist(remoteHashCode, localFile);
            switch(downloaded){
                    case 1: //flags[i]=1;
                            entry.setValue("1");
                            System.out.println("File Found : update value with 1 in data base DONE");
                            break;
                    case 2:  // set file flag 2 ---> exist but not NOT EQUAL IN HASHCODE
                             entry.setValue("2");
                            //flags[i]=2;
                            System.out.println("File Fount BUT not complete : update value with 2 in data base DONE");
                             break;
                    default: entry.setValue("3");
                            //flags[i]=3;
                            System.out.println("File doesn't exist : update value with 3 in data base DONE");
                            break;             
            }
           // i++;
        }
        //update dataBase
            op.updateUrlDownloadFlag(inputMapUrl_HasCode);
        //FileOP.updateFile("D:\\File Mirror Data\\input.txt",flags);
    }
    
    /*
    replace url file dir with local file dire to start search from
    */
    public  static String getLocalDir(String url,String oldString,String newString) {
           return  url.replace(oldString,newString);   
    }
    /*Check File Completly Donloded*/
    public int fileExist(String remoteFileHashCode,String localFile){
        //send file after reblace 
        //remotefile(dir,hashcode) & localDir
        //file exits&&equalHash-->1 else file exist but notEqualHash-->2
        //if file exist--->get hashcode else return false
        File file=new File(localFile);
        if(file.exists()){
            try {
                if (getFileContentHashCode(file).equals(remoteFileHashCode))
                    return 1;
                else
                    return 2;
            } catch (Exception ex) {
                System.err.println(ex);
            }
        } 
        return 3;
    }
    public static String getFileContentHashCode(File file) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		FileInputStream fis = new FileInputStream(file);
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		while ((nread = fis.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}
		fis.close();
		byte[] mdbytes = md.digest();
		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));

		}
            return "0x" + (sb.toString()).toUpperCase();
	}

    @Override
    public void run() {
            updateFileData();
    }
   
}
