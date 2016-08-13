package org.renix.updater.filehandler.old;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCompare {
    static Logger LOGGER =LoggerFactory.getLogger(FileCompare.class);
    /**
     * 需要新增的文件
     */
    public static Set<String> fileAddSet = new HashSet<String>();
    public static Set<String> fileDelSet = new HashSet<String>();
    public static Set<String> fileModSet = new HashSet<String>();
    
    public static Set<String> dirAddSet = new HashSet<String>();
    public static Set<String> dirDelSet = new HashSet<String>();
    
    public static void compareFile(Map<String,String> remoteFileMap,Map<String,String> localFileMap){
        fileAddSet.clear();
        fileModSet.clear();
        fileDelSet = localFileMap.keySet();
        for (String remoteKey : remoteFileMap.keySet()) {
            if(localFileMap.containsKey(remoteKey)){
                if(!remoteFileMap.get(remoteKey).equals(localFileMap.get(remoteKey))){
                    fileModSet.add(remoteKey);
                }
                fileDelSet.remove(remoteKey);
            }else{
                fileAddSet.add(remoteKey);
            }
        }
        for (String string : fileAddSet) {
            LOGGER.info("需要新增的文件："+string);
        }
        for (String string : fileDelSet) {
            LOGGER.info("需要删除的文件："+string);
        }
        for (String string : fileModSet) {
            LOGGER.info("需要更新的文件："+string);
        }
        
    }
    public static void compareDir(Set<String> remoteDirSet,Set<String> localDirSet){
        dirAddSet.clear();
        dirDelSet = localDirSet;
        for (String remoteDir : remoteDirSet) {
            if(localDirSet.contains(remoteDir)){
                dirDelSet.remove(remoteDir);
            }else{
                dirAddSet.add(remoteDir);
            }
        }
    }
}
