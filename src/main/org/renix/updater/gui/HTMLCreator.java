package org.renix.updater.gui;

import org.renix.updater.bean.Version;

/**
 * @ClassName: HTMLCreator
 * @Description: 更新说明板块格式化工具
 * @author renzx
 * @date 2016年10月10日
 */
public class HTMLCreator {

    public static String getList(Version v) {
        StringBuilder data = new StringBuilder();
        data.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
        data.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n");
        data.append("  <head>\n");
        data.append("    <title></title>\n");
        data.append("    <style type=\"text/css\">\n");
        data.append("      .jupentry { margin: 0px 4px 2px 4px; }\n");
        data.append("      .version { padding:4px 4px 4px 4px; margin: 5px 10px 5px 10px; background: #d2e6d2; font-weight: bold; }\n");
        data.append("      .info { margin: 0px 10px 16px 12px; }\n");
        data.append("    </style>\n");
        data.append("  </head>\n");
        data.append("  <body>\n");

        data.append("    <div class=\"jupentry\">\n");
        data.append("      <p class=\"version\">").append("版本Tag").append(": ")
                .append(v.getRelease()).append("</p>\n");
        data.append("      <p class=\"info\">").append("更新日志").append(": ").append(v.getDesp())
                .append("</p>\n");
        data.append("    </div>\n");
        data.append("  </body>\n</html>\n");
        return data.toString();
    }
}
