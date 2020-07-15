package com.xiumei.tank.chainofresponsibility;

import com.xiumei.tank.GameObject;
import com.xiumei.tank.Tank;
import com.xiumei.tank.Wall;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 13:08 2020/7/15
 * @Version: 1.0
 * @Description:
 **/
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        boolean result = true;
        if(o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            if(t.rect.intersects(w.rect)) {
                t.back();
            }
        } else if(o1 instanceof Wall && o2 instanceof Tank) {
            result = collide(o2, o1);
        }
        return result;
    }
}
