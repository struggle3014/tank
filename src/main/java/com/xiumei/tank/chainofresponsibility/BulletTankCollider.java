package com.xiumei.tank.chainofresponsibility;

import com.xiumei.tank.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 23:36 2020/7/14
 * @Version: 1.0
 * @Description: 子弹坦克碰撞器，策略模式，仿照 Comparator compare 方法
 **/
public class BulletTankCollider implements Collider {

    /**
     * 两物体相撞，子弹和坦克相撞
     * 碰撞的逻辑由 Collider 碰撞器实现
     * @param o1
     * @param o2
     */
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        boolean result = true;
        if(o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            // 判断是否为主坦克，如果为主坦克
            // 判断坦克和子弹是否为同一方，如果为同一方则无需做子弹和坦克碰撞后续死亡逻辑。
            if(b.group == t.getGroup()) {
                return result;
            }
            if(b.rect.intersects(t.rect)) {
                t.die();
                b.die();
                int eX = t.getX() + Tank.WIDTH/2 - Explode.WIDTH /2;
                int eY = t.getY() + Tank.HEIGHT/2 - Explode.HEIGHT /2;
                new Explode(eX, eY);
                result = false;
            }
        } else if(o1 instanceof Tank && o2 instanceof Bullet) {
            result = collide(o2, o1);
        }
        return result;
    }
}
