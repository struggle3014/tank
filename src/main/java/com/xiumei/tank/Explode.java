package com.xiumei.tank;

import com.xiumei.tank.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 22:25 2020/7/12
 * @Version: 1.0
 * @Description: 爆炸
 **/
public class Explode extends BaseExplode {

    public static int WIDTH = ResourceMgr.explodes[0].getWidth(); // 爆炸图片宽度
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight(); // 爆炸图片高度
    private int x, y; // 坐标
    private TankFrame tf;

    private int step = 0; // 步骤

    public Explode(int x, int y, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.tf = tf;

        // 函数式接口，接口中只有一个方法，可以使用 lambda 表达式
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if(step >= ResourceMgr.explodes.length) {
            tf.explodes.remove(this);
        }
    }

}
