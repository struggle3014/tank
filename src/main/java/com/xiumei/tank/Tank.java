package com.xiumei.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 15:45 2020/7/12
 * @Version: 1.0
 * @Description: 坦克类
 **/
public class Tank {
    private int x, y; // 坐标
    Dir dir; // 移动方向
    private final static int SPEED = 10; // 坦克速度
    private boolean moving = true; // 是否移动
    public final static int WIDTH = ResourceMgr.goodTankU.getWidth(); // 坦克宽度
    public final static int HEIGHT = ResourceMgr.goodTankU.getHeight(); // 坦克高度
    // 坦克 rect，用于后续碰撞检测
    Rectangle rect = new Rectangle();
    private boolean living = true; // 坦克是否存活
    private Group group = Group.BAD; // 游戏分组
    TankFrame tf;
    private Random random = new Random();

    GameModel gm;

    public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    /**
     * 描绘坦克
     * @param g
     */
    public void paint(Graphics g) {
        if(!living) {
            // 坦克处于未存活状态，将该坦克移除
            gm.enemyTanks.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y , null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y , null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y , null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y , null);
                break;
            default:
                break;
        }
        move();
    }

    /**
     * 坦克移动方法
     */
    private void move() {
        if(!moving) {
            return;
        }
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
            default:
                break;
        }
        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        // 边界检测
        boundsCheck();
        // 更新 rect
        rect.x = this.x;
        rect.y = this.y;
    }

    /**
     * 边界检测
     */
    private void boundsCheck() {
        if(this.x < 0) x = 2;
        if(this.y < 30) y = 28;
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    /**
     * 随机方向调整
     */
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];

    }

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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * 发射炮弹
     */
    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH /2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT /2;

        gm.bullets.add(new Bullet(bX, bY ,this.dir, this.group, gm));
    }

    /**
     * 将坦克存活状态置为 false
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
