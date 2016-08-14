package org.renix.updater.filehandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.joda.time.DateTime;
import org.renix.updater.util.ConfigUtil;

public class CreateVersionMD5 {
    public static void main(String[] args) throws IOException {
        ConfigUtil.appHome = "E:\\dist1";
        ConfigUtil.md5Dest = "E:\\dist1\\md5.xml";
        File f = FileUtils.getFile(ConfigUtil.appHome);
        File f1 = FileUtils.getFile(ConfigUtil.md5Dest);
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(f1, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            makeXmlFile(fileWriter, f,f1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getMd5File(){
        File f = FileUtils.getFile(ConfigUtil.appHome);
        File f1 = FileUtils.getFile(ConfigUtil.md5Dest);
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(f1, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            makeXmlFile(fileWriter, f,f1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeXmlFile(Writer fileWriter, File f, File f1) throws IOException {
        Document document = DocumentHelper.createDocument();

        // 创建root
        Element root = document.addElement("dirmd5");
        root.addAttribute("datetime", DateTime.now().toString("yyyy-MM-dd'T'HH:mm:ss"));

        File[] c =f.listFiles();
        for (File file : c) {
            if (file.equals(f)||file.equals(f1))
                continue;
            if (file.isFile()) {
                // 生成root的一个接点
                Element param = root.addElement("file");
                param.addAttribute("name", file.getName());
                param.addAttribute("size", String.valueOf(FileUtils.sizeOf(file)));
                param.addAttribute("md5", DigestUtils.md5Hex(FileUtils.openInputStream(file)));
                // System.out.println(file.getAbsolutePath() + "\t"
                // + FileUtils.sizeOf(file) + "\t" +
                // DigestUtils.md5Hex(FileUtils.openInputStream(file)));
            } else if (file.isDirectory()) {
                listInnerFile(file, root);
            }
            // System.out.println();

        }

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
        xmlWriter.write(document);
        // 关闭
        xmlWriter.close();
        // 输出xml
        System.out.println(fileWriter.toString());
    }

    public static void listInnerFile(File file, Element root) throws IOException {
        // System.out.println(file.getAbsolutePath() + "\t");
        Element root1 = root.addElement("dir");
        root1.addAttribute("name", file.getName());
        File[] subDir = file.listFiles();
        for (File file1 : subDir) {
            if (file1.isFile()) {
                Element param = root1.addElement("file");
                param.addAttribute("name", file1.getName());
                param.addAttribute("size", String.valueOf(FileUtils.sizeOf(file1)));
                param.addAttribute("md5", DigestUtils.md5Hex(FileUtils.openInputStream(file1)));

                // System.out.println(file1.getAbsolutePath() + "\t"
                // + FileUtils.sizeOf(file) + "\t" +
                // DigestUtils.md5Hex(FileUtils.openInputStream(file1)));
            } else {
                listInnerFile(file1, root1);
            }
        }
    }
}
