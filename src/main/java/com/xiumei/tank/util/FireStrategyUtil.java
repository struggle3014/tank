package com.xiumei.tank.util;

import com.xiumei.tank.FireStrategy;
import com.xiumei.tank.PropertyMgr;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 22:50 2020/7/13
 * @Version: 1.0
 * @Description: 开火策略工具类
 * 设计成每种策略为单例策略
 **/
public final class FireStrategyUtil {

    private static FireStrategy goodFs;
    private static FireStrategy badFs;

    /**
     * 获取好的开火策略
     * 单例模式
     * @return
     */
    public static FireStrategy getGoodFireStrategy() {
        if(goodFs == null) {
            synchronized (FireStrategyUtil.class) {
                if(goodFs == null) {
                    String goodFsStr = (String) PropertyMgr.get("goodFs");
                    try {
                        goodFs = (FireStrategy) Class.forName(goodFsStr).newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return goodFs;
    }

    /**
     * 获取坏的开火策略
     * 单例模式
     * @return
     */
    public static FireStrategy getBadFireStrategy() {
        if(badFs == null) {
            synchronized (FireStrategyUtil.class) {
                if(badFs == null) {
                    String badFsStr = (String) PropertyMgr.get("badFs");
                    try {
                        badFs = (FireStrategy) Class.forName(badFsStr).newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return badFs;
    }

}
