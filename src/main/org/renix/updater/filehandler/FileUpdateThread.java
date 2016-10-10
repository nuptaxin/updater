package org.renix.updater.filehandler;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.renix.updater.UpdaterMain;
import org.renix.updater.util.Cache;
import org.renix.updater.util.ConfigUtil;

/**
 * @ClassName: FileUpdateThread
 * @Description: 文件更新线程
 * @author renzx
 * @date 2016年10月10日
 */
public class FileUpdateThread implements Runnable {

    @Override
    public void run() {
        UpdaterMain.watcher.updateStepProgress(4);
        Cache cache = Cache.getInstance();
        Set<String> fileAddSet = cache.getFileAddSet();
        Set<String> fileModSet = cache.getFileModSet();

        Set<String> dirAddSet = cache.getDirAddSet();

        // move dir
        String appPath = ConfigUtil.appHome;
        for (String string : dirAddSet) {
            try {
                FileUtils.forceMkdir(FileUtils.getFile(appPath, string));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // move file
        String updatePath = ConfigUtil.updateTmpDir;
        for (String string : fileAddSet) {
            try {
                FileUtils.moveFile(FileUtils.getFile(updatePath, string),
                        FileUtils.getFile(appPath, string));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (String string : fileModSet) {
            try {
                FileUtils.moveFile(FileUtils.getFile(updatePath, string),
                        FileUtils.getFile(appPath, string));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 移动完所有文件后清理文件夹
        try {
            FileUtils.deleteDirectory(new File(updatePath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        UpdaterMain.watcher.updateProgressBar(1f);

    }
}
