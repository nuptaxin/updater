package org.renix.updater;

import org.renix.updater.bean.Update;
import org.renix.updater.bean.Version;
import org.renix.updater.filehandler.GetRemoteXML;
import org.renix.updater.gui.UpdaterFrame;
import org.renix.updater.gui.UpdaterWatcher;
import org.renix.updater.util.ConfigUtil;
import org.renix.updater.util.VersionUtil;

/**
 * Hello world!
 *
 */
public class UpdaterMain {
    public static Update up;
    public static UpdaterWatcher watcher;
    public static void main(String[] args) {
        ConfigUtil.initConfig();

        String xmlUrl = ConfigUtil.xmlUrl;
        up = GetRemoteXML.getUpdateFile(xmlUrl);
        Version localVersion = VersionUtil.getVersionByXML();
        if (up != null) {
            String newver = "有新版本的 " + up.getAppName() + " 可以下载!";
            String versinfo = up.getVersion().getTag() + "可以更新" + " ，你当期的版本为V1.0.0.0";
            String title = "找到 " + up.getAppName() + " 新版本";
            String infopane = "";
            
            UpdaterFrame gui = UpdaterFrame.getInstance();
            watcher = new UpdaterWatcher();
            watcher.setFrame(gui);
            gui.runUpdaterFrame(title,newver, versinfo,  infopane);
        }

    }
}
