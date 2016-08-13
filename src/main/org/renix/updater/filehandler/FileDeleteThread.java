package org.renix.updater.filehandler;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.renix.updater.UpdaterMain;
import org.renix.updater.util.Cache;
import org.renix.updater.util.ConfigUtil;

public class FileDeleteThread implements Runnable {
    @Override
    public void run() {
        UpdaterMain.watcher.updateStepProgress(4);
        Cache cache = Cache.getInstance();
        Set<String> fileDelSet = cache.getFileDelSet();
        Set<String> fileModSet = cache.getFileModSet();

        Set<String> dirDelSet = cache.getDirDelSet();

        // del file
        String localPath = ConfigUtil.appHome;
        String backUpPath = ConfigUtil.backupDir;
        for (String string : fileDelSet) {
            try {
                FileUtils.moveFile(FileUtils.getFile(localPath, string),
                        FileUtils.getFile(backUpPath, string));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (String string : fileModSet) {
            try {
                FileUtils.moveFile(FileUtils.getFile(localPath, string),
                        FileUtils.getFile(backUpPath, string));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // del dir
        for (String string : dirDelSet) {
            try {
                FileUtils.moveDirectoryToDirectory(FileUtils.getFile(localPath, string),
                        FileUtils.getFile(backUpPath, string), true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        UpdaterMain.watcher.updateProgressBar(0.5f);
    }
}
