package com.jiaxy.core.collection;

import java.util.TreeMap;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @author <a href=mailto:taobaorun@gmail.com>wutao</a>
 *
 * @since 2016/06/03 20:05
 */
public class Treemap {

    public static void main(String[] args){
        TreeMap<Integer,Integer> treeMap = new TreeMap<Integer, Integer>();
        treeMap.put(1,1);
        treeMap.put(2,1);
        treeMap.put(4,1);
        treeMap.put(5,1);
        System.out.println(treeMap.floorEntry(6));
        System.out.println(treeMap.floorKey(6));
        System.out.println(treeMap.floorEntry(3));
    }
}
