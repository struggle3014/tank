package com.xiumei.tank.abstractfactory;

import com.xiumei.tank.*;
import com.xiumei.tank.util.FireStrategyUtil;

import java.awt.*;
import java.util.Random;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 15:45 2020/7/12
 * @Version: 1.0
 * @Description: 方形坦克类产品类
 **/
public class RectTank extends BaseTank {
//    private int x, y; // 坐标
//    Dir dir; // 移动方向
//    private final static int SPEED = 10; // 坦克速度
//    private boolean moving = true; // 是否移动
//    public final static int WIDTH = ResourceMgr.goodTankU.getWidth(); // 坦克宽度
//    public final static int HEIGHT = ResourceMgr.goodTankU.getHeight(); // 坦克高度
//    // 坦克 rect，用于后续碰撞检测
//    public Rectangle rect = new Rectangle();
//    private boolean living = true; // 坦克是否存活
//    private Group group = Group.BAD; // 游戏分组
//    TankFrame tf;
    private Random random = new Random();

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super(x, y, dir, group, tf);
//        super();
//        this.x = x;
//        this.y = y;
//        this.dir = dir;
//        this.group = group;
//        this.tf = tf;
//
//        rect.x = this.x;
//        rect.y = this.y;
//        rect.width = WIDTH;
//        rect.height = HEIGHT;
    }

    /**
     * 描绘坦克
     * @param g
     */
    public void paint(Graphics g) {
        if(!living) {
            // 坦克处于未存活状态，将该坦克移除
            tf.enemyTanks.remove(this);
            return;
        }
        // 方形坦克绘制
        Color c = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
        g.fillRect(x, y, 40, 40);
        g.setColor(c);
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
            this.fire(FireStrategyUtil.getBadFireStrategy());
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
        if(this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 2) x = TankFrame.GAME_WIDTH - RectTank.WIDTH - 2;
        if(this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2;
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
     * 尽量不要将不属于该类的属性添加进来，
     * 会使得对象变得臃肿，且代码不清晰。尽量使用局部变量或传参方式。
     * @param fs
     */
    public void fire(FireStrategy fs) {
        fs.fire(this);
//        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH /2;
//        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT /2;
//
//        tf.bullets.add(new Bullet(bX, bY ,this.dir, this.group, this.tf));
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
