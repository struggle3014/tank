package com.xiumei.tank;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 12:54 2020/7/15
 * @Version: 1.0
 * @Description: 游戏中的墙
 **/
public class Wall extends GameObject {

    int weight, height;

    public Rectangle rect; // 代表墙的方块

    public Wall(int x, int y, int weight, int height) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.height = height;

        this.rect = new Rectangle(x, y, weight, height);

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, weight, height);
        g.setColor(c);
    }
}
