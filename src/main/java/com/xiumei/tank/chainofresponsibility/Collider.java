package com.xiumei.tank.chainofresponsibility;

import com.xiumei.tank.GameObject;

/**
 * 碰撞器，思想来源 Comparator
 * 策略模式
 */
public interface Collider {

//    void collide(GameObject o1, GameObject o2);

    /**
     * 碰撞方法
     * 为了配合 ColliderChain 的使用，链条中可能存在
     * 链条中的某个实例操作完成后，不需要处理链条上后续内容。
     * 将 collide 方法的返回值改成 boolean 类型，以实现上述的流程控制。
     * @param o1
     * @param o2
     * @return
     */
    boolean collide(GameObject o1, GameObject o2);

}
