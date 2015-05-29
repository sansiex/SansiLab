package com.dianping.sansi.sansilab.utils;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * Created by lenovo on 2014/6/28.
 */
public class StringHelper {
    public static String join(Collection<?> col, String delimiter, String prefix, String suffix){
        if(col==null){
            return null;
        }

        StringJoiner sj=new StringJoiner(delimiter,prefix,suffix);
        for(Object obj:col){
            sj.add(obj.toString());
        }
        return sj.toString();
    }

    public static String join(Object[] arr, String delimiter, String prefix, String suffix){
        if(arr==null){
            return null;
        }

        StringJoiner sj=new StringJoiner(delimiter,prefix,suffix);
        for(Object obj:arr){
            sj.add(obj.toString());
        }
        return sj.toString();
    }

    public static String join(Collection<?> col, String delimiter){
        return join(col,delimiter,"","");
    }

    public static String join(Object[] col, String delimiter){
        return join(col,delimiter,"","");
    }

    public static String join(Collection<?> col){
        return join(col,",","","");
    }

    public static String join(Object[] col){
        return join(col,",","","");
    }
}
