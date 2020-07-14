package com.xiumei.tank.abstractfactory;

import com.xiumei.tank.Audio;
import com.xiumei.tank.ResourceMgr;
import com.xiumei.tank.TankFrame;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 14:20 2020/7/14
 * @Version: 1.0
 * @Description:
 **/
public class RectExplode extends BaseExplode {

    public static int WIDTH = ResourceMgr.explodes[0].getWidth(); // 爆炸图片宽度
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight(); // 爆炸图片高度
    private int x, y; // 坐标
    private TankFrame tf;

    private int step = 0; // 步骤

    public RectExplode(int x, int y, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.tf = tf;

        // 函数式接口，接口中只有一个方法，可以使用 lambda 表达式
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
//        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10*step, 10*step);
        step++;
        g.setColor(c);

        if(step >= 15) {
            tf.explodes.remove(this);
        }

    }
}
