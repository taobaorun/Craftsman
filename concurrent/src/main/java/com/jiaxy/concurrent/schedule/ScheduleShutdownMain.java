package com.jiaxy.concurrent.schedule;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @since 2015/12/18 17:32
 */
public class ScheduleShutdownMain {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);


    public static void main(String[] args){
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("------working----"+new Date());
            }
        },2000, TimeUnit.MILLISECONDS);
        //shutdown 对已经提交还未执行的任务无法取消
        executorService.shutdown();
        System.out.println("shutdown method invoked");
        synchronized(ScheduleShutdownMain.class){
            while (true){
                try {
                    ScheduleShutdownMain.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
