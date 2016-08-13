package org.renix.updater.filehandler.old;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.renix.updater.UpdaterMain;
import org.renix.updater.util.ConfigUtil;

public class FileDownload {
    /**
     * 需要新增的文件
     */
    static Set<String> fileAddSet = FileCompare.fileAddSet;
    static Set<String> fileDelSet = FileCompare.fileDelSet;
    static Set<String> fileModSet = FileCompare.fileModSet;

    static Set<String> dirAddSet = FileCompare.dirAddSet;
    static Set<String> dirDelSet = FileCompare.dirDelSet;

    public static void DownloadFile() throws IOException {
        String localPath = ConfigUtil.appHome+"\\update";
        String urlStr = UpdaterMain.up.getUrl();
        for (String string : fileAddSet) {
            URL url = new URL(urlStr + "/" + UpdaterMain.up.getVersion().getTag() + "/" + string);
            File f = new File(localPath + "\\" + string);
            FileUtils.copyURLToFile(url, f);
        }
        for (String string : fileModSet) {
            URL url = new URL(urlStr + "/" + UpdaterMain.up.getVersion().getTag() + "/" + string);
            File f = new File(localPath + "\\" + string);
            FileUtils.copyURLToFile(url, f);
        }

    }

    public static void DownloadDir() throws IOException {
        String localPath = ConfigUtil.appHome+"\\update";
        for (String string : dirAddSet) {
            FileUtils.forceMkdir(new File(localPath + "\\" + string));
        }
    }

    // public static void actionCommit() {
    // long size = 0;
    // for (String key : curVersion.keySet())
    // size += curVersion.get(key).getSize();
    // watcher.setAllBytes(size);
    // download = new Thread() {
    // @Override
    // public void run() {
    // /* Fetch */
    // for (String key : curVersion.keySet()) {
    // String result = curVersion.get(key).fetch(application, watcher);
    // if (result != null) {
    // watcher.stopWatcher();
    // application.receiveMessage(result);
    // gui.errorOnCommit(result);
    // return;
    // }
    // }
    // /* Prepare */
    // watcher.stopWatcher();
    // gui.setIndetermined();
    // for (String key : curVersion.keySet()) {
    // String result = curVersion.get(key).prepare(application);
    // if (result != null) {
    // application.receiveMessage(result);
    // gui.errorOnCommit(result);
    // return;
    // }
    // }
    //
    // /* Construct launcher parameters */
    // ArrayList<XElement> elements = new ArrayList<XElement>();
    // for (String key : curVersion.keySet())
    // elements.add(curVersion.get(key).getExecElement());
    //
    // /* relaunch should be performed with original arguments, not jupidator update */
    // ArrayList<String> relaunch = new ArrayList<String>();
    // relaunch.addAll(hostVersion.getArch().getRelaunchCommand(hostInfo));
    //
    // DeployerParameters params = new DeployerParameters(curInfo.getApplicationHome());
    // params.setElements(elements);
    // if (!curInfo.isSelfUpdate()) // Add self update information if we do not update jupidator
    // params.addElement(AppVersion.construct(curVersion.getAppElements()).getXElement(curInfo.getApplicationHome()));
    // params.setHeadless(gui.isHeadless());
    // params.setRelaunchCommand(relaunch);
    //
    // /* Construct launcher command */
    // try {
    // procbuilder = PermissionManager.manager.getLaunchCommand(application, params);
    // if (procbuilder == null)
    // throw new IOException("Unable to create relauncer");
    // } catch (IOException ex) {
    // String message = ex.getMessage();
    // application.receiveMessage(message);
    // gui.errorOnRestart(message);
    // return;
    // }
    //
    // gui.successOnCommit(!relaunch.isEmpty());
    // }
    // };
    // watcher.startWatcher();
    // download.start();
    // }
}
