package com.jiaxy.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Title: <br>
 * <p/>
 * Description: <br>
 * <p/>
 *
 *
 * @since 2015/06/17 16:34
 */
public class AllofMain {

    public static void main(String[] args){
        CompletableFuture<String>[] completableFutures = new CompletableFuture[5];
        for ( int i = 0 ;i < 5 ;i++){
            completableFutures[i] = new CompletableFuture<String>();
            if ( i == 1){
                completableFutures[i].complete("----test---"+i);
            } else {
                //will hold
                completableFutures[i] = new CompletableFuture<>();
            }
        }
        //allof 所有的执行完才执行
        CompletableFuture<List> future = CompletableFuture.allOf(completableFutures).thenApply( v ->{
            System.out.println("---"+v);
            return new ArrayList();
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
