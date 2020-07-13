package com.xiumei.tank;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 19:41 2020/7/12
 * @Version: 1.0
 * @Description:
 **/
public class ImageTest {

    @Test
    public void test() {

        try {
//            BufferedImage image = ImageIO.read(new File(""));

//            BufferedImage image2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/bulletD.gif"));
            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            System.out.println(image2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
