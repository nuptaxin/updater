package org.renix.updater.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 读取配置文件
 * 
 * @author renzx
 */
public class ConfigUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ConfigUtil.class);

    public static String xmlUrl;
    public static String appHome;
    public static String backupDir;
    public static String updateTmpDir;
    public static String programEntry;

    @PostConstruct
    public static void initConfig() {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration();
            config.setEncoding("UTF-8");
            config.setFileName("update.properties");
            config.load();

            LOGGER.info("读取配置文件...");
            Class<ConfigUtil> clazz = ConfigUtil.class;
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 属性必须是公有静态
                if (field.getModifiers() != (Modifier.PUBLIC | Modifier.STATIC)) {
                    continue;
                }
                String fieldName = field.getName();
                String value = config.getString(fieldName);
                LOGGER.info(fieldName + " = " + value);

                if (value == null || value.trim().equals("")) {
                    LOGGER.warn(fieldName + "没有配置。");
                }
                Class<?> clazz0 = field.getType();
                try {
                    if (clazz0 == String.class) {
                        field.set(null, value);
                    } else if (clazz0 == int.class || clazz0 == Integer.class) {
                        field.setInt(null, Integer.parseInt(value));
                    } else if (clazz0 == long.class || clazz0 == Long.class) {
                        field.setLong(null, Long.parseLong(value));
                    } else if (clazz0 == float.class || clazz0 == Float.class) {
                        field.setFloat(null, Float.parseFloat(value));
                    } else if (clazz0 == double.class || clazz0 == Double.class) {
                        field.setDouble(null, Double.parseDouble(value));
                    } else if (clazz0 == boolean.class || clazz0 == Boolean.class) {
                        field.setBoolean(null, Boolean.parseBoolean(value));
                    }
                } catch (Exception e) {
                    LOGGER.error(fieldName + "配置出错", e);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("读取配置出错", ex);
        }
    }
}
