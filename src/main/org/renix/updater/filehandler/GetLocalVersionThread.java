package org.renix.updater.filehandler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.renix.updater.UpdaterMain;
import org.renix.updater.bean.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetLocalVersionThread implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(GetLocalVersionThread.class);

    private int parseLocalVersionFile(Element node, Version v) {
        int skipRelease = 0;
        if ("version".equals(node.getName())) {
            v.setTag(node.elementText("tag"));
            v.setDesp(node.elementText("description"));
            if(node.elementText("skip")!=null){
                skipRelease = Integer.valueOf(node.elementText("skip"));
            }
            
        }
        return skipRelease;
    }

    @Override
    public void run() {
        String userHome = FileUtils.getUserDirectoryPath();
        String versionParent = userHome + File.separator + ".updater-java";
        File versionFile = FileUtils.getFile(versionParent, "version.xml");
        // 如果存在本地版本信息，那么同步解析本地版本信息并加载到内存。
        if (versionFile.exists()) {
            Version v = UpdaterMain.localVersion;
            if (v == null) {
                v= new Version();
                // 创建SAXReader对象
                SAXReader reader = new SAXReader();
                // 读取文件 转换成Document
                Document document = null;
                try {
                    document = reader.read(versionFile);
                } catch (DocumentException e) {
                    LOGGER.error("无法读取本地版本信息文件", e);
                }
                // 获取根节点元素对象
                Element root = document.getRootElement();
                UpdaterMain.skipVersion = parseLocalVersionFile(root, v);
                UpdaterMain.localVersion = v;
            }

        } else {
            // 如果不存在本地版本信息，那么加载一个null信息的local version
            UpdaterMain.localVersion = null;
        }
        UpdaterMain.versionThread = "stop";

    }

    public static void main(String[] args) throws DocumentException, IOException {
        String userHome = FileUtils.getUserDirectoryPath();
        String versionParent = userHome + File.separator + ".updater-java";
        File versionFile = FileUtils.getFile(versionParent, "version.xml");
        // 如果存在本地版本信息，那么同步解析本地版本信息并加载到内存。
        if (versionFile.exists()) {
                // 创建SAXReader对象
                SAXReader reader = new SAXReader();
                // 读取文件 转换成Document
                Document document = null;
                try {
                    document = reader.read(versionFile);
                } catch (DocumentException e) {
                    LOGGER.error("无法读取本地版本信息文件", e);
                }
                // 获取根节点元素对象
                Element root = document.getRootElement();
                Version v =  new Version();
                GetLocalVersionThread m = new GetLocalVersionThread();
                int skip = m.parseLocalVersionFile(root, v);
                System.out.println(v.getTag());
                System.out.println(v.getDesp());
                System.out.println(skip);

        }
    }

}
