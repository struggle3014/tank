package com.xiumei.tank.cor;

import com.xiumei.tank.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 23:36 2020/7/14
 * @Version: 1.0
 * @Description: 子弹坦克碰撞器，策略模式，仿照 Comparator compare 方法
 **/
public class BulletWallCollider implements Collider {

    /**
     * 两物体相撞，子弹和坦克相撞
     * 碰撞的逻辑由 Collider 碰撞器实现
     * @param o1
     * @param o2
     */
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        boolean result = true;
        if(o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            if(b.rect.intersects(w.rect)) {
                b.die();
            }
        } else if(o1 instanceof Wall && o2 instanceof Bullet) {
            result = collide(o2, o1);
        }
        return result;
    }
}
