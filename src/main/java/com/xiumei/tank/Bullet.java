package com.xiumei.tank;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 16:11 2020/7/12
 * @Version: 1.0
 * @Description: 子弹类
 **/
public class Bullet extends GameObject {

    // 速度
    private static final int SPEED = 10;
    // 宽度和高度
    public final static int WIDTH = ResourceMgr.bulletU.getWidth();
    public final static int HEIGHT = ResourceMgr.bulletU.getHeight();
    // 子弹 rect，用于后续碰撞检测
    public Rectangle rect = new Rectangle();
    // 坐标
    private int x, y;
    // 方向
    private Dir dir;
    // 子弹是否存活
    private boolean living = true;
    // 分组
    public Group group = Group.BAD;
    // TankFrame 引用
    public GameModel gm;

    public Bullet(int x, int y, Dir dir, Group group, GameModel tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    /**
     * 描绘子弹
     * @param g
     */
    public void paint(Graphics g) {
        if(!living) {
            gm.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y , null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y , null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y , null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y , null);
                break;
            default:
                break;
        }
        move();
    }
    /**
     * 移动方法
     */
    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        // 更新 rect
        rect.x = this.x;
        rect.y = this.y;
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    /**
     * 将子弹存活状态置为 false
     */
    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
