package org.renix.updater.filehandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.renix.updater.UpdaterMain;
import org.renix.updater.util.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: GetRemoteMD5Thread
 * @Description: 获取远程MD5信息线程
 * @author renzx
 * @date 2016年10月10日
 */
public class GetRemoteMD5Thread implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(GetRemoteMD5Thread.class);


    @SuppressWarnings("unchecked")
    private void parseMd5XML(Element node, Map<String, Long> fileSizeMap,
            Map<String, String> fileMD5Map, Set<String> dirSet, String baseDir) throws IOException {
        if ("dir".equals(node.getName())) {
            dirSet.add(baseDir + node.attributeValue("name"));
            baseDir = baseDir + node.attributeValue("name") + "/";
        } else if ("file".equals(node.getName())) {
            fileSizeMap.put(baseDir + node.attributeValue("name"),
                    Long.valueOf(node.attributeValue("size")));
            fileMD5Map.put(baseDir + node.attributeValue("name"), node.attributeValue("md5"));
            // System.out.println(baseDir + node.attributeValue("name"));
        }

        // 同时迭代当前节点下面的所有子节点
        // 使用递归
        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            parseMd5XML(e, fileSizeMap, fileMD5Map, dirSet, baseDir);
        }
    }


    public void getMD5File(String xmlMD5Url, Map<String, Long> fileSizeMap,
            Map<String, String> fileMD5Map, Set<String> dirSet) {
        URL url;
        try {
            url = new URL(xmlMD5Url);
        } catch (MalformedURLException e) {
            LOGGER.error("无法获取版本详情信息文件" + xmlMD5Url, e);
            return;
        }

        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 读取文件 转换成Document
        Document document;
        try {
            document = reader.read(url);
        } catch (DocumentException e) {
            LOGGER.error("解析器无法解析远程版本详情文件", e);
            return;
        }
        // 获取根节点元素对象
        Element root = document.getRootElement();
        // Map<String,Long> fileSizeMap = new HashMap<String, Long>();
        // Map<String,String> fileMD5Map = new HashMap<String, String>();
        // Set<String> dirSet = new HashSet<String>();
        try {
            parseMd5XML(root, fileSizeMap, fileMD5Map, dirSet, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        UpdaterMain.watcher.updateStepProgress(0);
        String baseUrl = UpdaterMain.up.getUrl();
        String xmlMD5Url = baseUrl + "/" + UpdaterMain.up.getVersion().getTag() + "/md5.xml";
        System.out.println("xmlMD5Url" + xmlMD5Url);
        Cache cache = Cache.getInstance();
        Map<String, Long> fileSizeMap = cache.getFileSizeMap();
        Map<String, String> fileMD5Map = cache.getFileMD5Map();
        Set<String> dirSet = cache.getDirSet();

        URL url;
        try {
            url = new URL(xmlMD5Url);
        } catch (MalformedURLException e) {
            LOGGER.error("无法获取版本详情信息文件", e);
            return;
        }

        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 读取文件 转换成Document
        Document document;
        try {
            document = reader.read(url);
        } catch (DocumentException e) {
            LOGGER.error("解析器无法解析远程版本详情文件", e);
            return;
        }
        // 获取根节点元素对象
        Element root = document.getRootElement();
        try {
            parseMd5XML(root, fileSizeMap, fileMD5Map, dirSet, "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        UpdaterMain.watcher.updateProgressBar(1f);

    }


}
