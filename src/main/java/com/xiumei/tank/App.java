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

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for(int i=0; i<initTankCount; i++) {
            tf.enemyTanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, tf));
        }

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }

}
