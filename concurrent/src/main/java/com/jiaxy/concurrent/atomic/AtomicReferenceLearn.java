package com.jiaxy.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @author <a href=mailto:taobaorun@gmail.com>wutao</a>
 *
 * @since 2015/06/25 13:12
 */
public class AtomicReferenceLearn {

    public static void main(String[] args) throws InterruptedException {

        final AtomicReference<Object> atomicReference = new AtomicReference<Object>();
        atomicReference.set("testbb");
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean rs = atomicReference.compareAndSet(new String("testbb"),"test-new");
                if ( rs ){
                    System.out.println("update success");
                } else {
                    System.out.println("update failed");
                }
                rs = atomicReference.compareAndSet("testbb","test-new");
            }
        }).start();
        Thread.sleep(1000);
        System.out.println(atomicReference.get());

    }
}
