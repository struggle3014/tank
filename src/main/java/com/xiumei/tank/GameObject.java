package com.xiumei.tank;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 22:57 2020/7/14
 * @Version: 1.0
 * @Description: 抽象的游戏物体
 * 如坦克，炮弹，墙，爆炸等
 **/
public abstract class GameObject {

    // 坐标
    public int x, y;

    /**
     * 画图
     * @param g
     */
    public abstract void paint(Graphics g);

    /**
     * 获取宽度
     * @return
     */
    public abstract int getWidth();

    /**
     * 获取宽度
     * @return
     */
    public abstract int getHeight();

}
