package com.xiumei.tank;

/**
 * @Author: yue_zhou
 * @Email: yue_zhou@xinyan.com
 * @Date: 9:15 2020/7/12
 * @Version: 1.0
 * @Description: 认识 Frame 类
 **/
public class App {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }

}
