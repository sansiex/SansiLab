package com.dianping.sansi.sansilab.component.file;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by sansi on 2014/5/22.
 */
public class FileSystemUtils {
    public static File[] listDir(File file) throws FileNotFoundException, NotDirectoryException, AccessDeniedException {
        if(file==null){
            throw new FileNotFoundException();
        }

        if(!file.isDirectory()){
            throw new NotDirectoryException(file.getAbsolutePath());
        }

        File[] ret = file.listFiles();
        if(ret==null){
            throw new AccessDeniedException(file.getAbsolutePath());
        }
        return ret;
    }

    public static File[] listDir(String path) throws FileNotFoundException, NotDirectoryException, AccessDeniedException {
        File dir=new File(path);
        return listDir(dir);
    }

    public static File[] listRoots(){
        File[] roots= File.listRoots();
        return roots;
    }

    public static void moveFile(File src, String dest) throws FileNotFoundException, AccessDeniedException {
        if(src==null || dest==null){
            throw new InvalidParameterException();
        }
        if( !src.exists()){
            throw new FileNotFoundException();
        }
        File d=new File(dest);
        File p=d.getParentFile();
        if(!p.exists()){
            p.mkdir();
        }
        boolean res = src.renameTo(new File(dest));
        if(!res){
            throw new AccessDeniedException(dest);
        }
    }

    public static void copyFile(File src, String dest) throws InvalidParameterException, IOException {
        if(src==null || dest==null){
            throw new InvalidParameterException();
        }
        if(!src.exists()){
            throw new FileNotFoundException();
        }
        File d=new File(dest);
        File p=d.getParentFile();
        if(!p.exists()){
            p.mkdir();
        }

        int bytesum = 0;
        int byteread = 0;
        InputStream inStream = new FileInputStream(src);
        FileOutputStream fs = new FileOutputStream(d);
        byte[] buffer = new byte[2048];
        int length;
        while ((byteread = inStream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
        }
        inStream.close();
    }

    public static void main(String[] args) {
        try {
            copyFile(new File("E:\\def.txt"), "E:\\abc.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
