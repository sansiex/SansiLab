package com.sansilab.codejam.util;

/**
 * Created by zuhai.jiang on 2016/7/10.
 */
public class CollectionUtil {
    public static void swap(Object[] arr, int a, int b){
        Object t=arr[a];
        arr[a]=arr[b];
        arr[b]=t;
    }

    public static void swap(double[] arr, int a, int b){
        double t=arr[a];
        arr[a]=arr[b];
        arr[b]=t;
    }

    public static double time(double[] arr){
        double res=1;
        for (double v:arr){
            res*=v;
        }
        return res;
    }
}
