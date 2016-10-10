package org.renix.updater.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.joda.time.DateTime;
import org.renix.updater.bean.Version;

/**
 * @ClassName: VersionUtil
 * @Description: 版本信息工具类 获取本地程序版本号。 如果在当前用户目录下有记录，以当前用户记录为准。 如果当前用户目录下无记录，拿到当前{home}目录下的jar包进行记录。
 *               如果拿不到该记录，那么生成一次MD5值并与远程服务器进行对比。
 * @author renzx
 * @date 2016年10月10日
 */
public class VersionUtil {
    public static Version getVersionByXML() {
        return null;

    }

    public Version getVersionByJar() {
        return null;

    }

    public Version getVersionByUser() {
        return null;

    }

    public Version getVersionByCompareFile() {
        return null;

    }

    public static void ModifyVersionXML() {
        String userHome = FileUtils.getUserDirectoryPath();
        String versionParent = userHome + File.pathSeparator + ".updater-java";
        File versionFile = FileUtils.getFile(versionParent, "version.xml");
        if (!versionFile.exists()) {
            try {
                createInitVersionFile(versionFile);
                FileUtils.touch(versionFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private static void createInitVersionFile(File versionFile) {
        Version v = new Version();
        v.setTag("0.0.0.1");
        v.setDesp("程序初始化版本");

        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(versionFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = DocumentHelper.createDocument();

        // 创建root
        Element root = document.addElement("version");
        root.addAttribute("datetime", DateTime.now().toString("yyyy-MM-dd'T'HH:mm:ss"));

        // 生成root的一个接点
        Element paramTag = root.addElement("tag");
        paramTag.addText(v.getTag());
        Element paramDesp = root.addElement("description");
        paramDesp.addText(v.getDesp());
        Element paramDt = root.addElement("updateTime");
        paramDt.addText(new DateTime(v.getUpdateTime()).toString("yyyy-MM-dd'T'HH:mm:ss"));

        // 创建字符串缓冲区
        // StringWriter stringWriter = new StringWriter();
        // 设置文件编码
        OutputFormat xmlFormat = new OutputFormat();
        xmlFormat.setEncoding("UTF-8");
        // 设置换行
        xmlFormat.setNewlines(true);
        // 生成缩进
        xmlFormat.setIndent(true);
        // 使用4个空格进行缩进, 可以兼容文本编辑器
        xmlFormat.setIndent("    ");

        // 创建写文件方法
        XMLWriter xmlWriter = new XMLWriter(fileWriter, xmlFormat);
        // 写入文件
        try {
            xmlWriter.write(document);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 关闭
        try {
            xmlWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
