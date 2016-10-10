package org.renix.updater;

import org.renix.updater.bean.Update;
import org.renix.updater.bean.Version;
import org.renix.updater.filehandler.CreateVersionMD5;
import org.renix.updater.filehandler.GetLocalVersionThread;
import org.renix.updater.filehandler.GetRemoteVersion;
import org.renix.updater.gui.HTMLCreator;
import org.renix.updater.gui.UpdaterFrame;
import org.renix.updater.gui.UpdaterWatcher;
import org.renix.updater.util.CliUtil;
import org.renix.updater.util.ConfigUtil;



/**
 * @ClassName: UpdaterMain
 * @Description: 主函数入口
 * @author renzx
 * @date 2016年10月10日
 */
public class UpdaterMain {
    public static Version localVersion = null;
    public static int skipVersion = 0;
    public static Update up;
    public static UpdaterWatcher watcher;
    public static String versionThread = "start";

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        // ConfigUtil.initConfig();
        Long optType = CliUtil.parse(args);
        if (optType == 0l) {
            System.exit(0);
        } else if (optType == 2l) {
            CreateVersionMD5.getMd5File();
        } else {
            String xmlUrl = ConfigUtil.xmlUrl;
            // 查找本地配置文件目录
            Thread t = new Thread(new GetLocalVersionThread());
            t.start();
            up = GetRemoteVersion.getUpdateFile(xmlUrl);
            while (!"stop".equals(versionThread)) {
            }
            if (up != null) {
                // 如果localVersion=null,启动版本选择器
                String versionTagStr = null;
                watcher = new UpdaterWatcher();
                if (localVersion != null) {
                    versionTagStr = localVersion.getTag();
                    // 是否需要跳过此版本
                    if (up.getVersion().getRelease() == skipVersion) {
                        watcher.closeMeAndStarttarget(2);
                    }
                    // 是否是最新版本
                    if (up.getVersion().getTag().equals(versionTagStr)) {
                        watcher.closeMeAndStarttarget(2);
                    }
                }
                String newver = "有新版本的 " + up.getAppName() + " 可以下载!";
                String versinfo = "最新版本：" + up.getVersion().getTag() + "  当前版本：" + versionTagStr;
                String title = "找到 " + up.getAppName() + " 新版本";
                String infopane = HTMLCreator.getList(up.getVersion());

                UpdaterFrame gui = UpdaterFrame.getInstance();
                watcher.setFrame(gui);
                gui.runUpdaterFrame(title, newver, versinfo, infopane);

            }
        }
    }
}
