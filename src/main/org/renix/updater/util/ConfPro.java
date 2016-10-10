package org.renix.updater.util;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

/**
 * @ClassName: ConfPro
 * @Description: 读取配置文件-新
 * @author renzx
 * @date 2016年10月10日
 */
@Sources({"file:conf/config.properties"})
public interface ConfPro extends Config {
    String url();

    String rootDir();

    String bootFile();
}
