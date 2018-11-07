package com.jiaxy.core.string;


public class StringBuilderMain {

    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        String a = "abc";
        sb.append(a,0,0).append("_");
        System.out.println(sb.toString());
    }
}