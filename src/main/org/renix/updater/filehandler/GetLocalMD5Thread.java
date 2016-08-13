package org.renix.updater.filehandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.renix.updater.UpdaterMain;
import org.renix.updater.util.Cache;
import org.renix.updater.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetLocalMD5Thread implements Runnable {

    private Logger LOGGER = LoggerFactory.getLogger(GetLocalMD5Thread.class);

    private void listInnerFile(File f, Map<String, Long> localFileSizeMap,
            Map<String, String> localFileMD5Map, Set<String> localDirSet, String parentDir)
            throws IOException {
        File[] subDir = f.listFiles();
        for (File file : subDir) {
            if (file.isFile()) {
                localFileSizeMap.put(parentDir + file.getName(), FileUtils.sizeOf(file));
                FileInputStream fStream = FileUtils.openInputStream(file);
                localFileMD5Map.put(parentDir + file.getName(), DigestUtils.md5Hex(fStream));
                fStream.close();
            } else {
                localDirSet.add(parentDir + file.getName());
                String nextParentDir = parentDir + file.getName() + "/";
                listInnerFile(file, localFileSizeMap, localFileMD5Map, localDirSet, nextParentDir);
            }
        }

    }

    @Override
    public void run() {
        UpdaterMain.watcher.updateStepProgress(1);
        File f = new File(ConfigUtil.appHome);
        Cache cache = Cache.getInstance();
        Map<String, Long> localFileSizeMap = cache.getLocalFileSizeMap();
        Map<String, String> localFileMD5Map = cache.getLocalFileMD5Map();
        Set<String> localDirSet = cache.getLocalDirSet();
        Collection<File> c = FileUtils.listFilesAndDirs(f, new AbstractFileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                for (String name2 : UpdaterMain.up.getFileSets()) {
                    Pattern pattern = Pattern.compile(name2);
                    Matcher matcher = pattern.matcher(name);
                    if (matcher.find()) {
                        return true;
                    }
                }
                return false;
            }
        }, new NameFileFilter(new ArrayList<String>(UpdaterMain.up.getDirSets())));
        for (File file : c) {
            System.out.println(file);
            if (file.equals(f))
                continue;
            if (file.isFile()) {
                localFileSizeMap.put(file.getName(), FileUtils.sizeOf(file));
                FileInputStream fStream = null;
                try {
                    fStream = FileUtils.openInputStream(file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    localFileMD5Map.put(file.getName(), DigestUtils.md5Hex(fStream));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    if (fStream != null) {
                        fStream.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (file.isDirectory()) {
                localDirSet.add(file.getName());
                String parentDir = file.getName() + "/";
                try {
                    listInnerFile(file, localFileSizeMap, localFileMD5Map, localDirSet, parentDir);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        UpdaterMain.watcher.updateProgressBar(1f);

    }
}
