package com.xiumei.tank;

import com.xiumei.tank.abstractfactory.BaseTank;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 21:42 2020/7/13
 * @Version: 1.0
 * @Description: 默认开火策略
 * 设计成单例模式，防止每次执行时候都需要创建该对象
 **/
public class DefaultFireStrategy implements FireStrategy {

    /**
     * 定义开火策略对象，将其定义为私有化对象，防止外部直接访问
     */
    private static volatile FireStrategy INSTANCE; // 将其修饰为 volatile，防止指令重排。杜绝对象还未初始化完成，就将其返回的现象。

    /**
     * 私有的构造方法，防止外部创建对象
     */
//    private DefaultFireStrategy() {}

    /**
     * 公开的获取实例对象的方法
     * 双重检查
     * @return
     */
    public static FireStrategy getInstance() {
        if(INSTANCE == null) {
            synchronized (DefaultFireStrategy.class) {
                if(INSTANCE == null) {
                    INSTANCE = new DefaultFireStrategy();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 开火
     *
     * @param t
     */
    @Override
    public void fire(BaseTank t) {
        int bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        new Bullet(bX, bY, t.dir, t.getGroup(), t.tf);

        if (t.getGroup() == Group.GOOD) {
            // 函数式接口，接口中只有一个方法，可以使用 lambda 表达式
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
