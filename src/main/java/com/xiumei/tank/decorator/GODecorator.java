package com.xiumei.tank.decorator;

import com.xiumei.tank.GameObject;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 16:20 2020/7/15
 * @Version: 1.0
 * @Description: GameObject 装饰器
 **/
public abstract class GODecorator extends GameObject {

    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }

}
