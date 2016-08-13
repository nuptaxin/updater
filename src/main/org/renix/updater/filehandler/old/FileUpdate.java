package org.renix.updater.filehandler.old;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.renix.updater.util.ConfigUtil;

public class FileUpdate {
    /**
     * 需要新增的文件
     */
    static Set<String> fileAddSet = FileCompare.fileAddSet;
    static Set<String> fileDelSet = FileCompare.fileDelSet;
    static Set<String> fileModSet = FileCompare.fileModSet;
    
    static Set<String> dirAddSet = FileCompare.dirAddSet;
    static Set<String> dirDelSet = FileCompare.dirDelSet;
    
    public static void moveFile() throws IOException{
        String updatePath = ConfigUtil.appHome+"\\update";
        String appPath = ConfigUtil.appHome;
        for (String string : fileAddSet) {
            FileUtils.moveFile(FileUtils.getFile(updatePath, string), FileUtils.getFile(appPath, string));
        }
        for (String string : fileModSet) {
            FileUtils.moveFile(FileUtils.getFile(updatePath, string), FileUtils.getFile(appPath, string));
        }
        //移动完所有文件后清理文件夹
        FileUtils.deleteDirectory(new File(ConfigUtil.appHome+"\\update"));
        
    }
    public static void moveDir() throws IOException{
        String appPath = ConfigUtil.appHome;
        for (String string : dirAddSet) {
            FileUtils.forceMkdir(FileUtils.getFile(appPath, string));
        }
    }
}
