package com.dianping.sansi.sansilab.component.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by sansi on 2014/5/22.
 */
public class FileSystemUtils {
    public static File[] listDir(File file) throws FileNotFoundException, NotDirectoryException {
        if(file==null){
            throw new FileNotFoundException();
        }

        if(!file.isDirectory()){
            throw new NotDirectoryException(file.getAbsolutePath());
        }

        return file.listFiles();
    }

    public static File[] listDir(String path) throws FileNotFoundException, NotDirectoryException {
        File dir=new File(path);
        return listDir(dir);
    }

    public static File[] listRoots(){
        File[] roots= File.listRoots();
        return roots;
    }
}
