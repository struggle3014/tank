package com.xiumei.tank.strategy;

import com.xiumei.tank.Tank;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 21:41 2020/7/13
 * @Version: 1.0
 * @Description: 开火的策略
 **/
public interface FireStrategy {

    /**
     * 开火
     * @param t
     */
    void fire(Tank t);

}
