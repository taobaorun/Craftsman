package com.jiaxy.core.bit;

/**
 * Title: <br>
 * <p/>
 * Description: <br>
 * <p/>
 *
 */
public class BitOperation {


    public static void main(String[] args){
        System.out.println(-(1 << 5));
        System.out.println( (byte)(0xa0 | 1));
        BitOperation bo = new BitOperation();
        System.out.println(System.identityHashCode(bo));
        System.out.println(System.identityHashCode(bo) % 100);
        int hash = System.identityHashCode(bo) % 100 & 100;
        System.out.println(hash);
    }
}
