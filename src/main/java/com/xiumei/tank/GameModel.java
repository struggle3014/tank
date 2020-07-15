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
 * @Description: 所有实体类的大管家
 * 门面（外观）设计模式，相对于 TankFrame 而言。
 * 调停者模式，相对于众多的实体而言，包括坦克，子弹，墙，爆炸。
 * 单例模式，由于 GameModel 只需要单个实例即可，
 * 其他类在使用时，不需要以类属性或方法参数的形式提供，
 * 可以更好地履行低耦合、高内聚的特性。
 *
 * bug-2020-07-15：GameModel 实例化时，需要实例化 Tank，而实例化 Tank 时，需要实例化 GameModel，产生互相依赖的问题。
 * bug-2020-07-15 修复：其他对象实例化全都放在 init 方法中。
 **/
public class GameModel {

    // 懒汉式单例模式
    private static final GameModel INSTANCE = new GameModel();

    Tank myTank; // 主坦克

    // 碰撞器链表
    ColliderChain chain = new ColliderChain();

    private List<GameObject> objects = new ArrayList<>();

    static {
        INSTANCE.initialize();
    }

    /**
     * 移除 Object
     * @param go
     */
    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    /**
     * 添加 Object
     * @param go
     */
    public void add(GameObject go) {
        this.objects.add(go);
    }

    /**
     * 构造方法私有化，防止外部创建对象
     */
    private GameModel() {}

    /**
     * 初始化方法
     */
    private void initialize() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化主坦克
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);

        // 初始化敌方坦克
        for(int i=0; i<initTankCount; i++) {
            new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD);
        }

        // 初始化墙
        new Wall(150, 150, 200, 50);
        new Wall(550, 150, 200, 50);
        new Wall(300, 300, 50, 200);
        new Wall(550, 300, 50, 200);
    }

    /**
     * 提供公开的获取 GameModel 对象的方法
     * @return
     */
    public static GameModel getInstance() {
        return INSTANCE;
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
