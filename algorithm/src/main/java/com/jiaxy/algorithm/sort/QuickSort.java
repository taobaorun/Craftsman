package com.jiaxy.algorithm.sort;

/**
 * Description: <br/>
 * <p/>
 * <br/>
 * @Date: 2016/12/01 11:48
 */
public class QuickSort {

    public static void main(String[] args){
        Integer[] arr = new Integer[]{3,1,9,3,2,11,0};
        Comparable c = select(arr,5);
        System.out.println(c);
        System.out.println("=========================================");
        sort(arr,0,arr.length -1);
        for (int i = 0 ;i < arr.length ;i++){
            System.out.print(arr[i] +" ");
        }



    }


    public static void sort(Comparable[] arr,int begin,int end){
        if(begin >= end){
            return;
        }
        int pivot = partition(arr,begin,end);
        sort(arr,begin,pivot -1);
        sort(arr,pivot + 1,end);
    }



    public static int partition(Comparable[] arr,int begin,int end){
        int i = begin;
        int j = end + 1;
        Comparable value = arr[begin];
        while (true){
            while (arr[++i].compareTo(value) < 0 ){
                if (i == end){
                    break;
                }
            }
            while (arr[--j].compareTo(value) > 0){
                if (j == begin){
                    break;
                }
            }
            if (i >= j){
                break;
            }
            swap(arr,i,j);
        }
        swap(arr,begin,j);
        return j;
    }


    public static void swap(Comparable[] arr,int i ,int j){
        Comparable obj = arr[i];
        arr[i] = arr[j];
        arr[j] = obj;
    }




    public static Comparable select(Comparable[] arr,int k){
        int begin = 0;
        int end = arr.length -1;
        while (begin < end){
            int pivot = partition(arr,begin,end);
            if (pivot > k){
                end = pivot -1;
            } else if (pivot < k){
                begin = pivot + 1;
            } else {
                return arr[k];
            }
        }
        return arr[begin];

    }
}
