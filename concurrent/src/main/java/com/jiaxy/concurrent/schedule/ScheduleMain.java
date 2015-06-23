package com.jiaxy.concurrent.schedule;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @since 2015/04/15 09:03
 */
public class ScheduleMain {

    public static void main(String[] args) throws InterruptedException {

        /*ScheduledExecutorService sheduler = Executors.newSingleThreadScheduledExecutor();
        sheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        },2,2, TimeUnit.SECONDS);*/

        ScheduledExecutorService scheduleFixRate = scheduleFixRate(1000,10);
        Thread.sleep(2000);
        scheduleFixRate.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId()+"-----------------execute-------------"+new Date());
            }
        });

    }


    /**
     * scheduleAtFixedRate 如果方法的执行时间超过period ，方法执行完成后，立即执行下次调用
     *
     */
    public static ScheduledExecutorService scheduleFixRate(int period,final int sleepTime){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId()+"===="+new Date());
                try {
                    Thread.sleep(sleepTime);
                    //System.out.println("after---" + new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, period, TimeUnit.MILLISECONDS);
        return scheduler;
    }





}
