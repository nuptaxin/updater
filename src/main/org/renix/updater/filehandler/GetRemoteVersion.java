package org.renix.updater.filehandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.renix.updater.bean.Update;
import org.renix.updater.bean.Version;
import org.renix.updater.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetRemoteVersion {

    private static Logger LOGGER = LoggerFactory.getLogger(GetRemoteVersion.class);

    @SuppressWarnings("unchecked")
    private static void parseUpdateXML(Element node, Update up) {
        // 遍历属性节点
        if ("updatelist".equals(node.getName())) {
            up.setAppName(node.attributeValue("application"));
            up.setUrl(node.attributeValue("baseurl"));
            up.setIconUrl(node.attributeValue("icon"));
        }

        if ("launcher".equals(node.getName())) {
            up.setCmdList(new ArrayList<String>(3));
            List<String> cmdList = up.getCmdList();
            cmdList.add(node.attributeValue("exec"));
            List<Element> eList = node.elements();
            for (Element e : eList) {
                cmdList.add(e.attributeValue("value"));
            }
            return;
        }
        if ("version".equals(node.getName())) {
            Version v = new Version();
            Version v1 = up.getVersion();
            Integer release = Integer.valueOf(node.attributeValue("release"));
            v.setRelease(release);
            v.setTag(node.attributeValue("tag"));
            v.setDesp(node.elementText("description"));
            String dtStr = node.elementText("datetime");
            if (dtStr != null) {
                DateTime dt =
                        DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").parseDateTime(
                                node.elementText("datetime"));
                v.setUpdateTime(dt.toDate());
            }
            if(v1==null){
                up.setVersion(v);
            }else if (v1.getRelease() < release){
                up.setVersion(v);
            }
            up.getVersionMap().put(v.getTag(), v);
            
            return;

        }

        if ("filescan".equals(node.getName())) {
            up.setFileSets(new HashSet<String>());
            Set<String> fileSets = up.getFileSets();
            String[] fileStrs = StringUtils.split(node.elementText("file"), "|");
            for (String string : fileStrs) {
                fileSets.add(string);
            }
            up.setDirSets(new HashSet<String>());
            Set<String> dirSets = up.getDirSets();
            String[] dirStrs = StringUtils.split(node.elementText("directory"), "|");
            for (String string : dirStrs) {
                dirSets.add(string);
            }
            return;
        }

        // 同时迭代当前节点下面的所有子节点
        // 使用递归
        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            parseUpdateXML(e, up);
        }
    }

    @SuppressWarnings("unchecked")
    private static void parseMd5XML(Element node, Map<String, Long> fileSizeMap,
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

    public static Update getUpdateFile(String xmlUrl) {
        URL url;
        try {
            url = new URL(xmlUrl);
        } catch (MalformedURLException e) {
            LOGGER.error("无法获取远程版本信息文件", e);
            return null;
        }

        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 读取文件 转换成Document
        Document document;
        try {
            document = reader.read(url);
        } catch (DocumentException e) {
            LOGGER.error("解析器无法解析远程版本信息文件", e);
            return null;
        }
        // 获取根节点元素对象
        Element root = document.getRootElement();
        Update up = new Update();
        parseUpdateXML(root, up);
        return up;
    }

    public static void getMD5File(String xmlMD5Url, Map<String, Long> fileSizeMap,
            Map<String, String> fileMD5Map, Set<String> dirSet) {
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
        // Map<String,Long> fileSizeMap = new HashMap<String, Long>();
        // Map<String,String> fileMD5Map = new HashMap<String, String>();
        // Set<String> dirSet = new HashSet<String>();
        try {
            parseMd5XML(root, fileSizeMap, fileMD5Map, dirSet, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DocumentException, IOException {
        ConfigUtil.initConfig();
        String urlStr = ConfigUtil.xmlUrl;
        URL url = new URL(urlStr);

        url = new URL("http://192.1.1.56/resource/update/dist/m1.xml");

        // 创建SAXReader对象
        SAXReader reader = new SAXReader();
        // 读取文件 转换成Document
        Document document = reader.read(url);
        // 获取根节点元素对象
        Element root = document.getRootElement();


        // listNodes(root,urlStr,localStr);
        // Update up = new Update();
        // getUpdateXML(root, up);
        // System.out.println(up);

        Map<String, Long> fileSizeMap = new HashMap<String, Long>();
        Map<String, String> fileMD5Map = new HashMap<String, String>();
        Set<String> dirSet = new HashSet<String>();
        Long sumCount = 0l;
        parseMd5XML(root, fileSizeMap, fileMD5Map, dirSet, "");
        System.out.println(sumCount);

    }

}
