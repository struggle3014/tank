package com.xiumei.tank.decorator;

import com.xiumei.tank.GameObject;

import java.awt.*;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 16:21 2020/7/15
 * @Version: 1.0
 * @Description: 横线装饰器
 **/
public class LineDecorator extends GODecorator {

    public LineDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        // 子弹的位置是动态的，需要保留最新的位置，以便能够获取
        this.x = go.x;
        this.y = go.y;
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(go.x, go.y, go.x + go.getWidth(), go.y + go.getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
