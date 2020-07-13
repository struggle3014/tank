package com.xiumei.tank.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 23:06 2020/7/12
 * @Version: 1.0
 * @Description: 图片工具类
 **/
public class ImageUtil {

    /**
     * 旋转图片
     * @param bufferImage
     * @param degree
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferImage, final int degree) {
        int w = bufferImage.getWidth();
        int h = bufferImage.getHeight();
        int type = bufferImage.getColorModel().getTransferType();
        // 修复错误： java.lang.IllegalArgumentException: Unknown image type 0
        if(type == 0) {
            type = 5;
        }
        BufferedImage img;
        Graphics2D graphics2D;
        (graphics2D = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.rotate(Math.toRadians(degree), w/2, h/2);
        graphics2D.drawImage(bufferImage, 0, 0, null);
        graphics2D.dispose();
        return img;
    }

}
