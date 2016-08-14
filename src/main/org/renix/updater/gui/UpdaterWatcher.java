package org.renix.updater.gui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.renix.updater.UpdaterMain;
import org.renix.updater.bean.Version;
import org.renix.updater.filehandler.FileCompareThread;
import org.renix.updater.filehandler.FileDeleteThread;
import org.renix.updater.filehandler.FileDownloadThread;
import org.renix.updater.filehandler.FileUpdateThread;
import org.renix.updater.filehandler.GetLocalMD5Thread;
import org.renix.updater.filehandler.GetRemoteMD5Thread;
import org.renix.updater.filehandler.GetRemoteVersion;
import org.renix.updater.filehandler.WriteLocalVersion2File;
import org.renix.updater.filehandler.old.FileCompare;
import org.renix.updater.filehandler.old.FileDelete;
import org.renix.updater.filehandler.old.FileDownload;
import org.renix.updater.filehandler.old.FileUpdate;
import org.renix.updater.filehandler.old.GetLocalMD5;
import org.renix.updater.util.ConfigUtil;

public class UpdaterWatcher {
    public static Map<String,Long> fileSizeMap = new HashMap<String, Long>();
    static Map<String,String> fileMD5Map = new HashMap<String, String>();
    static Set<String> dirSet = new HashSet<String>();
    
    static Map<String,Long> localFileSizeMap = new HashMap<String, Long>();
    static Map<String,String> localFileMD5Map = new HashMap<String, String>();
    static Set<String> localDirSet = new HashSet<String>();
    UpdaterFrame uframe;
    public float percent = 0f;
    public int currentStep = 1;

    public void step1() {
        String baseUrl = UpdaterMain.up.getUrl();
        GetRemoteVersion.getMD5File(baseUrl+"/"+UpdaterMain.up.getVersion().getTag()+"/md5.xml", fileSizeMap, fileMD5Map, dirSet);
        currentStep=1;
        step2x();
    }
    public void step1x() {
        Thread t = new Thread(new GetRemoteMD5Thread());
        t.start();
        percent=0f;
        currentStep=1;
    }
    public void step2() {
        File f =FileUtils.getFile(ConfigUtil.appHome) ;
        
        try {
            GetLocalMD5.makeXmlFile(localFileSizeMap,localFileMD5Map,localDirSet,f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        uframe.updateStepProgress(1);
        step3();
    }
    public void step2x() {
        Thread t = new Thread(new GetLocalMD5Thread());
        t.start();
        percent=0f;
        currentStep=2;
    }
    private void step3() {
        FileCompare.compareDir(dirSet, localDirSet);
        FileCompare.compareFile(fileMD5Map, localFileMD5Map);
        uframe.updateStepProgress(2);
        percent=0f;
        while (percent!=1f) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        step4x();
    }
    public void step3x() {
        Thread t = new Thread(new FileCompareThread());
        t.start();
        percent=0f;
        currentStep=3;
    }
    public void step4x() {
        Thread t = new Thread(new FileDownloadThread());
        t.start();
        percent=0f;
        currentStep=4;
    }
    private void step4() {
        try {
            FileDownload.DownloadDir();
            FileDownload.DownloadFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        uframe.updateStepProgress(3);
        step5();
    }
    public void step5x() {
        Thread t = new Thread(new FileDeleteThread());
        t.start();
        percent=0f;
        currentStep=5;
    }
    public void step5xx() {
        Thread t1 = new Thread(new FileUpdateThread());
        t1.start();
    }
    private void step5() {
        try {
            FileDelete.delFile();
            FileDelete.delDir();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            FileUpdate.moveDir();
            FileUpdate.moveFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        uframe.updateStepProgress(4);
    }
    public void setFrame(UpdaterFrame frame) {
        this.uframe = frame;
    }
    
    public void updateStepProgress(int step){
        uframe.updateStepProgress(step);
    }
    public void updateProgressBar(float percent) {
        this.percent=percent;
        uframe.updateProgressBar("xxx", percent);
        
    }
    public void closeMeAndStarttarget(int type){
        try {
            //更新完重启
            if(type==1){
                List<String> strList = UpdaterMain.up.getCmdList();
                String[] strs = new String[strList.size()];
                for (int i = 0; i < strList.size(); i++) {
                    String str = StringUtils.replace(strList.get(i), "{appHome}", ConfigUtil.appHome);
                    strs[i] = str;
                }
                Runtime.getRuntime().exec(strs);
            }else{
                //跳过更新重启
                String[] strs = new String[3];
                strs[0]="cmd.exe";
                strs[1]="/c";
                strs[2]="start "+ConfigUtil.programEntry;
                Runtime.getRuntime().exec(strs);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            System.exit(0);
        }
    }
    public void WriteLocalVersion(Version version) {
        WriteLocalVersion2File.write(version,UpdaterMain.skipVersion);
        UpdaterMain.localVersion=version;
        //需要判断下选择的版本是否为最新的版本
        if(UpdaterMain.up.getVersion().getTag().equals(version.getTag())){
            closeMeAndStarttarget(2);
        }
        
    }
    public void WriteLocalVersion(Version localVersion, Integer release) {
        WriteLocalVersion2File.write(localVersion,release);
        
    }
}
