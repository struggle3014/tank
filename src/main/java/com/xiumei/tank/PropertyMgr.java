package com.xiumei.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 13:20 2020/7/13
 * @Version: 1.0
 * @Description: 配置文件管理类
 **/
public class PropertyMgr {

    static Properties props = new Properties();

    /**
     * 静态代码块加载
     */
    static {
        try {
            // classpath 路径下寻找，ClassLoader
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性值
     * @param key
     * @return
     */
    public static Object get(String key) {
        if(props == null) {
            return null;
        }
        return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }

}
