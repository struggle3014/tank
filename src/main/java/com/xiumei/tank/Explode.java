package com.xiumei.tank;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 22:25 2020/7/12
 * @Version: 1.0
 * @Description:
 **/
public class Explode {

    public static int WIDTH = ResourceMgr.explodes[0].getWidth(); // 爆炸图片宽度
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight(); // 爆炸图片高度
    private int x, y; // 坐标
    private GameModel gm;

    private int step = 0; // 步骤

    public Explode(int x, int y, GameModel tf) {
        super();
        this.x = x;
        this.y = y;
        this.gm = tf;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if(step >= ResourceMgr.explodes.length) {
            gm.explodes.remove(this);
        }
    }

}
