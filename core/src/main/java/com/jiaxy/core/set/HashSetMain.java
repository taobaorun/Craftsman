package com.jiaxy.core.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Title: <br>
 * <p>
 * Description: <br>
 * </p>
 *
 * @since 2016/03/10 10:14
 */
public class HashSetMain {

    public static void main(String[] args){

        Set<String> sets = new HashSet<String>();
        sets.add("A");

        for (String alphabet : sets){
            if (alphabet.equals("B")){
                break;
            }
        }
        Iterator<String> iterator = sets.iterator();
        while (iterator.hasNext()){
            String alphabet = iterator.next();
            if (alphabet.equals("B")){
                break;
            }
        }
    }

}
