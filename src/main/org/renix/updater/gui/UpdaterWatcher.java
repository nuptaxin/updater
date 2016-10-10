package org.renix.updater.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.renix.updater.UpdaterMain;
import org.renix.updater.bean.Version;
import org.renix.updater.filehandler.FileCompareThread;
import org.renix.updater.filehandler.FileDeleteThread;
import org.renix.updater.filehandler.FileDownloadThread;
import org.renix.updater.filehandler.FileUpdateThread;
import org.renix.updater.filehandler.GetLocalMD5Thread;
import org.renix.updater.filehandler.GetRemoteMD5Thread;
import org.renix.updater.filehandler.WriteLocalVersion2File;
import org.renix.updater.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: UpdaterWatcher
 * @Description: UI监控器
 * @author renzx
 * @date 2016年10月10日
 */
public class UpdaterWatcher {
    private static Logger LOGGER = LoggerFactory.getLogger(UpdaterWatcher.class);
    public static Map<String, Long> fileSizeMap = new HashMap<String, Long>();
    static Map<String, String> fileMD5Map = new HashMap<String, String>();
    static Set<String> dirSet = new HashSet<String>();

    static Map<String, Long> localFileSizeMap = new HashMap<String, Long>();
    static Map<String, String> localFileMD5Map = new HashMap<String, String>();
    static Set<String> localDirSet = new HashSet<String>();
    UpdaterFrame uframe;
    public float percent = 0f;
    public int currentStep = 1;

    public void step1x() {
        Thread t = new Thread(new GetRemoteMD5Thread());
        t.start();
        percent = 0f;
        currentStep = 1;
    }

    public void step2x() {
        Thread t = new Thread(new GetLocalMD5Thread());
        t.start();
        percent = 0f;
        currentStep = 2;
    }

    public void step3x() {
        Thread t = new Thread(new FileCompareThread());
        t.start();
        percent = 0f;
        currentStep = 3;
    }

    public void step4x() {
        Thread t = new Thread(new FileDownloadThread());
        t.start();
        percent = 0f;
        currentStep = 4;
    }

    public void step5x() {
        Thread t = new Thread(new FileDeleteThread());
        t.start();
        percent = 0f;
        currentStep = 5;
    }

    public void step5xx() {
        Thread t1 = new Thread(new FileUpdateThread());
        t1.start();
    }

    public void setFrame(UpdaterFrame frame) {
        this.uframe = frame;
    }

    public void updateStepProgress(int step) {
        uframe.updateStepProgress(step);
    }

    public void updateProgressBar(float percent) {
        this.percent = percent;
        uframe.updateProgressBar("xxx", percent);

    }

    public void closeMeAndStarttarget(int type) {
        // 启动前需要先检查注册表
        Boolean flag = true;
        /*
         * Integer status = RegUtil.getCmdExtStatus(); if (status == null || status == 0) { flag =
         * RegUtil.setCmdExtStatus(1); }
         */

        try {
            if (flag.equals(Boolean.FALSE)) {
                LOGGER.error("由于命令行扩展不支持，无法启动应用。");
            } else {
                // 更新完重启
                if (type == 1) {
                    List<String> strList = UpdaterMain.up.getCmdList();
                    String[] strs = new String[strList.size()];
                    for (int i = 0; i < strList.size(); i++) {
                        String str = strList.get(i);
                        if (StringUtils.contains(strList.get(i), "{appHome}")) {
                            str =
                                    StringUtils.replace(strList.get(i), "{appHome}", "\"\" \""
                                            + ConfigUtil.appHome);
                            str += "\"";
                        }

                        strs[i] = str;
                    }
                    Runtime.getRuntime().exec(strs);
                } else {
                    // 跳过更新重启
                    String[] strs = new String[3];
                    strs[0] = "cmd.exe";
                    strs[1] = "/c";
                    strs[2] = "start \"\" \"" + ConfigUtil.programEntry + "\"";
                    Runtime.getRuntime().exec(strs);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void WriteLocalVersion(Version version) {
        WriteLocalVersion2File.write(version, UpdaterMain.skipVersion);
        UpdaterMain.localVersion = version;
        // 需要判断下选择的版本是否为最新的版本
        if (UpdaterMain.up.getVersion().getTag().equals(version.getTag())) {
            closeMeAndStarttarget(2);
        }

    }

    public void WriteLocalVersion(Version localVersion, Integer release) {
        WriteLocalVersion2File.write(localVersion, release);

    }
}
