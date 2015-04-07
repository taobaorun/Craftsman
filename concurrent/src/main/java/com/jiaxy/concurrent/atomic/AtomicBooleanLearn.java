package com.jiaxy.concurrent.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanLearn {

    private AtomicBoolean flag = new AtomicBoolean(false);


    public static void main(String[] args){
        AtomicBooleanLearn learn = new AtomicBooleanLearn();
        System.out.println(learn.flag.getAndSet(true));
        System.out.println(learn.flag.getAndSet(true));
    }

}
