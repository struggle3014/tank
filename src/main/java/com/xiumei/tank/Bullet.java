package com.xiumei.tank;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 16:11 2020/7/12
 * @Version: 1.0
 * @Description: 子弹类
 **/
public class Bullet {

    // 速度
    private static final int SPEED = 10;
    // 宽度和高度
    public final static int WIDTH = ResourceMgr.bulletU.getWidth();
    public final static int HEIGHT = ResourceMgr.bulletU.getHeight();
    // 子弹 rect，用于后续碰撞检测
    Rectangle rect = new Rectangle();
    // 坐标
    private int x, y;
    // 方向
    private Dir dir;
    // 子弹是否存活
    private boolean living = true;
    // 分组
    private Group group = Group.BAD;
    // TankFrame 引用
    GameModel gm;

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
            gm.bullets.remove(this);
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
     * 子弹与坦克相撞
     * @param tank
     */
    public void collideWidth(Tank tank) {
        // 判断坦克和子弹是否为同一方，如果为同一方则无需做子弹和坦克碰撞后续死亡逻辑。
        if(this.group == tank.getGroup()) {
            return;
        }
        // TODO：用一个 rect 来记录子弹的位置，下方写法容易造成内存溢出
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGHT);
        if(this.rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH /2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT /2;
            gm.explodes.add(new Explode(eX, eY, gm));
        }
    }

    /**
     * 将子弹存活状态置为 false
     */
    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
