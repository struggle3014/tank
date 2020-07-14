package com.xiumei.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 22:20 2020/7/14
 * @Version: 1.0
 * @Description: 门面（外观）设计模式
 * 所有实体类的大管家
 **/
public class GameModel {

    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this); // 坦克
    java.util.List<Bullet> bullets = new ArrayList<>(); // 子弹容器
    java.util.List<Tank> enemyTanks = new ArrayList<>(); // 敌方坦克容器
    List<Explode> explodes = new ArrayList<>(); // 爆炸容器

    public GameModel() {

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for(int i=0; i<initTankCount; i++) {
            this.enemyTanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, this));
        }

    }

    public void paint(Graphics g) {
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

    public Tank getMyTank() {
        return myTank;
    }
}
