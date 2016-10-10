package org.renix.updater.filehandler;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.renix.updater.UpdaterMain;
import org.renix.updater.util.Cache;
import org.renix.updater.util.ConfigUtil;

/**
 * @ClassName: FileDownloadThread
 * @Description: 文件下载线程
 * @author renzx
 * @date 2016年10月10日
 */
public class FileDownloadThread implements Runnable {
    /**
     * 需要新增的文件
     * 
     * @param dirAddSet
     * @param updateTmpDir
     */
    private void DownloadDir(Set<String> dirAddSet, String updateTmpDir) throws IOException {
        String localPath = updateTmpDir;
        for (String string : dirAddSet) {
            FileUtils.forceMkdir(new File(localPath + "\\" + string));
        }
    }

    @Override
    public void run() {
        UpdaterMain.watcher.updateStepProgress(3);
        Cache cache = Cache.getInstance();
        Set<String> fileAddSet = cache.getFileAddSet();
        Set<String> fileModSet = cache.getFileModSet();

        Set<String> dirAddSet = cache.getDirAddSet();
        // 删除之前的更新文件临时目录
        String updateTmpDir = ConfigUtil.updateTmpDir;
        File updateTmpDirFile = new File(updateTmpDir);
        FileUtils.deleteQuietly(updateTmpDirFile);

        try {
            DownloadDir(dirAddSet, updateTmpDir);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String localPath = updateTmpDir;
        String urlStr = UpdaterMain.up.getUrl();
        Set<String> fileSet = new HashSet<String>();
        fileSet.addAll(fileAddSet);
        fileSet.addAll(fileModSet);
        // 计算总文件大小
        Long sumSize = 0l;
        Map<String, Long> fileSizeMap = cache.getFileSizeMap();
        for (String string : fileSet) {
            sumSize += fileSizeMap.get(string);
        }
        Long currentSize = 0l;
        for (String string : fileSet) {
            URL url = null;
            try {
                url = new URL(urlStr + "/" + UpdaterMain.up.getVersion().getTag() + "/" + string);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            File f = new File(localPath + "\\" + string);
            try {
                FileUtils.copyURLToFile(url, f);
                currentSize += fileSizeMap.get(string);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            float percent = currentSize * 1.0f / sumSize;
            UpdaterMain.watcher.updateProgressBar(percent);
        }

        UpdaterMain.watcher.updateProgressBar(1f);

    }

}
