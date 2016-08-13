package org.renix.updater.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.renix.updater.bean.Version;

/**
 * 获取本地程序版本号。
 * 如果在当前用户目录下有记录，以当前用户记录为准。
 * 如果当前用户目录下无记录，拿到当前{home}目录下的jar包进行记录。
 * 如果拿不到该记录，那么生成一次MD5值并与远程服务器进行对比。
 * @author renzx
 */
public class VersionUtil {
    public static Version getVersionByXML(){
        return null;
        
    }
    public Version getVersionByJar(){
        return null;
        
    }
    public Version getVersionByUser(){
        return null;
        
    }
    public Version getVersionByCompareFile(){
        return null;
        
    }
    public static void ModifyVersionXML(){
        String userHome = FileUtils.getUserDirectoryPath();
        String versionParent = userHome+File.pathSeparator+".updater-java";
        File versionFile = FileUtils.getFile(versionParent, "version.xml");
        if(!versionFile.exists()){
            try {
                createInitVersionFile(versionFile);
                FileUtils.touch(versionFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    private static void createInitVersionFile(File versionFile) {
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(versionFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
