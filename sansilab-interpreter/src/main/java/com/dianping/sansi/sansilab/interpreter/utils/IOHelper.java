package com.dianping.sansi.sansilab.interpreter.utils;

import java.io.*;

/**
 * Created by lenovo on 2014/6/15.
 */
public class IOHelper {
    public static byte[] obj2Bytes(Object obj){
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        ObjectOutputStream oos= null;
        try {
            oos = new ObjectOutputStream(out);
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static Object bytes2Obj(byte[] bytes) throws ClassNotFoundException {
        ByteArrayInputStream in=new ByteArrayInputStream(bytes);
        ObjectInputStream ois= null;
        try {
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
