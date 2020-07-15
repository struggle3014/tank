package com.xiumei.tank;

import com.xiumei.tank.cor.BulletTankCollider;
import com.xiumei.tank.cor.Collider;
import com.xiumei.tank.cor.ColliderChain;
import com.xiumei.tank.cor.TankTankCollider;

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
//    java.util.List<Bullet> bullets = new ArrayList<>(); // 子弹容器
//    java.util.List<Tank> enemyTanks = new ArrayList<>(); // 敌方坦克容器
//    List<Explode> explodes = new ArrayList<>(); // 爆炸容器

    // 碰撞器链表
    ColliderChain chain = new ColliderChain();

    private List<GameObject> objects = new ArrayList<>();

    /**
     * 添加 Object
     * @param go
     */
    public void add(GameObject go) {
        this.objects.add(go);
    }

    /**
     * 移除 Object
     * @param go
     */
    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public GameModel() {

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for(int i=0; i<initTankCount; i++) {
            add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, this));
        }

    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
//        g.drawString("敌方坦克数量：" + enemyTanks.size(), 10, 80);
//        g.drawString("爆炸数量：" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        // 画子弹，坦克，墙等 GameObject 具体实现对象
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        // 互相碰撞
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                // o1.collideWith(o2) 如果这样写，当游戏中有新物体进来时，就需要修改互相之间的关系。
                // 此时，我们可以考虑使用策略设计模式，如在前面策略设计模式中的 Comparator compare(o1, o2)，
                // 为此，我们可以仿造 Comparator，创建 Collider 碰撞器
                chain.collide(o1, o2);
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
