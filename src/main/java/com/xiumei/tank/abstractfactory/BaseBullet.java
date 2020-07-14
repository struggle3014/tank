package com.xiumei.tank.abstractfactory;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 14:10 2020/7/14
 * @Version: 1.0
 * @Description: 子弹抽象产品类
 **/
public abstract class BaseBullet {

    /**
     * 碰撞检测抽象方法
     * @param tank
     */
    public abstract void collideWidth(BaseTank tank);

    /**
     * 画图的抽象方法
     * @param g
     */
    public abstract void paint(Graphics g);
}
