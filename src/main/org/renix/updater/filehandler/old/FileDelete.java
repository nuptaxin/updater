package org.renix.updater.filehandler.old;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.renix.updater.util.ConfigUtil;

public class FileDelete {
    /**
     * 需要新增的文件
     */
    static Set<String> fileAddSet = FileCompare.fileAddSet;
    static Set<String> fileDelSet = FileCompare.fileDelSet;
    static Set<String> fileModSet = FileCompare.fileModSet;
    
    static Set<String> dirAddSet = FileCompare.dirAddSet;
    static Set<String> dirDelSet = FileCompare.dirDelSet;
    
    public static void delFile() throws IOException{
        String localPath = ConfigUtil.appHome;
        String backUpPath = ConfigUtil.appHome+"\\backup";
        for (String string : fileDelSet) {
            FileUtils.moveFile(FileUtils.getFile(localPath, string), FileUtils.getFile(backUpPath, string));
        }
        for (String string : fileModSet) {
            FileUtils.moveFile(FileUtils.getFile(localPath, string), FileUtils.getFile(backUpPath, string));
        }
        
    }
    public static void delDir() throws IOException{
        String localPath = ConfigUtil.appHome;
        String backUpPath = ConfigUtil.appHome+"\\backup";
        for (String string : dirDelSet) {
            FileUtils.moveDirectoryToDirectory(FileUtils.getFile(localPath, string), FileUtils.getFile(backUpPath, string), true);
        }
    }
}
