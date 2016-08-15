package org.renix.updater.filehandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.joda.time.DateTime;
import org.renix.updater.bean.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteLocalVersion2File {

    private static Logger LOGGER = LoggerFactory.getLogger(WriteLocalVersion2File.class);


    public static void write(Version v, Integer release) {
        String userHome = FileUtils.getUserDirectoryPath();
        String versionParent = userHome + File.separator + ".updater-java";
        File parentFile = new File(versionParent);
        try {
            FileUtils.forceMkdir(parentFile);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        File versionFile = FileUtils.getFile(versionParent, "version.xml");
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(versionFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        write2File(fileWriter, v,release);
    }


    private static void write2File(Writer fileWriter, Version v, Integer release) {
        Document document = DocumentHelper.createDocument();

        // 创建root
        Element root = document.addElement("version");
        root.addAttribute("datetime", DateTime.now().toString("yyyy-MM-dd'T'HH:mm:ss"));

        Element paramTag = root.addElement("tag");
        paramTag.addText(v.getTag());
        Element paramDesp = root.addElement("description");
        paramDesp.addText(v.getDesp());
        Element paramSkip = root.addElement("skip");
        paramSkip.addText(release+"");

        // 为节点添加属性
        // param.addAttribute("key", "sys.username");
        // 为节点添加文本, 也可以用addText()
        // param.addCDATA("中国");
        // param.addText("中国");

        // 创建字符串缓冲区
        StringWriter stringWriter = new StringWriter();
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
        // 输出xml

    }


}
