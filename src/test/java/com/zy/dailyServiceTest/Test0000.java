package com.zy.dailyServiceTest;

import org.apache.tomcat.jni.Time;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntPredicate;

/**
 * 练习测试类
 * @Author wangwentao
 * @Description 
 * @Date 2019/2/15 11:26
 **/
public class Test0000 {

    @Test
    public void test00() {
        IntPredicate ip = (int i) -> i % 1 == 0 ;
        ip.test(200);
    }

    @Test
    public void test01() {
        CountDownLatch cdl = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("第一条线程开启！！！");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一条线程执行完成！！！");
            cdl.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("第er条线程开启！！！");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第er条线程执行完成！！！");
            cdl.countDown();
        }).start();

        try {
            System.out.println("主线程开始 ！！！");
            cdl.await();
            System.out.println("两条线程执行完成 ？？？");
            System.out.println("主线程执行完成 ！！！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        int N = 4;
        CyclicBarrier cb = new CyclicBarrier(4,new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完预定线程后执行");
        }));

        for(int i = 0 ; i < N; i ++)
            new Thread(() -> {
                System.out.println("执行线程" + Thread.currentThread().getName() + " -- 开始");
                try {
                    Thread.sleep(5000);
                    System.out.println("执行线程" + Thread.currentThread().getName() + " -- 完成");
                    cb.await();
                    System.out.println("执行线程" + Thread.currentThread().getName() + " -- 开始执行后续操作");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
