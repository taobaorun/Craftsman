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

    public static void main(String[] args){

        ScheduledExecutorService sheduler = Executors.newSingleThreadScheduledExecutor();
        sheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        },2,2, TimeUnit.SECONDS);

    }
}
