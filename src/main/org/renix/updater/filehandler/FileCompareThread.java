package org.renix.updater.filehandler;

import java.util.Map;
import java.util.Set;
import org.renix.updater.UpdaterMain;
import org.renix.updater.util.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: FileCompareThread
 * @Description: 文件比较线程
 * @author renzx
 * @date 2016年10月10日
 */
public class FileCompareThread implements Runnable {
    Logger LOGGER = LoggerFactory.getLogger(FileCompareThread.class);

    @Override
    public void run() {
        UpdaterMain.watcher.updateStepProgress(2);
        Cache cache = Cache.getInstance();
        Set<String> fileAddSet = cache.getFileAddSet();
        Set<String> fileDelSet = cache.getFileDelSet();
        Set<String> fileModSet = cache.getFileModSet();

        Set<String> dirAddSet = cache.getDirAddSet();
        Set<String> dirDelSet = cache.getDirDelSet();

        Map<String, String> remoteFileMap = cache.getFileMD5Map();
        Map<String, String> localFileMap = cache.getLocalFileMD5Map();

        Set<String> remoteDirSet = cache.getDirSet();
        Set<String> localDirSet = cache.getLocalDirSet();

        // 对比文件夹差异
        for (String remoteDir : remoteDirSet) {
            if (localDirSet.contains(remoteDir)) {
                dirDelSet.remove(remoteDir);
            } else {
                dirAddSet.add(remoteDir);
            }
        }
        // 对比文件差异
        for (String remoteKey : remoteFileMap.keySet()) {
            if (localFileMap.containsKey(remoteKey)) {
                if (!remoteFileMap.get(remoteKey).equals(localFileMap.get(remoteKey))) {
                    fileModSet.add(remoteKey);
                }
                fileDelSet.remove(remoteKey);
            } else {
                fileAddSet.add(remoteKey);
            }
        }
        for (String string : fileAddSet) {
            LOGGER.info("需要新增的文件：" + string);
        }
        for (String string : fileDelSet) {
            LOGGER.info("需要删除的文件：" + string);
        }
        for (String string : fileModSet) {
            LOGGER.info("需要更新的文件：" + string);
        }

        UpdaterMain.watcher.updateProgressBar(1f);
    }
}
