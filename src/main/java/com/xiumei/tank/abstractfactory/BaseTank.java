package com.xiumei.tank.abstractfactory;

import com.xiumei.tank.Dir;
import com.xiumei.tank.Group;
import com.xiumei.tank.ResourceMgr;
import com.xiumei.tank.TankFrame;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 14:10 2020/7/14
 * @Version: 1.0
 * @Description: 坦克抽象产品类
 **/
public abstract class BaseTank {

    protected int x, y; // 坐标
    public Dir dir; // 移动方向
    public final static int SPEED = 10; // 坦克速度
    public boolean moving = true; // 是否移动
    public final static int WIDTH = ResourceMgr.goodTankU.getWidth(); // 坦克宽度
    public final static int HEIGHT = ResourceMgr.goodTankU.getHeight(); // 坦克高度
    // 坦克 rect，用于后续碰撞检测
    public Rectangle rect = new Rectangle();
    public boolean living = true; // 坦克是否存活
    public Group group = Group.BAD; // 游戏分组
    public TankFrame tf;

    public BaseTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    /**
     * 画图的抽象方法
     * @param g
     */
    public abstract void paint(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public abstract void die();
}
