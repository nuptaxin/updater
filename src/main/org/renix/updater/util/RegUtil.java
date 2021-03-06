package org.renix.updater.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: RegUtil
 * @Description: 注册表工具类
 * @author renzx
 * @date 2016年10月10日
 */
public class RegUtil {
    static Logger LOGGER = LoggerFactory.getLogger(RegUtil.class);

    public static Integer getCmdExtStatus() {
        Integer enableExtensions = null;
        /*
         * try { enableExtensions = Advapi32Util.registryGetIntValue(WinReg.HKEY_CURRENT_USER,
         * "Software\\Microsoft\\Command Processor", "EnableExtensions"); } catch (Exception e) {
         * LOGGER.warn("无法读取注册表CMD命令扩展启用信息", e); }
         */
        return enableExtensions;

    }

    public static Boolean setCmdExtStatus(Integer status) {
        Boolean flag = false;
        /*
         * try { Advapi32Util.registrySetIntValue(WinReg.HKEY_CURRENT_USER,
         * "Software\\Microsoft\\Command Processor", "EnableExtensions", status); flag = true; }
         * catch (Exception e) { LOGGER.error("无法读取注册表CMD命令扩展启用信息", e); }
         */
        return flag;
    }

}
