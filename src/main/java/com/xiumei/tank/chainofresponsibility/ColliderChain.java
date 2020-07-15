package com.xiumei.tank.chainofresponsibility;

import com.xiumei.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 10:53 2020/7/15
 * @Version: 1.0
 * @Description: 责任链模式
 * 碰撞器责任链，类比 FilterChain
 **/
public class ColliderChain implements Collider {

    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new BulletWallCollider());
        add(new TankWallCollider());
    }

    /**
     * 添加 Collider
     * @param c
     */
    public void add(Collider c) {
        colliders.add(c);
    }

    /**
     * 添加 ColliderChain，将多个链条组合在一起
     * @param chain
     */
    public void add(ColliderChain chain) {
        colliders.addAll(chain.getColliders()); // 将其他链条中的 colliders 添加到本链条中
    }

    /**
     * ColliderChain 链条碰撞实现方法
     * 底层调用的是 Collider 的 collide 方法
     * @param o1
     * @param o2
     */
    public boolean collide(GameObject o1, GameObject o2) {
        boolean result = true;
        for (int i = 0; i < colliders.size(); i++) {
            if(colliders.get(i).collide(o1, o2)) {
                result = false;
            }
        }
        return result;
    }

    public List<Collider> getColliders() {
        return colliders;
    }
}
