package com.xiumei.tank;

import com.xiumei.tank.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 20:21 2020/7/12
 * @Version: 1.0
 * @Description: 资源管理类
 **/
public class ResourceMgr {

    // 坦克图片
    public static BufferedImage goodTankU, goodTankR, goodTankD, goodTankL;
    // 坦克图片
    public static BufferedImage badTankU, badTankR, badTankD, badTankL;
    // 子弹图片
    public static BufferedImage bulletU, bulletR, bulletD, bulletL;
    // 爆炸图片
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            // 好坦克图片加载
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankL = ImageUtil.rotateImage(goodTankU, 270);
            // 坏坦克图片加载
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankL = ImageUtil.rotateImage(badTankU, 270);
            // 子弹图片加载
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletL = ImageUtil.rotateImage(bulletU, 270);
            // 爆炸图片初始化
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
