package com.xiumei.tank.cor;

import com.xiumei.tank.Bullet;
import com.xiumei.tank.GameObject;
import com.xiumei.tank.Tank;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 23:36 2020/7/14
 * @Version: 1.0
 * @Description: 两个 Tank 相撞器
 **/
public class TankTankCollider implements Collider {

    /**
     * 两物体相撞
     * 如果是两个坦克相撞，碰撞逻辑为让其回到上次的位置
     * @param o1
     * @param o2
     */
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        boolean result = true;
        if(o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if(t1.rect.intersects(t2.rect)) {
                t1.back();
                t2.back();
                // 此处用于都需要返回 true，因为坦克相撞，坦克并没有死
                // 需要继续处理 ColliderChain 的后续逻辑。
                // result = false;
            }
        }
        return result;
    }
}
