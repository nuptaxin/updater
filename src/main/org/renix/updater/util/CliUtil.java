package org.renix.updater.util;

import java.io.File;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class CliUtil {
    @SuppressWarnings("unchecked")
    public static Long parse(String[] args) {
        Options options = new Options();
        options.addOption("h", "help" , false, "显示帮助信息");
        options.addOption("u", "update" , true, "执行更新 URL APPHOME PROGRAMENTRY");
        options.addOption("m", "md5" , true, "生成目录下所有文件md5信息 APPHOME [DESTDIR]");

        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        CommandLine commandLine = null;
        CommandLineParser parser = new PosixParser();
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption('h')) {
                // 打印使用帮助
                hf.printHelp("java -jar updater.jar", options, true);
                return 0l;
            }else if (commandLine.hasOption('u')) {
                String[] strs = commandLine.getOptionValues("u");
                List<String> argList = commandLine.getArgList();
                if(argList==null||argList.size()!=2){
                    hf.printHelp("参数数量错误!", options,true);
                }else{
                    ConfigUtil.xmlUrl=strs[0];
                    ConfigUtil.programEntry=argList.get(1);
                    ConfigUtil.appHome=argList.get(0);
                    ConfigUtil.backupDir=argList.get(0)+File.separator+"updater-backup";
                    ConfigUtil.updateTmpDir=argList.get(0)+File.separator+"updater-update";
                }
                return 1l;
                
            }
            else if (commandLine.hasOption('m')) {
                String[] strs = commandLine.getOptionValues("m");
                List<String> argList = commandLine.getArgList();
                if(argList!=null&&argList.size()>1){
                    hf.printHelp("参数数量错误!", options,true);
                }else{
                    ConfigUtil.appHome=strs[0];
                    if(argList!=null&&argList.size()==1){
                        ConfigUtil.md5Dest=argList.get(0)+File.separator+"md5.xml";
                    }
                    else{
                        ConfigUtil.md5Dest=strs[0]+File.separator+"md5.xml";
                    }
                }
                return 2l;
                
            }else{
                hf.printHelp("java -jar updater.jar", options, true);
                return 0l;
            }
        } catch (ParseException e) {
            hf.printHelp("java -jar updater.jar", options, true);
            return 0l;
        }
    }
    public static void main(String[] args) {
        parse(args);
    }
}
