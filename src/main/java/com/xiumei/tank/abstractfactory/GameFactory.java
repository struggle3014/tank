package com.xiumei.tank.abstractfactory;

import com.xiumei.tank.Dir;
import com.xiumei.tank.Group;
import com.xiumei.tank.TankFrame;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 14:05 2020/7/14
 * @Version: 1.0
 * @Description: 游戏的抽象工厂
 **/
public abstract class GameFactory {

    /**
     * 坦克创建抽象方法
     * @param x
     * @param y
     * @param dir
     * @param group
     * @param tf
     * @return
     */
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);

    /**
     * 爆炸创建抽象方法
     * @param x
     * @param y
     * @param tf
     * @return
     */
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);

    /**
     * 子弹创建抽象方法
     * @param x
     * @param y
     * @param dir
     * @param group
     * @param tf
     * @return
     */
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);

}
