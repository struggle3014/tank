package com.xiumei.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 9:39 2020/7/12
 * @Version: 1.0
 * @Description: Tank Frame
 **/
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this); // 坦克
    List<Bullet> bullets = new ArrayList<>(); // 子弹容器
    List<Tank> enemyTanks = new ArrayList<>(); // 敌方坦克容器
    List<Explode> explodes = new ArrayList<>(); // 爆炸容器
//    Explode e = new Explode(100, 100, this);

    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960; // 像素

    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);

        // 添加键盘监听事件
        this.addKeyListener(new MyKeyListener());

        // 添加窗口监听事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // 系統退出
            }
        });
    }

    Image offScreenImage = null; // 内存中的图片
    /**
     * 解决双缓冲问题
     * 在内存中创建与游戏界面同样大小的图片，在该图片上画好对应的图像，
     * 一次性把图片画到屏幕上，就不会出现画面闪烁问题。
     * @param
     */
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("paint...");
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌方坦克数量：" + enemyTanks.size(), 10, 80);
        g.drawString("爆炸数量：" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        // 画子弹
        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        // 画敌方坦克
        for(int i = 0; i < enemyTanks.size(); i++) {
            enemyTanks.get(i).paint(g);
        }
//         // 下述方法在遍历过程中对元素进行删除操作会出现问题
//        for (Bullet bullet : bullets) {
//            bullet.paint(g);
//        }
        // 画出爆炸图片
        for(int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        // 碰撞检测：子弹和坦克
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyTanks.size(); j++) {
                bullets.get(i).collideWidth(enemyTanks.get(j));
            }
        }
    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch(key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setAppTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch(key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                // 按住 Ctrl 键，发出一颗子弹
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setAppTankDir();
        }

        /**
         * 设置坦克方向
         */
        private void setAppTankDir() {
            if(!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }

}
